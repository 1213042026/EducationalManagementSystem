package cn.edu.management.dao.impl;

import java.util.Collections;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.edu.management.comm.ClassTypeVOComparator;
import cn.edu.management.comm.ManagerComparator;
import cn.edu.management.comm.ProfessionVOComparator;
import cn.edu.management.comm.StudentComparator;
import cn.edu.management.comm.TeacherComparator;
import cn.edu.management.comm.VOconver;
import cn.edu.management.comm.WorkerTypeVOComparator;
import cn.edu.management.dao.ManagerDAO;
import cn.edu.management.dao.SuperDAO;
import cn.edu.management.vo.showVO.ShowMessageVO;
import cn.edu.management.vo.showVO.StudentVO;
import cn.edu.management.vo.voImpl.ApplyModifyLogVO;
import cn.edu.management.vo.voImpl.ClassTypeVO;
import cn.edu.management.vo.voImpl.EMS_Systemset;
import cn.edu.management.vo.voImpl.Ems_Manager_VO;
import cn.edu.management.vo.voImpl.Ems_Student_VO;
import cn.edu.management.vo.voImpl.Ems_Teacher_VO;
import cn.edu.management.vo.voImpl.ProfessionVO;
import cn.edu.management.vo.voImpl.UserVO;
import cn.edu.management.vo.voImpl.WorkerTypeVO;

@Component("managerDao")
public class ManagerDAOImpl extends SuperDAO implements ManagerDAO {
	/**
	 * add user 
	 * @param vo
	 * @return
	 */
	public boolean add( UserVO vo ){
		
		
		return true;
	}
	/**
	 * 帐号测试
	 * @return
	 */
	public boolean checkAccountIsExist(String account){
		// TODO Auto-generated method stub
		HibernateTemplate hibernate = this.getHibernateTemplate();	
		List list = hibernate.find("from Ems_Student_VO where iDnum='" + account  + "'" );
		if( null == list || list.size()<= 0 ){
			return false;
		}else{ 
			return true;
		}
		
	};
	
	/**
	 * 测试验证管理员编号是否合法
	 * @return
	 */
	public boolean checkManager(String account){
		// TODO Auto-generated method stub
		HibernateTemplate hibernate = this.getHibernateTemplate();	
		List list = hibernate.find("from Ems_Manager_VO where idnum='" + account  + "'" );
		if( null == list || list.size()<= 0 ){
			return false;
		}else{ 
			return true;
		}
	};
	
	/**
	 * 遍历所有的管理员
	 * @return
	 */
	public List<Ems_Manager_VO> listManager(){
		
		List<Ems_Manager_VO> list = this.getHibernateTemplate().loadAll( Ems_Manager_VO.class );
		
		////////////////////////////////////////////////////////////////////////
		//排序
		 Collections.sort(list,new ManagerComparator());
		return list;
	}
	
	/**
	 * 取到所有管理员编号中最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutManager(){
		//在hibernate2X中是使用find方法执行HQL,而在hibernate3.x中是使用
		//org.hibernate.Query接口来执行HQL的.
		//方式一:通过得到HibernateTemplate,后面不用使用list方法返回list
		List list = this.getHibernateTemplate().find( "select max(idnum) from Ems_Manager_VO t  where idnum!='system' " );
		 if(list == null || list.size() <= 0)		
			 return null;
		return (String) list.get(0);
	}
	
	/**
	 * 取出这个需要修改的管理员的信息
	 * @return
	 */
	public Ems_Manager_VO getModifyManagerInfo( String idnum  ){
		HibernateTemplate hibernate = this.getHibernateTemplate();
		Ems_Manager_VO vo = new Ems_Manager_VO();
		vo.setIdnum(idnum);
		//方式二:通过得到Session,再得到Query执行HQL，不要忘记最后还要使用list方法返回list
		List list =  this.getSession().createQuery("from Ems_Manager_VO where idnum='"+idnum +"'").list();
		 if(list == null || list.size() <= 0)		
			 return null;
		return (Ems_Manager_VO) list.get(0);
	}

	
	/*******************************教师***********************************************/
	/**
	 * 遍历所有的教师
	 * @return
	 */
	public List<Ems_Teacher_VO> listTeacher() {
		List<Ems_Teacher_VO> list = this.getHibernateTemplate().loadAll( Ems_Teacher_VO.class );
		VOconver.voConverTeacher(list);
        ////////////////////////////////////////////////////////////////////////
		//排序
		 Collections.sort(list,new TeacherComparator());
		
//		Ems_Teacher_VO test = list.get(0);
//		String str = test.getiDnum();
//		System.out.println("输出："+str);经测试可以取得编号
		return list;
		
	}

	/**
	 * 取到所有教师编号中最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutTeacher(){
		
		List list = this.getHibernateTemplate().find( "select max(idnum) from Ems_Teacher_VO t " );
		 if(list == null || list.size() <= 0)		
			 return null;
		return (String) list.get(0);
	}
	
	
	/**
	 * 取出这个需要修改的教师的信息
	 * @return
	 */
	public Ems_Teacher_VO getModifyTeacherInfo( String idnum  ){
		HibernateTemplate hibernate = this.getHibernateTemplate();
		Ems_Teacher_VO vo = new Ems_Teacher_VO();
		vo.setIdnum(idnum);
		List list =  this.getSession().createQuery("from Ems_Teacher_VO where idnum='"+idnum +"'").list();
		 if(list == null || list.size() <= 0)		
			 return null;
		return (Ems_Teacher_VO) list.get(0);
	}

	
	
	/*******************************学生***********************************************/
	

	
	
	/**
	 * 遍历所有的学生
	 * @return
	 */
	public List<StudentVO> listStudent() {
		List<Ems_Student_VO> sourelist = this.getHibernateTemplate().loadAll( Ems_Student_VO.class );
		
		List<StudentVO> destlist = VOconver.voConver(sourelist);
		////////////////////////////////////////////////////////////////////////
		//排序
		 Collections.sort(destlist,new StudentComparator());
//		Ems_Teacher_VO test = list.get(0);
//		String str = test.getiDnum();
//		System.out.println("输出："+str);经测试可以取得编号
		return destlist;
		
	}

	/**
	 * 通过专业名称得到专业编号
	 * @return
	 */
//	public String getProfession(String pro_name){
//		//SQL语句不能执行
//		String sql = "select idnum from ProfessionVO t where pro_name= '" +pro_name +
//		"'";
//		List list = this.getHibernateTemplate().find(sql);
//		System.out.println("Dao层:" +(String) list.get(0));
//		return (String) list.get(0);
//		
//	
//	};
	
	/**
	 * 取出这个需要修改的学生的的信息
	 * @return
	 */
	public Ems_Student_VO getModifyStudentInfo( String idnum  ){
		HibernateTemplate hibernate = this.getHibernateTemplate();
		Ems_Student_VO vo = new Ems_Student_VO();
		vo.setIdnum(idnum);
		List list =  this.getSession().createQuery("from Ems_Student_VO where idnum='"+idnum +"'").list();
		 if(list == null || list.size() <= 0)		
			 return null;
		return (Ems_Student_VO) list.get(0);
	};
	
	
	
	
/*****************************系统设置************************************/
	
	/***************作品类型********************/
	/**
	 * 遍历所有的作品类型
	 * @return
	 */
	public List<WorkerTypeVO> listWorkertype(){
		List<WorkerTypeVO> list = this.getHibernateTemplate().loadAll( WorkerTypeVO.class );
		////////////////////////////////////////////////////////////////////////
		//排序
		 Collections.sort(list,new WorkerTypeVOComparator());
//		System.out.println("长度："+ list.size());
//		ProfessionVO test = list.get(0);
//		String str = test.getIdnum();
//		System.out.println("Dao专业编号："+str);//经测试可以取得编号
		return list;
	};
	/**
	 * 取到所有教师编号中最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutWorkstype(){
		//SQL语句不能执行
		List list = this.getHibernateTemplate().find( "select max(idnum) from WorkerTypeVO t " );
		System.out.println("Dao最大编号:" +(String) list.get(0));
		 if(list == null || list.size() <= 0)		
			 return null;
		return (String) list.get(0);
	};
	
	
	/***************课程类型********************/
	/**
	 * 遍历所有的课程类型
	 * @return
	 */	
	public List<ClassTypeVO> listClasstype(){
		List<ClassTypeVO> list = this.getHibernateTemplate().loadAll( ClassTypeVO.class );
////    ////////////////////////////////////////////////////////////////////
		//排序
		 Collections.sort(list,new ClassTypeVOComparator());
//		System.out.println("长度："+ list.size());
//		ProfessionVO test = list.get(0);
//		String str = test.getIdnum();
//		System.out.println("Dao专业编号："+str);//经测试可以取得编号
		return list;
	};
	
	/**
	 * 取到所有教师编号中最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutClasstype(){
		//SQL语句不能执行
		
		List list = this.getHibernateTemplate().find( "select max(idnum) from ClassTypeVO t" );
		 if(list == null || list.size() <= 0)		
			 return null;
		System.out.println("Dao最大编号:" +(String) list.get(0));
		
		return (String) list.get(0);
	};
	
	
	
	/***************专业管理********************/
	/**
	 * 遍历所有的专业
	 * @return
	 */
	public List<ProfessionVO> listProfession(){
		List<ProfessionVO> list = this.getHibernateTemplate().loadAll( ProfessionVO.class );
		 ////////////////////////////////////////////////////////////////////
		//排序
		 Collections.sort(list,new ProfessionVOComparator());
//		System.out.println("长度："+ list.size());
//		ProfessionVO test = list.get(0);
//		String str = test.getIdnum();
//		System.out.println("Dao专业编号："+str);//经测试可以取得编号
		return list;
	}
	/**
	 * 取到所有专业编号中最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutProfession(){
		//SQL语句不能执行
		List list = this.getHibernateTemplate().find( "select max(idnum) from ProfessionVO t " );
		 if(list == null || list.size() <= 0)		
			 return null;
		System.out.println("Dao最大编号:" +(String) list.get(0));
		return (String) list.get(0);
	};
	
	
	/**************老师添加课程时间设置************************************************************************************************/
	/**
	 * 列取数据库中起止时间信息
	 * @return
	 */
	public EMS_Systemset disTeacherAddTime(String idnum){
		HibernateTemplate hibernate = this.getHibernateTemplate();	
		//默认显示的是0001的时间，也就是老师添加课程时间设置
		List list =  this.getSession().createQuery("from EMS_Systemset where idnum='"+idnum +"'").list();
		 if(list == null || list.size() <= 0)		
			 return null;
		return (EMS_Systemset) list.get(0);
	};			

	
	
	/**************老师录入成绩时间设置*****************/
	/**
	 * 
	 * 已废弃
	 * 
	 * 列取数据库中起止时间信息
	 * @return
	 */
	public EMS_Systemset disTeacherInputGrade(){
		HibernateTemplate hibernate = this.getHibernateTemplate();	
		//02这是设定好的标准
		List list =  this.getSession().createQuery("from EMS_Systemset where idnum='"+"02" +"'").list();
		 if(list == null || list.size() <= 0)		
			 return null;
		return (EMS_Systemset) list.get(0);
		
	};	
		
	
	
	/**************学生选课时间设置*****************/
	/**
	 * 
	 * 已废弃
	 * 
	 * 列取数据库中起止时间信息
	 * @return
	 */
	public EMS_Systemset disStudentTakeCourse(){
		HibernateTemplate hibernate = this.getHibernateTemplate();	
		//03这是设定好的标准
		List list =  this.getSession().createQuery("from EMS_Systemset where idnum='"+"03" +"'").list();
		
		

		
		return (EMS_Systemset) list.get(0);
		
		
		
	};	
	
	
	
	
	/******************************审核请求管理**********************************************/
	/*******查看信息***********/
	public List<ApplyModifyLogVO> disAditInfor(String state,String table){
		String sql = "from ApplyModifyLogVO where fortable='"+table+
		"' and modifyResult='"+state+"'";
		System.out.println("disAditInfor的sql:"+sql);
		List<ApplyModifyLogVO> list = this.getSession().createQuery(sql).list();
//			this.getHibernateTemplate().find(sql); 
			//
//		List a = this.getSession().createQuery(sql).list();
		// VOconver.voConverGb(list);
		return  list ;
	};
	

	
	/***********得到相应编号的申请表信息记录*********/
	public ApplyModifyLogVO getAuditInfor(String applyid){
	//	HibernateTemplate hibernate = this.getHibernateTemplate();
		List list = this.getSession().createQuery("from ApplyModifyLogVO where idnum='"+applyid+"'").list();
		 if(list == null || list.size() <= 0)		
			 return null;
		return (ApplyModifyLogVO)list.get(0);
	
	};
	
	
	/**
	 * 查看教师信息--分页
	 * @return
	 */
	public List<Ems_Teacher_VO> listTeacher(int nowPage){
		String sql = "from Ems_Teacher_VO";
	  	Query query = this.getSession().createQuery(sql);
		   query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);
		List<Ems_Teacher_VO> list = query.list(); 
			//this.getHibernateTemplate().loadAll( Ems_Teacher_VO.class );
		VOconver.voConverTeacher(list);
        ////////////////////////////////////////////////////////////////////////
		//排序
		 Collections.sort(list,new TeacherComparator());
		
//		Ems_Teacher_VO test = list.get(0);
//		String str = test.getiDnum();
//		System.out.println("输出："+str);经测试可以取得编号
		return list;
	};

	/**
	 * 得到教师信息记录数
	 * @return
	 */
	public int getTeacherCounts(){
		String sql = "from Ems_Teacher_VO";
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
		
	};
	
	/**
	 * 查看学生信息--分页
	 * @return
	 */
	public List<StudentVO> listStudent(int nowPage){
		String sql = "from Ems_Student_VO";
	  	Query query = this.getSession().createQuery(sql);
		   query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);
List<Ems_Student_VO> sourelist = query.list();
		
		List<StudentVO> destlist = VOconver.voConver(sourelist);
		////////////////////////////////////////////////////////////////////////
		//排序
		 Collections.sort(destlist,new StudentComparator());
//		Ems_Teacher_VO test = list.get(0);
//		String str = test.getiDnum();
//		System.out.println("输出："+str);经测试可以取得编号
		return destlist;
	};

	/**
	 * 得到学生信息记录数
	 * @return
	 */
	public int listStudentCounts(){
		String sql = "from Ems_Student_VO";
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	};
}


