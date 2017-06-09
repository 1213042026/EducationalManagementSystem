<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function validate()
		{ 
			var idnum=$("#idnum").val();
			var gname=$("#gname").val();
			var kCount=$("#kCount").val();
			var t_name=$("#t_name").val();
			var glevel=$("#glevel").val();
	     	var context=$("#context").text();
	     	
			if(gname==null||gname=="")
			{
				alert("请输入毕业设计名称！");
				return false;
			}

			if(kCount==null||kCount=="")
			{
				alert("请输入可选人数！");
				return false;
			}

			if(t_name=="请选择")
			{
				alert("请选择指导老师！");
				return false;
			}

			if(glevel=="请选择")
			{
				alert("请选择难易程度！");
				return false;
			}

			location="studentAction_addMyTitle.action?idnum="+idnum+"&gname="+gname+"&kCount="+kCount+"&t_name="+t_name+"&glevel="+glevel+"&context="+context;
			return true;
		}
</script>
<title>学生自拟选题</title>
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
<form name="editForm"  onsubmit="return depart_add()">
<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
  <tr>
    <td height="24" nowrap><font color="#FFFFFF"><img src="../Images/Explain.gif" width="18" height="18" border="0" align="absmiddle">&nbsp;<strong>自拟选题：提交自拟选题-&gt;指导老师审核（</strong></font><strong><span class="STYLE1">审核后不能修改</span></strong><font color="#FFFFFF"><strong>）-&gt;管理员审核</strong></font></td>
  </tr>
  <tr>
    <td height="24" align="center" nowrap  bgcolor="#EBF2F9">&nbsp;</td>    
  </tr>
</table>
  <center>
    <br/>
	
	

	<table width="100%" border="0" cellpadding="3" cellspacing="2" bgcolor="#6298E1">
  <tr>
    <td height="24" nowrap bgcolor="#EBF2F9">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" id=editProduct idth="100%">
      <tr>
        <td width="120" height="20" align="right">&nbsp;</td>
        <td><div align="left"></div></td>
      </tr>
      <tr>
        <td height="20" align="right">选题编号：</td>
        <td><div align="left">
            <input  type="text" name="idnum" id="idnum" readonly="true" value="<s:property value='#request.idnum' />" />
          &nbsp;</div></td>
      </tr>
      <tr/><tr/><tr/><tr/>
      <tr>
        <td height="20" align="right">添加新选题名称：</td>
        <td><div align="left">
            <input name="gname" type="text" class="textfield" id="gname" style="WIDTH: 120;" size="60" />
          &nbsp;*</div></td>
      </tr>
      <tr/><tr/><tr/><tr/>
      <tr>
        <td height="20" align="right">可选人数：</td>
        <td><div align="left">
            <input name="kCount" type="text" class="textfield" id="kCount"  />
          &nbsp;*</div></td>
      </tr>
      <tr/><tr/><tr/><tr/>
      <tr>
        <td height="20" align="right">指导老师：</td>
        <td><div align="left">
          <select name="t_name" id="t_name">
     			<option>请选择</option>
						<s:iterator value="teacherList">
							<option value="<s:property value='idnum' />"><s:property value="name" /></option>
						</s:iterator>
		</select>
        *</div></td>
      </tr>
      <tr/><tr/><tr/><tr/>
      <tr>
        <td height="20" align="right">难易程度：</td>
        <td><div align="left">
          <select name="glevel" id="glevel">
     			<option>请选择</option>
     			<option>容易</option>
     			<option>一般</option>
     			<option>困难</option>
						
		</select>
        *</div></td>
      </tr>
      <tr/><tr/><tr/><tr/>
      <tr>
        <td height="20" align="right" valign="top">自拟选题说明：</td>
        <td><div align="left">
          <textarea name="context" cols="60" rows="8" class="textfield" id="context" style="WIDTH: 580;" ></textarea>
        </div></td>
      </tr>
      <tr/><tr/><tr/><tr/>
      <tr>
        <td height="30" align="right">&nbsp;</td>
        <td valign="bottom"><div align="left">
         <input  type="button"  value=" 添 加 " onclick="validate();"  >
        　　
        <input name="submitSaveEdit2" type="reset" class="button"  id="submitSaveEdit2" value=" 重 置 " style="WIDTH: 60;">
        </div></td>
      </tr>
      <tr>
        <td height="20" align="right">&nbsp;</td>
        <td valign="bottom"><div align="left"></div></td>
      </tr>
    </table></td>
  </tr>
  </table>
</center>
 </form>


</body>
</html>


