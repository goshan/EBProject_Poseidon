<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="weibo4j.model.User" import="weibo4j.model.UserWapper" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'show_fans.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<%
  		UserWapper users = (UserWapper)request.getAttribute("users");
  		for (User u : users.getUsers()){
  			out.println("<a href=\"/WebTest/showDetail?wid="+u.getId()+"\">"+u.getName()+"</a><br>");
  		}
  	 %>
  </body>
</html>
