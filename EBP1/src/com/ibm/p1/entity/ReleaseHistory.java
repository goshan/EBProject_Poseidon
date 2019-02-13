package com.ibm.p1.entity;

import java.sql.Date;
import java.sql.Time;

public class ReleaseHistory implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int release_id;
	private User user;
	private String title;
	private String content;
	private String contentSub;
	private Date date;
	private Time time;
	private int isDraft;
	public ReleaseHistory(){
		//无参构造器
	}
	public ReleaseHistory(int release_id,User user,String title,String content,Date date,Time time,int isDraft){
		//全参构造器
		this.release_id = release_id;
		this.user =user;
		this.title = title;
		this.content = content;
		this.date = date;
		this.time = time;
		this.isDraft = isDraft;
		
	}
	public int getRelease_id() {
		return release_id;
	}
	public void setRelease_id(int release_id) {
		this.release_id = release_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public int getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(int isDraft) {
		this.isDraft = isDraft;
	}
	
	public String getContentSub(){
		return this.contentSub;
	}

	public void setContentSub(String contentSub){
		this.contentSub = contentSub;
	}
	
}
