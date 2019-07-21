package ThreadDemo4;

public class MyRunnable implements Runnable {
	public void run(){
		System.out.println("线程t正在运行！");
		try {
			Thread.sleep(500);
			System.out.println("线程t休眠，处于阻塞状态！");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("线程t被中断！");
		}
	}
	
	public static void main(String[] args){
		Thread t = new Thread(new MyRunnable(), "t");
		System.out.println("线程t处于新建状态！");
		t.start();
		System.out.println("线程t处于就绪状态！");
	}
}
