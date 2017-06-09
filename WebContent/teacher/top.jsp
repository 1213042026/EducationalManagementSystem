<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px;
	color: #000000;
}

.STYLE5 {
	font-size: 12
}

.STYLE7 {
	font-size: 12px;
	color: #FFFFFF;
}

.STYLE7 a {
	font-size: 12px;
	color: #FFFFFF;
}

a img {
	border: none;
}
-->
</style>
	

  </head>
  
  <body>
   <table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="57" background="teacher/images/main_03.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="378" height="57" background="teacher/images/main_01.gif">
								&nbsp;
							</td>
							<td>
								&nbsp;
							</td>
							<td width="281" valign="bottom">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="33" height="27">
											
										</td>
										<!-- background="teacher/images/main_06.gif" -->
										<td width="248" >
											<table width="225" border="0" align="center" cellpadding="0"
												cellspacing="0">
												<tr>
													<td height="17">
														<div align="right">
															
														</div>
													</td>
													<td>
														<div align="right">
															
														</div>
													</td>
													<td>
														<div align="right">
															<a href="" target="_parent"><img
																	src="teacher/images/quit.gif" alt=" " width="69" height="17" />
															</a>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="40" background="teacher/images/main_10.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="194" height="40" background="teacher/images/main_07.gif">
								&nbsp;
							</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="21">
											
										</td>
										<td width="35" class="STYLE7">
											<div align="center">
												<a href="main.html" target="rightFrame"></a>
											</div>
										</td>
										<td width="21" class="STYLE7">
										
										</td>
										<td width="35" class="STYLE7">
											<div align="center">
												<a href="javascript:history.go(-1);"></a>
											</div>
										</td>
										<td width="21" class="STYLE7">
											
										</td>
										<td width="35" class="STYLE7">
											<div align="center">
												<a href="javascript:history.go(1);"></a>
											</div>
										</td>
										<td width="21" class="STYLE7">
											
										</td>
										<td width="35" class="STYLE7">
											<div align="center">
												<a href="javascript:window.parent.location.reload();"></a>
											</div>
										</td>
										<td width="21" class="STYLE7">
											
										</td>
										<td width="35" class="STYLE7">
											<div align="center">
												<a href="http://www.865171.cn" target="_parent"></a>
											</div>
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td width="248" >
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="16%">
											<span class="STYLE5"></span>
										</td>
										<td width="75%">
											<div align="center">
												<span class="STYLE7"> <a
													href="http://Www.865171.cn" target="_blank"></a></span>
											</div>
										</td>
										<td width="9%">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="30" background="teacher/images/main_31.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="8" height="30">
								<img src="teacher/images/main_28.gif" width="8" height="30" />
							</td>
							<td width="147" background="teacher/images/main_29.gif">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="24%">
											&nbsp;
										</td>
										<td width="43%" height="20" valign="bottom" class="STYLE1">
											管理菜单
										</td>
										<td width="33%">
											&nbsp;
										</td>
									</tr>
								</table>
							</td>
							<td width="39">
								<img src="teacher/images/main_30.gif" width="39" height="30" />
							</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="20" valign="bottom">
											<span class="STYLE1">当前登录用户:</span>
											<font style=" font-size:14px;font-weight:bolder;"><s:property value="#session.teacheruser"/> </font>
										</td>
										<td valign="bottom" class="STYLE1">
											<div align="right"></div>
										</td>
									</tr>
								</table>
							</td>
							<td width="17">
								<img src="teacher/images/main_32.gif" width="17" height="30" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
  </body>
</html>
