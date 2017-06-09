<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" >
		function validate()
		{
			var name=$("#name_temp").val();
			var idcard=$("#idcard_temp").val();
			var address=$("#address_temp").val();

			if( (name == null || name == "" )&&( address==null||address=="")&&( idcard == null || idcard == "")){
				alert("请输入要修改的内容！");
				return false;
			}
			
			return true;
		}
</script>

<title>申请修改信息</title>
</head>


<body>
<table width="100%" border="0" cellpadding="3" cellspacing="1" bgcolor="#6298E1">
  <tr>
    <td height="24" nowrap><font color="#FFFFFF"><img src="student/iconsIMG/Explain.gif" width="18" height="18" border="0" align="absmiddle">&nbsp;<strong>个人中心：修改个人资料</strong></font></td>
  </tr>
  <tr>
    <td height="40" align="center" nowrap  bgcolor="#EBF2F9">选题流程：①认真填写个人信息--&gt;②浏览选题--&gt;③选择自己感兴趣的选题--&gt;④联系指导老师完成毕业设计</td>    
  </tr>
</table>
  <center>
    <br/>
	

 <hr size="5" color="84BDF6">
<table width="100%" border="0" cellpadding="3" cellspacing="2" bgcolor="#6298E1" >
  <form action="studentAction_insertModifyApply.action" method="post" style="text-align: center;" >
    <tr height="400" >
      <td height="24" nowrap="nowrap" bgcolor="#EBF2F9" >
      <table width="100%" border="0" cellpadding="0" cellspacing="0" id="editProduct" idth="100%" >
        <tr>
          <td width="120" height="20" align="right">&nbsp;</td>
          <td><div align="left"></div></td>
        </tr>
        <tr >
          <td height="30" align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;我的学号：</td>
          <td><div align="left">
            <input name="St_number" type="text" class="textfield" id="St_number" style="WIDTH: 120;" value="<s:property value='#request.studentVO.idnum'/>" size="15" readonly="TRUE"/>
            &nbsp;</div></td>
        </tr>
        <tr>
          <td height="30" align="right">我的姓名：</td>
          <td><div align="left">
            <input name="St_name" type="text" class="textfield" id="St_name" style="WIDTH: 120;" value="<s:property value='#request.studentVO.name'/>"  size="15" readonly="TRUE"/>
            </div></td>
        </tr>
        <tr>
          <td height="30" align="right">修改姓名：</td>
          <td><div align="left">
            <input name="name_temp" type="text" class="textfield" id="name_temp" style="WIDTH: 120;" value="" size="15" />
            &nbsp;（可修改）</div></td>
        </tr>
        <tr>
          <td height="30" align="right">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 别：</td>
          <td><div align="left">
			<input type="text" name="St_sex" type="radio" readonly="TRUE" value="<s:property value='#request.studentVO.sex'/>"  />
          </div></td>
        </tr>
        <tr>
          <td height="30" align="right">民&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;族：</td>
          <td><div align="left">
			<input type="text" name="St_sex" type="radio" readonly="TRUE" value="<s:property value='#request.studentVO.nation'/>"  />
          </div></td>
        </tr>
         <tr>
          <td height="30" align="right">专&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 业：</td>
          <td><div align="left">
			<input type="text" name="St_sex" type="radio" readonly="TRUE" value="<s:property value='#request.studentVO.prof_Name'/>"  />
          </div></td>
        </tr>
        <tr>
          <td height="30" align="right">家庭住址：</td>
          <td><div align="left">
            <input name="St_origin" type="text" class="textfield" readonly="TRUE" id="St_origin" style="WIDTH: 120;" value="<s:property value='#request.studentVO.address'/>"  size="19" />
            &nbsp;</div></td>
        </tr>
        <tr>
          <td height="30" align="right">修改住址：</td>
          <td><div align="left">
            <input name="address_temp" type="text" class="textfield" id="address_temp" style="WIDTH: 120;" value="" size="19" />
            &nbsp;（可修改）</div></td>
        </tr>
        
        <tr>
          <td height="30" align="right">入学日期：</td>
         <td><div align="left">
            <input name="identity_card" type="text" readonly="TRUE" class="textfield" id="identity_card" style="WIDTH: 120;" value="<s:property value='#request.studentVO.entranceDate'/>" size="19" />
            &nbsp;</div></td>
        </tr>
        <tr>
          <td height="30" align="right">身份证号：</td>
          <td><div align="left">
            <input name="identity_card" type="text"  readonly="TRUE" class="textfield" id="identity_card" style="WIDTH: 120;" value="<s:property value='#request.studentVO.idcard'/>" size="19" />
            &nbsp;（可修改）</div></td>
        </tr>
        <tr>
          <td height="30" align="right">修改身份证号：</td>
          <td><div align="left">
            <input name="idcard_temp" type="text" class="textfield" id="idcard_temp" style="WIDTH: 120;" value="" size="19" />
            &nbsp;</div></td>
        </tr>
        <tr>
          <td height="30" align="right">&nbsp;</td>
          <td valign="bottom"><div align="left">
            <input name="submitSaveEdit3" type="submit" onclick="return validate();" value=" 申 请 " style="WIDTH: 60;">&nbsp;&nbsp;&nbsp;
            
            <input name="submitSaveEdit22" type="reset" class="button"  id="submitSaveEdit22" value=" 重 置 " style="WIDTH: 60;" />
          </div></td>
        </tr>
        <tr>
          <td height="20" align="right">&nbsp;</td>
          <td valign="bottom"><div align="left"></div></td>
        </tr>
      </table></td>
    </tr>
  </form>
</table>

  
</center>
</body>
</html>


