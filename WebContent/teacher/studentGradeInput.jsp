<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'studentGradeInput.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function test(){
	var name=prompt("请录入成绩:");
	alert(name);}
	</script>
 <style type="text/css">
 
 a{
 
 text-decoration:none; 
 }
a:hover { 
color:gray; 
 text-decoration:underline; 
} 


 </style>
  </head>
  
  <body><div style="padding-left: 40%;">
  <br/><br/><br/>
   <a href="teacherAction_studentGradeInput.action">单个录入</a>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <a href="teacher/mode.jsp">批量录入</a>
   <br/><br/>

   </div>
  </body>
</html>
