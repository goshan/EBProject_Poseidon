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
  				<div id="workbench_publish_section" class="workbench_section">
  					<div id="workbench_publish_light" class="workbench_light"></div>
  					<a id="workbench_publish" class="workbench_publish_bg workbench_root_item" href="#">Publish</a>
  					<img class="workbench_menu_arrow" alt="arrow" src="/EBP1/image/workbenchMenuArrow.png"/>
  					<a id="workbench_publish_connection" class="workbench_publish_connection_bg workbench_final_item" href="/EBP1/communicationAction_publish.action?type=connection">
  						Connection
  						<img class="workbench_white_arrow hidden" alt="arrow" src="/EBP1/image/workbenchWhiteArrow.png">
  					</a>
  					<a id="workbench_publish_libray" class="workbench_publish_libray_bg workbench_final_item"  href="/EBP1/communicationAction_publish.action?type=libary">
  						Library
  						<img class="workbench_white_arrow hidden" alt="arrow" src="/EBP1/image/workbenchWhiteArrow.png">
  					</a>
  				</div>
  				<div id="workbench_reply_section" class="workbench_section">
  					<div id="workbench_reply_light" class="workbench_light"></div>
  					<a id="workbench_reply" class="workbench_reply_bg workbench_root_item" href="#">Reply</a>
  					<img class="workbench_menu_arrow" alt="arrow" src="/EBP1/image/workbenchMenuArrow.png"/>
  					<a id="workbench_reply_weibo" class="workbench_reply_weibo_bg workbench_final_item" href="/EBP1/replyAction_getWeiboReply.action">
  						Weibo
  						<img class="workbench_white_arrow hidden" alt="arrow" src="/EBP1/image/workbenchWhiteArrow.png">
  					</a>
  					<a id="workbench_reply_connection" class="workbench_reply_connection_bg workbench_final_item" href="/EBP1/replyAction_getConnectionReply.action">
  						Connection
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
  				 
  				  
  				<div id="workbench_chart_section" class="workbench_section">
  					<div id="workbench_chart_light" class="workbench_light"></div>
  					<a id="workbench_chart" class="workbench_chart_bg workbench_single_item" href="/EBP1/dataManageAction_showChartPage.action">
  						Chart
  						<img class="workbench_white_arrow hidden" alt="arrow" src="/EBP1/image/workbenchWhiteArrow.png">
  					</a>
  				</div>
  				
  				<div id="workbench_membership_section" class="workbench_section">
  					<div id="workbench_membership_light" class="workbench_light"></div>
  					<a id="workbench_membership" class="workbench_membership_bg workbench_single_item" href="/EBP1/userAction_showMembershipPage.action">
  						Membership
  						<img class="workbench_white_arrow hidden" alt="arrow" src="/EBP1/image/workbenchWhiteArrow.png">
  					</a>
  				</div>
  				
  				<div id="workbench_monitor_section" class="workbench_section">
  					<div id="workbench_monitor_light" class="workbench_light"></div>
  					<a id="workbench_monitor" class="workbench_monitor_bg workbench_single_item" href="/EBP1/communicationAction_showMonitorPage.action">
  						Monitor
  						<img class="workbench_white_arrow hidden" alt="arrow" src="/EBP1/image/workbenchWhiteArrow.png">
  					</a>
  				</div>
  				 
  				<div id="workbench_option_section" class="workbench_section">
  					<div id="workbench_option_light" class="workbench_light"></div>
  					<a id="workbench_option" class="workbench_option_bg workbench_single_item" href="/EBP1/manageAction_showOptionPage.action">
  						Option
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
  		public_page();
  	});
  </script>
  
</html>
