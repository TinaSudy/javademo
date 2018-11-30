package com.yihecloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDubboConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDubboConsumerApplication.class, args);
		System.out.println("启动成功---");
	}
}
