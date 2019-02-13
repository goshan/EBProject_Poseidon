package com.ibm.p1.service.impl;

import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.protocol.client.AbderaClient;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;

import com.ibm.p1.entity.Connection;
import com.ibm.p1.entity.ReleaseHistory;
import com.ibm.p1.entity.SearchResult;
import com.ibm.p1.entity.Source;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.User;
import com.ibm.p1.service.SearchService;
import com.ibm.p1.tools.HibernateUtil;
import com.ibm.p1.dao.SearchDao;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class SearchServiceImpl implements SearchService{

	private SearchDao searchDao;
	private List<SearchResult> allSearchResult = new ArrayList<SearchResult>();
	
	public SearchDao getSearchDao() {
		return searchDao;
	}

	public void setSearchDao(SearchDao searchDao) {
		this.searchDao = searchDao;
	}

	public List<SearchResult> getAllSearchResult() {
		return allSearchResult;
	}

	public void setAllSearchResult(List<SearchResult> allSearchResult) {
		this.allSearchResult = allSearchResult;
	}

	public List<Source> searchSourceByContent(String content) {
		// TODO Auto-generated method stub
		return searchDao.searchSourceByContent(content);
	}

	public List<SearchResult> searchConnectionByContent(String content) {
		// TODO Auto-generated method stub
		System.out.println("begin connection searching");
		String connectionHostUrl = "https://w3-connections.ibm.com";
		String url = connectionHostUrl+"/search/atom/search/results?query=\""+content+"\"";
		
		try{
			URLConnection feedUrl = new URL(url).openConnection();
			feedUrl.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; http://itindex.net)");
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(feedUrl));
			SyndContent listForTitle = feed.getTitleEx();
			List listForAuthor = feed.getAuthors();
			List<SyndEntry> list = feed.getEntries();
			allSearchResult.clear();
			
			for(int i = 0 ;i <list.size();i++){
				SearchResult searchResult = new SearchResult();
				searchResult.setResultLink(list.get(i).getLink().replace(content, "<span class='search_result_highlight'>"+content+"</span>"));
				searchResult.setResultTitle(list.get(i).getTitle().replace(content, "<span class='search_result_highlight'>"+content+"</span>"));
				searchResult.setResultContent(list.get(i).getContents().toString());
				searchResult.setResultUrl(list.get(i).getUri());
				searchResult.setResultAuthor(list.get(i).getAuthor().replace(content, "<span class='search_result_highlight'>"+content+"</span>"));
				searchResult.setResultUpdated(list.get(i).getUpdatedDate().toString());
				searchResult.setResultDescription(list.get(i).getDescription() == null ? "" : list.get(i).getDescription().getValue().replace(content, "<span class='search_result_highlight'>"+content+"</span>"));
				
				allSearchResult.add(searchResult);
			}
		} catch (Exception e) {
			System.out.println("Error in Connection searching");
			e.printStackTrace();
			return null;
		}
		
		System.out.println("finished connection search");
		return allSearchResult;
	}

	public List<User> searchUserByUsername(String username) {
		// TODO Auto-generated method stub
		return searchDao.searchUserByUsername(username);
	}

	public List<ReleaseHistory> searchReleaseHistoryByContent(String content) {
		// TODO Auto-generated method stub
		return searchDao.searchReleaseHistoryByContent(content);
	}

	public List<ReleaseHistory> searchReleaseHistoryByDate(String startDate,String endDate) {
		// TODO Auto-generated method stub
		return searchDao.searchReleaseHistoryByDate(startDate, endDate);
	}

	public List<ReleaseHistory> searchReleaseHistoryByContentAndDate(
			String content, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return searchDao.searchReleaseHistoryByContentAndDate(content, startDate, endDate);
	}
	
	public ReleaseHistory searchReleaseHistoryById(String id){
		return searchDao.searchReleaseHistoryById(id);
	}

}
