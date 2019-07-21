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
				this.movie.set("变形金刚", "一部科幻电影！");
				flag = false;
			} else {
				this.movie.set("神偷奶爸", "一部3D动画电影！");
				flag = true;
			}
		}
	}
}
