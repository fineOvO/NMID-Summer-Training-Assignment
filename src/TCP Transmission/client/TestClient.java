package client;

import java.net.InetAddress;

public class TestClient {
    public static void main (String[] args) throws Exception{

        int remotePort = 10006;
        // 创建客户端对象，绑定的服务端为本地主机
        Client client = new Client(InetAddress.getLocalHost().getHostAddress(), remotePort);
        // 主线程运行客户端
        client.run();
    }
}
