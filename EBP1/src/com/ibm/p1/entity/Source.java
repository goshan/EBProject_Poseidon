package com.ibm.p1.entity;

import java.sql.Timestamp;
import java.util.Set;

public class Source implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int source_id;
	private String content;
	private Timestamp created_at;
	private User user;
	private Set<Source_keyWords> source_keyWords;
	
	
	public int getSource_id() {
		return source_id;
	}
	public void setSource_id(int source_id) {
		this.source_id = source_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Source_keyWords> getSource_keyWords() {
		return source_keyWords;
	}
	public void setSource_keyWords(Set<Source_keyWords> source_keyWords) {
		this.source_keyWords = source_keyWords;
	}
	

	
	
}