
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

	public void setSex(String sex) throws GenderException { //throws是声明（告诉别人）此方法抛出的异常
		if(sex.equals("男") || sex.equals("女")){
			this.sex = sex;
		}else{
			//抛出自定义异常（throw是真的抛出异常），一旦有了throw，就必须要有throws或者有try-catch。
			throw new GenderException("性别只能为男女！"); 
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
		}catch(GenderException e){
			e.printStackTrace();
		}
	}
}
