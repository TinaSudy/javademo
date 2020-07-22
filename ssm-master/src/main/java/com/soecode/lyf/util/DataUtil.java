package com.soecode.lyf.util;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataUtil {
	
	
	ExecutorService executorService =null;
	List<String> opneIdList=null;
	Pattern pattern = Pattern.compile("^[^\u4e00-\u9fa5]{28}+$");
	
	public DataUtil(ExecutorService executorService) {
		super();
		this.executorService = executorService;
	}
	public void  dataProcessing(List<String> opneIdList,int executorCount){
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("当前线程号=="+executorCount); 
				List<String> openids=new LinkedList<String>();
				opneIdList.forEach(x->{
					Matcher matcher = pattern.matcher(x);
					if (matcher.find()) {
						System.out.println("匹配");
						openids.add(x);
					}else{
						System.out.println("不匹配");
					}
				});
				if (!openids.isEmpty()){
					System.out.println(openids.size()+"入库大小=====");
					//TODO openids 入库
				}
			}
		});
	}
}
