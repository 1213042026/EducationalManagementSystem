create table ems_profession(
       idnum varchar(20),            
       pro_name varchar(100),        
       remarks varchar(1000)
       
       
);

insert into ems_profession values('0001','计算机科学与技术',null);
insert into ems_profession values('0002','电子信息技术',null);
insert into ems_profession values('0003','装潢设计',null);
insert into ems_profession values('0004','服装表演',null);
insert into ems_profession values('0005','机械制造',null);



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

insert into ems_teacher values('0001','0001','钟坚成',null,'男','420623198012341256',null,'湖南师范大学',null,'0001','汉族','博士','讲师',0);
insert into ems_teacher values('0002','0002','张健军',null,'男','420623198012341246',null,'湖南师范大学',null,'0001','汉族','博士','讲师',1);
insert into ems_teacher values('0003','0003','杨家红',null,'男','420623198012341236',null,'湖南师范大学',null,'0001','汉族','博士','教授',1);
insert into ems_teacher values('0004','0004','付玉',null,  '女','420623198012341232',null,'湖南师范大学',null,'0001','汉族','博士','副教授',1);
insert into ems_teacher values('0005','0005','肖柳明',null,'女','420623198012341237',null,'湖南师范大学',null,'0001','汉族','博士','讲师',1);
insert into ems_teacher values('0006','0006','盛艳',null,'女',  '420623198012341341',null,'湖南师范大学',null,'0001','汉族','博士','讲师',1);
insert into ems_teacher values('0007','0007','王灿东',null,'女','420623198012341347',null,'湖南师范大学',null,'0001','汉族','博士','讲师',1);
insert into ems_teacher values('0008','0008','刘立吾',null,'男','420623198012341347',null,'湖南师范大学',null,'0001','汉族','博士','讲师',1);
insert into ems_teacher values('0009','0009','潘启元',null,'男','420623198012341347',null,'湖南师范大学',null,'0001','汉族','博士','讲师',1);
insert into ems_teacher values('0010','0010','谢锦',null,'女','420623198012341347',null,'湖南师范大学',null,'0001','汉族','博士','讲师',1);



create table Ems_Manager(
       idnum varchar(20),          
       password varchar(20),        
       name varchar(100),           
       sex varchar(2),             
       idcard varchar(18),          
       address varchar(200),       
       nation varchar(50)         
);

insert into ems_manager values('system','system','系统管理员',null,null,null,null);
insert into ems_manager values('0001','0001','马萌','女','430623199010301234','湖南师范大学','汉族');
insert into ems_manager values('0002','0002','白艳','男','430623199010301334','湖南师范大学','汉族');
insert into ems_manager values('0003','0003','杨忻澎','男','430623199010301434','湖南师范大学','汉族');

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

insert into ems_student values('2008180501','123456','易倩',null,'女','430726198911293149',null,'0001','常德',null,'土家族','20080907',0);
insert into ems_student values('2008180503','123456','杨荣',null,'女','430726198904071441',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180504','123456','苏安波',null,'男','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180502','123456','聂双平',null,'女','430725199003200347',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180505','123456','李立',null,'男','430681198810053233',null,'0001','岳阳',null,'汉族','20080907',0);
insert into ems_student values('2008180506','123456','熊艳芳',null,'女','430723199009165447',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180507','123456','周圆',null,'女','431102199002195682',null,'0001','永州',null,'汉族','20080907',0);
insert into ems_student values('2008180508','123456','陈鑫林',null,'男','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180509','123456','杨智丽',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180510','123456','唐娅',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180511','123456','石小红',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180512','123456','雷小燕',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180513','123456','王金元',null,'男','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180514','123456','阎陈',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180515','123456','贺敏',null,'男','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180516','123456','邝燕财',null,'男','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180517','123456','黄骏',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180518','123456','肖瑶',null,'男','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180519','123456','文倩颖',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180520','123456','黄查',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180521','123456','刘焱',null,'男','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180522','123456','易革军',null,'男','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180523','123456','杨忻澎',null,'男','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180524','123456','刘霞',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180525','123456','梁佳朗',null,'男','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180526','123456','李团',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180527','123456','伍媚',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180528','123456','罗燕',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180529','123456','谭水平',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180530','123456','白艳',null,'男','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180531','123456','邹芳云',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180532','123456','郭玉娇',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180533','123456','王益妮',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180534','123456','唐强',null,'男','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180535','123456','成幸毅',null,'男','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180536','123456','黄丽芬',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180537','123456','胡超',null,'男','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180538','123456','马萌',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180539','123456','刘飞',null,'男','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180540','123456','吴亚辉',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180541','123456','周霞',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);
insert into ems_student values('2008180542','123456','肖杨',null,'女','430726198801042832',null,'0001','常德',null,'汉族','20080907',0);

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
insert into ems_graduation values('0001','基于Java Web的教务管理系统','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','容易','所须知识：html网页设计，java基础，jsp动态网页设计，SHH框架，Oracle数据库等。');
insert into ems_graduation values('0002','基于android的连连看3D游戏','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：java基础,SQLite 数据库等。');
insert into ems_graduation values('0003','基于J2EE与VBA的科研项目管理信息系统','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','容易','所须知识：Office VBA,html网页设计，java基础，jsp动态网页设计，SHH框架，Oracle数据库等。');
insert into ems_graduation values('0004','车载智能防盗系统的设计与实现','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','容易','所须知识：html + jsp + java + oracle等。');
insert into ems_graduation values('0005','远程视频控制软件的设计及实现','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','容易','所须知识：html + jsp + java + oracle等。');
insert into ems_graduation values('0006','基于office VBA报表系统','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','容易','所须知识：html + jsp + java + oracle等。');
insert into ems_graduation values('0007','软件测试BUG跟踪及管理系统设计','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','困难','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0008','P2P计算技术研究','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','困难','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0009','通用智能升级系统的设计与实现','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','困难','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0010','基于手机平台GPS轨迹重现系统设计与实现','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','困难','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0011','基于嵌入式平台矩阵类库设计与实现','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','困难','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0012','基于J2EE与VBA工作流引擎设计与实现','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','困难','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0013','J2EE教学管理系统','0001','10','10',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','困难','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0014','Word ( PDF )文档结构解析','0002','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0015','实践教学排课系统设计','0002','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0016','网络教学资源库设计','0002','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0017','实验仪器设备管理平台设计','0002','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0018','基于分形的动画特效设计','0002','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0019','电子文档复制取证研究','0002','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0020','印刷体文档数字水印设计','0002','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0021','基于ARM的智能家居系统','0003','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0022','基于Android平台的3G流量检测系统','0003','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0044','基于android平台的中英文词典','0003','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0023','LINUX下个人防火墙','0003','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0024','基于MAPX的公交车辆实时监控设计与研发','0003','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0025','GPS智能抄表终端系统','0003','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0026','电力抄表系统导航中的应用研究和算法设计','0003','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0027','商品团购网的设计与开发','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0028',' 智能搜索引擎的算法分析与设计','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0029','高等职业教育信息管理信息系统的设计与实现','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0030','基于WEB的软件水平考试平台的设计与开发','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0031','双语教学精品课程系统的设计与实现','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0032','生物冶金的数据仓库与数据挖掘的算法设计与实现','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0033','云计算终端的分析与设计','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0034','云计算服务器的分析与设计','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0035','文档数字水印的设计与开发','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0036','基于Android手机平台的音乐播放器的设计与实现','0004','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0037','教师工作量管理系统','0005','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0038','企业合同管理系统','0005','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0039','教学秘书系统','0005','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0040','中小企业成本核算系统','0005','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0041','二维码编码解码器设计','0006','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0042','用霍夫曼编码实现的数字图像无损压缩','0006','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0043','二手房租售网站设计','0006','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');
insert into ems_graduation values('0045','小区物业管理系统','0007','10','10',TO_DATE('2012-3-19 12:00:00','YYYY-MM-DD HH24:MI:SS'),'teacher','一般','所须知识：语言：C/C++/C#,,Java等。,数据库：MS SQL,Mysql,Oracle等。服务器：tomcat,IIS等。开发工具：Eclipse,Myeclipse,visual studio,plsqldev ,navicat,Rational Rose ,PowerDesigner,Visio等。');

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

insert into ems_classtype values('0001','公共必修课',null);
insert into ems_classtype values('0002','专业必修课',null);
insert into ems_classtype values('0003','专业限选课',null);
insert into ems_classtype values('0004','任意选修课',null);


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
insert into ems_class values('0001','大学英语（一）','0001','0008','1','2008',null,'50','10',TO_DATE('2008-8-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0002','电路','0004','0010','1','2008',null,'50','10',TO_DATE('2008-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0003','普通物理（一）','0003','0009','1','2008','0001','50','10',TO_DATE('2008-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0004','概率论与数理统计','0002','0010','2','2008','0001','50','10',TO_DATE('2009-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0005','高等数学（二）','0001','0009','2','2008',null,'50','0',TO_DATE('2009-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0006','军事理论','0004','0001','2','2008',null,'50','10',TO_DATE('2009-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0007','面向对象的程序设计（C++）','0003','0002','2','2008','0001','50','10',TO_DATE('2009-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0008','复变函数与积分变换','0002','0010','1','2009','0001','50','10',TO_DATE('2009-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0009','数据结构','0002','0005','1','2008','0001','50','10',TO_DATE('2009-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0010','离散数学','0002','0007','1','2008','0001','50','10',TO_DATE('2009-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0011','操作系统','0002','0005','2','2008','0001','50','10',TO_DATE('2010-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0012','计算机组成原理','0003','0010','2','2008','0001','50','10',TO_DATE('2010-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0013','数据库原理','0002','0006','2','2008','0001','50','10',TO_DATE('2010-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0014','Java语言程序设计','0002','0001','1','2008','0001','50','10',TO_DATE('2010-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0015','计算机网络','0002','0002','1','2008','0001','50','10',TO_DATE('2010-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0016','算法设计与数据结构课程设计','0002','0007','1','2008','0001','50','10',TO_DATE('2010-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0017','现代教育技术基础','0004','0008','1','2008',null,'50','10',TO_DATE('2010-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0018','Linux/Unix操作系统','0002','0003','2','2008','0001','50','10',TO_DATE('2011-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0019','毛泽东思想和中国特色社会主义理论体系概论','0001','0008','2','2008',null,'50','10',TO_DATE('2011-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0020','软件工程','0002','0001','2','2008','0001','50','10',TO_DATE('2011-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0021','专业英语','0002','0004','2','2008','0001','50','10',TO_DATE('2011-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0022','TCP/IP协议','0002','0004','1','2008','0001','50','10',TO_DATE('2011-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0023','面向对象的系统分析与设计','0002','0001','1','2008','0001','50','10',TO_DATE('2011-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0024','系统设计与开发实训','0002','0003','1','2008','0001','50','10',TO_DATE('2011-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0025','文献检索','0002','0002','1','2008','0001','50','10',TO_DATE('2011-8-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_class values('0026','毕业设计','0002','0003','2','2008','0001','50','10',TO_DATE('2012-2-1 12:00:50','YYYY-MM-DD HH24:MI:SS'),null);





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

insert into Ems_applyModifyLog values('0002','0001',TO_DATE('2012-2-8 12:50:50','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-2-11 12:50:50','YYYY-MM-DD HH24:MI:SS'),'未通过','ems_teacher','0001','身份证号码位数不对');
insert into Ems_applyModifyLog values('0001','2008180503',TO_DATE('2012-2-9 12:50:50','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-2-12 12:50:50','YYYY-MM-DD HH24:MI:SS'),'未通过','ems_student','0002','姓名格式不对');

insert into Ems_applyModifyLog values('0003','2008180501',TO_DATE('2012-2-15 12:50:50','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-2-17 12:50:50','YYYY-MM-DD HH24:MI:SS'),'已通过','ems_student','0001',null);
insert into Ems_applyModifyLog values('0004','0002',TO_DATE('2012-2-23 12:50:50','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-2-25 12:50:50','YYYY-MM-DD HH24:MI:SS'),'已通过','ems_teacher','0001',null);



create table ems_systemset(
       idnum varchar(20) primary key,   
       syssetname varchar(100),          
       timestart timestamp,              
       timeend timestamp,                 
       remarks varchar(500)              
)
insert into ems_systemset　　values('0001','老师添加课程时间设置',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-6-2 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_systemset　　values('0002','老师添加毕业设计时间设置',TO_DATE('2012-1-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-6-2 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_systemset　　values('0003','老师录入课程成绩时间设置',TO_DATE('2012-3-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-6-2 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_systemset　　values('0004','老师录入毕业设计成绩时间设置',TO_DATE('2012-4-1 12:00:00','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-6-2 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_systemset　　values('0005','学生选课时间设置',TO_DATE('2012-3-3 12:00:00','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-6-2 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_systemset　　values('0006','学生毕业设计选题时间设置',TO_DATE('2012-1-3 12:00:00','YYYY-MM-DD HH24:MI:SS'),TO_DATE('2012-6-2 12:00:00','YYYY-MM-DD HH24:MI:SS'),null);


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
)

insert into ems_message values('0001','2008180501','0001','留言标题x','留言内容x',TO_DATE('2012-1-3 12:00:00','YYYY-MM-DD HH24:MI:SS'),'未回复',null,null,null);
insert into ems_message values('0002','2008180502','0001','留言标题y','留言内容d',TO_DATE('2012-1-3 12:00:00','YYYY-MM-DD HH24:MI:SS'),'未回复',null,null,null);
insert into ems_message values('0003','2008180501','0001','留言标题z','留言内容e',TO_DATE('2012-1-3 12:00:00','YYYY-MM-DD HH24:MI:SS'),'已回复','回复内容2',TO_DATE('2012-1-3 15:00:00','YYYY-MM-DD HH24:MI:SS'),null);
insert into ems_message values('0004','2008180502','0001','留言标题a','留言内容e',TO_DATE('2012-1-3 12:00:00','YYYY-MM-DD HH24:MI:SS'),'已回复','回复内容e',TO_DATE('2012-1-3 15:00:00','YYYY-MM-DD HH24:MI:SS'),null);
create table ems_gFlowConfi(
 idnum varchar(20) primary key,      
 sidnum varchar(20),          
 flowName varchar(100),      
 flag varchar(10),           
 grade varchar(5),            
 remark varchar(100)        
 
)

insert into ems_gFlowConfi values('0001','2008180501','学生选题','已完成',null,null);
insert into ems_gFlowConfi values('0002','2008180501','需求调查，文档编写','进行中',null,null);
insert into ems_gFlowConfi values('0003','2008180501','毕业设计原型开发',null,null);
insert into ems_gFlowConfi values('0004','2008180501','毕业设计开发完成','未开始',null,null);
insert into ems_gFlowConfi values('0005','2008180501','论文编写','未开始',null,null);
insert into ems_gFlowConfi values('0006','2008180502','学生选题','进行中',null,null);
insert into ems_gFlowConfi values('0007','2008180502','需求调查，文档编写','未开始',null,null);
insert into ems_gFlowConfi values('0008','2008180502','毕业设计原型开发','未开始',null,null);
insert into ems_gFlowConfi values('0009','2008180502','毕业设计开发完成','未开始',null,null);
insert into ems_gFlowConfi values('0010','2008180502','论文编写','未开始',null,null);



create table Ems_workerType(
       idnum varchar(20)  ,            
       works_name varchar(100),        
       remarks varchar(1000)           
       
       );

insert into Ems_workerType values('0001','毕业设计',null);
insert into Ems_workerType values('0002','课堂作业',null);



create table Ems_Workers(
       idnum varchar(20) primary key,    
       workerType varchar(20),         
       classTypeId varchar(20),        
       studentId varchar(20),          
       score int,              
       remarks varchar(1000)          
       
       
);

