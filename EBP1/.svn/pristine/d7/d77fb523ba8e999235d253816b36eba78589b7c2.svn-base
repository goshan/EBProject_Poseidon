package com.ibm.p1.dao;

import java.util.List;

import weibo4j.model.Comment;
import weibo4j.model.Status;

import com.ibm.p1.entity.ConnectionsReply;
import com.ibm.p1.entity.ConnectionsTopic;
import com.ibm.p1.entity.MyComment;
import com.ibm.p1.entity.MyStatus;
import com.ibm.p1.entity.Parameter;
import com.sun.syndication.feed.synd.SyndEntry;

public interface AcquireDao {
	public Parameter getAcquireInfoById(int parameter_id);
	public void saveStatusesMentions(Status status);
	public void saveCommentsMentions(Comment comment);
	public void saveStatuses(Status status);
	public void saveComments(Comment comment);
	public String contentWash(String content);
	public void saveTopics(SyndEntry entry);
	public void saveReplies(SyndEntry entry,String topic_id);
	public List<String> getStatusIds();
	public List<MyStatus> getAllMyStatuses();
	public List<MyComment> getAllMyComments(String statusId);
	public List<MyComment> getAllMyCommentsNotIgnored();
	public MyStatus getMyStatus(String id);
	public MyComment getMyComment(String id);
	public void ignoreComment(String commentId);
	public boolean ignoreReply(String replyId);

	public List<ConnectionsReply> getAllConnectionsRepliesNotIgnored();
	public List<ConnectionsReply> getAllConnectionsReplies(String topicId);
	public ConnectionsTopic getConnectionsTopic(String id);
	public ConnectionsReply getConnectionsReply(String id);
	public List<ConnectionsTopic> getConnectionsTopics();
	public boolean removeTopic(String topicId);
	
	
}
