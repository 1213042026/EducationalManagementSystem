<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
   	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>教师服务子系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
  <frameset rows="127,*,11" frameborder="no" border="0" framespacing="0">
		<frame src="teacher/top.jsp" name="topFrame" scrolling="no"
			noresize="noresize" id="topFrame" />
		<frame src="teacher/center.jsp" name="mainFrame" id="mainFrame" />
		<frame src="teacher/down.jsp" name="bottomFrame" scrolling="no"
			noresize="noresize" id="bottomFrame" />
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>
