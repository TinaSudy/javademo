package com.yihecloud.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDubboProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDubboProviderApplication.class, args);
		System.out.println("启动成功---");
	}
}
