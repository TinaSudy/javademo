package com.soecode.lyf.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alibaba.fastjson.JSONObject;
import com.soecode.lyf.entity.Book;
import com.soecode.lyf.entity.User;
import com.soecode.lyf.mybatis.master.UserDao;
import com.soecode.lyf.mybatis.slave.BookDao;
import com.soecode.lyf.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	BookDao bookDao;
	
	@Autowired 
	UserDao userDao;

	public Book getById() {
		
		//获取用户信息
		User u=userDao.getUser("123");
		logger.info("BookServiceImpl/getById/返回用户信息====="+JSONObject.toJSONString(u));
		
		Book boo=bookDao.getBook();
		logger.info("BookServiceImpl/getById/返回用户信息====="+JSONObject.toJSONString(boo));
		
		return null;
	}
	@Transactional(value="springTransactionManager",rollbackFor=Exception.class)
	public void update() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss:SSS");
		String formatStr =formatter.format(new Date());
		
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		User user=new User ();
		user.setId(uuid);
		user.setRealName(formatStr);
		
		Book book=new Book();
		book.setName(formatStr);
		book.setBookId(uuid);
		try {
			userDao.insert(user);
			 if (true){
				 throw new RuntimeException("註冊使用者:Member表資料插入不一致.");
		      }
			bookDao.insertBook(book);
		} catch (RuntimeException e) {
			System.out.println(e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();  
		}
	}
	
}
