package com.ibm.p1.service;

import java.util.List;

import com.ibm.p1.entity.AdminInstitution;
import com.ibm.p1.entity.CurrentToolsStatus;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.User;


public interface ManageService {
	
	static public String AcquireAndAnalyse = "AcquireAndAnalyse";
	static public String AcquireWeiboReply = "AcquireWeiboReply";
	static public String AcquireConnectionReply = "AcquireConnectionReply";
	static public String FansAnalyse = "FansAnalyse";
	static public String TopicsAnalyse = "TopicsAnalyse";
	static public String SourceAnalyse = "SourceAnalyse";
	static public String RecommandQA = "RecommandQA";
	static public String AtFans = "AtFans";
	
	
	public Parameter getAcquireInfoById(int parameter_id);
	
	public void setAcquireInfo(Parameter parameter);
	
	public List<Parameter> getAllAcquireInfo();
	
	public List<AdminInstitution> getAllInstitution();
	
	public AdminInstitution getInstitutionById(int institution_id);
	
	public void updateAdminInstitution(AdminInstitution adminInstitution);
	
	public void updateAllAdminInstitution(List<AdminInstitution> allAdminInstitution);
	
	public boolean changeCurrentToolsStatusToFreeById(int tools_id,User user);
	
	public boolean changeCurrentToolsStatusToWorkingById(int tools_id,User user);
	
	public boolean changeCurrentToolsStatusToFreeByName(String tools_name,User user);
	
	public boolean insertCurrentToolsStatusAndChangeToWorkingByName(String tools_name,User user);
	
	public List<CurrentToolsStatus> getAllCurrentToolsStatus();
	
	public CurrentToolsStatus getCurrentToolsStatusById(int tools_id);
	
	public List<CurrentToolsStatus> getCurrentToolsStatusByName(String tools_name);
	
	public List<CurrentToolsStatus> getAllFreeCurrentToolsStatus();
	
	public List<CurrentToolsStatus> getAllRunningCurrentToolsStatus();
	
	public boolean addTools(String tools_name,User user);
	
	public CurrentToolsStatus getLastToolsStatus();
	
	public CurrentToolsStatus getMyLastToolsStatus(User user);
	
	public boolean getWorkingToolsStatusByName(String tools_name);
	
	public boolean checkToolsConflict(String mession);
	
	public void insertToolsRecord(String mession, User currentUser);
	
	public void fredToolsRecord(String mession, User currentUser);
}
