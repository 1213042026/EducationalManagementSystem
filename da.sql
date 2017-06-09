create table ems_profession(
       idnum varchar(20),            
       pro_name varchar(100),        
       remarks varchar(1000)
       
       
);

insert into ems_profession values('0001','¼ÆËã»ú¿ÆÑ§Óë¼¼Êõ',null);
insert into ems_profession values('0002','µç×ÓÐÅÏ¢¼¼Êõ',null);
insert into ems_profession values('0003','×°äêÉè¼Æ',null);
insert into ems_profession values('0004','·þ×°±íÑÝ',null);
insert into ems_profession values('0005','»úÐµÖÆÔì',null);



create table EMS_TEACHER(
       idnum varchar(20),         
       password varchar(20),      
       name varchar(100),        
       name_temp varchar(100),    
       sex varchar(2),           
       idcard varchar(18),        
       idcard_temp varchar(18),   
       address varchar(200),     
       address_temp varchar(200), 
       profession  varchar(20),  
       nation varchar(50),        
       scientific varchar(5),    
       title varchar(10),        
       flag int default 0 
       
);

insert into ems_teacher values('0001','0001','ÖÓ¼á³É',null,'ÄÐ','420623198012341256',null,'ºþÄÏÊ¦·¶´óÑ§',null,'0001','ºº×å','²©Ê¿','½²Ê¦',0);
insert into ems_teacher values('0002','0002','ÕÅ½¡¾ü',null,'ÄÐ','420623198012341246',null,'ºþÄÏÊ¦·¶´óÑ§',null,'0001','ºº×å','²©Ê¿','½²Ê¦',1);
insert into ems_teacher values('0003','0003','Ñî¼Òºì',null,'ÄÐ','420623198012341236',null,'ºþÄÏÊ¦·¶´óÑ§',null,'0001','ºº×å','²©Ê¿','½ÌÊÚ',1);
insert into ems_teacher values('0004','0004','¸¶Óñ',null,  'Å®','420623198012341232',null,'ºþÄÏÊ¦·¶´óÑ§',null,'0001','ºº×å','²©Ê¿','¸±½ÌÊÚ',1);
insert into ems_teacher values('0005','0005','Ð¤ÁøÃ÷',null,'Å®','420623198012341237',null,'ºþÄÏÊ¦·¶´óÑ§',null,'0001','ºº×å','²©Ê¿','½²Ê¦',1);
insert into ems_teacher values('0006','0006','Ê¢ÑÞ',null,'Å®',  '420623198012341341',null,'ºþÄÏÊ¦·¶´óÑ§',null,'0001','ºº×å','²©Ê¿','½²Ê¦',1);
insert into ems_teacher values('0007','0007','Íõ²Ó¶«',null,'Å®','420623198012341347',null,'ºþÄÏÊ¦·¶´óÑ§',null,'0001','ºº×å','²©Ê¿','½²Ê¦',1);
insert into ems_teacher values('0008','0008','ÁõÁ¢Îá',null,'ÄÐ','420623198012341347',null,'ºþÄÏÊ¦·¶´óÑ§',null,'0001','ºº×å','²©Ê¿','½²Ê¦',1);
insert into ems_teacher values('0009','0009','ÅËÆôÔª',null,'ÄÐ','420623198012341347',null,'ºþÄÏÊ¦·¶´óÑ§',null,'0001','ºº×å','²©Ê¿','½²Ê¦',1);
insert into ems_teacher values('0010','0010','Ð»½õ',null,'Å®','420623198012341347',null,'ºþÄÏÊ¦·¶´óÑ§',null,'0001','ºº×å','²©Ê¿','½²Ê¦',1);



create table Ems_Manager(
       idnum varchar(20),          
       password varchar(20),        
       name varchar(100),           
       sex varchar(2),             
       idcard varchar(18),          
       address varchar(200),       
       nation varchar(50)         
);

insert into ems_manager values('system','system','ÏµÍ³¹ÜÀíÔ±',null,null,null,null);
insert into ems_manager values('0001','0001','ÂíÃÈ','Å®','430623199010301234','ºþÄÏÊ¦·¶´óÑ§','ºº×å');
insert into ems_manager values('0002','0002','°×ÑÞ','ÄÐ','430623199010301334','ºþÄÏÊ¦·¶´óÑ§','ºº×å');
insert into ems_manager values('0003','0003','ÑîÐÃÅì','ÄÐ','430623199010301434','ºþÄÏÊ¦·¶´óÑ§','ºº×å');

create table ems_student(
       idnum varchar(20),            
       password varchar(20),         
       name varchar(100),           
       username_temp varchar(100),   
       sex varchar(2),              
       idcard varchar(18),           
       idcrad_temp varchar(18),      
       profession varchar(20),       
       address varchar(200),         
       address_temp varchar(200),    
       nation varchar(50),           
       ENTRANCEDATE varchar(10),     
       flag int default 0         
);

insert into ems_student values('2008180501','123456','Ò×Ù»',null,'Å®','430726198911293149',null,'0001','³£µÂ',null,'ÍÁ¼Ò×å','20080907',0);
insert into ems_student values('2008180503','123456','ÑîÈÙ',null,'Å®','430726198904071441',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180504','123456','ËÕ°²²¨',null,'ÄÐ','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180502','123456','ÄôË«Æ½',null,'Å®','430725199003200347',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180505','123456','ÀîÁ¢',null,'ÄÐ','430681198810053233',null,'0001','ÔÀÑô',null,'ºº×å','20080907',0);
insert into ems_student values('2008180506','123456','ÐÜÑÞ·¼',null,'Å®','430723199009165447',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180507','123456','ÖÜÔ²',null,'Å®','431102199002195682',null,'0001','ÓÀÖÝ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180508','123456','³ÂöÎÁÖ',null,'ÄÐ','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180509','123456','ÑîÖÇÀö',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180510','123456','ÌÆæ«',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180511','123456','Ê¯Ð¡ºì',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180512','123456','À×Ð¡Ñà',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180513','123456','Íõ½ðÔª',null,'ÄÐ','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180514','123456','ÑÖ³Â',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180515','123456','ºØÃô',null,'ÄÐ','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180516','123456','Ú÷Ñà²Æ',null,'ÄÐ','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180517','123456','»Æ¿¥',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180518','123456','Ð¤Ñþ',null,'ÄÐ','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180519','123456','ÎÄÙ»Ó±',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180520','123456','»Æ²é',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180521','123456','ÁõìÍ',null,'ÄÐ','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180522','123456','Ò×¸ï¾ü',null,'ÄÐ','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180523','123456','ÑîÐÃÅì',null,'ÄÐ','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180524','123456','ÁõÏ¼',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180525','123456','Áº¼ÑÀÊ',null,'ÄÐ','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180526','123456','ÀîÍÅ',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180527','123456','ÎéÃÄ',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180528','123456','ÂÞÑà',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180529','123456','Ì·Ë®Æ½',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180530','123456','°×ÑÞ',null,'ÄÐ','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180531','123456','×Þ·¼ÔÆ',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180532','123456','¹ùÓñ½¿',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180533','123456','ÍõÒæÄÝ',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180534','123456','ÌÆÇ¿',null,'ÄÐ','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180535','123456','³ÉÐÒÒã',null,'ÄÐ','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180536','123456','»ÆÀö·Ò',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180537','123456','ºú³¬',null,'ÄÐ','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180538','123456','ÂíÃÈ',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180539','123456','Áõ·É',null,'ÄÐ','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180540','123456','ÎâÑÇ»Ô',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180541','123456','ÖÜÏ¼',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);
insert into ems_student values('2008180542','123456','Ð¤Ñî',null,'Å®','430726198801042832',null,'0001','³£µÂ',null,'ºº×å','20080907',0);

create table ems_graduation(
  idnum  varchar(20),                 
  gname varchar(100),                
  teacherId varchar(20),               
  gcount varchar(10),                  
  rcount varchar(10),                  
  recordTime timestamp,                
  flag varchar(20),                     
  glevel varchar(20),                   
  remark varchar(800)              
  
);
insert into ems_graduation values('0001','»ùÓÚJava WebµÄ½ÌÎñ¹ÜÀíÏµÍ³','0001','10','10','2012-3-1 12:00:00','teacher','ÈÝÒ×','ËùÐëÖªÊ¶£ºhtmlÍøÒ³Éè¼Æ£¬java»ù´¡£¬jsp¶¯Ì¬ÍøÒ³Éè¼Æ£¬SHH¿ò¼Ü£¬OracleÊý¾Ý¿âµÈ¡£');
insert into ems_graduation values('0002','»ùÓÚandroidµÄÁ¬Á¬¿´3DÓÎÏ·','0001','10','10','2012-3-1 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºjava»ù´¡,SQLite Êý¾Ý¿âµÈ¡£');
insert into ems_graduation values('0003','»ùÓÚJ2EEÓëVBAµÄ¿ÆÑÐÏîÄ¿¹ÜÀíÐÅÏ¢ÏµÍ³','0001','10','10','2012-3-1 12:00:00','teacher','ÈÝÒ×','ËùÐëÖªÊ¶£ºOffice VBA,htmlÍøÒ³Éè¼Æ£¬java»ù´¡£¬jsp¶¯Ì¬ÍøÒ³Éè¼Æ£¬SHH¿ò¼Ü£¬OracleÊý¾Ý¿âµÈ¡£');
insert into ems_graduation values('0004','³µÔØÖÇÄÜ·ÀµÁÏµÍ³µÄÉè¼ÆÓëÊµÏÖ','0001','10','10','2012-3-1 12:00:00','teacher','ÈÝÒ×','ËùÐëÖªÊ¶£ºhtml + jsp + java + oracleµÈ¡£');
insert into ems_graduation values('0005','Ô¶³ÌÊÓÆµ¿ØÖÆÈí¼þµÄÉè¼Æ¼°ÊµÏÖ','0001','10','10','2012-3-1 12:00:00','teacher','ÈÝÒ×','ËùÐëÖªÊ¶£ºhtml + jsp + java + oracleµÈ¡£');
insert into ems_graduation values('0006','»ùÓÚoffice VBA±¨±íÏµÍ³','0001','10','10','2012-3-1 12:00:00','teacher','ÈÝÒ×','ËùÐëÖªÊ¶£ºhtml + jsp + java + oracleµÈ¡£');
insert into ems_graduation values('0007','Èí¼þ²âÊÔBUG¸ú×Ù¼°¹ÜÀíÏµÍ³Éè¼Æ','0001','10','10','2012-3-1 12:00:00','teacher','À§ÄÑ','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0008','P2P¼ÆËã¼¼ÊõÑÐ¾¿','0001','10','10','2012-3-1 12:00:00','teacher','À§ÄÑ','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0009','Í¨ÓÃÖÇÄÜÉý¼¶ÏµÍ³µÄÉè¼ÆÓëÊµÏÖ','0001','10','10','2012-3-1 12:00:00','teacher','À§ÄÑ','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0010','»ùÓÚÊÖ»úÆ½Ì¨GPS¹ì¼£ÖØÏÖÏµÍ³Éè¼ÆÓëÊµÏÖ','0001','10','10','2012-3-1 12:00:00','teacher','À§ÄÑ','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0011','»ùÓÚÇ¶ÈëÊ½Æ½Ì¨¾ØÕóÀà¿âÉè¼ÆÓëÊµÏÖ','0001','10','10','2012-3-1 12:00:00','teacher','À§ÄÑ','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0012','»ùÓÚJ2EEÓëVBA¹¤×÷Á÷ÒýÇæÉè¼ÆÓëÊµÏÖ','0001','10','10','2012-3-1 12:00:00','teacher','À§ÄÑ','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0013','J2EE½ÌÑ§¹ÜÀíÏµÍ³','0001','10','10','2012-3-1 12:00:00','teacher','À§ÄÑ','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0014','Word ( PDF )ÎÄµµ½á¹¹½âÎö','0002','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0015','Êµ¼ù½ÌÑ§ÅÅ¿ÎÏµÍ³Éè¼Æ','0002','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0016','ÍøÂç½ÌÑ§×ÊÔ´¿âÉè¼Æ','0002','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0017','ÊµÑéÒÇÆ÷Éè±¸¹ÜÀíÆ½Ì¨Éè¼Æ','0002','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0018','»ùÓÚ·ÖÐÎµÄ¶¯»­ÌØÐ§Éè¼Æ','0002','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0019','µç×ÓÎÄµµ¸´ÖÆÈ¡Ö¤ÑÐ¾¿','0002','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0020','Ó¡Ë¢ÌåÎÄµµÊý×ÖË®Ó¡Éè¼Æ','0002','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0021','»ùÓÚARMµÄÖÇÄÜ¼Ò¾ÓÏµÍ³','0003','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0022','»ùÓÚAndroidÆ½Ì¨µÄ3GÁ÷Á¿¼ì²âÏµÍ³','0003','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0044','»ùÓÚandroidÆ½Ì¨µÄÖÐÓ¢ÎÄ´Êµä','0003','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0023','LINUXÏÂ¸öÈË·À»ðÇ½','0003','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0024','»ùÓÚMAPXµÄ¹«½»³µÁ¾ÊµÊ±¼à¿ØÉè¼ÆÓëÑÐ·¢','0003','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0025','GPSÖÇÄÜ³­±íÖÕ¶ËÏµÍ³','0003','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0026','µçÁ¦³­±íÏµÍ³µ¼º½ÖÐµÄÓ¦ÓÃÑÐ¾¿ºÍËã·¨Éè¼Æ','0003','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0027','ÉÌÆ·ÍÅ¹ºÍøµÄÉè¼ÆÓë¿ª·¢','0004','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0028',' ÖÇÄÜËÑË÷ÒýÇæµÄËã·¨·ÖÎöÓëÉè¼Æ','0004','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0029','¸ßµÈÖ°Òµ½ÌÓýÐÅÏ¢¹ÜÀíÐÅÏ¢ÏµÍ³µÄÉè¼ÆÓëÊµÏÖ','0004','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0030','»ùÓÚWEBµÄÈí¼þË®Æ½¿¼ÊÔÆ½Ì¨µÄÉè¼ÆÓë¿ª·¢','0004','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0031','Ë«Óï½ÌÑ§¾«Æ·¿Î³ÌÏµÍ³µÄÉè¼ÆÓëÊµÏÖ','0004','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0032','ÉúÎïÒ±½ðµÄÊý¾Ý²Ö¿âÓëÊý¾ÝÍÚ¾òµÄËã·¨Éè¼ÆÓëÊµÏÖ','0004','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0033','ÔÆ¼ÆËãÖÕ¶ËµÄ·ÖÎöÓëÉè¼Æ','0004','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0034','ÔÆ¼ÆËã·þÎñÆ÷µÄ·ÖÎöÓëÉè¼Æ','0004','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0035','ÎÄµµÊý×ÖË®Ó¡µÄÉè¼ÆÓë¿ª·¢','0004','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0036','»ùÓÚAndroidÊÖ»úÆ½Ì¨µÄÒôÀÖ²¥·ÅÆ÷µÄÉè¼ÆÓëÊµÏÖ','0004','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0037','½ÌÊ¦¹¤×÷Á¿¹ÜÀíÏµÍ³','0005','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0038','ÆóÒµºÏÍ¬¹ÜÀíÏµÍ³','0005','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0039','½ÌÑ§ÃØÊéÏµÍ³','0005','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0040','ÖÐÐ¡ÆóÒµ³É±¾ºËËãÏµÍ³','0005','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0041','¶þÎ¬Âë±àÂë½âÂëÆ÷Éè¼Æ','0006','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0042','ÓÃ»ô·òÂü±àÂëÊµÏÖµÄÊý×ÖÍ¼ÏñÎÞËðÑ¹Ëõ','0006','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0043','¶þÊÖ·¿×âÊÛÍøÕ¾Éè¼Æ','0006','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');
insert into ems_graduation values('0045','Ð¡ÇøÎïÒµ¹ÜÀíÏµÍ³','0007','10','10','2012-3-19 12:00:00','teacher','Ò»°ã','ËùÐëÖªÊ¶£ºÓïÑÔ£ºC/C++/C#,,JavaµÈ¡£,Êý¾Ý¿â£ºMS SQL,Mysql,OracleµÈ¡£·þÎñÆ÷£ºtomcat,IISµÈ¡£¿ª·¢¹¤¾ß£ºEclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,VisioµÈ¡£');

create table ems_graduateGrade(
 idnum varchar(20),                 
 gidnum varchar(20),                
 studentId  varchar(20),            
 grade  varchar(3),                 
 gxtime  Timestamp,                  
 remark varchar(300)              
 
);
insert into ems_graduateGrade values('0001','0001','2008180538',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0002','0001','2008180530',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0003','0001','2008180523',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0004','0022','2008180517',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0005','0022','2008180518',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0006','0022','2008180539',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0007','0036','2008180520',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0008','0036','2008180511',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0009','0044','2008180506',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0010','0044','2008180540',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0011','0003','2008180532',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0012','0003','2008180519',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0013','0043','2008180527',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0014','0043','2008180510',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0015','0043','2008180533',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0016','0043','2008180528',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0017','0041','2008180536',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0018','0041','2008180509',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0019','0030','2008180507',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0020','0030','2008180542',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0021','0013','2008180503',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0022','0013','2008180512',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0023','0023','2008180526',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0024','0023','2008180521',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0025','0024','2008180524',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0026','0002','2008180541',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0027','0002','2008180531',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0028','0006','2008180515',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0029','0006','2008180505',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0030','0029','2008180501',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0031','0029','2008180502',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0032','0010','2008180508',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0033','0014','2008180522',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0034','0025','2008180534',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0035','0025','2008180516',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0036','0025','2008180513',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0037','0013','2008180514',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0038','0013','2008180504',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0039','0013','2008180537',null,'2012-2-1 12:00:00',null);
insert into ems_graduateGrade values('0040','0026','2008180535',null,'2012-2-1 12:00:00',null);


create table EMS_CLASSTYPE(
       idnum varchar(20),               
       classtypename varchar(100),     
       remarks varchar(1000)          
       
       
);

insert into ems_classtype values('0001','¹«¹²±ØÐÞ¿Î',null);
insert into ems_classtype values('0002','×¨Òµ±ØÐÞ¿Î',null);
insert into ems_classtype values('0003','×¨ÒµÏÞÑ¡¿Î',null);
insert into ems_classtype values('0004','ÈÎÒâÑ¡ÐÞ¿Î',null);


create table ems_class(
       classId varchar(20) primary key ,    
       className varchar(100),              
       classType varchar(20),              
       teacherId varchar(20),               
       term varchar(10),                    
       grade varchar(20),                   
       professionId varchar(20),           
       kCount varchar(10),                 
       sCount varchar(10),                  
       recordTime timestamp,               
       remark  varchar(300)              
     
);
insert into ems_class values('0001','´óÑ§Ó¢Óï£¨Ò»£©','0001','0008','1','2008',null,'50','10','2008-8-1 12:00:00',null);
insert into ems_class values('0002','µçÂ·','0004','0010','1','2008',null,'50','10','2008-8-1 12:00:50',null);
insert into ems_class values('0003','ÆÕÍ¨ÎïÀí£¨Ò»£©','0003','0009','1','2008','0001','50','10','2008-8-1 12:00:50',null);
insert into ems_class values('0004','¸ÅÂÊÂÛÓëÊýÀíÍ³¼Æ','0002','0010','2','2008','0001','50','10','2009-2-1 12:00:50',null);
insert into ems_class values('0005','¸ßµÈÊýÑ§£¨¶þ£©','0001','0009','2','2008',null,'50','0','2009-2-1 12:00:50',null);
insert into ems_class values('0006','¾üÊÂÀíÂÛ','0004','0001','2','2008',null,'50','10','2009-2-1 12:00:50',null);
insert into ems_class values('0007','ÃæÏò¶ÔÏóµÄ³ÌÐòÉè¼Æ£¨C++£©','0003','0002','2','2008','0001','50','10','2009-2-1 12:00:50',null);
insert into ems_class values('0008','¸´±äº¯ÊýÓë»ý·Ö±ä»»','0002','0010','1','2009','0001','50','10','2009-8-1 12:00:50',null);
insert into ems_class values('0009','Êý¾Ý½á¹¹','0002','0005','1','2008','0001','50','10','2009-8-1 12:00:50',null);
insert into ems_class values('0010','ÀëÉ¢ÊýÑ§','0002','0007','1','2008','0001','50','10','2009-8-1 12:00:50',null);
insert into ems_class values('0011','²Ù×÷ÏµÍ³','0002','0005','2','2008','0001','50','10','2010-2-1 12:00:50',null);
insert into ems_class values('0012','¼ÆËã»ú×é³ÉÔ­Àí','0003','0010','2','2008','0001','50','10','2010-2-1 12:00:50',null);
insert into ems_class values('0013','Êý¾Ý¿âÔ­Àí','0002','0006','2','2008','0001','50','10','2010-2-1 12:00:50',null);
insert into ems_class values('0014','JavaÓïÑÔ³ÌÐòÉè¼Æ','0002','0001','1','2008','0001','50','10','2010-8-1 12:00:50',null);
insert into ems_class values('0015','¼ÆËã»úÍøÂç','0002','0002','1','2008','0001','50','10','2010-8-1 12:00:50',null);
insert into ems_class values('0016','Ëã·¨Éè¼ÆÓëÊý¾Ý½á¹¹¿Î³ÌÉè¼Æ','0002','0007','1','2008','0001','50','10','2010-8-1 12:00:50',null);
insert into ems_class values('0017','ÏÖ´ú½ÌÓý¼¼Êõ»ù´¡','0004','0008','1','2008',null,'50','10','2010-8-1 12:00:50',null);
insert into ems_class values('0018','Linux/Unix²Ù×÷ÏµÍ³','0002','0003','2','2008','0001','50','10','2011-2-1 12:00:50',null);
insert into ems_class values('0019','Ã«Ôó¶«Ë¼ÏëºÍÖÐ¹úÌØÉ«Éç»áÖ÷ÒåÀíÂÛÌåÏµ¸ÅÂÛ','0001','0008','2','2008',null,'50','10','2011-2-1 12:00:50',null);
insert into ems_class values('0020','Èí¼þ¹¤³Ì','0002','0001','2','2008','0001','50','10','2011-2-1 12:00:50',null);
insert into ems_class values('0021','×¨ÒµÓ¢Óï','0002','0004','2','2008','0001','50','10','2011-2-1 12:00:50',null);
insert into ems_class values('0022','TCP/IPÐ­Òé','0002','0004','1','2008','0001','50','10','2011-8-1 12:00:50',null);
insert into ems_class values('0023','ÃæÏò¶ÔÏóµÄÏµÍ³·ÖÎöÓëÉè¼Æ','0002','0001','1','2008','0001','50','10','2011-8-1 12:00:50',null);
insert into ems_class values('0024','ÏµÍ³Éè¼ÆÓë¿ª·¢ÊµÑµ','0002','0003','1','2008','0001','50','10','2011-8-1 12:00:50',null);
insert into ems_class values('0025','ÎÄÏ×¼ìË÷','0002','0002','1','2008','0001','50','10','2011-8-1 12:00:50',null);
insert into ems_class values('0026','±ÏÒµÉè¼Æ','0002','0003','2','2008','0001','50','10','2012-2-1 12:00:50',null);





create table ems_courserecord(
       idnum varchar(20) primary key  ,   
       studentId varchar(20),             
       classId varchar(20),               
       grade  varchar(3),                  
       chooseTime timestamp,              
       remarks varchar(300)            
   
);

insert into ems_courserecord values('0001','2008180501','0001','80','2008-8-25 12:00:50',null);
insert into ems_courserecord values('0003','2008180501','0002','80','2008-8-25 12:00:50',null);
insert into ems_courserecord values('0004','2008180501','0003','80','2008-8-25 12:00:50',null);
insert into ems_courserecord values('0005','2008180501','0004','80','2009-2-25 12:00:50',null);
insert into ems_courserecord values('0006','2008180501','0005','80','2009-2-25 12:00:50',null);
insert into ems_courserecord values('0007','2008180501','0006','80','2009-2-25 12:00:50',null);
insert into ems_courserecord values('0008','2008180501','0007','80','2009-2-25 12:00:50',null);
insert into ems_courserecord values('0009','2008180501','0008','80','2009-8-25 12:50:50',null);
insert into ems_courserecord values('0010','2008180501','0008','80','2009-8-25 12:00:50',null);
insert into ems_courserecord values('0011','2008180501','0010','80','2009-8-25 12:00:50',null);




create table Ems_applyModifyLog(
       idnum varchar(20),             
       applyUserId varchar(20),       
       applyDate datetime,                  
       auditDate datetime,                  
       modifyResult varchar(20),       
       fortable varchar(100),         
       auditMan varchar(20),            
       remark varchar(300)            
       
       
);

insert into Ems_applyModifyLog values('0002','2008180501','2012-2-8 12:50:50','2012-2-11 12:50:50','Î´Í¨¹ý','ems_teacher','0001','Éí·ÝÖ¤ºÅÂëÎ»Êý²»¶Ô');
insert into Ems_applyModifyLog values('0001','2008180503','2012-2-9 12:50:50','2012-2-12 12:50:50','Î´Í¨¹ý','ems_student','0002','ÐÕÃû¸ñÊ½²»¶Ô');

insert into Ems_applyModifyLog values('0003','2008180501','2012-2-15 12:50:50','2012-2-17 12:50:50','ÒÑÍ¨¹ý','ems_student','0001',null);
insert into Ems_applyModifyLog values('0004','0002','2012-2-23 12:50:50','2012-2-25 12:50:50','ÒÑÍ¨¹ý','ems_teacher','0001',null);



create table ems_systemset(
       idnum varchar(20) primary key,   
       syssetname varchar(100),          
       timestart timestamp,              
       timeend timestamp,                 
       remarks varchar(500)              
);
insert into ems_systemset values('0001','ÀÏÊ¦Ìí¼Ó¿Î³ÌÊ±¼äÉèÖÃ','2012-3-1 12:00:00','2012-6-2 12:00:00',null);
insert into ems_systemset values('0002','ÀÏÊ¦Ìí¼Ó±ÏÒµÉè¼ÆÊ±¼äÉèÖÃ','2012-1-1 12:00:00','2012-6-2 12:00:00',null);
insert into ems_systemset values('0003','ÀÏÊ¦Â¼Èë¿Î³Ì³É¼¨Ê±¼äÉèÖÃ','2012-3-1 12:00:00','2012-6-2 12:00:00',null);
insert into ems_systemset values('0004','ÀÏÊ¦Â¼Èë±ÏÒµÉè¼Æ³É¼¨Ê±¼äÉèÖÃ','2012-4-1 12:00:00','2012-6-2 12:00:00',null);
insert into ems_systemset values('0005','Ñ§ÉúÑ¡¿ÎÊ±¼äÉèÖÃ','2012-3-3 12:00:00','2012-6-2 12:00:00',null);
insert into ems_systemset values('0006','Ñ§Éú±ÏÒµÉè¼ÆÑ¡ÌâÊ±¼äÉèÖÃ','2012-1-3 12:00:00','2012-6-2 12:00:00',null);


create table ems_message(
  idnum    varchar(20) primary key, 
  sidnum   varchar(20), 
  tidnum   varchar(20),  
  title  varchar(500),  
  content  varchar(500),   
  mtime    timestamp,       
  status   varchar(20),   
  rcontent varchar(500),   
  rtime    timestamp,   
  remark   varchar(100)                
);

insert into ems_message values('0001','2008180501','0001','ÁôÑÔ±êÌâx','ÁôÑÔÄÚÈÝx','2012-1-3 12:00:00','Î´»Ø¸´',null,null,null);
insert into ems_message values('0002','2008180502','0001','ÁôÑÔ±êÌây','ÁôÑÔÄÚÈÝd','2012-1-3 12:00:00','Î´»Ø¸´',null,null,null);
insert into ems_message values('0003','2008180501','0001','ÁôÑÔ±êÌâz','ÁôÑÔÄÚÈÝe','2012-1-3 12:00:00','ÒÑ»Ø¸´','»Ø¸´ÄÚÈÝ2','2012-1-3 15:00:00',null);
insert into ems_message values('0004','2008180502','0001','ÁôÑÔ±êÌâa','ÁôÑÔÄÚÈÝe','2012-1-3 12:00:00','ÒÑ»Ø¸´','»Ø¸´ÄÚÈÝe','2012-1-3 15:00:00',null);
create table ems_gFlowConfi(
 idnum varchar(20) primary key,      
 sidnum varchar(20),          
 flowName varchar(100),      
 flag varchar(10),           
 grade varchar(5),            
 remark varchar(100)        
 
);

insert into ems_gFlowConfi values('0001','2008180501','Ñ§ÉúÑ¡Ìâ','ÒÑÍê³É',null,null);
insert into ems_gFlowConfi values('0002','2008180501','ÐèÇóµ÷²é£¬ÎÄµµ±àÐ´','½øÐÐÖÐ',null,null);
insert into ems_gFlowConfi values('0003','2008180501','±ÏÒµÉè¼ÆÔ­ÐÍ¿ª·¢', 'ÒÑÍê³É', null,null);
insert into ems_gFlowConfi values('0004','2008180501','±ÏÒµÉè¼Æ¿ª·¢Íê³É','Î´¿ªÊ¼',null,null);
insert into ems_gFlowConfi values('0005','2008180501','ÂÛÎÄ±àÐ´','Î´¿ªÊ¼',null,null);
insert into ems_gFlowConfi values('0006','2008180502','Ñ§ÉúÑ¡Ìâ','½øÐÐÖÐ',null,null);
insert into ems_gFlowConfi values('0007','2008180502','ÐèÇóµ÷²é£¬ÎÄµµ±àÐ´','Î´¿ªÊ¼',null,null);
insert into ems_gFlowConfi values('0008','2008180502','±ÏÒµÉè¼ÆÔ­ÐÍ¿ª·¢','Î´¿ªÊ¼',null,null);
insert into ems_gFlowConfi values('0009','2008180502','±ÏÒµÉè¼Æ¿ª·¢Íê³É','Î´¿ªÊ¼',null,null);
insert into ems_gFlowConfi values('0010','2008180502','ÂÛÎÄ±àÐ´','Î´¿ªÊ¼',null,null);



create table Ems_workerType(
       idnum varchar(20)  ,            
       works_name varchar(100),        
       remarks varchar(1000)           
       
       );

insert into Ems_workerType values('0001','±ÏÒµÉè¼Æ',null);
insert into Ems_workerType values('0002','¿ÎÌÃ×÷Òµ',null);



create table Ems_Workers(
       idnum varchar(20) primary key,    
       workerType varchar(20),         
       classTypeId varchar(20),        
       studentId varchar(20),          
       score int,              
       remarks varchar(1000)          
       
       
);

