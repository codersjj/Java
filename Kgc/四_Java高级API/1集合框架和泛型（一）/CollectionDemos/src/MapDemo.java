import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import sun.swing.text.CountingPrintable;

// 存储国家英文简称-国家全称（键-值对）
public class MapDemo {
	public static void main(String[] args) {
		Map countries = new HashMap();
		// 往Map集合中添加键值对
		countries.put("CN", "中华人民共和国");
		countries.put("RU", "俄罗斯联邦");
		countries.put("FR", "法兰西共和国");
		countries.put("US", "美利坚合众国");
		
		// 获取Map的元素组（对）数
		System.out.println(countries.size());
		
		// 通过某个键获取对应的值
		String cnStr = (String)countries.get("CN");
		System.out.println(cnStr);
		
		// 判断map中是否包含某个键
		boolean flag = countries.containsKey("US");
		System.out.println("集合中是否包含US？" + flag);
		
		// 删除特定键对应的键值对
		countries.remove("US");
		System.out.println(countries.size());
		
		flag = countries.containsKey("US");
		System.out.println("集合中是否包含US？" + flag);
		
		// 分别显示map中键的集合、值的集合和键值对的集合
		System.out.println(countries.keySet());
		System.out.println(countries.values());
		System.out.println(countries);
		
		// 清空
		/*countries.clear();
		if(countries.isEmpty()) {
			System.out.println("Map数据已经清空");
		}*/
		
		System.out.println("*******");
		// 分别获取Map中的键和值
		// （1）先获取到每个key，然后根据每个key拿到相应的value。
		Set keys = countries.keySet();
		// 方法一：使用增强型for拿到每个key
		for(Object obj : keys){
			String key = (String)obj;
			String value = (String)countries.get(key);
			System.out.println(key + "-" + value);
		}
		System.out.println("*******");
		// 方法二：使用Iterator迭代器，拿到每个key
		Iterator itor = keys.iterator();
		while(itor.hasNext()){
			String key = (String)itor.next();
			String value = (String)countries.get(key);
			System.out.println(key + "-" + value);
		}
	}
}
