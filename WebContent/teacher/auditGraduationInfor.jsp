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
    
    <title>审核学生毕业设计申请信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<SCRIPT type="text/javascript">

function auditGraduationInfor(sidnum,gidnum,ggidnum,applyDate){
 // alert(idnum);
 // var userObj = document.getElementById("user");
 // var userV = userObj.innerHTML;
  //alert(userV);  
 // var applyTime = document.getElementById('start');
 // var apply = applyTime.innerHTML;
  //alert(apply);
  
	location="teacherAction_auditGraduation.action?sidnum=" + sidnum +"&gidnum="
	 + gidnum +"&ggidnum=" + ggidnum +"&applyDate=" + applyDate;
  //alert();
}

</SCRIPT>

  </head>
  
  <body bgcolor="EFF6FF">
    
    <div style="padding-top: 10px; padding-left: 10px;">
     <hr size="5" color="84BDF6">
	    <table>
	    	<tr style="font: bolder;">
	    	
	    		<td width="250px">学号</td>
	    	    <td width="250px">专业</td>	   	    	
	    		<td width="350px">申请时间</td>
	    	</tr>
	    	<tr>
	    		<td colspan="3">
	    			<hr/>
	    		</td>
	    	</tr>
	    	<s:iterator value="disAuditGraTit">
		    	<tr style="color: 793AFF; ">
		    		<td id="user"><s:property value="sidnum"/></td>
		    		<td id="user"><s:property value="profession"/></td>
		    		<td id="start"><s:property value="applyDate"/></td>
		    	<td><input type="button" value="审核" onclick="auditGraduationInfor('<s:property value="sidnum"/>','<s:property value="gidnum"/>','<s:property value="ggidnum"/>','<s:property value="applyDate"/>')"/></td>
		    	</tr>
	    		<tr>
	    			<td scope="4" style="padding-bottom: 20px;"></td>
	    		</tr>
	    	</s:iterator>
	    	
	    </table>
    </div>
    
   
  </body>
</html>





