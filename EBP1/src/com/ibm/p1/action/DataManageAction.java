package com.ibm.p1.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.googlecode.jsonplugin.annotations.JSON;
import com.ibm.p1.entity.TotalKeyWords;
import com.ibm.p1.service.DataManageService;
import com.ibm.p1.service.ManageService;
import com.opensymphony.xwork2.ActionContext;

public class DataManageAction {
	private ManageService manageService;
	private DataManageService dataManageService;
	private List<TotalKeyWords> hotWordsList;
	private Map<String , ArrayList<String>> myDaysOfMonth= new HashMap<String , ArrayList<String>>();
	static private String[] JanDays =  {"1-5","6-10","11-15","16-20","21-25","26-31"};
    static private String[] FebDays =  {"1-5","6-10","11-15","16-20","21-25","26-28"};
    static private String[] MarDays =  {"1-5","6-10","11-15","16-20","21-25","26-31"};
    static private String[] AprDays =  {"1-5","6-10","11-15","16-20","21-25","26-30"};
    static private String[] MayDays =  {"1-5","6-10","11-15","16-20","21-25","26-31"};
    static private String[] JuneDays = {"1-5","6-10","11-15","16-20","21-25","26-30"};
    static private String[] JulyDays = {"1-5","6-10","11-15","16-20","21-25","26-31"};
    static private String[] AugDays =  {"1-5","6-10","11-15","16-20","21-25","26-31"};
	static private String[] SeptDays = {"1-5","6-10","11-15","16-20","21-25","26-30"};
	static private String[] OctDays =  {"1-5","6-10","11-15","16-20","21-25","26-31"};
	static private String[] NovDays =  {"1-5","6-10","11-15","16-20","21-25","26-30"};
	static private String[] DecDays =  {"1-5","6-10","11-15","16-20","21-25","26-31"};
    
	@JSON(serialize=false)
	public ManageService getManageService() {
		return manageService;
	}
	@JSON(serialize = false)
	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}
	@JSON(serialize=false)
	public DataManageService getDataManageService() {
		return dataManageService;
	}

	@JSON(serialize=false)
	public void setDataManageService(DataManageService dataManageService) {
		this.dataManageService = dataManageService;
	}
	@JSON(serialize=false)
	public List<TotalKeyWords> getHotWordsList() {
		return hotWordsList;
	}

	@JSON(serialize=false)
	public void setHotWordsList(List<TotalKeyWords> hotWordsList) {
		this.hotWordsList = hotWordsList;
	}
	public boolean init(String month){
		ArrayList<String> daysOfMonth = new ArrayList<String>();
		if("1".equals(month)){
			for(int i = 0;i<6;i++){
				daysOfMonth.add(i, JanDays[i]);
			}
		}else if("2".equals(month)){
			for(int i = 0;i<6;i++){
				daysOfMonth.add(i, FebDays[i]);
			}
		}else if("3".equals(month)){
			for(int i = 0;i<6;i++){
				daysOfMonth.add(i, MarDays[i]);
			}
		}else if("4".equals(month)){
			for(int i = 0;i<6;i++){
				daysOfMonth.add(i, AprDays[i]);
			}
		}else if("5".equals(month)){
			for(int i = 0;i<6;i++){
				daysOfMonth.add(i, MayDays[i]);
			}
		}else if("6".equals(month)){
			for(int i = 0;i<6;i++){
				daysOfMonth.add(i, JuneDays[i]);
			}
		}else if("7".equals(month)){
			for(int i = 0;i<6;i++){
				daysOfMonth.add(i, JulyDays[i]);
			}
		}else if("8".equals(month)){
			for(int i = 0;i<6;i++){
				daysOfMonth.add(i, AugDays[i]);
			}
		}else if("9".equals(month)){
			for(int i = 0;i<6;i++){
				daysOfMonth.add(i, SeptDays[i]);
			}
		}else if("10".equals(month)){
			for(int i = 0;i<6;i++){
				daysOfMonth.add(i, OctDays[i]);
			}
		}else if("11".equals(month)){
			for(int i = 0;i<6;i++){
				daysOfMonth.add(i, NovDays[i]);
			}
		}else if("12".equals(month)){
			for(int i = 0;i<6;i++){
				daysOfMonth.add(i, DecDays[i]);
			}
		}else{
			return false;
		}
		myDaysOfMonth.put(month,daysOfMonth);
		return true;
	}
	//chart部分
	public String dynamicChart() throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		int newSource = dataManageService.getNumOfNewSource();
		json.put("newSource", newSource);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "success_dynamicChart";
	}
	public String zombieChart() throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		
		int numOfZombie = dataManageService.getNumOfZombieFans();
		int numOfNormal = dataManageService.getNumOfNormalFans();
		
		json.put("NumOfZombie", numOfZombie);
		json.put("NumOfNormal", numOfNormal);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "success_zombieChart";
	}
	
	public String lineChart() throws IOException, ParseException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		String month=request.getParameter("month");
		Map<Integer,Double> map=dataManageService.getAverageReplyTimePer5Days(month);
		for(int i=0;i<map.size();i++){
			json.put(i, map.get(i));
		}
		init(month);
		ArrayList<String> daysOfMonth = myDaysOfMonth.get(month);
		json.put("daysOfMonth", daysOfMonth);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "success_lineChart";
	}
	public String columnChart() throws IOException, ParseException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		String month=request.getParameter("month");
		Map<String, LinkedHashMap<String ,Integer>> myMap = new LinkedHashMap<String, LinkedHashMap<String ,Integer>>();
		myMap = dataManageService.getNumofToolsByDate(month);
		System.out.println("myMap: "+myMap);
		for(int i=0;i<myMap.size();i++){
			json.put(i, myMap.get("第"+i+"组"));
		}
		init(month);
		ArrayList<String> daysOfMonth = myDaysOfMonth.get(month);
		ArrayList<String> finalDays = new ArrayList<String>();
		finalDays.add("1-5");
		finalDays.addAll(daysOfMonth);
		json.put("daysOfMonth", finalDays);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "success_columnChart";
	}



	public String showChartPage(){
		//zombieChart();
		return "success_showChartPage";
	}

	
	//测试数据管理chart部分
/*
	public String getNumOfConnection()throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		int num = dataManageService.getNumOfConnection();
		json.put("num", num);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "success_getNumOfConnection";
	} 
	public String getTopCountNumOfConnectionKeyWords()throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		Map<String, Integer> myMap =  new HashMap<String, Integer>();
		myMap = dataManageService.getTopCountNumOfConnectionKeyWords();
		
		json.put("num", myMap);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "success_getTopCountNumOfConnectionKeyWords";
	} 
	public String getNumOfZombieFans()throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		int num = dataManageService.getNumOfZombieFans();
		json.put("num", num);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "success_getNumOfZombieFans";
	} 
	public String getNumOfAllFans()throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		int num = dataManageService.getNumOfAllFans();
		json.put("num", num);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "success_getNumOfAllFans";
	} 
	public String getNumOfNormalFans()throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		int num = dataManageService.getNumOfNormalFans();
		json.put("num", num);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "success_getNumOfNormalFans";
	} 
	public String getTopCountNumOfFansKeyWords()throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		Map<String, Integer> myMap =  new HashMap<String, Integer>();
		myMap = dataManageService.getTopCountNumOfFansKeyWords();
		json.put("num", myMap);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "success_getTopCountNumOfFansKeyWords";
	} 
	public String getTotalhotWords()throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject jsonObj = new JSONObject(); 
		JSONArray json = new JSONArray();
		hotWordsList = dataManageService.getTotalHotWords();
		json = JSONArray.fromObject(hotWordsList);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "success_getTotalhotWords";
	} 
	public String getNumOfAdmin()throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		int num = dataManageService.getNumOfAdmin();
		json.put("num", num);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "success_getNumOfAdmin";
	} 
	public String getNumOfUser()throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		int num = dataManageService.getNumOfUser();
		json.put("num", num);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "success_getNumOfUser";
	} 
	public String getNumOfCandidate()throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		int num = dataManageService.getNumOfCandidate();
		json.put("num", num);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "success_getNumOfCandidate";
	} */
}
