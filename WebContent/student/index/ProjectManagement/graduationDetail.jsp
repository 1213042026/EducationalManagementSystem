<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>毕业设计详情</title>
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
<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
  <tr>
    <td height="24" nowrap><font color="#FFFFFF"><strong>当前位置：毕业设计详情</strong></font></td>
  </tr>
</table>
  <center>
	<form action="studentAction_selectGraduationProject" method="post" style="text-align: center;">
	<table width="100%" border="0" cellpadding="3" cellspacing="2" bgcolor="#6298E1">
  		<tr>
   			 <td height="24" nowrap bgcolor="#EBF2F9">
   	<table width="100%" border="0" cellpadding="0" cellspacing="0" id=editProduct width="100%">
      <tr>
        <td height="30" align="right">毕业设计题目：</td>
        <td><div align="left"> <s:property value="#request.graduation.gname"/> <br/> </div></td>
      </tr>
      <tr>
        <td height="30" align="right">指导老师：</td>
        <td><div align="left"  class="STYLE4">
            <s:property value="#request.tname"/> </div></td>
        </tr>
      <tr>
        <td height="30" align="right">可选人数：</td>
        <td><div align="left">
            <s:property value="#request.graduation.gcount"/>  </div></td>
      </tr>
      <tr>
        <td height="30" align="right" valign="top">剩余人数：</td>
        <td><div align="left">
            <s:property value="#request.graduation.rcount"/></div></td>
      </tr>
      <tr>
        <td height="30" align="right" valign="top">毕业设计详情：</td>
        <td><div align="left">
            <s:property value="#request.graduation.remark"/> </div><br/></td>
      </tr>
      <tr><td></td></tr>
     
       <tr><td height="20" align="center" valign="top"></td>
       <td><table><tr><td width="10%"><span  width='300'><input type="submit"  value="返 回"/></span></td><td width="25%"></td><td width="25%"></td><td width="25%"></td></tr>
       </table>
       </td>
       </tr>
  </table>
       </td>
     </tr>
</table>
  
      </form> 
     
       
	
</center>


</body>
</html>


