<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>选择毕业设计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  
  <body bgcolor="EFF6FF">
<form action="studentAction_selectGraduationProject.action" method="post" style="text-align: center;">
    <p></p>
    <P></P>
          <p align="center"> 你已经选择了毕业设计，如果想重新选择请先删除原来的！</p>
          <br/>
             <input type="submit" value="返回"/>
      </form>    
          
   　　　
  </body>
</html>
