package com.ibm.p1.entity;

import java.sql.Date;
import java.util.List;

public class TripleMap {
	private String str;
	private int num;
	private List<Date> dates;
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public List<Date> getDates() {
		return dates;
	}
	public void setDates(List<Date> dates) {
		this.dates = dates;
	}
	
	
}
