<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ibm.p1.entity.Parameter"%>

<% 
	HashMap<String, Boolean> status = (HashMap<String, Boolean>)request.getAttribute("status");
%>





<div id="option_tools_tittle">
	<span id="option_tools_title_content">Tools</span>
</div>
<img id="option_tools_conrner" src="/EBP1/image/optionCorner.png">
<div id="option_tools_table">
	<div id="option_tools_table_head">
		<span id="option_tools_name">Tools name</span>
		<span id="option_tools_cycle">Run cycle</span>
		<span id="option_tools_status">Status</span>
	</div>
	<div class="option_tools_line_even">
		<span class="option_tools_name_content">数据获取与分析</span>
		<span class="option_tools_cycle_content">1次/天</span>
		<span class="option_tools_status_running <%=(status.get("AcquireAndAnalyseRunning") ? "option_tools_status_open" : "") %>"><%=(status.get("AcquireAndAnalyseRunning") ? "●运行中" : "●未运行") %></span>
		<span class="option_tools_status_mession <%=(status.get("AcquireAndAnalyseOpen") ? "option_tools_status_open" : "") %>"><%=(status.get("AcquireAndAnalyseOpen") ? "●定时开" : "●定时关") %></span>
		<a class="option_tools_detail" href="#"></a>
		<div class="option_tools_detail_content hidden" style="top: 282px;">顺序执行sina，connection的静态数据获取与分析任务</div>
		<a class="option_tools_action_running option_tools_<%=(status.get("AcquireAndAnalyseRunning") ? "hold" : "run") %>" href="/EBP1/acquireAction_executeMession.action?mession=AcquireAndAnalyse&action=run"></a>
		<a class="option_tools_action_mession option_tools_<%=(status.get("AcquireAndAnalyseOpen") ? "close" : "open") %>" href="/EBP1/acquireAction_executeMession.action?mession=AcquireAndAnalyse&action=<%=(status.get("AcquireAndAnalyseOpen") ? "close" : "open") %>"></a>
	</div>
	<div class="option_tools_line_odd">
		<span class="option_tools_name_content">微博留言获取</span>
		<span class="option_tools_cycle_content">1次/2小时</span>
		<span class="option_tools_status_running <%=(status.get("WeiboReplyRunning") ? "option_tools_status_open" : "") %>"><%=(status.get("WeiboReplyRunning") ? "●运行中" : "●未运行") %></span>
		<span class="option_tools_status_mession <%=(status.get("WeiboReplyOpen") ? "option_tools_status_open" : "") %>"><%=(status.get("WeiboReplyOpen") ? "●定时开" : "●定时关") %></span>
		<a class="option_tools_detail" href="#"></a>
		<div class="option_tools_detail_content hidden" style="top: 324px;">获取sina对应主页内的粉丝留言内容</div>
		<a class="option_tools_action_running option_tools_<%=(status.get("WeiboReplyRunning") ? "hold" : "run") %>" href="/EBP1/acquireAction_executeMession.action?mession=AcquireWeiboReply&action=run"></a>
		<a class="option_tools_action_mession option_tools_<%=(status.get("WeiboReplyOpen") ? "close" : "open") %>" href="/EBP1/acquireAction_executeMession.action?mession=AcquireWeiboReply&action=<%=(status.get("WeiboReplyOpen") ? "close" : "open") %>"></a>
	</div>
	<div class="option_tools_line_even">
		<span class="option_tools_name_content">Connection回复获取</span>
		<span class="option_tools_cycle_content">1次/天</span>
		<span class="option_tools_status_running <%=(status.get("ConnectionReplyRunning") ? "option_tools_status_open" : "") %>"><%=(status.get("ConnectionReplyRunning") ? "●运行中" : "●未运行") %></span>
		<span class="option_tools_status_mession <%=(status.get("ConnectionReplyOpen") ? "option_tools_status_open" : "") %>"><%=(status.get("ConnectionReplyOpen") ? "●定时开" : "●定时关") %></span>
		<a class="option_tools_detail" href="#"></a>
		<div class="option_tools_detail_content hidden" style="top: 366px;">获取connection对应主页内的topic的留言内容</div>
		<a class="option_tools_action_running option_tools_<%=(status.get("ConnectionReplyRunning") ? "hold" : "run") %>" href="/EBP1/acquireAction_executeMession.action?mession=AcquireConnectionReply&action=run"></a>
		<a class="option_tools_action_mession option_tools_<%=(status.get("ConnectionReplyOpen") ? "close" : "open") %>" href="/EBP1/acquireAction_executeMession.action?mession=AcquireConnectionReply&action=<%=(status.get("ConnectionReplyOpen") ? "close" : "open") %>"></a>
	</div>
	<div class="option_tools_line_odd">
		<span class="option_tools_name_content">粉丝兴趣分析</span>
		<span class="option_tools_cycle_content">手动执行</span>
		<span class="option_tools_status_running option_tools_status_single <%=(status.get("FansAnalyseRuning") ? "option_tools_status_open" : "") %>"><%=(status.get("FansAnalyseRuning") ? "●运行中" : "●未运行") %></span>
		<a class="option_tools_detail" href="#"></a>
		<div class="option_tools_detail_content hidden" style="top: 408px;">根据sina粉丝的跟人信息分析其兴趣点</div>
		<a class="option_tools_action_running option_tools_<%=(status.get("FansAnalyseRuning") ? "hold" : "run") %>" href="/EBP1/analysisAction_executeAnalysis.action?mession=FansAnalyse"></a>
	</div>
	<div class="option_tools_line_even">
		<span class="option_tools_name_content">Topics关键词分析</span>
		<span class="option_tools_cycle_content">手动执行</span>
		<span class="option_tools_status_running option_tools_status_single <%=(status.get("TopicsAnalyseRunning") ? "option_tools_status_open" : "") %>"><%=(status.get("TopicsAnalyseRunning") ? "●运行中" : "●未运行") %></span>
		<a class="option_tools_detail" href="#"></a>
		<div class="option_tools_detail_content hidden" style="top: 450px;">根据connection的topic内容提取其关键词</div>
		<a class="option_tools_action_running option_tools_<%=(status.get("TopicsAnalyseRunning") ? "hold" : "run") %>" href="/EBP1/analysisAction_executeAnalysis.action?mession=TopicsAnalyse"></a>
	</div>
</div>


<script>
	$(document).ready(function(){
		option_tools_page();
	});
</script>