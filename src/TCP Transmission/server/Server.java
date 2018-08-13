package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server  {

    // 创建服务端Socket服务
    private ServerSocket serverSocket;
    // 初始化服务端对象时让其监听一个指定的端口
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }
    // 运行服务端
    public void serverRun() throws Exception {

        System.out.println("服务端开始运行！");
        //多线程，多个客户端并发登录
        while(true)
        {
            // 每有一个客户端请求连接，分配一个用户线程为其服务
            Socket socket = serverSocket.accept();
            new Thread(new UserThread(socket)).start();
        }
    }
}



