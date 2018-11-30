package com.yihecloud.demo.service.imp;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yihecloud.demo.service.DemoService;
import com.yihecloud.demo.service.DemoServiceConsumer;

@Component
public class DemoServiceImpl implements DemoServiceConsumer {
	
	
	@Reference(version = "1.0.0") 
	private DemoService demoService;

	@Override
	public List<String> getAll() {
		
		return this.demoService.getAll();
	}

}
