package com.ibm.p1.entity;

import java.sql.Time;
import java.sql.Date;

public class HotWords implements Comparable<HotWords>{
	private int hotWords_id;
	private Date date;
	private Time time;
	private String word;
	private float weight;
	
	public int getHotWords_id() {
		return hotWords_id;
	}
	public void setHotWords_id(int howWords_id) {
		this.hotWords_id = howWords_id;
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
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	
	public int compareTo(HotWords o) {
		// TODO Auto-generated method stub
		if (this.weight < o.weight){
			return 1;
		}
		else if (this.weight == o.weight){
			return 0;
		}
		else {
			return -1;
		}
	}
	
	
}
