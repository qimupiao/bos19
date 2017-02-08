package com.itheima.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.itheima.bos.dao.UserDao;
import com.itheima.bos.dao.base.impl.BaseDaoImpl;
import com.itheima.bos.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public User findByUserNameAndPassWord(String username, String password) {
		/*
		 * 根据密码和用户名查询
		 */
		String hql = "FROM  User u WHERE u.username= ? AND u.password= ?";
		List<User> list = this.getHibernateTemplate().find(hql, username, password);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
