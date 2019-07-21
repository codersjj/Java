/**
 * 电影类
 * @author Shane
 *
 */
public class Movie {
	private String name;
	private String info;
	public String getName() {
		return name;
	}
	public String getInfo() {
		return info;
	}
	
	// 同步方法
	public synchronized void set(String name, String info){
		this.name = name;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.info = info;
	}
	
	public synchronized void get(){
		System.out.println(this.getName() + "-" + this.getInfo());
	}
}
