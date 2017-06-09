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
  
  <body bgcolor="EFF6FF">
    
    <div style="padding-top: 10px; padding-left: 10px;">
     <hr size="5" color="84BDF6">
	    <table>
	    	<tr style="font: bolder;">
	    	
	    		  	
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
	    <hr/>
	    <table align="left" '100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#D7E4F7'>
		<tr>
			<td>&nbsp;<font style='color:#000000'> <a href='teacherAction_displayAlreadyApplyStatus.action?jumpPage=1'>首页</a>
		    </font> | <font style='color:#000000'> <a href="teacherAction_displayAlreadyApplyStatus.action?jumpPage=<s:property value='#request.pageNow-1'/>">前页</a>
		    </font> | <font style='color:#000000'> <a href="teacherAction_displayAlreadyApplyStatus.action?jumpPage=<s:property value='#request.pageNow+1'/>">后页</a>
		    </font> | <font style='color:#000000'> <a href="teacherAction_displayAlreadyApplyStatus.action?jumpPage=<s:property value='#request.pageCount'/>">末页</a>
		    </font> | <b><s:property value='#request.pageNow'/></b>页/<b><s:property value='#request.pageCount'/></b>页 | 共<b><s:property 

value='#request.maxRowCount'/></b>条记录
		</tr>
	</table>
    </div>
    
   
  </body>
</html>
