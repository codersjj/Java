package ThreadPoolDemo4;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 使用newScheduledThreadPool()方法创建线程池
 * @author Shane
 *
 */
public class Test {
	public static void main(String[] args) {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
		System.out.println("*******开始执行*******");
		scheduledThreadPool.scheduleAtFixedRate(new MyRunnable(), 5, 2, TimeUnit.SECONDS);
	}
}

class MyRunnable implements Runnable {
	public void run() {
		System.out.println(Thread.currentThread().getName() + "延时5s执行，每2s执行一次！");
	}
	
}