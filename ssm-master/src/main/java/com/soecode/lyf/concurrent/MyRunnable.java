package com.soecode.lyf.concurrent;

import org.junit.Test;

public class MyRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("1231");
	}
	
	@Test
	public void aa(){
		MyRunnable my=new MyRunnable();
		my.run();
	}

}
