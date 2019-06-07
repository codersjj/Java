import java.util.ArrayList;
import java.util.List;

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
//		List list = new ArrayList(); // 父类引用指向子类对象（可以用，但这样写就只能调用List和ArrayList公有的方法，而不能调用子类特有的方法）
		
		//添加元素到集合中
		//集合里存的是对象（如果是基本数据类型，会被包装成对象）
		list.add(title1);
		list.add(title2);
		list.add(title3);
		list.add(title4);
		list.add(1, title5); // 在指定位置插入元素
		
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
		
		System.out.println("*******");
		
		//contains()、remove()
		System.out.println(list.contains(title1));
		//删除一条
		list.remove(title1);
		System.out.println(list.contains(title1));
		//打印删除后的list长度
		System.out.println(list.size());
		System.out.println("*******");
		//清空集合
		list.clear();
		System.out.println(list.size());
		//判断集合是否为空
		System.out.println(list.isEmpty());
	}
}
