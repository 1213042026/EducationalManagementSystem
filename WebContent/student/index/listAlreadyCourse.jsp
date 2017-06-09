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
    
    <title>显示选修课程界面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body >
    	<div style="padding-top: 10px; padding-left: 10px;">
    	<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
  <tr>
    <td height="24" nowrap><font color="#FFFFFF"><img src="student/iconsIMG/Explain.gif" width="18" height="18" border="0" align="absmiddle">&nbsp;<strong>当前位置：选修课程</strong></font></td>
  </tr>
  <tr>
    <td align="center" nowrap  bgcolor="#EBF2F9">
	
    </td>    
  </tr>
</table>
<br />
     <hr size="5" color="84BDF6">
	     <table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
	
		    <tr>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>课程名字</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>课程类型</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>授课老师</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>选课学期</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9" ><center><font color="#FFFFFF"><strong>选课学年</strong></font></center></td>
		     
		    </tr>
	    
	    
	   
		    <s:iterator  var="s" value="classList">
		     
		        <tr bgcolor="#FFFFFF" onmouseover="this.bgColor='f1f1f1'" onmouseout="this.bgColor='FFFFFF'"  style='cursor:hand'  title="" align="center">
		    		<td><s:property value="#s.className"/>&nbsp;</td>
		    		<td><s:property value="#s.classType"/>&nbsp;</td>
		    		<td><s:property value="#s.teacherId"/>&nbsp;</td>	    	
		    		<td><s:property value="#s.term"/>&nbsp;</td>
		    		<td><s:property value="#s.xn"/>&nbsp;</td>
		    		
		    	</tr>
		    	
	    	</s:iterator>
		 	
	    </table>
	    <br />
    </div>
  </body>
</html>
