import java.util.ArrayList;

//新闻管理
public class ArrayListDemo {
	public static void main(String[] args) {
		//集合里存储多条新闻标题
		NewsTitle title1 = new NewsTitle(1, "南京下雨了1", "admin");
		NewsTitle title2 = new NewsTitle(2, "南京下雨了2", "admin");
		NewsTitle title3 = new NewsTitle(3, "南京下雨了3", "admin");
		NewsTitle title4 = new NewsTitle(4, "南京下雨了4", "admin");
		NewsTitle title5 = new NewsTitle(5, "南京下雨了5", "admin");
		
		ArrayList list = new ArrayList();
		//添加元素到集合中
		list.add(title1);
		list.add(title2);
		list.add(title3);
		list.add(title4);
		
		//size()为获取集合的长度（Returns the number of elements in this list.）
		System.out.println("新闻标题的总数：" + list.size());
		
		//遍历list，取出每条新闻标题的题目
		for(int i = 0; i < list.size(); i++){
			NewsTitle title = (NewsTitle)list.get(i);
			System.out.println(title.getTitle());
		}
		
		System.out.println("*******");
		
		//增强型for循环
		for(Object obj : list){
			NewsTitle title = (NewsTitle)obj;
			System.out.println(title.getTitle());
		}
	}
}
