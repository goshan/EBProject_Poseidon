package com.ibm.p1.entity;

import java.util.Set;

public class Connection {
	private static int title_len = 20;
	private int topic_id;
	private String uuid;
	private String title;
	private String content;
	private Set<Connection_keyWords> connection_keyWords;
	private float similarity;
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public float getSimilarity() {
		return similarity;
	}
	public void setSimilarity(float similarity) {
		this.similarity = similarity;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleShow() {
		return title.length() <= title_len ? title : title.substring(0, title_len);
	}
	public void setTitleShow(String title) {
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Set<Connection_keyWords> getConnection_keyWords() {
		return connection_keyWords;
	}
	public void setConnection_keyWords(Set<Connection_keyWords> connection_keyWords) {
		this.connection_keyWords = connection_keyWords;
	}
	
	
}
