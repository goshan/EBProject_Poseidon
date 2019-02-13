package com.ibm.p1.service;

import java.util.List;
import com.ibm.p1.entity.User;

public interface UserService {
	public void createUserCandidate(User user);
	
	public boolean createUser(User user);
	
	public void updateUser(User user);
		
	public void deleteUser(User user);
	
	public User getUserByUserId(int user_id);
	
	public User getUserCandidateByUserId(int user_id);
	
	public User getUserByAccessToken(String access_token);
	
	public boolean existsUserCandidateByUserName(String username);
	
	public User getUserByUsername(String username);
	
	public List<User> getAllUsers();
	
	public List<User> getAllUserCandidates();
	
}
