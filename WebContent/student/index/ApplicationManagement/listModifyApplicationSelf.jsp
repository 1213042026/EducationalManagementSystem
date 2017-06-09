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
    
    <title>查询已申请状态</title>
    
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
  
  <body >
    <table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
  <tr>
    <td height="24" nowrap><font color="#FFFFFF">&nbsp;<strong>当前位置：自拟选题审核查询</strong></font></td>
  </tr>
  <tr>
    <td height="24" align="center" nowrap  bgcolor="#EBF2F9">
    </td>    
  </tr>
</table>
<br />
  <center>
  <table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
    <tr align="center">
    	<td nowrap="nowrap" bgcolor="#8DB5E9" ><font color="#FFFFFF"><strong>审核内容</strong></font></td>
  	    <td nowrap="nowrap" bgcolor="#8DB5E9" ><font color="#FFFFFF"><strong>申请时间</strong></font></td>
  	    <td nowrap="nowrap" bgcolor="#8DB5E9" ><font color="#FFFFFF"><strong>审核时间</strong></font></td>
  	    <td nowrap="nowrap" bgcolor="#8DB5E9"><font color="#FFFFFF"><strong>审核人</strong></font></td>
 	    <td nowrap="nowrap" bgcolor="#8DB5E9"><font color="#FFFFFF"><strong>审核结果</strong></font></td>
 	    <td nowrap="nowrap" bgcolor="#8DB5E9"><font color="#FFFFFF"><strong>审核意见</strong></font></td>
    </tr>
	    	
	    		  	
	    	<s:iterator value="applyList">
		    	<tr  align="center" bgcolor="#FFFFFF" onmouseover="this.bgColor='f1f1f1'" onmouseout="this.bgColor='FFFFFF'"  style='cursor:hand'  title="">
		    		<td id="start">自拟选题申请</td>
		    		<td id="start"><s:property value="applyDate"/></td>
		    		<td id="user"><s:property value="auditDate"/></td>
		    		<td id="start"><s:property value="auditMan"/></td>
		    		<td id="user" style="color: red;"><s:property value="modifyResult"/></td>
		    		<td id="start"><s:property value="remarks"/></td>
		    	
		    	</tr>
	    	</s:iterator>
	    	
	    </table>
	    <br/>
 	 </center>
  </body>
</html>
