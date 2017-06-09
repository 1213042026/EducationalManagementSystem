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
     			<tr><td>教师编号：</td><td><input type="text" readonly="readonly" name="idnum" id="idnum" value="<s:property value='#request.teacherVO.idnum'/>" style="background: gray;"/></td></tr>
     		    <tr><td>教师姓名：</td><td><input type="text" style="background: gray;" readonly="readonly" name="name" id="name" value="<s:property value='#request.teacherVO.name'/>"/></td></tr>
     		<tr><td>性               别：</td><td><input type="text" style="background: gray;" readonly="readonly" name="sex" id="sex" value="<s:property value='#request.teacherVO.sex'/>"/></td></tr>
     		<tr><td>专　　　业:</td><td><input type="text" style="background: gray;" readonly="readonly" name="name" id="name" value="<s:property value='#request.profession'/>"/></td></tr>
     			<tr><td>身份证号码：</td><td ><input type="text" style="background: gray;" readonly="readonly" name="idcard" id="idcard" value="<s:property value='#request.teacherVO.idcard'/>"/></td></tr>
     			<tr><td>地　　　 址 :</td><td><input type="text" name="address" id="address"value="<s:property value='#request.teacherVO.address'/>"/>	<input type="submit" onclick="return validate();" value="修改"> </td></tr>
     			<tr><td>民               族:</td><td><input type="text" style="background: gray;" readonly="readonly" name="nation" id="nation"value="<s:property value='#request.teacherVO.nation'/>"/></td></tr>
     			<tr><td>学　　　历:</td><td><input type="text"  style="background: gray;" readonly="readonly" name="scientific" id="scientific" value="<s:property value='#request.teacherVO.scientific'/>"/></td></tr>
     			<tr><td>职　　　称:</td><td><input type="text"  style="background: gray;" readonly="readonly" name="title" id="title"value="<s:property value='#request.teacherVO.title'/>"/></td></tr>
     			</table>
     		     		
     		</form>
     	</div>
    </div>
  </body>
</html>
