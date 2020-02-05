package com.springboot.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 启动20个线程  1+10000 
 * @author Administrator
 *
 */
public class AtomicIntegerTest {
	 
    private static final int THREADS_CONUT = 20;
    
    public static int count = 0;
    
    private static AtomicInteger atomiccount = new AtomicInteger(0);

 
    public static void increase() {
        count++;
    }
    public static void atomicincar(){
    	atomiccount.incrementAndGet();
    }
    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_CONUT];
        for (int i = 0; i < THREADS_CONUT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increase();
                        atomicincar();
                    }
                }
            });
            threads[i].start();
        }
 
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        
        System.out.println(count);//输出结果 19651
        System.out.println(atomiccount);
    }
}