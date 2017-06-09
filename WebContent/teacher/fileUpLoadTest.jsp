<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()

+path+"/";
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

 function validate() { 
   var filename = document.all('image').value; //获得上传文件的物理路径
    if(filename =='')
     {
       alert("你还没有选择要上传的文件"); 
return false;
     }
   return true;
 }
 
 function delGraduation(gname){
// alert("a");
   var gidnum = $("#graID").val();
  var truthBeTold = window.confirm("确定要删除此文件吗？");
	 if (truthBeTold)
	  { 
  		location="teacherAction_delTeacherUpFile.action?gidnum=" + gidnum+"&gname=" + 

gname;
  	  }
  
 }
 
 function downFile(gname) {
    // alert("a");
      var gidnum = $("#graID").val();
  location="teacherAction_downfiletest1.action?gidnum=" + gidnum+"&gname=" + gname;
 }
 
 
</SCRIPT>
  </head>
  <body bgcolor="EFF6FF">

     <p>
  <style>
.fu_list {
	width:600px;
	background:#86C2E7;
	font-size:12px;
}
.fu_list td {
	padding:5px;
	line-height:20px;
	background-color:#fff;
}
.fu_list table {
	width:100%;
	border:1px solid #ebebeb;
}
.fu_list thead td {
	background-color:#f4f4f4;
}
.fu_list b {
	font-size:14px;
}
</style>
</p>

<p>&nbsp;</p>
    
 <form action="teacherAction_fileUpLoadTest.action" enctype="multipart/form-data"
   method="post" >
	 <table width="67%" border="0" align="center" cellspacing="1" class="fu_list">
    <thead>
      <tr>
        <td colspan="2" style="background-color: #86C2E7;"><b>文件列表</b></td>
      </tr>
    </thead>
	<tbody>
	    	<s:iterator value="fileList" status="rowstatus">
		    	<tr >
		    	<td  > <span style="WIDTH: 70%;"  > <a><s:property value="fileList

[#rowstatus.index]"/></a></span>
		 	    <span   >
		    		<input type="button" value="下载" onclick="downFile('<s:property value="fileList[#rowstatus.index]"/>')"/>
		    		<s:if test="#request.studentID!='value'"> 
		    		<input type="button" value="删除" onclick="delGraduation

('<s:property value="fileList[#rowstatus.index]"/>')" />
		    		</s:if></span></td>
		    	</tr>
	    		<tr>
	    			<td scope="3" style="padding-bottom: 20px;"></td>
	    		</tr>
	    	</s:iterator>
	    	<input type="hidden"  id="graID" name="graID" value="<s:property value="#request.graID"/>">
	<s:if test="#request.studentID!='value'">     	
	    <tr>
         
        <thead>
      <tr>
        <td colspan="2" style="background-color: #86C2E7;border-color: #86C2E7"><b>添加文件

</b></td>
      </tr>
    </thead>
      
        <td><input type="file" id="image" name="image"/>
           </td>
      </tr>
      <tr>
        <td colspan="2" style="color:gray">注意：文件最大不能超过100M</td>
      </tr>
      <tr>
        <td colspan="2" align="center" id="idMsg"> <input type="submit" onclick="return validate()" value="开始上传"/> <input type="hidden"  id="graID" name="graID" value="<s:property value="#request.graID"/>">

</td>
      </tr> </s:if>
    </tbody>
  </table>
</form>
  </body>
 
</html>
