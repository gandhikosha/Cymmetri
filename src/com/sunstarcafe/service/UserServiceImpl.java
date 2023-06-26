package com.sunstarcafe.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunstarcafe.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	
	private SessionFactory sessionFactory;

	// create session
	private Session session;
	
	@Autowired
	UserServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			this.session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			this.session = sessionFactory.openSession();
		}

	}

	@Override
	public Integer addUser(User user) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Integer userId=(Integer)session.save(user);
		tx.commit();
		return userId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		List<User> userList=session.createQuery("from User").list();
		return userList;
	}

	@Override
	public User getUser(Integer userId) {
		// TODO Auto-generated method stub
		User user=(User) session.createQuery("from User where userId='"+userId+"'").uniqueResult();
		return user;
	}
	
	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		User user=(User) session.createQuery("from User where email='"+email+"'").uniqueResult();
		return user;
	}

	@Override
	public void deleteUser(Integer userId) {
		session.delete(getUser(userId));		
	}
	
	@Override
	public void updateUser(User user) {
		
		User extUser=getUser(user.getUserId());
		extUser.setEmail(user.getEmail());
		extUser.setFirstName(user.getFirstName());
		extUser.setLastName(user.getLastName());
		extUser.setMobileNo(user.getMobileNo());
		extUser.setPassword(user.getPassword());
		Transaction tx = session.beginTransaction();
		session.update(user);
		tx.commit();
	}
	

}
