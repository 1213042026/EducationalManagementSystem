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
    
    <title>My JSP 'listStudent.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor="EFF6FF">
    
    <div style="padding-top: 10px; padding-left: 10px;">
     <hr size="5" color="84BDF6">
	    <table>
	    	<tr style="font: bolder;">
	    		<td width="100px">学号</td>
	    		<td width="150px">姓名</td>
	    		<td width="70px">性别</td>
	    		<td width="200px">身份证</td>
	    		<td width="350px">专业</td>
	    		<td width="300px">家庭住址</td>
	    		<td width="100px">民族</td>
	    		<td width="100px">入学年份</td>
	    	</tr>
	    	<tr>
	    		<td colspan="8">
	    			<hr/>
	    		</td>
	    	</tr>
	    	
		    	<tr style="color: 793AFF; ">
		    		<td><s:property value="#request.studentVO.idnum"/></td>
		    		<td><s:property value="#request.studentVO.name"/></td>
		    		<td><s:property value="#request.studentVO.sex"/></td>
		    		<td><s:property value="#request.studentVO.idcard"/></td>
		    		<td><s:property value="#request.studentVO.prof_Name"/></td>
		    		<td><s:property value="#request.studentVO.address"/></td>
		    		<td><s:property value="#request.studentVO.nation"/></td>
		    		<td><s:property value="#request.studentVO.entranceDate"/></td>
		    	</tr>
	    		<tr>
	    			<td scope="8" style="padding-bottom: 20px;"></td>
	    		</tr>
	    	
	    	
	    </table>
    </div>
    
   
  </body>
</html>
