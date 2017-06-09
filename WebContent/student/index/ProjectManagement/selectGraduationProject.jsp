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
    
    <title>学生对毕业设计进行选题</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery.js"></script>	
<STYLE type=text/css>


A.proNameId:link {
	PADDING-LEFT: 4px;
	COLOR: #0055bb; 
	TEXT-DECORATION: none
}

A.proNameId:visited {
	PADDING-LEFT: 4px;
	COLOR:#000000;
	TEXT-DECORATION: none
}

A.proNameId:hover {
	PADDING-LEFT: 4px;
	COLOR: #ff0000;
	TEXT-DECORATION: none
}

td{
 font-size:17px;
 font-family: 宋体, serif
}

.STYLE1{
 color:#FF0000;
 font-size:17px;
 
}

</STYLE>

	<script type="text/javascript">

	var res="";
	function InitAjax()
	{
	　var ajax=false;
	　try {
	　　ajax = new ActiveXObject("Msxml2.XMLHTTP");
	　} catch (e) {
	　　try {
	　　　ajax = new ActiveXObject("Microsoft.XMLHTTP");
	　　} catch (E) {
	　　　ajax = false;
	　　}
	　}
	　if (!ajax && typeof XMLHttpRequest!='undefined') {
	　　ajax = new XMLHttpRequest();
	　}
	　return ajax;
	}


	function  query()
	{
		　//需要进行Ajax的URL地址
		　var url = "studentAction_isApplying.action";
		　//实例化Ajax对象
		　var ajax = InitAjax();
		　//使用Get方式进行请求
		　ajax.open("GET", url, false);
		　//获取执行状态
		　ajax.onreadystatechange = function() 
		{
		　　//如果执行是状态正常，那么就把返回的内容赋值给上面指定的层
		　　if (ajax.readyState == 4 && ajax.status == 200)
			 {
		　　　  res=ajax.responseText;

		　	 }
		}
		　//发送空
		　ajax.send(null);
	}

	function selectProject(idnum,recordTime)
	{
		 
		query();
		if(res=='1')
			alert("你已经申请自拟选题，不能再选择毕业设计！");
		
		else
		{
			var truthBeTold = window.confirm("确定要选择此毕业设计吗？");
			if (truthBeTold) { 
				location="studentAction_addGraduateProject.action?idnum=" + idnum+"&recordTime="+recordTime;
			}
		}
		
	}

	function disProjectFile(idnum)
	{
		location="teacherAction_disTeacherFileList.action?idnum=" + idnum+"&user="+"student";
	}

	function showDetail(idnum,gname)
	{
		//alert(teacherId);
		location="studentAction_showProjectDetail.action?idnum=" + idnum+"&gname="+gname;
	}
	
	//function check(jumpPage,tea_name,glevel){
	//studentAction_index.action?jumpPage=&tea_name=&glevel=
	 // alert(jumpPage);
	 // alert(tea_name);
	 //   alert(glevel);
	 //   gelvela=encodeURI(glevel);
	 // location="studentAction_index.action?jumpPage="+jumpPage+"&tea_name="+tea_name+"&glevel="+gelvela;
	//}
	</script>
  </head>
  
  <body >
    	<div style="padding-top: 10px; padding-left: 10px;">
    	<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
  <tr>
    <td height="24" nowrap><font size='2' color="#FFFFFF"><img src="student/iconsIMG/Explain.gif" width="18" height="18" border="0" align="absmiddle">&nbsp;<strong>所有选题：查看所有选题、选择自己感兴趣的选题（<span class="STYLE3">每人限选</span>1<span class="STYLE3">题，每题最多</span>10<span class="STYLE3">人选！</span>）</strong></font></td>
  </tr>
  <tr>
    <td align="center" nowrap  bgcolor="#EBF2F9">
	
	<form id="form1" name="form1" method="post" action="studentAction_index">
        难易程度：
            <select name="glevel" id="glevel">
			    <option  >请选择</option>
         		<option value="easy" >容易</option>
                <option  value="often">一般</option>
                <option  value="diff">困难</option>
          </select>
    
	指导老师：
          <select name="tea_name" id="tea_name" >
		  <option value="">请选择</option>
            <s:iterator  value="teacherList">
            	<option value="<s:property value="idnum"/>"  >
         		<s:property value="name"/>
         		</option> 
            </s:iterator>
          </select>
			
	　
	<input type="submit" name="Submit" value=" 检 索 " />
      </form>
	
	
    </td>    
  </tr>
</table>
 <div align="center"  class="STYLE1">查看毕业设计详情，请单击毕业设计名称，查看毕业设计的相关资料文档，请点击下载按钮</div>
	     <table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1" align="center" >
		    <tr align="center">
		      <td  bgcolor="#8DB5E9"><font color="#FFFFFF"><strong>ID</strong></font></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><font  color="#FFFFFF"><strong>选题名称</strong></font></td>
		      <td nowrap="nowrap" bgcolor="#8DB5E9"><font   color="#FFFFFF"><strong>指导老师</strong></font></td>
		      <td  bgcolor="#8DB5E9"><strong><font color="#FFFFFF">难易程度</font></strong></td>
		      <td  bgcolor="#8DB5E9"><strong><font  color="#FFFFFF">可选人数</font></strong></td>
		      <td  bgcolor="#8DB5E9"><font  color="#FFFFFF"><strong>剩余容量</strong></font></td>
		      <td  bgcolor="#8DB5E9"><font  color="#FFFFFF"><strong>录入时间</strong></font></td>
		      <td  bgcolor="#8DB5E9"><font   color="#FFFFFF"><strong>选题须知</strong></font></td>
		      <td  bgcolor="#8DB5E9"><font   color="#FFFFFF"><strong>选择</strong></font></td>
		     
		    </tr>
	    
	    	<s:iterator value="graduationList">
		    	<tr bgcolor="#FFFFFF" onmouseover="this.bgColor='f1f1f1'" onmouseout="this.bgColor='FFFFFF'"  style='cursor:hand;font-size:14px'  title="" align="center">
		    		<td ><font color="#000000"  ><s:property value="idnum"/>&nbsp;</font></td>
		    		<td ><font color="#000000" ><a onclick="showDetail('<s:property value="idnum"/>','<s:property value="gname"/>')"><s:property value="gname" />&nbsp;</a></font></td>
		    		<td><font color="#000000" ><s:property value="teacherId"/>&nbsp;</font></td>	    	
		    		<td><font color="#000000" ><s:property value="glevel"/>&nbsp;</font></td>
		    		<td><font color="#000000" ><s:property value="gcount"/>&nbsp;</font></td>
		    		<td><font color="#000000" ><s:property value="rcount"/>&nbsp;</font></td>
		    		<td><font color="#000000" ><s:property value="recordTime"/>&nbsp;</font></td>
		    		<td><input type="button" value="下 载" onclick="disProjectFile('<s:property value="idnum"/>')"/></td>
				    <td><input type="button" value="选 择" onclick="selectProject('<s:property value="idnum"/>',<s:property value="recordTime"/>)"/></td>
		    	</tr>
		    	
	    	</s:iterator>
  <tr bgcolor='#D7E4F7'> 
    <td height=25 colspan="10"> 
    <s:if test="#request.test!='test'">
	<table align="right" '100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#D7E4F7'>
		<tr>
			<td>&nbsp;<font style='color:#000000'> <a  href='studentAction_selectGraduationProject.action?jumpPage=1'>首页</a>
		    </font> | <font style='color:#000000'> <a href="studentAction_selectGraduationProject.action?jumpPage=<s:property value='#request.pageNow-1'/>">前页</a>
		    </font> | <font style='color:#000000'> <a href="studentAction_selectGraduationProject.action?jumpPage=<s:property value='#request.pageNow+1'/>">后页</a>
		    </font> | <font style='color:#000000'> <a href="studentAction_selectGraduationProject.action?jumpPage=<s:property value='#request.pageCount'/>">末页</a>
		    </font> | <b><s:property value='#request.pageNow'/></b>页/<b><s:property value='#request.pageCount'/></b>页 | 共<b><s:property value='#request.maxRowCount'/></b>条记录
		</tr>
	</table></s:if>
	<s:else>
		<table align="right" '100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#D7E4F7'>
		<tr>
			<td>&nbsp;<font style='color:#000000'> <a  href="studentAction_index.action?jumpPage=1&tea_name=<s:property value='#request.tea_name'/>&glevel=<s:property value='#request.glevel'/>">首页</a>
		    </font> | <font style='color:#000000'> <a href="studentAction_index.action?jumpPage=<s:property value='#request.pageNow-1'/>&tea_name=<s:property value='#request.tea_name'/>&glevel=<s:property value='#request.glevel'/>">前页</a>
		    </font> | <font style='color:#000000'> <a href="studentAction_index.action?jumpPage=<s:property value='#request.pageNow+1'/>&tea_name=<s:property value='#request.tea_name'/>&glevel=<s:property value='#request.glevel'/>">后页</a>
		    </font> | <font style='color:#000000'> <a href="studentAction_index.action?jumpPage=<s:property value='#request.pageCount'/>&tea_name=<s:property value='#request.tea_name'/>&glevel=<s:property value='#request.glevel'/>">末页</a>
		    </font> | <b><s:property value='#request.pageNow'/></b>页/<b><s:property value='#request.pageCount'/></b>页 | 共<b><s:property value='#request.maxRowCount'/></b>条记录
		</tr>
	</table>
	</s:else>
  
</table>

</BODY>
</HTML>
