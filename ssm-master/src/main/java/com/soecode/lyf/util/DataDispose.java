package com.soecode.lyf.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class DataDispose {
	
	@Test
	public void read() throws FileNotFoundException{
			File file = new File("D:\\2.csv");
	        InputStream inputStream = new FileInputStream(file);
	        try {
	        	long startTime = System.currentTimeMillis(); 
					List<String> list =CsvUtil.read(inputStream,"GB2312");
					if (!list.isEmpty()){
							//foraa(list);
					    }  
					list.stream().forEachOrdered(x->{
						
					});
					System.out.println("解析条数"+list.size()+"=====");
				long endTime = System.currentTimeMillis(); 
				 	System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
				
			} catch (Exception e) {
				System.out.println(e);
			}
	}
	
	public static void foraa( List<String> list ) throws Exception {
		//开始时间
        long start = System.currentTimeMillis();
        
        // 每500条数据开启一条线程
        int threadSize = 1000;
        // 总数据条数
        int dataSize = list.size();
        // 线程数
        int threadNum = dataSize / threadSize + 1;
        // 定义标记,过滤threadNum为整数
        boolean special = dataSize % threadSize == 0;
        // 创建一个线程池
        ExecutorService exec = Executors.newFixedThreadPool(1);
        exec.execute(new Runnable() {
        	 List<String> cutList = null;
			@Override
			public void run() {
				 // 确定每条线程的数据
		        for (int i = 0; i < threadNum; i++) {
		            if (i == threadNum - 1) {
		                if (special) {
		                    break;
		                }
		                cutList = list.subList(threadSize * i, dataSize);
		            } else {
		                cutList = list.subList(threadSize * i, threadSize * (i + 1));
		            }
		           //TODO 数据入库
		        }
				
			}
		});
        // 关闭线程池
        exec.shutdown();
    }

}
