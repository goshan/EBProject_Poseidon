package com.ibm.p1.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*; 
import java.util.Map.Entry;
import java.sql.Time;
import java.text.*;
import java.util.List;
import java.text.SimpleDateFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibm.p1.dao.AnalysisDao;
import com.ibm.p1.entity.Connection;
import com.ibm.p1.entity.Connection_keyWords;
import com.ibm.p1.entity.Fans;
import com.ibm.p1.entity.Fans_keyWords;
import com.ibm.p1.entity.HotWords;
import com.ibm.p1.entity.KeyWords;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.KeyWordsSet;
import com.ibm.p1.entity.Source;
import com.ibm.p1.entity.Source_keyWords;
import com.ibm.p1.tools.HibernateUtil;

public class AnalysisDaoImpl extends HibernateDaoSupport implements AnalysisDao{

	private Parameter parameter;
	private List<Fans> fansList;
	private List<Connection> connectionList;
	private List<Fans_keyWords> fans_keyWordsList;
	private List<Connection_keyWords> connection_keyWordsList;
	private KeyWordsSet keyWordsSet;
	private KeyWords keyWords;
	private SessionFactory sessionFactory;
	private static final Logger log = LoggerFactory.getLogger(AnalysisDaoImpl.class);
	public List<Fans> getFansList() {
		return fansList;
	}
	public void setFansList(List<Fans> fansList) {
		this.fansList = fansList;
	}
	@Autowired  
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	public KeyWordsSet getKeyWordsSet() {
		return keyWordsSet;
	}
	public void setKeyWordsSet(KeyWordsSet keyWordsSet) {
		this.keyWordsSet = keyWordsSet;
	}
	public KeyWords getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(KeyWords keyWords) {
		this.keyWords = keyWords;
	}
	public static Logger getLog() {
		return log;
	}
	public Parameter getParameter(){
		int parameter_id = 1;
		parameter = getHibernateTemplate().get(Parameter.class, parameter_id);
		return parameter;
	}
	public void setParameter(Parameter parameter) {
		
		this.parameter = parameter;
	}
	

	public boolean saveFansPOI() {
		// TODO Auto-generated method stub
		
		String dir = parameter.getRootPath()+parameter.getCache_dir_FansPOI();
		Fans fans = new Fans(); 
		Fans_keyWords fans_keyWords = new Fans_keyWords();
		keyWordsSet = new KeyWordsSet(dir+"Weight.res");
		int totalNum = keyWordsSet.getKeyWordsSetNum();
		for(int i = 0 ; i<totalNum ;i++){
			keyWords = keyWordsSet.getKeyWords(i);
			String name = keyWords.getName();
			fans.setUsername(name);
			fans.setZombieType(0);
			fansList = getHibernateTemplate().find("from Fans f where f.username=?",name);
			if(fansList.isEmpty()){
				getHibernateTemplate().save(fans);
				fansList = getHibernateTemplate().findByExample(fans);
				fans = fansList.get(0);
				for(int j = 0;j<keyWords.getWordsNum();j++){		
					fans_keyWords.setKeyword(keyWords.getWord(j));
					fans_keyWords.setWeight(keyWords.getWeight(j));
					fans_keyWords.setFans(fans);
					getHibernateTemplate().save(fans_keyWords);
				}
			}else{
				fans = fansList.get(0);
				int fans_id = fans.getFans_id();
				fans_keyWordsList = getHibernateTemplate().find("from Fans_keyWords d where d.fans = ?", fans);
				for(int j = 0;j<keyWords.getWordsNum();j++){
					fans_keyWords = fans_keyWordsList.get(j);
					fans_keyWords.setKeyword(keyWords.getWord(j));
					fans_keyWords.setWeight(keyWords.getWeight(j));
					fans_keyWords.setFans(fans);
					getHibernateTemplate().update(fans_keyWords);
				}
			}
		}
		
		return true;
	}
	
	public boolean saveTotalPOI() {
		Date now = new Date();
		java.sql.Date date=new java.sql.Date(new java.util.Date().getTime()); 
		DateFormat timeformat = DateFormat.getTimeInstance();
		String timeStr = timeformat.format(now);      
		Time time = Time.valueOf(timeStr);
    	String line = "";
    	String dir = parameter.getRootPath()+parameter.getCache_dir_TotalPOI();
    	File f = new File(dir+"Weight.res");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
	    	while ((line = br.readLine()) != null){
	    		String name = line.substring(0, line.indexOf("\t"));
	    		float weight = new Float(line.substring(line.indexOf("\t")));
	    		HotWords hotwords = new HotWords();
	    		hotwords.setDate(date);
	    		hotwords.setTime(time);
	    		hotwords.setWord(name);
	    		hotwords.setWeight(weight);
	    		getHibernateTemplate().save(hotwords);
	    	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return true;
	}

	public boolean saveZombieTypeCheck() {
		// TODO Auto-generated method stub
		
		String dir = parameter.getRootPath()+parameter.getCache_dir_ZombieType();
		File file = new File(dir+"ZombieType.res");
	    BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            int line = 1;
	            while ((tempString = reader.readLine()) != null) {
	                // 显示行号    
	            	String[] result = tempString.split("\\\t");
	            	String username =result[0];
	            	int zombieType = Integer.parseInt(result[1]);   
	            	Fans fansToUpdate = new Fans();
	            	fansList = getHibernateTemplate().find("from Fans f where f.username=?",username);
	            	fansToUpdate = fansList.get(0);
	            	fansToUpdate.setZombieType(zombieType);     
	            	getHibernateTemplate().update(fansToUpdate);	            	
	            	line++;
	            }
	            reader.close();
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                    return true;
	                } catch (IOException e1) {
	                	return false;
	                }
	            }
	        }
	}

	public boolean saveConnectionKeyWords() {
		// TODO Auto-generated method stub
		String dir = parameter.getRootPath()+parameter.getCache_dir_ConnectionKeyWords();
		Connection connection = new Connection(); 
		Connection_keyWords connection_keyWords = new Connection_keyWords();
		keyWordsSet = new KeyWordsSet(dir+"Weight.res");
		int totalNum = keyWordsSet.getKeyWordsSetNum();
		for(int i = 0 ; i<totalNum ;i++){
			keyWords = keyWordsSet.getKeyWords(i);
			if(keyWords.getName().startsWith(".")){
				continue;
			}
			String[] result = keyWords.getName().split("\\.");
			String uuid = result[0];
			
			connection.setUuid(uuid);
			connection.setSimilarity(0);
			connection.setTitle("");
			connection.setContent("");
			connectionList = getHibernateTemplate().find("from Connection c where c.uuid=?",uuid);
			if(connectionList.isEmpty()){
				getHibernateTemplate().save(connection);
				connectionList = getHibernateTemplate().findByExample(connection);
				connection = connectionList.get(0);
				for(int j = 0;j<keyWords.getWordsNum();j++){		
					connection_keyWords.setKeyword(keyWords.getWord(j));
					connection_keyWords.setWeight(keyWords.getWeight(j));
					connection_keyWords.setConnection(connection);
					getHibernateTemplate().save(connection_keyWords);
				}
			}else{
				connection = connectionList.get(0);
				int topic_id = connection.getTopic_id();
				connection_keyWordsList = getHibernateTemplate().find("from Connection_keyWords c where c.connection = ?", connection); 
				for(int j = 0;j<keyWords.getWordsNum();j++){
					connection_keyWords = connection_keyWordsList.get(j);
					connection_keyWords.setKeyword(keyWords.getWord(j));
					connection_keyWords.setWeight(keyWords.getWeight(j));
					connection_keyWords.setConnection(connection);
					getHibernateTemplate().update(connection_keyWords);
				}
			}		
			
			
		}
		return true;
	}
	
	public boolean saveConnectionInfo(){
		String dir = parameter.getRootPath()+parameter.getCache_dir_connection();
		File file = new File(dir);
		String []topics = file.list();
        try {
        	for (String uuid : topics){
        		File topic = new File(dir+"\\"+uuid);
        		BufferedReader reader = new BufferedReader(new FileReader(topic));
        		reader.readLine();
        		String title = reader.readLine();
        		String content = reader.readLine();
        		String name = uuid.substring(0, uuid.lastIndexOf("."));
        		String queryString = "from Connection c where c.uuid=:myName";
        		String paramName= "myName";
        		connectionList = getHibernateTemplate().findByNamedParam(queryString, paramName, name);
        		Connection connection = connectionList.get(0);
        		connection.setTitle(title);
        		connection.setContent(content);
        		getHibernateTemplate().update(connection);
        		reader.close();
        	}
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public boolean saveKeyWordsMatch() {
		// TODO Auto-generated method stub
		String dir = parameter.getRootPath()+parameter.getCache_dir_KeyWordsMatch();
		File file = new File(dir+"Similarity.res");
	    BufferedReader reader = null;
	        try {	            
	            reader = new BufferedReader(new FileReader(file));
	            Connection connection = new Connection(); 
	            //HotWords hotWords = new HotWords();
	            String tempString = null;
	            while ((tempString = reader.readLine()) != null) {
	            	String[] result = tempString.split("\t");
	            	String name =result[0];
	            	float weight = Float.parseFloat(result[1]);
	            	int index = name.indexOf(".txt");
	            	if (index != -1) {
	            		name = name.substring(0, index);
	            		String queryString = "from Connection c where c.uuid=:myName";
	            		String paramName= "myName";
	            		connectionList = getHibernateTemplate().findByNamedParam(queryString, paramName, name);
	            		connection = connectionList.get(0);
	            		connection.setSimilarity(weight);
	            		getHibernateTemplate().update(connection);
	            	}
	            }
	            reader.close();
	            return true;
	        }catch(IOException e) {
	            e.printStackTrace();
	            return false;
	        }finally{
	            if (reader != null) {
	                try {
	                    reader.close();
	                    return true;
	                } catch (IOException e1) {
	                	return false;
	                }
	            }
	        }
	}
	
	public boolean saveQuestion(){
		return true;
	}
	
	public ArrayList<Connection> loadConnectionFromUuid(ArrayList<Entry<String, Double>> uuids){
		ArrayList<Connection> res = new ArrayList<Connection>();
		for (Entry<String, Double> uuid : uuids){
			String id = uuid.getKey();
			String queryString = "from Connection c where c.uuid=:myName";
			String paramName= "myName";
			connectionList = getHibernateTemplate().findByNamedParam(queryString, paramName, id);
			Connection connection = connectionList.get(0);
			res.add(connection);
		}
		return res;
	}
	public boolean saveSourceKeyWords() {
		// TODO Auto-generated method stub
		String dir = parameter.getRootPath()+parameter.getCache_dir_SourceKeyWords();
		Source_keyWords source_keyWords = new Source_keyWords();
		Source source = new Source();
		keyWordsSet = new KeyWordsSet(dir+"Weight.res");
		int totalNum = keyWordsSet.getKeyWordsSetNum();
		int id = 0;
		keyWords = keyWordsSet.getKeyWords(id);
		
		source = getHibernateTemplate().get(Source.class,Integer.parseInt(keyWords.getName()));
		
		for(int j = 0 ;j<keyWords.getWordsNum();j++){
			source_keyWords.setKeyword(keyWords.getWord(j));
			source_keyWords.setWeight(keyWords.getWeight(j));
			source_keyWords.setSource(source);
			getHibernateTemplate().save(source_keyWords);
		}
		return true;
	}
		

}
