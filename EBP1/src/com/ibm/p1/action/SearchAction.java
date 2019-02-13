package com.ibm.p1.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;

import com.ibm.p1.entity.Connection;
import com.ibm.p1.entity.HotWords;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.ReleaseHistory;
import com.ibm.p1.entity.Source;
import com.ibm.p1.entity.User;
import com.ibm.p1.entity.SearchResult;
import com.ibm.p1.service.AnalysisService;
import com.ibm.p1.service.CommunicationService;
import com.ibm.p1.service.DataManageService;
import com.ibm.p1.service.ManageService;
import com.ibm.p1.service.ReleaseService;
import com.ibm.p1.service.SearchService;
import com.ibm.p1.service.UserService;
import com.ibm.p1.tools.Utils;
import com.opensymphony.xwork2.ActionContext;

public class SearchAction {
	private Parameter parameter;
	private User user;
	private DataManageService dataManageService;
	private ManageService manageService;
	private ReleaseService releaseService;
	private CommunicationService communicationService;
	private UserService userService;
	private ReleaseHistory releaseHistory = new ReleaseHistory();
	private AnalysisService analysisService;
	private SearchService searchService;
	private List<Source> allSource;
	private List<SearchResult> allSearchResult = new ArrayList<SearchResult>();
	private List<User> allUsers = new ArrayList<User>();
	private List<ReleaseHistory> allReleaseHistorys = new ArrayList<ReleaseHistory>();
	private String type;
	private String err_msg;
	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Source> getAllSource() {
		return allSource;
	}

	public void setAllSource(List<Source> allSource) {
		this.allSource = allSource;
	}

	public DataManageService getDataManageService() {
		return dataManageService;
	}

	public void setDataManageService(DataManageService dataManageService) {
		this.dataManageService = dataManageService;
	}

	public ManageService getManageService() {
		return manageService;
	}

	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}

	public ReleaseService getReleaseService() {
		return releaseService;
	}

	public void setReleaseService(ReleaseService releaseService) {
		this.releaseService = releaseService;
	}

	public CommunicationService getCommunicationService() {
		return communicationService;
	}

	public void setCommunicationService(CommunicationService communicationService) {
		this.communicationService = communicationService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ReleaseHistory getReleaseHistory() {
		return releaseHistory;
	}

	public void setReleaseHistory(ReleaseHistory releaseHistory) {
		this.releaseHistory = releaseHistory;
	}

	public AnalysisService getAnalysisService() {
		return analysisService;
	}

	public void setAnalysisService(AnalysisService analysisService) {
		this.analysisService = analysisService;
	}

	public SearchService getSearchService() {
		return searchService;
	}

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public List<SearchResult> getAllSearchResult() {
		return allSearchResult;
	}

	public void setAllSearchResult(List<SearchResult> allSearchResult) {
		this.allSearchResult = allSearchResult;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public List<ReleaseHistory> getAllReleaseHistorys() {
		return allReleaseHistorys;
	}

	public void setAllReleaseHistorys(List<ReleaseHistory> allReleaseHistorys) {
		this.allReleaseHistorys = allReleaseHistorys;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getErr_msg() {
		return err_msg;
	}

	public void setErr_msg(String err_msg) {
		this.err_msg = err_msg;
	}
	
	public String searchReleaseHistoryById() throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		JSONObject json = new JSONObject(); 
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		String flag = "";
		JsonConfig cfg = new JsonConfig();
		cfg.setJsonPropertyFilter(new PropertyFilter(){
			public boolean apply(Object source, String name, Object value) {
				if(name.equals("user") || name.equals("date") || name.equals("time")) {
					return true;
				}
				else {
					return false;
				}
			}
		});
		
		if (!Utils.isLogin()){
			flag ="not_login";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "401_error_json";
		}
		else if (!Utils.currentUserType().equals("admin")){
			flag ="not_admin";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "403_error_json";
		}
		
		String id = request.getParameter("id");
		ReleaseHistory his = searchService.searchReleaseHistoryById(id);
		if (his == null){
			flag ="history_not_found";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "403_error_json";
		}
		flag ="search_success";
		json.put("flag", flag);
		json.put("release_history", JSONObject.fromObject(his, cfg));
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";
	}

	public String searchQuery() throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		
		if (!Utils.isLogin()){
			return "401_page";
		}
		if (!Utils.currentUserType().equals("admin")){
			return "403_page";
		}
		
		type = request.getParameter("type");
		if (type == null || type.equals("")){
			err_msg = "empty type";
			return "500_page";
		}
		String query = request.getParameter("query");
		if (query == null || query.equals("")){
			type = "empty";
			return "success_search";
		}
		query = new String (query.getBytes("ISO8859-1"), "UTF8");
		if (type.equals("source")){
			allSource = searchService.searchSourceByContent(query);
			return "success_search";
		}
		else if (type.equals("connection")){
			allSearchResult = searchService.searchConnectionByContent(query);
			if (allSearchResult == null){
				err_msg = "Connection host error";
				return "500_page";
			}
			return "success_search";
		}
		else if (type.equals("user")){
			allUsers = searchService.searchUserByUsername(query);
			return "success_search";
		}
		else if (type.equals("history")){
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			if (start.equals("") && end.equals("")){
				allReleaseHistorys = searchService.searchReleaseHistoryByContent(query);
			}
			else if (start.equals("")){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				start = df.format(new Date(0));
				allReleaseHistorys = searchService.searchReleaseHistoryByContentAndDate(query, start, end);
			}
			else if (end.equals("")){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				end = df.format(new Date());
				allReleaseHistorys = searchService.searchReleaseHistoryByContentAndDate(query, start, end);
			}
			else {
				allReleaseHistorys = searchService.searchReleaseHistoryByContentAndDate(query, start, end);
			}
			return "success_search";
		}
		else {
			err_msg = "unknow type";
			return "500_page";
		}
		
	}
}
