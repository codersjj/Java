package Sale2;
/**
 * 模拟网站
 * @author Shane
 *
 */
public class Site implements Runnable {
	private int count = 10; // 记录剩余票数
	private int num = 0; // 记录当前抢到第几张票
	private boolean flag = false; // 记录票是否卖完
	public void run() {
		// 循环  当剩余票数为0时结束
		while (!flag) {
			sale(); // 调用同步方法
		}
	}
	
	// 同步方法：售票 
	public synchronized void sale() {
		if (count <= 0) {
			return;
		}
		// 1.修改数据（剩余票数、抢到第几张票）
		count--;
		num++;
		try {
			Thread.sleep(500); // 通过线程休眠模拟网络延迟
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 2.显示信息，反馈用户抢到第几张票
		System.out.println(Thread.currentThread().getName() + "抢到第" + num + "张票，剩余" + count + "张票！");

	}
	
}
