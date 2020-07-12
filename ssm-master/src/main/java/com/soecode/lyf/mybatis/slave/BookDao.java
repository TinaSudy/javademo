package com.soecode.lyf.mybatis.slave;

import org.apache.ibatis.annotations.Select;

import com.soecode.lyf.entity.Book;


public interface BookDao {
	
	@Select({
		"select name,number from book where book_id='1000' "
	})
	public com.soecode.lyf.entity.Book getBook();
	
	public void insertBook(Book book);

}
