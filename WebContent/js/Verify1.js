
/***********************************************************************************************/
/************注册用户  验证开始***************/	 

var accout_flag = false;
/**
 * 验证账号是否合法
 */
function checkAccount( obj)
	{     //alert("操作失败");
	 
	
		  //获取img对像  div
         // var img = document.getElementById( "img" );
          var div = document.getElementById( "div" );
          div.innerHTML = "账号不能为空";
          var acc = document.getElementById( "account" ).value;
          var acc = obj.value;
		  var account = "account="+obj.value;
		  var regex=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){1,9}$/;  

		  $.ajax({
				type: "POST",
				url: "managerAction_checkAccount.action",
				data: account,
				dataType:'json',
				success: function(data){
					if(data.result=='space')
					{  
				            
				              div.innerHTML = "账号不能为空";
				              div.style.color = "red";
				              accout_flag = false;
				     }
					else if(data.result=='false')
					{
						if(!regex.test(acc)){
					    
					    	 div.innerHTML = "只能输入2-10个以字母开头、可带数字、“_”、“.”的字串 ";
					    	 div.style.color = "red";
					    	 accout_flag = false;
					        } 
				         else
					        {
						
			                div.innerHTML = "账号验证成功";
			                div.style.color = "blue";
			                accout_flag = true;
					        }
						}
					else
					{
						
		                div.innerHTML = "账号已存在";
		                div.style.color = "red";
		                accout_flag = false;
						}
			 	},
			 	error: function(){
					alert("操作失败");//操作失败
					accout_flag = false;
				}
			});	
         
	}

/**
 * 验证姓名是否合法
 */
    var name_flag = false;
	function checkName(obj)
	{
		//获取img对像  div对象
        var nameImg = document.getElementById( "nameImg" );
        var nameDiv = document.getElementById( "nameDiv" );
        var name = obj.value;
        var regex= /[\u4E00-\u9FA5\uF900-\uFA2D]/; 
        
		  if(name=="")
			{  
			  nameImg.src = "images/invalid.gif";
			  nameImg.style.display = '';
			  nameDiv.innerHTML = "姓名不能为空";
			  nameDiv.style.color = "red";
			  name_flag = false;
		     }else if(!regex.test(name))
		     {
		    	 nameImg.src = "images/invalid.gif";
		    	 nameImg.style.display = '';
		    	 nameDiv.innerHTML = "姓名请填写汉字";
		    	 nameDiv.style.color = "red";
		    	 name_flag = false;
		        }  
			  else 
			  {
				  nameImg.src = "images/valid.gif";
				  nameImg.style.display = '';
				  nameDiv.innerHTML = "姓名验证成功";
				  nameDiv.style.color = "blue";
				  name_flag = true;
			  }       
	}
	
	/**
	 * 验证密码是否合法
	 */
	var pwd_flag = false;
	function checkPWD(obj)
	{
		//获取img对像  div
        var pwdImg = document.getElementById( "pwdImg" );
        var pwdDiv = document.getElementById( "pwdDiv" );
        var pwd = obj.value;
        var regex= /^(\w){6,20}$/;  
        
		  if(pwd=="")
			{  
			  pwdImg.src = "images/invalid.gif";
			  pwdImg.style.display = '';
			  pwdDiv.innerHTML = "密码不能为空";
			  pwdDiv.style.color = "red";
			  pwd_flag = false;
		     }else if(!regex.test(pwd))
		     {
		    	 pwdImg.src = "images/invalid.gif";
		    	 pwdImg.style.display = '';
		    	 pwdDiv.innerHTML = "密码应为6-12位字符";
		    	 pwdDiv.style.color = "red";
		    	 pwd_flag = false;
		        }  
			  else 
			  {
				  pwdImg.src = "images/valid.gif";
				  pwdImg.style.display = '';
				  pwdDiv.innerHTML = "密码验证成功";
				  pwdDiv.style.color = "blue";
				  pwd_flag = true;
			  }
	}		  

	/**
	 * 验证电话号码是否合法
	 */
	var tel_flag = false;
	function checkTel(obj)
	{
		//获取img对像  div
        var telImg = document.getElementById( "telImg" );
        var telDiv = document.getElementById( "telDiv" );
        var tel = obj.value;
        var regex1=/^((\(\d{3}\))|(\d{3}\-))?13[456789]\d{8}|15[89]\d{8}/; //手机号码
        var regex2=/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})/;//电话号码
        
		  if(tel=="")
			{  
			  telImg.src = "images/invalid.gif";
			  telImg.style.display = '';
		              
			  telDiv.innerHTML = "电话不能为空";
			  telDiv.style.color = "red";
			  tel_flag = false;
		     }
		  else if(!(regex1.test(tel))&& !( regex2.test(tel)) )
		  {
			  telImg.src = "images/invalid.gif";
			  telImg.style.display = '';
			  telDiv.innerHTML = "电话格式不正确";
			  telDiv.style.color = "red";
			  tel_flag = false;
	        }  
		  else
		  {
			  telImg.src = "images/valid.gif";
			  telImg.style.display = '';
			  telDiv.innerHTML = "电话验证成功";
			  telDiv.style.color = "blue";
			  tel_flag = true;  
		  }  
	}
	
	/**
	 * 验证电子邮件是否合法
	 */
	var email_flag = false;
	function checkEmail(obj)
	{
		//获取img对像  div
        var emailImg = document.getElementById( "emailImg" );
        var emailDiv = document.getElementById( "emailDiv" );
        var email = obj.value;
        var regex=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
       
		  if(email=="")
			{  
			  emailImg.src = "images/invalid.gif";
			  emailImg.style.display = '';
			  emailDiv.innerHTML = "电子邮件不能为空";
			  emailDiv.style.color = "red";
			  email_flag = false;
		     }
		  else if(!regex.test(email))
		  {
			  emailImg.src = "images/invalid.gif";
			  emailImg.style.display = '';
			  emailDiv.innerHTML = "电子邮件格式不正确";
			  emailDiv.style.color = "red";
			  email_flag = false;
	        }  
		  else
		  {
			  emailImg.src = "images/valid.gif";
			  emailImg.style.display = '';
              emailDiv.innerHTML = "电子邮件验证成功";
              emailDiv.style.color = "blue";
              email_flag = true;
	        }  
	}
	
	
	/**
	 * 验证表单
	 */
	
	 function validateResFrom(){
		 
		 if(  true == accout_flag &&
			  true == name_flag &&
			  true == pwd_flag &&
			  true == tel_flag &&
			  true == email_flag){
			 return true;
		 }
		 else
		 {
			  alert("有未通过验证的项");
				return false;
		 }
	 }
	 
/***********************************************************************************************/
/************添加管理员  验证开始***************/	 
	
	 /**
	  * 验证管理员账户是否存在
	  */
	 var m_account_plag =false;
	function validateManagerAccount(obj)
	{
		 //获取img对像  div
		
        var accountImg = document.getElementById( "accountImg" );
        var accountDiv = document.getElementById( "accountDiv" );
  //      var mamagerAccount = document.getElementById( "mamagerAccount" ).value;
        var mamagerAccount = obj.value;
	    var account = "account="+obj.value;
		var regex=/^[a-zA-Z]{1}([a-zA-Z0-9]|[._]){1,9}$/;  
		  $.ajax({
				type: "POST",
				url: "managerAction_checkManagerAccount.action",
				data: account,
				dataType:'json',
				success: function(data){
					if(data.result=='space')
					{  
						accountImg.src = "images/invalid.gif";
						accountImg.style.display = '';
						accountDiv.innerHTML = "账号不能为空";
						accountDiv.style.color = "red";
						m_account_plag =false;
				     }
					else if(data.result=='false')
					{
						if(!regex.test(mamagerAccount)){
							accountImg.src = "images/invalid.gif";
							accountImg.style.display = '';
					    	 accountDiv.innerHTML = "只能输入2-10个以字母开头、可带数字、“_”、“.”的字串 ";
					    	 accountDiv.style.color = "red";
					    	 m_account_plag =false;
					        } 
				         else
					        {
				        	 accountImg.src = "images/valid.gif";
				        	 accountImg.style.display = '';
			                accountDiv.innerHTML = "账号验证成功";
			                accountDiv.style.color = "blue";
			                m_account_plag =true;
					        }
						}
					else
					{
						accountImg.src = "images/invalid.gif";
						accountImg.style.display = '';
		                accountDiv.innerHTML = "账号已存在";
		                accountDiv.style.color = "red";
		                m_account_plag =false;
					}
			 	},
			 	error: function(){
					alert("操作失败");
					m_account_plag =false;
				}
			});	
       
		
	}
	
	/**
	 * 验证管理员姓名是否合法
	 */
	    var m_name_flag = false;
		function checkManagerName(obj)
		{
			//获取img对像  div对象
	        var nameImg = document.getElementById( "nameImg" );
	        var nameDiv = document.getElementById( "nameDiv" );
	        var name = obj.value;
	        var regex= /[\u4E00-\u9FA5\uF900-\uFA2D]/; 
	        
			  if(name=="")
				{  
				  nameImg.src = "images/invalid.gif";
				  nameImg.style.display = '';
				  nameDiv.innerHTML = "姓名不能为空";
				  nameDiv.style.color = "red";
				  m_name_flag = false;
			     }else if(!regex.test(name))
			     {
			    	 nameImg.src = "images/invalid.gif";
			    	 nameImg.style.display = '';
			    	 nameDiv.innerHTML = "姓名请填写汉字";
			    	 nameDiv.style.color = "red";
			    	 m_name_flag = false;
			        }  
				  else 
				  {
					  nameImg.src = "images/valid.gif";
					  nameImg.style.display = '';
					  nameDiv.innerHTML = "姓名验证成功";
					  nameDiv.style.color = "blue";
					  m_name_flag = true;
				  }       
		}
		
		
		/**
		 * 验证电话号码是否合法
		 */
		var m_tel_flag = false;
		function checkManagerTel(obj)
		{
			//获取img对像  div
	        var telImg = document.getElementById( "telImg" );
	        var telDiv = document.getElementById( "telDiv" );
	        var tel = obj.value;
	        var regex1=/^((\(\d{3}\))|(\d{3}\-))?13[456789]\d{8}|15[89]\d{8}/; //手机号码
	        var regex2=/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})/;//电话号码
	        
			  if(tel=="")
				{  
				  telImg.src = "images/invalid.gif";
				  telImg.style.display = '';
			              
				  telDiv.innerHTML = "电话不能为空";
				  telDiv.style.color = "red";
				  m_tel_flag = false;
			     }
			  else if(!(regex1.test(tel))&& !( regex2.test(tel)) )
			  {
				  telImg.src = "images/invalid.gif";
				  telImg.style.display = '';
				  telDiv.innerHTML = "电话格式不正确";
				  telDiv.style.color = "red";
				  m_tel_flag = false;
		        }  
			  else
			  {
				  telImg.src = "images/valid.gif";
				  telImg.style.display = '';
				  telDiv.innerHTML = "电话验证成功";
				  telDiv.style.color = "blue";
				  m_tel_flag = true;  
			  }  
		}
		
		/**
		 * 验证添加管理员表单
		 */
		
		 function validateAddManagerFrom(){
			 
			 if(  true == m_account_plag &&
				  true == m_name_flag &&
				 true == m_tel_flag){
				
				 return true;
			 }
			 else
			 {
				  alert("有未通过验证的项");
					return false;
			 }
		 }

/***********************************************************************************************/
/************修改管理员  验证开始***************/	 
 
 /**
 * 验证修改管理员姓名是否合法
 */
var am_name_flag = true;
function checkAlterManagerName(obj)
{
	//获取img对像  div对象
var nameImg = document.getElementById( "nameImg" );
var nameDiv = document.getElementById( "nameDiv" );
var name = obj.value;
var regex= /[\u4E00-\u9FA5\uF900-\uFA2D]/; 

  if(name=="")
{  
  nameImg.src = "images/invalid.gif";
  nameImg.style.display = '';
  nameDiv.innerHTML = "姓名不能为空";
  nameDiv.style.color = "red";
  am_name_flag = false;
 }else if(!regex.test(name))
 {
	 nameImg.src = "images/invalid.gif";
 nameImg.style.display = '';
 nameDiv.innerHTML = "姓名请填写汉字";
 nameDiv.style.color = "red";
	 am_name_flag = false;
    }  
  else 
  {
	  nameImg.src = "images/valid.gif";
  nameImg.style.display = '';
  nameDiv.innerHTML = "姓名验证成功";
  nameDiv.style.color = "blue";
			  am_name_flag = true;
		  }       
}


/**
 * 验证修改管理员电话号码是否合法
 */
var am_tel_flag = true;
function checkAlterManagerTel(obj)
{
	//获取img对像  div
var telImg = document.getElementById( "telImg" );
var telDiv = document.getElementById( "telDiv" );
var tel = obj.value;
var regex1=/^((\(\d{3}\))|(\d{3}\-))?13[456789]\d{8}|15[89]\d{8}/; //手机号码
var regex2=/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})/;//电话号码



  
  if(tel=="")
{  
  telImg.src = "images/invalid.gif";
  telImg.style.display = '';
          
  telDiv.innerHTML = "电话不能为空";
  telDiv.style.color = "red";
	  am_tel_flag = false;
     }
  else if(!(regex1.test(tel))&& !( regex2.test(tel)) )
  {
	  telImg.src = "images/invalid.gif";
  telImg.style.display = '';
  telDiv.innerHTML = "电话格式不正确";
  telDiv.style.color = "red";
	  am_tel_flag = false;
    }  
  else
  {
	  telImg.src = "images/valid.gif";
  telImg.style.display = '';
  telDiv.innerHTML = "电话验证成功";
  telDiv.style.color = "blue";
		  am_tel_flag = true;  
	  }  
}

/**
 * 验证修改管理员表单
 */

 function validateAlterManagerFrom(){
	 
	 if(
		  true == am_name_flag &&
		 true == am_tel_flag){
		 return true;
	 }
	 else
	 {
		  alert("有未通过验证的项");
							return false;
					 }
				 }
 

/***********************************************************************************************/
/************修改用户  验证开始***************/	 
 
 /**
 * 验证修改用户姓名是否合法
 */
var au_name_flag = true;

function checkAlterUserName(obj)
{
	//获取img对像  div对象
var nameImg = document.getElementById( "nameImg" );
var nameDiv = document.getElementById( "nameDiv" );
var name = obj.value;
var regex= /[\u4E00-\u9FA5\uF900-\uFA2D]/; 

  if(name=="")
	{  
	  nameImg.src = "images/invalid.gif";
	  nameImg.style.display = '';
	  nameDiv.innerHTML = "姓名不能为空";
	  nameDiv.style.color = "red";
	  au_name_flag = false;
     }else if(!regex.test(name))
     {
    	 nameImg.src = "images/invalid.gif";
    	 nameImg.style.display = '';
    	 nameDiv.innerHTML = "姓名请填写汉字";
    	 nameDiv.style.color = "red";
    	 au_name_flag = false;
        }  
	  else 
	  {
		  nameImg.src = "images/valid.gif";
		  nameImg.style.display = '';
		  nameDiv.innerHTML = "姓名验证成功";
		  nameDiv.style.color = "blue";
			  au_name_flag = true;
		  }       
}


/**
 * 验证修改用户电话号码是否合法
 */
var au_tel_flag = true;
function checkAlterUserTel(obj)
{
	//获取img对像  div
var telImg = document.getElementById( "telImg" );
var telDiv = document.getElementById( "telDiv" );
var tel = obj.value;
var regex1=/^((\(\d{3}\))|(\d{3}\-))?13[456789]\d{8}|15[0125689]\d{8}/; //手机号码
var regex2=/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})/;//电话号码



  
  if(tel=="")
	{  
	  telImg.src = "images/invalid.gif";
	  telImg.style.display = '';
              
	  telDiv.innerHTML = "电话不能为空";
	  telDiv.style.color = "red";
	  au_tel_flag = false;
     }
  else if(!(regex1.test(tel))&& !( regex2.test(tel)) )
  {
	  telImg.src = "images/invalid.gif";
	  telImg.style.display = '';
	  telDiv.innerHTML = "电话格式不正确";
	  telDiv.style.color = "red";
	  au_tel_flag = false;
    }  
  else
  {
	  telImg.src = "images/valid.gif";
	  telImg.style.display = '';
	  telDiv.innerHTML = "电话验证成功";
	  telDiv.style.color = "blue";
		  au_tel_flag = true;  
	  }  
}

/**
 * 验证修改用户电子邮件是否合法
 */
var au_email_flag = true;
function checkAlterUserEmail(obj)
{
	//获取img对像  div
    var emailImg = document.getElementById( "emailImg" );
    var emailDiv = document.getElementById( "emailDiv" );
    var email = obj.value;
    var regex=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
   
	  if(email=="")
		{  
		  emailImg.src = "images/invalid.gif";
		  emailImg.style.display = '';
		  emailDiv.innerHTML = "电子邮件不能为空";
		  emailDiv.style.color = "red";
		  au_email_flag = false;
	     }
	  else if(!regex.test(email))
	  {
		  emailImg.src = "images/invalid.gif";
		  emailImg.style.display = '';
		  emailDiv.innerHTML = "电子邮件格式不正确";
		  emailDiv.style.color = "red";
		  au_email_flag = false;
        }  
	  else
	  {
		  emailImg.src = "images/valid.gif";
		  emailImg.style.display = '';
          emailDiv.innerHTML = "电子邮件验证成功";
          emailDiv.style.color = "blue";
          au_email_flag = true;
        }  
}



/**
 * 验证修改用户表单
 */

 function validateAlterUserFrom(){
	 
	 if(
		  true == au_name_flag &&
		 true == au_tel_flag &&
		 true == au_email_flag ){
		 return true;
	 }
	 else
	 {
		  alert("有未通过验证的项");
 							return false;
 					 }
 				 }
  
 /***********************************************************************************************/
 /************lee修改个人用户  验证开始***************/	 
  
  /**
  * 验证修改用户姓名是否合法
  */
 var au_name_flag0 = true;

 function checkAlterUserName0(obj)
 {
 	//获取img对像  div对象
 var nameImg = document.getElementById( "nameImg" );
 var nameDiv = document.getElementById( "nameDiv" );
 var name = obj.value;
 var regex= /[\u4E00-\u9FA5\uF900-\uFA2D]/; 

   if(name=="")
 	{  
 	  nameImg.src = "images/invalid.gif";
 	  nameImg.style.display = '';
 	  nameDiv.innerHTML = "姓名不能为空";
 	  nameDiv.style.color = "red";
 	  au_name_flag0 = false;
      }else if(!regex.test(name))
      {
     	 nameImg.src = "../images/invalid.gif";
     	 nameImg.style.display = '';
     	 nameDiv.innerHTML = "姓名请填写汉字";
     	 nameDiv.style.color = "red";
     	 au_name_flag0 = false;
         }  
 	  else 
 	  {
 		  nameImg.src = "../images/valid.gif";
 		  nameImg.style.display = '';
 		  nameDiv.innerHTML = "姓名验证成功";
 		  nameDiv.style.color = "blue";
 			  au_name_flag0 = true;
 		  }       
 }


 /**
  * 验证修改用户电话号码是否合法
  */
 var au_tel_flag0 = true;
 function checkAlterUserTel0(obj)
 {
 	//获取img对像  div
 var telImg = document.getElementById( "telImg" );
 var telDiv = document.getElementById( "telDiv" );
 var tel = obj.value;
 var regex1=/^((\(\d{3}\))|(\d{3}\-))?13[456789]\d{8}|15[0125689]\d{8}/; //手机号码
 var regex2=/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})/;//电话号码



   
   if(tel=="")
 	{  
 	  telImg.src = "../images/invalid.gif";
 	  telImg.style.display = '';
               
 	  telDiv.innerHTML = "电话不能为空";
 	  telDiv.style.color = "red";
 	  au_tel_flag0 = false;
      }
   else if(!(regex1.test(tel))&& !( regex2.test(tel)) )
   {
 	  telImg.src = "../images/invalid.gif";
 	  telImg.style.display = '';
 	  telDiv.innerHTML = "电话格式不正确";
 	  telDiv.style.color = "red";
 	  au_tel_flag0 = false;
     }  
   else
   {
 	  telImg.src = "../images/valid.gif";
 	  telImg.style.display = '';
 	  telDiv.innerHTML = "电话验证成功";
 	  telDiv.style.color = "blue";
 		  au_tel_flag0 = true;  
 	  }  
 }

 /**
  * 验证修改用户电子邮件是否合法
  */
 var au_email_flag0 = true;
 function checkAlterUserEmail0(obj)
 {
 	//获取img对像  div
     var emailImg = document.getElementById( "emailImg" );
     var emailDiv = document.getElementById( "emailDiv" );
     var email = obj.value;
     var regex=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
    
 	  if(email=="")
 		{  
 		  emailImg.src = "../images/invalid.gif";
 		  emailImg.style.display = '';
 		  emailDiv.innerHTML = "电子邮件不能为空";
 		  emailDiv.style.color = "red";
 		  au_email_flag0 = false;
 	     }
 	  else if(!regex.test(email))
 	  {
 		  emailImg.src = "../images/invalid.gif";
 		  emailImg.style.display = '';
 		  emailDiv.innerHTML = "电子邮件格式不正确";
 		  emailDiv.style.color = "red";
 		  au_email_flag0 = false;
         }  
 	  else
 	  {
 		  emailImg.src = "../images/valid.gif";
 		  emailImg.style.display = '';
           emailDiv.innerHTML = "电子邮件验证成功";
           emailDiv.style.color = "blue";
           au_email_flag0 = true;
         }  
 }



 /**
  * 验证修改用户表单
  */

  function validateAlterUserFrom0(){
 	 
 	 if(
 		  true == au_name_flag0 &&
 		 true == au_tel_flag0 &&
 		 true == au_email_flag0 ){
 		 return true;
 	 }
 	 else
 	 {
 		  alert("有未通过验证的项");
  							return false;
  					 }
  				 }
   
  		 		 		 		 