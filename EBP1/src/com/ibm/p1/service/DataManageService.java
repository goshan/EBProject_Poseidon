package com.ibm.p1.service;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ibm.p1.entity.Connection;
import com.ibm.p1.entity.ConnectionsTopic;
import com.ibm.p1.entity.HotWords;
import com.ibm.p1.entity.MyStatus;
import com.ibm.p1.entity.SourceOfUser;
import com.ibm.p1.entity.TotalKeyWords;
import com.ibm.p1.entity.TripleMap;
import com.ibm.p1.entity.User;

public interface DataManageService {
	public List<HotWords> getHotWords();
	
	public List<Connection> getConnections();
	
	/*
	 * chart functions
	 */
	//connection 相关
	public int getNumOfConnection();
	public Map<String,Integer> getTopCountNumOfConnectionKeyWords();
	
	//粉丝相关
	public int getNumOfZombieFans();
	public int getNumOfAllFans();
	public int getNumOfNormalFans();
	public Map<String,Integer> getTopCountNumOfFansKeyWords();
	
	//热词相关
	public List<TotalKeyWords> getTotalHotWords();
	
	//发布历史相关
	public Map<User,Integer> getReleaseNumAndUser();//某个用户发布的历史数据量
	public Map<Date,Integer> getReleaseNumAndDate();//某天发布的历史
	public int getTotalNumOfRelease();// 发布历史数据总数
	public int getTotalNumOfReleaseDraft();//草稿总数
	
	//素材池相关
	public int getNumOfNewSource();
	public int getNumOfAllSources();//素材池素材总数
	public Map<Date,Integer> getSourceOfDay();//某天素材池新增数量
	public SourceOfUser getSourceOfUser();//某用户添加的所有素材
	public Map<String,Integer> getTopCountNumOfSourceKeyWords();//素材池的关键词集合
	
	//状态相关
	public Map<Date,Integer> getStatusOfDay();//某天发布的微博
	public Map<String,Integer> getStatusOfWeiBoAccount();//某账号发布的微博
	
	//评论回复相关
	public Map<Date,Integer> getStatusCommentsOfDay();//某天所有回复
	public Map<MyStatus,Integer> getNumOfReplyOfTopic();//某条微博的所有回复
	public Map<String,Integer> getTopInteractionUser();//交互频繁的用户集合
	public Map<Date,Integer> getIgnoredNumOfDay();//某天回复忽略数
													//某天回复发布数
	public Map<Date,Integer> getNotIgnoredNumOfDay();//某天回复未处理数
	
	public Map<Integer,Double> getAverageReplyTimePer5Days(String month) throws ParseException ;
	
	//工具监控相关
	public Map<List<String>,Date> getDoneByDate();//某天工具运行情况
	public TripleMap getNumOfDone();//某工具的运行状态
	public Map<User,TripleMap> getNumOfDoneByUser();//某人使用的所有的工具的状态
	public Map<String, LinkedHashMap<String ,Integer>> getNumofToolsByDate(String month);
	
	//connection发帖相关
	public Map<Date,Integer> getNumOfTopicByDate();//某天创建connections topic数量
	
	//connection发帖的回复相关
	public Map<ConnectionsTopic,Integer> getNumOfReplyOfConnectionsTopic();//某个topic回复数量
	public Map<Date,Integer> getNumOfReplyOfDate();//某天所有回复数量
	public Map<String,Integer> getTopInteractionConnectionUser();//connections中交互最频繁的用户集合
	
	//user相关
	public int getNumOfAdmin();
	public int getNumOfUser();
	public int getNumOfCandidate();
	
}
