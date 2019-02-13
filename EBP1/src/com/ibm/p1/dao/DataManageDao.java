package com.ibm.p1.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ibm.p1.entity.Connection;
import com.ibm.p1.entity.HotWords;
import com.ibm.p1.entity.SourceOfUser;
import com.ibm.p1.entity.TotalKeyWords;
import com.ibm.p1.entity.User;

public interface DataManageDao {
	public List<Connection> getConnections();
	
	public List<HotWords> getHotWords();
	
	
	/*
	 * chart functions
	 */
	//connection 相关
	public int getNumOfConnection();
	public List<Object> getTopCountNumOfConnectionKeyWords();
	//粉丝相关
	public int getNumOfZombieFans();
	public int getNumOfAllFans();
	public int getNumOfNormalFans();
	public List<Object> getTopCountNumOfFansKeyWords();
	//热词相关
	public List<Object> getTotalHotWords();
	public List<Date> getDatesByHotWords(String word);
	
	
	//发布历史相关
	/*
	public List<Object> getReleaseNumAndUser();//用户发布的历史数据量
	public Map<Date,Integer> getReleaseNumAndDate();//某天发布的历史
	public int getTotalNumOfRelease();// 发布历史数据总数
	public int getTotalNumOfReleaseDraft();//草稿总数
	*/
	//素材池相关
	public int getNumOfAllSources();
	public void updateNumOfAllSources(int num);
	
	//user相关
	public int getNumOfAdmin();
	public int getNumOfUser();
	public int getNumOfCandidate();
	
	//工具监控相关
	public Map<Integer, LinkedHashMap<String ,Integer>> getNumofToolsByDate(String month);
	
	
	public Map<Integer,Long> getAverageTimePer5Days(String month) throws ParseException ;
}
