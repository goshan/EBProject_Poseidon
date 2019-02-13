package com.ibm.p1.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import weibo4j.Comments;
import weibo4j.Timeline;
import weibo4j.model.Comment;
import weibo4j.model.Status;

import com.ibm.p1.dao.ReleaseDao;
import com.ibm.p1.entity.Fans;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.ReleaseHistory;
import com.ibm.p1.entity.Source;
import com.ibm.p1.service.ReleaseService;
import com.ibm.p1.tools.HibernateUtil;

public class ReleaseServiceImpl implements ReleaseService{
	
	private ReleaseDao releaseDao;
	private String access_token;
	private String weibo_account;
	private Parameter param;
	private String volume;
	
	public ReleaseServiceImpl(){
		param=HibernateUtil.getParam();
	}
	
	public ReleaseServiceImpl(Parameter param){
		this.param = param;
	}
	public ReleaseDao getReleaseDao() {
		return releaseDao;
	}

	public void setReleaseDao(ReleaseDao releaseDao) {
		this.releaseDao = releaseDao;
	}

	public boolean saveReleaseSinaWeiboHistory(ReleaseHistory releaseHistory) {
		try {
			// TODO Auto-generated method stub
			releaseDao.saveReleaseSinaWeiboHistory(releaseHistory);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean saveReleaseSinaWeiboHistoryToDraft(ReleaseHistory releaseHistory) {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			releaseDao.saveReleaseSinaWeiboHistoryToDraft(releaseHistory);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}
	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getWeibo_account() {
		return weibo_account;
	}

	public void setWeibo_account(String weibo_account) {
		this.weibo_account = weibo_account;
	}

	public Parameter getParam() {
		return param;
	}

	public void setParam(Parameter param) {
		this.param = param;
	}
	
	public void init(){
		this.access_token = param.getAccess_token();
		this.weibo_account = param.getWeibo_account();
		this.volume = param.getRootPath().substring(0, param.getRootPath().indexOf("\\"));
	}
	
	public String publishWeibo(String title, String content){
		this.init();
		try {
			Timeline tm = new Timeline();
			tm.client.setToken(this.access_token);
			Status status = tm.UpdateStatus(content);
		}
		catch (Exception e){
			String errorCode = e.getLocalizedMessage();
			System.out.println("weibo publish failed: "+errorCode);
			if(errorCode.indexOf(":") != -1){
				errorCode = errorCode.substring(0, errorCode.indexOf(":"));
				return errorCode;
			}else return "noErrorCode";
		}
		return "200";
	}
	
	public String replyWeibo(String status_id, String comment_id, String comments){
		this.init();
		try {
			Comments cm = new Comments();
			cm.client.setToken(access_token);
			Comment com = cm.replyComment(comment_id, status_id, comments);
		}
		catch (Exception e){
			String errorCode = e.getLocalizedMessage();
			System.out.println("weibo reply failed: "+errorCode);
			errorCode = errorCode.substring(0, errorCode.indexOf(":"));
			return errorCode;
		}
		return "200";
	}

	public boolean getFansList() {
		// TODO Auto-generated method stub
		this.init();
		try {
			String exe = this.param.getRootPath()+this.param.getTools_dir_KeyWordsMatch()+" && KeyWordsMatch";
			String input1 = this.param.getRootPath()+this.param.getCache_dir_AtFans()+"AtWeight.res";
			String input2 = this.param.getRootPath()+this.param.getCache_dir_FansPOI()+"Weight.res";
			String output_dir = this.param.getRootPath()+this.param.getCache_dir_AtFans();
			String cmd = "cmd /c \""+this.volume+" && cd "+exe+" "+input1+" "+input2+" "+output_dir+"\"";
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String res = br.readLine();
			if (res.equals("process_finished")){
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void writeContentAndSource(String content,List<Source> allSource) {
		// TODO Auto-generated method stub
		String dir=param.getRootPath()+param.getCache_dir_AtFans();
		String path = dir+"AtSources.txt";
		
		try {
			File fileName = new File(path);
			if(fileName.exists()){
				fileName.delete();
			}
			fileName.createNewFile();
			String sourceStr = "####0####"+"\r\n"+content+"\r\n"+"============================"+"\r\n";
			FileWriter writer = new FileWriter(path, true);
			writer.write(sourceStr);
			for(int i = 0 ;i<allSource.size()-1;i++){
				String allSourceStr = "####"+allSource.get(i).getSource_id()+"####"+"\r\n"+allSource.get(i).getContent()+"\r\n"+"============================"+"\r\n";
				writer.write(allSourceStr);
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Fans> getRecommandFans(){
		ArrayList<Fans> res = new ArrayList<Fans>();
		try {
			String file_path = param.getRootPath()+param.getCache_dir_AtFans()+"Similarity.res";
			File f = new File(file_path);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			String line = "";
			HashMap<String, Double> map = new HashMap<String,Double>();
			while ((line = br.readLine()) != null){
				String name = line.substring(0, line.indexOf("\t"));
				Double similarity = new Double(line.substring(line.indexOf("\t")+1));
				map.put(name, similarity);
			}
			ArrayList<Entry<String,Double>> list = new ArrayList<Entry<String,Double>>(map.entrySet());
			Collections.sort(list, new Comparator<Object>(){
				public int compare(Object e1, Object e2){   
					double v1 = ((Entry<String, Double>)e1).getValue();   
					double v2 = ((Entry<String, Double>)e2).getValue();
					if (v1 == v2){
						return 0;
					}
					else if (v1 > v2){
						return -1;
					}
					else {
						return 1;
					}
				}
			});
			ArrayList<Entry<String, Double>> tmp = new ArrayList<Entry<String, Double>>();
			for (int i=0; i<(list.size()>10 ? 10 : list.size()); i++){
			    tmp.add(list.get(i));
			}
			return releaseDao.getFansByEntry(tmp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}
