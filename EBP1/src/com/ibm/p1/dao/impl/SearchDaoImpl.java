package com.ibm.p1.dao.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibm.p1.dao.SearchDao;
import com.ibm.p1.entity.Connection;
import com.ibm.p1.entity.ReleaseHistory;
import com.ibm.p1.entity.Source;
import com.ibm.p1.entity.User;

public class SearchDaoImpl extends HibernateDaoSupport implements SearchDao{
	private Source source;
	private List<Source> allSource;
	private List<User> allUsers;
	private List<ReleaseHistory> allReleaseHistorys;
	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public List<Source> getAllSource() {
		return allSource;
	}

	public void setAllSource(List<Source> allSource) {
		this.allSource = allSource;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public List<ReleaseHistory> getAllReleaseHistorys() {
		return allReleaseHistorys;
	}

	public void setAllReleaseHistorys(List<ReleaseHistory> allReleaseHistorys) {
		this.allReleaseHistorys = allReleaseHistorys;
	}

	public List<Source> searchSourceByContent(String content) {
		// TODO Auto-generated method stub
		allSource = this.getHibernateTemplate().find("from Source s where s.content like ?", "%"+content+"%");
		for (Source source : allSource){
			String res = source.getContent().replace(content, "<span class='search_result_highlight'>"+content+"</span>");
			source.setContent(res);
		}
		return allSource;
	}

	public List<Connection> searchConnectionByContent(String content) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> searchUserByUsername(String username) {
		// TODO Auto-generated method stub
		allUsers = this.getHibernateTemplate().find("from User u where u.username like ? or u.user_info like ?", "%"+username+"%", "%"+username+"%");
		for (User user : allUsers){
			String res = user.getUsername().replace(username, "<span class='search_result_highlight'>"+username+"</span>");
			user.setUsername(res);
			res = user.getUser_info().replace(username, "<span class='search_result_highlight'>"+username+"</span>");
			user.setUser_info(res);
		}
		return allUsers;
	}

	public List<ReleaseHistory> searchReleaseHistoryByContent(String content) {
		// TODO Auto-generated method stub
		allReleaseHistorys = this.getHibernateTemplate().find("from ReleaseHistory r where r.content like ?", "%"+content+"%");
		for (ReleaseHistory his : allReleaseHistorys){
			String sub = his.getContent().length() <= 20 ? his.getContent() : his.getContent().substring(0, 20)+"...";
			String res = his.getContent().replace(content, "<span class='search_result_highlight'>"+content+"</span>");
			his.setContent(res);
			sub = sub.replace(content, "<span class='search_result_highlight'>"+content+"</span>");
			his.setContentSub(sub);
		}
		return allReleaseHistorys;
	}

	public List<ReleaseHistory> searchReleaseHistoryByDate(String startDate,String endDate) {
		// TODO Auto-generated method stub
		String str = "from ReleaseHistory as r where r.date between '"+ startDate +"' and '"+ endDate +"'";
		allReleaseHistorys = getHibernateTemplate().find(str);
		return allReleaseHistorys;
	}

	public List<ReleaseHistory> searchReleaseHistoryByContentAndDate(String content, String startDate, String endDate) {
		// TODO Auto-generated method stub
		String str = "from ReleaseHistory as r where r.date between '"+ startDate +"' and '"+ endDate +"'AND r.content like '%"+content+"%'";
		allReleaseHistorys = getHibernateTemplate().find(str);
		for (ReleaseHistory his : allReleaseHistorys){
			String res = his.getContent().replace(content, "<span class='search_result_highlight'>"+content+"</span>");
			his.setContent(res);
		}
		return allReleaseHistorys;
	}
	
	public ReleaseHistory searchReleaseHistoryById(String id){
		String str = "from ReleaseHistory as r where r.release_id = "+id;
		allReleaseHistorys = getHibernateTemplate().find(str);
		if (allReleaseHistorys == null || allReleaseHistorys.size() == 0){
			return null;
		}
		return allReleaseHistorys.get(0);
	}

}
