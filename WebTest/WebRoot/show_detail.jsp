<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="weibo4j.model.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'show_detail.jsp' starting page</title>
    
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
  		User u = (User)request.getAttribute("user");
  		String zombieType = (String)request.getAttribute("ZombieType");
  		Vector<String> words = (Vector<String>)request.getAttribute("KeyWords");
  	 %>
  	<div>
	    <h1 style="font-size:21">
	    	<%=u.getName() %>
	    </h1>
	</div>
    <div>
    	id: <%=u.getId() %>
    </div>
    <div>
    	location: <%=u.getLocation() %>
    </div>
    <div>
    	description: <%=u.getDescription() %>
    </div>
    <div>
    	gender: <%=u.getGender() %>
    </div>
    <div>
    	followers: <%=u.getFollowersCount() %>
    </div>
    <div>
    	followings: <%=u.getFriendsCount() %>
    </div>
    <div>
    	statuses: <%=u.getStatusesCount() %>
    </div>
    <div>
    	favourites: <%=u.getFavouritesCount() %>
    </div>
    <div>
    	zombie: <%=zombieType.equals("0") ? "normal" : "zombie" %>
    </div>
    <div>
    	keywords: <br>
    	<%
    		for (int i=0; i<words.size(); i++){
    			out.println(words.elementAt(i)+"  ");
    		} 
    	%>
    </div>
  </body>
</html>
