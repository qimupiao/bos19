package com.itheima.bos.dao;

import com.itheima.bos.dao.base.BaseDao;
import com.itheima.bos.domain.User;

public interface UserDao extends BaseDao<User> {

	public User findByUserNameAndPassWord(String userName, String password);
}
