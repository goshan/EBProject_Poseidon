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
    
    <title>My JSP 'showAdminTransInstitution.jsp' starting page</title>
    
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
      <s:form name="editInstitutionform" action="manageAction_editAdminTransInstitution.action" method="post" theme="simple">
              <TBODY>
                <TR>
                  <TH class=gridViewHeader scope=col>月份</TH>    
                  <TH class=gridViewHeader scope=col>值班人姓名</TH> 
                  <TH class=gridViewHeader scope=col>变更</TH> 
                </TR>
                <c:forEach items="${allAdminInstitution}" var="adminInstitution">
                <TR>     
            	  <TD class=gridViewItem scope="col" align="center">${adminInstitution.month}</TD>  
            	  <TD class=gridViewItem scope="col" align="center">${adminInstitution.focal}</TD>      
                  <TD class=gridViewItem scope="col" align="center"><A href="manageAction_editAdminTransInstitutionById.action?institution_id=${adminInstitution.institution_id}" class=cmdField>变更</A></TD> 
                </TR>
                </c:forEach>
                <tr> 
              	<td colspan ="3">
                 <div align="center">
                         <s:submit type ="button" name="save">整体编辑 
						 </s:submit>
                 </div>
                 </td>
                </tr>
              </TBODY>
              </s:form>
            </TABLE>
    </td>       
  </tr>

  </body>
</html>
