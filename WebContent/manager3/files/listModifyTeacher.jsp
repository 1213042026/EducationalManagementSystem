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
    
    <title>My JSP 'listModifyManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function modifyTeacher( idnum ){
			var truthBeTold = window.confirm("确定要修改此教师吗？"); 
			if (truthBeTold) { 
				location="managerAction_getModifyTeacherInfo.action?idnum=" + idnum;
			}
		}
	</script>
  </head>
  
  <body bgcolor="EFF6FF">
    
    <div style="padding-top: 10px; padding-left: 10px;">
     <hr size="5" color="84BDF6">
	    <table>
	    	<tr style="font: bolder;">
	    		<td width="100px">教师编号</td>
	    		<td width="150px">姓名</td>
	    		<td width="50px">性别</td>
	    		<td width="200px">专业</td>
	    		<td width="200px">身份证</td>
	    		<td width="300px">地址</td>
	    		<td width="50px">民族</td>
	    		<td width="50px">学历</td>
	    			<td width="50px">职称</td>
	    	</tr>
	    	<tr>
	    		<td colspan="8">
	    			<hr/>
	    		</td>
	    	</tr>
	    	<s:iterator value="showTeacherList">
		    	<tr style="color: 793AFF; ">
		    		<td><s:property value="idnum"/></td>
		    		<td><s:property value="name"/></td>
		    		<td><s:property value="sex"/></td>
		    		<td><s:property value="profession"/></td>
		    		<td><s:property value="idcard"/></td>
		    		<td><s:property value="address"/></td>
		    		<td><s:property value="nation"/></td>
		    		<td><s:property value="scientific"/></td>
		    		<td><s:property value="title"/></td>
		    			<td><input type="button" value="修  改" onclick="modifyTeacher('<s:property value="idnum"/>')"/></td>
		    	</tr>
	    		<tr>
	    			<td scope="8" style="padding-bottom: 20px;"></td>
	    		</tr>
	    	</s:iterator>
	    	
	    </table>
	    
	     <hr/>
	    <table align="left" '100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#D7E4F7'>
		<tr>
			<td>&nbsp;<font style='color:#000000'> <a  href='managerAction_listModifyTeacher.action?jumpPage=1'>首页</a>
		    </font> | <font style='color:#000000'> <a href="managerAction_listModifyTeacher.action?jumpPage=<s:property value='#request.pageNow-1'/>">前页</a>
		    </font> | <font style='color:#000000'> <a href="managerAction_listModifyTeacher.action?jumpPage=<s:property value='#request.pageNow+1'/>">后页</a>
		    </font> | <font style='color:#000000'> <a href="managerAction_listModifyTeacher.action?jumpPage=<s:property value='#request.pageCount'/>">末页</a>
		    </font> | <b><s:property value='#request.pageNow'/></b>页/<b><s:property value='#request.pageCount'/></b>页 | 共<b><s:property 

value='#request.maxRowCount'/></b>条记录
		</tr>
	</table>
	    
    </div>
    
   
  </body>
</html>
