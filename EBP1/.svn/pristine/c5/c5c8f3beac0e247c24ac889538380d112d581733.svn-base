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
    
    <title>My JSP 'getPassword.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="js/dijit/themes/claro/claro.css"/>
	<script type="text/javascript" src="js/dojo/dojo.js" data-dojo-config="isDebug:true,parseOnLoad:true"></script>
	<script type="text/javascript">
	dojo.require("dijit.form.Form");
	dojo.require("dijit.form.ValidationTextBox");
	dojo.require("dojox.form.PasswordValidator");
	</script>
	<script type="text/javascript">
		function changeimg(){
			var myimg = document.getElementById("code"); 
			now = new Date(); 
			myimg.src="makeCertPic.jsp?code="+now.getTime();
		} 
	</script>
  </head>
  
  <body>
    <s:form name="myform" action="userAction_findPassword.action" method="post" theme="simple">
                        <table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
                          <tr>
                            <td width="18%" height="38" class="top_hui_text"><span class="login_txt">用户名：&nbsp;&nbsp; </span></td>
                            <td height="38" colspan="2" class="top_hui_text">
                            <s:textfield name="user.username"
			  							 maxlength="16"
			  							 data-dojo-type="dijit.form.ValidationTextBox"
			  							 data-dojo-props="required:true,invlidMessage:'输入错误'"></s:textfield>                         </td>
                          </tr>
                          <tr>
                            <td width="13%" height="35" ><span class="login_txt">验证码：</span></td>
                            <td height="35" colspan="2" class="top_hui_text">
                            <s:textfield name="user.certCode" 
                            			 maxlength="4" 
			  							 data-dojo-props="required:true,invlidMessage:'输入错误'">
                            </s:textfield>
                            <img id="code" src="makeCertPic.jsp"> <A id=LinkButton1 href="javascript:changeimg()">看不清,换一张?</A>
                              </td>
                          </tr>
                          <tr>
                            <td height="35" >&nbsp;</td>
                            <td width="30%" height="35" >
                            <s:submit type="button"  onclick="checkloginform()">确认</s:submit>
							</td>
                            <td width="30%" height="35">
                            <input name="cs" type="button" class="button" id="cs" value="取 消" onClick="showConfirmMsg1()">
                            </td>
                          </tr>
                          
                        </table>
                        <br>
                    </s:form>
  </body>
</html>
