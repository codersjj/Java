package cn.kgc.common;

import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		int i = 9;
		System.out.println(i);
		
		List list = new ArrayList();
		list.add(i); // 这里可以加类型为基本数据类型的变量i，why???   答：因为jdk5以后有了自动装箱拆箱，这里的i自动装箱变成了Integer（属于Number类型（属于Object类型 ））类型了。
	}
}
