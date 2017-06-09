<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>显示选修课程界面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
		function display()
		{
			location="studentAction_listAllAlreadyCourse.action";
		}
	
	</script>

  </head>
  
  <body >
    	<div style="padding-top: 10px; padding-left: 10px;">
    	<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
  <tr>
    <td height="24" nowrap><font color="#FFFFFF"><img src="student/iconsIMG/Explain.gif" width="18" height="18" border="0" align="absmiddle">&nbsp;<strong>当前位置：选修课程</strong></font></td>
  </tr>
  <tr>
    <td align="center" nowrap  bgcolor="#EBF2F9" height="24">
	<form id="form1" name="form1" method="post" action="studentAction_indexCourse">
      <div STYLE=" align:right;width:100;height:10; " >  
      <input type="button" name="diaplayAll"  onclick="display()" value="显示全部 " />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       	学    年：
            <select name="allXn" id="allXn">
            <s:iterator  value="grades" status="rowstatus">
            	<option value="<s:property value="grades[#rowstatus.index]"/>"  ><s:property value="grades[#rowstatus.index]"/>学年</option> 
            </s:iterator>
          </select>
      
        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学   期：
          <select name="term_name" id="term_name" >
           		<option  >请选择</option>
         		<option  >1</option>
                <option  >2</option>
          </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" name="Submit" value=" 检   索 " />
	</div>	
      </form>
    </td>    
  </tr>
</table>
<br />
	     <table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
	
		    <tr>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>课程名字</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>课程类型</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>授课老师</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><center><font color="#FFFFFF"><strong>选课学期</strong></font></center></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9" ><center><font color="#FFFFFF"><strong>选课学年</strong></font></center></td>
		     
		    </tr>
	    
	    
	   
		    <s:iterator  var="s" value="classList">
		     
		        <tr bgcolor="#FFFFFF" onmouseover="this.bgColor='f1f1f1'" onmouseout="this.bgColor='FFFFFF'"  style='cursor:hand'  title="" align="center">
		    		<td><s:property value="#s.className"/>&nbsp;</td>
		    		<td><s:property value="#s.classType"/>&nbsp;</td>
		    		<td><s:property value="#s.teacherId"/>&nbsp;</td>	    	
		    		<td><s:property value="#s.term"/>&nbsp;</td>
		    		<td><s:property value="#s.xueNian"/>&nbsp;</td>
		    		
		    	</tr>
		    	
	    	</s:iterator>
	    	<s:if test="#request.test!='test'">
		 	  <tr bgcolor='#D7E4F7'> 
    <td height=25 colspan="10"> 
    
	<table align="right" '100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#D7E4F7'>
		<tr>
			<td>&nbsp;<font style='color:#000000'> <a  href='studentAction_listAllAlreadyCourse.action?jumpPage=1'>首页</a>
		    </font> | <font style='color:#000000'> <a href="studentAction_listAllAlreadyCourse.action?jumpPage=<s:property value='#request.pageNow-1'/>">前页</a>
		    </font> | <font style='color:#000000'> <a href="studentAction_listAllAlreadyCourse.action?jumpPage=<s:property value='#request.pageNow+1'/>">后页</a>
		    </font> | <font style='color:#000000'> <a href="studentAction_listAllAlreadyCourse.action?jumpPage=<s:property value='#request.pageCount'/>">末页</a>
		    </font> | <b><s:property value='#request.pageNow'/></b>页/<b><s:property value='#request.pageCount'/></b>页 | 共<b><s:property value='#request.maxRowCount'/></b>条记录
		</tr>
	</table></td></tr></s:if>
	    </table>
	    <br />
    </div>
  </body>
</html>
