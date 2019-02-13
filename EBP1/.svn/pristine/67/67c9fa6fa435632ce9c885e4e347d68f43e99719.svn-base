package com.ibm.p1.dao.impl;


import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import weibo4j.model.Comment;
import weibo4j.model.Status;

import com.ibm.p1.dao.AcquireDao;
import com.ibm.p1.entity.ConnectionsReply;
import com.ibm.p1.entity.ConnectionsTopic;
import com.ibm.p1.entity.MyComment;
import com.ibm.p1.entity.MyStatus;
import com.ibm.p1.entity.Parameter;
import com.ibm.p1.tools.HibernateUtil;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;

public class AcquireDaoImpl extends HibernateDaoSupport implements AcquireDao {
	private Parameter parameter;

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public Parameter getAcquireInfoById(int parameter_id) {
		// TODO Auto-generated method stub
		parameter = getHibernateTemplate().get(Parameter.class, parameter_id);
		return parameter;
	}

	public void saveStatusesMentions(Status status) {
		// TODO Auto-generated method stub
		Session session = null;
		session = super.getSessionFactory().getCurrentSession();
		String sql="insert ignore into statuses_mentions(status_id,user_screenname,status_text) values('"+status.getId()+"','"+status.getUser().getScreenName()+"','"+status.getText()+"')";
		Transaction tx = session.beginTransaction();
		session.createSQLQuery(sql).executeUpdate();
		tx.commit();
	}

	public void saveCommentsMentions(Comment comment) {
		// TODO Auto-generated method stub
		Session session = null;
		session = super.getSessionFactory().getCurrentSession();
		String sql="insert ignore into comments_mentions(comment_id,status_id,user_screenname,comment_text) values('"+comment.getId()+"','"+comment.getStatus().getId()+"','"+comment.getUser().getScreenName()+"','"+comment.getText()+"')";
		Transaction tx = session.beginTransaction();
		session.createSQLQuery(sql).executeUpdate();
		tx.commit();
	}
	
	public void saveStatuses(Status status){
		Session session = null;
		session = super.getSessionFactory().getCurrentSession();
		String sql="insert ignore into statuses(status_id,user_screenname,status_text) values('"+status.getId()+"','"+status.getUser().getScreenName()+"','"+status.getText()+"')";
		Transaction tx = session.beginTransaction();
		session.createSQLQuery(sql).executeUpdate();
		tx.commit();
		
		
	}

	public List<String> getStatusIds() {
		// TODO Auto-generated method stub
		
		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql="select statusId from MyStatus";
		List<String> list=session.createQuery(sql).list();
					
		return list;
	}

	public void saveComments(Comment comment) {
		// TODO Auto-generated method stub
		Session session = null;
		session = super.getSessionFactory().getCurrentSession();
		String sql="insert ignore into statuses_comments(comment_id,status_id,comment_user,comment_text,comment_date,ignored,mid) values('"+comment.getId()+"','"+comment.getStatus().getId()+"','"+comment.getUser().getScreenName()+"','"+comment.getText()+"','"+this.dateToDateTime(comment.getCreatedAt())+"','false'"+","+comment.getMid()+")";
		Transaction tx = session.beginTransaction();
		session.createSQLQuery(sql).executeUpdate();
		tx.commit();
		
		
	}

	//Tue Jul 23 16:22:40 CST 2013
	public String dateToDateTime(Date date){
		final String[] month={
				"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"
		};
		StringBuffer ret=new StringBuffer();
		String dateToString=date.toString();
		ret.append(dateToString.substring(24,24+4));
		String sMonth=dateToString.substring(4,4+3);
		
		for(int i =0;i<12;i++){
			if(sMonth.equalsIgnoreCase(month[i])){
				if((i+1) <10){
					ret.append("-0");
				}
				else{
					ret.append("-");
				}
				ret.append(i+1);
				break;
			}
		}
		ret.append("-");
		ret.append(dateToString.substring(8,8+2));
		ret.append(" ");
		ret.append(dateToString.substring(11,11+8));
		return ret.toString();
	}
	
	public String contentWash(String content) {
		String res = "";

		// 1. remove html tag and link<a>
		boolean tagArea = false;
		boolean linkArea = false;
		for (int i = 0; i < content.length(); i++) {
			if (content.charAt(i) == '<') {
				if (content.charAt(i + 1) == '/') {
					if (linkArea) {
						linkArea = false;
					}
					tagArea = true;
				} else if (content.charAt(i + 1) == 'a') {
					linkArea = true;
				} else {
					tagArea = true;
				}
			} else if (content.charAt(i) == '>') {
				if (tagArea) {
					tagArea = false;
				}
			} else {
				if (!linkArea && !tagArea) {
					res += content.charAt(i);
				}
			}
		}

		// 2. remove /r /n and html mark
		res = res.replaceAll("\r\n", " ");
		res = res.replaceAll("\r", " ");
		res = res.replaceAll("\n", " ");
		res = res.replaceAll("&nbsp;", " ");
		res = res.replaceAll("&gt;", " ");
		res = res.replaceAll("&lt;", " ");
		res = res.replaceAll("&quot;", "\"");

		// 3. combine space
		boolean spaceArea = false;
		String tmp = res;
		res = "";
		for (int i = 0; i < tmp.length(); i++) {
			if (tmp.charAt(i) == ' ' || tmp.charAt(i) == '\t') {
				if (spaceArea) {
				} else {
					res += " ";
					spaceArea = true;
				}
			} else {
				res += tmp.charAt(i);
				spaceArea = false;
			}
		}
		return res;
	}

	public void saveTopics(SyndEntry entry) {
		// TODO Auto-generated method stub
		String uri=entry.getUri();
		String id=uri.substring(uri.lastIndexOf(":")+1);
		String title = entry.getTitle();
		String statusId = "";
		String commentId = "";
		if (title.contains("(")){
			statusId = title.substring(title.indexOf("statusId:")+"statusId: ".length(), title.indexOf(","));
			commentId = title.substring(title.indexOf("commentId:")+"commentId: ".length(), title.indexOf(")"));
			title = title.substring(0, title.indexOf(" ("));
		}
		String content = entry.getContents().get(0).toString();
		content = content.substring(content
				.indexOf("SyndContentImpl.value=")
				+ new String("SyndContentImpl.value=").length());
		content = content.substring(0, content
				.indexOf("SyndContentImpl.interface="));
		content = this.contentWash(content);
		
		Session session = null;
		session = super.getSessionFactory().getCurrentSession();
		String sql="insert ignore into topics(topic_id,title,weibo_status_id,published_date,weibo_comment_id,content) values('"+id+"','"+title+"','"+statusId+"','"+this.dateToDateTime(entry.getPublishedDate())+"','"+commentId+"','"+content+"')";
		Transaction tx = session.beginTransaction();
		session.createSQLQuery(sql).executeUpdate();
		tx.commit();
	}

	public void saveReplies(SyndEntry entry,String topic_id) {
		// TODO Auto-generated method stub
		String uri=entry.getUri();
		String id=uri.substring(uri.lastIndexOf(":")+1);
		String author=entry.getAuthor();
		
		
		SyndContent content=(SyndContent) entry.getContents().get(0);
		String s1=content.getValue();
		
		if(s1.length() <= 0){
			return;
		}
		String s2=s1.substring(s1.indexOf(">")+1);
		String text=s2.substring(0,s2.indexOf("<"));
		
		Session session = null;
		session = super.getSessionFactory().getCurrentSession();
		String sql="insert ignore into topics_replies(topic_id,reply_id,reply_text,published_date,author,ignored) values('"+topic_id+"','"+id+"','"+text.trim()+"','"+this.dateToDateTime(entry.getPublishedDate())+"','"+author+"','false')";
		Transaction tx = session.beginTransaction();
		session.createSQLQuery(sql).executeUpdate();
		tx.commit();
	}

	public List<MyStatus> getAllMyStatuses() {

		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql="select * from statuses";
		List<MyStatus> list=session.createSQLQuery(sql).addEntity(MyStatus.class).list();
		tx.commit();
		return list;
	}

	public List<MyComment> getAllMyComments(String statusId) {
		// TODO Auto-generated method stub
		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql="select * from statuses_comments where status_id='"+statusId+"'"+" order by comment_date desc";
		List<MyComment> list=session.createSQLQuery(sql).addEntity(MyComment.class).list();
		tx.commit();
		return list;
	}
	public List<MyComment> getAllMyCommentsNotIgnored() {
		String mySinaName = HibernateUtil.getParam().getWeibo_account();
		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql="select * from statuses_comments where ignored='false' and comment_user != '"+mySinaName+"' order by comment_date desc";
		List<MyComment> list=session.createSQLQuery(sql).addEntity(MyComment.class).list();
		tx.commit();
		return list;
	}

	public void ignoreComment(String commentId) {
		
		
		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql="update statuses_comments set ignored='true' where comment_id='"+commentId+"'";
		session.createSQLQuery(sql).executeUpdate();
		tx.commit();
		
	}

	public MyStatus getMyStatus(String id) {
		
		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql="select * from statuses where status_id='"+id+"'";
		List<MyStatus> status=session.createSQLQuery(sql).addEntity(MyStatus.class).list();
		
		return status == null?null:status.get(0);
	}

	public MyComment getMyComment(String id) {
		
		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql="select * from statuses_comments where comment_id='"+id+"'";
		List<MyComment> comment=session.createSQLQuery(sql).addEntity(MyComment.class).list();
		
		return comment == null || comment.size() == 0?null:comment.get(0);
	}

	public List<ConnectionsReply> getAllConnectionsRepliesNotIgnored() {
		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql="select * from topics_replies where ignored='false' order by published_date desc";
		List<ConnectionsReply> list=session.createSQLQuery(sql).addEntity(ConnectionsReply.class).list();
		tx.commit();
		return list;
	}

	public ConnectionsTopic getConnectionsTopic(String id) {

		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql="select * from topics where topic_id='"+id+"'";
		List<ConnectionsTopic> topic=session.createSQLQuery(sql).addEntity(ConnectionsTopic.class).list();
		
		return topic == null?null:topic.get(0);
	}
	
	public ConnectionsReply getConnectionsReply(String id) {

		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql="select * from topics_replies where reply_id='"+id+"'";
		List<ConnectionsReply> replies=session.createSQLQuery(sql).addEntity(ConnectionsReply.class).list();
		
		return replies == null?null:replies.get(0);
	}

	public boolean ignoreReply(String replyId) {
		boolean flag;
		try{
			Session session=super.getSessionFactory().getCurrentSession();
			Transaction tx=session.beginTransaction();
			String sql="update topics_replies set ignored='true' where reply_id='"+replyId+"'";
			session.createSQLQuery(sql).executeUpdate();
			tx.commit();
			flag=true;
		}catch(Exception ex){
			flag=false;
		}
		return flag;
	}

	public List<ConnectionsTopic> getConnectionsTopics() {
		
		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql="select * from topics  order by published_date desc";
		List<ConnectionsTopic> list=session.createSQLQuery(sql).addEntity(ConnectionsTopic.class).list();
		tx.commit();
		return list;
		
	}

	public boolean removeTopic(String topicId) {
		
		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql="delete from topics where topic_id ='"+topicId+"'";
		session.createSQLQuery(sql).executeUpdate();
		tx.commit();
		return false;
	}
	public List<ConnectionsReply> getAllConnectionsReplies(String topicId) {
		
		Session session=super.getSessionFactory().getCurrentSession();
		Transaction tx=session.beginTransaction();
		String sql="select * from topics_replies where topic_id='"+topicId+"' order by published_date desc";
		List<ConnectionsReply> list=session.createSQLQuery(sql).addEntity(ConnectionsReply.class).list();
		tx.commit();
		return list;
	}
	
}
