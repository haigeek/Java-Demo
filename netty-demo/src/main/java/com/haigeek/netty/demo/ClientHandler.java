package com.haigeek.netty.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author zhaohj
 * @date 2019/5/8 下午1:24
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private final ByteBuf firstMessage;

    public ClientHandler(){
        firstMessage= Unpooled.buffer(Client.SIZE);
        for(int i=0;i<firstMessage.capacity();i++){
            firstMessage.writeByte((byte)i);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
