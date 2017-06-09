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
    
    <title>添加修改申请</title>
    
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
			var name = $("#name_temp").val();
		
			var idcard = $("#idcard_temp").val();
		
			
			if( name == null || name == "" ){
				alert("请输入姓名!");
				return false;
			}
			
		
			if( idcard == null || idcard == ""){
				alert("请输入身份证号码！");
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
     		<div style="color: 793AFF; font-size: large"><b>添加申请</b></div>
     	</center>
     	<hr/>
     	<div style="margin-left: 100px;">
     		<form action="teacherAction_insertApplyInfor.action" method="post" style="text-align: center;">
     		
     			<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;修&nbsp;改&nbsp;姓&nbsp;名：</span><input type="text" name="name_temp" id="name_temp"/><br/>
     		<br/>
     			<span>修改身份证号码：</span><input type="text" name="idcard_temp" id="idcard_temp"><br/>
     			<br/>
     			<input type="submit" onclick="return validate();" value="添加"> 
     			
     		</form>
     	</div>
    </div>
  </body>
</html>
