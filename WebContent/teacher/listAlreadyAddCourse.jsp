<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>查看已添加课程</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
<SCRIPT type="text/javascript">

function tdCheck(td){
//td.style.color = 'gray'; 
//td.style.cursor = 'hand';
//td.style.text-decoration = 'underline';
}
</SCRIPT>
<STYLE type="text/css">
b{

}
</STYLE>
	</head>

	<body bgcolor="EFF6FF">

		<div style="padding-top: 10px; padding-left: 10px;">
			<hr size="5" color="84BDF6">
			<table border="1" bordercolor="gray">
				<tr style="font: bolder;">
					<td width="300px">
						名称
					</td>
					<td width="200px">
						类型
					</td>
					<td width="100px">
						学期
					</td>
				
					<td width="200px">
						所属年级
					</td>
					<td width="200px">
						专业
					</td>
					<td width="200px">
						可选人数
					</td>
					<td width="200px">
						剩余人数
					</td>
					<td width="700px">
						录入课程时间
					</td>
				</tr>

				<s:iterator value="courseList">
					<tr style="color: 793AFF;">
						<td>
							<s:property value="className" />
							&nbsp;
						</td>
						<td><s:property value="classType" />
								&nbsp;
							</td>
					
						<td>
							<s:property value="term" />
							&nbsp;
						</td>
						
							<td>
							<s:property value="grade" />
							&nbsp;
						</td>
						
							<td>
							<s:property value="professionId" />
							&nbsp;
						</td>									
					
					
						<td>
							<s:property value="kkCount" />
							&nbsp;
						</td>
								<td>
							<s:property value="ssCount" />
							&nbsp;
					   </td>
					   	</td>
								<td>
							<s:property value="recordTime" />
							&nbsp;
					   </td>
					</tr>
					<tr>
						<td scope="8" style="padding-bottom: 20px;"></td>
					</tr>
				</s:iterator>

			</table>
			<table align="left" '100%' border='0' cellspacing='0' cellpadding='0' bgcolor='#D7E4F7'>
		<tr>
			<td>&nbsp;<font style='color:#000000'> <a  href='teacherAction_displayAlreadyAddCourse.action?jumpPage=1'>首页</a>
		    </font> | <font style='color:#000000'> <a href="teacherAction_displayAlreadyAddCourse.action?jumpPage=<s:property value='#request.pageNow-1'/>">前页</a>
		    </font> | <font style='color:#000000'> <a href="teacherAction_displayAlreadyAddCourse.action?jumpPage=<s:property value='#request.pageNow+1'/>">后页</a>
		    </font> | <font style='color:#000000'> <a href="teacherAction_displayAlreadyAddCourse.action?jumpPage=<s:property value='#request.pageCount'/>">末页</a>
		    </font> | <b><s:property value='#request.pageNow'/></b>页/<b><s:property value='#request.pageCount'/></b>页 | 共<b><s:property value='#request.maxRowCount'/></b>条记录
		</tr>
	</table>
		</div>


	</body>
</html>
