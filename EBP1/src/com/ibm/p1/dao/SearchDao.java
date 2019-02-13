package com.ibm.p1.dao;

import java.sql.Date;
import java.util.List;

import com.ibm.p1.entity.Connection;
import com.ibm.p1.entity.ReleaseHistory;
import com.ibm.p1.entity.Source;
import com.ibm.p1.entity.User;

public interface SearchDao {
	public List<Source> searchSourceByContent(String content);
	
	public List<Connection> searchConnectionByContent(String content);
	
	public List<User> searchUserByUsername(String username);
	
	public List<ReleaseHistory> searchReleaseHistoryByContent(String content);
	
	public List<ReleaseHistory> searchReleaseHistoryByDate(String startDate,String endDate);
	
	public List<ReleaseHistory> searchReleaseHistoryByContentAndDate(String content,String startDate,String endDate);
	
	public ReleaseHistory searchReleaseHistoryById(String id);
}
