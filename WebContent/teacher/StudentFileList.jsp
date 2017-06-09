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
    
    <title>My JSP 'fileUpLoadTest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery.js"></script>
<SCRIPT type="text/javascript">


 
 function delGraduation(sname){
// sidnum为学号，sname为学生上传的文件
   var sidnum = $("#sidnum").val();
  // alert(gidnum);
  // alert(gname);
  // alert(gidnum);
  location="teacherAction_delStudentUpFile.action?sidnum=" + sidnum+"&sname=" + sname;
 }
 
 function downFile(sname) {
    // alert("a");
      var sidnum = $("#sidnum").val();
  location="teacherAction_downStudentFile.action?sidnum=" + sidnum+"&sname=" + sname;
 }
 
 
</SCRIPT>
  </head>
  <body bgcolor="EFF6FF">
    
    <div style="padding-top: 10px; padding-left: 10px;">
     <hr size="5" color="84BDF6">
	    <table border="1" bordercolor="gray">
	    	<tr style="font: bolder;">
	    		<td width="300px">文件名</td>	    		
	    		
	    	</tr>
	

	    	<s:iterator value="fileList" status="rowstatus">
		    	<tr style="color: 793AFF; ">
		    	<td  >  <a><s:property value="fileList[#rowstatus.index]"/></a></td>
		 	    
		    		<td><input type="button" value="下载" onclick="downFile('<s:property value="fileList[#rowstatus.index]"/>')"/></td>
		    		<td><input type="button" value="删除" onclick="delGraduation('<s:property value="fileList[#rowstatus.index]"/>')" /></td>
		    		
		    	</tr>
	    		<tr>
	    			<td scope="3" style="padding-bottom: 20px;"></td>
	    		</tr>
	    	</s:iterator>
	    	
	    </table>
	    <br/> 
	    
	       <input type="hidden"  id="sidnum" name="sidnum" value="<s:property value="#request.sidnum"/>">
    </div>
    
   
  </body>
 
</html>
