package com.soecode.lyf.quartz;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzTask implements Job{

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		  //执行任务
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        TaskData quartzTask = (TaskData)dataMap.get("task");
        // 调用接口函数
        quartzTask.execute();
	}
	

}
