package com.ibm.p1.entity;

public class Connection_keyWords {
		
	private int keyword_id;
	private String keyword;
	private float weight;
	private Connection connection;
	
	public int getKeyword_id() {
		return keyword_id;
	}
	public void setKeyword_id(int keyword_id) {
		this.keyword_id = keyword_id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	
}
