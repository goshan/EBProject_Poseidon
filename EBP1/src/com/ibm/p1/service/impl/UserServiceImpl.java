package com.ibm.p1.service.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ibm.p1.dao.UserDao;
import com.ibm.p1.entity.User;
import com.ibm.p1.service.UserService;

public class UserServiceImpl extends HibernateDaoSupport implements UserService {

	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}



	public boolean createUser(User user) {
		boolean result = userDao.createUser(user);
		return result;
	}
	
	public void createUserCandidate(User user) {
		// TODO Auto-generated method stub
		userDao.createUserCandidate(user);
	}


	public void updateUser(User user) {
		userDao.updateUser(user);
	}
	
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	public User getUserByUserId(int user_id) {
		// TODO Auto-generated method stub
		User user = userDao.findUserById(user_id);
		
		return user;
	}
	public User getUserCandidateByUserId(int user_id) {
		// TODO Auto-generated method stub
		User user = userDao.getUserCandidateByUserId(user_id);
		return user;
	}
	public boolean existsUserCandidateByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.existsUserCandidateByUserName(username);
	}
	
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		try
		{
			return userDao.findUserByUsername(username);
		}
		catch(RuntimeException ex)
		{
			throw ex;
		}
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		
		List<User> users = null;
		users = userDao.getAllUsers();
		
		return users;
	}


	public List<User> getAllUserCandidates() {
		// TODO Auto-generated method stub
		List<User> users = null;
		users = userDao.getAllUserCandidates();
		
		return users;
	}


	public User getUserByAccessToken(String access_token) {
		// TODO Auto-generated method stub
		return userDao.getUserByAccessToken(access_token);
	}












}