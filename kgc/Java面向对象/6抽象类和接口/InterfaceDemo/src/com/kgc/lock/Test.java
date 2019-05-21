package com.kgc.lock;

public class Test {
	public static void main(String[] args) {
		TheftProofDoor door = new TheftProofDoor();
		door.close();
		door.lockUp();
		
		door.takePhoto();
		
		door.lockOpen();
		door.open();
	}
}
