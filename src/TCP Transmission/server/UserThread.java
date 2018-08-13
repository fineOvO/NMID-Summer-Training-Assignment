package server;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserThread implements Runnable {

    // 接收一个客户端socket，为其提供服务
    private Socket socket;
    UserThread (Socket socket) {
        this.socket = socket;
    }

    // 服务的客户端IP
    private String ip;
    // 服务的客户端端口号
    private int port;

    public void run () {

        // 连接一个客户端后，获取其IP和端口，并在服务端打印
        ip = socket.getInetAddress().getHostAddress();
        port = socket.getLocalPort();
        System.out.println("\n"+ip+"    "+port+ "......connected"+"\n");

        try { // 开始接收客户端消息
            receiveMess();
        } catch (IOException e) {
            throw new RuntimeException("信息接收异常！");
        }
        finally { // 关闭服务完毕的客户端
                try {
                    socket.close();
                    System.out.println("\n"+ip+"    "+port+ "......offLine"+"\n");
            } catch (IOException e) {
                throw new RuntimeException("客户端关闭时发生异常！");
            }
        }

    }

    private void receiveMess() throws IOException { // 接收并录入一个连入的客户端发来诸消息

        // 定义消息记录文件的路径
        String path = "D:/Server.txt";
        // line用于存储接收到的消息
        String line = null;
        // 一条消息的相关信息，包括  发送时间 + IP + 端口
        String messgeINFO = null;
        // 用于存储一条记录，包括 消息发送信息 + 正文
        String record = null;
        // 获取连接的客户端的socket读取流，并装饰
        BufferedReader bufIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 获取连接的客户端的socket输出流，并装饰
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        while ((line = bufIn.readLine()) != null) { // 接收客户端的一条消息

            // 成功接收一条消息后，向客户端反馈
            out.println(getFormatTime ()+"  服务器：已成功收到消息！");
            // 这条消息的相关信息
            messgeINFO = getFormatTime()+"   "+ip+"   "+port;
            // 服务端控制台打印收到的数据和客户端的相关信息
            System.out.println(messgeINFO+"\n   "+line+"\n");
            //将一条消息记录录入文件
            record = messgeINFO+"       "+line;
            saveRecord (record);
        }

    }

    private void saveRecord (String record) throws IOException {

        byte[] sourceByte = record.getBytes();
        // 定义消息记录文件的路径
        String path = "D:/Server.txt";
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
        String toNextLine = System.getProperty("line.separator");
        outStream.write(toNextLine.getBytes());

        //关闭文件输出流
        outStream.close();
    }

    private String getFormatTime () {
        // 获取当前时间对象
        Date date = new Date();
        // 定义时间格式化模式
        String datePattern = "yyyy-MM-dd HH:mm:ss";
        // 定义时间格式化对象
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        // 返回格式化后的时间
        return dateFormat.format(date);
    }

}
