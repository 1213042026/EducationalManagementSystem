<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'disAlreadyReplyMessage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <hr size="5" color="84BDF6">
     	<center>
     		<div style="color: 793AFF; font-size: large"><b>查看留言</b></div>
     	</center>
     	<hr/>
   	<s:iterator value="disMessageVO">
		    	<span style="font-weight:bold;font-size: 17px;font-family:黑体;">学生：<br></span><span style="font-size: 15px; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="sidnum"/></span><br>
		        <span  style="font-weight:bold;font-size: 17px;font-family:黑体;">主题：<br></span><span style="font-size: 15px; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="title"/></span><br>
		        <span  style="font-weight:bold;font-size: 17px;font-family:黑体;">留言内容：<br>
		        </span><span style="font-size: 15px; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="content"/></span><br>
		        <span  style="font-weight:bold;font-size: 17px;font-family:黑体;">留言时间：<br></span><span style="font-size: 15px; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="mtime"/></span><br>
		        <span  style="font-weight:bold;font-size: 17px;font-family:黑体;">回复内容：<br>
		        </span><span style="font-size: 15px; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="rcontent"/></span><br>
		         <span  style="font-weight:bold;font-size: 17px;font-family:黑体;">回复时间：<br></span><span style="font-size: 15px; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="rtime"/></span><br>
		         <hr>
	    	</s:iterator>
	    	<table align="left" '100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#D7E4F7'>
		<tr>
			<td>&nbsp;<font style='color:#000000'> <a  href='teacherAction_disAlreadyReplyMessage.action?jumpPage=1'>首页</a>
		    </font> | <font style='color:#000000'> <a href="teacherAction_disAlreadyReplyMessage.action?jumpPage=<s:property value='#request.pageNow-1'/>">前页</a>
		    </font> | <font style='color:#000000'> <a href="teacherAction_disAlreadyReplyMessage.action?jumpPage=<s:property value='#request.pageNow+1'/>">后页</a>
		    </font> | <font style='color:#000000'> <a href="teacherAction_disAlreadyReplyMessage.action?jumpPage=<s:property value='#request.pageCount'/>">末页</a>
		    </font> | <b><s:property value='#request.pageNow'/></b>页/<b><s:property value='#request.pageCount'/></b>页 | 共<b><s:property 

value='#request.maxRowCount'/></b>条记录
		</tr>
	</table>
	    	
  </body>
</html>

