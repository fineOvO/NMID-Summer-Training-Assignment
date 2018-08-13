package server;


public class TestServer {
    public static void main (String[] args) throws Exception {

        int localPort = 10006;
        // 创建服务端对象,并绑定其监听的本地端口
        Server server = new Server(localPort);
        // 主线程运行服务端
        server.serverRun();
    }
}
