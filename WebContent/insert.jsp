<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'insert.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/Verify.js"></script>

  </head>
  
  <body>
  <center>  

    <form action="managerAction_insert.action" method="post">
    	userName:<input type="text" name="account" id="account" onblur="checkAccount(this)"><br/><span id="div"></span>
    	passWord:<input type="password" name="password" id="password"><br/>
    	<input type="reset" value="reset">&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" value="submt">
    </form>
  </center>
  </body>
</html>
