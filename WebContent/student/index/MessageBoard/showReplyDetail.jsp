<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script type="text/javascript" src="js/jquery.js"></script>
<title>查看留言回复详情</title>
</head>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" > 
</head>

<body>
  
	<div style="padding-top: 10px; padding-left: 10px;"/>	
 	<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
  		<tr>
  		  <td height="24" nowrap><font color="#FFFFFF"><img src="student/iconsIMG/Explain.gif" width="18" height="18" border="0" align="absmiddle">&nbsp;<strong>当前位置：查看留言回复详情</strong></font></td>
  		</tr>
  		<tr>
  		  <td align="center" nowrap  bgcolor="#EBF2F9"> </td>    
  		</tr>
	</table>
	<br />
    <hr size="5" color="84BDF6">
	
	<form action="studentAction_showReply" method="post" style="text-align: center;">
	<table width="100%" border="0" cellpadding="3" cellspacing="2" bgcolor="#6298E1">
  		<tr>
   			 <td height="24" nowrap bgcolor="#EBF2F9">
   	<table width="100%" border="0" cellpadding="0" cellspacing="0" id=editProduct idth="100%">
  	    <tr>
  	    	<td> <input  type="hidden" name="MessageId" id="MessageId" value="<s:property value='#request.idnum' />" />
            	 <input  type="hidden" name="tIdnum" id="tIdnum" value="<s:property value='#request.tIdnum' />" />
            </td>
       		 <td width="120" height="20" align="right">&nbsp;</td>
       		 <td><div align="left"></div></td>
        </tr>
      <tr>
        <td height="20" align="right">留言对象：</td>
        <td><div align="left">
            <input name="sName" readonly="true" type="text" class="textfield" id="sName" style="WIDTH: 120;" value="<s:property value='#request.sName' />" size="19" />
        </div></td>
      </tr>
      <tr>
        <td height="20" align="right">回复对象：</td>
        <td><div align="left"  class="STYLE4">
            <input name="tName" readonly="true" type="text" class="textfield" id="tName" value="<s:property value='#request.tName' />" style="WIDTH: 120;" size="19" />
        </div></td>
        </tr>
      <tr>
        <td height="20" align="right">留言主题：</td>
        <td><div align="left">
            <input name="title" readonly="true" type="text" class="textfield" id="title" value="<s:property value="#request.title" />"  style="WIDTH: 120;" size="50" />
        </div></td>
      </tr>
      <tr>
        <td height="20" align="right" valign="top">留言内容：</td>
        <td><div align="left">
            <textarea name="content" readonly="true" cols="60" rows="5" class="textfield" id="content"   style="WIDTH: 580;" ><s:property value="#request.contentMessage" /></textarea>
        </div></td>
      </tr>
      <tr>
        <td height="20" align="right" valign="top">回复内容：</td>
        <td><div align="left">
            <textarea name="content" readonly="true" cols="60" rows="5" class="textfield" id="content"   style="WIDTH: 580;" ><s:property value="#request.rcontentMessage" /></textarea>
        </div><br/></td>
      </tr>
     
       <tr><td height="20" align="right" valign="top"></td>
       <td><table><tr><td width="10%"><span  width='300'><input type="submit"  value="返 回"/></span></td><td width="25%"></td><td width="25%"></td><td width="25%"></td></tr>
       </table>
       </td>
       </tr>
  </table>
       </td>
     </tr>
</table>
  
      </form> 
</body>
</html>

