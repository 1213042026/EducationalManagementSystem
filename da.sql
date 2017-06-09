create table ems_profession(
       idnum varchar(20),            
       pro_name varchar(100),        
       remarks varchar(1000)
       
       
);

insert into ems_profession values('0001','�������ѧ�뼼��',null);
insert into ems_profession values('0002','������Ϣ����',null);
insert into ems_profession values('0003','װ�����',null);
insert into ems_profession values('0004','��װ����',null);
insert into ems_profession values('0005','��е����',null);



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

insert into ems_teacher values('0001','0001','�Ӽ��',null,'��','420623198012341256',null,'����ʦ����ѧ',null,'0001','����','��ʿ','��ʦ',0);
insert into ems_teacher values('0002','0002','�Ž���',null,'��','420623198012341246',null,'����ʦ����ѧ',null,'0001','����','��ʿ','��ʦ',1);
insert into ems_teacher values('0003','0003','��Һ�',null,'��','420623198012341236',null,'����ʦ����ѧ',null,'0001','����','��ʿ','����',1);
insert into ems_teacher values('0004','0004','����',null,  'Ů','420623198012341232',null,'����ʦ����ѧ',null,'0001','����','��ʿ','������',1);
insert into ems_teacher values('0005','0005','Ф����',null,'Ů','420623198012341237',null,'����ʦ����ѧ',null,'0001','����','��ʿ','��ʦ',1);
insert into ems_teacher values('0006','0006','ʢ��',null,'Ů',  '420623198012341341',null,'����ʦ����ѧ',null,'0001','����','��ʿ','��ʦ',1);
insert into ems_teacher values('0007','0007','���Ӷ�',null,'Ů','420623198012341347',null,'����ʦ����ѧ',null,'0001','����','��ʿ','��ʦ',1);
insert into ems_teacher values('0008','0008','������',null,'��','420623198012341347',null,'����ʦ����ѧ',null,'0001','����','��ʿ','��ʦ',1);
insert into ems_teacher values('0009','0009','����Ԫ',null,'��','420623198012341347',null,'����ʦ����ѧ',null,'0001','����','��ʿ','��ʦ',1);
insert into ems_teacher values('0010','0010','л��',null,'Ů','420623198012341347',null,'����ʦ����ѧ',null,'0001','����','��ʿ','��ʦ',1);



create table Ems_Manager(
       idnum varchar(20),          
       password varchar(20),        
       name varchar(100),           
       sex varchar(2),             
       idcard varchar(18),          
       address varchar(200),       
       nation varchar(50)         
);

insert into ems_manager values('system','system','ϵͳ����Ա',null,null,null,null);
insert into ems_manager values('0001','0001','����','Ů','430623199010301234','����ʦ����ѧ','����');
insert into ems_manager values('0002','0002','����','��','430623199010301334','����ʦ����ѧ','����');
insert into ems_manager values('0003','0003','������','��','430623199010301434','����ʦ����ѧ','����');

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

insert into ems_student values('2008180501','123456','��ٻ',null,'Ů','430726198911293149',null,'0001','����',null,'������','20080907',0);
insert into ems_student values('2008180503','123456','����',null,'Ů','430726198904071441',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180504','123456','�հ���',null,'��','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180502','123456','��˫ƽ',null,'Ů','430725199003200347',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180505','123456','����',null,'��','430681198810053233',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180506','123456','���޷�',null,'Ů','430723199009165447',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180507','123456','��Բ',null,'Ů','431102199002195682',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180508','123456','������',null,'��','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180509','123456','������',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180510','123456','���',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180511','123456','ʯС��',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180512','123456','��С��',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180513','123456','����Ԫ',null,'��','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180514','123456','�ֳ�',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180515','123456','����',null,'��','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180516','123456','�����',null,'��','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180517','123456','�ƿ�',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180518','123456','Ф��',null,'��','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180519','123456','��ٻӱ',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180520','123456','�Ʋ�',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180521','123456','����',null,'��','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180522','123456','�׸��',null,'��','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180523','123456','������',null,'��','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180524','123456','��ϼ',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180525','123456','������',null,'��','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180526','123456','����',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180527','123456','����',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180528','123456','����',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180529','123456','̷ˮƽ',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180530','123456','����',null,'��','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180531','123456','�޷���',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180532','123456','����',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180533','123456','������',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180534','123456','��ǿ',null,'��','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180535','123456','������',null,'��','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180536','123456','������',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180537','123456','����',null,'��','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180538','123456','����',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180539','123456','����',null,'��','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180540','123456','���ǻ�',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180541','123456','��ϼ',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);
insert into ems_student values('2008180542','123456','Ф��',null,'Ů','430726198801042832',null,'0001','����',null,'����','20080907',0);

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
insert into ems_graduation values('0001','����Java Web�Ľ������ϵͳ','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','����','����֪ʶ��html��ҳ��ƣ�java������jsp��̬��ҳ��ƣ�SHH��ܣ�Oracle���ݿ�ȡ�');
insert into ems_graduation values('0002','����android��������3D��Ϸ','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ��java����,SQLite ���ݿ�ȡ�');
insert into ems_graduation values('0003','����J2EE��VBA�Ŀ�����Ŀ������Ϣϵͳ','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','����','����֪ʶ��Office VBA,html��ҳ��ƣ�java������jsp��̬��ҳ��ƣ�SHH��ܣ�Oracle���ݿ�ȡ�');
insert into ems_graduation values('0004','�������ܷ���ϵͳ�������ʵ��','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','����','����֪ʶ��html + jsp + java + oracle�ȡ�');
insert into ems_graduation values('0005','Զ����Ƶ�����������Ƽ�ʵ��','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','����','����֪ʶ��html + jsp + java + oracle�ȡ�');
insert into ems_graduation values('0006','����office VBA����ϵͳ','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','����','����֪ʶ��html + jsp + java + oracle�ȡ�');
insert into ems_graduation values('0007','�������BUG���ټ�����ϵͳ���','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','����','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0008','P2P���㼼���о�','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','����','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0009','ͨ����������ϵͳ�������ʵ��','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','����','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0010','�����ֻ�ƽ̨GPS�켣����ϵͳ�����ʵ��','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','����','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0011','����Ƕ��ʽƽ̨������������ʵ��','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','����','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0012','����J2EE��VBA���������������ʵ��','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','����','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0013','J2EE��ѧ����ϵͳ','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','����','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0014','Word ( PDF )�ĵ��ṹ����','0002','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0015','ʵ����ѧ�ſ�ϵͳ���','0002','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0016','�����ѧ��Դ�����','0002','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0017','ʵ�������豸����ƽ̨���','0002','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0018','���ڷ��εĶ�����Ч���','0002','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0019','�����ĵ�����ȡ֤�о�','0002','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0020','ӡˢ���ĵ�����ˮӡ���','0002','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0021','����ARM�����ܼҾ�ϵͳ','0003','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0022','����Androidƽ̨��3G�������ϵͳ','0003','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0044','����androidƽ̨����Ӣ�Ĵʵ�','0003','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0023','LINUX�¸��˷���ǽ','0003','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0024','����MAPX�Ĺ�������ʵʱ���������з�','0003','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0025','GPS���ܳ����ն�ϵͳ','0003','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0026','��������ϵͳ�����е�Ӧ���о����㷨���','0003','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0027','��Ʒ�Ź���������뿪��','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0028',' ��������������㷨���������','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0029','�ߵ�ְҵ������Ϣ������Ϣϵͳ�������ʵ��','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0030','����WEB�����ˮƽ����ƽ̨������뿪��','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0031','˫���ѧ��Ʒ�γ�ϵͳ�������ʵ��','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0032','����ұ������ݲֿ��������ھ���㷨�����ʵ��','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0033','�Ƽ����ն˵ķ��������','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0034','�Ƽ���������ķ��������','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0035','�ĵ�����ˮӡ������뿪��','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0036','����Android�ֻ�ƽ̨�����ֲ������������ʵ��','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0037','��ʦ����������ϵͳ','0005','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0038','��ҵ��ͬ����ϵͳ','0005','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0039','��ѧ����ϵͳ','0005','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0040','��С��ҵ�ɱ�����ϵͳ','0005','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0041','��ά�������������','0006','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0042','�û���������ʵ�ֵ�����ͼ������ѹ��','0006','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0043','���ַ�������վ���','0006','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');
insert into ems_graduation values('0045','С����ҵ����ϵͳ','0007','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','һ��','����֪ʶ�����ԣ�C/C++/C#,,Java�ȡ�,���ݿ⣺MS SQL,Mysql,Oracle�ȡ���������tomcat,IIS�ȡ��������ߣ�Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio�ȡ�');

create table ems_graduateGrade(
 idnum varchar(20),                 
 gidnum varchar(20),                
 studentId  varchar(20),            
 grade  varchar(3),                 
 gxtime  Timestamp,                  
 remark varchar(300)              
 
);
insert into ems_graduateGrade values('0001','0001','2008180538',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0002','0001','2008180530',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0003','0001','2008180523',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0004','0022','2008180517',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0005','0022','2008180518',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0006','0022','2008180539',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0007','0036','2008180520',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0008','0036','2008180511',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0009','0044','2008180506',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0010','0044','2008180540',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0011','0003','2008180532',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0012','0003','2008180519',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0013','0043','2008180527',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0014','0043','2008180510',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0015','0043','2008180533',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0016','0043','2008180528',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0017','0041','2008180536',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0018','0041','2008180509',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0019','0030','2008180507',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0020','0030','2008180542',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0021','0013','2008180503',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0022','0013','2008180512',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0023','0023','2008180526',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0024','0023','2008180521',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0025','0024','2008180524',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0026','0002','2008180541',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0027','0002','2008180531',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0028','0006','2008180515',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0029','0006','2008180505',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0030','0029','2008180501',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0031','0029','2008180502',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0032','0010','2008180508',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0033','0014','2008180522',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0034','0025','2008180534',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0035','0025','2008180516',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0036','0025','2008180513',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0037','0013','2008180514',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0038','0013','2008180504',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0039','0013','2008180537',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_graduateGrade values('0040','0026','2008180535',null,TO_DATE('2012-2-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);


create table EMS_CLASSTYPE(
       idnum varchar(20),               
       classtypename varchar(100),     
       remarks varchar(1000)          
       
       
);

insert into ems_classtype values('0001','�������޿�',null);
insert into ems_classtype values('0002','רҵ���޿�',null);
insert into ems_classtype values('0003','רҵ��ѡ��',null);
insert into ems_classtype values('0004','����ѡ�޿�',null);


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
insert into ems_class values('0001','��ѧӢ�һ��','0001','0008','1','2008',null,'50','10',TO_DATE('2008-8-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0002','��·','0004','0010','1','2008',null,'50','10',TO_DATE('2008-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0003','��ͨ����һ��','0003','0009','1','2008','0001','50','10',TO_DATE('2008-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0004','������������ͳ��','0002','0010','2','2008','0001','50','10',TO_DATE('2009-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0005','�ߵ���ѧ������','0001','0009','2','2008',null,'50','0',TO_DATE('2009-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0006','��������','0004','0001','2','2008',null,'50','10',TO_DATE('2009-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0007','�������ĳ�����ƣ�C++��','0003','0002','2','2008','0001','50','10',TO_DATE('2009-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0008','���亯������ֱ任','0002','0010','1','2009','0001','50','10',TO_DATE('2009-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0009','���ݽṹ','0002','0005','1','2008','0001','50','10',TO_DATE('2009-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0010','��ɢ��ѧ','0002','0007','1','2008','0001','50','10',TO_DATE('2009-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0011','����ϵͳ','0002','0005','2','2008','0001','50','10',TO_DATE('2010-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0012','��������ԭ��','0003','0010','2','2008','0001','50','10',TO_DATE('2010-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0013','���ݿ�ԭ��','0002','0006','2','2008','0001','50','10',TO_DATE('2010-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0014','Java���Գ������','0002','0001','1','2008','0001','50','10',TO_DATE('2010-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0015','���������','0002','0002','1','2008','0001','50','10',TO_DATE('2010-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0016','�㷨��������ݽṹ�γ����','0002','0007','1','2008','0001','50','10',TO_DATE('2010-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0017','�ִ�������������','0004','0008','1','2008',null,'50','10',TO_DATE('2010-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0018','Linux/Unix����ϵͳ','0002','0003','2','2008','0001','50','10',TO_DATE('2011-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0019','ë��˼����й���ɫ�������������ϵ����','0001','0008','2','2008',null,'50','10',TO_DATE('2011-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0020','�������','0002','0001','2','2008','0001','50','10',TO_DATE('2011-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0021','רҵӢ��','0002','0004','2','2008','0001','50','10',TO_DATE('2011-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0022','TCP/IPЭ��','0002','0004','1','2008','0001','50','10',TO_DATE('2011-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0023','��������ϵͳ���������','0002','0001','1','2008','0001','50','10',TO_DATE('2011-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0024','ϵͳ����뿪��ʵѵ','0002','0003','1','2008','0001','50','10',TO_DATE('2011-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0025','���׼���','0002','0002','1','2008','0001','50','10',TO_DATE('2011-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0026','��ҵ���','0002','0003','2','2008','0001','50','10',TO_DATE('2012-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);





create table ems_courserecord(
       idnum varchar(20) primary key  ,   
       studentId varchar(20),             
       classId varchar(20),               
       grade  varchar(3),                  
       chooseTime timestamp,              
       remarks varchar(300)            
   
);

insert into ems_courserecord values('0001','2008180501','0001','80',TO_DATE('2008-8-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0003','2008180501','0002','80',TO_DATE('2008-8-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0004','2008180501','0003','80',TO_DATE('2008-8-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0005','2008180501','0004','80',TO_DATE('2009-2-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0006','2008180501','0005','80',TO_DATE('2009-2-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0007','2008180501','0006','80',TO_DATE('2009-2-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0008','2008180501','0007','80',TO_DATE('2009-2-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0009','2008180501','0008','80',TO_DATE('2009-8-25 12:50:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0010','2008180501','0008','80',TO_DATE('2009-8-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0011','2008180501','0010','80',TO_DATE('2009-8-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0012','2008180501','0011','80',TO_DATE('2010-2-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0013','2008180501','0012','80',TO_DATE('2010-2-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0014','2008180501','0013','80',TO_DATE('2010-2-25 12:50:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0015','2008180501','0014','80',TO_DATE('2010-8-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0016','2008180501','0015','80',TO_DATE('2010-8-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0017','2008180501','0016','80',TO_DATE('2010-8-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0018','2008180501','0017','80',TO_DATE('2010-8-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0019','2008180501','0018','80',TO_DATE('2011-2-25 12:50:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0020','2008180501','0019','80',TO_DATE('2011-2-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0021','2008180501','0020','80',TO_DATE('2011-2-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0022','2008180501','0021','80',TO_DATE('2011-2-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0023','2008180501','0022','80',TO_DATE('2011-8-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0002','2008180501','0023','80',TO_DATE('2011-8-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0024','2008180501','0024','80',TO_DATE('2011-8-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0025','2008180501','0025','80',TO_DATE('2011-8-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_courserecord values('0026','2008180501','0026',null,TO_DATE('2012-2-25 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);



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

insert into Ems_applyModifyLog values('0002','0001',TO_DATE('2012-2-8 12:50:50','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-2-11 12:50:50','YYYY-MM-DD HH24:MI:SS'),'δͨ��','ems_teacher','0001','���֤����λ������');
insert into Ems_applyModifyLog values('0001','2008180503',TO_DATE('2012-2-9 12:50:50','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-2-12 12:50:50','YYYY-MM-DD HH24:MI:SS'),'δͨ��','ems_student','0002','������ʽ����');

insert into Ems_applyModifyLog values('0003','2008180501',TO_DATE('2012-2-15 12:50:50','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-2-17 12:50:50','YYYY-MM-DD HH24:MI:SS'),'��ͨ��','ems_student','0001',null);
insert into Ems_applyModifyLog values('0004','0002',TO_DATE('2012-2-23 12:50:50','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-2-25 12:50:50','YYYY-MM-DD HH24:MI:SS'),'��ͨ��','ems_teacher','0001',null);



create table ems_systemset(
       idnum varchar(20) primary key,   
       syssetname varchar(100),          
       timestart timestamp,              
       timeend timestamp,                 
       remarks varchar(500)              
)
insert into ems_systemset����values('0001','��ʦ��ӿγ�ʱ������',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-6-2 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_systemset����values('0002','��ʦ��ӱ�ҵ���ʱ������',TO_DATE('2012-1-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-6-2 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_systemset����values('0003','��ʦ¼��γ̳ɼ�ʱ������',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-6-2 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_systemset����values('0004','��ʦ¼���ҵ��Ƴɼ�ʱ������',TO_DATE('2012-4-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-6-2 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_systemset����values('0005','ѧ��ѡ��ʱ������',TO_DATE('2012-3-3 12:00:00','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-6-2 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_systemset����values('0006','ѧ����ҵ���ѡ��ʱ������',TO_DATE('2012-1-3 12:00:00','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-6-2 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);


create table ems_message(
  idnum    varchar(20) primary key,  ��
  sidnum   varchar(20), �������������� 
  tidnum   varchar(20),    ������������
  title  varchar(500),   ������������  
  content  varchar(500),   ������������
  mtime    timestamp,       ������������
  status   varchar(20),    ������������
  rcontent varchar(500),   ������������
  rtime    timestamp,       ������������
  remark   varchar(100)                
)

insert into ems_message values('0001','2008180501','0001','���Ա���x','��������x',TO_DATE('2012-1-3 12:00:00','YYYY-MM-DD HH24:MI:SS'),'δ�ظ�',null,null,null);
insert into ems_message values('0002','2008180502','0001','���Ա���y','��������d',TO_DATE('2012-1-3 12:00:00','YYYY-MM-DD HH24:MI:SS'),'δ�ظ�',null,null,null);
insert into ems_message values('0003','2008180501','0001','���Ա���z','��������e',TO_DATE('2012-1-3 12:00:00','YYYY-MM-DD HH24:MI:SS'),'�ѻظ�','�ظ�����2',TO_DATE('2012-1-3 15:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_message values('0004','2008180502','0001','���Ա���a','��������e',TO_DATE('2012-1-3 12:00:00','YYYY-MM-DD HH24:MI:SS'),'�ѻظ�','�ظ�����e',TO_DATE('2012-1-3 15:00:00','YYYY-MM-DD HH24:MI:SS'),null);
create table ems_gFlowConfi(
 idnum varchar(20) primary key,      
 sidnum varchar(20),          
 flowName varchar(100),      
 flag varchar(10),           
 grade varchar(5),            
 remark varchar(100)        
 
)

insert into ems_gFlowConfi values('0001','2008180501','ѧ��ѡ��','�����',null,null);
insert into ems_gFlowConfi values('0002','2008180501','������飬�ĵ���д','������',null,null);
insert into ems_gFlowConfi values('0003','2008180501','��ҵ���ԭ�Ϳ���',null,null);
insert into ems_gFlowConfi values('0004','2008180501','��ҵ��ƿ������','δ��ʼ',null,null);
insert into ems_gFlowConfi values('0005','2008180501','���ı�д','δ��ʼ',null,null);
insert into ems_gFlowConfi values('0006','2008180502','ѧ��ѡ��','������',null,null);
insert into ems_gFlowConfi values('0007','2008180502','������飬�ĵ���д','δ��ʼ',null,null);
insert into ems_gFlowConfi values('0008','2008180502','��ҵ���ԭ�Ϳ���','δ��ʼ',null,null);
insert into ems_gFlowConfi values('0009','2008180502','��ҵ��ƿ������','δ��ʼ',null,null);
insert into ems_gFlowConfi values('0010','2008180502','���ı�д','δ��ʼ',null,null);



create table Ems_workerType(
       idnum varchar(20)  ,            
       works_name varchar(100),        
       remarks varchar(1000)           
       
       );

insert into Ems_workerType values('0001','��ҵ���',null);
insert into Ems_workerType values('0002','������ҵ',null);



create table Ems_Workers(
       idnum varchar(20) primary key,    
       workerType varchar(20),         
       classTypeId varchar(20),        
       studentId varchar(20),          
       score int,              
       remarks varchar(1000)          
       
       
);

