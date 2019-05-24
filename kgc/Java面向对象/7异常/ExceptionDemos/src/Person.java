
public class Person {
	//封装：属性私有，方法公开。
	private String name;
	private int age;
	private String sex;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) throws Exception { //throws是声明（告诉别人）此方法抛出的异常
		if(sex.equals("男") || sex.equals("女")){
			this.sex = sex;
		}else{
			//抛出异常（throw是真的抛出异常），一旦有了throw，就必须要有throws或者有try-catch。
			throw new Exception("性别只能为男女！"); 
			//	Exception(String message)
			// 		Constructs a new exception with the specified detail message.
		}
	}

	//输出个人信息
	public void print(){
		System.out.println(this.getName() + "-" + this.getSex() + "-" + this.getAge());
	}
	
	public static void main(String[] args) {
		Person p = new Person();
		try{
			p.setSex("boy");
			p.print();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
