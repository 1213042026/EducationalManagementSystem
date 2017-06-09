<%@ page language="java" import="java.util.*,cn.edu.management.vo.voImpl.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(manager3/images/left.gif);
}
-->
</style>
		<link href="manager3/css/css.css" rel="stylesheet" type="text/css" />

  </head>
  <SCRIPT language=JavaScript>
function tupian(idt){
    var nametu="xiaotu"+idt;
    var tp = document.getElementById(nametu);
    tp.src="manager3/images/ico05.gif";//图片ico04为白色的正方形
	
	for(var i=1;i<100;i++)
	{
	  
	  var nametu2="xiaotu"+i;
	  if(i!=idt*1)
	  {
	    var tp2=document.getElementById('xiaotu'+i);
		if(tp2!=undefined)
	    {tp2.src="manager3/images/ico06.gif";}//图片ico06为蓝色的正方形
	  }
	}
}

function list(idstr){
	var name1="subtree"+idstr;
	var name2="img"+idstr;
	var objectobj=document.all(name1);
	var imgobj=document.all(name2);
	
	
	//alert(imgobj);
	
	if(objectobj.style.display=="none"){
		for(i=1;i<10;i++){
			var name3="img"+i;
			var name="subtree"+i;
			var o=document.all(name);
			if(o!=undefined){
				o.style.display="none";
				var image=document.all(name3);
				//alert(image);
				image.src="manager3/images/ico04.gif";
			}
		}
		objectobj.style.display="";
		imgobj.src="manager3/images/ico03.gif";
	}
	else{
		objectobj.style.display="none";
		imgobj.src="manager3/images/ico04.gif";
	}
}

</SCRIPT>
  <body>
  <table width="198" border="0" cellpadding="0" cellspacing="0"
			class="left-table01">
			<tr>
				<TD>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="207" height="55" background="manager3/images/nav01.gif">
								<table width="90%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="25%" rowspan="2">
											<img src="manager3/images/ico02.gif" width="35" height="35" />
										</td>
										<td width="75%" height="22" class="left-font01">
											您好，
											<!-- session.getAttribute("user")与登陆信息同步 -->
											<span class="left-font02"><s:property value="#session.manageruser"/></span>
										</td>
									</tr>
									<tr>
										<td height="22" class="left-font01">
											[&nbsp;
											<a href="./index.jsp" target="_top" class="left-font01">退出</a>&nbsp;]
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>



					<!--  任务系统开始    -->
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
						class="left-table03">
						<tr>
							<td height="29">
								<table width="85%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="8%">
											<img name="img8" id="img8" src="manager3/images/ico04.gif"
												width="8" height="11" />
										</td>
										<td width="92%">
											<a href="javascript:" target="mainFrame" class="left-font03"
												onClick="list('8');">管理员管理</a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</TABLE>
					<table id="subtree8" style="DISPLAY: none" width="80%" border="0"
						align="center" cellpadding="0" cellspacing="0"
						class="left-table02">
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu51" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<!-- manager3/files/listManger.jsp -->								
									<a href="managerAction_displayPersonInformation.action" target="mainFrame"
									class="left-font03" onClick="tupian('51');">查看个人信息</a>
							</td>
						</tr>
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu20" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<!-- manager3/files/listManger.jsp -->								
									<a href="manager3/files/ModifyPassWord.jsp" target="mainFrame"
									class="left-font03" onClick="tupian('20');">修改个人密码</a>
							</td>
						</tr>
						<!--------------------------------------------- system用户验证-------------------------->
						 <s:if test="! #session.system.isEmpty()">
											
												 
									
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu21" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
							<!-- manager3/files/listManger.jsp -->
								<a href="managerAction_listManager.action" target="mainFrame" class="left-font03"
									onClick="tupian('21');">查看管理员信息</a>
							</td>
						</tr>
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu22" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_getMaxIdNumAboutManager.action" target="mainFrame" class="left-font03"
									onClick="tupian('22');">添加管理员</a>
							</td>
						</tr>
						<tr>
							<td width="9%" height="21">
								<img id="xiaotu23" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_ListDeleteManager.action" target="mainFrame" class="left-font03"
									onClick="tupian('23');">删除管理员</a>
							</td>
						</tr>
						<tr>
							<td width="9%" height="22">
								<img id="xiaotu24" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_listModifyManager.action" target="mainFrame" class="left-font03"
									onClick="tupian('24');">修改管理员信息</a>
							</td>
						</tr>
								 </s:if>
					</table>
					<!--  任务系统结束    -->



					<!--  消息系统开始    -->
					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
						class="left-table03">
						<tr>
							<td height="29">
								<table width="85%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="8%">
											<img name="img7" id="img7" src="manager3/images/ico04.gif"
												width="8" height="11" />
										</td>
										<td width="92%">
											<a href="javascript:" target="mainFrame" class="left-font03"
												onClick="list('7');">教师管理</a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</TABLE>
					<table id="subtree7" style="DISPLAY: none" width="80%" border="0"
						align="center" cellpadding="0" cellspacing="0"
						class="left-table02">
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu11" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_listTeacher.action" target="mainFrame" class="left-font03"
									onClick="tupian('11');">查看教师信息</a>
							</td>
						</tr>
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu12" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_getMaxIdNumAboutTeacher.action" target="mainFrame"
									class="left-font03" onClick="tupian('12');">添加教师</a>
							</td>
						</tr>
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu13" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_ListDeleteTeacher.action" target="mainFrame"
									class="left-font03" onClick="tupian('13');">删除教师 </a>
							</td>
						</tr>
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu14" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_listModifyTeacher.action" target="mainFrame" class="left-font03"
									onClick="tupian('14');">修改教师</a>
							</td>
						</tr>
					</table>
					<!--  消息系统结束    -->



					<TABLE width="100%" border="0" cellpadding="0" cellspacing="0"
						class="left-table03">
						<tr>
							<td height="29">
								<table width="85%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="8%">
											<img name="img1" id="img1" src="manager3/images/ico04.gif"
												width="8" height="11" />
										</td>
										<td width="92%">
											<a href="javascript:" target="mainFrame" class="left-font03"
												onClick="list('1');">学生管理</a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</TABLE>
					<table id="subtree1" style="DISPLAY: none" width="80%" border="0"
						align="center" cellpadding="0" cellspacing="0"
						class="left-table02">
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu1" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_listStudent.action" target="mainFrame"
									class="left-font03" onClick="tupian('1');">查看学生信息</a>
							</td>
						</tr>
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu2" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_getProfessionForAddStudent.action" target="mainFrame" class="left-font03"
									onClick="tupian('2');">添加学生</a>
							</td>
						</tr>
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu3" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_ListDeleteStudent.action" target="mainFrame" class="left-font03"
									onClick="tupian('3');">删除学生</a>
							</td>
						</tr>
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu4" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_listModifyStudent.action" target="mainFrame" class="left-font03"
									onClick="tupian('4');">修改学生信息</a>
							</td>
						</tr>
						
					</table>
					<!--  项目系统结束    -->

					<!--  客户系统开始    -->
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="left-table03">
						<tr>
							<td height="29">
								<table width="85%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="8%" height="12">
											<img name="img2" id="img2" src="manager3/images/ico04.gif"
												width="8" height="11" />
										</td>
										<td width="92%">
											<a href="javascript:" target="mainFrame" class="left-font03"
												onClick="list('2');">审核请求管理</a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<table id="subtree2" style="DISPLAY: none" width="80%" border="0"
						align="center" cellpadding="0" cellspacing="0"
						class="left-table02">

						<tr>
							<td width="9%" height="20">
								<img id="xiaotu31" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_auditStudentInfor.action" target="mainFrame"
									class="left-font03" onClick="tupian('31');">审核学生请求信息</a>
							</td>
							
						</tr>
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu32" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_auditTeacherInfor.action" target="mainFrame"
									class="left-font03" onClick="tupian('32');">审核老师请求信息</a>
							</td>
							
						</tr>
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu33" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_searchStudentInfor.action" target="mainFrame"
									class="left-font03" onClick="tupian('33');">查询学生审核记录</a>
							</td>
							
						</tr>
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu34" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_searchTeacherInfor.action" target="mainFrame"
									class="left-font03" onClick="tupian('34');">查询老师审核记录</a>
							</td>
							
						</tr>
					</table>

<!-- 系统帮助开始 -->
				
				

					<!--  人员系统开始    -->
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="left-table03">
						<tr>
							<td height="29">
								<table width="85%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="8%" height="12">
											<img name="img3" id="img3" src="manager3/images/ico04.gif"
												width="8" height="11" />
										</td>
										<td width="92%">
											<a href="javascript:" target="mainFrame" class="left-font03"
												onClick="list('3');">系统设置</a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

					<table id="subtree3" style="DISPLAY: none" width="80%" border="0"
						align="center" cellpadding="0" cellspacing="0"
						class="left-table02">
						
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu41" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_teacherAddCourse" target="mainFrame"
									class="left-font03" onClick="tupian('41');">时间设置</a>
							</td>
						</tr>
						
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu48" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_WorksTypeManage" target="mainFrame"
									class="left-font03" onClick="tupian('48');">作品类型设置</a>
							</td>
						</tr>
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu48" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_ClassTypeManage" target="mainFrame"
									class="left-font03" onClick="tupian('48');">课程类型设置</a>
							</td>
						</tr>
						<tr>
							<td width="9%" height="20">
								<img id="xiaotu49" src="manager3/images/ico06.gif" width="8"
									height="12" />
							</td>
							<td width="91%">
								<a href="managerAction_ProfessionManage" target="mainFrame"
									class="left-font03" onClick="tupian('49');">专业管理</a>
							</td>
						</tr>
						
						
					</table>

					

				</TD>
			</tr>

		</table>
  </body>
</html>
