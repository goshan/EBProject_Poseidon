package com.ibm.p1.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.ibm.p1.entity.AdminInstitution;
import com.ibm.p1.entity.CurrentToolsStatus;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.User;
import com.ibm.p1.service.ManageService;
import com.ibm.p1.service.AcquireService;
import com.ibm.p1.service.UserService;
import com.ibm.p1.tools.TimerTaskOfSwitchAdmin;
import com.ibm.p1.tools.Utils;
import com.opensymphony.xwork2.ActionContext;

public class ManageAction {
	private Parameter parameter;
	private AdminInstitution adminInstitution;
	private ManageService manageService;
	private UserService userService;
	private User user;
	private List<Parameter> allParameters;
	private AcquireService acquireService;
	private List<AdminInstitution> allAdminInstitution;
	private List<User> allUsers;
	private CurrentToolsStatus currentToolsStatus;
	private List<CurrentToolsStatus> allCurrentToolsStatuses;
	
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public AdminInstitution getAdminInstitution() {
		return adminInstitution;
	}
	public void setAdminInstitution(AdminInstitution adminInstitution) {
		this.adminInstitution = adminInstitution;
	}
	public Parameter getParameter() {
		return parameter;
	}
	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}
	public ManageService getManageService() {
		return manageService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setManageService(ManageService manageService) {
		this.manageService = manageService;
	}
	public List<Parameter> getAllParameters() {
		return allParameters;
	}
	public void setAllParameters(List<Parameter> allParameters) {
		this.allParameters = allParameters;
	}
	public AcquireService getAcquireService() {
		return acquireService;
	}
	public void setAcquireService(AcquireService acquireService) {
		this.acquireService = acquireService;
	}
	public List<AdminInstitution> getAllAdminInstitution() {
		return allAdminInstitution;
	}
	public void setAllAdminInstitution(List<AdminInstitution> allAdminInstitution) {
		this.allAdminInstitution = allAdminInstitution;
	}
	public List<User> getAllUsers() {
		return allUsers;
	}
	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}
	public CurrentToolsStatus getCurrentToolsStatus() {
		return currentToolsStatus;
	}
	public void setCurrentToolsStatus(CurrentToolsStatus currentToolsStatus) {
		this.currentToolsStatus = currentToolsStatus;
	}
	public List<CurrentToolsStatus> getAllCurrentToolsStatuses() {
		return allCurrentToolsStatuses;
	}
	public void setAllCurrentToolsStatuses(
			List<CurrentToolsStatus> allCurrentToolsStatuses) {
		this.allCurrentToolsStatuses = allCurrentToolsStatuses;
	}
	
	public String showOptionPage(){
		if (!Utils.isLogin()){
			return "401_page";
		}
		else if (!Utils.currentUserType().equals("admin")){
			return "403_page";
		}
		return "success_showOptionPage";
	}
	
	public String getAllAcquireInfo(){
		if (!Utils.isLogin()){
			return "401_page";
		}
		else if (!Utils.currentUserType().equals("admin")){
			return "403_page";
		}
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		allParameters = manageService.getAllAcquireInfo();
		request.setAttribute("parameters", allParameters.get(0));
		return "success_getAllAcquireInfo";	
	}
	
	public String setAcquireInfo() throws IOException{
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		JSONObject json = new JSONObject(); 
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw  = response.getWriter();
		String flag = "";
		
		if (!Utils.isLogin()){
			flag = "not_login";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "401_error_json";
		}

		User current_user = Utils.currentUser();
		if (!current_user.getType().equals("admin")){
			flag = "not_admin";
			json.put("flag", flag);
			pw.write(json.toString());
			pw.flush();
			pw.close();
			return "403_error_json";
		}
		
		int acquireTimer = Integer.parseInt(request.getParameter("acquire_timer"));
		int acquireNum = Integer.parseInt(request.getParameter("acquire_num"));
		int weiboNum = Integer.parseInt(request.getParameter("weibo_num"));
		String cacheDir = request.getParameter("cache_dir");
		String toolsDir = request.getParameter("tools_dir");
		int chineseFilter = Integer.parseInt(request.getParameter("chinese_filter"));
		int communitiesNum = Integer.parseInt(request.getParameter("communities_num"));
		String accessToken = request.getParameter("access_token");
		String weiboAccount = new String(request.getParameter("weibo_account").getBytes("ISO8859-1"),"UTF8");;
		int sourceCountNum = Integer.parseInt(request.getParameter("source_count_num"));
		String communityUuid = request.getParameter("community_uuid");
		String connectionAccount = new String(request.getParameter("connection_account").getBytes("ISO8859-1"),"UTF8");;
		String connectionPwd = request.getParameter("connection_password");
		String connectionForumUuid = request.getParameter("connection_forum_uuid");
		
		parameter = manageService.getAcquireInfoById(1);
		
		parameter.setAcquireTimer(acquireTimer);
		parameter.setAcquireNum(acquireNum);
		parameter.setWeiboNum(weiboNum);
		parameter.setCache_dir(cacheDir);
		parameter.setTools_dir(toolsDir);
		parameter.setChineseFilter(chineseFilter);
		parameter.setCommunitiesNum(communitiesNum);
		parameter.setAccess_token(accessToken);
		parameter.setWeibo_account(weiboAccount);
		parameter.setSource_count_num(sourceCountNum);
		parameter.setCommunity_uuid(communityUuid);
		parameter.setConnectionsAccount(connectionAccount);
		parameter.setConnectionsPwd(connectionPwd);
		parameter.setConnectionsForumUuid(connectionForumUuid);
		
		manageService.setAcquireInfo(parameter);
		
		flag = "set_param_success";
		json.put("flag", flag);
		pw.write(json.toString());
		pw.flush();
		pw.close();
		return "200_success_json";
	}
	
	public String getAllToolsStatus(){
		if (!Utils.isLogin()){
			return "401_page";
		}
		else if (!Utils.currentUserType().equals("admin")){
			return "403_page";
		}
		
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		
		HashMap<String, Boolean> res = new HashMap<String, Boolean>();
		res.put("AcquireAndAnalyseOpen", acquireService.isScheduleMessionOpen(manageService.AcquireAndAnalyse));
		res.put("WeiboReplyOpen", acquireService.isScheduleMessionOpen(manageService.AcquireWeiboReply));
		res.put("ConnectionReplyOpen", acquireService.isScheduleMessionOpen(manageService.AcquireConnectionReply));
		
		res.put("AcquireAndAnalyseRunning", manageService.checkToolsConflict(manageService.AcquireAndAnalyse));
		res.put("WeiboReplyRunning", manageService.checkToolsConflict(manageService.AcquireWeiboReply));
		res.put("ConnectionReplyRunning", manageService.checkToolsConflict(manageService.AcquireConnectionReply));
		res.put("FansAnalyseRuning", manageService.checkToolsConflict(manageService.FansAnalyse));
		res.put("TopicsAnalyseRunning", manageService.checkToolsConflict(manageService.TopicsAnalyse));
		
		request.setAttribute("status", res);
		
		return "success_getAllToolsStatus";
	}
	
	
	
	
	
	//================================
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getAcquireInfoById(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String parameter_idTemp = request.getParameter("parameter_id");
		System.out.println("parameter_idTemp"+parameter_idTemp);
		int parameter_id = Integer.parseInt(parameter_idTemp);
		
		parameter = manageService.getAcquireInfoById(parameter_id);
		
		return "success_getAcquireInfoById";
	}
	
	public String getAdminTransInstitution(){
		allAdminInstitution = manageService.getAllInstitution();
		return "success_getAdminTransInstitution";
	}
	public String editAdminTransInstitution(){
		allAdminInstitution = manageService.getAllInstitution();
		allUsers = userService.getAllUsers();
		return "success_editAdminTransInstitution";
	}
	public String setAdminTransInstitution(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		allAdminInstitution = manageService.getAllInstitution();
		int totalSize = allAdminInstitution.size();
		for(int i= 0;i<totalSize;){
			adminInstitution = allAdminInstitution.get(i);
			i=i+1;
			String focal = request.getParameter(i+".focal");
			String newName = request.getParameter(i+".username");
			adminInstitution.setFocal(focal);
			if(newName != null && newName != ""){
				if(!newName.equals(focal)){
					adminInstitution.setFocal(newName);
				}
			}
		}
		manageService.updateAllAdminInstitution(allAdminInstitution);
		return "success_setAdminTransInstitution";
	}
	public String setAdminTransInstitutionById(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String institution_idTemp = request.getParameter("adminInstitution.institution_id");
		int institution_id= Integer.parseInt(institution_idTemp);
		adminInstitution = manageService.getInstitutionById(institution_id);
		
		String focal = request.getParameter("adminInstitution.focal");
		String month = request.getParameter("adminInstitution.month");
		String username = request.getParameter("user.username");
		adminInstitution.setFocal(focal);
		if(username != null){
			if(!username.equals(focal)){
				adminInstitution.setFocal(username);
			}
		}
		adminInstitution.setMonth(month);
		
		manageService.updateAdminInstitution(adminInstitution);
		
		return "success_setAdminTransInstitutionById";
	}
	public String editAdminTransInstitutionById(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String institution_idTemp = request.getParameter("institution_id");
		int institution_id= Integer.parseInt(institution_idTemp);
		adminInstitution = manageService.getInstitutionById(institution_id);
		allUsers = userService.getAllUsers();
		return "success_editAdminTransInstitutionById";
	}
	public String switchAdmin(){
		Date now = new Date();
		java.sql.Date date=new java.sql.Date(new java.util.Date().getTime()); 
		System.out.println("date日期时间："+date);
		Timer timer = new Timer();
		TimerTask task = new TimerTaskOfSwitchAdmin();
		timer.schedule(task, date, PERIOD_DAY);

		return "success_switchAdmin";
	}
	public Date addDay(Date date, int num) {

		  Calendar startDT = Calendar.getInstance();
		  startDT.setTime(date);
		  startDT.add(Calendar.DAY_OF_MONTH, num);
		  return (Date) startDT.getTime();

		 }
	
	/*for test////////////////////
	以下方法为测试使用
	////////////////////////////////////
	*/
	
	public String getMyLastToolsStatus(){
		Map<String,Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("current_user");
		currentToolsStatus =manageService.getMyLastToolsStatus(user);
		
		return "success_getMyLastToolsStatus";
	}
	public String getLastToolsStatus(){
		currentToolsStatus =manageService.getLastToolsStatus();
		return "success_getLastToolsStatus";
	}
	/*
	public String changeCurrentToolsStatusToFreeById(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String tools_idTemp = request.getParameter("tools_id");
		int tools_id =Integer.parseInt(tools_idTemp);
		Map<String,Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("current_user");
		
		boolean result = manageService.changeCurrentToolsStatusToFreeById(tools_id, user);
		if(result){
			return "success_changeCurrentToolsStatusToFree";
		}
		return "fail_changeCurrentToolsStatusToFreeById";
		
	}
	
	public String changeCurrentToolsStatusToWorkingById(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String tools_idTemp = request.getParameter("tools_id");
		int tools_id =Integer.parseInt(tools_idTemp);
		Map<String,Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("current_user");
		
		boolean result = manageService.changeCurrentToolsStatusToWorkingById(tools_id, user);
		if(result){
			return "success_changeCurrentToolsStatusToWorkingById";
		}
		return "fail_changeCurrentToolsStatusToWorking";
	}
	public String changeCurrentToolsStatusToFreeByName(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String tools_name = request.getParameter("tools_name");
		Map<String,Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("current_user");
		boolean result = manageService.changeCurrentToolsStatusToFreeByName(tools_name, user);
		if(result){
			return "success_changeCurrentToolsStatusToFreeByName";
		}
		return "fail_changeCurrentToolsStatusToFreeByName";
	}
	public String insertCurrentToolsStatusAndChangeToWorkingByName(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String tools_name = request.getParameter("tools_name");
		Map<String,Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("current_user");
		System.out.println("into insertCurrentToolsStatusAndChangeToWorkingByName,在这里");
		boolean result = manageService.insertCurrentToolsStatusAndChangeToWorkingByName(tools_name, user);
		if(result){
			return "success_insertCurrentToolsStatusAndChangeToWorkingByName";
		}
		return "fail_insertCurrentToolsStatusAndChangeToWorkingByName";
	}
	*/
	public String getAllCurrentToolsStatus(){
		allCurrentToolsStatuses = manageService.getAllCurrentToolsStatus();
		return "success_getAllCurrentToolsStatus";
	}
	
	public String getCurrentToolsStatusById(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String tools_idTemp = request.getParameter("tools_id");
		int tools_id =Integer.parseInt(tools_idTemp);
		
		currentToolsStatus = manageService.getCurrentToolsStatusById(tools_id);
		
		return "success_getCurrentToolsStatusById";
	}
	
	public String getCurrentToolsStatusByName(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String tools_name = request.getParameter("tools_name");
		
		allCurrentToolsStatuses = manageService.getCurrentToolsStatusByName(tools_name);
		
		return "success_getCurrentToolsStatusByName";
	}
	
	public String getAllFreeCurrentToolsStatus(){

		allCurrentToolsStatuses = manageService.getAllFreeCurrentToolsStatus();
		return "success_getAllFreeCurrentToolsStatus";
	}
	
	public String getAllRunningCurrentToolsStatus(){
		allCurrentToolsStatuses = manageService.getAllRunningCurrentToolsStatus();
		return "success_getAllRunningCurrentToolsStatus";
	}
	/*
	public String addTools(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		String tools_name = request.getParameter("tools_name");
		Map<String,Object> session = ActionContext.getContext().getSession();
		user = (User) session.get("current_user");
		
		manageService.addTools(tools_name, user);
		
		return "success_addTools";
	}
	*/
}
