<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>课程管理操作流程</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="../js/CssAdmin.css">

<style type="text/css">
<!--
.style2 {color: #FFFFFF}
.STYLE3 {
	color: #FF0000;
	font-weight: bold;
}
.STYLE4 {color: #FF0000}
-->
</style>

  </head>
  
<BODY>
<div align="center">
  <table width="100%" border="0" align=center cellpadding="2" cellspacing="1" bgcolor="#6298E1" class="border">
    <tr align="center">
      <td height=25 class="style2"><div align="left">您好,欢迎使用教师子系统！ 教师操作流程如下：</div>      
    </tr>
  </table>
  <table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
    <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='f1f1f1'" onMouseOut="this.bgColor='FFFFFF'">
      <td><div align="center">操作</div></td>
      <td><div align="center">说明</div></td>
      <td><div align="center">备注</div></td>
    </tr>    
   
    <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='f1f1f1'" onMouseOut="this.bgColor='FFFFFF'">
      <td><div align="center">课程维护</div></td>
      <td>添加、修改、删除、查询自己的课程。</td>
<td><div align="center"></div></td>
    </tr>
    <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='f1f1f1'" onMouseOut="this.bgColor='FFFFFF'">
      <td><div align="center">↓</div></td>
      <td>&nbsp;</td>
      <td><div align="center"></div></td>
    </tr>
     
    <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='f1f1f1'" onMouseOut="this.bgColor='FFFFFF'">
      <td><div align="center">学生选课情况</div></td>
      <td>查看选择自己课程的学生的基本信息。</td>
      <td><div align="center"></div></td>
    </tr>
    <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='f1f1f1'" onMouseOut="this.bgColor='FFFFFF'">
      <td><div align="center">↓</div></td>
      <td>&nbsp;</td>
      <td><div align="center"></div></td>
    </tr>
     
    <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='f1f1f1'" onMouseOut="this.bgColor='FFFFFF'">
      <td><div align="center">学生成绩管理</div></td>
      <td>录入、修改、查看学生课程成绩。</td>
      <td><div align="center"></div></td>
    </tr>
   
       <tr bgcolor="#FFFFFF" onMouseOver="this.bgColor='f1f1f1'" onMouseOut="this.bgColor='FFFFFF'">
      <td><div align="center"></div></td>
      <td>&nbsp;</td>
      <td><div align="center"></div></td>
    </tr>
  
  </table>
  <table width="100%" border="0" align=center cellpadding="2" cellspacing="1" bgcolor="#6298E1" class="border">
  <tr align="center">
    <td height=15 class="topbg">  </tr>
</table>
</div>
</BODY>
</html>
