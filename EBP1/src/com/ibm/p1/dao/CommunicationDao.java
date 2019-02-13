package com.ibm.p1.dao;

import java.io.IOException;
import java.util.List;

import com.ibm.p1.entity.Source;

public interface CommunicationDao {
	public void submitSource(Source source);
	
	public Source getSourceById(int id);
	
	public List<Source> getAllSources();
	
	public List<Source> getSourcesYesterdayAndToday();
	
	public List<Source> sourceAnalysis(int num, Source source,String dir) throws IOException;
	
	public Source getLastSource();
	
	public List<Source> getEnoughNumOfSources(int num);
	
	public List<Source> searchSourceByContent(String content);
}
