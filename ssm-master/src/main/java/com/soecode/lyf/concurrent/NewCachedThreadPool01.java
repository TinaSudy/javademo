package com.soecode.lyf.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Test;


/**
 * 
 * @author Administrator
 * execute和submit方法区别
 * 	1：execute没有返回值,submit有返回值;
 *  2:submit依然调用了execute方法,execute提交任务用的task,submit提交任务使用FutureTask;
 *  3:execute在执行任务时，如果遇到异常会直接抛出，而submit不会直接抛出，只有在使用Future的get方法获取返回值时，才会抛出异常。
 *
 */
public class NewCachedThreadPool01 {
	
	@Test
	public void test01(){
		ExecutorService  executor01=Executors.newCachedThreadPool(); 
		//无返回值
		executor01.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("开始---");
				int i=1/0;
				System.out.println("结束---");
				
			}
		});
		executor01.shutdown();
	}
	
	/**
	 *  实现Callable接口回去返回值
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void test02() throws InterruptedException, ExecutionException{
		ExecutorService  executor01=Executors.newCachedThreadPool(); 
		Future fu=executor01.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("开始---");
				int i=1/0;
				System.out.println("结束---");
				return "哈哈哈";
			}
		});
		executor01.shutdown();
		//System.out.println(fu.get()+"===");
	}
	
	@Test
	public void test03() throws InterruptedException, ExecutionException{
		ScheduledExecutorService   executor01=Executors.newScheduledThreadPool(1);
		executor01.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("延迟3秒执行");
			}
		}, 3, TimeUnit.SECONDS);
		executor01.shutdown();
		System.out.println("123");
	}
	
	@Test
	public void test04() throws InterruptedException, ExecutionException{
		 ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		    for (int i = 0; i < 3; i++) {
		        final int index = i;
		        singleThreadExecutor.execute(new Runnable() {
		            @Override
		            public void run() {
		                System.out.println(Thread.currentThread().getName()+", index="+index);
		            }
		        });

		        try {
		            Thread.sleep(1000);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
	}

}
