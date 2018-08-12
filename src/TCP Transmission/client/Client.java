package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client implements Runnable {

        // 客户端socket
        private Socket s;

        public Client (String host,int port) throws IOException {
            s = new Socket(host, port);
    }

        public void run()  {

            // line 用于接收键盘录入
            String line = null;
            // 获取键盘录入
            BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

            try {
                // 获取Socket 输出流
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);

                // 获取Socket 输入流，读取服务端的反馈信息
                BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));

                // 不断从键盘读入输入存到line，并发送到服务端
                while ((line = bufr.readLine()) != null) {
                    // 如果输入的是“over”，跳出循环，客户端运行结束
                    if ("over".equals(line)) {
                        System.out.println("客户端关闭！");
                        break;
                    }
                    // 将键盘录入的文本数据发送到服务端
                    out.println(line);

                    // 读取并打印服务端的反馈信息
                    System.out.println(bufIn.readLine()+"\n");
                }
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


}
