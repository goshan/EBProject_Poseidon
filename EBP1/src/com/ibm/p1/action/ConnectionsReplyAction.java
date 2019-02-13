package com.ibm.p1.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ibm.p1.dao.AcquireDao;
import com.ibm.p1.entity.ConnectionsReply;
import com.ibm.p1.entity.ConnectionsTopic;
import com.ibm.p1.tools.ConnectionsForumTopics;
import com.opensymphony.xwork2.ActionContext;

public class ConnectionsReplyAction{
	
	private List<ConnectionsReply> myReplies;
	private List<ConnectionsTopic> myTopics;
	private AcquireDao dao;
	
	public AcquireDao getDao() {
		return dao;
	}
	public void setDao(AcquireDao dao) {
		this.dao = dao;
	}
	public List<ConnectionsReply> getMyReplies() {
		return myReplies;
	}
	public void setMyReplies(List<ConnectionsReply> myReplies) {
		this.myReplies = myReplies;
	}
	public List<ConnectionsTopic> getMyTopics() {
		return myTopics;
	}
	public void setMyTopics(List<ConnectionsTopic> myTopics) {
		this.myTopics = myTopics;
	}
	public String executeReply(){
		myReplies= dao.getAllConnectionsRepliesNotIgnored();
		myTopics=dao.getConnectionsTopics();
		return "success_reply";
	}
	public String getReplies() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		
		List<ConnectionsReply> replies=dao.getAllConnectionsRepliesNotIgnored();
		json.put("replies", JSONArray.fromObject(replies));
		
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";
		
	}
	public String getTopics() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		
		List<ConnectionsTopic> topics=dao.getConnectionsTopics();
		json.put("topics", JSONArray.fromObject(topics));
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";
		
	}
	public String displayDetailsOfTopic() throws IOException{
		
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		String flag = "";
		
		String topicId = request.getParameter("topicId");
		
		ConnectionsTopic topic=dao.getConnectionsTopic(topicId);
		json.put("topic", JSONObject.fromObject(topic));
		
		//json.put("all_comments", JSONArray.fromObject(allComments));
		
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";
		
	}
	public String displayDetailsOfReplies() throws IOException{
		
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		String flag = "";
		
		String topicId = request.getParameter("topicId");
		
		List<ConnectionsReply> replies=dao.getAllConnectionsRepliesNotIgnored();
		json.put("replies", JSONArray.fromObject(replies));
		
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";
		
	}
	
	public String ignoreReply() throws IOException{
		
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		
		String replyId = request.getParameter("replyId");
		
		
		if(dao.ignoreReply(replyId)){
			json.put("result", "success");
		}
		else{
			json.put("result", "failure");
		}
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";
	}
	
	public String publishReply() throws IOException{
		
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		
		String replyText = request.getParameter("text");
		System.out.println(URLDecoder.decode(replyText,"utf-8"));
		//模拟发布成功
		json.put("result", "success");
	
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";
	}
	public String removeTopic() throws IOException{
		
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		
		String topicId = request.getParameter("topicId");
		System.out.println("topicId:"+topicId);
		
		dao.removeTopic(topicId);
		
		//模拟删除主题成功
		json.put("result", "success");
	
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";
	}
	
	public String createTopic() throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		JSONObject json = new JSONObject(); 
		
		String topicTitle =URLDecoder.decode(request.getParameter("title"),"utf-8") ;
		String topicContent=URLDecoder.decode(request.getParameter("content"),"utf-8");
		
		System.out.println("topicTitle:"+URLDecoder.decode(topicTitle,"utf-8"));
		System.out.println("topicContent:"+URLDecoder.decode(topicContent,"utf-8"));
		
		int status=ConnectionsForumTopics.createTopic(topicTitle, topicContent);
		System.out.println("status:"+status);
		
		//模拟创建主题成功
		if(status == 200 || status == 201){
			json.put("result", "success");
		}
		else{
			json.put("result", "failure");
		}
	
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";
		
	}
	
}
