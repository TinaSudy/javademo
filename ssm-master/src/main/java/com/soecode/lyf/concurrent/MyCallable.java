package com.soecode.lyf.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;


/**
 * 有返回的多线程
 * @author Administrator
 *
 */
public class MyCallable implements Callable{

	@Override
	public Object call() throws Exception {
		String[] strs = new String[]{"java", "pyhton", "html", "orcal", "tomcat"};
		String result = strs[(int) (Math.random()*5)];
		return result;
	}
	
	@Test
	public void aa() throws InterruptedException, ExecutionException{
		Callable<String> call=new MyCallable();
		 // 交给任务管理器
		FutureTask<String> task = new FutureTask<String>(call);
		 // 创建代理类并启动线程
		 new Thread(task).start();
		 System.out.println("获取结果-->" + task.get());
		 System.out.println("任务是否执行完成-->" + task.isDone());
		
		
	}

}
