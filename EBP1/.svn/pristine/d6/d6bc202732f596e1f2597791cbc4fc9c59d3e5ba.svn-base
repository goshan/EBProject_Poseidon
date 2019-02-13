package com.ibm.p1.entity;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibm.p1.tools.DateUtils;

public class ConnectionsTopic {
	private String topicId;
	private String title;
	private Timestamp timeStamp;
	private String dateTime;
	private String content;
	private String statusId;
	private String commentId;
	
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {

		this.timeStamp=timeStamp;
		String formatStr ="yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		try {
			Date d = formatter.parse(timeStamp.toString());
			this.setDateTime(DateUtils.dateToDateTime(d));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	
	
}
