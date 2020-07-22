package com.soecode.lyf;

import java.util.Date;

import org.junit.Test;
import org.quartz.Calendar;
import org.quartz.JobKey;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.soecode.lyf.quartz.QuartzManager;
import com.soecode.lyf.quartz.QuartzTask;
import com.soecode.lyf.quartz.impl.TaskDataImpl;
import com.soecode.lyf.util.CronUtils;


public class QuartzTest {
	
	@Test
	public void bb(){
		System.out.println(CronUtils.getCron(new Date()));
	}
	

	@Test
	public void aa(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/spring-quartz.xml");
        QuartzManager quartzManager = (QuartzManager) ctx.getBean("quartzManager");
        try {
        	//创建任务
            quartzManager.addJob("test", "test", "test", "test", QuartzTask.class, "0 29 17 16 7 ? 2020",new TaskDataImpl());
            //System.out.println(quartzManager.getJobDetail("test", "test"));
            //quartzManager.removeJob("test", "test", "test", "test");
            System.out.println("没跑===获取任务"+quartzManager.getJobDetail("test", "test"));
            
            Thread.sleep(60000);
           
            //修改任务执行时间
            quartzManager.modifyJobTime("test", "test", "test", "test", "0 28 17 16 7 ? 2020");
            
           
            //Thread.sleep(180000);
            System.out.println("程序正常结束===获取任务"+quartzManager.getJobDetail("test", "test"));
            
           
            
           
           
            
/*
            Thread.sleep(5000);
            System.out.println("【修改job1时间】开始(每2秒输出一次)...");
            quartzManager.modifyJobTime("test", "test", "test", "test", "0/2 * * * * ?");
//
            Thread.sleep(10000);
            System.out.println("【移除job1定时】开始...");
            quartzManager.removeJob("test", "test", "test", "test");

            // 关掉任务调度容器
             quartzManager.shutdownJobs();*/
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
