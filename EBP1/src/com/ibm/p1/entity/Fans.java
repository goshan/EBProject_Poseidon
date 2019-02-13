package com.ibm.p1.entity;

import java.util.Set;

public class Fans {
	private int fans_id;
	private String username;
	private int zombieType;
	private Set<Fans_keyWords> fans_keyWords;
	public int getFans_id() {
		return fans_id;
	}
	public void setFans_id(int fans_id) {
		this.fans_id = fans_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getZombieType() {
		return zombieType;
	}
	public void setZombieType(int zombieType) {
		this.zombieType = zombieType;
	}
	public Set<Fans_keyWords> getFans_keyWords() {
		return fans_keyWords;
	}
	public void setFans_keyWords(Set<Fans_keyWords> fans_keyWords) {
		this.fans_keyWords = fans_keyWords;
	}
	
	
}
