package com.ibm.p1.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import java.sql.Timestamp;

import com.googlecode.jsonplugin.annotations.JSON;
import com.ibm.p1.entity.Connection;
import com.ibm.p1.entity.HotWords;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.ReleaseHistory;
import com.ibm.p1.entity.Source;
import com.ibm.p1.entity.User;
import com.ibm.p1.service.AnalysisService;
import com.ibm.p1.service.CommunicationService;
import com.ibm.p1.service.DataManageService;
import com.ibm.p1.service.ManageService;
import com.ibm.p1.service.ReleaseService;
import com.ibm.p1.service.UserService;
import com.ibm.p1.tools.Utils;
import com.opensymphony.xwork2.ActionContext;

public class CommunicationAction {
	private Parameter parameter;
	private User user;
	private List<Connection> conns;
	private List<Source> allSource;
	private List<HotWords> hotWords;
	private String shownPageType;
	private DataManageService dataManageService;
	private ManageService manageService;
	private ReleaseService releaseService;
	private CommunicationService communicationService;
	private UserService userService;
	private ReleaseHistory releaseHistory = new ReleaseHistory();
	private AnalysisService analysisService;
	
	public Parameter getParameter() {
		return parameter;
	}
	
	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}
	
	public List<Connection> getConns() {
		return conns;
	}
	
	public void setConns(List<Connection> conns) {
		this.conns = conns;
	}
	
	public List<HotWords> getHotWords() {
		return hotWords;
	}
	
	public void setHotWords(List<HotWords> hotWords) {
		this.hotWords = hotWords;
	}
	
	public void setAllSource(List<Source> allSource) {
		this.allSource = allSource;
	}
	
	public List<Source> getAllSource() {
		return allSource;
	}
	
	public String getShownPageType() {
		return shownPageType;
	}
	
	public void setShownPageType(String shownPageType) {
		this.shownPageType = shownPageType;
	}
	
	public ReleaseHistory getReleaseHistory() {
		return releaseHistory;
	}
	
	public void setReleaseHistory(ReleaseHistory releaseHistory) {
		this.releaseHistory = releaseHistory;
	}
	
	public DataManageService getDataManageService() {
		return dataManageService;
	}
	
	public void setDataManageService(DataManageService dataManageService) {
		this.dataManageService = dataManageService;
	}
	
	public CommunicationService getCommunicationService() {
		return communicationService;
	}
	
	public void setCommunicationService(CommunicationService communicationService) {
		this.communicationService = communicationService;
	}
	
	public ReleaseService getReleaseService() {
		return releaseService;
	}
	
	public void setReleaseService(ReleaseService releaseService) {
		this.releaseService = releaseService;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public ManageService getManageService() {
		return manageService;
	}
	
	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}
	
	public AnalysisService getAnalysisService() {
		return analysisService;
	}
	
	public void setAnalysisService(AnalysisService analysisService) {
		this.analysisService = analysisService;
	}
	
	public String afterLogin(){
		if (!Utils.isLogin()){
			return "401_page";
		}
		if (Utils.currentUserType().equals("admin")){
			return "admin_page";
		}
		else if (Utils.currentUserType().equals("user")){
			return "user_page";
		}
		else if (Utils.currentUserType().equals("candidate")){
			return "candidate_page";
		}
		return "500_page";
	}
	
	public String publish(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		
		if (!Utils.isLogin()){
			return "401_page";
		}
		else if (!Utils.currentUserType().equals("admin")){
			return "403_page";
		}
		
		shownPageType = (String)request.getParameter("type");
		
		
		hotWords=this.dataManageService.getHotWords();
		if (shownPageType.equals("connection")){
			conns=this.dataManageService.getConnections();
			
		}
		else {
			allSource = this.communicationService.getAllSources();
		}
			
		return "success_publishPage";
	}
	
	//================================
	
	public String showSearchPage(){
		return "success_showSearchPage";
	}
	
	public String showMonitorPage(){
		return "success_showMonitorPage";
	}
	
	public String showChartPage(){
		return "success_showChartPage";
	}
	public String showLibraryPage(){
		return "success_showLibraryPage";
	}
	public String addSource() throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String content = request.getParameter("content");
		JSONObject json = new JSONObject(); 
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		String flag = null;
		
		Date now = new Date();
 		java.sql.Date date=new java.sql.Date(new java.util.Date().getTime()); 
 		Timestamp time = new Timestamp(date.getTime());
 		System.out.println("time的时间是:"+time);
 		
 		Map<String,Object> session = ActionContext.getContext().getSession();
 		String request_type = request.getParameter("request_type");
 		if("mobile".equals(request_type)){
 			String access_token = request.getParameter("access_token");
			System.out.println("accesstoken是"+access_token);
			User user = userService.getUserByAccessToken(access_token);
			if( null == user||user.equals("")){
				flag ="fail_to_get_user_by_accesstoken";
				json.put("flag", flag);
				pw.write(json.toString());
				pw.flush();
				pw.close();
				return "fail_to_get_user_by_accesstoken";
			}
			parameter = manageService.getAcquireInfoById(1);
			int source_count_num = parameter.getSource_count_num();
 	 		String dir = parameter.getRootPath()+parameter.getCache_dir_SourceKeyWords();
 	 		System.out.println("目录是："+dir);
 	 		Source source = new Source();
 	 		
 	 		source.setContent(content);
 			source.setCreated_at(time);
 			source.setUser(user);
 			
 			communicationService.submitSource(source);
 			source = communicationService.getLastSource();
 			communicationService.sourceAnalysis(source_count_num, source,dir);
 			analysisService.SourceKeyWords();
 			flag ="success_addSourceByMobile";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
 			return "success_addSourceByMobile";
 		}else if("web".equals(request_type)){
 			int user_idTemp = (Integer) session.get("user_id");
 	 		//int user_id = Integer.parseInt(user_idTemp);
 	 		int user_id = user_idTemp;
 	 		
 	 		parameter = manageService.getAcquireInfoById(1);
 	 		
 	 		user = userService.getUserByUserId(user_id);
 	 		
 	 		int source_count_num = parameter.getSource_count_num();
 	 		String dir = parameter.getRootPath()+parameter.getCache_dir_SourceKeyWords();
 	 		System.out.println("目录是："+dir);
 	 		Source source = new Source();
 	 		
 	 		source.setContent(content);
 			source.setCreated_at(time);
 			source.setUser(user);
 			
 			communicationService.submitSource(source);
 			source = communicationService.getLastSource();
 			communicationService.sourceAnalysis(source_count_num, source,dir);
 			analysisService.SourceKeyWords();
 			return "success_addSource";
 		}
 		flag ="unknown_request_type";
		json.put("flag", flag);
		pw.write(json.toString());
		pw.flush();
		pw.close();
 		return "unknown_request_type";
		
		
	}
	public String gotoAddPage(){
		
		return "success_gotoAddPage";
	}
	
	public String getSourceById(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String idTemp = request.getParameter("id");
		int id = Integer.parseInt(idTemp);
		Source source = new Source();
		source = communicationService.getSourceById(id);
		
		return "success_getSourceById";
	}
	public String getSourcesYesterdayAndToday() throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject(); 
		PrintWriter pw  = response.getWriter();
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
  		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
  		jsonConfig.setExcludes(new String[]{"source","source_keyWords","user","releaseHistory","currentToolsStatus"});
  
		String request_type = request.getParameter("request_type");  
		String flag = null;
		if("mobile".equals(request_type)){
			String access_token = request.getParameter("access_token");
			System.out.println("accesstoken是"+access_token);
			User user = userService.getUserByAccessToken(access_token);
			if(null == user||user.equals("")){
				flag ="fail_to_get_user_by_accesstoken";
				json.put("flag", flag);
				pw.write(json.toString());
				pw.flush();
				pw.close();
				return "fail_to_get_user_by_accesstoken";
			}
			allSource = this.communicationService.getSourcesYesterdayAndToday();
			JSONArray jsonArray = JSONArray.fromObject(allSource,jsonConfig);
			List<User> allUsers = new ArrayList<User>();
			for(int i = 0;i<allSource.size();i++){
				User returnUser = new User();
				returnUser.setUser_id(allSource.get(i).getUser().getUser_id());
				returnUser.setUsername(allSource.get(i).getUser().getUsername());
				allUsers.add(i, returnUser);
			}
			jsonArray.add(allUsers, jsonConfig);
			flag ="success_get_sources";
			json.put("flag", flag);
			json.put("source", jsonArray);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "success_getSourcesYesterdayAndToday";
		}
		flag ="unknown_request_type";
		json.put("flag", flag);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "unknown_request_type";
		
		
	}
	public String searchSourceByContent(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String content = request.getParameter("searchContent");
		
		allSource = communicationService.searchSourceByContent(content);
		
		return "success_searchSourceByContent";
	}
	//mobile client functions
	public String getConnectionRecommend() throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
  		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
  		jsonConfig.setExcludes(new String[]{"connection","connection_keyWords"});  
  		String request_type = request.getParameter("request_type");
  		String flag = null;
  		if("mobile".equals(request_type)){
			String access_token = request.getParameter("access_token");
			//System.out.println("accesstoken是"+access_token);
			User user = userService.getUserByAccessToken(access_token);
			if(null == user||user.equals("")){
				flag ="fail_to_get_user_by_accesstoken";
				json.put("flag", flag);
				pw.write(json.toString());
				pw.flush();
				pw.close();
				return "fail_to_get_user_by_accesstoken";
			}
			conns=this.dataManageService.getConnections();
			flag ="success_get_connection_recommend";
			json.put("flag", flag);
			JSONArray jsonArray = new JSONArray();
			jsonArray.add(json);
			jsonArray = JSONArray.fromObject(conns,jsonConfig);
			json.put("connection", jsonArray);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "success_getConnectionRecommend";
		}
  		flag ="unknown_request_type";
		json.put("flag", flag);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "unknown_request_type";
	}
	public String getAllSourceForMobile() throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		String request_type = request.getParameter("request_type"); 
		JsonConfig jsonConfig = new JsonConfig();  //建立配置文件
  		jsonConfig.setIgnoreDefaultExcludes(false);  //设置默认忽略
  		jsonConfig.setExcludes(new String[]{"source","source_keyWords","user","releaseHistory","currentToolsStatus"});
  		String flag = null;
  		
  		if("mobile".equals(request_type)){
			String access_token = request.getParameter("access_token");
			System.out.println("accesstoken是"+access_token);
			User user = userService.getUserByAccessToken(access_token);
			if(null == user||user.equals("")){
				flag ="fail_to_get_user_by_accesstoken";
				json.put("flag", flag);
				pw.write(json.toString());
				pw.flush();
				pw.close();
				return "fail_to_get_user_by_accesstoken";
			}
			allSource = this.communicationService.getAllSources();
			JSONArray jsonArray = JSONArray.fromObject(allSource,jsonConfig);
			List<User> allUsers = new ArrayList<User>();
			for(int i = 0;i<allSource.size();i++){
				User returnUser = new User();
				returnUser.setUser_id(allSource.get(i).getUser().getUser_id());
				returnUser.setUsername(allSource.get(i).getUser().getUsername());
				allUsers.add(i, returnUser);
			}
			jsonArray.add(allUsers, jsonConfig);
			flag ="success_get_all_sources";
			json.put("flag", flag);
			json.put("source", jsonArray);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "success_getAllSourceForMobile";
		}
  		flag ="unknown_request_type";
		json.put("flag", flag);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "unknown_request_type";
	}
}
