package com.soecode.lyf.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.soecode.lyf.BaseTest;
import com.soecode.lyf.BaseTestTX;
import com.soecode.lyf.service.BookService;


public class BookServiceTXTest extends BaseTestTX {

	@Autowired
	private BookService bookService;

	/*@Test
	public void testAppoint() throws Exception {
		bookService.getById();
	}*/
	
	@Test
	public void testAppoint() throws Exception {
		bookService.update();
	}

}
