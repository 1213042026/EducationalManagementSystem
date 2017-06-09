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
    
    <title>My JSP 'listManger.jsp' starting page</title>
    
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
	    		<td width="100px">管理员编号</td>
	    		<td width="150px">姓名</td>
	    		<td width="50px">性别</td>
	    		<td width="200px">身份证</td>
	    		<td width="300px">家庭住址</td>
	    		<td width="50px">民族</td>
	    	</tr>
	    	<tr>
	    		<td colspan="6">
	    			<hr/>
	    		</td>
	    	</tr>
	    	<s:iterator value="managerList">
		    	<tr style="color: 793AFF; ">
		    		<td><s:property value="idnum"/></td>
		    		<td><s:property value="name"/></td>
		    		<td><s:property value="sex"/></td>
		    		<td><s:property value="idcard"/></td>
		    		<td><s:property value="address"/></td>
		    		<td><s:property value="nation"/></td>
		    	</tr>
	    		<tr>
	    			<td scope="6" style="padding-bottom: 20px;"></td>
	    		</tr>
	    	</s:iterator>
	    	
	    </table>
    </div>
    
   
  </body>
</html>
