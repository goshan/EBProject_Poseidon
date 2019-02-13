package com.ibm.p1.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.ibm.p1.dao.ReleaseDao;
import com.ibm.p1.entity.Fans;
import com.ibm.p1.entity.ReleaseHistory;

public class ReleaseDaoImpl extends HibernateDaoSupport implements ReleaseDao{

	public boolean saveReleaseSinaWeiboHistory(ReleaseHistory releaseHistory) {
		try {
			// TODO Auto-generated method stub
			getHibernateTemplate().save(releaseHistory);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

	public boolean saveReleaseSinaWeiboHistoryToDraft(ReleaseHistory releaseHistory) {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			getHibernateTemplate().save(releaseHistory);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public ArrayList<Fans> getFansByEntry(ArrayList<Entry<String, Double>> tmp){
		ArrayList<Fans> res = new ArrayList<Fans>();
		for (int i=0; i<tmp.size(); i++){
			String name = tmp.get(i).getKey();
			List<Fans> fansList = getHibernateTemplate().find("from Fans f where f.username=?",name);
			if (!fansList.isEmpty()){
				Fans fans = fansList.get(0);
				res.add(fans);
			}
		}
		return res;
	}

}
