package com.itheima.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bos.dao.UserDao;
import com.itheima.bos.domain.User;
import com.itheima.bos.service.UserServicce;
import com.itheima.bos.utils.MD5Utils;

@Service
@Transactional
public class UserServiceImpl implements UserServicce {
	@Autowired
	private UserDao userDao;

	public User login(User user) {
		String userName = user.getUsername();
		String passWord = user.getPassword();
		passWord = MD5Utils.md5(passWord);
		return userDao.findByUserNameAndPassWord(userName, passWord);
	}

	public void editPassword(String passWord, String id) {
		// TODO Auto-generated method stub

	}

}
