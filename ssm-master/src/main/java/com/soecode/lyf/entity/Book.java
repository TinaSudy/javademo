package com.soecode.lyf.entity;

/**
 * 图书实体
 */
public class Book {

	private String bookId;// 图书ID

	private String name;// 图书名称

	private int number;// 馆藏数量

	public Book() {
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	

}
