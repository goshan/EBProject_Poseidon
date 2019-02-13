package com.ibm.p1.service;

import java.util.List;
import com.ibm.p1.entity.Source;

public interface CommunicationService {
	public void submitSource(Source source);
	
	public Source getSourceById(int id );
	
	public List<Source> getAllSources();
	
	public List<Source> getSourcesYesterdayAndToday();
	
	public void sourceAnalysis(int num, Source source,String dir);
	
	public Source getLastSource();
	
	public List<Source> getEnoughNumOfSources(int num);
	
	public List<Source> searchSourceByContent(String content);
}
