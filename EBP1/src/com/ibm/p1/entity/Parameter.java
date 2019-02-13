package com.ibm.p1.entity;

public class Parameter implements java.io.Serializable{
	/**
	 * 
	 */
	private int parameter_id;
	private int acquireTimer;
	private int acquireNum;
	private int weiboNum;
	private String cache_dir;
	private String tools_dir;
	private int chineseFilter;
	private int communitiesNum;
	private String access_token;
	private String weibo_account;
	private String community_uuid;
	private int source_count_num;
	private String connectionsAccount;
	private String connectionsPwd;
	private String connectionsForumUuid;
	private int totalSourceNum;
	
	//static globe
	public  String getRootPath(){
		return System.getProperty("user.dir").substring(0, System.getProperty("user.dir").lastIndexOf("\\"))
		+"\\webapps\\EBP1\\";
	}
	
	public int getParameter_id() {
		return parameter_id;
	}
	public void setParameter_id(int parameter_id) {
		this.parameter_id = parameter_id;
	}
	public int getAcquireTimer() {
		return acquireTimer;
	}
	public void setAcquireTimer(int acquireTimer) {
		this.acquireTimer = acquireTimer;
	}
	public int getAcquireNum() {
		return acquireNum;
	}
	public void setAcquireNum(int acquireNum) {
		this.acquireNum = acquireNum;
	}
	public int getWeiboNum() {
		return weiboNum;
	}
	public void setWeiboNum(int weiboNum) {
		this.weiboNum = weiboNum;
	}
	public String getCache_dir() {
		return this.cache_dir;
	}
	public void setCache_dir(String cache_dir) {
		this.cache_dir = cache_dir;
	}
	public String getCache_dir_sina() {
		return cache_dir+"Sina\\";
	}
	public String getCache_dir_connection() {
		return cache_dir+"Connection\\";
	}
	public String getCache_dir_ZombieType() {
		return cache_dir+"ZombieFansFilter\\";
	}
	public String getCache_dir_FansPOI() {
		return cache_dir+"FansPOI\\";
	}
	public String getCache_dir_TotalPOI() {
		return cache_dir+"TotalPOI\\";
	}
	public String getCache_dir_ConnectionKeyWords() {
		return cache_dir+"ConnectionKeyWords\\";
	}
	public String getCache_dir_KeyWordsMatch() {
		return cache_dir+"KeyWordsMatch\\";
	}
	public String getCache_dir_RecommandQA() {
		return cache_dir+"RecommandQA\\";
	}
	public String getCache_dir_SourceKeyWords() {
		return cache_dir+"SourceKeyWords\\";
	}
	public String getCache_dir_AtFans(){
		return cache_dir+"AtFans\\";
	}
	public String getTools_dir() {
		return tools_dir;
	}
	public void setTools_dir(String tools_dir) {
		this.tools_dir = tools_dir;
	}
	public String getTools_dir_ZombieType() {
		return tools_dir+"ZombieFansFilter\\";
	}
	public String getTools_dir_FansPOI() {
		return tools_dir+"FansPOI\\";
	}
	public String getTools_dir_TotalPOI() {
		return tools_dir+"TotalPOI\\";
	}
	public String getTools_dir_ConnectionKeyWords() {
		return tools_dir+"ConnectionKeyWords\\";
	}
	public String getTools_dir_KeyWordsMatch() {
		return tools_dir+"KeyWordsMatch\\";
	}
	public String getTools_dir_RecommandQA() {
		return tools_dir+"RecommandQA\\";
	}
	public String getTools_dir_SourceKeyWords() {
		return tools_dir+"SourceKeyWords";
	}
	public int getChineseFilter() {
		return chineseFilter;
	}
	public void setChineseFilter(int chineseFilter) {
		this.chineseFilter = chineseFilter;
	}
	public int getCommunitiesNum() {
		return communitiesNum;
	}
	public void setCommunitiesNum(int communitiesNum) {
		this.communitiesNum = communitiesNum;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getWeibo_account() {
		return weibo_account;
	}
	public void setWeibo_account(String weibo_account) {
		this.weibo_account = weibo_account;
	}

	public String getCommunity_uuid() {
		return community_uuid;
	}

	public void setCommunity_uuid(String communityUuid) {
		community_uuid = communityUuid;
	}

	public String getConnectionsAccount() {
		return connectionsAccount;
	}

	public void setConnectionsAccount(String connectionsAccount) {
		this.connectionsAccount = connectionsAccount;
	}

	public String getConnectionsPwd() {
		return connectionsPwd;
	}

	public void setConnectionsPwd(String connectionsPwd) {
		this.connectionsPwd = connectionsPwd;
	}

	public String getConnectionsForumUuid() {
		return connectionsForumUuid;
	}

	public void setConnectionsForumUuid(String connectionsForumUuid) {
		this.connectionsForumUuid = connectionsForumUuid;
	}
	public int getSource_count_num() {
		return source_count_num;
	}

	public void setSource_count_num(int source_count_num) {
		this.source_count_num = source_count_num;
	}

	public int getTotalSourceNum() {
		return totalSourceNum;
	}

	public void setTotalSourceNum(int totalSourceNum) {
		this.totalSourceNum = totalSourceNum;
	}
	
	
	
}