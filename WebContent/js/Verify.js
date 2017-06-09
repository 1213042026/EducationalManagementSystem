var accout_flag = false;
/**
 * 测试验证账号是否合法
 */
function checkAccount( obj)
	{     //alert("操作失败");
	 
	
		  //获取div对象
          var div = document.getElementById( "div" );
         // div.innerHTML = "账号不能为空";
       //   var acc = document.getElementById( "account" ).value;
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
			     }else	 if(data.result=='true')//帐号已经存在
					{
						  div.innerHTML = "账号已存在";
			                div.style.color = "red";
			                accout_flag = false;
						}
					else
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
			 	},
			 	error: function(){
					alert("操作失败");//操作失败
					accout_flag = false;
				}
			});	
         
	}

/**
 * 测试验证毕业设计名称是否合法
 */
function checkGname( obj)
	{     //alert("操作失败");
	 
	
		  //获取div对象
          var div = document.getElementById( "div" );
        //  div.innerHTML = "名称不能为空";
       //   var acc = document.getElementById( "account" ).value;
          var acc = (obj.value).replace(/\s+/g,"");
		  var account = "account="+acc;		 

		  $.ajax({
				type: "POST",
				url: "teacherAction_checkGname.action",
				data: account,
				dataType:'json',
				success: function(data){
			  if(data.result=='space')
				{  
			            
			              div.innerHTML = "名称不能为空";
			              div.style.color = "red";
			              accout_flag = false;
			     }else	 if(data.result=='true')//帐号已经存在
					{
						  div.innerHTML = "名称已存在";
			                div.style.color = "red";
			                accout_flag = false;
						}
					else
					{
						div.innerHTML = "";
						
						
		              
						}
			 	},
			 	error: function(){
					alert("操作失败");//操作失败
					accout_flag = false;
				}
			});	
         
	}

/**
 * 测试验证管理员编号是否合法
 */
function checkManager(obj)
	{     //alert("操作失败");
	 
	
		  //获取div对象
          var div = document.getElementById( "div" );
        //  div.innerHTML = "名称不能为空";
       //   var acc = document.getElementById( "account" ).value;
          var acc = (obj.value).replace(/\s+/g,"");
		  var account = "account="+acc;		 

		  $.ajax({
				type: "POST",
				url: "managerAction_checkManager.action",
				data: account,
				dataType:'json',
				success: function(data){
			  if(data.result=='space')
				{  
			            
			              div.innerHTML = "编号不能为空";
			              div.style.color = "red";
			              accout_flag = false;
			     }else	 if(data.result=='true')//帐号已经存在
					{
						  div.innerHTML = "编号已存在";
			                div.style.color = "red";
			                accout_flag = false;
						}
					else
					{
						div.innerHTML = "";
						
						
		              
						}
			 	},
			 	error: function(){
					alert("操作失败");//操作失败
					accout_flag = false;
				}
			});	
         
	}







