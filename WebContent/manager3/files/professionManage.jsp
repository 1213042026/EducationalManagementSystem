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
    
    <title>My JSP 'professionManage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript">
		function deleteProfession( idnum ){
			var truthBeTold = window.confirm("确定要删除此专业吗？"); 
			if (truthBeTold) { 
				location="managerAction_deleteProfession.action?idnum=" + idnum;
			}
		}
		
		/*
		字段验证：
		#pro_name 表示取出id命名为pro_name的标签，
		.pro_name 表示取出class命名为pro_name的标签
		$("#pro_name")表示取出id命名为pro_name的标签对象　
	　　 $("#pro_name").val();表示取出id命名为pro_name的标签对象的value中的值
		*/
		function validate(){
			var name = $("#pro_name").val();
			
			if( name == null || name == "" ){
				alert("请输入专业名称!");
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
     		<div style="color: 793AFF; font-size: large"><b>专业管理</b></div>
     	</center>
     	<hr/>
     	
	    <table align="center">
	    	<tr style="font: bolder;">
	    		<td width="80px" align="center">专业编号</td>
	    		<td width="150px" align="center">专业名称</td>
	    		<td width="150px" align="center">备注</td>
	    	    <!-- 专业备注待加 -->
	    	</tr>
	    	<tr>
	    		<td colspan="6">
	    			<hr/>
	    		</td>
	    	</tr>
	    	<s:iterator value="professionList">
		    	<tr style="color: 793AFF; ">
		    	<td align="center"><s:property value="idnum"/></td>
		    	<td align="center"><s:property value="pro_name"/></td>
		    	<td align="center"><s:property value="remarks"/></td>
		<td><input type="button" value="删  除" onclick="deleteProfession('<s:property value="idnum"/>')"/></td>
		    	</tr>
	    		<tr>
	    			<td scope="6" style="padding-bottom: 20px;"></td>
	    		</tr>
	    	</s:iterator>
				<tr>
					<td colspan="6">
						<hr />
					</td>
				</tr>
				
				
			</table>
			
			<form action="managerAction_insertProfession.action" method="post" style="text-align: center;">
		<span>专业名称: </span><input type="text" name="pro_name" id="pro_name">
		<input type="submit" onclick="return validate();" value="添加">
			</form>
    </div>
    
   
  </body>
</html>
