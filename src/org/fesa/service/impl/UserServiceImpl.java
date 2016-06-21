package org.fesa.service.impl;

import java.util.List;

import org.fesa.dao.UserDao;
import org.fesa.pojo.UserPojo;
import org.fesa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public List<UserPojo> getUsers() {
		List<UserPojo> list_result=userDao.getAllUser();
		return list_result;
	}
}
