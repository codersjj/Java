import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//测试Set的使用
public class SetDemo {
	public static void main(String[] args) {
		NewsTitle title1 = new NewsTitle(1, "南京下雨了1", "admin");
		NewsTitle title2 = new NewsTitle(2, "南京下雨了2", "admin");
		NewsTitle title3 = new NewsTitle(3, "南京下雨了3", "admin");
		NewsTitle title4 = new NewsTitle(4, "南京下雨了4", "admin");
		NewsTitle title5 = new NewsTitle(5, "南京下雨了5", "admin");
		
		Set set = new HashSet(); // Set是接口，没法new，只能new它的实现类。
		set.add(title1);
		set.add(title2);
		set.add(title3);
		set.add(title4);
		set.add(title5);
		
		System.out.println("新闻总记录数：" + set.size());
		
		/* 遍历无序的集合，可以使用的两个方式：
		 * （1）增强型for
		 * （2）iterator迭代器 */
		
		// 遍历每条新闻信息（增强型for方式）
		// Set，结果顺序是无序的，和添加的顺序不是一样的。
		for(Object obj : set){
			NewsTitle title = (NewsTitle)obj;
			System.out.println(title.getId() + "-" + title.getTitle());
		}
		System.out.println("*******");
		
		// 使用迭代器遍历
		Iterator itor = set.iterator(); // 获得迭代器itor
		// 通过迭代器迭代出集合元素
		while(itor.hasNext()){
			NewsTitle title = (NewsTitle)itor.next();
			System.out.println(title.getId() + "-" + title.getTitle());
		}
	}
}
