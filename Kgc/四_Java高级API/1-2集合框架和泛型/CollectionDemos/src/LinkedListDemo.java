import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {
	public static void main(String[] args) {
		//集合里存储多条新闻标题
		NewsTitle title1 = new NewsTitle(1, "南京下雨了1", "admin");
		NewsTitle title2 = new NewsTitle(2, "南京下雨了2", "admin");
		NewsTitle title3 = new NewsTitle(3, "南京下雨了3", "admin");
		NewsTitle title4 = new NewsTitle(4, "南京下雨了4", "admin");
		NewsTitle title5 = new NewsTitle(5, "南京下雨了5", "admin");
		NewsTitle title6 = new NewsTitle(6, "南京下雨了6", "admin");
		NewsTitle title7 = new NewsTitle(7, "南京下雨了7", "admin");
		
		//注意：这样声明，无法使用LinkedList独有的方法
//		List list = new LinkedList(); // 父类引用指向子类对象，这样做会导致list调用不了子类独有的方法（比如这里调用LinkedList的6个独有方法时会报错）
		LinkedList list = new LinkedList();
		list.add(title1);
		list.add(title2);
		list.add(title3);
		list.add(1, title4);
		
		/* 添加头和尾 */
		list.addFirst(title5); // LinkedList特有的方法
		list.addLast(title6); // LinkedList特有的方法
		
		list.add(title7);
//		System.out.println(list.size());
		
		for(int i = 0; i < list.size(); i++){
			NewsTitle title = (NewsTitle)list.get(i);
			System.out.println(title.getId() + "-" + title.getTitle());
		}
		
		System.out.println("*******");
		
		for(Object obj : list){
			NewsTitle title = (NewsTitle)obj;
			System.out.println(title.getId() + "-" + title.getTitle());
		}
		
		/* 删除头和尾 */
		list.removeFirst();
		System.out.println("*******");
		for(Object obj : list){
			NewsTitle title = (NewsTitle)obj;
			System.out.println(title.getId() + "-" + title.getTitle()); 
		}
		
		list.removeLast();
		System.out.println("*******");
		for(Object obj : list){
			NewsTitle title = (NewsTitle) obj;
			System.out.println(title.getId() + "-" + title.getTitle());
		}
		
		System.out.println("*******");
		
		/* 获得头和尾 */
		NewsTitle firstName = (NewsTitle)list.getFirst();
		System.out.println("头条新闻标题：" + firstName.getTitle());
		NewsTitle lastName = (NewsTitle)list.getLast();
		System.out.println("末条新闻标题：" + lastName.getTitle());
	}
}
