<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function serviceStart(){
			alert("服务启动成功！");
		}
	</script>
  </head>
  
  <body>
    This is my index page as a candidate. <br>
    <table>
    <s:form name="usermanageform" action="userAction_getUserCandidateInfo.action" method="post" theme="simple">
    	<s:submit type="button"  >修改个人信息</s:submit>
    </s:form>
    <br>
    <s:form name="addSource" action="communicationAction_gotoAddPage.action" method="post" theme="simple">
    	<br>
    	<s:submit type="button" >向素材池添加内容</s:submit>
    </s:form>
    <!--  
    <s:form name="usermanageform" action="userAction_getUserManageIndex.action" method="post" theme="simple">
    	<br>
    	<s:submit type="button"  >进入用户管理模块</s:submit>
    </s:form>
    <s:form name="manageform" action="manageAction_getAllAcquireInfo.action" method="post" theme="simple">
    	<br>
    	<s:submit type="button"  >进入管理模块</s:submit>
    </s:form>
    <s:form name="sina" action="acquireAction_executeAcquire.action" method="post" theme="simple">
    	<br>
    	<s:submit type="button"  onclick = "serviceStart()">执行获取&分析服务</s:submit>
    </s:form>
    
    <s:form name="connection" action="acquireAction_executeConnectionAcquire.action" method="post" theme="simple">
    	<br>
    	<s:submit type="button"  onclick = "serviceStart()">执行Connection获取&分析服务</s:submit>
    </s:form>
    
    </br>
    </br>
    </br>
    <s:form name="FansPOI" action="analysisAction_executeSinaFansPOIAnalysis.action" method="post" theme="simple">
    	<br>
    	<s:submit type="button"  onclick = "serviceStart()">执行微博分析兴趣点分析服务</s:submit>
    </s:form>
    <s:form name="zombie" action="analysisAction_executeSinaZombieAnalysis.action" method="post" theme="simple">
    	<br>
    	<s:submit type="button"  onclick = "serviceStart()">执行僵尸粉分析服务</s:submit>
    </s:form>
    <s:form name="connectionKeyWords" action="analysisAction_executeConnectionKeyWordsAnalysis.action" method="post" theme="simple">
    	<br>
    	<s:submit type="button"  onclick = "serviceStart()">执行connection关键词分析服务</s:submit>
    </s:form>
    <s:form name="keyWordsMatch" action="analysisAction_executeKeyWordsMatch.action" method="post" theme="simple">
    	<br>
    	<s:submit type="button"  onclick = "serviceStart()">执行整合分析服务</s:submit>
    </s:form>

    </br>
    </br>
    </br>
    <div>
    	<a href ="releaseAction_publishBroadcast.action">微博发布测试</a>
	</div>
	-->
    </table>
  </body>
</html>
