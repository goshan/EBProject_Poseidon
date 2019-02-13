package com.ibm.p1.tools;

import java.io.IOException;
import java.util.Map;

import com.ibm.p1.entity.User;
import com.opensymphony.xwork2.ActionContext;

public class Utils {
	//create the session for the user
	static public void createUserSession(User user){
	    Map<String,Object> session = ActionContext.getContext().getSession();
		session.put("current_user",user);
	}
	
	static public void deleteUserSession(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		session.remove("current_user");
	}
	
	static public User currentUser(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		return (User)session.get("current_user");
	}
	
	static public boolean isLogin(){
		User currentUser = Utils.currentUser();
		return currentUser != null;
	}
	
	static public String currentUserType(){
		User currentUser = Utils.currentUser();
		return currentUser.getType();
	}
}
