package cn.edu.management.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.edu.management.comm.DateConventer;
import cn.edu.management.comm.VOconver;
import cn.edu.management.dao.SuperDAO;
import cn.edu.management.dao.TeacherDAO;
import cn.edu.management.vo.showVO.GraduationStudentInfor;
import cn.edu.management.vo.showVO.ShowMessageVO;
import cn.edu.management.vo.showVO.StudentGraduationWorksMange;
import cn.edu.management.vo.showVO.disAuditGraduationResult;
import cn.edu.management.vo.showVO.disAuditGraduationTitle;
import cn.edu.management.vo.showVO.showAlreadyAddCourse;
import cn.edu.management.vo.voImpl.ApplyModifyLogVO;
import cn.edu.management.vo.voImpl.ClassTypeVO;
import cn.edu.management.vo.voImpl.EMS_Class;
import cn.edu.management.vo.voImpl.EMS_CourseRecord;
import cn.edu.management.vo.voImpl.EMS_GraduateGrade;
import cn.edu.management.vo.voImpl.EMS_Graduation;
import cn.edu.management.vo.voImpl.EMS_Systemset;
import cn.edu.management.vo.voImpl.Ems_Student_VO;
import cn.edu.management.vo.voImpl.Ems_Teacher_VO;
import cn.edu.management.vo.voImpl.MessageVO;

@Component("teacherDao")
public class TeacherDAOImpl extends SuperDAO implements TeacherDAO {

	/****************************************** 个人信息维护 ***********************************************/
	/***** 查看个人信息 *****/
	public Ems_Teacher_VO displayPersonInformation(String idnum) {
        System.out.println("DAO编号："+idnum);
		HibernateTemplate hibernate = this.getHibernateTemplate();
		Ems_Teacher_VO vo = new Ems_Teacher_VO();
		vo.setIdnum(idnum);
		List list =  this.getSession().createQuery("from Ems_Teacher_VO where idnum='"+idnum +"'").list();
		Ems_Teacher_VO teachervo = (Ems_Teacher_VO) list.get(0);
		teachervo.getProfession().getPro_name();
		return teachervo;
	}	
	
	/******************************************毕业设计管理***********************************************/
	/*****查看已添加毕业设计*****/
	public List<EMS_Graduation>  displayAlreadyAdd(String idnum){
//		List<EMS_Graduation> list = this.getHibernateTemplate().loadAll( EMS_Graduation.class );
		List<EMS_Graduation> list = this.getSession().createQuery("from EMS_Graduation where teacherId ='"+idnum +"' and flag='teacher'" ).list();
		VOconver.voConverGa(list);
		return list ;
	};	
		
	/**
	 * 取到所有毕业设计中编号最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutGraduate(){
		
		List list = this.getSession().createQuery("select max(idnum) from EMS_Graduation t").list();
		 if(list == null || list.size() <= 0)		
			 return null;
		return (String) list.get(0);

		
	};

	/**
	 * 取到所有毕业设计中编号最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutApplyModify(){
		List list = this.getSession().createQuery("select max(idnum) from ApplyModifyLogVO t").list();
		System.out.println("DAO申请修改编号："+(String)list.get(0));
		return (String)list.get(0);
	};
	
	//找出相应的申请表记录
	public List<ApplyModifyLogVO>  displayAlreadyApplyStatus(String idnum){
		String sql = "from ApplyModifyLogVO where applyUserId='"+idnum+"'";
		System.out.println("IdnumSql:"+sql);
		List<ApplyModifyLogVO> list = this.getSession().createQuery(sql).list();
		
		return list;
	};
	
	
	/**
	 * 点击添加申请按钮时
	 * 
	 * 在申请记录表查找是否有这个老师的记录且审核结果为“审核中”
	 */
	public ApplyModifyLogVO addModifyApplycheck(String applyUserId){
		String sql = "from ApplyModifyLogVO where applyUserId='"+ applyUserId +
		"'and modifyResult='审核中'";
		System.out.println("添加申请提示"+sql);
		 
		 List list = this.getHibernateTemplate().find(sql);
		 if(list == null || list.size() <= 0)		
		 return null;
		 
		 return (ApplyModifyLogVO)list.get(0);
	};
	
	/**
	 * 得到相应编号的毕业设计
	 * @param idnum
	 * @return
	 */
	public EMS_Graduation getModifyGraduationInfo(String idnum){
		List list = this.getSession().createQuery("from EMS_Graduation where idnum='"+idnum+"'").list();
		
		if(list ==null || list.size() <= 0)
			return null;
		return (EMS_Graduation)list.get(0);
	};
	
//	public void test() {
//		String sql = "from EMS_GraduateGrade g,Ems_Student_VO s where g.studentId= s.idnum"; 
//		List list = this.getSession().createQuery(sql).list();
//		Object[] obj = (Object[])list.get(0);
//		EMS_GraduateGrade g = (EMS_GraduateGrade)obj[0];
//		System.out.println("成绩："+g.getGrade());
//		
//	}
	/**
	 * 查询毕业设计学生信息
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearch(String teacherid){
		
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//界面类
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			EMS_GraduateGrade gg = (EMS_GraduateGrade)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//毕业设计记录表编号
			System.out.println("毕名："+g.getGname());
			gStudentInfor.setGname(g.getGname());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("毕名："+g.getGname());
	//	System.out.println("学生姓名:"+s.getName());
	
		return gStudentInforList;
	
	};
	
	/**
	 * 分页--查询毕业设计学生信息
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearchPage(String teacherid,int nowPage){
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum"; 
		Query query = this.getSession().createQuery(sql);
		query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);
		
		List<Object[]> list = query.list();
		
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//界面类
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			EMS_GraduateGrade gg = (EMS_GraduateGrade)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//毕业设计记录表编号
			System.out.println("毕名："+g.getGname());
			gStudentInfor.setGname(g.getGname());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("毕名："+g.getGname());
	//	System.out.println("学生姓名:"+s.getName());
	
		return gStudentInforList;
	
	};
	  
	/**
	 * 学生文件管理 
	 * @return
	 */
	public List<StudentGraduationWorksMange> studentFileManage(String teacherid){
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<StudentGraduationWorksMange> gStudentInforList = new ArrayList<StudentGraduationWorksMange>();//界面类
		for(Object[] obj:list){
			StudentGraduationWorksMange gStudentInfor = new StudentGraduationWorksMange();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			System.out.println("毕名："+g.getGname());
			gStudentInfor.setGname(g.getGname());//毕名
			gStudentInfor.setPro_name(s.getProfession().getPro_name());//专业名
			gStudentInfor.setSidnum(s.getIdnum());//学号
			gStudentInfor.setSname(s.getName());//学生姓名
			gStudentInfor.setGidnum(g.getIdnum());//毕编号
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("毕名："+g.getGname());
	//	System.out.println("学生姓名:"+s.getName());
	
		return gStudentInforList;
	};
	/**
	 * 学生文件管理 
	 * @return
	 */
	public List<StudentGraduationWorksMange> studentFileManagePage(String teacherid,int pageNow){
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum"; 
		
		Query query = this.getSession().createQuery(sql);
		  query.setMaxResults(6);
		   query.setFirstResult((pageNow-1)*6);
		   
		List<Object[]> list = query.list();
		
		List<StudentGraduationWorksMange> gStudentInforList = new ArrayList<StudentGraduationWorksMange>();//界面类
		for(Object[] obj:list){
			StudentGraduationWorksMange gStudentInfor = new StudentGraduationWorksMange();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			System.out.println("毕名："+g.getGname());
			gStudentInfor.setGname(g.getGname());//毕名
			gStudentInfor.setPro_name(s.getProfession().getPro_name());//专业名
			gStudentInfor.setSidnum(s.getIdnum());//学号
			gStudentInfor.setSname(s.getName());//学生姓名
			gStudentInfor.setGidnum(g.getIdnum());//毕编号
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("毕名："+g.getGname());
	//	System.out.println("学生姓名:"+s.getName());
	
		return gStudentInforList;
	};
	
	
	/**
	 * 多表操作
	 *ems_graduation
	 *ems_graduationgrade
	 *ems_student 
	 */
	public List<Object[]>  graStuGragrade(String teacherid){
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.grade!=null and gg.studentId=s.idnum"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		return list;
	} 
	/**
	 * 多表操作
	 *ems_graduation
	 *ems_graduationgrade
	 *ems_student 
	 */
	public List<Object[]>  graStuGragrade(String teacherid,int nowPage){
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.grade!=null and gg.studentId=s.idnum"; 
	  	Query query = this.getSession().createQuery(sql);
		   query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);
		List<Object[]> list = query.list();
		return list;
	} 
	//显示要审核的毕业设计题目
	public List<disAuditGraduationTitle> disAuditGraduationTitle(String tidnum){
		String sql = "from ApplyModifyLogVO ap,Ems_Student_VO es,EMS_Graduation eg where ap.fortable='ems_graduation' and ap.modifyResult='审核中' and ap.remark=es.idnum"
			+" and eg.idnum=ap.applyUserId and eg.teacherId='"+tidnum+"'"	;
		
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<disAuditGraduationTitle> gStudentInforList = new ArrayList<disAuditGraduationTitle>();
		for(Object[] obj:list){
			disAuditGraduationTitle gStudentInfor = new disAuditGraduationTitle();
			ApplyModifyLogVO ap = (ApplyModifyLogVO)obj[0];
			Ems_Student_VO es = (Ems_Student_VO)obj[1];
			
			//System.out.println("毕号："+ap.getApplyUserId());
			gStudentInfor.setGidnum(ap.getApplyUserId());//毕业设计号,用于标记为teacher
		//	System.out.println("学号："+ap.getRemark());
			gStudentInfor.setSidnum(ap.getRemark());//学号,用于写入选毕业设计记录表中		
			gStudentInfor.setGgidnum(ap.getIdnum());// 毕业设计记录表编号,用于更改为审核结果
			gStudentInfor.setProfession(es.getProfession().getPro_name());//专业
			gStudentInfor.setApplyDate(DateConventer.timestampToStr(ap.getApplyDate()));//申请时间
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
		return gStudentInforList;
	}
	
	
	//得到最大编号从毕业设计记录表中
	public String getMaxIdNumAboutGraduateGrade(){
		List list = this.getSession().createQuery("select max(idnum) from EMS_GraduateGrade t").list();
		System.out.println("DAO毕业设计记录表编号："+(String)list.get(0));
		return (String)list.get(0);
	};
	
	//得到审核记录
	public  List<disAuditGraduationResult> disAditInfor(String tidnum){
	
			String sql = "from ApplyModifyLogVO ap where fortable='ems_graduation'" +
					" and modifyResult!='审核中' and auditMan='"+tidnum+"' ";
			System.out.println("disAditInfor的sql:"+sql);
			
			List list = this.getSession().createQuery(sql).list();
//			private String sidnum;//学号			
//		    private String graduation ; //integer, --毕业设计名称
//		    private String applyDate ; //date,  --申请时间 		    
//		    private String auditDate ; //date,  --审核时间    
//		    private String result ; //审核结果
			List<disAuditGraduationResult> gStudentInforList = new ArrayList<disAuditGraduationResult>();
			for(Object obj:list){
				disAuditGraduationResult gStudentInfor = new disAuditGraduationResult();
				ApplyModifyLogVO ap = (ApplyModifyLogVO)obj;				
				
				//System.out.println("毕号："+ap.getApplyUserId());
				gStudentInfor.setSidnum(ap.getRemark());//学号
			//	System.out.println("学号："+ap.getRemark());
			  //  gStudentInfor.setGraduation(eg.getGname());//毕业设计名称				
				gStudentInfor.setApplyDate(DateConventer.timestampToStr(ap.getApplyDate()));//申请时间
				gStudentInfor.setAuditDate(DateConventer.timestampToStr(ap.getAuditDate()));//审核时间
				gStudentInfor.setResult(ap.getModifyResult());//审核结果
				gStudentInfor.setSuggest(ap.getRemarks());//审核建议
				gStudentInforList.add(gStudentInfor);//加入单条记录
			}
			
			return  gStudentInforList ;
		
		
	};
	
	/**
	 * 学生毕业设计成绩录入
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeInput(String teacherid){
		
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum and gg.grade=null"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//界面类
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			EMS_GraduateGrade gg = (EMS_GraduateGrade)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//毕业设计记录表编号
			System.out.println("毕名："+g.getGname());
			gStudentInfor.setGname(g.getGname());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("毕名："+g.getGname());
	//	System.out.println("学生姓名:"+s.getName());
	
		return gStudentInforList;
	};
	
	/**
	 * 学生毕业设计成绩录入--分页
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeInput(String teacherid,int nowPage){
		
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum and gg.grade=null"; 
		Query query = this.getSession().createQuery(sql);
		   query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);
		List<Object[]> list = query.list();
		
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//界面类
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			EMS_GraduateGrade gg = (EMS_GraduateGrade)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//毕业设计记录表编号
			System.out.println("毕名："+g.getGname());
			gStudentInfor.setGname(g.getGname());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("毕名："+g.getGname());
	//	System.out.println("学生姓名:"+s.getName());
	
		return gStudentInforList;
	};
	
	/**
	 * 得到指定毕业设计记录表中的记录
	 * @param ggidnum
	 * @return
	 */
	public EMS_GraduateGrade getSpecifyGraduationG(String ggidnum){
		List list = this.getSession().createQuery("from EMS_GraduateGrade where idnum='"+ggidnum+"'").list();
		if(list==null || list.size()==0)
		  return null;
		return (EMS_GraduateGrade)list.get(0);
	};
	
	
	/**
	 * 学生毕业设计成绩修改
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeupdate(String teacherid){
		
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum and gg.grade!=null"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//界面类
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			EMS_GraduateGrade gg = (EMS_GraduateGrade)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//毕业设计记录表编号
			gStudentInfor.setGrade(gg.getGrade());//成绩
			System.out.println("毕名："+g.getGname());
			gStudentInfor.setGname(g.getGname());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("毕名："+g.getGname());
	//	System.out.println("学生姓名:"+s.getName());
	
		return gStudentInforList;
	};
	
	
	/**
	 * 显示已添加课程
	 * @param teacherid
	 * @return
	 */
	public List<showAlreadyAddCourse> displayAlreadyAddCourse(String teacherid){
      String sql = "from EMS_Class where teacherId='" + teacherid +"'";
      List<EMS_Class> list = this.getSession().createQuery(sql).list();
      //在Dao层完成转换
		return VOconver.voConverdisAlreadyCourse(list);
	};
	
	/**
	 * 得到课程表的最大号
	 */
	public String getMaxIdNumAboutCourse(){
		List list = this.getSession().createQuery("select max(classId) from EMS_Class t").list();
		 if(list == null || list.size() <= 0)		
			 return null;
		return (String) list.get(0);
		
	};
	
	/**
	 * 得到课程类型
	 */
    public List<ClassTypeVO> listClassType(){
    	List<ClassTypeVO> list = this.getHibernateTemplate().loadAll( ClassTypeVO.class );
    	return list;
    };
	
    
    /**
     * 得到相应编号的课程
     */
    public EMS_Class getModifyCourseInfo(String cidnum){
     List list = this.getSession().createQuery("from EMS_Class where classId='"+cidnum+"'").list();
		
		if(list ==null || list.size() <= 0)
			return null;
		return (EMS_Class)list.get(0);
    };
    
    
    
    /**
	 * 查询选课学生信息
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearchCourse(String teacherid){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//界面类
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			//gStudentInfor.setGgidnum(gg.getIdnum());//毕业设计记录表编号
			System.out.println("课程名称："+g.getClassName());
			gStudentInfor.setGname(g.getClassName());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("毕名："+g.getGname());
	//	System.out.println("学生姓名:"+s.getName());
	
		return gStudentInforList;
	
	};
	
	/**
	 * 查询选课学生信息成绩

	 */
	@SuppressWarnings("unchecked")
public List<Object[]>  graStuGragradeCourse(String teacherid,String nowPage,int nowPag){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.grade!=null and gg.studentId=s.idnum and s.idnum='"+nowPage+"'"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		return list;
	} 
	/**
	 * 查询选课学生信息成绩

	 */
	@SuppressWarnings("unchecked")
	public List<Object[]>  graStuGragradeCourse(String teacherid){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.grade!=null and gg.studentId=s.idnum"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		return list;
	} 
	/**
	 * 学生毕业设计成绩录入
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeInput(String teacherid){
		
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum and gg.grade=null"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//界面类
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//选课记录表编号
		//	System.out.println("毕名："+g.getGname());
			gStudentInfor.setGname(g.getClassName());//课程名
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("毕名："+g.getGname());
	//	System.out.println("学生姓名:"+s.getName());
	
		return gStudentInforList;
	};
	
	
    /**
     * 得到相应编号的课程
     */
    public EMS_CourseRecord getSpecifyCourseRecord(String cidnum){
     List list = this.getSession().createQuery("from EMS_CourseRecord where idnum='"+cidnum+"'").list();
		
		if(list ==null || list.size() <= 0)
			return null;
		return (EMS_CourseRecord)list.get(0);
    };
    
    /**
	 * 学生毕业设计成绩修改
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeupdate(String teacherid){
		
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum and gg.grade!=null"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//界面类
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//选课记录表编号
			gStudentInfor.setGrade(gg.getGrade());//成绩
			//System.out.println("毕名："+g.getGname());
			gStudentInfor.setGname(g.getClassName());//课程姓名
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("毕名："+g.getGname());
	//	System.out.println("学生姓名:"+s.getName());
	
		return gStudentInforList;
	};
    
	/**
	 * 显示已回复留言
	 */
	public List<ShowMessageVO> disAlreadyReplyMessage(String tidnum,String status){
		String sql = "from MessageVO where tidnum='"+tidnum+
		"' and status='"+status+"'";
		System.out.println("disAlreadyReplyMessage的sql:"+sql);
		List<MessageVO> list = this.getSession().createQuery(sql).list();
		
        // System.out.println(list.get(0).getTidnum().getIdnum());		
		return  VOconver.voConverdisAlreadyReplyMessage(list) ;
	};
	
	/**
	 * 得到指定编号的留言信息
	 */
	public MessageVO getMessageVO(String idnum){
		 List list = this.getSession().createQuery("from MessageVO where idnum='"+idnum+"'").list();
			
			if(list ==null || list.size() <= 0)
				return null;
			return (MessageVO)list.get(0);
		
	};	
	
	/**
	 * 得到毕业设计记录数
	 */
	public int getGrauationCounts(String teacherid) {
		// TODO Auto-generated method stub
		List list=this.getHibernateTemplate().find("from EMS_Graduation where flag='teacher' and teacherId='"+teacherid+"'");
		return list.size();
	}
	
	/*****查看所有毕业设计--分页*****/
	public List<EMS_Graduation> displayAlreadyAddPage(String idnum, int pageNow) {
		// TODO Auto-generated method stub
		Query query=this.getSession().createQuery("from EMS_Graduation where teacherId ='"+idnum +"' and flag='teacher'" );
		  
		   query.setMaxResults(6);
		   query.setFirstResult((pageNow-1)*6);
		List<EMS_Graduation> list=query.list();
		
		VOconver.voConverGa(list);
		
		return  list;
	}
	
	/**
	 * 得到选课学生信息记录数
	 */
	public int studentInforSearchCounts(String teacherid) {
		// TODO Auto-generated method stub
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum"; 
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	}
	
	/**
	 * 得到学生文件管理记录数
	 */
	public int studentFileManageCounts(String teacherid) {
		// TODO Auto-generated method stub
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum"; 
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	}
	/**
	 * 得到学生成绩查询记录数
	 * @return
	 */
		public int studentGradeSearchCounts(String teacherid){
			// TODO Auto-generated method stub
			String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
			"' and g.idnum=gg.gidnum and gg.grade!=null and gg.studentId=s.idnum"; 
			List list=this.getHibernateTemplate().find(sql);
			return list.size();
		};
		
		/**
		 * 查询选课学生信息成绩--分页

		 */
		public List<Object[]>  graStuGragradeCourse(String teacherid,int nowPage){
			String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
			"' and g.classId=gg.idnum and gg.grade!=null and gg.studentId=s.idnum"; 
		  	Query query = this.getSession().createQuery(sql);
			   query.setMaxResults(6);
			   query.setFirstResult((nowPage-1)*6);
			List<Object[]> list = query.list();
			
			return list;
		};
		
		/**
		 * 得到学生成输入记录数
		 * @return
		 */
			public int studentGradeInputCounts(String teacherid){
				// TODO Auto-generated method stub
				String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
				"' and g.idnum=gg.gidnum and gg.studentId=s.idnum and gg.grade=null"; 
				List list=this.getHibernateTemplate().find(sql);
				return list.size();
			};

			/**
			 * 得到学生成绩修改记录数
			 * @return
			 */
	public int studentGradeupdateCounts(String teacherid){
		// TODO Auto-generated method stub
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum and gg.grade!=null"; 
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	};
	/**
	 * 学生毕业设计成绩修改-分页
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeupdate(String teacherid,int nowPage){
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum and gg.grade!=null"; 

		Query query = this.getSession().createQuery(sql);
				   query.setMaxResults(6);
				   query.setFirstResult((nowPage-1)*6);

		List<Object[]> list = query.list();
		
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//界面类
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			EMS_GraduateGrade gg = (EMS_GraduateGrade)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//毕业设计记录表编号
			gStudentInfor.setGrade(gg.getGrade());//成绩
			System.out.println("毕名："+g.getGname());
			gStudentInfor.setGname(g.getGname());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("毕名："+g.getGname());
	//	System.out.println("学生姓名:"+s.getName());
	
		return gStudentInforList;
	};
	
	
	/**
	 * 显示已添加课程--分页
	 * @param teacherid
	 * @return
	 */
	public List<showAlreadyAddCourse> displayAlreadyAddCourse(String teacherid,int nowPage){
		 String sql = "from EMS_Class where teacherId='" + teacherid +"'";
		 Query query = this.getSession().createQuery(sql);
		   query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);
	      List<EMS_Class> list = query.list();
	      //在Dao层完成转换
			return VOconver.voConverdisAlreadyCourse(list);
	};
	/**
	 * 得到课程表记录数
	 * @return
	 */
		public int CourseCounts(String teacherid){
			// TODO Auto-generated method stub
			List list=this.getHibernateTemplate().find("from EMS_Class where teacherId='" + teacherid +"'");
			return list.size();
		};
		
		
		/**
		 * 得到选 课学生信息记录数
		 * @return
		 */
	public int studentInforSearchCourseCounts(String teacherid){
		// TODO Auto-generated method stub
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum"; 
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	};

		    
		    /**
			 * 查询选课学生信息--分页
			 * @return
			 */
	public List<GraduationStudentInfor> studentInforSearchCourse(String teacherid,int nowPage){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum"; 
		Query query = this.getSession().createQuery(sql);
		   query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);

		List<Object[]> list = query.list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//界面类
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			//gStudentInfor.setGgidnum(gg.getIdnum());//毕业设计记录表编号
			System.out.println("课程名称："+g.getClassName());
			gStudentInfor.setGname(g.getClassName());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("毕名："+g.getGname());
	//	System.out.println("学生姓名:"+s.getName());
	
		return gStudentInforList;
	};
	/**
	 * 得到选 课学生成绩记录数
	 * 
	 * @return
	 */
	public int studentGradeSearchCourseCounts(String teacherid){
		// TODO Auto-generated method stub
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.grade!=null and gg.studentId=s.idnum"; 
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	};
	
	
	/**
	 * 查询录入课程成绩--分页
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeInput(String teacherid,int nowPage){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum and gg.grade=null"; 
		Query query = this.getSession().createQuery(sql);
		   query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);
		List<Object[]> list = query.list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//界面类
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//选课记录表编号
		//	System.out.println("毕名："+g.getGname());
			gStudentInfor.setGname(g.getClassName());//课程名
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("毕名："+g.getGname());
	//	System.out.println("学生姓名:"+s.getName());
	
		return gStudentInforList;
	};

	/**
	 * 得到录入课程成绩记录数
	 * @return
	 */
	public int studentCourseGradeInputCounts(String teacherid){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum and gg.grade=null"; 
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	};	
		
	/**
	 * 查询修改课程成绩--分页
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeupdate(String teacherid,int nowPage){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum and gg.grade!=null"; 
		Query query = this.getSession().createQuery(sql);
		   query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);
		List<Object[]> list = query.list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//界面类
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//选课记录表编号
			gStudentInfor.setGrade(gg.getGrade());//成绩
			//System.out.println("毕名："+g.getGname());
			gStudentInfor.setGname(g.getClassName());//课程姓名
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("毕名："+g.getGname());
	//	System.out.println("学生姓名:"+s.getName());
	
		return gStudentInforList;
	};

	/**
	 * 得到修改课程成绩记录数
	 * @return
	 */
	public int studentCourseGradeupdateCounts(String teacherid){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum and gg.grade!=null"; 
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	};		
	
	
	/**
	 * 查看已申请状态--分页
	 * @return
	 */
	public List<ApplyModifyLogVO> displayAlreadyApplyStatus(String teacherid,int nowPage){
		String sql = "from ApplyModifyLogVO where applyUserId='"+teacherid+"'";
		System.out.println("IdnumSql:"+sql);
		Query query = this.getSession().createQuery(sql);
		   query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);
		List<ApplyModifyLogVO> list = query.list();
		
		return list;
	};

	/**
	 * 得到查看已申请状态记录数
	 * @return
	 */
	public int displayAlreadyApplyStatusCounts(String teacherid){
		String sql = "from ApplyModifyLogVO where applyUserId='"+teacherid+"'";
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	};
	
	
	/**
	 * 查看已回复留言--分页
	 * @return
	 */
	public List<ShowMessageVO> disAlreadyReplyMessage(String teacherid,String status,int nowPage){
		String sql = "from MessageVO where tidnum='"+teacherid+
		"' and status='"+status+"'";
		System.out.println("disAlreadyReplyMessage的sql:"+sql);
		

	  	Query query = this.getSession().createQuery(sql);
	   query.setMaxResults(2);
	   query.setFirstResult((nowPage-1)*2);	   

		List<MessageVO> list = query.list();		
        // System.out.println(list.get(0).getTidnum().getIdnum());		
		return  VOconver.voConverdisAlreadyReplyMessage(list) ;
		
	};

	/**
	 * 得到已回复留言记录数
	 * @return
	 */
	public int disAlreadyReplyMessageCounts(String teacherid,String status){
		String sql = "from MessageVO where tidnum='"+teacherid+
		"' and status='"+status+"'";
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	};

	/**
	 * 系统时间设置
	 * @param status
	 * @return
	 */
	public boolean checkTime(String status){
		//return this.teacherDao.checkTime(status);
		String sql = "from EMS_Systemset where syssetname='"+status+"'";
		EMS_Systemset nowDate = (EMS_Systemset)this.getHibernateTemplate().find(sql).get(0);
		Timestamp nowday = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		Timestamp startTime = nowDate.getTimestart();
		Timestamp endTime = nowDate.getTimeend();
		if(nowday.compareTo(startTime) >= 0 && nowday.compareTo(endTime) <= 0){
			return true;
		}
		return false;
	};
		
		
	/**
	 * 帐号测试
	 * @return
	 */
	public boolean checkGname(String account){
		// TODO Auto-generated method stub
		HibernateTemplate hibernate = this.getHibernateTemplate();	
		List list = hibernate.find("from EMS_Graduation where gname='" + account  + "'" );
		if( null == list || list.size()<= 0 ){
			return false;
		}else{ 
			return true;
		}
	};
	
}
