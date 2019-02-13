package com.ibm.p1.entity;

import java.util.HashSet;
import java.util.Set;

public class User implements java.io.Serializable {

	private int user_id;
	private String username;
	private String password;
	private String user_info;
	private String type;
	private String certCode;
	private String email;
	private String realname;
	private String access_token;
	private Set<ReleaseHistory> releaseHistory  = new HashSet<ReleaseHistory>();
	private Set<Source> source = new HashSet<Source>();
	private Set<CurrentToolsStatus> currentToolsStatus = new HashSet<CurrentToolsStatus>();
	public String getCertCode() {
		return certCode;
	}
	public void setCertCode(String certCode) {
		this.certCode = certCode;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_info() {
		return user_info;
	}
	public void setUser_info(String user_info) {
		this.user_info = user_info;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<ReleaseHistory> getReleaseHistory() {
		return releaseHistory;
	}
	public void setReleaseHistory(Set<ReleaseHistory> releaseHistory) {
		this.releaseHistory = releaseHistory;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Set<Source> getSource() {
		return source;
	}
	public void setSource(Set<Source> source) {
		this.source = source;
	}
	public Set<CurrentToolsStatus> getCurrentToolsStatus() {
		return currentToolsStatus;
	}
	public void setCurrentToolsStatus(Set<CurrentToolsStatus> currentToolsStatus) {
		this.currentToolsStatus = currentToolsStatus;
	}
	
	
}
