package cn.kgc.enumdemos;

// 根据用户输入的数字（1-7），返回对应日期可以做什么事情。
public class Test {
	public void doWhat(int day) {
		if (day < 1 || day > 7) {
			System.out.println("请输入1-7之间的整数！");
			return;
		} else {
			switch (day) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				System.out.println("工作日，要好好敲代码！");
				break;
			case 6:
				System.out.println("周六啦！可以去看个电影！");
				break;
			case 7:
				System.out.println("周日啦！可以睡个懒觉！");
				break;
			}
		}
	}

	public void doWhat2(Week day) {
		switch (day) {
		case MON:
		case TUE:
		case WED:
		case THU:
		case FRI:
			System.out.println("工作日，要好好敲代码！");
			break;
		case SAT:
			System.out.println("周六啦！可以去看个电影！");
			break;
		case SUN:
			System.out.println("周日啦！可以睡个懒觉！");
			break;
		}
	}

	public static void main(String[] args) {
		Test t = new Test();
//		t.doWhat(7);
		t.doWhat2(Week.SUN);
	}
}
