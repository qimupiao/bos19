package com.itheima.bos.service;

import com.itheima.bos.domain.User;

public interface UserServicce {

	public User login(User user);

	public void editPassword(String passWord, String id);
}
