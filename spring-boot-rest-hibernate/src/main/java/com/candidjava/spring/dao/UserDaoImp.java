package com.candidjava.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.candidjava.spring.bean.User;
@Repository
public class UserDaoImp implements UserDao{
	@Autowired
	private SessionFactory sessionFactory;
	  
	public void addUser(User user) {
		 Session session = sessionFactory.getCurrentSession();
		  session.save(user);
	}

	public List<User> getUser() {
		 Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<User> list= session.createCriteria(User.class).list();
		return list;
	}

	public User findById(int id) {
		 Session session = sessionFactory.getCurrentSession();
		User user=(User) session.get(User.class,id);
		return user;
	}

	public User update(User val, int id) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.getTransaction();
		User user =(User)session.get(User.class, id);
		user.setCountry(val.getCountry());
		user.setName(val.getName());
		session.update(user);
		tx.commit();
		tx.rollback();
		return user;
	}

	public void delete(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = findById(id);
		session.delete(user);
	}
	@Override
	public User updateCountry(User val, int id){
		Session session = sessionFactory.getCurrentSession();
		User user = (User)session.load(User.class, id);
		user.setCountry(val.getCountry());
		return user;
	}

}
