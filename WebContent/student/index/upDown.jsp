<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">

	
		<title>管理员管理子系统</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 1px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #4AA3D8;
}

html {
	overflow-x: ;
	overflow-y: ;
	border: 0;
}
-->
</style>

	</head>

   <FRAMESET frameSpacing=0 rows=50%,50% frameBorder=0 >
		<FRAME name=up src="studentAction_listCourse.action" frameBorder=0 noResize
			scrolling="yes">
		<FRAME name=down src="studentAction_listDownCourse.action" frameBorder=0 noResize
			scrolling="yes">
		<NOFRAMES>
			<p>
				This page requires frames, but your browser does not support them.
			</p>
		</NOFRAMES>
	</FRAMESET>
</html>
