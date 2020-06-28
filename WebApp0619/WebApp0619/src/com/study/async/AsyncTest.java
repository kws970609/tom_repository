package com.study.async;

public class AsyncTest {
	Thread thread;
	
	public AsyncTest() {
		thread = new Thread() {
			
			public void run() {
				System.out.println("A");
			}
		};
		thread.start();
		
		System.out.println("B");
	}
	
	public static void main(String[] args) {
		new AsyncTest();
	}
}






















