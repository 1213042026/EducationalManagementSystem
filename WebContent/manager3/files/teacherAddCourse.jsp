<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'teacherAddCourse.jsp' starting page</title>

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

		<SCRIPT type="text/javascript">
function change(idnum){
			//alert("test");
				location="managerAction_teacherAddCourse.action?idnum=" + idnum ;
			
		}
		function update(){
	//	alert("update"); 
	var selectObj = document.getElementById('sname');  //得到id为sname的对象
	var startObj = document.getElementById('start');  //得到id为sname的对象
	var start = startObj.value;
	var endObj = document.getElementById('end');  //得到id为sname的对象
	var end = endObj.value;
	var idnum = selectObj.value;
      // alert(selectObj.value);//得到对象的值    
      var cname = selectObj.options[selectObj.selectedIndex].text;//昨到对象的text的值 ，也就是我们写的中文 
		    
	// alert(cname);
				location="managerAction_updateTeacherAddCourse.action?idnum=" + idnum +"&cname=" + cname +"&start=" + start+"&end=" + end;
			
		}
		
</SCRIPT>
<SCRIPT type="text/javascript">


</SCRIPT>

	</head>

	<body>
		<div style="padding-top: 10px; padding-left: 10px;">

			<hr size="5" color="84BDF6">
			<center>
				<div style="color: 793AFF; font-size: large">
					<b>时间设置</b>
				</div>
			</center>
			<hr />


			<!-- action="managerAction_updateTeacherAddCourse.action?idnum=this.time.value" -->
			<div style="text-align: center;">
				<span>选择类型：</span>
				<!-- this.value表示select选择项的value值,因为select的value就是被选option项的value -->
				<select name="time" id="sname" onchange="change(this.value)">
					<option value="0001" <s:property value="#request.a"/>>
						老师添加课程时间设置
					</option>
					<option value="0002" <s:property value="#request.b"/>>
						老师添加毕业设计时间设置
					</option>
					<option value="0003" <s:property value="#request.c"/>>
						老师录入课程成绩时间设置
					</option>
					<option value="0004" <s:property value="#request.d"/>>
						老师录入毕业设计成绩时间设置
					</option>
					<option value="0005" <s:property value="#request.e"/>>
						学生选课时间设置
					</option>
					<option value="0006" <s:property value="#request.f"/>>
						学生毕业设计选题时间设置
					</option>

				</select>
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<span>起始时间: </span>
				<input id="start" name="start" type="text" class="Wdate"
					value="<s:property value="#request.teacherAddCourse.start"/>"
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				<span>终止时间: </span>
				<input id="end" name="end" type="text" class="Wdate"
					value="<s:property value="#request.teacherAddCourse.end"/>"
					onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				&nbsp;&nbsp;
				<input type="button" onclick="update()" value="修改">
			</div>

		</div>
	</body>
</html>
