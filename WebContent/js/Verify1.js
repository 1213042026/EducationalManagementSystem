
/***********************************************************************************************/
/************ע���û�  ��֤��ʼ***************/	 

var accout_flag = false;
/**
 * ��֤�˺��Ƿ�Ϸ�
 */
function checkAccount( obj)
	{     //alert("����ʧ��");
	 
	
		  //��ȡimg����  div
         // var img = document.getElementById( "img" );
          var div = document.getElementById( "div" );
          div.innerHTML = "�˺Ų���Ϊ��";
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
				            
				              div.innerHTML = "�˺Ų���Ϊ��";
				              div.style.color = "red";
				              accout_flag = false;
				     }
					else if(data.result=='false')
					{
						if(!regex.test(acc)){
					    
					    	 div.innerHTML = "ֻ������2-10������ĸ��ͷ���ɴ����֡���_������.�����ִ� ";
					    	 div.style.color = "red";
					    	 accout_flag = false;
					        } 
				         else
					        {
						
			                div.innerHTML = "�˺���֤�ɹ�";
			                div.style.color = "blue";
			                accout_flag = true;
					        }
						}
					else
					{
						
		                div.innerHTML = "�˺��Ѵ���";
		                div.style.color = "red";
		                accout_flag = false;
						}
			 	},
			 	error: function(){
					alert("����ʧ��");//����ʧ��
					accout_flag = false;
				}
			});	
         
	}

/**
 * ��֤�����Ƿ�Ϸ�
 */
    var name_flag = false;
	function checkName(obj)
	{
		//��ȡimg����  div����
        var nameImg = document.getElementById( "nameImg" );
        var nameDiv = document.getElementById( "nameDiv" );
        var name = obj.value;
        var regex= /[\u4E00-\u9FA5\uF900-\uFA2D]/; 
        
		  if(name=="")
			{  
			  nameImg.src = "images/invalid.gif";
			  nameImg.style.display = '';
			  nameDiv.innerHTML = "��������Ϊ��";
			  nameDiv.style.color = "red";
			  name_flag = false;
		     }else if(!regex.test(name))
		     {
		    	 nameImg.src = "images/invalid.gif";
		    	 nameImg.style.display = '';
		    	 nameDiv.innerHTML = "��������д����";
		    	 nameDiv.style.color = "red";
		    	 name_flag = false;
		        }  
			  else 
			  {
				  nameImg.src = "images/valid.gif";
				  nameImg.style.display = '';
				  nameDiv.innerHTML = "������֤�ɹ�";
				  nameDiv.style.color = "blue";
				  name_flag = true;
			  }       
	}
	
	/**
	 * ��֤�����Ƿ�Ϸ�
	 */
	var pwd_flag = false;
	function checkPWD(obj)
	{
		//��ȡimg����  div
        var pwdImg = document.getElementById( "pwdImg" );
        var pwdDiv = document.getElementById( "pwdDiv" );
        var pwd = obj.value;
        var regex= /^(\w){6,20}$/;  
        
		  if(pwd=="")
			{  
			  pwdImg.src = "images/invalid.gif";
			  pwdImg.style.display = '';
			  pwdDiv.innerHTML = "���벻��Ϊ��";
			  pwdDiv.style.color = "red";
			  pwd_flag = false;
		     }else if(!regex.test(pwd))
		     {
		    	 pwdImg.src = "images/invalid.gif";
		    	 pwdImg.style.display = '';
		    	 pwdDiv.innerHTML = "����ӦΪ6-12λ�ַ�";
		    	 pwdDiv.style.color = "red";
		    	 pwd_flag = false;
		        }  
			  else 
			  {
				  pwdImg.src = "images/valid.gif";
				  pwdImg.style.display = '';
				  pwdDiv.innerHTML = "������֤�ɹ�";
				  pwdDiv.style.color = "blue";
				  pwd_flag = true;
			  }
	}		  

	/**
	 * ��֤�绰�����Ƿ�Ϸ�
	 */
	var tel_flag = false;
	function checkTel(obj)
	{
		//��ȡimg����  div
        var telImg = document.getElementById( "telImg" );
        var telDiv = document.getElementById( "telDiv" );
        var tel = obj.value;
        var regex1=/^((\(\d{3}\))|(\d{3}\-))?13[456789]\d{8}|15[89]\d{8}/; //�ֻ�����
        var regex2=/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})/;//�绰����
        
		  if(tel=="")
			{  
			  telImg.src = "images/invalid.gif";
			  telImg.style.display = '';
		              
			  telDiv.innerHTML = "�绰����Ϊ��";
			  telDiv.style.color = "red";
			  tel_flag = false;
		     }
		  else if(!(regex1.test(tel))&& !( regex2.test(tel)) )
		  {
			  telImg.src = "images/invalid.gif";
			  telImg.style.display = '';
			  telDiv.innerHTML = "�绰��ʽ����ȷ";
			  telDiv.style.color = "red";
			  tel_flag = false;
	        }  
		  else
		  {
			  telImg.src = "images/valid.gif";
			  telImg.style.display = '';
			  telDiv.innerHTML = "�绰��֤�ɹ�";
			  telDiv.style.color = "blue";
			  tel_flag = true;  
		  }  
	}
	
	/**
	 * ��֤�����ʼ��Ƿ�Ϸ�
	 */
	var email_flag = false;
	function checkEmail(obj)
	{
		//��ȡimg����  div
        var emailImg = document.getElementById( "emailImg" );
        var emailDiv = document.getElementById( "emailDiv" );
        var email = obj.value;
        var regex=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
       
		  if(email=="")
			{  
			  emailImg.src = "images/invalid.gif";
			  emailImg.style.display = '';
			  emailDiv.innerHTML = "�����ʼ�����Ϊ��";
			  emailDiv.style.color = "red";
			  email_flag = false;
		     }
		  else if(!regex.test(email))
		  {
			  emailImg.src = "images/invalid.gif";
			  emailImg.style.display = '';
			  emailDiv.innerHTML = "�����ʼ���ʽ����ȷ";
			  emailDiv.style.color = "red";
			  email_flag = false;
	        }  
		  else
		  {
			  emailImg.src = "images/valid.gif";
			  emailImg.style.display = '';
              emailDiv.innerHTML = "�����ʼ���֤�ɹ�";
              emailDiv.style.color = "blue";
              email_flag = true;
	        }  
	}
	
	
	/**
	 * ��֤��
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
			  alert("��δͨ����֤����");
				return false;
		 }
	 }
	 
/***********************************************************************************************/
/************��ӹ���Ա  ��֤��ʼ***************/	 
	
	 /**
	  * ��֤����Ա�˻��Ƿ����
	  */
	 var m_account_plag =false;
	function validateManagerAccount(obj)
	{
		 //��ȡimg����  div
		
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
						accountDiv.innerHTML = "�˺Ų���Ϊ��";
						accountDiv.style.color = "red";
						m_account_plag =false;
				     }
					else if(data.result=='false')
					{
						if(!regex.test(mamagerAccount)){
							accountImg.src = "images/invalid.gif";
							accountImg.style.display = '';
					    	 accountDiv.innerHTML = "ֻ������2-10������ĸ��ͷ���ɴ����֡���_������.�����ִ� ";
					    	 accountDiv.style.color = "red";
					    	 m_account_plag =false;
					        } 
				         else
					        {
				        	 accountImg.src = "images/valid.gif";
				        	 accountImg.style.display = '';
			                accountDiv.innerHTML = "�˺���֤�ɹ�";
			                accountDiv.style.color = "blue";
			                m_account_plag =true;
					        }
						}
					else
					{
						accountImg.src = "images/invalid.gif";
						accountImg.style.display = '';
		                accountDiv.innerHTML = "�˺��Ѵ���";
		                accountDiv.style.color = "red";
		                m_account_plag =false;
					}
			 	},
			 	error: function(){
					alert("����ʧ��");
					m_account_plag =false;
				}
			});	
       
		
	}
	
	/**
	 * ��֤����Ա�����Ƿ�Ϸ�
	 */
	    var m_name_flag = false;
		function checkManagerName(obj)
		{
			//��ȡimg����  div����
	        var nameImg = document.getElementById( "nameImg" );
	        var nameDiv = document.getElementById( "nameDiv" );
	        var name = obj.value;
	        var regex= /[\u4E00-\u9FA5\uF900-\uFA2D]/; 
	        
			  if(name=="")
				{  
				  nameImg.src = "images/invalid.gif";
				  nameImg.style.display = '';
				  nameDiv.innerHTML = "��������Ϊ��";
				  nameDiv.style.color = "red";
				  m_name_flag = false;
			     }else if(!regex.test(name))
			     {
			    	 nameImg.src = "images/invalid.gif";
			    	 nameImg.style.display = '';
			    	 nameDiv.innerHTML = "��������д����";
			    	 nameDiv.style.color = "red";
			    	 m_name_flag = false;
			        }  
				  else 
				  {
					  nameImg.src = "images/valid.gif";
					  nameImg.style.display = '';
					  nameDiv.innerHTML = "������֤�ɹ�";
					  nameDiv.style.color = "blue";
					  m_name_flag = true;
				  }       
		}
		
		
		/**
		 * ��֤�绰�����Ƿ�Ϸ�
		 */
		var m_tel_flag = false;
		function checkManagerTel(obj)
		{
			//��ȡimg����  div
	        var telImg = document.getElementById( "telImg" );
	        var telDiv = document.getElementById( "telDiv" );
	        var tel = obj.value;
	        var regex1=/^((\(\d{3}\))|(\d{3}\-))?13[456789]\d{8}|15[89]\d{8}/; //�ֻ�����
	        var regex2=/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})/;//�绰����
	        
			  if(tel=="")
				{  
				  telImg.src = "images/invalid.gif";
				  telImg.style.display = '';
			              
				  telDiv.innerHTML = "�绰����Ϊ��";
				  telDiv.style.color = "red";
				  m_tel_flag = false;
			     }
			  else if(!(regex1.test(tel))&& !( regex2.test(tel)) )
			  {
				  telImg.src = "images/invalid.gif";
				  telImg.style.display = '';
				  telDiv.innerHTML = "�绰��ʽ����ȷ";
				  telDiv.style.color = "red";
				  m_tel_flag = false;
		        }  
			  else
			  {
				  telImg.src = "images/valid.gif";
				  telImg.style.display = '';
				  telDiv.innerHTML = "�绰��֤�ɹ�";
				  telDiv.style.color = "blue";
				  m_tel_flag = true;  
			  }  
		}
		
		/**
		 * ��֤��ӹ���Ա��
		 */
		
		 function validateAddManagerFrom(){
			 
			 if(  true == m_account_plag &&
				  true == m_name_flag &&
				 true == m_tel_flag){
				
				 return true;
			 }
			 else
			 {
				  alert("��δͨ����֤����");
					return false;
			 }
		 }

/***********************************************************************************************/
/************�޸Ĺ���Ա  ��֤��ʼ***************/	 
 
 /**
 * ��֤�޸Ĺ���Ա�����Ƿ�Ϸ�
 */
var am_name_flag = true;
function checkAlterManagerName(obj)
{
	//��ȡimg����  div����
var nameImg = document.getElementById( "nameImg" );
var nameDiv = document.getElementById( "nameDiv" );
var name = obj.value;
var regex= /[\u4E00-\u9FA5\uF900-\uFA2D]/; 

  if(name=="")
{  
  nameImg.src = "images/invalid.gif";
  nameImg.style.display = '';
  nameDiv.innerHTML = "��������Ϊ��";
  nameDiv.style.color = "red";
  am_name_flag = false;
 }else if(!regex.test(name))
 {
	 nameImg.src = "images/invalid.gif";
 nameImg.style.display = '';
 nameDiv.innerHTML = "��������д����";
 nameDiv.style.color = "red";
	 am_name_flag = false;
    }  
  else 
  {
	  nameImg.src = "images/valid.gif";
  nameImg.style.display = '';
  nameDiv.innerHTML = "������֤�ɹ�";
  nameDiv.style.color = "blue";
			  am_name_flag = true;
		  }       
}


/**
 * ��֤�޸Ĺ���Ա�绰�����Ƿ�Ϸ�
 */
var am_tel_flag = true;
function checkAlterManagerTel(obj)
{
	//��ȡimg����  div
var telImg = document.getElementById( "telImg" );
var telDiv = document.getElementById( "telDiv" );
var tel = obj.value;
var regex1=/^((\(\d{3}\))|(\d{3}\-))?13[456789]\d{8}|15[89]\d{8}/; //�ֻ�����
var regex2=/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})/;//�绰����



  
  if(tel=="")
{  
  telImg.src = "images/invalid.gif";
  telImg.style.display = '';
          
  telDiv.innerHTML = "�绰����Ϊ��";
  telDiv.style.color = "red";
	  am_tel_flag = false;
     }
  else if(!(regex1.test(tel))&& !( regex2.test(tel)) )
  {
	  telImg.src = "images/invalid.gif";
  telImg.style.display = '';
  telDiv.innerHTML = "�绰��ʽ����ȷ";
  telDiv.style.color = "red";
	  am_tel_flag = false;
    }  
  else
  {
	  telImg.src = "images/valid.gif";
  telImg.style.display = '';
  telDiv.innerHTML = "�绰��֤�ɹ�";
  telDiv.style.color = "blue";
		  am_tel_flag = true;  
	  }  
}

/**
 * ��֤�޸Ĺ���Ա��
 */

 function validateAlterManagerFrom(){
	 
	 if(
		  true == am_name_flag &&
		 true == am_tel_flag){
		 return true;
	 }
	 else
	 {
		  alert("��δͨ����֤����");
							return false;
					 }
				 }
 

/***********************************************************************************************/
/************�޸��û�  ��֤��ʼ***************/	 
 
 /**
 * ��֤�޸��û������Ƿ�Ϸ�
 */
var au_name_flag = true;

function checkAlterUserName(obj)
{
	//��ȡimg����  div����
var nameImg = document.getElementById( "nameImg" );
var nameDiv = document.getElementById( "nameDiv" );
var name = obj.value;
var regex= /[\u4E00-\u9FA5\uF900-\uFA2D]/; 

  if(name=="")
	{  
	  nameImg.src = "images/invalid.gif";
	  nameImg.style.display = '';
	  nameDiv.innerHTML = "��������Ϊ��";
	  nameDiv.style.color = "red";
	  au_name_flag = false;
     }else if(!regex.test(name))
     {
    	 nameImg.src = "images/invalid.gif";
    	 nameImg.style.display = '';
    	 nameDiv.innerHTML = "��������д����";
    	 nameDiv.style.color = "red";
    	 au_name_flag = false;
        }  
	  else 
	  {
		  nameImg.src = "images/valid.gif";
		  nameImg.style.display = '';
		  nameDiv.innerHTML = "������֤�ɹ�";
		  nameDiv.style.color = "blue";
			  au_name_flag = true;
		  }       
}


/**
 * ��֤�޸��û��绰�����Ƿ�Ϸ�
 */
var au_tel_flag = true;
function checkAlterUserTel(obj)
{
	//��ȡimg����  div
var telImg = document.getElementById( "telImg" );
var telDiv = document.getElementById( "telDiv" );
var tel = obj.value;
var regex1=/^((\(\d{3}\))|(\d{3}\-))?13[456789]\d{8}|15[0125689]\d{8}/; //�ֻ�����
var regex2=/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})/;//�绰����



  
  if(tel=="")
	{  
	  telImg.src = "images/invalid.gif";
	  telImg.style.display = '';
              
	  telDiv.innerHTML = "�绰����Ϊ��";
	  telDiv.style.color = "red";
	  au_tel_flag = false;
     }
  else if(!(regex1.test(tel))&& !( regex2.test(tel)) )
  {
	  telImg.src = "images/invalid.gif";
	  telImg.style.display = '';
	  telDiv.innerHTML = "�绰��ʽ����ȷ";
	  telDiv.style.color = "red";
	  au_tel_flag = false;
    }  
  else
  {
	  telImg.src = "images/valid.gif";
	  telImg.style.display = '';
	  telDiv.innerHTML = "�绰��֤�ɹ�";
	  telDiv.style.color = "blue";
		  au_tel_flag = true;  
	  }  
}

/**
 * ��֤�޸��û������ʼ��Ƿ�Ϸ�
 */
var au_email_flag = true;
function checkAlterUserEmail(obj)
{
	//��ȡimg����  div
    var emailImg = document.getElementById( "emailImg" );
    var emailDiv = document.getElementById( "emailDiv" );
    var email = obj.value;
    var regex=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
   
	  if(email=="")
		{  
		  emailImg.src = "images/invalid.gif";
		  emailImg.style.display = '';
		  emailDiv.innerHTML = "�����ʼ�����Ϊ��";
		  emailDiv.style.color = "red";
		  au_email_flag = false;
	     }
	  else if(!regex.test(email))
	  {
		  emailImg.src = "images/invalid.gif";
		  emailImg.style.display = '';
		  emailDiv.innerHTML = "�����ʼ���ʽ����ȷ";
		  emailDiv.style.color = "red";
		  au_email_flag = false;
        }  
	  else
	  {
		  emailImg.src = "images/valid.gif";
		  emailImg.style.display = '';
          emailDiv.innerHTML = "�����ʼ���֤�ɹ�";
          emailDiv.style.color = "blue";
          au_email_flag = true;
        }  
}



/**
 * ��֤�޸��û���
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
		  alert("��δͨ����֤����");
 							return false;
 					 }
 				 }
  
 /***********************************************************************************************/
 /************lee�޸ĸ����û�  ��֤��ʼ***************/	 
  
  /**
  * ��֤�޸��û������Ƿ�Ϸ�
  */
 var au_name_flag0 = true;

 function checkAlterUserName0(obj)
 {
 	//��ȡimg����  div����
 var nameImg = document.getElementById( "nameImg" );
 var nameDiv = document.getElementById( "nameDiv" );
 var name = obj.value;
 var regex= /[\u4E00-\u9FA5\uF900-\uFA2D]/; 

   if(name=="")
 	{  
 	  nameImg.src = "images/invalid.gif";
 	  nameImg.style.display = '';
 	  nameDiv.innerHTML = "��������Ϊ��";
 	  nameDiv.style.color = "red";
 	  au_name_flag0 = false;
      }else if(!regex.test(name))
      {
     	 nameImg.src = "../images/invalid.gif";
     	 nameImg.style.display = '';
     	 nameDiv.innerHTML = "��������д����";
     	 nameDiv.style.color = "red";
     	 au_name_flag0 = false;
         }  
 	  else 
 	  {
 		  nameImg.src = "../images/valid.gif";
 		  nameImg.style.display = '';
 		  nameDiv.innerHTML = "������֤�ɹ�";
 		  nameDiv.style.color = "blue";
 			  au_name_flag0 = true;
 		  }       
 }


 /**
  * ��֤�޸��û��绰�����Ƿ�Ϸ�
  */
 var au_tel_flag0 = true;
 function checkAlterUserTel0(obj)
 {
 	//��ȡimg����  div
 var telImg = document.getElementById( "telImg" );
 var telDiv = document.getElementById( "telDiv" );
 var tel = obj.value;
 var regex1=/^((\(\d{3}\))|(\d{3}\-))?13[456789]\d{8}|15[0125689]\d{8}/; //�ֻ�����
 var regex2=/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})/;//�绰����



   
   if(tel=="")
 	{  
 	  telImg.src = "../images/invalid.gif";
 	  telImg.style.display = '';
               
 	  telDiv.innerHTML = "�绰����Ϊ��";
 	  telDiv.style.color = "red";
 	  au_tel_flag0 = false;
      }
   else if(!(regex1.test(tel))&& !( regex2.test(tel)) )
   {
 	  telImg.src = "../images/invalid.gif";
 	  telImg.style.display = '';
 	  telDiv.innerHTML = "�绰��ʽ����ȷ";
 	  telDiv.style.color = "red";
 	  au_tel_flag0 = false;
     }  
   else
   {
 	  telImg.src = "../images/valid.gif";
 	  telImg.style.display = '';
 	  telDiv.innerHTML = "�绰��֤�ɹ�";
 	  telDiv.style.color = "blue";
 		  au_tel_flag0 = true;  
 	  }  
 }

 /**
  * ��֤�޸��û������ʼ��Ƿ�Ϸ�
  */
 var au_email_flag0 = true;
 function checkAlterUserEmail0(obj)
 {
 	//��ȡimg����  div
     var emailImg = document.getElementById( "emailImg" );
     var emailDiv = document.getElementById( "emailDiv" );
     var email = obj.value;
     var regex=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
    
 	  if(email=="")
 		{  
 		  emailImg.src = "../images/invalid.gif";
 		  emailImg.style.display = '';
 		  emailDiv.innerHTML = "�����ʼ�����Ϊ��";
 		  emailDiv.style.color = "red";
 		  au_email_flag0 = false;
 	     }
 	  else if(!regex.test(email))
 	  {
 		  emailImg.src = "../images/invalid.gif";
 		  emailImg.style.display = '';
 		  emailDiv.innerHTML = "�����ʼ���ʽ����ȷ";
 		  emailDiv.style.color = "red";
 		  au_email_flag0 = false;
         }  
 	  else
 	  {
 		  emailImg.src = "../images/valid.gif";
 		  emailImg.style.display = '';
           emailDiv.innerHTML = "�����ʼ���֤�ɹ�";
           emailDiv.style.color = "blue";
           au_email_flag0 = true;
         }  
 }



 /**
  * ��֤�޸��û���
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
 		  alert("��δͨ����֤����");
  							return false;
  					 }
  				 }
   
  		 		 		 		 