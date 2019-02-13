package com.ibm.p1.service;

import com.opensymphony.xwork2.ActionContext;

public interface AcquireService {

	public Object getWeiboData();
	public Object getWeiboData(int users,int statuses);
	public Object getWeiboData(int statuses);
	public void hackTopicsFromConnections();
	
	public Object getWeiboDataDynamically();
	public Object getConnectionRepliesDynamically();
	public Object getWeiboFansAndConnectionsDataToAnalyse(AnalysisService analysis);
	
	
	public void getWeiboDataNow();
	public void getConnsRepliesNow();
	public void getDataAndAnalyseNow(AnalysisService analysis);
	
	public boolean pauseWeiboData();
	public boolean pauseConnectionReplies();
	public boolean pauseWeiboFansAndConnectionsDataToAnalyse();
	
	public boolean isScheduleMessionOpen(String acquireMession);
	
}
