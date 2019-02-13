package com.ibm.p1.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import com.ibm.p1.dao.AcquireDao;
import com.ibm.p1.dao.DataManageDao;
import com.ibm.p1.entity.Connection;
import com.ibm.p1.entity.ConnectionsTopic;
import com.ibm.p1.entity.HotWords;
import com.ibm.p1.entity.MyStatus;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.SourceOfUser;
import com.ibm.p1.entity.TotalKeyWords;
import com.ibm.p1.entity.TripleMap;
import com.ibm.p1.entity.User;

import com.ibm.p1.service.DataManageService;

public class DataManageServiceImpl implements DataManageService{

	
	private DataManageDao dataManageDao;
	private AcquireDao acquireDao;
	
	private List<TotalKeyWords> hotWordsList = new ArrayList<TotalKeyWords>();
	public DataManageDao getDataManageDao() {
		return dataManageDao;
	}
	public void setDataManageDao(DataManageDao dataManageDao) {
		this.dataManageDao = dataManageDao;
	}
	public AcquireDao getAcquireDao() {
		return acquireDao;
	}
	public void setAcquireDao(AcquireDao acquireDao) {
		this.acquireDao = acquireDao;
	}
	public List<TotalKeyWords> getHotWordsList() {
		return hotWordsList;
	}
	public void setHotWordsList(List<TotalKeyWords> hotWordsList) {
		this.hotWordsList = hotWordsList;
	}
	//原始数据管理部分
	public List<Connection> getConnections() {
		return this.dataManageDao.getConnections();
	}
	public List<HotWords> getHotWords() {
		return this.dataManageDao.getHotWords();
	}
	
	///*图表chart部分开始
	//connection 相关
	public int getNumOfConnection() {
		// TODO Auto-generated method stub
		int num = dataManageDao.getNumOfConnection();
		return num;
	}

	public Map<String, Integer> getTopCountNumOfConnectionKeyWords() {
		// TODO Auto-generated method stub
		
		List numList = dataManageDao.getTopCountNumOfConnectionKeyWords();
		Map<String, Integer> myMap=this.list2Map(numList);
		//System.out.println("myMap:"+myMap.size()+myMap.toString());
		return myMap;
	}
	//粉丝相关
	public int getNumOfZombieFans() {
		// TODO Auto-generated method stub
		return dataManageDao.getNumOfZombieFans();
	}

	public int getNumOfAllFans() {
		// TODO Auto-generated method stub
		return dataManageDao.getNumOfAllFans();
	}
	
	public int getNumOfNormalFans() {
		// TODO Auto-generated method stub
		return dataManageDao.getNumOfNormalFans();
	}

	public Map<String, Integer> getTopCountNumOfFansKeyWords() {
		// TODO Auto-generated method stub
		Map<String, Integer> myMap =  new HashMap<String, Integer>();
		List<Object> numList = new ArrayList<Object>();
		numList = dataManageDao.getTopCountNumOfFansKeyWords();
		myMap = sortByInteger(numList);
		//System.out.println("myMap:"+myMap.size()+myMap.toString());
		return myMap;
	}
	//热词相关
	public List<TotalKeyWords> getTotalHotWords() {
		// TODO Auto-generated method stub
		List<Object> objList = dataManageDao.getTotalHotWords();
		List<Object> sortedObjList = sortByWeight(objList);
		//System.out.println("myList:"+sortedObjList.size()+sortedObjList.toString());
		for(int i = 0 ;i<sortedObjList.size();i++){
			Object[] object = (Object[]) sortedObjList.get(i);
			List<Date> dateList = dataManageDao.getDatesByHotWords(object[0].toString());
			TotalKeyWords totalKeyWords = new TotalKeyWords();
			totalKeyWords.setKeyWord(object[0].toString());
			totalKeyWords.setTotalAppear(Integer.parseInt(object[1].toString()));
			totalKeyWords.setTotalWeight(Float.parseFloat(object[2].toString()));
			totalKeyWords.setAppearDates(dateList);
			hotWordsList.add(totalKeyWords);
		}
		//System.out.println("hotWordsList"+hotWordsList.toString());
		return hotWordsList;
	}

	public Map<User, Integer> getReleaseNumAndUser() {
		// TODO Auto-generated method stub		
		return null;
	}

	public Map<Date, Integer> getReleaseNumAndDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getTotalNumOfRelease() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTotalNumOfReleaseDraft() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumOfAllSources() {
		// TODO Auto-generated method stub
		int num = dataManageDao.getNumOfAllSources();

		return num;
	}
	
	public int getNumOfNewSource() {
		// TODO Auto-generated method stub
		int newSource = 0;
		int num = dataManageDao.getNumOfAllSources();
		Parameter param = new Parameter();
		param = acquireDao.getAcquireInfoById(1);
		int oldNum = param.getTotalSourceNum();
		if(num > oldNum){
			newSource = num - oldNum;
			dataManageDao.updateNumOfAllSources(num);
		}
		return newSource;
	}
	
	public Map<Date, Integer> getSourceOfDay() {
		// TODO Auto-generated method stub
		return null;
	}

	public SourceOfUser getSourceOfUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Integer> getTopCountNumOfSourceKeyWords() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Date, Integer> getStatusOfDay() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Integer> getStatusOfWeiBoAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Date, Integer> getStatusCommentsOfDay() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<MyStatus, Integer> getNumOfReplyOfTopic() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Integer> getTopInteractionUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Date, Integer> getIgnoredNumOfDay() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Date, Integer> getNotIgnoredNumOfDay() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<List<String>, Date> getDoneByDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public TripleMap getNumOfDone() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<User, TripleMap> getNumOfDoneByUser() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Map<String, LinkedHashMap<String ,Integer>> getNumofToolsByDate(String month) {
		// TODO Auto-generated method stub
		Map<Integer, LinkedHashMap<String ,Integer>> myMap = new LinkedHashMap<Integer, LinkedHashMap<String ,Integer>>(); 
		Map<String, LinkedHashMap<String ,Integer>> myMapFor3Class = new LinkedHashMap<String, LinkedHashMap<String ,Integer>>(); 
		myMap = dataManageDao.getNumofToolsByDate(month);
		System.out.println("service层得到的myMap："+myMap);
		for(int i = 0;i<myMap.size();i++){
			LinkedHashMap<String ,Integer> calClass = new LinkedHashMap<String ,Integer>();
			calClass = myMap.get(i);
			System.out.println("service层得到的calClass："+calClass);
			ArrayList<Integer> myList = calClassification(calClass);
			System.out.println("返回的单个信息："+myList.toString());
			LinkedHashMap<String ,Integer> afterCal= new LinkedHashMap<String ,Integer>();

			afterCal.put("weiboClass", myList.get(0));
			afterCal.put("sourceClass", myList.get(1));
			afterCal.put("negativeClass", myList.get(2));
			myMapFor3Class.put("第"+i+"组", afterCal);
			
		}	
		return myMapFor3Class;
	}
	public Map<Date, Integer> getNumOfTopicByDate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Map<ConnectionsTopic, Integer> getNumOfReplyOfConnectionsTopic() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Map<Date, Integer> getNumOfReplyOfDate() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Integer> getTopInteractionConnectionUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumOfAdmin() {
		// TODO Auto-generated method stub
		return dataManageDao.getNumOfAdmin();
	}

	public int getNumOfUser() {
		// TODO Auto-generated method stub
		return dataManageDao.getNumOfUser();
	}

	public int getNumOfCandidate() {
		// TODO Auto-generated method stub
		return dataManageDao.getNumOfCandidate();
	}
	public Map<String,Integer> list2Map(List numList){
		Map<String,Integer> map=new LinkedHashMap<String,Integer>();
		
		try{
			for(int i=0;i<numList.size();i++){
				Object[] obj=(Object[])numList.get(i);
				map.put(obj[0].toString(), Integer.parseInt(obj[1].toString()));
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		return map;
	}
	
	
	public Map<String, Integer> sortByInteger(List<Object> numList){
		Map<String, Integer> sortedMap =  new LinkedHashMap<String, Integer>();
		int temp = 0;
		int biggerTemp= 0;
		for(int i= 0;i<numList.size();i = 0){
			int index = 0 ;
			Object[] obj1 = (Object[]) numList.get(i);
			temp = Integer.parseInt(obj1[1].toString());	
			for(int j= 1;j<numList.size();j++){
				Object[] obj2 = (Object[]) numList.get(j);
				biggerTemp = Integer.parseInt(obj2[1].toString());	
					if(temp > biggerTemp){
						index = i;
					}else{
						temp = biggerTemp;
						index = j;
						i = j;
					}
			}
			Object[] finalObj =(Object[]) numList.get(index);
			sortedMap.put(finalObj[0].toString(), Integer.parseInt(finalObj[1].toString()));
			numList.remove(index);
			
		}
		
		return sortedMap;
	}
	public List<Object> sortByWeight(List<Object> numList){
		List<Object> sortedObjList =  new ArrayList<Object>();
		float temp = 0;
		float biggerTemp= 0;
		for(int i= 0;i<numList.size();i = 0){
			int index = 0 ;
			Object[] obj1 = (Object[]) numList.get(i);
			temp = Float.parseFloat(obj1[2].toString());	
			for(int j= 1;j<numList.size();j++){
				Object[] obj2 = (Object[]) numList.get(j);
				biggerTemp = Float.parseFloat(obj2[2].toString());	
					if(temp > biggerTemp){
						index = i;
					}else{
						temp = biggerTemp;
						index = j;
						i = j;
					}
			}
			Object[] finalObj =(Object[]) numList.get(index);
			sortedObjList.add(finalObj);
			//sortedMap.put(finalObj[0].toString(), Integer.parseInt(finalObj[1].toString()));
			numList.remove(index);
			
		}
		
		return sortedObjList;
	}
	
	public Map<Integer, Double> getAverageReplyTimePer5Days(String month) throws ParseException {
		Map<Integer, Long> myMap = dataManageDao.getAverageTimePer5Days(month);
		Map<Integer, Double> myNewMap = new LinkedHashMap<Integer,Double>();
		DecimalFormat df=new DecimalFormat(".##");
		//将秒转变为时间
		for(int i = 0;i<myMap.size();i++){
			Long time = myMap.get(i);
			Long diffSecend = time % 60;
			Long diffMin = (time/60)% 60;
			double little = (5.0*diffSecend)/300;
			double result = (diffMin +little);
			String str=df.format(result);
			myNewMap.put(i, Double.parseDouble(str));
		}
		for(int j = 0;j<myNewMap.size();j++){
			System.out.println("新的平均时间是："+myNewMap.get(j));
		}
		return myNewMap;
	}
	public ArrayList<Integer> calClassification(LinkedHashMap<String ,Integer> calClass){
		ArrayList<Integer> myList = new ArrayList<Integer>();
		int weiboClass = 0;
		int sourceClass = 0;
		int negativeClass = 0;
		/*
		System.out.println("calClass的size是："+calClass.size());
		System.out.println("calClass获取名字的结果："
				            +calClass.get("FansPOI")
				            +calClass.get("TotalPOI")
				            +calClass.get("ZombieFansFilter")
				            +calClass.get("AcquireWeiboUser")
				            +calClass.get("AcquireWeiboReply")
				            +calClass.get("AcquireFansAndTopics")
				            +"以下是source"
				            +calClass.get("ConnectionKeyWords")
				            +calClass.get("KeyWordsMatch")
				            +calClass.get("RecommandQA")
				            +calClass.get("SourceKeyWords")
				            +calClass.get("AcquireConnectionTopic")
				            +calClass.get("AcquireConnectionReply")
				            +"以下是neg："
				            +calClass.get("NegativeAnalysis"));
		*/
		weiboClass  = Integer.parseInt((calClass.get("FansPOI")==null)?"0":calClass.get("FansPOI").toString())
		             +Integer.parseInt((calClass.get("TotalPOI")==null)?"0":calClass.get("TotalPOI").toString())
		             +Integer.parseInt((calClass.get("ZombieFansFilter")==null)?"0":calClass.get("ZombieFansFilter").toString())
		             +Integer.parseInt((calClass.get("AcquireWeiboUser")==null)?"0":calClass.get("AcquireWeiboUser").toString())
		             +Integer.parseInt((calClass.get("AcquireWeiboReply")==null)?"0":calClass.get("AcquireWeiboReply").toString())
		             +Integer.parseInt((calClass.get("AcquireFansAndTopics")==null)?"0":calClass.get("AcquireFansAndTopics").toString()); 
		
		sourceClass = ((calClass.get("ConnectionKeyWords")==null)?0:calClass.get("ConnectionKeyWords"))
				     +((calClass.get("KeyWordsMatch")==null)?0:calClass.get("KeyWordsMatch"))
				     +((calClass.get("RecommandQA")==null)?0:calClass.get("RecommandQA"))
				     +((calClass.get("SourceKeyWords")==null)?0:calClass.get("SourceKeyWords"))
				     +((calClass.get("AcquireConnectionTopic")==null)?0:calClass.get("AcquireConnectionTopic"))
				     +((calClass.get("AcquireConnectionReply")==null)?0:calClass.get("AcquireConnectionReply"));
		
		negativeClass = Integer.parseInt((calClass.get("NegativeAnalysis")==null)?"0":calClass.get("NegativeAnalysis").toString());
		myList.add(0,weiboClass);
		myList.add(1,sourceClass);
		myList.add(2,negativeClass);
		return myList;
	}
	//*/图表chart部分结束
	
}
