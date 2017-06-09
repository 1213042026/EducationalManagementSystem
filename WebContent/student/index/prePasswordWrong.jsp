<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'prePasswordWrong.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
function validate(){

  var prePassword = $("#prePassword").val();
  var newPassword = $("#newPassword").val();
  var validatePassword = $("#validatePassword").val();
  

  if(prePassword == null || prePassword==""){
    alert("旧密码不能为空！");
    return false;
  }
  
  if(newPassword == null || newPassword==""){
    alert("新密码不能为空！");
    return false;
  }
  
  if(validatePassword == null || validatePassword =="") {
  　　alert("确定密码不能为空");
  　　return false;
  }
  
  if(newPassword != validatePassword){
    alert("您输入的新密码与确认密码不一致，请重新输入！");
    $(".newPassword").val("");//让这个classs对象的value值为空
    $(".validatePassword").val("");//让这个classs对象的value值为空
    return false;
  }
  return true;
}
</script>
  </head>
  
  <body>
  <form action="studentAction_modifyPassword.action" method="post"  style="text-align: center;">
  
   <span  >旧     密      码：</span><input type="password" id="prePassword" name="prePassword"/><br/>
			 	 <s:if test='#request.msg=="wrong"'>
												<font color="red">旧密码错误！</font>
												 
											 </s:if><br/><br/>
			<span style="">新     密      码：</span><input type="password" class="newPassword" id="newPassword" name="newPassword"/><br/>
   <span style="">确认新密码：</span><input type="password" class="validatePassword" id="validatePassword" name="validatePassword"/><br/><br/>
   <input type="submit" onclick="return validate();"value="修改"/>
   </form>
  </body>
</html>