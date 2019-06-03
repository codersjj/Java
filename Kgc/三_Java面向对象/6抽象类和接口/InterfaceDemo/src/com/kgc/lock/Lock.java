package com.kgc.lock;

//锁
public interface Lock {
	//上锁
	public void lockUp(); // 因为是接口，所以这里public abstract都可以省略不写
	//开锁
	public void lockOpen(); // 因为是接口，所以这里public abstract都可以省略不写
}
