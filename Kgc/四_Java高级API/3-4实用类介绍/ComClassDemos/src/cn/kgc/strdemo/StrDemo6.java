package cn.kgc.strdemo;

import java.util.Scanner;

// 字符串拆分方法：split()
public class StrDemo6 {
	public static void main(String[] args) {
		/*String originalLyrics = "长亭外 古道边 芳草碧连天 晚风扶 柳笛声残 夕阳山外山";
		String[] changedLyrics = new String[100];
		
		System.out.println("***原歌词格式***");
		System.out.println(originalLyrics);
		changedLyrics = originalLyrics.split(" ");
		System.out.println("***拆分后歌词格式***");
		for (String lyrics : changedLyrics) {
			System.out.println(lyrics);
		}*/
		
		Scanner input = new Scanner(System.in);
		System.out.println("请输入一个字符串：");
		String info = input.next();
		System.out.println("请输入您要查询的字符：");
		String want = input.next();
//		String[] splitedInfos = info.split("-");
		String[] splitedInfos = new String[info.length()];
		
		// 特定字符出现的次数
		int count = 0;
		for (int i = 0; i < splitedInfos.length; i++) {
			splitedInfos[i] = info.substring(i, i + 1);
			if (splitedInfos[i].equals(want)) {
				count++;
			}
		}

		System.out.println("\"" + want + "\"在\"" + info + "\"中" + "出现的次数为：" + count + "次。"); // 转义符号：\
		
	}
}
