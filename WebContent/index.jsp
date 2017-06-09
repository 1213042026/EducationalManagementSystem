<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!-- 在这里实现了跳转到login登陆窗口 -->
	<meta http-equiv="refresh" content="0;url=login/login.jsp">
	
	
  </head>
  
  <body>
  <!-- 正文没有内容
  	<center>
    This is my JSP page. <br>
    
    <form action="userAction_login.action" method="post" >
    	用户名：<input type="text" id="userId" name="userId" /><br/>
    	密码：<input id="password" name="password" type="password"><br/>
    	<input type="submit" value="提交">
    </form>
    
    <a href="./login/login.jsp" >进入登录界面</a>
  	</center>
  </body> -->
</html>
