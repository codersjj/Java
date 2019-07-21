package ThreadDemo2;

public class Test {
	public static void main(String[] args) {
		MyThread t1 = new MyThread();
		MyThread t2 = new MyThread();
		/*t1.start(); // 启动线程
		t2.start();*/
		t1.run();
		t2.run(); // 1.只有主线程这一条执行路径  2.依次调用了两次run()方法
	}
}
