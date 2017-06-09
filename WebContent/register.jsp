<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="/struts-tags" prefix="s" %>



<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
	<HEAD>
		<TITLE>普通用户注册</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/Verify.js"></script>
		<STYLE type=text/css>
{
FONT-SIZE
:
 
12
px

	
}
.gridView {
	BORDER-RIGHT: #bad6ec 1px;
	BORDER-TOP: #bad6ec 1px;
	BORDER-LEFT: #bad6ec 1px;
	COLOR: #566984;
	BORDER-BOTTOM: #bad6ec 1px;
	FONT-FAMILY: Courier New
}

.gridViewHeader {
	BORDER-RIGHT: #bad6ec 1px solid;
	BORDER-TOP: #bad6ec 1px solid;
	BACKGROUND-IMAGE: url(images/bg_th.gif);
	BORDER-LEFT: #bad6ec 1px solid;
	LINE-HEIGHT: 27px;
	BORDER-BOTTOM: #bad6ec 1px solid
}

.gridViewItem {
	BORDER-RIGHT: #bad6ec 1px solid;
	BORDER-TOP: #bad6ec 1px solid;
	BORDER-LEFT: #bad6ec 1px solid;
	LINE-HEIGHT: 32px;
	BORDER-BOTTOM: #bad6ec 1px solid;
	TEXT-ALIGN: center
}

.cmdField {
	BORDER-RIGHT: 0px;
	BORDER-TOP: 0px;
	BACKGROUND-IMAGE: url(images/bg_rectbtn.png);
	OVERFLOW: hidden;
	BORDER-LEFT: 0px;
	WIDTH: 67px;
	COLOR: #364c6d;
	LINE-HEIGHT: 27px;
	BORDER-BOTTOM: 0px;
	BACKGROUND-REPEAT: repeat-x;
	HEIGHT: 27px;
	BACKGROUND-COLOR: transparent;
	TEXT-DECORATION: none
}

.buttonBlue {
	BORDER-RIGHT: 0px;
	BORDER-TOP: 0px;
	BACKGROUND-IMAGE: url(images/bg_button_blue.gif);
	BORDER-LEFT: 0px;
	WIDTH: 78px;
	COLOR: white;
	BORDER-BOTTOM: 0px;
	BACKGROUND-REPEAT: no-repeat;
	HEIGHT: 21px
}
</STYLE>




		<META content="MSHTML 6.00.2900.5848" name=GENERATOR>
	</HEAD>
	<BODY
		style="BACKGROUND-POSITION-Y: -120px; BACKGROUND-IMAGE: url(images/bg.gif); BACKGROUND-REPEAT: repeat-x">
		<SCRIPT type=text/javascript>
	//<![CDATA[
	var theForm = document.forms['aspnetForm'];
	if (!theForm) {
	    theForm = document.aspnetForm;
	}
	function __doPostBack(eventTarget, eventArgument) {
	    if (!theForm.onsubmit || (theForm.onsubmit() != false)) {
	        theForm.__EVENTTARGET.value = eventTarget;
	        theForm.__EVENTARGUMENT.value = eventArgument;
	        theForm.submit();
	    }
	}
	//]]>
	</SCRIPT>
	
			<c:if test="${!empty( msg )}">
		       <script>alert('${msg}');</script>
	        </c:if>
		<DIV>
			<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%"
				border=0>
				<TBODY>
					<TR
						style="BACKGROUND-IMAGE: url(images/bg_header.gif); BACKGROUND-REPEAT: repeat-x"
						height=47>
						<TD width=10>
							<SPAN
								style="FLOAT: left; BACKGROUND-IMAGE: url(images/main_hl.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></SPAN>
						</TD>
						<TD>
							<SPAN
								style="FLOAT: left; BACKGROUND-IMAGE: url(images/main_hl2.gif); WIDTH: 15px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></SPAN>
							<SPAN
								style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; FLOAT: left; BACKGROUND-IMAGE: url(images/main_hb.gif); PADDING-BOTTOM: 10px; COLOR: white; PADDING-TOP: 10px; BACKGROUND-REPEAT: repeat-x; HEIGHT: 47px; TEXT-ALIGN: center; 0 px: ">
								
							注册用户
							</SPAN>
							<SPAN
								style="FLOAT: left; BACKGROUND-IMAGE: url(images/main_hr.gif); WIDTH: 60px; BACKGROUND-REPEAT: no-repeat; HEIGHT: 47px"></SPAN>
						</TD>
						<TD
							style="BACKGROUND-POSITION: 50% bottom; BACKGROUND-IMAGE: url(images/main_rc.gif)"
							width=10></TD>
					</TR>
					<TR>
						<TD style="BACKGROUND-IMAGE: url(images/main_ls.gif)">&nbsp;
							
						</TD>
						<TD
							style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; COLOR: #566984; PADDING-TOP: 10px; BACKGROUND-COLOR: white"
							vAlign=top align=center>
							<form action="userAction_registerUser.action" method="post">
							<DIV>
								<TABLE width="60%" height="197" border=1 cellSpacing=0
									rules=all class=gridView id=ctl00_ContentPlaceHolder2_GridView1
									style="WIDTH: 90%; BORDER-COLLAPSE: collapse">
									<TBODY>
									
										<tr>
											<Td class=gridViewHeader scope=col>
												姓名
											</Td>
											<td>
												<input id='name' name ='name' type='text'  onblur="checkName(this)" />	
											 <img id="nameImg" src="images/valid.gif" style="display:none" />
                  							<span id="nameDiv"></span>
											</td>
										</tr>
										<tr>
											<Td class=gridViewHeader scope=col>
												性别
											</Td>
											<td>
											    <input   type= "radio"  id='radioboy' name= "sexType"  checked  value= "boy"   /> 男 &nbsp;&nbsp;
												<input   type= "radio"  id='radiogirl'  name= "sexType"   value= "girl"   /> 女
											</td>
										</tr>
										
										<TR>
											<td width="20%" class=gridViewHeader scope=col>
												账号	</td>
											<td width="90%">
												<input id='account' name ='account' type='text'  onblur="checkAccount(this)" />	
											    <img id="img" src="images/valid.gif" style="display:none" />
                  								<span id="div"></span>
											</td>
										</TR>
										
										<TR>
											<td width="20%" class=gridViewHeader scope=col>
												密码	</td>
											<td width="90%">
												<input id='password' name ='password' type='text'  onblur="checkPWD(this)" />	
											<img id="pwdImg" src="images/valid.gif" style="display:none" />
                  							<span id="pwdDiv"></span>
											</td>
										</TR>
										
										<tr>
											<Td class=gridviewHeader scope=col>
												电话
											</Td>
											<td>
												<input id='telePhone' name ='telePhone'   type='text' onblur="checkTel(this)" />	
											<img id="telImg" src="images/valid.gif" style="display:none" />
                  							<span id="telDiv"></span>
											</td>
										</tr>
										
										<TR>
											<td width="20%" class=gridViewHeader scope=col>
												电子邮件
											</td>
											<td>
												<input id='email' name ='email' type='text' onblur="checkEmail(this)" />	
											<img id="emailImg" src="images/valid.gif" style="display:none" />
                  							<span id="emailDiv"></span>
											</td>
											
										</TR>
											<tr>
												<TD class=gridViewItem>
													<INPUT id=registerBt type=submit value=" 注  册 " onclick="return validateResFrom();" name=btnRegister>
												</TD>
											</tr>
										
																				
										
									</TBODY>
							  </TABLE>
							</DIV>
							</form>
						</TD>
						<TD style="BACKGROUND-IMAGE: url(images/main_rs.gif)"></TD>
					</TR>
					
				</TBODY>
			</TABLE>
		</DIV>
	
		
	</BODY>

</HTML>

