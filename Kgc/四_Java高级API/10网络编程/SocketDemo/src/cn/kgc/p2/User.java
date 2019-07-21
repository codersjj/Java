package cn.kgc.p2;

import java.io.Serializable;

// ”√ªß
public class User implements Serializable {
	private String userName;
	private String pwd;

	public User() {}

	public User(String userName, String pwd) {
		this.userName = userName;
		this.pwd = pwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
