
public class Producer implements Runnable {
	private Movie movie = null;
	private boolean flag = false;
	public Producer(Movie movie) {
		super();
		this.movie = movie;
	}
	public void run() {
		// 循环录入50遍电影数据，两部电影交替录入
		for (int i = 0; i < 50; i++) {
			if (flag) {
				this.movie.setName("变形金刚");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.movie.setInfo("一部科幻电影！");
				flag = false;
			} else {
				this.movie.setName("神偷奶爸");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.movie.setInfo("一部3D动画电影！");
				flag = true;
			}
		}
	}

}
