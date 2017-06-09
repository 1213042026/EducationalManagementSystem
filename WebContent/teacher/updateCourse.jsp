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
    
    <title>修改课程</title>
    
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
			var name = $("#name").val();
			var grade =  $("#grade").val() ;
			
			var kxPeople = $("#kxPeople").val();
		
			if( name == null || name == "" ){
				alert("请输入课程名称");
				return false;
			}
			if( grade == null || grade == ""){
				alert("请输入年级！");
				return false;
			}
			
			if( kxPeople == null || kxPeople == ""){
				alert("请输入可选人数！");
				return false;
			}
			
			return true;
		}
	</script>
  </head>
  
  <body bgcolor="EFF6FF">
    <div style="padding-top: 10px; padding-left: 10px;">
    	<hr size="5" color="84BDF6">
     	<center>
     		<div style="color: 793AFF; font-size: large"><b>添加课程</b></div>
     	</center>
     	<hr/>
     	<div style="margin-left: 100px;">
     		<form action="teacherAction_updateCourseButton.action" method="post">
     			<span>课程编号：</span><input readonly="readonly" type="text"  name="idnum" id="idnum" value="<s:property value='#request.courseClass.classId' />" style="background: gray;"/><br/>
     			<span>课程名称：</span><input type="text" value="<s:property value='#request.courseClass.className' />"  name="name" id="name"/><br/>
     			<span>课程类型：</span><select name="courseType" id="courseType">
						<s:iterator value="courseTypeLit">
							<option value="<s:property value='idnum' />"><s:property value="classtypename" /></option>
						</s:iterator>
					</select>
					<br/>	<br/>
					<!-- 			
				<span>学	期：</span><SELECT name="term" id="term" >
				<option value="1">上学期</option>
				<option value="2">下学期</option>
				</SELECT>	
					</br></br>
					 -->
						<span>年	级：</span><input type="text" name="grade" id="grade" value="<s:property value='#request.courseClass.grade' />"/><br/>
						<br/>
						<span>专业：</span><select name="profession" id="profession">
						<s:iterator value="professionList">
							<option value="<s:property value='idnum' />"><s:property value="pro_name" /></option>
						</s:iterator>
					</select>
					<br/></br>
					<span>可选人数：</span><input type="text" name="kxPeople" id="kxPeople" value="<s:property value='#request.KXRS' />"><br/>
     			<span>备	注: </span><input type="text" name="remark" id="remark" value="<s:property value='#request.courseClass.remark' />" ><br/>
     			<br/>
     			
     			<input type="submit" onclick="return validate();" value="修改"> 
     			
     		</form>
     	</div>
    </div>
  </body>
</html>
