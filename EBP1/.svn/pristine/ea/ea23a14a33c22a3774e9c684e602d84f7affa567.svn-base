<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ibm.p1.entity.Parameter"%>

<% 
	Parameter param = (Parameter)request.getAttribute("parameters");
%>





<div id="option_config_tittle">
	<span id="option_config_title_content">Paramster Configuration</span>
	<a id="option_save" href="/EBP1/manageAction_setAcquireInfo.action"></a>
</div>
<img id="option_config_conrner" src="/EBP1/image/optionCorner.png">
<div id="option_config_scroll">
	<div class="option_input_line">
		<span class="option_input_label">获取周期</span>
		<span id="option_acquire_timer_freeze" class="option_input_content_freeze"><%=param.getAcquireTimer() %></span>
		<input id="option_acquire_timer" class="option_input_content" type="text">
		<a class="option_edit" href="#"></a>
		<a class="option_back" href="#"></a>
	</div>
	<div class="option_input_line">
		<span class="option_input_label">获取粉丝数</span>
		<span id="option_acquire_num_freeze" class="option_input_content_freeze"><%=param.getAcquireNum() %></span>
		<input id="option_acquire_num" class="option_input_content" type="text">
		<a class="option_edit" href="#"></a>
		<a class="option_back" href="#"></a>
	</div>
	<div class="option_input_line">
		<span class="option_input_label">获取粉丝微博数</span>
		<span id="option_weibo_num_freeze" class="option_input_content_freeze"><%=param.getWeiboNum() %></span>
		<input id="option_weibo_num" class="option_input_content" type="text">
		<a class="option_edit" href="#"></a>
		<a class="option_back" href="#"></a>
	</div>
	<div class="option_input_line">
		<span class="option_input_label">缓存路径</span>
		<span id="option_cache_path_freeze" class="option_input_content_freeze"><%=param.getCache_dir() %></span>
		<input id="option_cache_path" class="option_input_content" type="text">
		<a class="option_edit" href="#"></a>
		<a class="option_back" href="#"></a>
	</div>
	<div class="option_input_line">
		<span class="option_input_label">分析工具路径</span>
		<span id="option_tools_path_freeze" class="option_input_content_freeze"><%=param.getTools_dir() %></span>
		<input id="option_tools_path" class="option_input_content" type="text">
		<a class="option_edit" href="#"></a>
		<a class="option_back" href="#"></a>
	</div>
	<div class="option_input_line">
		<span class="option_input_label">是否中文过滤</span>
		<span id="option_chinese_filter_freeze" class="option_input_content_freeze"><%=param.getChineseFilter() %></span>
		<input id="option_chinese_filter" class="option_input_content" type="text">
		<a class="option_edit" href="#"></a>
		<a class="option_back" href="#"></a>
	</div>
	<div class="option_input_line">
		<span class="option_input_label">获取社区数</span>
		<span id="option_communities_num_freeze" class="option_input_content_freeze"><%=param.getCommunitiesNum() %></span>
		<input id="option_communities_num" class="option_input_content" type="text">
		<a class="option_edit" href="#"></a>
		<a class="option_back" href="#"></a>
	</div>
	<div class="option_input_line">
		<span class="option_input_label">微博accessToken</span>
		<span id="option_access_token_freeze" class="option_input_content_freeze"><%=param.getAccess_token() %></span>
		<input id="option_access_token" class="option_input_content" type="text">
		<a class="option_edit" href="#"></a>
		<a class="option_back" href="#"></a>
	</div>
	<div class="option_input_line">
		<span class="option_input_label">微博账户名</span>
		<span id="option_weibo_name_freeze" class="option_input_content_freeze"><%=param.getWeibo_account() %></span>
		<input id="option_weibo_name" class="option_input_content" type="text">
		<a class="option_edit" href="#"></a>
		<a class="option_back" href="#"></a>
	</div>
	<div class="option_input_line">
		<span class="option_input_label">素材池分析规模</span>
		<span id="option_source_num_freeze" class="option_input_content_freeze"><%=param.getSource_count_num() %></span>
		<input id="option_source_num" class="option_input_content" type="text">
		<a class="option_edit" href="#"></a>
		<a class="option_back" href="#"></a>
	</div>
	<div class="option_input_line">
		<span class="option_input_label">自主社区uuid</span>
		<span id="option_community_uuid_freeze" class="option_input_content_freeze"><%=param.getCommunity_uuid() %></span>
		<input id="option_community_uuid" class="option_input_content" type="text">
		<a class="option_edit" href="#"></a>
		<a class="option_back" href="#"></a>
	</div>
	<div class="option_input_line">
		<span class="option_input_label">connection账户名</span>
		<span id="option_connection_name_freeze" class="option_input_content_freeze"><%=param.getConnectionsAccount() %></span>
		<input id="option_connection_name" class="option_input_content" type="text">
		<a class="option_edit" href="#"></a>
		<a class="option_back" href="#"></a>
	</div>
	<div class="option_input_line">
		<span class="option_input_label">connection密码</span>
		<span id="option_connection_password_freeze" class="option_input_content_freeze"><%=param.getConnectionsPwd() %></span>
		<input id="option_connection_password" class="option_input_content" type="text">
		<a class="option_edit" href="#"></a>
		<a class="option_back" href="#"></a>
	</div>
	<div class="option_input_line">
		<span class="option_input_label">connection论坛uuid</span>
		<span id="option_forum_uuid_freeze" class="option_input_content_freeze"><%=param.getConnectionsForumUuid() %></span>
		<input id="option_forum_uuid" class="option_input_content" type="text">
		<a class="option_edit" href="#"></a>
		<a class="option_back" href="#"></a>
	</div>
</div>


<script>
	$(document).ready(function(){
		option_config_page();
	});
</script>