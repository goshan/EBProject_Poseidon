package com.ibm.p1.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibm.p1.dao.ManageDao;
import com.ibm.p1.dao.impl.ManageDaoImpl;
import com.ibm.p1.entity.AdminInstitution;
import com.ibm.p1.entity.CurrentToolsStatus;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.User;
import com.ibm.p1.service.ManageService;

public class ManageServiceImpl extends HibernateDaoSupport implements ManageService{
	private Parameter parameter;
	private List<Parameter> allParameters;
	private ManageDao manageDao;
	private AdminInstitution adminInstitution;
	private List<AdminInstitution> allAdminInstitution;
	private CurrentToolsStatus currentToolsStatus;
	private List<CurrentToolsStatus> allCurrentToolsStatuses;
	
	private String toolsName[] = {"AcquireFansAndTopics", "AcquireWeiboReply", "AcquireConnectionReply", "FansPOI", "TotalPOI", "ConnectionKeyWords", "KeyWordsMatch", "RecommandQA", "SourceKeyWords", "ZombieFansFilter"};
	

	public Parameter getParameter() {
		return parameter;
	}
	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}
	public List<Parameter> getAllParameters() {
		return allParameters;
	}
	public void setAllParameters(List<Parameter> allParameters) {
		this.allParameters = allParameters;
	}
	public ManageDao getManageDao() {
		return manageDao;
	}
	public void setManageDao(ManageDao manageDao) {
		this.manageDao = manageDao;
	}
	public AdminInstitution getAdminInstitution() {
		return adminInstitution;
	}
	public void setAdminInstitution(AdminInstitution adminInstitution) {
		this.adminInstitution = adminInstitution;
	}
	public List<AdminInstitution> getAllAdminInstitution() {
		return allAdminInstitution;
	}
	public void setAllAdminInstitution(List<AdminInstitution> allAdminInstitution) {
		this.allAdminInstitution = allAdminInstitution;
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
	public Parameter getAcquireInfoById(int parameter_id) {
		// TODO Auto-generated method stub
		parameter = manageDao.getAcquireInfoById(parameter_id);
		//System.out.println(System.getProperty("user.dir"));
		return parameter;
	}
	public void setAcquireInfo(Parameter parameter){
		// TODO Auto-generated method stub
		manageDao.setAcquireInfo(parameter);
	}

	public List<Parameter> getAllAcquireInfo() {
		// TODO Auto-generated method stub
		allParameters = manageDao.getAllAcquireInfo();
		return allParameters;
	}

	public List<AdminInstitution> getAllInstitution() {
		// TODO Auto-generated method stub
		allAdminInstitution = manageDao.getAllInstitution();
		return allAdminInstitution;
	}

	public AdminInstitution getInstitutionById(int institution_id) {
		// TODO Auto-generated method stub
		adminInstitution = manageDao.getInstitutionById(institution_id);
		return adminInstitution;
	}
	public void updateAdminInstitution(AdminInstitution adminInstitution) {
		// TODO Auto-generated method stub
		manageDao.updateAdminInstitution(adminInstitution);
	}
	public void updateAllAdminInstitution(
			List<AdminInstitution> allAdminInstitution) {
		// TODO Auto-generated method stub
		manageDao.updateAllAdminInstitution(allAdminInstitution);
	}
	
	public List<CurrentToolsStatus> getAllCurrentToolsStatus() {
		// TODO Auto-generated method stub
		return manageDao.getAllCurrentToolsStatus();
	}
	public CurrentToolsStatus getCurrentToolsStatusById(int tools_id) {
		// TODO Auto-generated method stub
		return manageDao.getCurrentToolsStatusById(tools_id);
	}
	public List<CurrentToolsStatus> getCurrentToolsStatusByName(
			String tools_name) {
		// TODO Auto-generated method stub
		return manageDao.getCurrentToolsStatusByName(tools_name);
	}
	public List<CurrentToolsStatus> getAllFreeCurrentToolsStatus() {
		// TODO Auto-generated method stub
		allCurrentToolsStatuses = manageDao.getAllFreeCurrentToolsStatus();
		return allCurrentToolsStatuses;
	}
	public List<CurrentToolsStatus> getAllRunningCurrentToolsStatus() {
		// TODO Auto-generated method stub
		allCurrentToolsStatuses = manageDao.getAllRunningCurrentToolsStatus();
		return allCurrentToolsStatuses;
	}
	public boolean addTools(String tools_name, User user) {
		// TODO Auto-generated method stub
		CurrentToolsStatus myToolsStatus = new CurrentToolsStatus();
		java.sql.Date date=new java.sql.Date(new java.util.Date().getTime()); 
 		Timestamp updateTime = new Timestamp(date.getTime());
 		
		myToolsStatus.setTools_name(tools_name);
		myToolsStatus.setTools_status("free");
		myToolsStatus.setUpdateTime(updateTime);
		myToolsStatus.setUser(user);
		
		return manageDao.addTools(myToolsStatus);
	}
	public boolean changeCurrentToolsStatusToFreeById(int tools_id, User user) {
		// TODO Auto-generated method stub
		currentToolsStatus = manageDao.getCurrentToolsStatusById(tools_id);
		if("free".equals(currentToolsStatus.getTools_status())){
			System.out.println("The tools is free now,can not change to free status!");
			return false;
		}else if("working".equals(currentToolsStatus.getTools_status())){
			manageDao.updateCurrentToolsStatus(tools_id, "free", user);
			return true;
		}
		return false;
	}
	public boolean changeCurrentToolsStatusToWorkingById(int tools_id, User user) {
		// TODO Auto-generated method stub
		currentToolsStatus = manageDao.getCurrentToolsStatusById(tools_id);
		if("working".equals(currentToolsStatus.getTools_status())){
			System.out.println("The tools is working now,can not change to working status!");
			return false;
		}else if("free".equals(currentToolsStatus.getTools_status())){
			manageDao.updateCurrentToolsStatus(tools_id, "working", user);
			return true;
		}
		return false;
	}
	public CurrentToolsStatus getLastToolsStatus() {
		// TODO Auto-generated method stub
		return manageDao.getLastToolsStatus();
	}
	public CurrentToolsStatus getMyLastToolsStatus(User user) {
		// TODO Auto-generated method stub
		return manageDao.getMyLastToolsStatus(user);
	}
	public boolean changeCurrentToolsStatusToFreeByName(String tools_name,User user) {
		// TODO Auto-generated method stub
		currentToolsStatus = manageDao.getLastWorkingToolsStatusOprationByMe(tools_name, user);
		int tools_id = currentToolsStatus.getTools_id();
		if("free".equals(currentToolsStatus.getTools_status())){
			return false;
		}
		return manageDao.updateCurrentToolsStatus(tools_id,"free",user);
	}
	public boolean insertCurrentToolsStatusAndChangeToWorkingByName(String tools_name, User user) {
		// TODO Auto-generated method stub
		return manageDao.insertCurrentToolsStatusAndChangeToWorkingByName(tools_name,user);
	}
	public boolean getWorkingToolsStatusByName(String tools_name) {
		// TODO Auto-generated method stub
		return manageDao.getWorkingToolsStatusByName(tools_name);
	}
	
	
	
	//================== made by goshan =======================//
	
	
	public boolean checkToolsConflict(String mession){
		if (mession.equals(AcquireAndAnalyse)){
			return this.getWorkingToolsStatusByName(this.toolsName[0]) 
			|| this.getWorkingToolsStatusByName(this.toolsName[3]) 
			|| this.getWorkingToolsStatusByName(this.toolsName[4]) 
			|| this.getWorkingToolsStatusByName(this.toolsName[5]) 
			|| this.getWorkingToolsStatusByName(this.toolsName[6]) 
			|| this.getWorkingToolsStatusByName(this.toolsName[9]);
		}
		else if (mession.equals(AcquireWeiboReply)){
			return this.getWorkingToolsStatusByName(this.toolsName[1]);
		}
		else if (mession.equals(AcquireConnectionReply)){
			return this.getWorkingToolsStatusByName(this.toolsName[2]);
		}
		else if (mession.equals(FansAnalyse)){
			return this.getWorkingToolsStatusByName(this.toolsName[3]) 
			|| this.getWorkingToolsStatusByName(this.toolsName[4]) 
			|| this.getWorkingToolsStatusByName(this.toolsName[9]);
		}
		else if (mession.equals(TopicsAnalyse)){
			return this.getWorkingToolsStatusByName(this.toolsName[5]) 
			|| this.getWorkingToolsStatusByName(this.toolsName[6]);
		}
		else if (mession.equals(RecommandQA)){
			return this.getWorkingToolsStatusByName(this.toolsName[7]);
		}
		return false;
	}
	
	public void insertToolsRecord(String mession, User currentUser){
		if (mession.equals(AcquireAndAnalyse)){
			this.insertCurrentToolsStatusAndChangeToWorkingByName(this.toolsName[0], currentUser); 
			this.insertCurrentToolsStatusAndChangeToWorkingByName(this.toolsName[3], currentUser);
			this.insertCurrentToolsStatusAndChangeToWorkingByName(this.toolsName[4], currentUser);
			this.insertCurrentToolsStatusAndChangeToWorkingByName(this.toolsName[5], currentUser);
			this.insertCurrentToolsStatusAndChangeToWorkingByName(this.toolsName[6], currentUser);
			this.insertCurrentToolsStatusAndChangeToWorkingByName(this.toolsName[9], currentUser);
		}
		else if (mession.equals(AcquireWeiboReply)){
			this.insertCurrentToolsStatusAndChangeToWorkingByName(this.toolsName[1], currentUser);
		}
		else if (mession.equals(AcquireConnectionReply)){
			this.insertCurrentToolsStatusAndChangeToWorkingByName(this.toolsName[2], currentUser);
		}
		else if (mession.equals(FansAnalyse)){
			this.insertCurrentToolsStatusAndChangeToWorkingByName(this.toolsName[3], currentUser);
			this.insertCurrentToolsStatusAndChangeToWorkingByName(this.toolsName[4], currentUser);
			this.insertCurrentToolsStatusAndChangeToWorkingByName(this.toolsName[9], currentUser);
		}
		else if (mession.equals(TopicsAnalyse)){
			this.insertCurrentToolsStatusAndChangeToWorkingByName(this.toolsName[5], currentUser);
			this.insertCurrentToolsStatusAndChangeToWorkingByName(this.toolsName[6], currentUser);
		}
		else if (mession.equals(RecommandQA)){
			this.insertCurrentToolsStatusAndChangeToWorkingByName(this.toolsName[7], currentUser);
		}
	}
	
	public void fredToolsRecord(String mession, User currentUser){
		if (mession.equals(AcquireAndAnalyse)){
			this.changeCurrentToolsStatusToFreeByName(this.toolsName[0], currentUser); 
			this.changeCurrentToolsStatusToFreeByName(this.toolsName[3], currentUser);
			this.changeCurrentToolsStatusToFreeByName(this.toolsName[4], currentUser);
			this.changeCurrentToolsStatusToFreeByName(this.toolsName[5], currentUser);
			this.changeCurrentToolsStatusToFreeByName(this.toolsName[6], currentUser);
			this.changeCurrentToolsStatusToFreeByName(this.toolsName[9], currentUser);
		}
		else if (mession.equals(AcquireWeiboReply)){
			this.changeCurrentToolsStatusToFreeByName(this.toolsName[1], currentUser);
		}
		else if (mession.equals(AcquireConnectionReply)){
			this.changeCurrentToolsStatusToFreeByName(this.toolsName[2], currentUser);
		}
		else if (mession.equals(FansAnalyse)){
			this.changeCurrentToolsStatusToFreeByName(this.toolsName[3], currentUser);
			this.changeCurrentToolsStatusToFreeByName(this.toolsName[4], currentUser);
			this.changeCurrentToolsStatusToFreeByName(this.toolsName[9], currentUser);
		}
		else if (mession.equals(TopicsAnalyse)){
			this.changeCurrentToolsStatusToFreeByName(this.toolsName[5], currentUser);
			this.changeCurrentToolsStatusToFreeByName(this.toolsName[6], currentUser);
		}
		else if (mession.equals(RecommandQA)){
			this.changeCurrentToolsStatusToFreeByName(this.toolsName[7], currentUser);
		}
	}
}

