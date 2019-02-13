package com.ibm.p1.service;

import java.util.Map;

import com.ibm.p1.entity.User;

public interface AnalysisService {
	
	public boolean FansPOI();
	
	public boolean connectionKeyWords();
	
	public boolean keyWordsMatch();
	
	public boolean RecommandQA(String question);
	
	public Map getRecommandResult();
	
	public boolean SourceKeyWords();
	
	public boolean calAtKeyWords();
}
