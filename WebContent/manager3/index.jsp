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
		<title>管理员服务子系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


  </head>
  
 <frameset rows="59,*" cols="*" frameborder="no" border="0"
		framespacing="0">
		<frame src="manager3/files/top.jsp" name="topFrame" scrolling="no"
			noresize="noresize" id="topFrame" title="topFrame" />
		<frameset cols="213,*" frameborder="no" border="0" framespacing="0">
			<frame src="manager3/files/left.jsp" name="leftFrame" scrolling="no"
				noresize="noresize" id="leftFrame" title="leftFrame" />
			<frame src="manager3/files/mainfra.jsp" name="mainFrame" id="mainFrame"
				title="mainFrame" />
		</frameset>
	</frameset>
	<noframes>
		<body>
		</body>
	</noframes>
</html>
