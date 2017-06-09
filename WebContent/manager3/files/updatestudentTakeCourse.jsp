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

		<title>My JSP 'studentTakeCourse.jsp.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script language="javascript" type="text/javascript"
			src="My97DatePicker/WdatePicker.js"></script>
			<script type="text/javascript">
			alert("修改成功！");
			</script>
	</head>

	<body>
		<div style="padding-top: 10px; padding-left: 10px;">

			<hr size="5" color="84BDF6">
			<center>
				<div style="color: 793AFF; font-size: large">
					<b>学生选课时间设置</b>
				</div>
			</center>
			<hr />



			<form action="managerAction_updateStudentTakeCourse.action" method="post" style="text-align: center;">
				<span>起始时间: </span>
				<input id="start" name="start" type="text" class="Wdate"
				value="<s:property value="#request.studentTakeCourse.start"/>"
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
           &nbsp;&nbsp;  &nbsp;&nbsp; &nbsp;&nbsp;  &nbsp;&nbsp;
				<span>终止时间: </span>
				<input id="end" name="end" type="text" class="Wdate"
				value="<s:property value="#request.studentTakeCourse.end"/>"
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
					 &nbsp;&nbsp;
				<input type="submit" value="修改">
			</form>
		</div>
	</body>
</html>
