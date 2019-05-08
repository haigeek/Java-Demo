package com.haigeek.netty.socket.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author zhaohj
 * @date 2019/5/8 下午2:41
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //新加入的Channel
        Channel incoming = ctx.channel();
        //给所有Channel发出上线消息
        for(Channel channel: channels){
            channel.writeAndFlush("[SERVER] - "+incoming.remoteAddress()+"加入\n");
        }
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        //给所有Channel发下线消息
        for(Channel channel: channels){
            channel.writeAndFlush("[SERVER] - "+incoming.remoteAddress()+"离开\n");
        }
        channels.remove(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        Channel incoming = ctx.channel();
        for(Channel channel: channels){
            if(channel!=incoming){
                channel.writeAndFlush("["+incoming.remoteAddress()+"] "+s+"\n");
            }
            else{
                channel.writeAndFlush("[YOU] "+s+"\n");
            }

        }
        System.out.println();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:"+ incoming.remoteAddress()+"在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:"+ incoming.remoteAddress()+"掉线");
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:"+ incoming.remoteAddress()+"异常");
        cause.printStackTrace();
        ctx.close();
    }
}
