<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<base href="<%=basePath%>">


		<TITLE>用户登录</TITLE>
		<LINK href="login/images/Default.css" type=text/css rel=stylesheet>
		<LINK href="login/images/xtree.css" type=text/css rel=stylesheet>
		<LINK href="login/images/User_Login.css" type=text/css rel=stylesheet>
	
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		
		function validate(){
			var name = $("#name").val();
			var pass =  $("#password").val() ;
			
			if( name == null || name == "" ){
				alert("用户编号不能为空!");
				return false;
			}
			if( pass == null || pass == ""){
				alert("用户密码不能为空！");
				return false;
			}
		
			return true;
		}
	</script>

	</head>

	<BODY id=userlogin_body >

		<form action="userAction_login.action" method="post">
		<DIV id=user_login>
			<DL>
				<DD id=user_top>
					<UL>
						<LI class=user_top_l></LI>
						<LI class=user_top_c></LI>
						<LI class=user_top_r></LI>
					</UL>
					<DD id=user_main>
						<UL>
							<LI class=user_main_l></LI>
							<LI class=user_main_c>
								<DIV class=user_main_box>
									<UL>
										<LI class=user_main_text >
											用户名：
										</LI>
										<LI class=user_main_input>
											<INPUT class=TxtUserNameCssClass id="name"   
										 maxLength=20
												name="userId">
										</LI>
									</UL>
									<UL>
										<LI class=user_main_text>
											密 码：
										</LI>
										<LI class=user_main_input>
											<input class=TxtPasswordCssClass id="password" type=password
												name="password">
										</LI>
									</UL>
									<UL>
										<LI class=user_main_text>
											身份：
										</ LI>
										<LI class=user_main_input>
										<select name="identity" id="identity" class="validate" style=" background-image:url('login/images/user_login_validatecode.gif'); " >
												<option id="1" value="1" selected="selected">
													学生
												</option>
												<option value="2">
													教师
												</option>
												<option value="3">
													管理员
												</option>

											</select>
										</LI>
									</UL>
									<UL>
										<LI >
											<span >
											 <s:if test="! #request.msg.isEmpty()">
												<font color="red">提示：用户名或密码错误！</font>
												 
											 </s:if>
											</span>
										
										</LI>
									
									</UL>
								</DIV>
							</LI>
							<LI class=user_main_r>
								<INPUT class=IbtnEnterCssClass id=IbtnEnter
									style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px"
								onclick="return validate();"
									type=image src="login/images/user_botton.gif" name=IbtnEnter>
							</LI>
						</UL>
						
						<DD id=user_bottom>

							<UL>
								<LI class=user_bottom_l></LI>
								<LI class=user_bottom_c>
									欢迎访问：基于Java WEB的教务管理系统
								</LI>
								<LI class=user_bottom_r></LI>
							</UL>
						</DD>
			</DL>
		</DIV>

		</FORM>
	</BODY>
</html>
