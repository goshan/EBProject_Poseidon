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
    
    <title>My JSP 'editAdminTransInstitutionById.jsp' starting page</title>
    
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
<tr>
    <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp管理员轮换制度表</div></td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td valign="top" bgcolor="#F7F8F9">
    <TABLE class=gridView id=ctl00_ContentPlaceHolder2_GridView1 
      style="WIDTH: 60%; BORDER-COLLAPSE: collapse" cellSpacing=0 rules=all 
      border=1 align="center">
  		<s:form name="editInstitutionform" action="manageAction_setAdminTransInstitutionById.action" method="post" theme="simple">
                <TR>
                  <TH class=gridViewHeader scope=col>月份</TH>    
                  <TH class=gridViewHeader scope=col>原有值班人姓名</TH> 
                  <TH class=gridViewHeader scope=col>变更值班人姓名</TH> 
                </TR>
                <TR>   
                  <s:hidden name="adminInstitution.institution_id"></s:hidden>
            	  <TD class=gridViewItem align="center">
            	  <s:textfield name="adminInstitution.month"
									data-dojo-type="dijit.form.ValidationTextBox"
									data-dojo-props="trim:true,required:true,invalidMessage:''">
				  </s:textfield>
				  </TD>  
				  <TD class=gridViewItem align="center">
            	  <s:textfield name="adminInstitution.focal"
									data-dojo-type="dijit.form.ValidationTextBox"
									data-dojo-props="trim:true,required:true,invalidMessage:''">
				  </s:textfield>
				  </TD>
            	  <TD class=gridViewItem align="center">
            	  	<select name="user.username">
       							<option value="">--请选择--</option>
  								<c:forEach items="${allUsers}" var="user">
            	  				<option value="${user.username}" >${user.username}</option>
                				</c:forEach> 
					</select>
            	  </TD>      
                </TR>
                <tr>
                <td colspan ="3" align="center">
                <s:submit type ="button" name="save" >保存
				</s:submit>
				<button type="button" name="cancel" onclick="javascript:window.location.href='manageAction_getAdminTransInstitution.action'">取消</button> 
                </td>
                </tr>
		</s:form>
		</TABLE>
    </td>       
  </tr>
  </body>
</html>
