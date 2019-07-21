package ThreadDemo8;

public class MyRunnable implements Runnable {
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName() + "运行：" + i);
			// 当i == 3时，线程礼让，当前线程将CPU资源让出
			if (i == 3) {
				Thread.yield(); // yield()是静态方法，因此可以直接用类名来调用。
				System.out.print("线程礼让：");
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
