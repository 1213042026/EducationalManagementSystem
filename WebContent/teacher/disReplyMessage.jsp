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
    
    <title>My JSP 'disReplyMessage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
 <script type="text/javascript" src="js/jquery.js"></script>
  <script type="text/javascript">
		function reply( idnum ){
			var truthBeTold = window.confirm("确定回复留言？"); 
			if (truthBeTold) {
			alert(idnum); 
			//var att = $(('#'+idnum).text());//可以得到输入域中的内容
			//alert(att);
			var attitudeObj = document.getElementById(idnum);//	
			var attitude = attitudeObj.innerHTML;	
			alert(attitude);
				location="teacherAction_disReplyMessageSubmit.action?idnum=" + idnum +"&rcontent=" + attitude;
			}
		}	
		

  		function replya(sidnum,gidnum,ggidnum,applyid){
	
		//	var attitudeObj = document.getElementById("attitude");			
			//var attitude = attitudeObj.innerHTML;			
		//	var att = $("#attitude").text();//可以得到输入域中的内容
			
			//	alert(att);
			
		//	att   =   att.replace(/\s+/g,""); //去除空格
		//	if( att == null || att == ""){
		
			//	alert("请输入选题申请介绍！");
			//	return false;
			//}
		

		//	location="teacherAction_updateButtonGraduation.action?sidnum=" + sidnum 
		//	+"&gidnum=" + gidnum+"&ggidnum=" + ggidnum +"&applyid=" + applyid
		//	+"&att=" + att+"&diff=" + diff;
		//	return true;
		}
	</script>
  </head>
  
  <body>
   <hr size="5" color="84BDF6">
     	<center>
     		<div style="color: 793AFF; font-size: large"><b>回复留言</b></div>
     	</center>
     	<hr/>
   	<s:iterator value="disMessageVO">
		    	<span style="font-weight:bold;font-size: 17px;font-family:黑体;">学生：<br></span><span style="font-size: 15px; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="idnum"/></span><br>
		        <span  style="font-weight:bold;font-size: 17px;font-family:黑体;">主题：<br></span><span style="font-size: 15px; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="title"/></span><br>
		        <span  style="font-weight:bold;font-size: 17px;font-family:黑体;">留言内容：<br>
		        </span><span style="font-size: 15px; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="content"/></span><br>
		        <span  style="font-weight:bold;font-size: 17px;font-family:黑体;">留言时间：<br></span><span style="font-size: 15px; ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="mtime"/></span><br>
		        
		       
		        <TEXTAREA name="<s:property value="idnum" />" id="<s:property value="idnum" />" ></TEXTAREA>
		        <input type="button" onclick="reply('<s:property value="idnum" />')" value="回复">
		       
		        
		       <hr>
	    	</s:iterator>
  </body>
</html>
