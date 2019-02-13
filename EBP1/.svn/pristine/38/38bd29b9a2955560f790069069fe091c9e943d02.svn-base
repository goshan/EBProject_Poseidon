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
  	<div id="bg_1" class="bg" style="background: url('/EBP1/image/bg_1.png') no-repeat center"></div>
  	<div id="bg_2" class="bg hidden" style="background: url('/EBP1/image/bg_2.png') no-repeat center"></div>
  	<div id="bg_3" class="bg hidden" style="background: url('/EBP1/image/bg_3.png') no-repeat center"></div>
  	<div id="bg_4" class="bg hidden" style="background: url('/EBP1/image/bg_4.png') no-repeat center"></div>
  	<div id="bg_5" class="bg hidden" style="background: url('/EBP1/image/bg_5.png') no-repeat center"></div>
  	<div id="panel">
  		<div id="content">
  			<div id="logo">
  				<img src="image/logo.png" alt="logo"/>
  			</div>
  			<div id="intro">
  				<div>Link all</div>
  				<div style="margin: 10px 0 0 165px">Start now</div>
  			</div>
  			<div id="logo_big">
  				<img src="image/logoBig.png">
  			</div>
  			<div id="line"></div>
  			<img id="loading" class="hidden" src="image/loading.png"/>
  			<a id="ibm_logo" class="box viewport-flip" href="#">
  				<img class="list flip out" src="image/ibmButton_on.png"/>
  				<img class="list flip" src="image/ibmButton.png"/>
  			</a>
  			<div id="login_form" style="opacity: 1; right: 7px; z-index: 10">
  				<div id="login_error_tip" class="opacity">no message to show</div>
  				<div class="switch">
  					<span id="login_label" class="switch_label">Login</span>
  					<a class="switch_button" href="#">
  						Sign-up
  						<img src="image/smallArrow.png"/>
  					</a>
  				</div>
  				<div class="login_form_line">
  					<input type="text" id="login_username_input" class="login_input" placeholder="Username"></input>
  					<img id="login_username_empty" class="input_error hidden" src="image/loginTooltip.png"/>
  					<img id="login_username_error" class="input_error hidden" src="image/loginErrorTooltip.png"/>
  				</div>
  				<div class="login_form_line">
  					<input type="password" id="login_password_input" class="login_input" placeholder="Password"></input>
  					<img id="login_password_empty" class="input_error hidden" src="image/loginTooltip.png"/>
  					<img id="login_password_error" class="input_error hidden" src="image/loginErrorTooltip.png"/>
  				</div>
  				<a id="login_submit" href="#"></a>
  			</div>
  			<div id="signup_form" style="opacity: 0; right: -43px; z-index: 1">
  				<div id="signup_error_tip" class="opacity">no message to show</div>
  				<div class="switch">
  					<span id="signup_label" class="switch_label">Sign-up</span>
  					<a class="switch_button" href="#">
  						Login
  						<img src="image/smallArrow.png"/>
  					</a>
  				</div>
				<div class="signup_form_line">
					<input type="text" id="signup_username_input" class="signup_input" placeholder="Username"></input>
					<img id="signup_username_empty" class="input_error hidden empty" src="image/signupTooltip.png"/>
					<img id="signup_username_error" class="input_error hidden error" src="image/signupErrorTooltip.png"/>
				</div>
				<div class="signup_form_line">
					<input type="password" id="signup_password_input" class="signup_input" placeholder="Password"></input>
					<img id="signup_password_empty" class="input_error hidden empty" src="image/signupTooltip.png"/>
					<img id="signup_password_error" class="input_error hidden error" src="image/signupErrorTooltip.png"/>
				</div>
				<div class="signup_form_line">
					<input type="password" id="signup_confirm_password_input" class="signup_input" placeholder="Confirm Password"></input>
					<img id="signup_confirm_password_empty" class="input_error hidden empty" src="image/signupTooltip.png"/>
				</div>
				<div class="signup_form_line">
					<input type="text" id="signup_realname_input" class="signup_input" placeholder="Realname"></input>
					<img id="signup_realname_empty" class="input_error hidden empty" src="image/signupTooltip.png"/>
				</div>
				<div class="signup_form_line">
					<input type="text" id="signup_email_input" class="signup_input" placeholder="E-mail"></input>
					<img id="signup_email_empty" class="input_error hidden empty" src="image/signupTooltip.png"/>
				</div>
				<div class="signup_form_line">
					<input type="text" id="signup_info_input" class="signup_input" placeholder="Comment"></input>
				</div>
				<a id="signup_submit" href="#"></a>
  			</div>
  		</div>
  	</div>
  	<div id="clock_bg">
  		<div id="clock_content">
  			<div id="date" class="clock">08/02/2013</div>
  			<div id="time" class="clock">01:51</div>
  		</div>
  	</div>
  </body>
  
  <script>
  	$(document).ready(function(){
  		login_page();
  	});
  </script>
  
</html>
