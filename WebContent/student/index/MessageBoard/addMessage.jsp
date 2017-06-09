<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script type="text/javascript" src="js/jquery.js"></script>

<script language="javascript" type="text/javascript"> 



	function validate()
		{ 
		var MessageId=$("#MessageId").val();
		var studentId=$("#studentId").val();
		var sName=$("#sName").val();
		var tIdnum=$("#tIdnum").val();
		var title=$("#title").val();
     	var content=$("#content").text();

		if(title==null||title=="")
		{
			alert("请输入留言主题！");
			return false;
		}

		if(content==null||content=="")
		{
			alert("请输入留言内容！");
			return false;
		}
	
		location="studentAction_insertMessage.action?MessageId="+MessageId+"&tIdnum="+tIdnum+"&title="+title+"&content="+content;
		return true;
		}
</script>
<title>添加留言</title>
</head>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" > 
</head>

<body>

	<div style="padding-top: 10px; padding-left: 10px;"/>	
 	<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
  		<tr>
  		  <td height="24" nowrap><font color="#FFFFFF"><img src="student/iconsIMG/Explain.gif" width="18" height="18" border="0" align="absmiddle">&nbsp;<strong>当前位置：选修课程</strong></font></td>
  		</tr>
  		<tr>
  		  <td align="center" nowrap  bgcolor="#EBF2F9"> </td>    
  		</tr>
	</table>
	<br />
    <hr size="5" color="84BDF6">
	
	<table width="100%" border="0" cellpadding="3" cellspacing="2" bgcolor="#6298E1">
  		<form name="editForm" method="post" action="?Action=addnew" onsubmit="return depart_add()">
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
    	   	 <td height="20" align="right">学  号：</td>
    	 	 <td><div align="left">
            		<input name="studentId" readonly="true" type="text" class="textfield" id="studentId" style="WIDTH: 120;" value="<s:property value='#request.studentId' />"  size="19" />
        		</div></td>
      </tr>
      <tr>
        <td height="20" align="right">学生姓名：</td>
        <td><div align="left">
            <input name="sName" readonly="true" type="text" class="textfield" id="sName" style="WIDTH: 120;" value="<s:property value='#request.sName' />" size="19" />
        </div></td>
      </tr>
      <tr>
        <td height="20" align="right">老师姓名：</td>
        <td><div align="left"  class="STYLE4">
            <input name="tName" type="text" class="textfield" id="tName" value="<s:property value='#request.tName' />" style="WIDTH: 120;" size="19" />
        </div></td>
        </tr>
      <tr>
      <tr>
        <td height="20" align="right">留言主题：</td>
        <td><div align="left">
            <input name="title" type="text" class="textfield" id="title" style="WIDTH: 120;" size="50" />
        </div></td>
      </tr>
        <td height="20" align="right" valign="top">留言内容：</td>
        <td><div align="left">
            <textarea name="content" cols="60" rows="8" class="textfield" id="content" style="WIDTH: 580;" ></textarea>
        </div></td>
      </tr>
      <tr>
        <td height="30" align="right">&nbsp;</td>
         <td valign="bottom"><div align="left">
         <input  type="button"  value=" 添 加 " onclick="validate();"  >
        　　
        <input name="submitSaveEdit2" type="reset" class="button"  id="submitSaveEdit22" value=" 重 置 " style="WIDTH: 60;" />
        </div></td>
      </tr>
      <tr>
        <td height="20" align="right">&nbsp;</td>
        <td valign="bottom"><div align="left"></div></td>
      </tr>
  </table>
       </td>
     </tr>
  </form>
</table>
</body>
</html>

