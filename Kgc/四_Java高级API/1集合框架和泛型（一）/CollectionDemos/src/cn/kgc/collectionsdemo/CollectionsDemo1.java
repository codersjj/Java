package cn.kgc.collectionsdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsDemo1 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("animal");
		list.add("pea");
		list.add("beautiful");
		list.add("zero");
		list.add("young");
		list.add("love");
		list.add("slim");
		
		// 打印输出集合中的最大值和最小值
		String strMax = (String)Collections.max(list);
		String strMin = (String)Collections.min(list);
		System.out.println(strMax + "-" + strMin);
		System.out.println("*******");
		
		// 升序排序
		Collections.sort(list);
		for(String str : list){
			System.out.println(str);
		}
		System.out.println("*******");
		
		// 查找
		System.out.println(Collections.binarySearch(list, "love"));
		System.out.println("*******");
		
		// 反转集合元素
		Collections.reverse(list);
		for(String str : list){
			System.out.println(str);			
		}
		
	}
}
