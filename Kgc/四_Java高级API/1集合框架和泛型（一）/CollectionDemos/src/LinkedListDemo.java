import java.util.LinkedList;

public class LinkedListDemo {
	public static void main(String[] args) {
		//集合里存储多条新闻标题
		NewsTitle title1 = new NewsTitle(1, "南京下雨了1", "admin");
		NewsTitle title2 = new NewsTitle(2, "南京下雨了2", "admin");
		NewsTitle title3 = new NewsTitle(3, "南京下雨了3", "admin");
		NewsTitle title4 = new NewsTitle(4, "南京下雨了4", "admin");
		NewsTitle title5 = new NewsTitle(5, "南京下雨了5", "admin");
		
		LinkedList list = new LinkedList();
		list.add(title1);
		list.add(title2);
		list.add(title3);
		list.add(1, title4);
		System.out.println(list.size());
		
		for(int i = 0; i < list.size(); i++){
			NewsTitle title = (NewsTitle)list.get(i);
			System.out.println(title.getId() + "-" + title.getTitle());
		}
		
		System.out.println("*******");
		
		for(Object obj : list){
			NewsTitle title = (NewsTitle)obj;
			System.out.println(title.getId() + "-" + title.getTitle());
		}
	}
}
