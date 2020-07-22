package com.soecode.lyf.quartz.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.soecode.lyf.quartz.QuartzManager;
import com.soecode.lyf.quartz.TaskData;

public class TaskDataImpl   implements TaskData{
	
	QuartzManager  quartzmanager;

	@Override
	public void execute() {
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		 System.out.println("定时任务。。。。。。。。。。。。执行中。。。。。哈哈哈");
		 System.out.println("任务执行时间"+df.format(new Date()));

	}

}
