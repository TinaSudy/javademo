package com.springboot.synchronized1;

import org.junit.Test;


public class Synchronized implements Runnable	{
	
	private static int count = 0;

	@Override
	public void run() {
		
		synchronized (this) {
			for (int i=0;i<5;i++){
				System.out.println(Thread.currentThread().getName() + ":" + (count++));
			}
		}
	}
	
	class Parent {
        public synchronized void method() { }
	}
	class Child extends Parent {
        public synchronized void method() { }
	}
	
	@Test
	public void test01() {
		Synchronized syncThread = new Synchronized();
    	Thread thread1 = new Thread(syncThread, "SyncThread1");
    	Thread thread2 = new Thread(syncThread, "SyncThread2");
    	thread1.start();
    	thread2.start();
	}
	
	@Test
	public void test02() {
		Synchronized syncThread = new Synchronized();
		Synchronized syncThread02= new Synchronized();
    	Thread thread1 = new Thread(syncThread, "SyncThread1");
    	Thread thread2 = new Thread(syncThread02, "SyncThread2");
    	thread1.start();
    	thread2.start();
	}


}
