package com.ibm.p1.dao.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibm.p1.dao.ManageDao;
import com.ibm.p1.entity.AdminInstitution;
import com.ibm.p1.entity.CurrentToolsStatus;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.User;

public class ManageDaoImpl extends HibernateDaoSupport implements ManageDao{
	private Parameter parameter;
	private AdminInstitution adminInstitution;
	private CurrentToolsStatus currentToolsStatus;
	private List<CurrentToolsStatus> allCurrentToolsStatuses;
	
	public Parameter getParameter() {
		return parameter;
	}
	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}
	public AdminInstitution getAdminInstitution() {
		return adminInstitution;
	}
	public void setAdminInstitution(AdminInstitution adminInstitution) {
		this.adminInstitution = adminInstitution;
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
		parameter = getHibernateTemplate().get(Parameter.class, parameter_id);
		return parameter;
	}
	public void setAcquireInfo(Parameter parameter) {

		getHibernateTemplate().update(parameter);
	}
	public List<Parameter> getAllAcquireInfo() {
		// TODO Auto-generated method stub
		List<Parameter> parameters = this.getHibernateTemplate().find("from Parameter");
		return parameters;
	}
	public List<AdminInstitution> getAllInstitution() {
		// TODO Auto-generated method stub
		List<AdminInstitution> adminInstitution = getHibernateTemplate().find("from AdminInstitution");
		return adminInstitution;
	}
	public AdminInstitution getInstitutionById(int institution_id) {
		// TODO Auto-generated method stub
		adminInstitution = getHibernateTemplate().get(AdminInstitution.class, institution_id);
		return adminInstitution;
	}
	public void updateAdminInstitution(AdminInstitution adminInstitution) {
		try {
			// TODO Auto-generated method stub
			getHibernateTemplate().update(adminInstitution);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void updateAllAdminInstitution(
			List<AdminInstitution> allAdminInstitution) {
		// TODO Auto-generated method stub
		int totalSize = allAdminInstitution.size();
		for(int i = 0;i<totalSize;i++){
			adminInstitution = allAdminInstitution.get(i);
			getHibernateTemplate().update(adminInstitution);
		}
	}
	public boolean updateCurrentToolsStatus(int tools_id, String status,User user) {
		// TODO Auto-generated method stub
		currentToolsStatus = getHibernateTemplate().get(CurrentToolsStatus.class, tools_id);
		
		java.sql.Date date=new java.sql.Date(new java.util.Date().getTime()); 
 		Timestamp updateTime = new Timestamp(date.getTime());
 		
		currentToolsStatus.setTools_status(status);
		currentToolsStatus.setUser(user);
		currentToolsStatus.setUpdateTime(updateTime);
		
		getHibernateTemplate().update(currentToolsStatus);
		
		currentToolsStatus = getHibernateTemplate().get(CurrentToolsStatus.class, tools_id);
		boolean result = currentToolsStatus.getTools_status().equals(status);
		return result;
	}
	public List<CurrentToolsStatus> getAllCurrentToolsStatus() {
		// TODO Auto-generated method stub
		allCurrentToolsStatuses = getHibernateTemplate().find("from CurrentToolsStatus");
		return allCurrentToolsStatuses;
	}
	public CurrentToolsStatus getCurrentToolsStatusById(int tools_id) {
		// TODO Auto-generated method stub
		currentToolsStatus = getHibernateTemplate().get(CurrentToolsStatus.class, tools_id);
		return currentToolsStatus;
	}
	public List<CurrentToolsStatus> getCurrentToolsStatusByName(String tools_name) {
		// TODO Auto-generated method stub
		CurrentToolsStatus myToolsStatus = new CurrentToolsStatus();
		myToolsStatus.setTools_name(tools_name);
		String hql = "from CurrentToolsStatus c where c.tools_name =?";
		//allCurrentToolsStatuses = getHibernateTemplate().find(hql, tools_name);
		allCurrentToolsStatuses = getHibernateTemplate().findByExample(myToolsStatus);
		return allCurrentToolsStatuses;
	}
	public List<CurrentToolsStatus> getAllFreeCurrentToolsStatus() {
		// TODO Auto-generated method stub
		CurrentToolsStatus myToolsStatus = new CurrentToolsStatus();
		myToolsStatus.setTools_status("free");
		allCurrentToolsStatuses = getHibernateTemplate().findByExample(myToolsStatus);
		return allCurrentToolsStatuses;
	}
	public List<CurrentToolsStatus> getAllRunningCurrentToolsStatus() {
		// TODO Auto-generated method stub
		CurrentToolsStatus myToolsStatus = new CurrentToolsStatus();
		myToolsStatus.setTools_status("working");
		allCurrentToolsStatuses = getHibernateTemplate().findByExample(myToolsStatus);
		return allCurrentToolsStatuses;
	}
	public boolean addTools(CurrentToolsStatus myToolsStatus) {
		// TODO Auto-generated method stub
		
		getHibernateTemplate().saveOrUpdate(myToolsStatus);
		
		int result = getHibernateTemplate().findByExample(myToolsStatus).size();
		if(result == 1){
			return true;
		}
		return false;
	}
	public CurrentToolsStatus getLastToolsStatus(){
		// TODO Auto-generated method stub
		String sql = "select max(updateTime) from CurrentToolsStatus";
		List<CurrentToolsStatus>  thisCurrentToolsStatus = new ArrayList<CurrentToolsStatus>();
		thisCurrentToolsStatus =  getHibernateTemplate().find(sql);
		String dateStr = thisCurrentToolsStatus.toString().substring(1, thisCurrentToolsStatus.toString().length()-1);
	    Timestamp updateTime = changeToTimestamp(dateStr);
		String hql = "from CurrentToolsStatus c where c.updateTime =?";
		
		allCurrentToolsStatuses = getHibernateTemplate().find(hql,updateTime);
		
		if(allCurrentToolsStatuses.size() == 1){
			currentToolsStatus = allCurrentToolsStatuses.get(0);
		}else{
			currentToolsStatus = null;
		}
		
		return currentToolsStatus;
	}
	public boolean getWorkingToolsStatusByName(String tools_name){
		// TODO Auto-generated method stub
		allCurrentToolsStatuses = getAllRunningCurrentToolsStatus();
		for(int i= 0 ;i < allCurrentToolsStatuses.size();i++){
			 if(tools_name.equals(allCurrentToolsStatuses.get(i).getTools_name())){
				 return true;
			 }
		}
		return false;
	}
	public CurrentToolsStatus getMyLastToolsStatus(User user) {
		// TODO Auto-generated method stub
		
		String sql = "select max(updateTime) from CurrentToolsStatus";
		List<CurrentToolsStatus>  thisCurrentToolsStatus = new ArrayList<CurrentToolsStatus>();
		thisCurrentToolsStatus =  getHibernateTemplate().find(sql);
		String dateStr = thisCurrentToolsStatus.toString().substring(1, thisCurrentToolsStatus.toString().length()-1);
	    Timestamp updateTime = changeToTimestamp(dateStr);
	    
		String hql = "from CurrentToolsStatus c where c.updateTime = '"+updateTime+"' and c.user =?";
		  
		allCurrentToolsStatuses = getHibernateTemplate().find(hql,user);
		if(allCurrentToolsStatuses.size() == 1){
			currentToolsStatus = allCurrentToolsStatuses.get(0);
		}else{
			currentToolsStatus = null;
		}
		
		return currentToolsStatus;
	}
	public boolean insertCurrentToolsStatusAndChangeToWorkingByName(
			String tools_name, User user) {
		// TODO Auto-generated method stub
		CurrentToolsStatus myToolsStatus = new CurrentToolsStatus();
		java.sql.Date date=new java.sql.Date(new java.util.Date().getTime()); 
 		Timestamp updateTime = new Timestamp(date.getTime());
 		boolean result = getWorkingToolsStatusByName(tools_name);
 		if(result == true){
 			System.out.println("过程在执行，未再启动！");
 			return false;
 		}else{
 			myToolsStatus.setTools_name(tools_name);
 			myToolsStatus.setTools_status("working");
 			myToolsStatus.setUpdateTime(updateTime);
 			myToolsStatus.setUser(user);
		
 			getHibernateTemplate().save(myToolsStatus);
 			System.out.println("过程插入成功，成功启动！");
 			return true;
 		}
	}
	public CurrentToolsStatus getLastWorkingToolsStatusOprationByMe(
			String tools_name, User user) {
		// TODO Auto-generated method stub
		CurrentToolsStatus myToolsStatus = new CurrentToolsStatus();
		myToolsStatus.setTools_name(tools_name);
		myToolsStatus.setTools_status("working");
		myToolsStatus.setUser(user);
		
		allCurrentToolsStatuses =  getHibernateTemplate().findByExample(myToolsStatus);
		int totalSize = allCurrentToolsStatuses.size();
		if(totalSize == 1){
			currentToolsStatus = allCurrentToolsStatuses.get(0);
			return currentToolsStatus;
		}else if(totalSize > 1){
			Timestamp updateTime = null;
			int maxIndex = 0;
			for(int i = 0 ;i<totalSize;i++){
				currentToolsStatus = allCurrentToolsStatuses.get(i);
				if(updateTime == null|| updateTime.before(currentToolsStatus.getUpdateTime())){
					updateTime = currentToolsStatus.getUpdateTime();
					maxIndex = i;
				}
			}
			currentToolsStatus = allCurrentToolsStatuses.get(maxIndex);
			return currentToolsStatus;
		}
		return null;
	}

	public Timestamp changeToTimestamp(String str){
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date date = new java.util.Date(new java.util.Date().getTime());
		try {
			date = sim.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp updateTime = new Timestamp(date.getTime());
		return updateTime;
	}
}
