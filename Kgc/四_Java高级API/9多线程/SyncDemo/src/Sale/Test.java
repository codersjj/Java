package Sale;
/**
 * 模拟多人抢票
 * @author Shane
 *
 */
public class Test {
	public static void main(String[] args) {
		Site site = new Site();
		Thread person1 = new Thread(site, "小七");
		Thread person2 = new Thread(site, "晓");
		Thread person3 = new Thread(site, "qi");
		person1.start();
		person2.start();
		person3.start();
	}
}
