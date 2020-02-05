package com.springboot.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.springboot.entity.Dept;
import com.springboot.entity.Person;

/**
 * jdk8 foreach 特性
 * @author Administrator
 *
 */
public class Jdk8forEach {

	public static void main(String[] args) {
		//forEach01();
		//filter();
		mapforEach();
		
	}
	public static void mapforEach(){
		Map<String,Integer> items = new HashMap();
		items.put("A", 10);
	    items.put("B", 20);
	    items.put("C", 30);
	    items.put("D", 40);
	    items.put("E", 50);
	    items.put("F", 60);
	    //遍历maps
	   items.forEach((k,v)->{
	    	System.out.println(k+"===="+v);
	    });
	   //遍历map
	    items.entrySet().forEach(entry->{
	    	System.out.println(entry.getKey()+"===="+entry.getValue());
	    });
	    
	    Map<Integer, String> HOSTING = new HashMap<>();
        HOSTING.put(1, "linode.com");
        HOSTING.put(2, "heroku.com");
        HOSTING.put(3, "digitalocean.com");
        HOSTING.put(4, "aws.amazon.com");

        //过滤map中值 并返回map
        Map<Integer, String> collect = HOSTING
        		.entrySet().stream()
                .filter(map -> map.getKey() == 2)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
        
        System.out.println(collect); //output : {2=heroku.com}
        
        Map<Integer, String> map = new HashMap<>();
        map.put(10, "apple");
        map.put(20, "orange");
        map.put(30, "banana");
        map.put(40, "watermelon");
        map.put(50, "dragonfruit");
       
        //map转list 
        List<Integer> result = map
        		.entrySet()
        		.stream()
                .map(x -> x.getKey())
                .collect(Collectors.toList());
       
	}
	
	public static void forEach01(){
		List<String> list=new ArrayList<String>();
	    list.add("C");
        list.add("H");
        list.add("A");
        list.add("A");
        list.add("B");
        list.add("F");
        list.add("");
        
        //多线程执行方式
        list.parallelStream().forEach(a->{
        	System.out.println(a);
        });  		
        //单线程执行
        list.forEach(b ->{
        	System.out.println(b);
        });
        
        //字符过滤方式
        List<String> result = list.stream()            // 转化为一个流
                .filter(line -> !"A".equals(line))     // 排除 'A'
                .collect(Collectors.toList());
        
        List<Person> persons = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );
        //获取对象
        Person result1 = persons.stream()                        // 转化为流
                .filter(x -> "jack".equals(x.getName()))        // 只过滤出"jack"
                .findAny()                                      // 如果找到了就返回
                .orElse(null);                                  // 如果找不到就返回null
       
	 //lst转map 
       Map<Integer,Person> b= 
    		   	persons.stream().
    		   	collect(Collectors.toMap(Person::getAge, Function.identity()));
        

	}
	
	public static void filter(){
		List<Dept> stringList=new ArrayList<Dept>();
		Dept dep=new Dept();
		dep.setDname("test01");
		Dept dep2=new Dept();
		dep2.setDname("");
		
		stringList.add(dep2);
		stringList.add(dep);
		
		stringList.stream().filter(list->!stringList.isEmpty()).collect(Collectors.toList());
		System.out.println(stringList.size());
		
		stringList.parallelStream().forEach(a->{
	        	System.out.println(a.getDname());
	        });  	
	}

}
