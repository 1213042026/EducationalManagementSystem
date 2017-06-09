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
    
    <title>查询教师审核记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<SCRIPT type="text/javascript">

function auditStudentInfor(idnum){
 // alert(idnum);
  var userObj = document.getElementById("user");
  var userV = userObj.innerHTML;
  //alert(userV);  
  var applyTime = document.getElementById('start');
  var apply = applyTime.innerHTML;
  //alert(apply);
  
	location="managerAction_auditStudent.action?idnum=" + idnum +"&user=" + userV +"&applyTime=" + apply;
  //alert();
}

</SCRIPT>

  </head>
  
  <body bgcolor="EFF6FF">
    
    <div style="padding-top: 10px; padding-left: 10px;">
     <hr size="5" color="84BDF6">
	    <table>
	    	<tr style="font: bolder;">
	    	
	    		<td width="150px">教师编号</td>	    	
	    		<td width="300px">申请时间</td>
	    		<td width="300px">审核时间</td>
	    		<td width="200px">审核人</td>
	    		<td width="200px" >审核结果</td>
	    		<td width="350px">审核意见</td>
	    	</tr>
	    	<tr>
	    		<td colspan="6">
	    			<hr/>
	    		</td>
	    	</tr>
	    	<s:iterator value="applyModifyList">
		    	<tr style="color: 793AFF; ">
		    	<td id="user"><s:property value="applyUserId"/></td>
		    		<td id="start"><s:property value="applyDate"/></td>
		    	<td id="user"><s:property value="auditDate"/></td>
		    		<td id="start"><s:property value="auditMan"/></td>
		    		<td id="user" style="color: red;"><s:property value="modifyResult"/></td>
		    		<td id="start"><s:property value="remark"/></td>
		    	
		    	</tr>
	    		<tr>
	    			<td scope="6" style="padding-bottom: 20px;"></td>
	    		</tr>
	    	</s:iterator>
	    	
	    </table>
    </div>
    
   
  </body>
</html>
