package com.ibm.p1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.ibm.p1.entity.Fans;
import com.ibm.p1.entity.ReleaseHistory;
import com.ibm.p1.entity.Source;

public interface ReleaseService {
	
	public boolean saveReleaseSinaWeiboHistory(ReleaseHistory releaseHistory);
	
	public boolean saveReleaseSinaWeiboHistoryToDraft(ReleaseHistory releaseHistory);
		
	public String publishWeibo(String title, String content);
	
	public String replyWeibo(String status_id, String comment_id, String comments);
	
	public boolean getFansList();
	
	public void writeContentAndSource(String content,List<Source> allSource);
	
	public ArrayList<Fans> getRecommandFans();
	
}
