package DeadLock;
/**
 * 模拟死锁
 * @author Shane
 *
 */
public class Test {
	public static void main(String[] args) {
		Object bobby = new Object();
		Object duck = new Object();
		// 下面两个线程共同操作同一个资源，都在等待对方先完成，造成程序的停滞，产生了死锁现象。
		Thread tangtang = new Thread(new Tangtang(bobby, duck));
		Thread doudou = new Thread(new Doudou(bobby, duck));
		tangtang.start();
		doudou.start();
	}
}

class Tangtang implements Runnable{
	Object bobby; // 芭比
	Object duck; // 玩具鸭
	public Tangtang(Object bobby, Object duck) {
		super();
		this.bobby = bobby;
		this.duck = duck;
	}
	public void run() {
		synchronized(bobby) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (duck) {
				
			}
			System.out.println("糖糖把芭比给豆豆玩！");
		}
	}
}

class Doudou implements Runnable{
	Object bobby; // 芭比
	Object duck; // 玩具鸭
	public Doudou(Object bobby, Object duck) {
		super();
		this.bobby = bobby;
		this.duck = duck;
	}
	public void run() {
		synchronized(duck) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (bobby) {
				
			}
			System.out.println("豆豆把玩具鸭给糖糖玩！");
		}
	}
	
}
