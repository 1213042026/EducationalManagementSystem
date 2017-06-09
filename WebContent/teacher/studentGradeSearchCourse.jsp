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
    
    <title>学生成绩查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function jump(){
	 // alert('a');
	  location="teacherAction_studentGradeSearchCourse.action";
	}
	</script>
	
  </head>
  
  <body bgcolor="EFF6FF">
    
    <div style="padding-top: 10px; padding-left: 10px;">
     <hr size="5" color="84BDF6">
     <form action="teacherAction_studentGradeSearchCourseCondition.action" method="post" style="text-align: center">
      <span>学号：</span><input type="text" name="sidnum"/>
      <input type="submit" value="查询" >
      <input type="button" onclick="jump()" style="margin-left: 45px;" value="刷新"/>
     </form>
     
	    <table border="1" bordercolor="gray">
	    	<tr style="font: bolder;">
	    		<td width="500px">课程名称</td>	    		
	    		<td width="200px">学号</td>
	    	<td width="150px">姓名</td>
	    		
	    		<td width="200px">成绩</td>	    		
	    	</tr>
	    
	    	<s:iterator value="studentGradeSearchList">
		    	<tr style="color: 793AFF; ">
		    		<td><s:property value="gname"/>&nbsp;</td>
		    		<td><s:property value="sidnum"/>&nbsp;</td>
		    			<td><s:property value="sname"/>&nbsp;</td>
		    		<td><s:property value="grade"/>&nbsp;</td>		    	
		    	
		    	</tr>
	    		<tr>
	    			<td scope="3" style="padding-bottom: 20px;"></td>
	    		</tr>
	    	</s:iterator>
	    	
	    </table>
	    
	    
	<table align="left" '100%' border='0' cellspacing='0' cellpadding='0' 

bgcolor='#D7E4F7'>
		<tr>
			<td>&nbsp;<font style='color:#000000'> <a  

href='teacherAction_studentGradeSearchCourse.action?jumpPage=1'>首页</a>
		    </font> | <font style='color:#000000'> <a 

href="teacherAction_studentGradeSearchCourse.action?jumpPage=<s:property 

value='#request.pageNow-1'/>">前页</a>
		    </font> | <font style='color:#000000'> <a 

href="teacherAction_studentGradeSearchCourse.action?jumpPage=<s:property 

value='#request.pageNow+1'/>">后页</a>
		    </font> | <font style='color:#000000'> <a 

href="teacherAction_studentGradeSearchCourse.action?jumpPage=<s:property 

value='#request.pageCount'/>">末页</a>
		    </font> | <b><s:property value='#request.pageNow'/></b>

页/<b><s:property value='#request.pageCount'/></b>页 | 共<b><s:property 

value='#request.maxRowCount'/></b>条记录
		</tr>
	</table>
    </div>
    
   
  </body>
</html>
