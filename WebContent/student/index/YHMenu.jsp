<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD id=Head1>
	
    <base href="<%=basePath%>">
    
    	<TITLE>导航</TITLE>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
	<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
	
			<STYLE type=text/css>
BODY {
	PADDING-RIGHT: 0px;
	PADDING-LEFT: 0px;
	PADDING-BOTTOM: 0px;
	MARGIN: 0px;
	PADDING-TOP: 0px;
	BACKGROUND-COLOR: #2a8dc8
}

BODY {
	FONT-SIZE: 11px;
	COLOR: #003366;
	FONT-FAMILY: Verdana
}

TD {
	FONT-SIZE: 11px;
	COLOR: #003366;
	FONT-FAMILY: Verdana
}

DIV {
	FONT-SIZE: 11px;
	COLOR: #003366;
	FONT-FAMILY: Verdana
}

P {
	FONT-SIZE: 11px;
	COLOR: #003366;
	FONT-FAMILY: Verdana
}

.mainMenu {
	FONT-WEIGHT: bold;
	FONT-SIZE: 14px;
	CURSOR: hand;
	COLOR: #000000;
	TEXT-DECORATION: none
}

A.style2:link {
	PADDING-LEFT: 4px;
	COLOR: #0055bb;
	TEXT-DECORATION: none
}

A.style2:visited {
	PADDING-LEFT: 4px;
	COLOR: #0055bb;
	TEXT-DECORATION: none
}

A.style2:hover {
	PADDING-LEFT: 4px;
	COLOR: #ff0000;
	TEXT-DECORATION: none
}

A.active {
	PADDING-LEFT: 4px;
	COLOR: #ff0000;
	TEXT-DECORATION: none
}

.span {
	COLOR: #ff0000
}
</STYLE>

		<SCRIPT language=javascript>

		 var res="";
		 var ress="";
		function InitAjax()
		{
		　var ajax=false;
		　try {
		　　ajax = new ActiveXObject("Msxml2.XMLHTTP");
		　} catch (e) {
		　　try {
		　　　ajax = new ActiveXObject("Microsoft.XMLHTTP");
		　　} catch (E) {
		　　　ajax = false;
		　　}
		　}
		　if (!ajax && typeof XMLHttpRequest!='undefined') {
		　　ajax = new XMLHttpRequest();
		　}
		　return ajax;
		}

		//判断选课时间
		function  query()
		{
			　//需要进行Ajax的URL地址
			　var url = "studentAction_selectCourseTime.action";
			　//实例化Ajax对象
			　var ajax = InitAjax();
			　//使用Get方式进行请求
			　ajax.open("GET", url, false);
			　//获取执行状态
			　ajax.onreadystatechange = function() 
			{
			　　//如果执行是状态正常，那么就把返回的内容赋值给上面指定的层
			　　if (ajax.readyState == 4 && ajax.status == 200)
				 {
			　　　  res=ajax.responseText;
	
				  // alert(res);
			　	 }
			}
			　//发送空
			　ajax.send(null);
		}
		//判断选择毕业设计时间
		function  queryy()
		{
			　//需要进行Ajax的URL地址
			　var url = "studentAction_selectGraduationTime.action";
			　//实例化Ajax对象
			　var ajax = InitAjax();
			　//使用Get方式进行请求
			　ajax.open("GET", url, false);
			　//获取执行状态
			　ajax.onreadystatechange = function() 
			{
			　　//如果执行是状态正常，那么就把返回的内容赋值给上面指定的层
			　　if (ajax.readyState == 4 && ajax.status == 200)
				 {
			　　　  ress=ajax.responseText;
	
				  // alert(res);
			　	 }
			}
			　//发送空
			　ajax.send(null);
		}

		//判断管理毕业设计时间
		function  query1()
		{
			　//需要进行Ajax的URL地址
			　var url = "studentAction_selectGraduationManagementTime.action";
			　//实例化Ajax对象
			　var ajax = InitAjax();
			　//使用Get方式进行请求
			　ajax.open("GET", url, false);
			　//获取执行状态
			　ajax.onreadystatechange = function() 
			{
			　　//如果执行是状态正常，那么就把返回的内容赋值给上面指定的层
			　　if (ajax.readyState == 4 && ajax.status == 200)
				 {
			　　　  ress=ajax.responseText;
	
				  // alert(res);
			　	 }
			}
			　//发送空
			　ajax.send(null);
		}

		function validate()
		{
			location="studentAction_MyGraduationProject?user="+"student";
		}
		
		function MenuDisplay(obj)
		{
			
			if(document.getElementById(obj).style.display != 'none'){
				
				document.getElementById(obj).style.display='none';
				document.getElementById(obj+'Span').innerText='＋';
				
			}
			else
			{
				 if(obj == 'table_3'  && document.getElementById('table_3Span').innerText =='＋')
				 {  
					 
				 	 query();
				  if(res=='1'){
					  alert('现在不是选课时间！');
					  return ;
				  }
				   
				 }

				 if(obj == 'table_4'  && document.getElementById('table_4Span').innerText =='＋')
				 {  
					 
				 	 queryy();
				  if(ress=='1'){
					  alert('现在不是选择毕业设计时间！');
					  return ;
				  }
				   
				 }

				 if(obj == 'table_5'  && document.getElementById('table_4Span').innerText =='＋')
				 {  
					 
				 	 query1();
				  if(ress=='1'){
					  alert('现在不是管理毕业设计时间！');
					  return ;
				  }
				   
				 }

				
					for(var i=1;i<=6;i++)//
				    {
				        document.getElementById('table_'+i).style.display='none';
						document.getElementById('table_'+i+'Span').innerText='＋';
				    }
					if(document.getElementById(obj).style.display =='none')
					{
						
						document.getElementById(obj).style.display='block';
						document.getElementById(obj+'Span').innerText='－';
					}
					else
					{
						document.getElementById(obj).style.display='none';
						document.getElementById(obj+'Span').innerText='＋';
					}

				
				
				
			}

			
		}
    </SCRIPT>
	
  </head>
  
  <body>
   <FORM id=form1 name=form1 action=YHMenu.aspx method=post>
			<TABLE cellSpacing=0 cellPadding=0 width=210 align=center border=0>
				<TBODY>
					<TR>
						<TD width=15>
							<IMG src="student/index/YHMenu.files/new_005.jpg" border=0>
						</TD>
						<TD align=middle width=180 background=student/index/YHMenu.files/new_006.jpg
							height=35>
							<B>学生端 －功能菜单</B>
						</TD>
						<TD width=15>
							<IMG src="student/index/YHMenu.files/new_007.jpg" border=0>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
			<TABLE cellSpacing=0 cellPadding=0 width=210 align=center border=0>
				<TBODY>
					<TR>
						<TD width=15 background=student/index/YHMenu.files/new_008.jpg></TD>
						<TD vAlign=top width=180 bgColor=#ffffff>
							<TABLE cellSpacing=0 cellPadding=3 width=165 align=center
								border=0>
								<TBODY>
									<TR>
										<TD class=mainMenu onClick="MenuDisplay('table_1');">
											<SPAN class=span id=table_1Span>＋</SPAN> <A class=mainMenu   href="student/index/mainfra.jsp" target=dmMain>信息维护</A>
										</TD>
									</TR>
									<TR>
										<TD>
											<TABLE id=table_1 style="DISPLAY: none" cellSpacing=0
												cellPadding=2 width=155 align=center border=0>
												<TBODY>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_displayPersonInformation.action" target=dmMain>－ 个人信息</A>
														</TD>
													</TR>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="student/index/InformationMaintenance/modifyPassword.jsp" target=dmMain>－ 密码修改</A>
														</TD>
													</TR>
												</TBODY>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD background=student/index/YHMenu.files/new_027.jpg height=1></TD>
									</TR>
									<TR>
										<TD class=mainMenu onClick="MenuDisplay('table_2');">
											<SPAN class=span id=table_2Span>＋</SPAN> <A class=mainMenu   href="student/index/mainfra.jsp" target=dmMain>信息查询</A>
										</TD>
									</TR>
									<TR>
										<TD>
											<TABLE id=table_2 style="DISPLAY: none" cellSpacing=0
												cellPadding=2 width=155 align=center border=0>
												<TBODY>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_listMyScore.action" target=dmMain>－ 在校成绩查询</A>
														</TD>
													</TR>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_listAllAlreadyCourse.action" target=dmMain>－ 选课情况查询</A>
														</TD>
													</TR>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_currentSelectedCourse.action" target=dmMain>－ 当前选课情况查询</A>
														</TD>
													</TR>
												</TBODY>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD background=student/index/YHMenu.files/new_027.jpg height=1></TD>
									</TR>
									<TR>
										<TD id='selectOnline' class=mainMenu onClick="MenuDisplay('table_3');">
											<SPAN class=span id=table_3Span>＋</SPAN> <A class=mainMenu   href="student/index/mainfra.jsp" target=dmMain>网上选课</A>
										</TD>
									</TR>
									<TR>
										<TD>
											<TABLE id=table_3 style="DISPLAY: none" cellSpacing=0
												cellPadding=2 width=155 align=center border=0>
												<TBODY>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="student/index/upDown.jsp" target=dmMain>－ 选修课程</A>
														</TD>
													</TR>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_currentSelectedCourse.action" target=dmMain>－ 已选课程</A>
														</TD>
													</TR>
												</TBODY>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD background=student/index/YHMenu.files/new_027.jpg height=1></TD>
									</TR>
									<TR>
										<TD class=mainMenu onClick="MenuDisplay('table_4');">
											<SPAN class=span id=table_4Span>＋</SPAN> <A class=mainMenu   href="student/index/mainfra.jsp" target=dmMain>毕业设计选题</A>
										</TD>
									</TR>
									<TR>
										<TD>
											<TABLE id=table_4 style="DISPLAY: none" cellSpacing=0
												cellPadding=2 width=155 align=center border=0>
												<TBODY>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="student/index/ProjectManagement/selectProjectProcess.jsp" target=dmMain>－ 选题流程</A>
														</TD>
													</TR>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_getMaxIdnumAboutGraduationa" target=dmMain>－ 自拟选题</A>
														</TD>
													</TR>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_listModifyApplicationSelf" target=dmMain>－ 自拟选题审核查询</A>
														</TD>
													</TR>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_selectGraduationProject" target=dmMain>－ 毕业设计选题</A>
														</TD>
													</TR>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_MyGraduationProject?user='student'" target=dmMain>－ 我的毕业设计</A>
														</TD>
													</TR>
												</TBODY>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD background=student/index/YHMenu.files/new_027.jpg height=1></TD>
									</TR>
									<TR>
										<TD class=mainMenu onClick="MenuDisplay('table_5');">
											<SPAN class=span id=table_5Span>＋</SPAN> <A class=mainMenu   href="student/index/mainfra.jsp" target=dmMain>毕业设计管理</A>
										</TD>
									</TR>
									<TR>
										<TD>
											<TABLE id=table_5 style="DISPLAY: none" cellSpacing=0
												cellPadding=2 width=155 align=center border=0>
												<TBODY>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="student/index/ProjectManagement/projectProcess.jsp" target=dmMain>－毕业设计流程</A>
														</TD>
													</TR>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_projectManagement" target=dmMain>－ 作品管理</A>
														</TD>
													</TR>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_MyGraduationProjecttt" target=dmMain>－ 我的毕业设计</A>
														</TD>
													</TR>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_addMessage" target=dmMain>－ 添加留言</A>
														</TD>
													</TR>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_showReply" target=dmMain>－ 查看回复</A>
														</TD>
													</TR>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_showMessage" target=dmMain>－ 查看所有留言</A>
														</TD>
													</TR>
												</TBODY>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD background=student/index/YHMenu.files/new_027.jpg height=1></TD>
									</TR>
									<TR>
										<TD class=mainMenu onClick="MenuDisplay('table_6');">
											<SPAN class=span id=table_6Span>＋</SPAN> <A class=mainMenu   href="student/index/mainfra.jsp" target=dmMain>个人信息申请修改</A>
										</TD>
									</TR>
									<TR>
										<TD>
											<TABLE id=table_6 style="DISPLAY: none" cellSpacing=0
												cellPadding=2 width=155 align=center border=0>
												<TBODY>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_addModifyApplication" target=dmMain>－添加申请</A>
														</TD>
													</TR>
													<TR>
														<TD class=menuSmall>
															<A class=style2 href="studentAction_listModifyApplication" target=dmMain>－查看申请状态</A>
														</TD>
													</TR>
												</TBODY>
											</TABLE>
										</TD>
									</TR>
									<TR>
										<TD background=student/index/YHMenu.files/new_027.jpg height=1></TD>
									</TR>
								</TBODY>
							</TABLE>
						</TD>
						<TD width=15 background=student/index/YHMenu.files/new_009.jpg></TD>
					</TR>
				</TBODY>
			</TABLE>
			<TABLE cellSpacing=0 cellPadding=0 width=210 align=center border=0>
				<TBODY>
					<TR>
						<TD width=15>
							<IMG src="student/index/YHMenu.files/new_010.jpg" border=0>
						</TD>
						<TD align=middle width=180 background=student/index/YHMenu.files/new_011.jpg
							height=15></TD>
						<TD width=15>
							<IMG src="student/index/YHMenu.files/new_012.jpg" border=0>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
		</FORM>
  </body>
</html>
