package com.haigeek.socket.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhaohj
 * @date 2019/4/16 下午9:58
 */
public class NioServer {
    private Selector selector;
    private final static int port = 8686;
    private final static int BUF_SIZE = 10240;
    private void initServer() throws IOException{
        //创建通道管理器对象selector
        this.selector=Selector.open();
        //创建一个通信通道
        ServerSocketChannel channel=ServerSocketChannel.open();
        //设置通道不堵塞
        channel.configureBlocking(false);
        channel.socket().bind(new InetSocketAddress(port));
        //将上述的通道管理器和通道绑定，并为该通道注册OP_ACCEPT事件
        //注册事件后，当该事件到达时，selector.select()会返回（一个key），如果该事件没到达selector.select()会一直阻塞
        SelectionKey selectionKey=channel.register(selector,SelectionKey.OP_ACCEPT);

        while (true){
            //这是一个阻塞方法，一直等待直到有数据可读，返回值是key的数量（可以有多个）
            selector.select();
            Set keys=selector.selectedKeys();
            Iterator iterator=keys.iterator();
            while (iterator.hasNext()){
                SelectionKey key= (SelectionKey) iterator.next();
                iterator.remove();
                if(key.isAcceptable()){
                    doAccept(key);
                }else if (key.isReadable()){
                    doRead(key);
                }else if (key.isWritable() && key.isValid()){
                    doWrite(key);
                }else if (key.isConnectable()){
                    System.out.println("连接成功！");
                }
            }
        }

    }
    public void doAccept(SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        System.out.println("ServerSocketChannel正在循环监听");
        SocketChannel clientChannel = serverChannel.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(key.selector(),SelectionKey.OP_READ);
    }

    public void doRead(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUF_SIZE);
        long bytesRead = clientChannel.read(byteBuffer);
        while (bytesRead>0){
            byteBuffer.flip();
            byte[] data = byteBuffer.array();
            String info = new String(data).trim();
            System.out.println("从客户端发送过来的消息是："+info);
            byteBuffer.clear();
            bytesRead = clientChannel.read(byteBuffer);
        }
        clientChannel.register(key.selector(),SelectionKey.OP_WRITE);
        //为了和客户端保持通信，所以注册写事件，是为了给客户端发欢迎信息
//        if (bytesRead==-1){
//            clientChannel.close();
//        }
    }

    public void doWrite(SelectionKey key) throws IOException {
        String info = "客户端你好!!";
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUF_SIZE);
        SocketChannel clientChannel = (SocketChannel) key.channel();
        byteBuffer.clear();
        byteBuffer.put(info.getBytes("UTF-8"));
        byteBuffer.flip();
        clientChannel.write(byteBuffer);
        clientChannel.register(key.selector(),SelectionKey.OP_READ);
        clientChannel.close();
    }

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer();
        nioServer.initServer();
    }
}
