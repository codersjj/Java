package cn.kgc.strdemo;

// 字符串拆分方法：split()
public class StrDemo6 {
	public static void main(String[] args) {
		String originalLyrics = "长亭外 古道边 芳草碧连天 晚风扶 柳笛声残 夕阳山外山";
		String[] changedLyrics = new String[100];
		
		System.out.println("***原歌词格式***");
		System.out.println(originalLyrics);
		changedLyrics = originalLyrics.split(" ");
		System.out.println("***拆分后歌词格式***");
		for (String lyrics : changedLyrics) {
			System.out.println(lyrics);
		}
	}
}
