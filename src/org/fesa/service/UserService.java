package org.fesa.service;

import java.util.List;

import org.fesa.pojo.UserPojo;

public interface UserService {

	/*
	 * 获取所有用户
	 */
	List<UserPojo> getUsers();
}
