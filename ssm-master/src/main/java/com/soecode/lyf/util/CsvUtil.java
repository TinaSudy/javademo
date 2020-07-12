package com.soecode.lyf.util;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CsvUtil {
	
	/*@Test
	public void jsjsj(){
		BigDataDispose bdd = new BigDataDispose();
        Thread thread = new Thread(bdd); 
        thread.start();
	}*/
	
	/*@Test
	public void aa() throws FileNotFoundException{
			File file = new File("D:\\2.csv");
	        InputStream inputStream = new FileInputStream(file);
	        try {
	        	long startTime = System.currentTimeMillis(); 
					List<String[]> list = read(inputStream,"GB2312");
					System.out.println("解析条数"+list.size()+"=====");
				long endTime = System.currentTimeMillis(); 
				 	System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
				
			} catch (Exception e) {
				System.out.println(e);
			}
	}*/
	/*@Test
	public void bb() throws Exception{
		long startTime = System.currentTimeMillis(); 
		List<String[]> list=new ArrayList<String[]>();
		for (int i=0;i<=1000000;i++){
			String[] a=new String[]{"djdjdjdjdkandjdndjdkkkkddhdh"+i};
			list.add(a);
		}
		long endTime = System.currentTimeMillis(); 
		write(list,"D:\\2.csv");
		System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
	}*/
	
	   /**
     * 读取csv文件内容
     * @param inputStream
     * @param code csv文件的编码,如utf8,,gbk
     *
     * @return 返回csv文件中的数据
     * @throws Exception
     */
    public static List<String[]> read(InputStream inputStream, String code) throws Exception{
    	
        //1. 存储csv文件中的内容
        List<String[]> csvList = new ArrayList<String[]>();

        //2. 创建CsvReader
        CsvReader reader = new CsvReader(inputStream, ',', Charset.forName(code));

        //3. 跳过表头,如果需要表头的话，不要写这句
        reader.readHeaders();

        //4.逐行读入除表头的数据
        while(reader.readRecord()){
            csvList.add(reader.getValues());
        }

        //5. 释放资源
        reader.close();
        return csvList;
    }
    
    /**
     * 数据写入csv文件
     * @param list UTF-8编码写入csv文件的内容
     * @param filePath 写入的csv文件的指定路劲
     *
     * @throws Exception
     */
    public static void write(List<String[]> list, String filePath) throws Exception{
        CsvWriter wr = new CsvWriter(filePath, ',', Charset.forName("UTF-8"));
        for (int i = 0; i < list.size(); i++) {
            wr.writeRecord(list.get(i));
        }
        wr.close();
    }
}
