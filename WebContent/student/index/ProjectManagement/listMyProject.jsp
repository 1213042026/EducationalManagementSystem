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
    
    <title>��ʾѧ��ѡ��ı�ҵ���</title>
    
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
	��var ajax=false;
	��try {
	����ajax = new ActiveXObject("Msxml2.XMLHTTP");
	��} catch (e) {
	����try {
	������ajax = new ActiveXObject("Microsoft.XMLHTTP");
	����} catch (E) {
	������ajax = false;
	����}
	��}
	��if (!ajax && typeof XMLHttpRequest!='undefined') {
	����ajax = new XMLHttpRequest();
	��}
	��return ajax;
	}


	function  query()
	{
		��//��Ҫ����Ajax��URL��ַ
		��var url = "studentAction_selectGraduationTime.action";
		��//ʵ����Ajax����
		��var ajax = InitAjax();
		��//ʹ��Get��ʽ��������
		��ajax.open("GET", url, false);
		��//��ȡִ��״̬
		��ajax.onreadystatechange = function() 
		{
		����//���ִ����״̬��������ô�Ͱѷ��ص����ݸ�ֵ������ָ���Ĳ�
		����if (ajax.readyState == 4 && ajax.status == 200)
			 {
		������  res=ajax.responseText;

		��	 }
		}
		��//���Ϳ�
		��ajax.send(null);
	}
	

	
	function deleteProject()
	{
		query();
		if(res=='1')
			alert("���ڲ��Ǳ�ҵ���ѡ��ʱ�䣬����ɾ����");
		
		else
		{
			var truthBeTold = window.confirm("ȷ��Ҫɾ����ҵ�����");
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
    <td height="24" nowrap><font color="#FFFFFF"><img src="student/iconsIMG/Explain.gif" width="18" height="18" border="0" align="absmiddle">&nbsp;<strong>��ǰλ�ã��ҵı�ҵ���</strong></font></td>
  </tr>
  <tr>
    <td align="center" nowrap  bgcolor="#EBF2F9">
	
    </td>    
  </tr>
</table>
<br />
     <hr size="5" color="84BDF6">
	     
	     
	     <s:if test="#request.myGraduateProject==null">
	          <p align="center">����û��ѡ���ҵ��ƣ�����ѡ��</p>
	     </s:if>
	     <s:else>
	        <table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
		    <tr>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>ѡ������</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>�γ�����</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>ָ����ʦ</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>�ɼ�</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9" ><center><font color="#FFFFFF"><strong>ѡ��ʱ��</strong></font></center></td>
		      <td  bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>ɾ��</strong></font></center></td>
		     
		    </tr>
	    
		    	<tr bgcolor="#FFFFFF" onmouseover="this.bgColor='f1f1f1'" onmouseout="this.bgColor='FFFFFF'"  style='cursor:hand'  title="" align="center">
		    		<td><s:property value="#request.myGraduateProject.gname"/>&nbsp;</td>
		    		<td>רҵ���޿�</td>	
		    		<td><s:property value="#request.myGraduateProject.teacherName"/>&nbsp;</td>	    	
		    		<td><s:property value="#request.myGraduateProject.grade"/>&nbsp;</td>
		    		<td><s:property value="#request.myGraduateProject.gxtime"/>&nbsp;</td>
		    		<td><input type="button" value="ɾ��" onclick="deleteProject()"/></td>
		    	</tr>
		   </table>
		   </s:else> 	
	    	
	    
	    <br />
    </div>
  </body>
</html>
