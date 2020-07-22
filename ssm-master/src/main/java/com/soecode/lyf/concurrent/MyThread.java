package com.soecode.lyf.concurrent;

import org.junit.Test;

/**
 * Thread类本质上是实现了Runnable接口的一个实例，代表一个线程的实例。
 * 启动线程的唯一方法就是通过Thread类的start()实例方法。
 * start()方法是一个native方法，它将启动一个新线程，并执行run()方法
 * @author Administrator
 *
 */
public class MyThread extends   Thread{
	@Override
	public void run() {
		System.out.println("1231");
	}
	
	@Test
	public void aa() throws InterruptedException{
		MyThread my=new MyThread();
		my.start();
		//使当前的线程休眠，状态由运行态转变为可运行态，让出CPU执行权。等待休眠时间结束，再次运行。（线程虽然休眠了 但并没有释放锁）
		//my.sleep();
		//等待调用该方法的线程执行完毕之后，再执行其他线程，其内部是wait实现的，因此它会释放对象锁,可以保证线程的顺序性
		my.join();
		//随意唤醒一个等待的线程
		my.notify();
		//将所有等待的线程均唤醒
		my.notifyAll();
		
	}
}
