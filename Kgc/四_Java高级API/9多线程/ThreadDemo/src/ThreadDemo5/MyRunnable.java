package ThreadDemo5;
/**
 * 实现Runnable接口方式创建线程
 * @author Shane
 *
 */
public class MyRunnable implements Runnable {

	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + ":" + i);
		}
		
	}

}
