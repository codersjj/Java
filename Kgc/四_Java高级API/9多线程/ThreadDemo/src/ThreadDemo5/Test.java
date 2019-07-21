package ThreadDemo5;
/**
 * 设置线程优先级
 * @author Shane
 *
 */
public class Test {
	public static void main(String[] args) {
		Thread t1 = new Thread(new MyRunnable(), "线程a");
		Thread t2 = new Thread(new MyRunnable(), "线程b");
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MIN_PRIORITY);
		t1.start();
		t2.start();
	}
}
