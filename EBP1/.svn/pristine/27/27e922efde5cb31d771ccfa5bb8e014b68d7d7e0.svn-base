package com.ibm.p1.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ibm.p1.dao.AcquireDao;
import com.ibm.p1.entity.ConnectionsReply;
import com.ibm.p1.entity.ConnectionsTopic;
import com.ibm.p1.entity.MyComment;
import com.ibm.p1.entity.MyStatus;
import com.ibm.p1.entity.User;
import com.ibm.p1.service.AnalysisService;
import com.ibm.p1.service.ManageService;
import com.ibm.p1.tools.ConnectionsForumTopics;
import com.ibm.p1.tools.Utils;
import com.opensymphony.xwork2.ActionContext;

public class ReplyAction {
	private AcquireDao dao;
	private AnalysisService analysisService;
	private ManageService manageService;
	private List<MyComment> myComments;
	private List<ConnectionsReply> myReplies;

	public AcquireDao getDao() {
		return dao;
	}
	public void setDao(AcquireDao dao) {
		this.dao = dao;
	}
	public AnalysisService getAnalysisService() {
		return analysisService;
	}
	public void setAnalysisService(AnalysisService analysisService) {
		this.analysisService = analysisService;
	}
	public ManageService getManageService() {
		return manageService;
	}
	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}
	public List<MyComment> getMyComments() {
		return myComments;
	}
	public void setMyComments(List<MyComment> myComments) {
		this.myComments = myComments;
	}
	public List<ConnectionsReply> getMyReplies() {
		return myReplies;
	}
	public void setMyReplies(List<ConnectionsReply> myReplies) {
		this.myReplies = myReplies;
	}
	
	
	public String getWeiboReply() {
		if (!Utils.isLogin()){
			return "401_page";
		}
		else if (!Utils.currentUserType().equals("admin")){
			return "403_page";
		}
		myComments = dao.getAllMyCommentsNotIgnored();
		return "success_getWeiboReply";
	}
	
	public String getConnectionReply(){
		if (!Utils.isLogin()){
			return "401_page";
		}
		else if (!Utils.currentUserType().equals("admin")){
			return "403_page";
		}
		myReplies= dao.getAllConnectionsRepliesNotIgnored();
		return "success_getConnectionReply";
	}
	
	public String makeWeiboCommentRead() throws IOException{
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
		dao.ignoreComment(commentId);
		flag = "make_read_success";
		json.put("flag", flag);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";
	}
	
	public String makeConnectionReplyRead() throws IOException{
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
		
		String replyId = request.getParameter("reply_id");
		dao.ignoreReply(replyId);
		flag = "make_read_success";
		json.put("flag", flag);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";
	}
	
	public String showWeiboCommentDetails() throws IOException{
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
		
		MyStatus status=dao.getMyStatus(comment.getStatusId());
		if (status == null){
			flag = "status_not_exist";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "500_error_json";
		}
		
		List<MyComment> allComments = dao.getAllMyComments(comment.getStatusId());
		flag = "show_detail_success";
		json.put("flag", flag);
		json.put("status", JSONObject.fromObject(status));
		json.put("all_comments", JSONArray.fromObject(allComments));
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";
	}
	
	public String showConnectionReplyDetails() throws IOException{
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
		
		String replyId = request.getParameter("reply_id");
		ConnectionsReply reply = dao.getConnectionsReply(replyId);
		if (reply == null){
			flag = "reply_not_found";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "500_error_json";
		}
		ConnectionsTopic topic = dao.getConnectionsTopic(reply.getTopicId());
		if (topic == null){
			flag = "topic_not_found";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "500_error_json";
		}
		List<ConnectionsReply> replies = dao.getAllConnectionsReplies(topic.getTopicId());
		flag = "show_detail_success";
		json.put("flag", flag);
		json.put("topic", JSONObject.fromObject(topic));
		json.put("replies", JSONArray.fromObject(replies));
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";	
	}
	
	public String recommandReply() throws IOException{
		ActionContext context=ActionContext.getContext();
		Map<String,Object> session=context.getSession();
		User user=(User)session.get("current_user");
		
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
		
		String question = request.getParameter("question");
		if (question == null || question.equals("")){
			flag = "empty_question";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "500_error_json";
		}
		
		try {
			question = new String(question.getBytes("ISO-8859-1"),"UTF-8");
			if (manageService.checkToolsConflict(manageService.RecommandQA)){
				flag = "duplicate_running";
				json.put("flag", flag);
				pw.write(json.toString());
				pw.flush();
				pw.close();
				return "500_error_json";
			}
			User currentUser = Utils.currentUser();
			manageService.insertToolsRecord(manageService.RecommandQA, currentUser);
			boolean result = analysisService.RecommandQA(question);
			manageService.fredToolsRecord(manageService.RecommandQA, currentUser);
			if (!result){
				flag = "recommand_failed";
				json.put("flag", flag);
				pw.write(json.toString());
				pw.flush();
				pw.close();
				return "500_error_json";
			}
			
			Map res = analysisService.getRecommandResult();
			flag = "recommand_success";
			json.put("flag", flag);
			json.put("replies", JSONObject.fromObject(res));
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "200_success_json";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flag = "encode_error";
		json.put("flag", flag);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "500_error_json";
	}
}
