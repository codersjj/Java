package cn.kgc.datedemo;

import java.util.Calendar;

public class DateDemo2 {
	public static void main(String[] args){
		// 日期和时间
		Calendar t = Calendar.getInstance(); // Calendar类是一个抽象类，没法new，需要通过它的一个静态方法getInstance()获取一个Calendar对象。
		System.out.println(t.get(Calendar.YEAR) + "-" + (t.get(Calendar.MONTH) + 1) + "-" + t.get(Calendar.DAY_OF_MONTH));
		System.out.println("今天是星期" + (t.get(Calendar.DAY_OF_WEEK) - 1));
	}
}
