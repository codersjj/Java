package ThreadDemo1;
/**
 * 获取和设置主线程名字
 * @author Shane
 *
 */
public class Test {
	public static void main(String[] args) {
		// 1.获取主线程对象
		Thread t = Thread.currentThread();
		System.out.println("当前的线程是：" + t.getName());
		t.setName("MyJavaThread");
		System.out.println("更改名称后，当前的线程是：" + t.getName());
	}
}
