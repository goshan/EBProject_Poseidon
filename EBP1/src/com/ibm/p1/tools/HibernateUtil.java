package com.ibm.p1.tools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ibm.p1.entity.Parameter;

public class HibernateUtil {

	private static final Parameter param = new Parameter();

	public static Parameter getParam() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ebp1?characterEncoding=utf-8",
					"root", "root");

			Statement stmt = conn.createStatement();
			String sql = "select * from parameter";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				param.setParameter_id(rs.getInt("parameter_id"));
				param.setAcquireTimer(rs.getInt("acquireTimer"));
				param.setAcquireNum(rs.getInt("acquireNum"));
				param.setWeiboNum(rs.getInt("weiboNum"));
				param.setCache_dir(rs.getString("cache_dir"));
				param.setTools_dir(rs.getString("tools_dir"));
				param.setChineseFilter(rs.getInt("chineseFilter"));
				param.setCommunitiesNum(rs.getInt("communitiesNum"));
				param.setWeibo_account(rs.getString("weibo_account"));
				param.setSource_count_num(rs.getInt("source_count_num"));
				param.setAccess_token(rs.getString("access_token"));
				param.setCommunity_uuid(rs.getString("community_uuid"));
				param.setConnectionsAccount(rs.getString("connections_account"));
				param.setConnectionsForumUuid(rs.getString("connections_forumUuid"));
				param.setConnectionsPwd(rs.getString("connections_pwd"));
			}
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return param;
	}
}
