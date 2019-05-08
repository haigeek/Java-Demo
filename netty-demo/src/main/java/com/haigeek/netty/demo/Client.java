package com.haigeek.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zhaohj
 * @date 2019/5/8 下午1:24
 */
public class Client {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8081"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String [] args) throws Exception{
        EventLoopGroup group=new NioEventLoopGroup();
        try {
            //启动NIO的辅助类
            Bootstrap b = new Bootstrap();
            b.group(group)
                    //channel接受进来的连接
                    .channel(NioSocketChannel.class)
                    //处理一个最近接收的连接
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    })
                    //option() 是提供给NioServerSocketChannel 用来接收进来的连接,在此处配置channel参数
                    .option(ChannelOption.TCP_NODELAY, true);

            // 进行端口连接
            ChannelFuture f = b.connect(HOST, PORT).sync();
            // 等待服务器  socket关闭
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
