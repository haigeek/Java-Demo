package com.haigeek.netty.socket.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author zhaohj
 * @date 2019/5/7 下午10:27
 */
public class ChatClient {
    private String host;
    private int port;
    public ChatClient(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception{
        EventLoopGroup worker = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new ChatClientInitializer());
            Channel channel = b.connect(host, port).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                channel.writeAndFlush(in.readLine()+"\r\n");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            worker.shutdownGracefully();
        }
    }
    public static void main(String[] args)throws Exception{
        String host = "127.0.0.1";
        int port = 8080;
        new ChatClient(host, port).run();

    }
}
