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
    
    <title>My JSP 'auditStudentView.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<style type="text/css">
		span{
		color: 793AFF;
		padding-bottom: 20px;
		font-weight: bold;}
	</style>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		
		function validate(){
			var name = $("#name").val();
			var pass =  $("#password").val() ;
			var repass = $("#repassword").val();
			var idcard = $("#idcard").val();
			var address = $("#address").val();
			var nation = $("#nation").val();
			
			if( name == null || name == "" ){
				alert("请输入管理员姓名!");
				return false;
			}
			
			if( pass == null || pass == ""){
				alert("请输入密码！");
				return false;
			}
			if( repass != pass ){
				alert("输入密码与确认密码不一致，请重新输入");
				$(".password").val("");
				$(".repassword").val("");
				return false;
			}
			if( idcard == null || idcard == ""){
				alert("请输入身份证号码！");
				return false;
			}
			if( address == null || address == "" ){
				alert("请输入管理员住址！");
				return false;
			}
			if( nation == null || nation == "" ){
				alert("请输入管理员民族！");
				return false;
			}
			return true;
		}
		
		function  managerAuditStudentPass(){
		var studentObj = document.getElementById("studentid");
		var studentid = studentObj.value;//学号
		//alert(studentid);
		var nameObj = document.getElementById("name");
		var name = nameObj.value;
		//alert(name);
		var username_tempObj = document.getElementById("username_temp");
		var username_temp = username_tempObj.value;  
		//alert(username_temp);
		var idcardObj = document.getElementById("idcard");
		var idcard = idcardObj.value;  
		//alert(idcard);
		var idcard_tempObj = document.getElementById("idcard_temp");
		var idcard_temp = idcard_tempObj.value;  
		//alert(idcard_temp);
		var applyidObj = document.getElementById("applyid");
		var applyid = applyidObj.value;  
		//alert(applyid);
		
			location="managerAction_managerAuditStudentPass.action?studentid="
			 + studentid +"&username_temp=" + username_temp +"&idcard_temp=" + idcard_temp
			 +"&applyid=" + applyid;
	}
	
		function  managerAuditStudentNoPass(){
		var studentObj = document.getElementById("studentid");
		var studentid = studentObj.value;//学号
	//	alert(studentid);
		var nameObj = document.getElementById("name");
		var name = nameObj.value;
	//	alert(name);
		var username_tempObj = document.getElementById("username_temp");
		var username_temp = username_tempObj.value;  
		//alert(username_temp);
		var idcardObj = document.getElementById("idcard");
		var idcard = idcardObj.value;  
		//alert(idcard);
		var idcard_tempObj = document.getElementById("idcard_temp");
		var idcard_temp = idcard_tempObj.value;  
		//alert(idcard_temp);
		var applyidObj = document.getElementById("applyid");
		var applyid = applyidObj.value;  
		//alert(applyid);
		var attitudeObj = document.getElementById("attitude");
		var attitude = attitudeObj.innerHTML;  
		//alert(attitude);
		
			location="managerAction_managerAuditStudentNoPass.action?studentid="
			 + studentid +"&applyid=" + applyid+"&attitude=" + attitude;
	}
	</script>

  </head>
  
   
  <body bgcolor="EFF6FF">
    <div>
    	<hr size="5" color="84BDF6">
     	<center>
     		<div style="color: 793AFF; font-size: large"><b>学生审核界面</b></div>
     	</center>
     	<hr/>
     	<!--  value="<s:property value="#request.maxIdnum"/>" style="background: gray;" -->
     	<div style="margin-left: 100px;">
     	<!-- 	<form action="managerAction_managerAuditStudent.action" method="post"> -->
     			<span>学号：</span><input id="studentid" type="text" readonly="readonly" name="studentid"  value="<s:property value="#request.studentvo.idnum"/>" style="background: gray;"/><br/>
     			<span>原姓名：</span><input id="name" type="text" readonly="readonly" name="name"  value="<s:property value="#request.studentvo.name"/>" style="background:#D1D1D1;"/><br/>
     			<span>修改后的姓名：</span><input id="username_temp" readonly="readonly" type="text" name="username_temp"  value="<s:property value="#request.studentvo.username_temp"/>" style="background:#F0F0F0;"><br/>
     			<span>原身份证号码：</span><input  id="idcard" readonly="readonly" type="text" name="idcard"  value="<s:property value="#request.studentvo.idcard"/>" style="background:#D1D1D1;"/><br/>
     			<span>修改后的身份证号码：</span><input  id="idcard_temp" readonly="readonly" type="text" name="idcard_temp"  value="<s:property value="#request.studentvo.idcrad_temp"/>" style="background:#F0F0F0;"/><br/>     			
     			<input type="hidden" name="applyid" id="applyid" value="<s:property value="#request.applyid"/>">
     			
     			<span>审核意见: </span><textarea id ="attitude" name="attitude" rows="3" cols="30" style="color: red;"></textarea><br/><br/>
     			
     			<input type="button"  name="pass" onclick="managerAuditStudentPass()" value="通过">  <input style="margin-left: 100px" type="button" onclick="managerAuditStudentNoPass()" value="不通过">
     			
     	<!-- 	</form> -->
     	</div>
    </div>
  </body>
</html>
