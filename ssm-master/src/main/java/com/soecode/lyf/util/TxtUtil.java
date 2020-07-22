package com.soecode.lyf.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TxtUtil {

    public static void splitIt(String toTest){
        String[] result = toTest.split(" ");
        ExecutorService pool = Executors.newCachedThreadPool();  
        final List<String> linkList=new LinkedList<String>();
        for (final String piece:result) {
            Runnable run = new Runnable() {  
                public void run() {  
                    try {  
                        //new Thread().sleep(1000);
                        //模拟耗时操作
                    	System.out.println("[1]" + Thread.currentThread().getName()+"----"+piece);
                    	linkList.add(piece);
                    } catch (Exception e) {  
                    }  
                }  
            }; 
            pool.execute(run);  
		
		}
        System.out.println("[1] done!");
        pool.shutdown();  
    }


    public static void main(String[] args) throws IOException {
    	FileReader fileReader=null;
    	BufferedReader reader=null;
		try {
			File test = new File("D:\\test.txt");
			fileReader= new FileReader(test);
			reader= new BufferedReader(fileReader);
			String line = null;
			while ((line = reader.readLine()) != null) {
				splitIt(line);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			reader.close();
		}
    }
}
