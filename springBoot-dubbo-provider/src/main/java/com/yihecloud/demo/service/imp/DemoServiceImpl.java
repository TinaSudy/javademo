package com.yihecloud.demo.service.imp;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.yihecloud.demo.service.DemoService;


@Service(version = "1.0.0")
public class DemoServiceImpl implements DemoService {
	
	@Override
	public List<String> getAll() {
		List<String> li=new ArrayList<String>();
		String name="yihecloud";
		li.add(name);
		return li;
	}

}
