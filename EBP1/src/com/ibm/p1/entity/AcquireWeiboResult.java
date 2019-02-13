package com.ibm.p1.entity;
/*
 * 
 * 每次获取微博数据的返回结果
 */
public class AcquireWeiboResult {
	
	private long users=0;
	private long statuses=0;
	private String result;
	public long getUsers() {
		return users;
	}
	public void setUsers(long users) {
		this.users = users;
	}
	public long getStatuses() {
		return statuses;
	}
	public void setStatuses(long statuses) {
		this.statuses = statuses;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
