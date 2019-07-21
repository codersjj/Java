package ThreadDemo3;

public class Test {
	public static void main(String[] args) {
		// 1.创建线程对象
		Runnable myRunnable = new MyRunnable();
		Thread t = new Thread(myRunnable, "MyThread");
		// 2.启动线程
		t.start(); 
	}
}
