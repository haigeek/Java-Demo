package com.haigeek.socket.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author zhaohj
 * @date 2019/4/16 下午10:22
 */
public class NioClient {
    private Selector selector;
    private final static int port = 8686;
    private final static int BUF_SIZE = 10240;
    private static ByteBuffer byteBuffer = ByteBuffer.allocate(BUF_SIZE);

    private void  initClient() throws IOException {
        this.selector = Selector.open();
        SocketChannel clientChannel = SocketChannel.open();
        clientChannel.configureBlocking(false);
        clientChannel.connect(new InetSocketAddress(port));
        clientChannel.register(selector, SelectionKey.OP_CONNECT);
        while (true){
            //这是一个阻塞方法，一直等待直到有数据可读，返回值是key的数量（可以有多个）
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isConnectable()){
                    doConnect(key);
                }else if (key.isReadable()){
                    doRead(key);
                }
            }
        }
    }

    public void doConnect(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        if (clientChannel.isConnectionPending()){
            clientChannel.finishConnect();
        }
        clientChannel.configureBlocking(false);
        String info = "服务端你好!!";
        byteBuffer.clear();
        byteBuffer.put(info.getBytes("UTF-8"));
        byteBuffer.flip();
        clientChannel.write(byteBuffer);
        clientChannel.register(key.selector(),SelectionKey.OP_READ);
        //为了和服务端保持通信，所以注册读事件，是为了接受服务端返回的信息
        //clientChannel.close();
    }

    public void doRead(SelectionKey key) throws IOException {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(BUF_SIZE);
        long bytesRead = clientChannel.read(byteBuffer);
        while (bytesRead>0){
            byteBuffer.flip();
            byte[] data = byteBuffer.array();
            String info = new String(data).trim();
            System.out.println("从服务端发送过来的消息是："+info);
            byteBuffer.clear();
            bytesRead = clientChannel.read(byteBuffer);
        }
        clientChannel.register(key.selector(),SelectionKey.OP_WRITE);
    }

    public static void main(String[] args) throws IOException {
        NioClient myNioClient = new NioClient();
        myNioClient.initClient();
    }
}
