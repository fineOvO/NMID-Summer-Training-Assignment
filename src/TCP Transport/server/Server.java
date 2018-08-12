package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.text.*;

public class Server implements Runnable {

    // 定义消息记录文件的路径
    private String path = "D:/Server.txt";

    // 创建服务端 SeverSocket
    private ServerSocket ss;
    public Server(int port) throws IOException {
        // 创建 SeverSocket对象并让其监听一个指定的端口
        ss = new ServerSocket(port);
    }

    public void run() {

        try {
            // 尝试和一个客户端连接
            Socket s = ss.accept();

            // 连接成功后获取客户端 IP地址并在服务端打印
            String ip = s.getInetAddress().getHostAddress();
            System.out.println("\n"+ip + "......connected"+"\n");

            // 获取连接的客户端的端口号
            int port = s.getLocalPort();

            // 获取连接的客户端的socket读取流，并装饰
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // 获取连接的客户端的socket输出流，并装饰
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            // line用于存储接收到的消息
            String line = null;


            // 定义 换行输入
            String toNextLine = System.getProperty("line.separator");

            // date用于存储接收到一条消息的时间
            Date date;

            // 定义时间格式化模式
            String datePattern = "yyyy-MM-dd HH:mm:ss";

            // 定义时间格式化对象
            SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

            // time 用于存储格式化后的时间
            String time;

            // 这条消息的相关信息，包括  发送时间 + IP + 端口
            String messgeINFO = null;

            // 用于存储一条记录，包括 消息发送信息 + 正文
            String record = null;

            while ((line = bufIn.readLine()) != null) {
                // 记录收到消息的时间
                date = new Date();
                // 格式化时间
                time = dateFormat.format(date);
                messgeINFO = time+"   "+ip+"   "+port;
                // 服务端控制台打印收到的数据和客户端的相关信息
                System.out.println(messgeINFO+"\n"+line+"\n");

                record = messgeINFO+"       "+line;
                byte[] sourceByte = record.getBytes();
                try {
                    //文件路径（路径+文件名）
                    File file = new File(path);
                    //文件输出流用于将数据写入文件
                    FileOutputStream outStream = new FileOutputStream(file,true);

                    if (!file.exists()) {	//文件不存在则创建文件，先创建目录
                        File dir = new File(file.getParent());
                        dir.mkdirs();
                        file.createNewFile();
                    }

                    // 写入目标数据
                    outStream.write(sourceByte);
                    // 换行
                    outStream.write(toNextLine.getBytes());
                    // 消息记录成功后，向客户端反馈
                    out.println(time+"  服务端消息录入成功！");
                    //关闭文件输出流
                    outStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            s.close();
            ss.close();
            System.out.println("服务端关闭！");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
