package com.haigeek.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author zhaohj
 * @date 2019/5/7 下午10:27
 */
public class Server {

    public final int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        //NioEventLoopGroup是用来处理I/O操作的多线程事件循环器
        //bossGroup主要处理进来的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //workerGroup主要处理已经被接收的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //启动NIO的辅助类
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    //channel接受进来的连接
                    .channel(NioServerSocketChannel.class)
                    //处理一个最近接收的连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ServerHandler());
                        }
                    })
                    //option() 是提供给NioServerSocketChannel 用来接收进来的连接,在此处配置channel参数
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //childOption() 是提供给由父管道 ServerChannel 接收到的连接，在这个例子中也是 NioServerSocketChannel
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync();
            // 等待服务器  socket关闭
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8081;
        }
        new Server(port).run();
    }

}
