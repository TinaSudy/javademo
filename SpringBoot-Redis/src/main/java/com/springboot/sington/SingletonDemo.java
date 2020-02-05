package com.springboot.sington;

import java.util.UUID;

import org.junit.Test;

/**
 * 
/*
    实现单例的四种方式：
    饿汉式(线程安全，调用效率高，但是不能延时加载)
    懒汉式（线程安全，调用效率不高，可以延时加载）
    静态内部类式（线程安全，调用效率高，但是可以延时加载）
    枚举单例（线程安全，调用效率高，不能延时加载）
 *
 */


/**
 * 单例设计模式：保证一个类在使用过程中，只有一个实例。优势就是他的作用，这个类永远只有一个实例。
 *  步骤：  1.将该类的构造方法私有；
		 2. 在内部实例化一个该类的实例；
		 3. 提供接口给外部访问。
 *    //饿汉式：//线程安全,“饿汉式”是在不管你用的用不上，一开始就建立这个单例对象：
 */
	
	/** 这种写法不会出现并发问题，但是它是饿汉式的，在ClassLoader加载类后SingletonDemo 的实例就会第一时间被创建，
	饿汉式的创建方式在一些场景中将无法使用：譬如SingletonDemo 实例的创建是依赖参数或者配置 文件的，
	  在getInstance()之前必须调用某个方法设置参数给它，那样这种单例写法就无法使用了。
	*/ 
public class SingletonDemo {
	
      @Test
      public void aa(){
    	  
    	  String[] chars = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G",   
                  "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };   
    	        StringBuffer shortBuffer = new StringBuffer();    
    	        String uuid = UUID.randomUUID().toString().replace("-","");   
    	        for (int j=0;j<8;j++) {    
    	            String str=uuid.substring(j*4,j*4+4);  
    	            int par=Integer.parseInt(str,16);   
    	            shortBuffer.append(chars[par % 36]);    
    	        }  
    	        System.out.println(shortBuffer.toString());  
    	   
    	  
      }
      
      
}	

