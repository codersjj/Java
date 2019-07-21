/**
 * 电影类
 * @author Shane
 *
 */
public class Movie {
	private String name;
	private String info;
	private boolean flag = true; // 设置标志位，控制生产者生产，消费者消费。
	public String getName() {
		return name;
	}
	public String getInfo() {
		return info;
	}
	
	// 同步方法
	public synchronized void set(String name, String info){
		if (!flag) { // 若标志位为false
			try {
				super.wait(); // 让当前线程（生产者线程）等待，也就是让消费者线程进行下去。
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.name = name; // 若标志位为true，让生产者生产信息。
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.info = info;
		flag = false; // 重置标志位，让消费者消费。
		super.notify(); // 唤醒消费者的线程
	}
	
	public synchronized void get(){
		if (flag) { // 若标志位为true
			try {
				super.wait(); // 让当前线程（消费者线程）等待，也就是让生产者生产完之后再让消费者消费。
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(this.getName() + "-" + this.getInfo());
		flag = true; // 消费者消费完之后，重置标志位，让生产者生产。
		super.notify(); // 唤醒生产者的线程
	}
}
