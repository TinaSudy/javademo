package com.yihecloud.demo.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yihecloud.demo.service.DemoServiceConsumer;




@RestController
public class dubboTest {
	
	@Autowired
	DemoServiceConsumer demoServiceConsumer;

	    @RequestMapping(value = "/hello", method = RequestMethod.GET)
	    public String index(HttpServletRequest request) {
	       
	    	System.out.println(this.demoServiceConsumer.getAll());
	    	
	    	return "Hello World";
	    }
	
}
