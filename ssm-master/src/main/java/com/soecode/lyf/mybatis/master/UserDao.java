package com.soecode.lyf.mybatis.master;

import org.apache.ibatis.annotations.Select;

import com.soecode.lyf.entity.User;


public interface UserDao {
	
	@Select({
		"select real_name,mobile from user where id='1155085431545540610' "
	})
	public User getUser(String id);
	
	
	public void insert(User user);

}
