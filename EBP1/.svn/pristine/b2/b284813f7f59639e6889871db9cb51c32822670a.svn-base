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
    This is my index page as a user. <br>
    <table>
    <s:form name="usermanageform" action="userAction_getUserInfo.action" method="post" theme="simple">
    	<s:submit type="button"  >修改个人信息</s:submit>
    </s:form>
    </br>
    <s:form name="addSource" action="communicationAction_gotoAddPage.action" method="post" theme="simple">
    	<br>
    	<s:submit type="button" >向素材池添加内容</s:submit>
    </s:form>
    </table>
  </body>
</html>
