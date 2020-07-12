package com.soecode.lyf.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.soecode.lyf.BaseTest;
import com.soecode.lyf.dataSource.DynamicDataSource;
import com.soecode.lyf.entity.Book;
import com.soecode.lyf.mybatis.master.UserDao;
import com.soecode.lyf.mybatis.slave.BookDao;

public class BookDaoTest extends BaseTest {

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private UserDao user;
	


	@Test
	public void testQueryAll() throws Exception {
		DynamicDataSource.clearCustomerType();//重点： 实际操作证明，切换的时候最好清空一下
       // DynamicDataSource.setCustomerType(DynamicDataSource.dataSourceB);
		List<Book> books;
		/*try {
			books = bookDao.queryAll(0, 4);
			for (Book book : books) {
				System.out.println(book);
				}
		} catch (Exception e) {
			System.out.println(e);
		}*/
		
		System.out.println(user.getUser("123"));
	}
}
