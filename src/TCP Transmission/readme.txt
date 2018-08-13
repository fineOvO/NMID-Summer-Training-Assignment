	server包中有三个类，实现了同时并发为多个客户端提供服务。 
	（1） Server类的实例为服务器对象，每当有客户端请求连接时，创建一个用户 线程为其提供服务；
	（2） UserThread类描述用户线程，封装了为一个客户端提供服务的处理流程；
	（3） TestServer类为调用Server类的启动类。

	client包中有两个类。 
	（1） Client类描述TCP客户端；
	（2） TestClient类为调用Client类的启动类。
