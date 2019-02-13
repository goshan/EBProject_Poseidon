
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
    
    <%@include file="/head.jsp" %>
    
  </head>
  
  <body>
  	<%@include file="/WEB-INF/view/public.jsp" %>
  	
  	<div id="workbench_content">
  		<div id="workbench_nav">
  			<div id="workbench_watch">
  				<div id="workbench_logo">
  					<img alt="logo" src="/EBP1/image/workbench_logo.png"/>
  				</div>
  				<div id="workbench_time">9:45</div>
  				<div id="workbench_date">2013 / 8 / 9</div>
  			</div>
  			<div id="workbench_select">
  				<div id="workbench_library_section" class="workbench_section">
  					<div id="workbench_publish_light" class="workbench_light"></div>
  					<a id="workbench_library" class="workbench_library_bg workbench_single_item" href="/EBP1/communicationAction_showLibraryPage.action">
  						Library
  						<img class="workbench_white_arrow hidden" alt="arrow" src="/EBP1/image/workbenchWhiteArrow.png">
  					</a>
  				</div>
  				<div id="workbench_search_section" class="workbench_section">
  					<div id="workbench_search_light" class="workbench_light"></div>
  					<a id="workbench_search" class="workbench_search_bg workbench_single_item" href="/EBP1/communicationAction_showSearchPage.action">
  						Search
  						<img class="workbench_white_arrow hidden" alt="arrow" src="/EBP1/image/workbenchWhiteArrow.png">
  					</a>
  				</div>
  
  			</div>
  		</div>
  		<div id="workbench_toolbar">
  			<div id="workbench_title">POSEIDON</div>
  			<div id="workbench_profile">
  				<img id="workbench_avatar" src="/EBP1/image/workbenchAvatar.png"/>
  				<%=current_user.getUsername() %>
  				<a id="workbench_operate" href="#">
  					<img src="/EBP1/image/workbenchProfileArrow.png"/>
  				</a>
  			</div>
  			<img src="/EBP1/image/workbenchToolbarShadow.png"/>
  			<div id="workbench_operation_panel" class="hidden">
  				<a id="workbench_setting" href="/EBP1/userAction_setting.action"></a>
  				<a id="workbench_logout" href="/EBP1/userAction_logout.action"></a>
  			</div>
  		</div>
  		<div id="workbench_station_panel">
  		</div>
  	</div>
  </body>
  
  <script>
  	$(document).ready(function(){
  		workbench_page();
  		library_page();
  	});
  </script>
  
</html>
