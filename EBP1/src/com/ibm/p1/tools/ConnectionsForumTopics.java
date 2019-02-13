package com.ibm.p1.tools;

import java.net.URISyntaxException;
import java.util.Date;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.protocol.Response;
import org.apache.abdera.protocol.client.AbderaClient;
import org.apache.abdera.protocol.client.RequestOptions;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;

import com.ibm.p1.entity.Parameter;

public class ConnectionsForumTopics {

//	private String connectionsAccount;
//	private String connectionsPwd;
//	private String forumUuid;
	public static int createTopic(String title, String content) {

		Parameter param=HibernateUtil.getParam();
		
		Abdera abdera = new Abdera();
		Factory factory = abdera.getFactory();

		Entry entry = createAtomEntry(factory, new Date(), title,
				content);
		
		System.out.println("开始发布条目到Connections");
		AbderaClient client = new AbderaClient(abdera);
		
		Credentials creds = new UsernamePasswordCredentials(
				param.getConnectionsAccount(), param.getConnectionsPwd());

		// 将凭证信息加到abdera客户端
		try {
			client
					.addCredentials(
							"https://w3-connections.ibm.com/",
							null, null, creds);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		RequestOptions options = client.getDefaultRequestOptions();
		options.setUseChunked(false);
		Response response = client
				.post(
						"https://w3-connections.ibm.com/forums/atom/topics?forumUuid="+param.getConnectionsForumUuid(),
						entry, options);
		System.out.println(response.getStatus());
		
		return response.getStatus();
	}
	

	public static Entry createAtomEntry(Factory abderaFactory,
			Date updatedDate, String title, String content) {
		Entry entry = abderaFactory.newEntry();
		entry.setTitle(title);
		entry.addCategory("http://www.ibm.com/xmlns/prod/sn/type",
				"forum-topic", "forum-topic");
		entry.setContentAsHtml("<p>" + content + "</p>");
		return entry;
	}
}
