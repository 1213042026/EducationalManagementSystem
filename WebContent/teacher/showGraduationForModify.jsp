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
    
    <title>添加毕业设计</title>
    
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
		
		function validate(){
		  var idnum = $("#idnum").val();			
			var name = $("#name").val();
			var kman = $("#kman").val();			
			var t_name =  $("#t_name").val() ;
			var diff =  $("#diff").val() ;
		//	var attitudeObj = document.getElementById("attitude");			
			//var attitude = attitudeObj.innerHTML;			
			var att = $("#attitude").text();//可以得到输入域中的内容
			
			//	alert(att);
			if( name == null || name == "" ){
			
				alert("请输入毕业设计名称!");
				return false;
			}
			if( kman == null || kman == "" ){
			
				alert("请输入毕业设计名称!");
				return false;
			}
			att   =   att.replace(/\s+/g,""); //去除空格
			if( att == null || att == ""){
		
				alert("请输入选题须知！");
				return false;
			}
	
		
	//	location="teacher/mainfra.jsp";
			location="teacherAction_updateButtonGraduation.action?idnum=" + idnum +"&name=" + name+"&kman=" + kman +"&t_name=" + t_name+"&att=" + att+"&diff=" + diff;
			return true;
		}
	</script>
  </head>
  
  <body bgcolor="EFF6FF">
    <div style="padding-top: 10px; padding-left: 10px;">
    	<hr size="5" color="84BDF6">
     	<center>
     		<div style="color: 793AFF; font-size: large"><b>修改毕业设计</b></div>
     	</center>
     	<hr/>
     	<div style="margin-left: 100px;">
     	<!--  	<form action="teacherAction_AddGraduationTitle.action" method="post" >-->
     			<span>自动编号：</span><input type="text" readonly="readonly" name="idnum" id="idnum" style="background: gray;" value="<s:property value="#request.graduationVO.idnum"/>"/><br/>
     			<br/>
     			<span>毕业设计名称：</span><input type="text"  size="35" name="name" id="name" value="<s:property value="#request.graduationVO.gname"/>"/><br/><br/>
     			<span>可选人数：</span><input type="text" name="kman" id="kman" value="<s:property value="#request.graduationVO.gcount"/>"/><br/><br/>
     				<span>难　　度：</span><select name="diff" id="diff">
					<option  value="容易" selected="selected">
						容易
					</option>
					<option value="一般">
						一般
					</option>
					<option value="困难">
						困难
					</option>
				</select>
				<br/><br/>
     					
				
					<span>选题须知：</span>
					<textarea id ="attitude" name="attitude" rows="5" cols="35"  style="color: red;">
				<s:property value="#request.graduationVO.remark"/>"
					</textarea>
					<br/>
     			
     			<br/>
     			
     			<input style="margin-left: 170px;" type="button" onclick="validate();" value="修改"> 
     			
     		<!-- </form> -->
     	</div>
    </div>
  </body>
</html>


