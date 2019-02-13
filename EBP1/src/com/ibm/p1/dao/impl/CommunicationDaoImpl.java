package com.ibm.p1.dao.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ibm.p1.dao.CommunicationDao;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.entity.Source;
import com.ibm.p1.service.AnalysisService;
import com.ibm.p1.service.impl.AnalysisServiceImpl;
import com.ibm.p1.tools.HibernateUtil;

public class CommunicationDaoImpl extends HibernateDaoSupport implements CommunicationDao{
	private Source source;
	private List<Source> allSource;
	static private Long milSec = (long) 1000;
	static private Long milSecPerDay = (long)milSec*60*60*24;
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
	}

	public void setAllSource(List<Source> allSource) {
		this.allSource = allSource;
	}
	public List<Source> getAllSource() {
		return allSource;
	}
	public Source getSourceById(int id) {
		// TODO Auto-generated method stub
		source = getHibernateTemplate().get(Source.class, id);
		return source;
	}

	public List<Source> getAllSources() {
		// TODO Auto-generated method stub
		allSource = getHibernateTemplate().find("from Source");
		return allSource;
	}

	public void submitSource(Source source) {
		try {
			// TODO Auto-generated method stub
			getHibernateTemplate().save(source);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public List<Source> sourceAnalysis(int num, Source source,String dir) throws IOException {
		// TODO Auto-generated method stub
		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from Source");
		q.setFirstResult(0);
		q.setMaxResults(num);
		allSource = q.list();
		tx.commit();
		return allSource;
	}

	public Source getLastSource() {
		// TODO Auto-generated method stub
		allSource = getHibernateTemplate().find("from Source");
		int lastSourceSize = allSource.size()-1;
		source = allSource.get(lastSourceSize);
		return source;
	}
	public List<Source> getEnoughNumOfSources(int num) {
		// TODO Auto-generated method stub
		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from Source");
		q.setFirstResult(0);
		q.setMaxResults(num);
		allSource = q.list();
		tx.commit();
		return allSource;
	}
	public List<Source> searchSourceByContent(String content) {
		// TODO Auto-generated method stub
		allSource = this.getHibernateTemplate().find("from Source s where s.content like ?", content);
		return allSource;
	}
	public List<Source> getSourcesYesterdayAndToday() {
		// TODO Auto-generated method stub
		Date now = new Date();
		java.sql.Date date=new java.sql.Date(new java.util.Date().getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		String endDate = sdf.format(new Date((date.getTime()+milSecPerDay)));
		String startDate = sdf.format(new Date((date.getTime()-milSecPerDay)));
		String sql = "from Source as s where s.created_at between '"+ startDate +"' and '"+ endDate +"'";
		//System.out.println(endDate+"date:"+date.getTime()+startDate);
		
		allSource = getHibernateTemplate().find(sql);
		for(int i = 0 ;i<allSource.size();i++){
			System.out.println("allSource"+allSource.get(i).toString());
		}
		
		return allSource;
	}
	
}
