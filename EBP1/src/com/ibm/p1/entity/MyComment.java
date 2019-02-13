package com.ibm.p1.entity;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibm.p1.tools.DateUtils;

public class MyComment {
	
	
	private String commentId;
	private String statusId;
	private String commentUser;
	private String commentText;
	private Timestamp timestamp;
	private String datetime;
	private String ignored;
	private String mid;
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getCommentUser() {
		return commentUser;
	}
	public void setCommentUser(String commentUser) {
		this.commentUser = commentUser;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		
		this.commentText =commentText;//= commentText.length()>20 ?commentText.substring(0,20)+"......" :commentText;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
		
		String formatStr ="yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		try {
			Date d = formatter.parse(timestamp.toString());
			this.setDatetime(DateUtils.dateToDateTime(d));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		
		this.datetime = datetime;
	}
	public String getIgnored() {
		return ignored;
	}
	public void setIgnored(String ignored) {
		this.ignored = ignored;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	
}
