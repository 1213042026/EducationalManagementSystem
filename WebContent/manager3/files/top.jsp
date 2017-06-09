<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
<title>管理员管理系统 </title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="manager3/css/css.css" rel="stylesheet" type="text/css" />

  </head>
  
  <body>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="59" background="manager3/images/top.gif"><table width="99%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="1%"><a href="./index.jsp" target="_blank"><img src="manager3/images/logo.gif" width="557" height="59" border="0" /></a></td>
        <td width="64%" align="right" style="font-size:12px;vertical-align:bottom;"> &nbsp;<a target="_blank" href="./index.jsp" style="color:#0099FF;text-decoration:none;"></a></td>
      </tr>
    </table></td>
  </tr>
</table>
  </body>
</html>
