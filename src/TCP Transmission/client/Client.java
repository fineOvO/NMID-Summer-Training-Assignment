package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client  {

        // 客户端socket
        private Socket socket;

        public Client (String host,int port) throws IOException {
            socket = new Socket(host, port);
    }

        public void clientRun() throws IOException {

            // line 用于接收键盘录入
            String line = null;
            // 获取键盘输入的流
            BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

            try {
                // 获取Socket 输出流
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                // 获取Socket 输入流，读取服务端的反馈信息
                BufferedReader bufIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                System.out.println("\n请开始输入消息：（输入“/over”结束本次服务）\n");
                // 不断从键盘读入输入存到line，并发送到服务端
                while ((line = bufr.readLine()) != null) {
                    // 如果输入的是“/over”，跳出循环，客户端运行结束
                    if ("/over".equals(line)) {
                        System.out.println("客户端关闭！");
                        break;
                    }
                    // 将键盘录入的文本数据发送到服务端
                    out.println(line);

                    // 读取并打印服务端的反馈信息
                    System.out.println(bufIn.readLine()+"\n");
                }

            }
            finally {
                socket.close();
            }

        }

}
