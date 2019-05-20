package com.kgc.animal;

public class Test {
	public static void main(String[] args) {
		Host host = new Host();
		Animal animal = host.sendAnimal1("dog");
		animal.shout();
	}
}
