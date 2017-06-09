<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>左边的菜单</title>
		<script type="text/javascript" src="teacher/js/jquery.js"></script>
<script type="text/javascript" src="teacher/js/chili-1.7.pack.js"></script>
<script type="text/javascript" src="teacher/js/jquery.easing.js"></script>
<script type="text/javascript" src="teacher/js/jquery.dimensions.js"></script>
<script type="text/javascript" src="teacher/js/jquery.accordion.js"></script>
<script language="javascript">
	jQuery().ready(function(){
		jQuery('#navigation').accordion({
			header: '.head',
			navigation1: true, 
			event: 'click',
			fillSpace: true,
			animated: 'bounceslide'
		});
	});
</script>
<style type="text/css">
<!--
body {
	margin:0px;
	padding:0px;
	font-size: 12px;
}
#navigation {
	margin:0px;
	padding:0px;
	width:147px;
}
#navigation a.head {
	cursor:pointer;
	background:url(teacher/images/main_34.gif) no-repeat scroll;
	display:block;
	font-weight:bold;
	margin:0px;
	padding:5px 0 5px;/****/
	text-align:center;
	font-size:12px;
	text-decoration:none;
}
#navigation ul {
	border-width:0px;
	margin:0px;
	padding:0px;
	text-indent:0px;
}
#navigation li {
	list-style:none; display:inline;
}
#navigation li li a {
	display:block;
	font-size:12px;
	text-decoration: none;
	text-align:center;
	padding:3px;
}
#navigation li li a:hover {
	background:url(teacher/images/tab_bg.gif) repeat-x;
		border:solid 1px #adb9c2;
}
-->
</style>

  </head>
  
  <body>
  <!-- disTeacherFileList -->
  <div  style="height:100%;">
  <ul id="navigation">
    <li> <a class="head">个人信息维护</a>
      <ul>
        <li><a href="teacherAction_displayPersonInformation.action" target="rightFrame">查看个人信息</a></li>
        <li><a href="teacher/ModifyPassWord.jsp" target="rightFrame">修改密码信息</a></li>
      </ul>
    </li>
    <li> <a class="head">毕业设计管理</a>
      <ul>
        <li><a href="teacher/OperationProcess.jsp" target="rightFrame">查看流程</a></li>
        <li><a href="teacherAction_displayAlreadyAdd.action" target="rightFrame">查看现有毕业设计</a></li>
        <li><a href="teacherAction_getMaxIdNumAboutGraduate.action" target="rightFrame">上传毕业设计</a></li>
         <li><a href="teacherAction_updateAlreadyAdd.action" target="rightFrame">修改毕业设计</a></li>
         <li><a href="teacherAction_deleteAlreadyAdd.action" target="rightFrame">删除毕业设计</a></li>
         <li><a href="teacherAction_teacherFileManage.action" target="rightFrame">老师文件管理</a></li>
         <li><a href="teacherAction_studentInforSearch.action" target="rightFrame">学生信息查询</a></li>
         <li><a href="teacherAction_studentFileManage.action" target="rightFrame">学生作品管理</a></li>
         <li><a href="teacherAction_studentGradeInputx.action" target="rightFrame">学生成绩录入</a></li>
            <li><a href="teacher/studentGradeUpdate.jsp" target="rightFrame">学生成绩修改</a></li>
         <li><a href="teacherAction_studentGradeSearch.action" target="rightFrame">学生成绩查询</a></li>
         
      </ul>
    </li>
    <li> <a class="head">课程管理</a>
      <ul>
       <li><a href="teacher/CourseOperationProcess.jsp" target="rightFrame">查看流程</a></li>
        <li><a href="teacherAction_displayAlreadyAddCourse.action" target="rightFrame">查看已添加课程</a></li>
        <li><a href="teacherAction_getMaxIdNumAboutCourse.action" target="rightFrame">添加课程</a></li>
        <li><a href="teacherAction_updateAlreadyAddCourse.action" target="rightFrame">修改课程</a></li>
        <li><a href="teacherAction_deleteAlreadyAddCourse.action" target="rightFrame">删除课程</a></li>
         <li><a href="teacherAction_studentInforSearchCourse.action" target="rightFrame">查看选课学生信息</a></li>
          <li><a href="teacherAction_studentCourseGradeInputx.action" target="rightFrame">录入课程成绩</a></li>
           <li><a href="teacher/studentCourseGradeUpdate.jsp" target="rightFrame">修改课程成绩</a></li>
            <li><a href="teacherAction_studentGradeSearchCourse.action" target="rightFrame">查看课程成绩</a></li>
      </ul>
    </li>
    <li> <a class="head">个人信息申请管理</a>
      <ul>
        <li><a href="teacherAction_addModifyApplycheck.action" target="rightFrame">添加申请</a></li>
        <li><a href="teacherAction_displayAlreadyApplyStatus.action" target="rightFrame"> 查看已申请状态</a></li>
        
      </ul>
    </li>
     <li> <a class="head">毕业设计审核管理</a>
      <ul>
        <li><a href="teacherAction_disAuditGraduationTitle.action" target="rightFrame">审核学生毕设题目申请</a></li>
        <li><a href="teacherAction_disAuditGraduationStatus.action" target="rightFrame"> 查看审核结果</a></li>
        
      </ul>
    </li>
         <li> <a class="head">学生留言管理</a>
      <ul>
        <li><a href="teacherAction_disReplyMessage.action" target="rightFrame">回复留言</a></li>
        <li><a href="teacherAction_disAlreadyReplyMessage.action" target="rightFrame"> 查看已回复留言</a></li>
        
      </ul>
    </li>
    <li> <a class="head">版本信息</a>
      <ul>
        <li><a >by BaiYan</a></li>
      </ul>
    </li>
  </ul>
</div>
  </body>
</html>
