package cn.edu.management.comm;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import cn.edu.management.dao.ManagerDAO;
import cn.edu.management.vo.showVO.EMS_GraduateGradeA;
import cn.edu.management.vo.showVO.EMS_GraduationA;
import cn.edu.management.vo.showVO.EMS_GraduationB;
import cn.edu.management.vo.showVO.ShowMessageVO;
import cn.edu.management.vo.showVO.StudentVO;
import cn.edu.management.vo.showVO.showAlreadyAddCourse;
import cn.edu.management.vo.showVO.showApplyModifyLogVO;
import cn.edu.management.vo.showVO.showEmsClass;
import cn.edu.management.vo.showVO.showTeacher_VO;
import cn.edu.management.vo.voImpl.ApplyModifyLogVO;
import cn.edu.management.vo.voImpl.EMS_Class;
import cn.edu.management.vo.voImpl.EMS_GraduateGrade;
import cn.edu.management.vo.voImpl.EMS_Graduation;
import cn.edu.management.vo.voImpl.Ems_Student_VO;
import cn.edu.management.vo.voImpl.Ems_Teacher_VO;
import cn.edu.management.vo.voImpl.MessageVO;
import cn.edu.management.vo.voImpl.ProfessionVO;


/**
 * 
 * 用于在各VO类之间转换
 * 
 * 也就是将与表对应的VO类，转为方便显示的VO类，一般用于在就外键参照的表上
 * 
 * @author by
 *
 */
public  class VOconver {
	
	
	
	private ManagerDAO managerDao;
	
	
	@Resource
	public void setManagerDao(ManagerDAO managerDao) {
		this.managerDao = managerDao;
	}

	
	
	/**
	 * 
	 * 
	 * 
	 * @param source   待转换的VO类
	 * @return         目标VO类
	 */
	public static List<StudentVO> voConver(List<Ems_Student_VO> source) {

		List<StudentVO> studentvo = new ArrayList<StudentVO>();

		for (int i = 0; i <source.size(); i++) {
			StudentVO destStu = new StudentVO();
			Ems_Student_VO sourStu = source.get(i);

			destStu.setIdnum(sourStu.getIdnum());
			destStu.setPassword(sourStu.getPassword());
			destStu.setName(sourStu.getName());
			destStu.setSex(sourStu.getSex());
			destStu.setIdcard(sourStu.getIdcard());
			
//			System.out.println("专业号："+
//				 if(	sourStu.getProfession().getPro_name() == null)
//					 System.out.println("空");
//				 else
//					 System.out.println("不空");
//				.getPro_name());
			
			destStu.setProf_Name(sourStu.getProfession().getPro_name());// 得到外键的专业号
			destStu.setAddress(sourStu.getAddress());
			destStu.setNation(sourStu.getNation());
			destStu.setEntranceDate(sourStu.getEntranceDate());
			destStu.setFlag(sourStu.getFlag());

			studentvo.add(destStu);// 添加入集合
		}

		return studentvo;
	}
	
	
	/**
	 * 将毕业设计表类对象--显示教师已添加的毕业设计的显示VO类
	 * @param source
	 * @return
	 */
	public static List<EMS_GraduationA> voConverGa(List<EMS_Graduation> source) {

		List<EMS_GraduationA> graduationtvo = new ArrayList<EMS_GraduationA>();

		for (int i = 0; i <source.size(); i++) {
			EMS_GraduationA destStu = new EMS_GraduationA();
			EMS_Graduation sourStu = source.get(i);

//			private String idnum;// varchar2(20), --编号P K
//			private String gname; // gname varchar2(100),      --毕业设计名称
//			private String gyear; // gyear varchar2(10),     --学年
//			private String term;// term varchar2(10),      --学期
//			private String grade;// grade varchar2(20),      --所属年级
//			private String professionId; // professionId    --所属专业F K
//			private String gcount; // gcount varchar2(10),         --可选人数
//			private String rcount; //  rcount varchar2(10),        --剩于人数
//			private Timestamp recordTime; // recordTime timestamp,    --录入时间
//			private String remarks; // remark varchar2(300),   --备注
			
			destStu.setIdnum(sourStu.getIdnum());
			destStu.setGname(sourStu.getGname());
			
			//得到学年
			String gyear = DateConventer.timestampToStrYear(sourStu.getRecordTime());
			destStu.setGyear(gyear);

//			Integer ggrade = Integer.valueOf(gyear);
//			
//			destStu.setGrade(sourStu.getGrade());
//			destStu.setProfessionId(sourStu.getProfessionId().getPro_name());//外键
//			destStu.setProf_Name(sourStu.getProfession().getPro_name());// 得到外键的专业号
			destStu.setGlevel(sourStu.getGlevel());
			destStu.setFlag(sourStu.getFlag());
			
			destStu.setGcount(sourStu.getGcount());
			destStu.setRcount(sourStu.getRcount());
			 destStu.setRecordTime(DateConventer.timestampToStrNo(sourStu.getRecordTime()));
			destStu.setRemarks(sourStu.getRemark());
           
            graduationtvo.add(destStu);// 添加入集合
		}

		return graduationtvo;
	}
	
	
	/**
	 * 将毕业设计表类对象--显示学生对毕业设计进行选题的显示VO类
	 * @param source
	 * @return
	 */
	public static List<EMS_GraduationB> voConverGaS(List<EMS_Graduation> source) {

		List<EMS_GraduationB> graduationtvo = new ArrayList<EMS_GraduationB>();

		for (int i = 0; i <source.size(); i++) {
			EMS_GraduationB destStu = new EMS_GraduationB();
			EMS_Graduation sourStu = (EMS_Graduation)source.get(i);

//			private String idnum;// varchar2(20), --编号P K
//			private String gname; // gname varchar2(100),      --毕业设计名称
//			private String teacherId;// teacherId
//			private String gyear; // gyear varchar2(10),     --学年
//			private String flag;// varchar(20),                     --标志（学生申请插入student,老师插入teacher）
//			private String  glevel ;//varchar(20),                   --容易，一般，困难  
//			private String grade;// grade varchar2(20),      --所属年级
//			private String professionId; // professionId    --所属专业F K
//			private String gcount; // gcount varchar2(10),         --可选人数
//			private String rcount; //  rcount varchar2(10),        --剩于人数
//			private String recordTime; // recordTime timestamp,    --录入时间
//			private String remarks; // remark varchar2(300),   --备注
			
			destStu.setIdnum(sourStu.getIdnum());
			destStu.setGname(sourStu.getGname());
			
			//得到学年
			String gyear = DateConventer.timestampToStrYear(sourStu.getRecordTime());
			destStu.setGyear(gyear);

//			Integer ggrade = Integer.valueOf(gyear);
//			
//			destStu.setGrade(sourStu.getGrade());
			
			destStu.setTeacherId(sourStu.getTeacherId().getName());
			
//destStu.setProfessionId(sourStu.getProfessionId().getPro_name());//外键
//			destStu.setProf_Name(sourStu.getProfession().getPro_name());// 得到外键的专业号
			destStu.setGlevel(sourStu.getGlevel());
			destStu.setFlag(sourStu.getFlag());
			destStu.setGcount(sourStu.getGcount());
			destStu.setRcount(sourStu.getRcount());
			 destStu.setRecordTime(DateConventer.timestampToStrNo(sourStu.getRecordTime()));
			destStu.setRemarks(sourStu.getRemark());
           
            graduationtvo.add(destStu);// 添加入集合
		}

		return graduationtvo;
	}
	
	
	/**
	 * 将毕业设计表类对象--显示学生查看已选择的毕业设计显示VO类
	 * @param source
	 * @return
	 */
	public static EMS_GraduateGradeA voConverMyProject(EMS_GraduateGrade source) {

		EMS_GraduateGradeA destGra = new EMS_GraduateGradeA();
		EMS_GraduateGrade sourceGra = source;

//		private String gname; // 毕业设计名称 FK
//		private String grade; // varchar2(3), --成绩
//		private String teacherName; //指导老师
//		Timestamp gxtime;//选题时间
//		private String remark; // varchar2(500), --备注


		//设置毕业设计编号
		destGra.setGidnum(sourceGra.getGidnum().getIdnum());
		//设置毕业设计名称
		destGra.setGname(sourceGra.getGidnum().getGname());
		//设置成绩
		destGra.setGrade(sourceGra.getGrade());
		//设置指导老师
		destGra.setTeacherName(sourceGra.getGidnum().getTeacherId().getName());
		//设置选题时间
		destGra.setGxtime(DateConventer.timestampToStr(sourceGra.getGxtime()));
		//设置备注
		destGra.setRemark(sourceGra.getRemark());

		return destGra;
	}
	
	public static List<showEmsClass> voConverClass(List<EMS_Class> source) {
		// TODO Auto-generated method stub
		List<showEmsClass> classVO=new ArrayList<showEmsClass>();
		
		for(int i=0;i<source.size();i++)
		{
			showEmsClass destVO=new showEmsClass();
			EMS_Class sourceVO = source.get(i);
			
			destVO.setClassId(sourceVO.getClassId());
			destVO.setClassName(sourceVO.getClassName());
			if(sourceVO.getClassType()!=null)
			destVO.setClassType(sourceVO.getClassType().getClasstypename());
			destVO.setGrade(sourceVO.getGrade());
			destVO.setM(sourceVO.getkCount());
			if(sourceVO.getProfessionId()!=null)
			destVO.setProfessionId(sourceVO.getProfessionId().getPro_name());
			destVO.setRecordTime(DateConventer.timestampToStr(sourceVO.getRecordTime()));
			if(sourceVO.getRemark()!=null)
			destVO.setRemark(sourceVO.getRemark());
			destVO.setN(sourceVO.getsCount());
			destVO.setTeacherId(sourceVO.getTeacherId().getName());
			destVO.setTerm(sourceVO.getTerm());
			//           
			classVO.add(destVO);// 添加入集合
		}
		return classVO;
	}
	
	

	/**
	 * 将申请记录表类对象--显示已审核学生信息的显示VO类
	 * @param source
	 * @return
	 */
	public static List<showApplyModifyLogVO> voConverGb(List<ApplyModifyLogVO> source) {

		List<showApplyModifyLogVO> graduationtvo = new ArrayList<showApplyModifyLogVO>();

		for (int i = 0; i <source.size(); i++) {
			showApplyModifyLogVO destStu = new showApplyModifyLogVO();
			ApplyModifyLogVO sourStu = source.get(i);
		    destStu.setIdnum(sourStu.getIdnum());
			destStu.setApplyUserId(sourStu.getApplyUserId());
			String applyTime = DateConventer.timestampToStr(sourStu.getApplyDate());
			destStu.setApplyDate(applyTime);
			if(sourStu.getAuditDate()!=null){
			String auditTime = DateConventer.timestampToStr(sourStu.getAuditDate());
			destStu.setAuditDate(auditTime);}
			if(sourStu.getAuditDate()!=null){					
			destStu.setAuditMan(sourStu.getAuditMan());
			}
			destStu.setRemarks(sourStu.getRemarks());//毕业选题审核记录
			destStu.setModifyResult(sourStu.getModifyResult());
			destStu.setRemark(sourStu.getRemark());
			//           
            graduationtvo.add(destStu);// 添加入集合
		}

		return graduationtvo;
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * @param source   待转换的VO类
	 * @return         目标VO类
	 */
	public static List<showTeacher_VO> voConverTeacher(List<Ems_Teacher_VO> source) {

//	     idnum varchar2(20),         --老师编号：顺序生成做为索引pk
//	       password varchar2(20),      --密码
//	       name varchar2(100),         --姓名
//	       name_temp varchar2(100),    --姓名备份
//	       sex varchar2(2),            --性别
//	       idcard varchar2(18),        --身份证号码
//	       idcard_temp varchar2(18),   --身份证号码备份
//	       address varchar2(200),      --家庭住址
//	       address_temp varchar2(200), --家庭住址备份
//	       profession  varchar2(20),  --专业号
//	       nation varchar2(50),        --民族
//	       scientific varchar2(5),     --学历  
//	       title varchar2(5),          --职称 
//	       flag number default 0,      --修改标志：暂时没用
	      
		List<showTeacher_VO> teacherVO = new ArrayList<showTeacher_VO>();

		for (int i = 0; i <source.size(); i++) {
			showTeacher_VO destStu = new showTeacher_VO();
			Ems_Teacher_VO sourStu = source.get(i);

			destStu.setIdnum(sourStu.getIdnum());
		//destStu.setPassword(sourStu.getPassword());
			destStu.setName(sourStu.getName());
			//destStu.setUsername_temp(sourStu.getName_temp());
			destStu.setSex(sourStu.getSex());
			destStu.setIdcard(sourStu.getIdcard());
			destStu.setAddress(sourStu.getAddress());
			destStu.setProfession(sourStu.getProfession().getPro_name());// 得到外键的专业号
			
//			System.out.println("专业号："+
//				 if(	sourStu.getProfession().getPro_name() == null)
//					 System.out.println("空");
//				 else
//					 System.out.println("不空");
//				.getPro_name());
			
			
			destStu.setAddress(sourStu.getAddress());
			destStu.setNation(sourStu.getNation());
			destStu.setScientific(sourStu.getScientific());
			destStu.setTitle(sourStu.getTitle());
			destStu.setFlag(sourStu.getFlag());

			teacherVO.add(destStu);// 添加入集合
		}

		return teacherVO;
	}
	

	/**
	 *  用于显示已添加课程界面
	 * 
	 * 
	 * @param source   待转换的VO类
	 * @return         目标VO类
	 */
	public static List<showAlreadyAddCourse> voConverdisAlreadyCourse(List<EMS_Class> source) {

//	     idnum varchar2(20),         --老师编号：顺序生成做为索引pk
//	       password varchar2(20),      --密码
//	       name varchar2(100),         --姓名
//	       name_temp varchar2(100),    --姓名备份
//	       sex varchar2(2),            --性别
//	       idcard varchar2(18),        --身份证号码
//	       idcard_temp varchar2(18),   --身份证号码备份
//	       address varchar2(200),      --家庭住址
//	       address_temp varchar2(200), --家庭住址备份
//	       profession  varchar2(20),  --专业号
//	       nation varchar2(50),        --民族
//	       scientific varchar2(5),     --学历  
//	       title varchar2(5),          --职称 
//	       flag number default 0,      --修改标志：暂时没用
	      
		List<showAlreadyAddCourse> courseVO = new ArrayList<showAlreadyAddCourse>();

		for (int i = 0; i <source.size(); i++) {
			showAlreadyAddCourse destStu = new showAlreadyAddCourse();
			EMS_Class sourStu = source.get(i);

//			private String classId ; // varchar2(20), --课程编号ＰＫ
//		    private String className ; //varchar2(100), --课程名称
//			private String classType ;//课程类型名//FK
//			private String teacherId ; //教师姓名    //FK
//		    private String term ; // varchar2(10),  --学期	
//		    private String grade ; // varchar2(20),  --所属年级    
//			private String  professionId ;//专业名称	FK
//		    private String  kCount ;//varchar2(10),                  --可选人数
//		    private String sCount;// varchar2(10),                  --剩于人数    
//		    private String recordTime ; //timestamp,  --录入课程时间    
//		    private String remark;
//		    
			destStu.setClassId(sourStu.getClassId());
			destStu.setClassName(sourStu.getClassName());
			destStu.setClassType(sourStu.getClassType().getClasstypename());
			destStu.setTerm(sourStu.getTerm());
		    destStu.setGrade(sourStu.getGrade());
		    ProfessionVO proVO = sourStu.getProfessionId();		   
		    if(proVO!= null){
		    destStu.setProfessionId(proVO.getPro_name());
		    }
		    else{
		    	destStu.setProfessionId("任意选修课");
		    	
		    }
		    destStu.setKkCount(sourStu.getkCount());
		    destStu.setSsCount(sourStu.getsCount());
		    destStu.setRecordTime( DateConventer.timestampToStr(sourStu.getRecordTime()));
            destStu.setRemark(sourStu.getRemark());

			courseVO.add(destStu);// 添加入集合
		}

		return courseVO;
	}
	
	/**
	 *  用于显示已回复留言
	 * 
	 * 
	 * @param source   待转换的VO类
	 * @return         目标VO类
	 */
	public static List<ShowMessageVO> voConverdisAlreadyReplyMessage(List<MessageVO> source) {

//		  idnum    varchar2(20) primary key,  　--编号 PK
//		  sidnum   varchar2(20), 　　　　　　　 --学生编号 FK
//		  tidnum   varchar2(20),    　　　　　　--教师编号 FK
//		  title  varchar2(500),   　　　　　　  --留言主题
//		  content  varchar2(500),   　　　　　　--留言内容
//		  mtime    timestamp,       　　　　　　--留言时间
//		  status   varchar2(20),    　　　　　　--回复状态(未回复，已回复)
//		  rcontent varchar2(500),   　　　　　　--回复内容
//		  rtime    timestamp,       　　　　　　--回复时间
//		  remark   varchar(100),                --备注   
	      
		List<ShowMessageVO> courseVO = new ArrayList<ShowMessageVO>();

		for (int i = 0; i <source.size(); i++) {
			ShowMessageVO destStu = new ShowMessageVO();
			MessageVO sourStu = source.get(i);

		   destStu.setIdnum(sourStu.getIdnum());
		   destStu.setSidnum(sourStu.getSidnum().getIdnum());
		   destStu.setTidnum(sourStu.getTidnum().getIdnum());
		   destStu.setTitle(sourStu.getTitle());
		   destStu.setContent(sourStu.getContent());
		   
		   Timestamp mtime = sourStu.getMtime() ;
		   if(mtime != null)
		   destStu.setMtime(DateConventer.timestampToStr(mtime));
		   
		   destStu.setStatus(sourStu.getStatus());
		   destStu.setRcontent(sourStu.getRcontent());
		 
		   Timestamp rtime = sourStu.getRtime() ;
		   if(rtime != null)
		   destStu.setRtime(DateConventer.timestampToStr(rtime));
		   
		   destStu.setRemark(sourStu.getRemark());  
		 //  String s=DateConventer.timestampToStr(rtime);

			courseVO.add(destStu);// 添加入集合
		}

		return courseVO;
	}
	
	
	

}
