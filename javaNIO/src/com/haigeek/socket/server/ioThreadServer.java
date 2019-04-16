package com.haigeek.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhaohj
 * @date 2019/4/15 下午10:52
 */
public class ioThreadServer {
    //线程池
    private static ExecutorService executorService= Executors.newCachedThreadPool();
    private static class HandleMsg implements Runnable{

        Socket client;

        public HandleMsg(Socket client){
            this.client=client;
        }

        @Override
        public void run() {
            BufferedReader bufferedReader = null;       //创建字符缓存输入流
            PrintWriter printWriter = null;         //创建字符写入流
            try {
                bufferedReader=new BufferedReader(new InputStreamReader(client.getInputStream()));
                printWriter=new PrintWriter(client.getOutputStream(),true);//true代表实时刷新
                String inputLine=null;
                long a = System.currentTimeMillis();
                while ((inputLine = bufferedReader.readLine())!=null){
                    printWriter.println(inputLine);
                }
                long b = System.currentTimeMillis();
                System.out.println("此线程花费了："+(b-a)+"秒！");
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    bufferedReader.close();
                    printWriter.close();
                    client.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(8081);
        Socket client=null;
        while (true){
            client= serverSocket.accept();
            System.out.println(client.getRemoteSocketAddress()+"客户端连接成功");
            //放入线程池进行处理
            executorService.submit(new HandleMsg(client));
        }
    }
}
