<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">

  </head>
  
  <body>
    <center><br><br>
   <table width="555" height="119" border="1" cellpadding="-2" cellspacing="-2" align="center">
  
</table>
<table width="555" border="0" cellpadding="-2" cellspacing="-2" >
  <tr>
    <td width="817" valign="center">
	     <table width="100%"  border="1" align="right" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" bordercolorlight="#FFCCCC" bordercolordark="#FFFFFF">
        <tr>
          <td width="29%" height="27"><div align="center">����</div></td>
          <td width="14%" height="27"><div align="center">�ϴ���</div></td>
          <td width="23%" height="27"><div align="center">�ϴ�ʱ��</div></td>        
          <td width="6%" height="27"><div align="center">����</div></td>
          </tr>
        <tr>
          <td align="center">�¸�</td>
          <td align="center">С��</td>
          <td><div align="center">2009��6��23��</div></td>        
          <td><div align="center">
		      <A HREF="teacherAction_downfiletest1.action?path=<%=getServletContext().getRealPath("image/��ѧ����ְ����ģ���ȫ.doc") %>">
			  ����</A>
          </div></td>
          </tr>   
           <tr>
          <td align="center">down1.bmp</td>
          <td align="center">С��</td>
          <td><div align="center">2009��6��23��</div></td>        
          <td><div align="center">
		      <A HREF="teacherAction_downfiletest1.action?path=<%=getServletContext().getRealPath("image/WebRoot.rar") %>">
			  ����</A>
          </div></td>
          </tr>                 
      </table></td>
  </tr>
</table>
</center>
  </body>
</html>
