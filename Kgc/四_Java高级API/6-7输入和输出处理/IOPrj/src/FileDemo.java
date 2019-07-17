import java.io.File;
import java.io.IOException;

// 文件操作：创建文件、查看文件相关信息、删除文件
public class FileDemo {
	// c:\1\2\3\1.txt	绝对路径
	// 2\3\1.txt 		相对路径
	
	// 创建文件
	public void create(File file){
		if (!file.exists()) {
			try {
				file.createNewFile(); // 创建文件，不创建文件夹（若路径中的文件夹不存在，则会报错）
				System.out.println("文件已创建！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	// 查看文件相关信息
	public void showFileInfo(File file){
		if (file.exists()) {
			// 如果文件存在，则查看其信息
			if (file.isFile()) {
				// 是文件
				System.out.println("该文件名是：" + file.getName());
				System.out.println("相对路径：" + file.getPath());
				System.out.println("绝对路径：" + file.getAbsolutePath());
				System.out.println("文件大小：" + file.length() + "字节");
			}
			if (file.isDirectory()) {
				System.out.println("此文件是目录！");
			}
		} else {
			System.out.println("文件不存在！");
		}
	}
	
	// 删除文件
	public void delete(File file){
		if (file.exists()) {
			file.delete();
			System.out.println("文件已删除！");
		}
	}
	
	public static void main(String[] args){
		FileDemo fileDemo = new FileDemo();
		File file = new File("d:/JJSha/text.txt");
//		File file = new File("test.txt");
//		fileDemo.create(file); // 文件不存在时创建，存在就不执行这句代码
		fileDemo.showFileInfo(file);
		
//		fileDemo.delete(file);
	}
}
