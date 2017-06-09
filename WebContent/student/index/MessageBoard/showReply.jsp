<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" href="../js/CssAdmin.css">
<title>留言列表</title></head>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" > 
<script language="javascript">
	
	function showReplyDetail(idnum)
	{   
			location="studentAction_showReplyDetail.action?idnum="+idnum;
	}
</script>
</head>


<body>
<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
  <tr>
    <td height="24" nowrap><font color="#FFFFFF">&nbsp;<strong>当前位置：查看老师回复的留言</strong></font></td>
  </tr>
  <tr>
    <td height="24" align="center" nowrap  bgcolor="#EBF2F9">
    </td>    
  </tr>
</table>
<br />
  <center>
  <table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
    <tr>
      <td nowrap="nowrap" bgcolor="#8DB5E9" ><font color="#FFFFFF"><strong>编号</strong></font></td>
      <td nowrap="nowrap" bgcolor="#8DB5E9" ><font color="#FFFFFF"><strong>留言主题</strong></font></td>
      <td nowrap="nowrap" bgcolor="#8DB5E9" ><font color="#FFFFFF"><strong>内容</strong></font></td>
      <td nowrap="nowrap" bgcolor="#8DB5E9"><font color="#FFFFFF"><strong>回复内容</strong></font></td>
      <td nowrap="nowrap" bgcolor="#8DB5E9"><font color="#FFFFFF"><strong>回复时间</strong></font></td>
      <td nowrap="nowrap" bgcolor="#8DB5E9"><font color="#FFFFFF"><strong>查看详情</strong></font></td>
    </tr>
    
    <s:iterator  var="s" value="showMessages">
		     
		         <tr bgcolor="#FFFFFF" onmouseover="this.bgColor='f1f1f1'" onmouseout="this.bgColor='FFFFFF'"  style='cursor:hand'  title="">
    				<td> <s:property value='#s.idnum' /></td>
    				<td><s:property value="#s.title"/>&nbsp;</td>
		    		<td><div align="left"><s:property value="#s.content"/>&nbsp;</div></td>
		    		<td><div align="left"><s:property value="#s.rcontent"/>&nbsp;</div></td>	    	
		    		<td><s:property value="#s.rtime"/>&nbsp;</td>
		    		<td><input type="button" value="查 看" onclick="showReplyDetail('<s:property value="#s.idnum"/>')"/></td>
		    	</tr>
		    	
	</s:iterator>
  
</table>
<br />

</center>
</body>
</html>

