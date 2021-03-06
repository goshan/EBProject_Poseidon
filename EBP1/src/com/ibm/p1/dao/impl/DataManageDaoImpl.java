package com.ibm.p1.dao.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import weibo4j.model.Status;

import com.ibm.p1.dao.DataManageDao;
import com.ibm.p1.entity.Connection;
import com.ibm.p1.entity.Fans;
import com.ibm.p1.entity.HotWords;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.TotalKeyWords;
import com.ibm.p1.tools.DateUtils;
import com.ibm.p1.tools.HibernateUtil;

public class DataManageDaoImpl extends HibernateDaoSupport implements
		DataManageDao {

	private int num = 20;
	private List<TotalKeyWords> hotWordsList;
	private HotWords howWords;
	private List<HotWords> wordsList;
    private Map<String , ArrayList<String>> myMonth= new HashMap<String , ArrayList<String>>();
    static private String[] JanDays =  {"2013-01-01","2013-01-06","2013-01-11","2013-01-16","2013-01-21","2013-01-26","2013-01-31"};
    static private String[] FebDays =  {"2013-02-01","2013-02-06","2013-02-11","2013-02-16","2013-02-21","2013-02-26","2013-02-28"};
    static private String[] MarDays =  {"2013-03-01","2013-03-06","2013-03-11","2013-03-16","2013-03-21","2013-03-26","2013-03-31"};
    static private String[] AprDays =  {"2013-04-01","2013-04-06","2013-04-11","2013-04-16","2013-04-21","2013-04-26","2013-04-30"};
    static private String[] MayDays =  {"2013-05-01","2013-05-06","2013-05-11","2013-05-16","2013-05-21","2013-05-26","2013-05-31"};
    static private String[] JuneDays = {"2013-06-01","2013-06-06","2013-06-11","2013-06-16","2013-06-21","2013-06-26","2013-06-30"};
    static private String[] JulyDays = {"2013-07-01","2013-07-06","2013-07-11","2013-07-16","2013-07-21","2013-07-26","2013-07-31"};
    static private String[] AugDays =  {"2013-08-01","2013-08-06","2013-08-11","2013-08-16","2013-08-21","2013-08-26","2013-08-31"};
	static private String[] SeptDays = {"2013-09-01","2013-09-06","2013-09-11","2013-09-16","2013-09-21","2013-09-26","2013-09-30"};
	static private String[] OctDays =  {"2013-10-01","2013-10-06","2013-10-11","2013-10-16","2013-10-21","2013-10-26","2013-10-31"};
	static private String[] NovDays =  {"2013-11-01","2013-11-06","2013-11-11","2013-11-16","2013-11-21","2013-11-26","2013-11-30"};
	static private String[] DecDays =  {"2013-12-01","2013-12-06","2013-12-11","2013-12-16","2013-12-21","2013-12-26","2013-12-31"};
    
    public List<TotalKeyWords> getHotWordsList() {
		return hotWordsList;
	}
	public void setHotWordsList(List<TotalKeyWords> hotWordsList) {
		this.hotWordsList = hotWordsList;
	}
	public HotWords getHowWords() {
		return howWords;
	}
	public void setHowWords(HotWords howWords) {
		this.howWords = howWords;
	}
	public List<HotWords> getWordsList() {
		return wordsList;
	}
	public void setWordsList(List<HotWords> wordsList) {
		this.wordsList = wordsList;
	}
	public boolean init(String month){
		ArrayList<String> daysOfMonth = new ArrayList<String>();
		if("1".equals(month)){
			for(int i = 0;i<7;i++){
				daysOfMonth.add(i, JanDays[i]);
			}
		}else if("2".equals(month)){
			for(int i = 0;i<7;i++){
				daysOfMonth.add(i, FebDays[i]);
			}
		}else if("3".equals(month)){
			for(int i = 0;i<7;i++){
				daysOfMonth.add(i, MarDays[i]);
			}
		}else if("4".equals(month)){
			for(int i = 0;i<7;i++){
				daysOfMonth.add(i, AprDays[i]);
			}
		}else if("5".equals(month)){
			for(int i = 0;i<7;i++){
				daysOfMonth.add(i, MayDays[i]);
			}
		}else if("6".equals(month)){
			for(int i = 0;i<7;i++){
				daysOfMonth.add(i, JuneDays[i]);
			}
		}else if("7".equals(month)){
			for(int i = 0;i<7;i++){
				daysOfMonth.add(i, JulyDays[i]);
			}
		}else if("8".equals(month)){
			for(int i = 0;i<7;i++){
				daysOfMonth.add(i, AugDays[i]);
			}
		}else if("9".equals(month)){
			for(int i = 0;i<7;i++){
				daysOfMonth.add(i, SeptDays[i]);
			}
		}else if("10".equals(month)){
			for(int i = 0;i<7;i++){
				daysOfMonth.add(i, OctDays[i]);
			}
		}else if("11".equals(month)){
			for(int i = 0;i<7;i++){
				daysOfMonth.add(i, NovDays[i]);
			}
		}else if("12".equals(month)){
			for(int i = 0;i<7;i++){
				daysOfMonth.add(i, DecDays[i]);
			}
		}else{
			return false;
		}
		myMonth.put(month,daysOfMonth);
		return true;
	}
	public List<Connection> getConnections() {
		// TODO Auto-generated method stub
		
		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql="select c.topic_id as {conn.topic_id}, c.uuid as {conn.uuid}, c.similarity as {conn.similarity}, c.title as {conn.title}, c.content as {conn.content} from connection c order by similarity desc limit "+num;
		List<Connection> list=session.createSQLQuery(sql).addEntity("conn",Connection.class).list();
		tx.commit();
		
		return list;
	}

	public List<HotWords> getHotWords() {
		// TODO Auto-generated method stub
		
		Session session=super.getSessionFactory().getCurrentSession();
		
		Transaction tx=session.beginTransaction();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String s = format1.format(new Date());
		String sql="select t.hotwords_id as {word.hotWords_id},t.word as {word.word}, t.weight as {word.weight}, t.time as {word.time}, t.date as {word.date} from (select * from hotwords h where h.date='"+s+"') t order by time desc limit 10";
		List<HotWords> list=session.createSQLQuery(sql).addEntity("word",HotWords.class).list();
		tx.commit();
		Collections.sort(list);
		return list;
	}
	///*图表chart部分开始
	@SuppressWarnings("unchecked")
	public int getNumOfConnection() {
		// TODO Auto-generated method stub
		Session session=super.getSessionFactory().getCurrentSession();
		List<Integer> numList = new ArrayList<Integer>();
		int num = 0;
		String sql = "select count(*) from Connection";
		numList = getHibernateTemplate().find(sql);
		if(numList.size()== 1){
			String str = String.valueOf(numList.get(0));
			//System.out.println("Str:"+str);
			num = Integer.valueOf(str);
		}
		return num;
	}

	@SuppressWarnings("unchecked")
	public List<Object> getTopCountNumOfConnectionKeyWords() {
		// TODO Auto-generated method stub
		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql = "select * from (select keyword as k ,count(keyword) as c from Connection_keyWords group by keyword) t order by t.c desc";
		List numList=session.createSQLQuery(sql).list();
		tx.commit();
		
		for(int i = 0;i<numList.size();i++){
			
			Object[] os=(Object[]) numList.get(i);				
		}
		
		return numList;
	}
	public int getNumOfZombieFans() {
		// TODO Auto-generated method stub
		Fans fans = new Fans();
		fans.setZombieType(1);
		List list = getHibernateTemplate().findByExample(fans);
		int num = list.size();
		return num;
	}

	public int getNumOfAllFans() {
		// TODO Auto-generated method stub
		List list = getHibernateTemplate().find("from Fans");
		int num = list.size();
		return num;
	}

	public int getNumOfNormalFans() {
		Fans fans = new Fans();
		fans.setZombieType(0);
		List list = getHibernateTemplate().findByExample(fans);
		int num = list.size();
		return num;
	}

	public List<Object> getTopCountNumOfFansKeyWords() {
		// TODO Auto-generated method stub
		String sql = "select keyword ,count(keyword) from Fans_keyWords group by keyword";
		List<Object> numList = new ArrayList<Object>();
		numList = getHibernateTemplate().find(sql);
		return numList;
	}

	public List<Object> getTotalHotWords() {
		// TODO Auto-generated method stub
		String sql = "select word ,count(word),sum(weight) from HotWords group by word";
		List<Object> numList = new ArrayList<Object>();
		numList = getHibernateTemplate().find(sql);
		for(int i = 0;i<numList.size();i++){
			Object[] object = (Object[]) numList.get(i);
			//System.out.println("Object:"+i+" "+object[0]+" "+object[1]+" "+object[2]);
		}
		return numList;
	}

	public List<java.util.Date> getDatesByHotWords(String word) {
		// TODO Auto-generated method stub
		HotWords hotWords = new HotWords();
		hotWords.setWord(word);
		String sql ="from HotWords h where h.word = ?";
		wordsList = getHibernateTemplate().find(sql, word);
		
		List<java.util.Date> dateList = new ArrayList<java.util.Date>();
		for(int i = 0;i<wordsList.size();i++){
			hotWords = wordsList.get(i);
			java.sql.Date date = hotWords.getDate();
			java.util.Date d= new java.util.Date (date.getTime());
			dateList.add(i, d);
			//System.out.println("日期的字符串："+dateList.get(i).toString());
		}
		
		return dateList;
	}
	public int getNumOfAdmin() {
		// TODO Auto-generated method stub
		String type = "admin";
		int nums = 0;
		String sql = "select count(username)from User u where u.type = '"+type+"' group by username";
		List<Object> list = new ArrayList<Object>();
		list = getHibernateTemplate().find(sql);
		if(list.size()== 1){
			String str = String.valueOf(list.get(0));
			//System.out.println("Str:"+str);
			nums = Integer.valueOf(str);
		}
		return nums;
	}
	public int getNumOfUser() {
		// TODO Auto-generated method stub
		String type = "user";
		int nums = 0;
		String sql = "select count(username)from User u where u.type = '"+type+"' group by username";
		List<Object> list = new ArrayList<Object>();
		list = getHibernateTemplate().find(sql);
		if(list.size()== 1){
			String str = String.valueOf(list.get(0));
			//System.out.println("Str:"+str);
			nums = Integer.valueOf(str);
		}
		return nums;
	}
	public int getNumOfCandidate() {
		// TODO Auto-generated method stub
		String type = "candidate";
		int nums = 0;
		String sql = "select count(username)from User u where u.type = '"+type+"' group by username";
		List<Object> list = new ArrayList<Object>();
		list = getHibernateTemplate().find(sql);
		if(list.size()== 1){
			String str = String.valueOf(list.get(0));
			//System.out.println("Str:"+str);
			nums = Integer.valueOf(str);
		}
		return nums;
	}
	
	public List<Object> getReleaseNumAndUser() {
		// TODO Auto-generated method stub
		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql = "select user_id,count(*) from releasehistory group by user_id";
		List numList=session.createSQLQuery(sql).list();
		tx.commit();
		
		for(int i=0;i<numList.size();i++){
			Object[] obj=(Object[])numList.get(i);	
		}
		
		return numList;
	}
	public int getNumOfAllSources() {
		// TODO Auto-generated method stub
		List list = getHibernateTemplate().find("from Source");
		int num = list.size();
		
		return num;
	}
	public void updateNumOfAllSources(int num) {
		// TODO Auto-generated method stub
		Parameter param = getHibernateTemplate().get(Parameter.class, 1);
		
		param.setTotalSourceNum(num);
		getHibernateTemplate().update(param);
	}
	public Map<Integer, LinkedHashMap<String ,Integer>> getNumofToolsByDate(String month) {
		// TODO Auto-generated method stub
		boolean isInit = init(month);
		if(!isInit){
			return null;
		}
		ArrayList<String> dayOfMonth = myMonth.get(month);
		Session session=super.getSessionFactory().getCurrentSession();
		Map<Integer, LinkedHashMap<String ,Integer>> myMap = new LinkedHashMap<Integer, LinkedHashMap<String ,Integer>>(); 
		Transaction tx=session.beginTransaction();
		for(int j = 0;j<dayOfMonth.size()-1;j++){
			String str = "select tools_name as t ,count(tools_name) as n from tools_status as c where c.updateTime between '"+ dayOfMonth.get(j).toString() +"' and '"+ dayOfMonth.get(j+1).toString() +"'group by tools_name";
			//List<Object> myList = session.createSQLQuery(str).list();
			List<Object> myList = session.createSQLQuery(str).addScalar("t", Hibernate.STRING).addScalar("n", Hibernate.INTEGER).list();
			
			LinkedHashMap<String ,Integer> subMapForMap = new LinkedHashMap<String ,Integer>();
			for(int i = 0;i<myList.size();i++){
				Object[] object = (Object[]) myList.get(i);
				subMapForMap.put(object[0].toString(), Integer.parseInt(object[1].toString()));
			}
			int index = j;
			myMap.put(index, subMapForMap);
			//myMap.put("第"+index+"组："+dayOfMonth.get(j).toString()+"到"+dayOfMonth.get(j+1).toString(), subMapForMap);
		}
		
		
		//myMap.put(key, value);
		tx.commit();
		return myMap;
	}
	
	public Map<Integer, Long> getAverageTimePer5Days(String month) throws ParseException {
		boolean isInit = init(month);
		if(!isInit){
			return null;
		}
		HashMap<Integer, Long> map=new LinkedHashMap<Integer, Long>();
		ArrayList<String> dayOfMonth = myMonth.get(month);
		Parameter param= HibernateUtil.getParam();
		//System.out.println("月份数组总的大小是:"+dayOfMonth.size());
		for(int i = 0;i<dayOfMonth.size()-1;i++){
			String str = "select comment_date,comment_text from statuses_comments where comment_user='"+param.getWeibo_account()+"' and comment_date between '"+ dayOfMonth.get(i).toString() +"' and '"+ dayOfMonth.get(i+1).toString() +"'order by comment_date";
			Long result = calAvgTime(str);
			map.put(i, result);
		}
		return map;
	}
	public Long calAvgTime(String str) throws ParseException{ 
		
		Session session=super.getSessionFactory().getCurrentSession();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Transaction tx=session.beginTransaction();
		ArrayList<Long> timeArray = new ArrayList<Long>();
		Long total = (long) 0;
		Long avg =(long) 0;
		int index = 0;
		
		List<Object> myList = session.createSQLQuery(str).list();
		//System.out.println("myListSize:"+myList.size());
		for(int i = 0;i<myList.size();i++){
			Object[] object = (Object[]) myList.get(i);
			Date replyDate= format.parse(object[0].toString());
			String text=(String)object[1];
			//System.out.println("我的所有回复"+replyDate.toString()+" and  "+object[1]);
			String username = null;
			
			
			if(text.startsWith("回复@")){
					username = text.split(":")[0];
					username = username.split("@")[1];
					//System.out.println("我回复的人是： "+username);
					String sql = "select comment_date,comment_text from statuses_comments where comment_user='"+username+"' and comment_date  < '"+ object[0].toString()+"' order by comment_date";
					List<Object> replyToList = session.createSQLQuery(sql).list();
					int j = replyToList.size()-1;
					if(j>=0){
						Object[] replyTo = (Object[]) replyToList.get(j);
						//System.out.println("被回复的那条信息是："+replyTo[0]+" and  "+replyTo[1]);
						Date OriDate = format.parse(replyTo[0].toString());
						timeArray.add(index, (replyDate.getTime() - OriDate.getTime()));
						//System.out.println("这条的时间差是："+Long.parseLong(timeArray.get(index).toString())/1000+"秒");
						index++;
					}	
			}	
		}
		for(int k = 0;k < index;k++){
		    total += Long.parseLong(timeArray.get(k).toString())/ 1000;	  
		}
		
		tx.commit();
		if(index==0){
			return avg;
		}else{
			avg = total/index;
			//System.out.println("总的Index是："+index+"总的回复时间是："+total+"总的平均回复时间是："+avg);
			return avg;
		}
	}
	//*/图表chart部分结束
}
