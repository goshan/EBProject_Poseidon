package com.ibm.p1.service.impl;

import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ibm.p1.entity.AcquireWeiboResult;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.User;
import com.ibm.p1.service.ManageService;
import com.ibm.p1.service.UserService;
import com.opensymphony.xwork2.ActionContext;

public class AcquireFansAndConnData implements Job {
	private User currentUser = new User();
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub

		System.out.println("Quartz 定时任务开始执行！");
		
		Parameter param=(Parameter)context.getJobDetail().getJobDataMap().get("param");
		AcquireServiceImpl acquireServiceImpl = (AcquireServiceImpl)context.getJobDetail().getJobDataMap().get("acquireService");
		AnalysisServiceImpl analysisServiceImpl=(AnalysisServiceImpl)context.getJobDetail().getJobDataMap().get("analysisService");
		ManageService manageService=(ManageService)context.getJobDetail().getJobDataMap().get("manageService");
		User currentUser=(User)context.getJobDetail().getJobDataMap().get("user");
		int acquireNum = param.getAcquireNum();
		int weiboNum = param.getWeiboNum();
		
		System.out.println("weibo start");
		if(!manageService.checkToolsConflict(manageService.AcquireAndAnalyse)){
			manageService.insertToolsRecord(manageService.AcquireAndAnalyse, currentUser);
			analysisServiceImpl.setParam(param);
			AcquireWeiboResult acquireWeiboResult = (AcquireWeiboResult) acquireServiceImpl.getWeiboData(acquireNum, weiboNum);
			analysisServiceImpl.FansPOI();
			System.out.println("weibo end");

			System.out.println("connections start");
			acquireServiceImpl.hackTopicsFromConnections();
			analysisServiceImpl.connectionKeyWords();
			System.out.println("connections end");

			System.out.println("Total Match start");
			analysisServiceImpl.keyWordsMatch();
			System.out.println("Total Match end");
		}
		manageService.fredToolsRecord(manageService.AcquireAndAnalyse, currentUser);

	}
}
