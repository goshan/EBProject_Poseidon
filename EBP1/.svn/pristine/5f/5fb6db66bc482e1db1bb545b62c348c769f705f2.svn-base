package com.ibm.p1.dao;

import java.util.List;

import com.ibm.p1.entity.AdminInstitution;
import com.ibm.p1.entity.CurrentToolsStatus;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.User;

public interface ManageDao {
	public Parameter getAcquireInfoById(int parameter_id);
	
	public void setAcquireInfo(Parameter parameter);
	
	public List<Parameter> getAllAcquireInfo();
	
	public List<AdminInstitution> getAllInstitution();
	
	public AdminInstitution getInstitutionById(int institution_id);
	
	public void updateAdminInstitution(AdminInstitution adminInstitution);
	
	public void updateAllAdminInstitution(List<AdminInstitution> allAdminInstitution);
	
	public boolean updateCurrentToolsStatus(int tools_id,String status,User user);
	
	public boolean insertCurrentToolsStatusAndChangeToWorkingByName(String tools_name, User user);
	
	public CurrentToolsStatus getLastWorkingToolsStatusOprationByMe(String tools_name,User user);
	
	public CurrentToolsStatus getLastToolsStatus();
	
	public CurrentToolsStatus getMyLastToolsStatus(User user);
	
	public List<CurrentToolsStatus> getAllCurrentToolsStatus();
	
	public CurrentToolsStatus getCurrentToolsStatusById(int tools_id);
	
	public List<CurrentToolsStatus> getCurrentToolsStatusByName(String tools_name);
	
	public List<CurrentToolsStatus> getAllFreeCurrentToolsStatus();
	
	public List<CurrentToolsStatus> getAllRunningCurrentToolsStatus();
	
	public boolean addTools(CurrentToolsStatus currentToolsStatus);
	
	public boolean getWorkingToolsStatusByName(String tools_name);
}
