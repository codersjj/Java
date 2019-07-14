package cn.kgc.datedemo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDemo {
	public static void main(String[] args) {
		// 当前日期和时间
		Date date = new Date();
		System.out.println(date);
		// 将日期和时间的格式变为我们要求的格式“年-月-日 时：分：秒”。
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sdate = formater.format(date);
		System.out.println(sdate);
	}
}
