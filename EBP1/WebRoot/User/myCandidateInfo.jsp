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
    
    <title>My JSP 'myInfo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function checkPassword(){
			var password = document.getElementById("user.newPassword").value; 
			var confirmPassword = document.getElementById("user.confirmPassword").value; 
			//alert("password:"+password+"confirmPassword:"+confirmPassword);
			if(password != confirmPassword){
				alert("密码与确认密码不匹配，请重新输入！");
			}  
			var fm = document.updateUserform;
  			fm.action="userAction_updateUserCandidate.action";
  			fm.submit();
			
		} 
	</script>
  </head>
  
  <body>
   <s:form name="updateUserform" data-dojo-type="dijit.form.Form" action="userAction_updateUserCandidate.action" method="post" theme="simple">
    	<br>
    	<table>
						<s:hidden name="user.user_id"></s:hidden>
						<tr>
							<td>
							<label for="username">用户名：</label>
							</td>
							<td>
							<s:textfield name="user.username"
									data-dojo-type="dijit.form.ValidationTextBox"
									placeholder="例:王爽"
									data-dojo-props="trim:true,required:true,invalidMessage:'输入错误哦，亲~ 例:王爽'"></s:textfield>
							</td>
						</tr>
						<tr>
						
							<td>
							<label for="realname">真实姓名：</label>
							</td>
							<td>
							<s:textfield name="user.realname"
									data-dojo-type="dijit.form.ValidationTextBox"
									placeholder="例:王土鳖"
									data-dojo-props="trim:true,required:true,invalidMessage:'输入错误哦，亲~ 例:王土鳖'"></s:textfield>
							</td>						
						</tr>
						<tr>
							<td>
							<label for="password">旧密码：</label>
							</td>
							<td>
							<s:textfield name="user.password"
									data-dojo-type="dijit.form.ValidationTextBox"
									placeholder="例:IBM1234"
									data-dojo-props="trim:true,required:true,invalidMessage:'输入错误哦，亲~ 例:IBM1234'"></s:textfield>
							</td>						
						</tr>	
						<tr>
							<td>
							<label for="password">新密码：</label>
							</td>
							<td>
							<s:textfield id ="user.newPassword"
									name="user.newPassword"
									data-dojo-type="dijit.form.ValidationTextBox"
									placeholder="例:IBM1234"
									data-dojo-props="trim:true,required:true,invalidMessage:'输入错误哦，亲~ 例:IBM1234'"></s:textfield>
							</td>						
						</tr>
						<tr>
							<td>
							<label for="password">确认密码：</label>
							</td>
							<td>
							<s:textfield id ="user.confirmPassword"
									name="user.confirmPassword"
									data-dojo-type="dijit.form.ValidationTextBox"
									placeholder="例:IBM1234"
									data-dojo-props="trim:true,required:true,invalidMessage:'输入错误哦，亲~ 例:IBM1234'"></s:textfield>
							</td>						
						</tr>
						<tr>
						
							<td>
							<label for="Email">Email：</label>
							</td>
							<td>
							<s:textfield name="user.email"
									data-dojo-type="dijit.form.ValidationTextBox"
									placeholder="例:abc@cn.ibm.com"
									data-dojo-props="trim:true,required:true,invalidMessage:'输入错误哦，亲~ 例:abc@cn.ibm.com'"></s:textfield>
							</td>						
						</tr>
						<tr>
						
							<td>
							<label for="user_info">备注：</label>
							</td>
							<td>
							<s:textfield name="user.user_info"
									data-dojo-type="dijit.form.ValidationTextBox"					
									data-dojo-props="trim:true,required:true,invalidMessage:''"></s:textfield>
							</td>						
						</tr>


						<tr>
						
        					<td colspan="2" valign="top">&nbsp;</td>
        					<td>&nbsp;</td>
        					<td valign="top">&nbsp;</td>
      					</tr>
						<tr>	
							    <td>&nbsp;</td>
							    <td>
								<div class="butarea2"></div>
								<s:submit type ="button" name="update" onclick="checkPassword()">	
									保存
								</s:submit>
								<button type="button" name="back" onclick="javascript:window.location.href='candidateIndex.jsp'">撤销修改</button> 
								<button type="button" name="cancel" onclick="javascript:window.location.href='candidateIndex.jsp'">取消</button> 
								</td>
						</tr>	
						</table>
    	
    </s:form>
  </body>
</html>
