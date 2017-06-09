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
    
    <title>查看已添加毕业设计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function modifyGraduation( idnum ){
		//alert(idnum);
			var truthBeTold = window.confirm("确定要修改此毕业设计吗？"); 
			if (truthBeTold) { 
				location="teacherAction_disupdateAlreadyAdd.action?idnum=" + idnum;
			}
		}
	</script>
  </head>
  
  <body bgcolor="EFF6FF">
    
    <div style="padding-top: 10px; padding-left: 10px;">
     <hr size="5" color="84BDF6">
	    <table border="1" bordercolor="gray">
	    	<tr style="font: bolder;">
	    		<td width="100px">学年</td>	    		
	    		<td width="700px">名称</td>
	    		<td width="250px">课程性质</td>	    	
	    	
	    		<td width="200px">可选人数</td>
	    		<td width="200px">剩余容量</td>
	    			<td width="150px">难度 </td>
	    		<td width="200px">录入时间</td>
	    		<td width="700px">选题须知</td>
	    	</tr>
	    
	    	<s:iterator value="graduateaList">
		    	<tr style="color: 793AFF; ">
		    		<td><s:property value="gyear"/>&nbsp;</td>
		    		<td><s:property value="gname"/>&nbsp;</td>
		    		<td>专业必修课</td>		    	
		    	
		    		<td><s:property value="gcount"/>&nbsp;</td>
		    		<td><s:property value="rcount"/>&nbsp;</td>
		    			<td><s:property value="glevel"/>&nbsp;</td>
		    		<td><s:property value="recordTime"/>&nbsp;</td>
		    		<td><s:property value="remarks"/>&nbsp;</td>
		    		<td><input type="button" value="修  改" onclick="modifyGraduation('<s:property value="idnum"/>')"/></td>
		    	</tr>
	    		<tr>
	    			<td scope="8" style="padding-bottom: 20px;"></td>
	    		</tr>
	    	</s:iterator>
	    	
	    </table>
	    	<table align="left" '100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#D7E4F7'>
		<tr>
			<td>&nbsp;<font style='color:#000000'> <a  href='teacherAction_updateAlreadyAdd.action?jumpPage=1'>首页</a>
		    </font> | <font style='color:#000000'> <a href="teacherAction_updateAlreadyAdd.action?jumpPage=<s:property value='#request.pageNow-1'/>">前页</a>
		    </font> | <font style='color:#000000'> <a href="teacherAction_updateAlreadyAdd.action?jumpPage=<s:property value='#request.pageNow+1'/>">后页</a>
		    </font> | <font style='color:#000000'> <a href="teacherAction_updateAlreadyAdd.action?jumpPage=<s:property value='#request.pageCount'/>">末页</a>
		    </font> | <b><s:property value='#request.pageNow'/></b>页/<b><s:property value='#request.pageCount'/></b>页 | 共<b><s:property value='#request.maxRowCount'/></b>条记录
		</tr>
	</table>
    </div>
    
   
  </body>
</html>
