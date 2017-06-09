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
    
    <title>My JSP 'addManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		span{
		color: 793AFF;
		padding-bottom: 20px;
		font-weight: bold;}
	</style>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		
		function validate(){
			var name = $("#name").val();
			var pass =  $("#password").val() ;
			var repass = $("#repassword").val();
			var idcard = $("#idcard").val();
			var address = $("#address").val();
			var nation = $("#nation").val();
			
			if( name == null || name == "" ){
				alert("请输入管理员姓名!");
				return false;
			}
			
			if( pass == null || pass == ""){
				alert("请输入密码！");
				return false;
			}
			if( repass != pass ){
				alert("输入密码与确认密码不一致，请重新输入");
				$(".password").val("");
				$(".repassword").val("");
				return false;
			}
			if( idcard == null || idcard == ""){
				alert("请输入身份证号码！");
				return false;
			}
			if( address == null || address == "" ){
				alert("请输入管理员住址！");
				return false;
			}
			if( nation == null || nation == "" ){
				alert("请输入管理员民族！");
				return false;
			}
			return true;
		}
	</script>
  </head>
  
  <body bgcolor="EFF6FF">
    <div>
    	<hr size="5" color="84BDF6">
     	<center>
     		<div style="color: 793AFF; font-size: large"><b>添加管理员</b></div>
     	</center>
     	<hr/>
     	<div style="margin-left: 100px;">
     		<form action="managerAction_insert.action" method="post">
     			<span>管理员编号：</span><input type="text" readonly="readonly" name="iDnum" id="iDnum" value="<s:property value="#request.maxIdnum"/>" style="background: gray;"/><br/>
     			<span>管理员姓名：</span><input type="text" name="name" id="name"/><br/>
     			<span>管理员密码：</span><input class="password" type="password" name="password" id="password"><br/>
     			<span>确认     密码：</span><input class="repassword" type="password" name="repassword" id="repassword"><br/>
     			<span>性               别：</span><select name="sex" id="sex"><option value="1" selected="selected">女</option><option value="2">男</option></select><br/>
     			<span>身份证号码：</span><input type="text" name="idcard" id="idcard"><br/>
     			<span>家  庭  住 址: </span><input type="text" name="address" id="address"><br/>
     			<span>民               族: </span><input type="text" name="nation" id="nation"><br/>
     			
     			<input type="submit" onclick="return validate();" value="添加">  <input style="margin-left: 100px" type="reset" value="重置">
     			
     		</form>
     	</div>
    </div>
  </body>
</html>
