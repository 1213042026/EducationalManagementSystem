<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>显示个人信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		
	<style type="text/css">
	<!--
	.STYLE1 {color: #FF0000}
	.STYLE2 {
	color: #990000;
	font-size: 16;
	font-weight: bold;
	}
	-->
	</style>
	</head>
	<body>
			<table width="100%" border="0" cellpadding="3" cellspacing="1"
				bgcolor="#6298E1">
				<tr>
					<td height="24" nowrap>
						<font color="#FFFFFF">&nbsp;<strong>当前位置：显示个人信息</strong>
						</font>
					</td>
				</tr>
				<tr>
					<td height="24" align="center" nowrap bgcolor="#EBF2F9">
						&nbsp;
					</td>
				</tr>
			</table>
			
			
			<center>
				<br />
				
				<hr size="5" color="84BDF6">

<table align="center" width="100%" border="0" cellpadding="3" cellspacing="2" bgcolor="#6298E1">
  <tr>
    <td height="24" nowrap bgcolor="#EBF2F9">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id=editProduct width="100%">
      <tr>
        <td width="45%" height="20" align="right">&nbsp;</td>
        <td><div align="left"></div></td>
      </tr>
      <tr>
        <td height="30" align="right">学号：</td>
        <td><div align="left">
            <s:property value="#request.studentVO.idnum" />
          &nbsp;</div></td>
      </tr>
      <tr/>
      <tr>
        <td height="30" align="right">姓名：</td>
        <td><div align="left">
            <s:property value="#request.studentVO.name" />
          &nbsp;</div></td>
      </tr>
      <tr/>
      <tr>
        <td height="30" align="right">性别：</td>
        <td><div align="left">
            <s:property value="#request.studentVO.sex" />
          &nbsp;</div></td>
      </tr>
      <tr/>
      <tr>
        <td height="30" align="right">身份证：</td>
        <td><div align="left">
          <s:property value="#request.studentVO.idcard" />
        </div></td>
      </tr>
      <tr/>
      <tr>
        <td height="30" align="right">专业：</td>
        <td><div align="left">
          <s:property value="#request.studentVO.prof_Name" />
         </div></td>
      </tr>
      <tr/>
      <tr>
        <td height="30" align="right" >民族：</td>
        <td><div align="left">
			<s:property value="#request.studentVO.nation" />
        </div></td>
      </tr>
      <tr/>
      <tr>
      <td height="30" align="right" >家庭住址：</td>
        <td><div align="left">
			<s:property value="#request.studentVO.address" />
        </div></td>
      </tr>
      <tr>
        <td height="30" align="right" >入学年份：</td>
        <td><div align="left">
			<s:property value="#request.studentVO.entranceDate" />
        </div></td>
      </tr>
      <tr/>
      <tr>
        <td height="30" align="right">&nbsp;</td>
        <td height="30" valign="bottom"><div align="left"></div></td>
      </tr>
    </table></td>
  </tr>
  </table>
</center>


</body>
</html>
				