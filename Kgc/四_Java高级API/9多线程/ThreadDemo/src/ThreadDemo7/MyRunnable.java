package ThreadDemo7;

public class MyRunnable implements Runnable {
	public void run(){
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "ÔËÐÐ£º" + i);
		}
	}
}
