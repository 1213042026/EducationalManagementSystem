<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>删除所选课程</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		

  </head>
	<body >
         <form action="studentAction_modifyPassword.action" method="post"  style="text-align: center;" >
  
   <span  >旧     密      码  &nbsp;：</span><input type="password" id="prePassword" name="prePassword"/><br/>
			 	 <s:if test='#request.msg=="wrong"'>
												<font size=2   color="red">旧密码错误！</font>
											 </s:if><br/>
			<span style="">新     密      码 &nbsp;：</span><input type="password" class="newPassword" id="newPassword" name="newPassword"/><br/>
   <span style="">确认新密码：</span><input type="password" class="validatePassword" id="validatePassword" name="validatePassword"/><br/><br/>
   <input type="submit" onclick="return validate();"value="修改"/>
   </form>
   
  
</center>


</body>
</html>
