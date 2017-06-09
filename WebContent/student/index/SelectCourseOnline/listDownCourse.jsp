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
	<script type="text/javascript">
	
	function deleteCourse(classId)
	{
		var truthBeTold = window.confirm("确定要取消选择此课程吗？");
		if (truthBeTold) { 
			location="studentAction_deleteCourse.action?classId=" +classId;
		}
	
	}
	</script>

  </head>
  
  <body >
   <div style="display:block;"><a  id='tz' href='studentAction_listCourse.action' target='up'></a></div>
  <s:if test="#request.DEL!=null">
    <script>
      document.getElementById('tz').click();
    </script>
  </s:if>
         已选课程
  	<hr size="3" color="84BDF6">
	     <table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
	
		    <tr>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>课程编号</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>课程名字</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>课程类型</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>授课老师</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>选课学期</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9" ><center><font color="#FFFFFF"><strong>取消选课</strong></font></center></td>
		     
		    </tr>
	    
	    
	   
		    <s:iterator  var="s" value="classList">
		     
		        <tr bgcolor="#FFFFFF" onmouseover="this.bgColor='f1f1f1'" onmouseout="this.bgColor='FFFFFF'"  style='cursor:hand'  title="" align="center">
		    		<td><s:property value="#s.classId" />&nbsp;</td>
		    		<td><s:property value="#s.className"/>&nbsp;</td>
		    		<td><s:property value="#s.classType"/>&nbsp;</td>
		    		<td><s:property value="#s.teacherId"/>&nbsp;</td>	    	
		    		<td><s:property value="#s.term"/>&nbsp;</td>
		    		<td><input type="button" value="删  除" onclick="deleteCourse('<s:property value="#s.classId"/>')"/>
		    	</tr>
		    	
	    	</s:iterator>
		 	
	    </table>
	    <br />
    </div>
  </body>
</html>
