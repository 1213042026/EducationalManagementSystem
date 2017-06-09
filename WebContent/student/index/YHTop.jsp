<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head id=Head1>
    <base href="<%=basePath%>">
    
    <title>顶部</title>
    
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
	FONT-SIZE: 12px;
	COLOR: #003366;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}

TD {
	FONT-SIZE: 12px;
	COLOR: #003366;
	FONT-FAMILY: Arial, Helvetica,Verdana,sans-serif
}

DIV {
	FONT-SIZE: 12px;
	COLOR: #003366;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}

P {
	FONT-SIZE: 12px;
	COLOR: #003366;
	FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
}
</STYLE>
    
  </head>
  
  <body>
    <FORM id=form1 name=form1 action=YHTop.aspx method=post>
			<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					<TR>
						<!-- <TD width=10>
							<IMG src="student/index/YHTop.files/new_001.jpg" border=0>
						</TD>
						 -->
						<TD background=student/index/YHTop.files/new_002.jpg>
							<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
								<TBODY>
									<TR >
										<TD >
											<FONT size=7 face="华文隶书" color="799FD9"><B>基于Java Web教务管理系统</B>
											</FONT>
										</TD>
									
										<TD >
											<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
												<TBODY>
													<TR>
														<TD align=right height=35></TD>
													</TR>
													<TR>
														<TD >
															<B>当前登录用户:</B>
															<font style=" font-size:13px;font-weight:bolder;color:black">
															<s:property value="#session.user"/>  
															</font>
														</TD>
													</TR>
												</TBODY>
											</TABLE>
										</TD>
									
								</TBODY>
							</TABLE>
							
							
						</TD>
						
						<TD background=student/index/YHTop.files/new_002.jpg>
							<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
								<TBODY>
									<TR>
										<TD align=right height=35></TD>
									</TR>
									<TR>
										<TD height=35>
											<A href="#" target=_top><FONT color=red><B>安全退出</B>
											</FONT>
											</A>
										</TD>
									</TR>
								</TBODY>
							</TABLE>
						</TD>
						<TD width=10>
							<IMG src="student/index/YHTop.files/new_003.jpg" border=0>
						</TD>
					</TR>
				</TBODY>
			</TABLE>
		</FORM>
  </body>
</html>
