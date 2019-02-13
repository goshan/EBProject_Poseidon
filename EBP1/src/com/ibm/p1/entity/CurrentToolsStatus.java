package com.ibm.p1.entity;

import java.sql.Timestamp;

public class CurrentToolsStatus implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tools_id;
	private String tools_name;
	private String tools_status;
	private User user;
	private Timestamp updateTime;
	
	public int getTools_id() {
		return tools_id;
	}
	public void setTools_id(int tools_id) {
		this.tools_id = tools_id;
	}
	public String getTools_name() {
		return tools_name;
	}
	public void setTools_name(String tools_name) {
		this.tools_name = tools_name;
	}
	public String getTools_status() {
		return tools_status;
	}
	public void setTools_status(String tools_status) {
		this.tools_status = tools_status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
