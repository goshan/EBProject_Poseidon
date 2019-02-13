package com.ibm.p1.action;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ibm.p1.dao.AcquireDao;
import com.ibm.p1.entity.Connection;
import com.ibm.p1.entity.ConnectionsReply;
import com.ibm.p1.entity.ConnectionsTopic;
import com.ibm.p1.entity.Fans;
import com.ibm.p1.entity.HotWords;
import com.ibm.p1.entity.MyComment;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.Source;
import com.ibm.p1.service.DataManageService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;

import com.ibm.p1.entity.ReleaseHistory;
import com.ibm.p1.entity.User;
import com.ibm.p1.service.AnalysisService;
import com.ibm.p1.service.CommunicationService;
import com.ibm.p1.service.ManageService;
import com.ibm.p1.service.ReleaseService;
import com.ibm.p1.service.UserService;
import com.ibm.p1.tools.ConnectionsForumTopics;
import com.ibm.p1.tools.Utils;
import com.opensymphony.xwork2.ActionContext;

public class ReleaseAction{
	
	private User user;
	private List<User> allUsers;
	private List<Source> allSource;
	private Parameter param;
	private UserService userService;
	private DataManageService dataManageService;
	private ReleaseService releaseService;
	private AnalysisService analysisService;
	private CommunicationService communicationService;
	private ManageService manageService;
	private AcquireDao dao;
	private ReleaseHistory releaseHistory = new ReleaseHistory();
	//private InputStream jsonInputStream;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getAllUsers() {
		return allUsers;
	}
	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}
	public List<Source> getAllSource() {
		return allSource;
	}
	public void setAllSource(List<Source> allSource) {
		this.allSource = allSource;
	}
	public Parameter getParam() {
		return param;
	}
	public void setParam(Parameter param) {
		this.param = param;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public DataManageService getDataManageService() {
		return dataManageService;
	}
	public void setDataManageService(DataManageService dataManageService) {
		this.dataManageService = dataManageService;
	}
	public ReleaseService getReleaseService() {
		return releaseService;
	}
	public void setReleaseService(ReleaseService releaseService) {
		this.releaseService = releaseService;
	}
	public AnalysisService getAnalysisService() {
		return analysisService;
	}
	public void setAnalysisService(AnalysisService analysisService) {
		this.analysisService = analysisService;
	}
	public CommunicationService getCommunicationService() {
		return communicationService;
	}
	public void setCommunicationService(CommunicationService communicationService) {
		this.communicationService = communicationService;
	}
	public ManageService getManageService() {
		return manageService;
	}
	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}
	public AcquireDao getDao() {
		return dao;
	}
	public void setDao(AcquireDao dao) {
		this.dao = dao;
	}
	public ReleaseHistory getReleaseHistory() {
		return releaseHistory;
	}
	public void setReleaseHistory(ReleaseHistory releaseHistory) {
		this.releaseHistory = releaseHistory;
	}
	/*
	public InputStream getJsonInputStream() {
		return jsonInputStream;
	}
	public void setJsonInputStream(InputStream jsonInputStream) {
		this.jsonInputStream = jsonInputStream;
	}
	*/
	public String releaseSinaWeibo() throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		JSONObject json = new JSONObject(); 
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		
		String flag = null;
		
		String request_type = request.getParameter("request_type");
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
			Date now = new Date();
			String title = new String (request.getParameter("title").getBytes("ISO8859-1"), "UTF8");
			String weibo_content = new String (request.getParameter("content").getBytes("ISO8859-1"), "UTF8");
			java.sql.Date date=new java.sql.Date(new java.util.Date().getTime()); 
			DateFormat timeformat = DateFormat.getTimeInstance();
			String timeStr = timeformat.format(now);      
			Time time = Time.valueOf(timeStr);
			int isDraft = 0;
			boolean result =false;
			user = userService.getUserByUserId(user.getUser_id());
			
			releaseHistory.setUser(user);
			releaseHistory.setDate(date);
			releaseHistory.setTime(time);
			releaseHistory.setTitle(title);
			releaseHistory.setContent(weibo_content);
			releaseHistory.setIsDraft(isDraft);
			
			result = releaseService.saveReleaseSinaWeiboHistory(releaseHistory);
			
			if(result == true){
				String result2 = releaseService.publishWeibo(title, weibo_content);
				if (!result2.equals("200")){
					flag ="publish_sina_failed";
					json.put("flag", flag);
					pw.write(json.toString());
					pw.flush();
					pw.close();
					return "500_error_json";
				}
				flag ="release_success";
				json.put("flag", flag);
				pw.write(json.toString());
				pw.flush();
				pw.close();
				return "200_success_json";
			}
			
			flag ="publish_DBSave_failed";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "500_error_json";
		}else if("web".equals(request_type)){
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
			Date now = new Date();
			User current_user = Utils.currentUser();
			int user_id = current_user.getUser_id();
			String title = new String (request.getParameter("title").getBytes("ISO8859-1"), "UTF8");
			String weibo_content = new String (request.getParameter("content").getBytes("ISO8859-1"), "UTF8");
			java.sql.Date date=new java.sql.Date(new java.util.Date().getTime()); 
			DateFormat timeformat = DateFormat.getTimeInstance();
			String timeStr = timeformat.format(now);      
			Time time = Time.valueOf(timeStr);
			int isDraft = 0;
			boolean result =false;
			user = userService.getUserByUserId(user_id);
			
			releaseHistory.setUser(user);
			releaseHistory.setDate(date);
			releaseHistory.setTime(time);
			releaseHistory.setTitle(title);
			releaseHistory.setContent(weibo_content);
			releaseHistory.setIsDraft(isDraft);
			
			result = releaseService.saveReleaseSinaWeiboHistory(releaseHistory);
			
			if(result == true){
				String result2 = releaseService.publishWeibo(title, weibo_content);
				if (!result2.equals("200")){
					flag ="publish_failed";
					json.put("flag", flag);
					pw.write(json.toString());
					pw.flush();
					pw.close();
					return "500_error_json";
				}
				flag ="release_success";
				json.put("flag", flag);
				pw.write(json.toString());
				pw.flush();
				pw.close();
				return "200_success_json";
			}
			
			flag ="release_failed";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "500_error_json";
		}
		flag ="unknown_request_type";
		json.put("flag", flag);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "unknown_request_type";
		
	}
	
	public String postToConnection() throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		String flag = "";
		
		if (!Utils.isLogin()){
			flag = "not_login";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "401_error_json";
		}
		if (!Utils.currentUserType().equals("admin")){
			flag = "not_admin";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "403_error_json";
		}
		
		String commentId = request.getParameter("comment_id");
		MyComment comment=dao.getMyComment(commentId);
		if(comment == null){
			flag = "comment_not_exist";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "500_error_json";
		}
		String topicTitle = "微博用户提问 (statusId: "+comment.getStatusId()+", commentId: "+comment.getCommentId()+")";
		String topicContent = comment.getCommentText();
		int status=ConnectionsForumTopics.createTopic(topicTitle, topicContent);
		if (status == 200 || status == 201){
			flag = "post_success";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "200_success_json";
		}
		flag = "post_failed";
		json.put("flag", flag);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "500_error_json";
	}
	
	public String releaseConnectionReplyToWeibo() throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		JSONObject json = new JSONObject(); 
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		String flag = null;
		
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
		
		String reply_id = request.getParameter("reply_id");
		ConnectionsReply reply = dao.getConnectionsReply(reply_id);
		if (reply == null){
			flag ="reply_not_found";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "500_error_json";
		}
		ConnectionsTopic topic = dao.getConnectionsTopic(reply.getTopicId());
		if (topic == null){
			flag ="topic_not_found";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "500_error_json";
		}
		String status_id = topic.getStatusId();
		String comment_id = topic.getCommentId();
		String content = new String(request.getParameter("content").getBytes("ISO8859-1"), "UTF8");
		String result = releaseService.replyWeibo(status_id, comment_id, content);
		if (!result.equals("200")){
			flag ="reply_failed";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "500_error_json";
		}
		dao.ignoreReply(reply_id);
		dao.ignoreComment(comment_id);
		flag ="reply_success";
		json.put("flag", flag);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";
	}
	
	public String releaseSinaReply() throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		JSONObject json = new JSONObject(); 
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		String flag = null;
		
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
		
		String status_id = request.getParameter("status_id");
		String comment_id = request.getParameter("comment_id");
		String content = new String(request.getParameter("content").getBytes("ISO8859-1"), "UTF8");
		String result = releaseService.replyWeibo(status_id, comment_id, content);
		if (!result.equals("200")){
			flag ="reply_failed";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "500_error_json";
		}
		dao.ignoreComment(comment_id);
		flag ="reply_success";
		json.put("flag", flag);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";
	}
	
	
	
	
	
	
	
	
	//==========================================
	
	
	
	
/*
	
	public String releaseAtSinaWeiBo(){
		
		
		try {
			ActionContext context=ActionContext.getContext();
			Map<String,Object> session=context.getSession();
			User user=(User)session.get("current_user");
//			
			HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
			String content = request.getParameter("content");
			param = manageService.getAcquireInfoById(1);
			int num = param.getSource_count_num();
			allSource = communicationService.getEnoughNumOfSources(num);
			
			releaseService.writeContentAndSource(content,allSource);
			if (analysisService.calAtKeyWords() && releaseService.getFansList()){
				ArrayList<Fans> recommandFans = releaseService.getRecommandFans();
				JsonConfig cfg = new JsonConfig();
				cfg.setJsonPropertyFilter(new PropertyFilter(){
					public boolean apply(Object source, String name, Object value) {
						if(name.equals("fans")) {
							return true;
						}
						else {
							return false;
						}
					}
				});
				String tmp = JSONArray.fromObject(recommandFans, cfg).toString();
				jsonInputStream = new ByteArrayInputStream(tmp.getBytes("UTF-8"));
			}
			else {
				String tmp = "{\"error\":\"server internal error\"}";
				jsonInputStream = new ByteArrayInputStream(tmp.getBytes("UTF-8"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success_releaseAtSinaWeiBo";
	} 
	 */
}
