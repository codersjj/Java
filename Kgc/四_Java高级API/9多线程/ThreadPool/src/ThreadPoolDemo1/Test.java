package ThreadPoolDemo1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用newCachedThreadPool()方法创建线程池
 * @author Shane
 *
 */
public class Test {
	public static void main(String[] args) {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		
		// 在线程池中执行10个任务（输出结果：10个任务都被1个线程执行了）
		for (int i = 0; i < 10; i++) {
			cachedThreadPool.execute(new MyRunnable(i));
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyRunnable implements Runnable {
	int num;
	public MyRunnable(int num) {
		super();
		this.num = num;
	}
	public void run() {
		System.out.println(Thread.currentThread().getName() + ":" + num);
	}
	
}