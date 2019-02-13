package com.ibm.p1.dao;

import java.util.ArrayList;
import java.util.Map.Entry;

import com.ibm.p1.entity.Fans;
import com.ibm.p1.entity.ReleaseHistory;

public interface ReleaseDao {
	public boolean saveReleaseSinaWeiboHistory(ReleaseHistory releaseHistory);
	
	public boolean saveReleaseSinaWeiboHistoryToDraft(ReleaseHistory releaseHistory);
	
	public ArrayList<Fans> getFansByEntry(ArrayList<Entry<String, Double>> tmp);
}
