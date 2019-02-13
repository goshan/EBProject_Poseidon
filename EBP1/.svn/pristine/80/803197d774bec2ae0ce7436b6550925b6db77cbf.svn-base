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
  </head>
  
  <body>
    This is my index page. <br>
    <table>
    </br>
    <s:form name="publishQuestions" action="publishQuestionsAction_publishQuestions.action" method="post" theme="simple">
    	<br>
    	<s:submit type="button"  onclick = "serviceStart()">向connections发布问题</s:submit>
    </s:form>
    <s:form name="addSource" action="communicationAction_gotoAddPage.action" method="post" theme="simple">
    	<br>
    	<s:submit type="button" >向素材池添加内容</s:submit>
    </s:form>
    </br>
    </br>
    
    <div>
    	<a href ="Communication/releaseAtSource.jsp">@功能测试</a>
	</div>
    </table>
  </body>
</html>
