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
    
    <title>My JSP 'modifyManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		span{
		color: 793AFF;
		padding-bottom: 20px;
		font-weight: bold;}
	</style>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		
		function validate(){
			var name = $("#name").val();
			var pass =  $("#password").val() ;
			var repass = $("#repassword").val();
			var idcard = $("#idcard").val();
			var address = $("#address").val();
			var nation = $("#nation").val();
			var scientific = $("#scientific").val();
			var title = $("#title").val();
			if( name == null || name == "" ){
				alert("请输入教师姓名!");
				return false;
			}
			if( pass == null || pass == ""){
				alert("请输入密码！");
				return false;
			}
			if( repass != pass ){
				alert("输入密码与确认密码不一致，请重新输入");
				$(".password").val("");
				$(".repassword").val("");
				return false;
			}
			if( idcard == null || idcard == ""){
				alert("请输入身份证号码！");
				return false;
			}
			if( address == null || address == "" ){
				alert("请输入教师地址！");
				return false;
			}
			if( nation == null || nation == "" ){
				alert("请输入教师民族！");
				return false;
			}
			if( scientific == null || scientific == "" ){
				alert("请输入教师学历！");
				return false;
			}
			if(　title == null || title == "" ){
				alert("请输入教师职称！");
				return false;
			}
			return true;
		}
	</script>
  </head>
  
  <body bgcolor="EFF6FF">
    <div style="padding-top: 10px; padding-left: 10px;">
    	<hr size="5" color="84BDF6">
     	<center>
     		<div style="color: 793AFF; font-size: large"><b>修改教师</b></div>
     	</center>
     	<hr/>
     	<div style="margin-left: 100px;">
     		<form action="managerAction_updateTeacher.action" method="post">
     			<span>教师编号：</span><input type="text" readonly="readonly" name="idnum" id="idnum" value="<s:property value='#request.teacherVO.idnum'/>" style="background: gray;"/><br/>
     			<span>教师姓名：</span><input type="text" name="name" id="name" value="<s:property value='#request.teacherVO.name'/>"/><br/>
     			<span>教师密码：</span><input class="password" type="password" name="password" id="password" value="<s:property value='#request.teacherVO.password'/>"/><br/>
     			<span>确认     密码：</span><input class="repassword" type="password" name="repassword" id="repassword" value="<s:property value='#request.teacherVO.password'/>"/><br/>
     			<span>性               别：</span><select name="sex" id="sex"><option value="1" >女</option><option value="2" selected="selected">男</option></select><br/>
     				<span>专　　业：</span><select name="pro_name" id="pro_name">
						<s:iterator value="professionList">
							<option value="<s:property value='idnum' />"><s:property value="pro_name" /></option>
						</s:iterator>
					</select>
					<br/>
     			<span>身份证号码：</span><input type="text" name="idcard" id="idcard" value="<s:property value='#request.teacherVO.idcard'/>"/><br/>
     			<span>地　　　 址: </span><input type="text" name="address" id="address"value="<s:property value='#request.teacherVO.address'/>"/><br/>
     			<span>民               族: </span><input type="text" name="nation" id="nation"value="<s:property value='#request.teacherVO.nation'/>"/><br/>
     			<span>学　　　历: </span><input type="text" name="scientific" id="scientific" value="<s:property value='#request.teacherVO.scientific'/>"/><br/>
     			<span>职　　　称: </span><input type="text" name="title" id="title"value="<s:property value='#request.teacherVO.title'/>"/><br/>
     			
     		     			<input type="submit" onclick="return validate();" value="修改">  <input style="margin-left: 100px" type="reset" value="重置">
     		</form>
     	</div>
    </div>
  </body>
</html>
