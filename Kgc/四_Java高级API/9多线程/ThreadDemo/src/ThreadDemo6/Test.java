package ThreadDemo6;
/**
 * 主线程休眠5秒
 * @author Shane
 *
 */
public class Test {
	public static void main(String[] args) {
		System.out.println("-------主线程开始休眠-------");
		Wait.bySec(5); // 让主线程休眠5秒
		System.out.println("-------主线程休眠结束-------");
	}
}
