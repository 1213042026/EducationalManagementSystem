<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title>学生服务子系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<META content="MSHTML 6.00.2900.3492" name=GENERATOR>

  </head>
  

   <FRAMESET frameSpacing=0 rows=80,* frameBorder=0>
		<FRAME name=top src="student/index/YHTop.jsp" frameBorder=0 noResize
			scrolling=no>
		<FRAMESET frameSpacing=0 frameBorder=0 cols=220,*>
			<FRAME name=menu src="student/index/YHMenu.jsp" frameBorder=0 noResize>
			<FRAME name=dmMain src="student/index/mainfra.jsp" frameBorder=0>
		</FRAMESET>
		<NOFRAMES>
			<p>
				This page requires frames, but your browser does not support them.
			</p>
		</NOFRAMES>
	</FRAMESET>
 
</html>
