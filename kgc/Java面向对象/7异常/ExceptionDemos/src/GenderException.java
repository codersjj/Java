// 程序中只要性别输入错误，都会引起本自定义异常GenderException。
public class GenderException extends Exception { // 定义一个异常类，让它继承父类Exception
	// 添加构造方法
	public GenderException(){}
	public GenderException(String message){
		super(message); // 调用父类Exception中已经有的构造方法以实现Constructs a new exception with the specified detail message.
	}
}
