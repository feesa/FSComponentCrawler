package org.fesa.dao.impl;

import java.util.List;

import org.fesa.dao.UserDao;
import org.fesa.pojo.UserPojo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserPojo> getAllUser() {
		String hql="from UserPojo";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		return query.list();
	}
}
