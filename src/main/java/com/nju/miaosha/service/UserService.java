package com.nju.miaosha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nju.miaosha.dao.UserDao;
import com.nju.miaosha.domain.User;

@Service
public class UserService {
	@Autowired
	UserDao userDao;
	public User getById(int id) {
		return userDao.getById(id);
	}
	
	//使用事务
	@Transactional
	public boolean tx() {
		User user=new User();
		user.setId(3);
		user.setName("nju");
		userDao.insert(user);
		
		User user1=new User();
		user1.setId(1);
		user1.setName("ljs2");
		userDao.insert(user1);			//这里出问题则回滚
		
		return true;
	}
}
