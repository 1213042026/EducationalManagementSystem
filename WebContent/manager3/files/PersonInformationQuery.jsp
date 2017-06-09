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
    
    <title>查看个人信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		span{
		color: 793AFF;
		padding-bottom: 20px;
		font-weight: bold;}
	</style>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		
		function validate(){
			
			var address = $("#address").val();

			if( address == null || address == "" ){
				alert("教师地址不能为空！");
				return false;
			}
	
			return true;
		}
	</script>
  </head>
  
  <body bgcolor="EFF6FF">
    <div style="padding-top: 10px; padding-left: 10px;">
    	<hr size="5" color="84BDF6">
     	<center>
     		<div style="color: 793AFF; font-size: large"><b>个人信息查询</b></div>
     	</center>
     	<hr/>
     	<div style="margin-left: 100px;">
     		<form action="teacherAction_updateTeacherPersonInfor.action" method="post">
     	<table  align="center" border="1">
     			<tr><td>管理员编号：</td><td><s:property value='#request.managerVO.idnum'/>&nbsp;</td></tr>
     		    <tr><td>管理员姓名：</td><td><s:property value='#request.managerVO.name'/>&nbsp;</td></tr>
     		<tr><td>性               别：</td><td><s:property value='#request.managerVO.sex'/>&nbsp;</td></tr>
     			<tr><td>身份证号码：</td><td ><s:property value='#request.managerVO.idcard'/>&nbsp;</td></tr>
     			<tr><td>地　　　 址:</td><td> <s:property value='#request.managerVO.address'/>&nbsp;</td></tr>
     			<tr><td>民               族:</td><td><s:property value='#request.managerVO.nation'/>&nbsp;</td></tr>
     			</table>
     		     		
     		</form>
     	</div>
    </div>
  </body>
</html>
