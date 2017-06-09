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
    
    <title>点击审核毕业设计后,返回的页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		span{
		color: 793AFF;
		padding-bottom: 20px;
		font-weight: bold;}
	</style>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		
		function validate(sidnum,gidnum,ggidnum,applyid){
	
		//	var attitudeObj = document.getElementById("attitude");			
			//var attitude = attitudeObj.innerHTML;			
			var att = $("#attitude").text();//可以得到输入域中的内容
			
			//	alert(att);
			
			att   =   att.replace(/\s+/g,""); //去除空格
		//	if( att == null || att == ""){
		
			//	alert("请输入选题申请介绍！");
			//	return false;
			//}
	
		
	//	location="teacher/mainfra.jsp";
			location="teacherAction_updateButtonGraduation.action?sidnum=" + sidnum 
			+"&gidnum=" + gidnum+"&ggidnum=" + ggidnum +"&applyid=" + applyid
			+"&att=" + att+"&diff=" + diff;
			return true;
		}
		
		
		function  teacherAuditGraduationPass(sidnum,gidnum,ggidnum,applyid){
	
			location="teacherAction_teacherAuditGraduationPass.action?sidnum=" + sidnum 
			+"&gidnum=" + gidnum+"&ggidnum=" + ggidnum +"&applyid=" + applyid;
	}
	
		function  teacherAuditGraduationNoPass(sidnum,gidnum,ggidnum,applyid){
		
		var attitudeObj = document.getElementById("attitude");
		var attitude = attitudeObj.innerHTML;  
		//alert(attitude);
		
				location="teacherAction_teacherAuditGraduationNoPass.action?sidnum=" + sidnum 
			+"&gidnum=" + gidnum+"&ggidnum=" + ggidnum +"&applyid=" + applyid
			+"&att=" + attitude;
	}
	</script>
  </head>
  
  <body bgcolor="EFF6FF">
    <div style="padding-top: 10px; padding-left: 10px;">
    	<hr size="5" color="84BDF6">
     	<center>
     		<div style="color: 793AFF; font-size: large"><b>毕业设计自拟题目申请审核界面</b></div>
     	</center>
     	<hr/>
     	<div style="margin-left: 100px;">
     	<!--  	<form action="teacherAction_AddGraduationTitle.action" method="post" >-->
     			
     			<br/>
     			<span>毕业设计名称：</span><input readonly="readonly" type="text"  size="35" name="name" id="name" value="<s:property value="#request.graduationVO.gname"/>" style="background: #F0F0F0;"/><br/><br/>
     			<span>可选人数：</span><input readonly="readonly"  type="text" name="kman" id="kman" value="<s:property value="#request.graduationVO.gcount"/>" style="background: #F0F0F0;"/><br/><br/>
     				<span>难　　度：</span><input readonly="readonly"  type="text" name="kman" id="kman" value="<s:property value="#request.graduationVO.glevel"/>" style="background: #F0F0F0;"/>
				<br/><br/>    					
				
					<span>申请介绍：</span>
					<textarea readonly="readonly"  id ="" name="" rows="5" cols="35"  style="color: red;background: #F0F0F0;">
				<s:property value="#request.graduationVO.remark"/>"
					</textarea>
					<br/>
     			
     			<br/>
     			
     			 
     			<span>审核意见: </span><textarea id ="attitude" name="attitude" rows="3" cols="30" style="color: red;"></textarea><br/><br/>
     			
     			<input type="button"  name="pass" onclick="teacherAuditGraduationPass('<s:property value="#request.sidnum"/>','<s:property value="#request.gidnum"/>','<s:property value="#request.ggidnum"/>','<s:property value="#request.applyDate"/>')" value="通过"> 
     		  <input style="margin-left: 100px" type="button" onclick="teacherAuditGraduationNoPass('<s:property value="#request.sidnum"/>','<s:property value="#request.gidnum"/>','<s:property value="#request.ggidnum"/>','<s:property value="#request.applyDate"/>')" value="不通过">
     			 
     			
     		<!-- </form> -->
     	</div>
    </div>
  </body>
</html>
