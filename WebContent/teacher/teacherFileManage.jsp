<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>老师文件管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
		function fileManage(idnum){
		//这里的id是毕业设计编号
		//alert(idnum);
				//location="teacherAction_listTeacherFile.action?idnum=" + idnum;
				location="teacherAction_disTeacherFileList.action?idnum=" + idnum;
			
		}
	</script>
  </head>
  
  <body bgcolor="EFF6FF">
    
    <div style="padding-top: 10px; padding-left: 10px;">
     <hr size="5" color="84BDF6">
	    <table border="1" bordercolor="gray">
	    	<tr style="font: bolder;">
	    		<td width="100px">编号</td>	    		
	    		<td width="700px">名称</td>	    		
	    	</tr>
	    
	    	<s:iterator value="graduateaList">
		    	<tr style="color: 793AFF; ">
		    		<td><s:property value="idnum"/>&nbsp;</td>
		    		<td><s:property value="gname"/>&nbsp;</td>		    	
		    		<td><input type="button" value="显示文件列表" onclick="fileManage('<s:property value="idnum"/>')"/></td>
		    	</tr>
	    		<tr>
	    			<td scope="3" style="padding-bottom: 20px;"></td>
	    		</tr>
	    	</s:iterator>
	    	
	    </table>
	    <table align="left" '100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#D7E4F7'>
		<tr>
			<td>&nbsp;<font style='color:#000000'> <a  href='teacherAction_teacherFileManage.action?jumpPage=1'>首页</a>
		    </font> | <font style='color:#000000'> <a href="teacherAction_teacherFileManage.action?jumpPage=<s:property value='#request.pageNow-1'/>">前页</a>
		    </font> | <font style='color:#000000'> <a href="teacherAction_teacherFileManage.action?jumpPage=<s:property value='#request.pageNow+1'/>">后页</a>
		    </font> | <font style='color:#000000'> <a href="teacherAction_teacherFileManage.action?jumpPage=<s:property value='#request.pageCount'/>">末页</a>
		    </font> | <b><s:property value='#request.pageNow'/></b>页/<b><s:property value='#request.pageCount'/></b>页 | 共<b><s:property value='#request.maxRowCount'/></b>条记录
		</tr>
	</table>
    </div>
    
   
  </body>
</html>
