<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>显示学生选择的毕业设计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="javascript">
	var res="";
	function InitAjax()
	{
	　var ajax=false;
	　try {
	　　ajax = new ActiveXObject("Msxml2.XMLHTTP");
	　} catch (e) {
	　　try {
	　　　ajax = new ActiveXObject("Microsoft.XMLHTTP");
	　　} catch (E) {
	　　　ajax = false;
	　　}
	　}
	　if (!ajax && typeof XMLHttpRequest!='undefined') {
	　　ajax = new XMLHttpRequest();
	　}
	　return ajax;
	}


	function  query()
	{
		　//需要进行Ajax的URL地址
		　var url = "studentAction_selectGraduationTime.action";
		　//实例化Ajax对象
		　var ajax = InitAjax();
		　//使用Get方式进行请求
		　ajax.open("GET", url, false);
		　//获取执行状态
		　ajax.onreadystatechange = function() 
		{
		　　//如果执行是状态正常，那么就把返回的内容赋值给上面指定的层
		　　if (ajax.readyState == 4 && ajax.status == 200)
			 {
		　　　  res=ajax.responseText;

		　	 }
		}
		　//发送空
		　ajax.send(null);
	}
	

	
	function deleteProject()
	{
		query();
		if(res=='1')
			alert("现在不是毕业设计选题时间，不能删除！");
		
		else
		{
			var truthBeTold = window.confirm("确定要删除毕业设计吗？");
			if (truthBeTold) { 
				location="studentAction_deleteGraduateProject.action";
		}
		}
	}
	</script>

  </head>
  
  <body >
    	<div style="padding-top: 10px; padding-left: 10px;">
    	<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
  <tr>
    <td height="24" nowrap><font color="#FFFFFF"><img src="student/iconsIMG/Explain.gif" width="18" height="18" border="0" align="absmiddle">&nbsp;<strong>当前位置：我的毕业设计</strong></font></td>
  </tr>
  <tr>
    <td align="center" nowrap  bgcolor="#EBF2F9">
	
    </td>    
  </tr>
</table>
<br />
     <hr size="5" color="84BDF6">
	     
	     
	     <s:if test="#request.myGraduateProject==null">
	          <p align="center">您还没有选择毕业设计，请先选择！</p>
	     </s:if>
	     <s:else>
	        <table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
		    <tr>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>选题名称</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>课程性质</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>指导老师</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>成绩</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9" ><center><font color="#FFFFFF"><strong>选题时间</strong></font></center></td>
		      <td  bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>删除</strong></font></center></td>
		     
		    </tr>
	    
		    	<tr bgcolor="#FFFFFF" onmouseover="this.bgColor='f1f1f1'" onmouseout="this.bgColor='FFFFFF'"  style='cursor:hand'  title="" align="center">
		    		<td><s:property value="#request.myGraduateProject.gname"/>&nbsp;</td>
		    		<td>专业必修课</td>	
		    		<td><s:property value="#request.myGraduateProject.teacherName"/>&nbsp;</td>	    	
		    		<td><s:property value="#request.myGraduateProject.grade"/>&nbsp;</td>
		    		<td><s:property value="#request.myGraduateProject.gxtime"/>&nbsp;</td>
		    		<td><input type="button" value="删除" onclick="deleteProject()"/></td>
		    	</tr>
		   </table>
		   </s:else> 	
	    	
	    
	    <br />
    </div>
  </body>
</html>
