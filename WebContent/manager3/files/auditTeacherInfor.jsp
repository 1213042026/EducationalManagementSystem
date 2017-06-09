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
    
    <title>审核老师请求信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<SCRIPT type="text/javascript">

function auditTeacherInfor(idnum,userV,applyTime){
 // alert(idnum);
 // var userObj = document.getElementById("user");
 // var userV = userObj.innerHTML;
  //alert(userV);  
  //var applyTime = document.getElementById('start');
 // var apply = applyTime.innerHTML;
 // alert(apply);
  
	location="managerAction_auditTeacher.action?idnum=" + idnum +"&user=" + userV +"&applyTime=" + applyTime;
  //alert();
}

</SCRIPT>
  </head>
  
  <body bgcolor="EFF6FF">
    
    <div style="padding-top: 10px; padding-left: 10px;">
     <hr size="5" color="84BDF6">
	    <table>
	    	<tr style="font: bolder;">
	    	
	    		<td width="250px">教师编号</td>	    	
	    		<td width="350px">申请时间</td>
	    	</tr>
	    	<tr>
	    		<td colspan="3">
	    			<hr/>
	    		</td>
	    	</tr>
	    	<s:iterator value="applyModifyList">
		    	<tr style="color: 793AFF; ">
		    		<td id="user"><s:property value="applyUserId"/></td>
		    		<td id="start"><s:property value="applyDate"/></td>
		    	<td><input type="button" value="审核" onclick="auditTeacherInfor('<s:property value="idnum"/>','<s:property value="applyUserId"/>','<s:property value="applyDate"/>')"/></td>
		    	</tr>
	    		<tr>
	    			<td scope="3" style="padding-bottom: 20px;"></td>
	    		</tr>
	    	</s:iterator>
	    	
	    </table>
    </div>
    
   
  </body>
</html>
