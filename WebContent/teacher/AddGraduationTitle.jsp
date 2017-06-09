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
    
    <title>添加毕业设计</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		span{
		color: 793AFF;
		padding-bottom: 20px;
		font-weight: bold;}
	</style>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/Verify.js"></script>
	<script type="text/javascript">
	

		function checkup()
	{     //alert("操作失败");
	 
	
		  
          var  addGra = document.getElementById( "addGra" );//span的标签          
         // alert(addGra);  
          //div.innerHTML = "账号不能为空";
          var graName = document.all('name').value;//得到毕业设计名称
          //alert(graName);
          var filename = document.all('image').value; //获得上传文件的物理路径
          if(filename =='')
        {//1路径不能为空
           alert("你还没有选择要上传的文件"); 
           return false;
        }
          var index = filename.lastIndexOf("\\");
          var sub = filename.substring(index+1);//得到文件名,含扩展名
          var subindex = sub.lastIndexOf(".");
          //alert(sub);
         var subsub = sub.substring(0,subindex);//得到文件名，不含扩展名
         // alert(subsub);
          if(graName!=subsub){//2名字一样
          alert("文件名称与毕设题目名称不一致");
          return false;
          }
          var regex=/[\d\w\s]*[.]doc$/;
          if(!regex.test(sub)){//3必须是以doc结尾  
          alert("请选择正确格式的文档");
          return false;
          }
          //alert(index);
        //  alert(sub);
         // alert(filename);
       //  addGra.innerHTML="验证成功";
         // alert("test");
         
		  return true;
         
	}
		
		
		
		function validate(){
		  var idnum = $("#idnum").val();			
			var name = $("#name").val();
			var kman = $("#kman").val();			
			var diff =  $("#diff").val() ;
			 var  fup = document.getElementById( "upfile" );//上传文件按钮的标签        
			//var pro_name =  $("#pro_name").val() ;
		//	var attitudeObj = document.getElementById("attitude");			
			//var attitude = attitudeObj.innerHTML;			
			var att = $("#attitude").text();//可以得到输入域中的内容
			if( name == null || name == "" ){
			
				alert("请输入毕业设计名称!");
				return false;
			}
			if( kman == null || kman == "" ){
			
				alert("请输入可选人数!");
				return false;
			}
			att   =   att.replace(/\s+/g,""); //去除空格
			if( att == null || att == ""){
		
				alert("请输入选题须知！");
				return false;
			}
			/////////////////////////////////
			  var graName = document.all('name').value;//得到毕业设计名称
			 var filename = document.all('image').value; //获得上传文件的物理路径
          if(filename =='')
        {//1路径不能为空
           alert("你还没有选择要上传的文件"); 
           return false;
        }
      
          var index = filename.lastIndexOf("\\");
          var sub = filename.substring(index+1);//得到文件名,含扩展名
          var subindex = sub.lastIndexOf(".");
          //alert(sub);
         var subsub = sub.substring(0,subindex);//得到文件名，不含扩展名
           if(graName!=subsub){//2名字一样
          alert("文件名称与毕设题目名称不一致");
          return false;
          }
         
			  var regex=/[\d\w\s]*[.]doc$/;
          if(!regex.test(sub)){//3必须是以doc结尾  
          alert("请选择正确格式的文档");
          return false;
          } 
		////////////////////////////////////////	      
			
			//	if( t_name == null || t_name == "" ){
				//alert("请选择教师!");
			//	return false;
			//}
		//if(!checkup()){//文件上传验证不通过，则不能添加选题
	   // 	return false;
		//};
			fup.submit();//触发上传表单的提交事件
		
		alert("文件上传成功");
			location="teacherAction_AddGraduationTitle.action?idnum=" + idnum +"&name=" + name+"&kman=" + kman +"&att=" + att+"&diff=" + diff;
			return true;
		}
	</script>
  </head>
  
  <body bgcolor="EFF6FF">
    <div style="padding-top: 10px; padding-left: 10px;">
    	<hr size="5" color="84BDF6">
     	<center>
     		<div style="color: 793AFF; font-size: large"><b>上传毕业设计</b></div>
     	</center>
     	<hr/>
     	<div style="margin-left: 100px;">
     	<!--  	<form action="teacherAction_AddGraduationTitle.action" method="post" >-->
     			<span>自动编号：</span><input type="text" readonly="readonly" name="idnum" id="idnum" style="background: gray;" value="<s:property value="#request.maxIdnum"/>"/><br/>
     			<br/>
     			<span>毕业设计名称：</span><input type="text"  name="name" onblur="checkGname(this)"  id="name"/><span id="div" ></span><br/><br/>
     			<span>可选人数：</span><input type="text" name="kman" id="kman"/><br/><br/>
					<span>难　　度：</span><select name="diff" id="diff">
					<option  value="容易" selected="selected">
						容易
					</option>
					<option value="一般">
						一般
					</option>
					<option value="困难">
						困难
					</option>
				</select>
				<br/><br/>
				<!-- 正常情况下的学年的开始就是以当年为开始,因为只能加本年的作品，所以不用选择学年了  		
     			<span>所属教师：</span><select name="t_name" id="t_name">
     			<option></option>
						<s:iterator value="teacherList">
							<option value="<s:property value='idnum' />"><s:property value="name" /></option>
						</s:iterator>
					</select> <br/> <br/>  -->   
					<!-- 要从数据库中得到 -->
     		
					<span>选题须知：</span>
					<textarea id ="attitude" name="attitude" rows="3" cols="30" style="color: red;">
					</textarea>
					<br/>
     			
     			<br/>
     			
     			 <form action="teacherAction_fileUpLoadTestAdd.action" enctype="multipart/form-data" id="upfile" method="post" >
                      <input type="file" id="image" name="image" onkeydown="return   false"/>
                      <!-- <input type="submit" id="fup" name="fup" onclick="return checkup();" value="上传"/> --><span id="addGra">必须上传一个选题概述文件（.doc格式），毕业设计名称必须与文件名相同</span>
                       <input type="hidden"  id="graID" name="graID" value="<s:property value="#request.maxIdnum"/>">
   </form>
     			
     			<input type="button" style="margin-left: 200px;" onclick="validate();" value="添加">  
     			
     		<!-- </form> -->
     	</div>
    </div>
  </body>
</html>
