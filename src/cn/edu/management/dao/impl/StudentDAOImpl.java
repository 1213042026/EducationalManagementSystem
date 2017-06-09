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
import cn.edu.management.dao.StudentDAO;
import cn.edu.management.dao.SuperDAO;
import cn.edu.management.vo.voImpl.ApplyModifyLogVO;
import cn.edu.management.vo.voImpl.EMS_Class;
import cn.edu.management.vo.voImpl.EMS_CourseRecord;
import cn.edu.management.vo.voImpl.EMS_GraduateGrade;
import cn.edu.management.vo.voImpl.EMS_Graduation;
import cn.edu.management.vo.voImpl.EMS_Systemset;
import cn.edu.management.vo.voImpl.Ems_Student_VO;
import cn.edu.management.vo.voImpl.Ems_Teacher_VO;
import cn.edu.management.vo.voImpl.MessageVO;
import cn.edu.management.vo.voImpl.ProfessionVO;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

@Component("studentDao")
public class StudentDAOImpl extends SuperDAO implements StudentDAO {

	/****************************************** 个人信息维护 ***********************************************/
	/***** 查看个人信息 *****/
	public Ems_Student_VO displayPersonInformation(String idnum) {

		HibernateTemplate hibernate = this.getHibernateTemplate();
//		Ems_Student_VO vo = new Ems_Student_VO();
//		vo.setIdnum(idnum);
		System.out.println("Dao:"+idnum);
		List list =  this.getSession().createQuery("from Ems_Student_VO where idnum='"+idnum +"'").list();
		VOconver.voConver(list);
		if(list.size()==0)return null;
		return (Ems_Student_VO) list.get(0);
	}
	

	/****************************************** 信息查询 ***********************************************/
	/***** 在校成绩查询 *****/
	public String findScoreByCourserecord(String classId,Ems_Student_VO vo) {
		// TODO Auto-generated method stub
		EMS_CourseRecord list=(EMS_CourseRecord) this.getSession().createQuery("from EMS_CourseRecord where classId='"+classId+"' and studentId='"+vo.getIdnum()+"'").list().get(0);
		String score=list.getGrade();
		if(score!=null)
		     return score;
		else return null;
	}
	
	/**所有选课情况查询**/
	public List<EMS_Class> listMyAllCourse(String idnum, String grade,
			String profession,int pageNow) {
		// TODO Auto-generated method stub
		
		//从课程表中选择学生选的课程（课程号在选课记录表中筛选出来）：查询条件是学生所选的课是在选课时间（老师添加课程的月份）之内的
		   Query query=this.getSession().createQuery("from EMS_Class cc where cc.classId in ( select c.classId from EMS_CourseRecord c where (c.studentId='"+idnum+"'))");
		  
		   query.setMaxResults(10);
		   query.setFirstResult((pageNow-1)*10);
		   List<EMS_Class> classList=query.list();
		    System.out.println(classList.get(0).getRecordTime());
		   VOconver.voConverClass(classList);
		 
		   return classList;
	}
	
	public List<EMS_Class> listMyAllCourse1(String idnum, String grade,
			String profession,int pageNow) {
		// TODO Auto-generated method stub
		
		//从课程表中选择学生选的课程（课程号在选课记录表中筛选出来）：查询条件是学生所选的课是在选课时间（老师添加课程的月份）之内的
		   Query query=this.getSession().createQuery("from EMS_Class cc where cc.classId in ( select c.classId from EMS_CourseRecord c where (c.studentId='"+idnum+"') and c.grade is not null)");
		  
		   query.setMaxResults(10);
		   query.setFirstResult((pageNow-1)*10);
		   List<EMS_Class> classList=query.list();
		    System.out.println(classList.get(0).getRecordTime());
		   VOconver.voConverClass(classList);
		 
		   return classList;
	}
	
	/**
	 * 得到某门课程的选课时间
	 */
	public String selectCourseTime(EMS_Class classVo,Ems_Student_VO vo)
	{
		String Xgrade,t;
		String cid=classVo.getClassId();
		EMS_CourseRecord temp=null;

		temp=(EMS_CourseRecord)this.getSession().createQuery("from EMS_CourseRecord c where studentId='"+vo.getIdnum()+"' and classId='"+cid+"'").list().get(0);
		t=DateConventer.timestampToStrNo(temp.getChooseTime());
		Xgrade=t.substring(0,t.length()-3);
		System.out.println("选课时间："+Xgrade);
		System.out.println("year:"+Xgrade.substring(0,4));

		return Xgrade;
	}
	
	
	/******************************************网上选课***********************************************/

	/**
	 * 判断现在是否是学生选课时间
	 */
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		Date date=new Date();
		System.out.println("Date Now:"+date);
//String year=date.substring(0,4);
		EMS_Systemset selectTime=(EMS_Systemset) this.getSession().createQuery("from EMS_Systemset where syssetname='学生选课时间设置'").list().get(0);
		String start=DateConventer.timestampToStr(selectTime.getTimestart());
		Date startTime=DateConventer.strToDate(start);
		String end=DateConventer.timestampToStr(selectTime.getTimeend());
		Date endTime=DateConventer.strToDate(end);
		
		if(date.after(startTime)&&date.before(endTime))
			return true;
		else
			return false;
	}

	
	/*****选修课程*****/
	/**
	 * 查看可选修的课程
	 * @param idnum
	 * @param grade
	 * @param profession 
	 * @return
	 */
	public List<EMS_Class> listCourse(String idnum, String grade,String profession) {
		// TODO Auto-generated method stub

		String date=DateConventer.dateToStr(new Date());
		System.out.println("Dao-date:"+date);
		String year=date.substring(0,4);
		System.out.println("Dao-year:"+year);
		String month=date.substring(5,7);
		System.out.println("Dao-month:"+month);
		String term;
		if(Integer.valueOf(month)>=1&&Integer.valueOf(month)<=6)
			term="2";
		else term="1";
		List<EMS_Class> classList=null;
		
		String sql=" from EMS_Class c where (c.classType  in('0001','0004') or c.professionId='"+profession+"') and c.grade='"+grade+"'  and to_number(c.sCount)>0 and  c.term='"+term+"' and to_char(c.recordTime,'YYYY')='"+year+"' and classId not in(select cc.classId  from EMS_CourseRecord cc where  cc.studentId='"+idnum+"'" ;
		System.out.println(sql);
		
		//列出公共必修课和任意选修课，专业必修课，该年级，学期，剩余人数>0，该课程录入时间是在同一年，并且该学生还没有选的课程，选了的不列出来(，以及往年修过的课程也不列出来（通过成绩如果不为空来判断）   不需要，通过前面的录入时间已经筛选了)
		// classList=(List<EMS_Class>)this.getSession().createQuery(" from EMS_Class c where (c.classType  in('0001','0004') or c.professionId='"+profession+"') and c.grade='"+grade+"'  and to_number(c.sCount)>0 and  c.term='"+term+"' and to_char(c.recordTime,'YYYY')='"+year+"' and classId not in(select cc.classId  from EMS_CourseRecord cc where to_char(cc.chooseTime,'YYYY')='"+year+"' and cc.studentId='"+idnum+"'  and cc.grade is not null)" ).list();
		   List list=this.getSession().createQuery(" from EMS_Class c where (c.classType  in('0001','0004') or c.professionId='"+profession+"') and c.grade='"+grade+"'  and to_number(c.sCount)>0 and  c.term='"+term+"' and to_char(c.recordTime,'YYYY')='"+year+"' and classId not in(select cc.classId  from EMS_CourseRecord cc where cc.studentId='"+idnum+"' )" ).list();
		   if(list.size()==0)
			   return null;
		   classList=(List<EMS_Class>)list;
		   VOconver.voConverClass(classList);
		 
		return classList;
	}

	/**
	 * 得到课程记录表的最大ID
	 * @return
	 */
	public String getMaxIdnumAboutCourseRecord() {
		// TODO Auto-generated method stub
		List list=this.getHibernateTemplate().find("select max(idnum) from EMS_CourseRecord");
		if(list==null || list.size()==0)
			return null;
		return (String) list.get(0);
	}

	/**
	 * 查看我这学期已经选修的课程
	 * @return
	 */
	public List<EMS_Class> listMyCourse(String idnum, String grade,String profession) {
		// TODO Auto-generated method stub
		
		EMS_Systemset sys=(EMS_Systemset) this.getSession().createQuery("from EMS_Systemset where syssetname='老师添加课程时间设置'").list().get(0);
		Timestamp timestart=sys.getTimestart();
		Timestamp timeEnd=sys.getTimeend();
		
		System.out.println(timestart.toString().substring(0,timestart.toString().length()-2 ));
		String date=DateConventer.dateToStr(new Date());
		System.out.println("Dao-date:"+date);
		String year=date.substring(0,4);
		System.out.println("Dao-year:"+year);
		String month=date.substring(5,7);
		System.out.println("Dao-month:"+month);
		
		List<EMS_Class> classList=null;
		
		//从课程表中选择学生选的课程（课程号在选课记录表中筛选出来）：查询条件是学生所选的课是在选课时间（老师添加课程的月份）之内的
		 classList=(List<EMS_Class>)this.getSession().createQuery("from EMS_Class cc where cc.classId in ( select c.classId from EMS_CourseRecord c where (c.studentId='"+idnum+"'  and  to_char(c.chooseTime,'yyyy-mm-dd HH:mm:ss') between  '"+timestart.toString().substring(0,timestart.toString().length()-2 )+"'  and  '"+timeEnd.toString().substring(0,timeEnd.toString().length()-2 ) +"' ))").list();
		
		 VOconver.voConverClass(classList);
		 
		return classList;
	}
	
	/**
	 * 通过课程号查找这条课程记录
	 * @param classId
	 * @return
	 */
	public EMS_Class findClassById(String classId) {
		// TODO Auto-generated method stub
		List list=this.getSession().createQuery("from EMS_Class where classId='"+classId+"'").list();
		if(list.size()!=0)
		{
			return (EMS_Class) list.get(0);
		}
		else return null;
		
	}

	
	/**
	 * 选择学生的一条选课记录
	 * @param classId
	 * @param studentId
	 * @return
	 */
	public EMS_CourseRecord findCRecord(String classId, String studentId) {
		// TODO Auto-generated method stub
		List list=this.getSession().createQuery("from EMS_CourseRecord where classId='"+classId+"' and studentId='"+studentId+"'").list();
		if(list.size()!=0)
		{
			return (EMS_CourseRecord) list.get(0);
		}
		else return null;
	}
	
	
	
	
	/******************************************毕业设计管理***********************************************/
	/*****查看所有毕业设计*****/
	public List<EMS_Graduation> selectGraduationProject(String idnum, int pageNow) {
		// TODO Auto-generated method stub
		Query query=this.getSession().createQuery("from EMS_Graduation g where g.flag='teacher'");
		  
		   query.setMaxResults(10);
		   query.setFirstResult((pageNow-1)*10);
		List<EMS_Graduation> list=query.list();
		
	     VOconver.voConverGaS(list);
		
		return  list;
	}
	/**
	 * 拿到毕业设计表中所有的老师
	 * @return
	 */
	public List<Ems_Teacher_VO> getGraduateTeacher()
	{
		List<Ems_Teacher_VO> list = new ArrayList<Ems_Teacher_VO>();
	    List tList = this.getSession().createQuery("select distinct t.idnum,t.name   from EMS_Graduation g,Ems_Teacher_VO t where g.teacherId.idnum=t.idnum").list();
		
		for (int i = 0; i < tList.size(); i++) {
			Object[] o = (Object[]) tList.get(i);
			Ems_Teacher_VO temp = new Ems_Teacher_VO();
			
			temp.setIdnum(o[0].toString());
			temp.setName(o[1].toString());
			list.add(temp);
		}
		
		return list;
	}
	
//	/**
//	 * 拿到毕业设计表中所有的专业
//	 * @return
//	 */
//	public List<ProfessionVO> gerProfession() {
//		// TODO Auto-generated method stub
//		List<ProfessionVO> list=new ArrayList<ProfessionVO>();
//		List pList=this.getSession().createQuery("select distinct p.idnum,p.pro_name from EMS_Graduation g,ProfessionVO p where g.professionId.idnum=p.idnum").list();
//		
//		for (int i = 0; i < pList.size(); i++) {
//			Object[] o = (Object[]) pList.get(i);
//			ProfessionVO temp = new ProfessionVO();
//			
//			temp.setIdnum(o[0].toString());
//			temp.setPro_name(o[1].toString());
//			list.add(temp);
//		}
//		
//		return list;
//	}

	/**
	 * 通过老师名字，难易程度检索毕业设计
	 * @param teacherIdnum
	 * @param glevel
	 * @return
	 */
	public List<EMS_Graduation> indexGraduationProject(String teacherIdnum,String glevel,int pageNow) {
		// TODO Auto-generated method stub
		
		String hql="from EMS_Graduation g where 1=1";
		
		if(!teacherIdnum.equals("")&&teacherIdnum!=null){
			hql+=" and g.teacherId.idnum='"+teacherIdnum +"'";
		}
		if(!glevel.equals("")&&glevel!=null){
			hql+=" and  g.glevel ='" +glevel +"'";
		}
		
		Query query=this.getSession().createQuery(hql+"and g.flag='teacher'");
		  
		   query.setMaxResults(10);
		   query.setFirstResult((pageNow-1)*10);
		   List<EMS_Graduation> list=query.list();
		
	     VOconver.voConverGaS(list);
		
		return  list;
	}


	/**
	 * 用于添加毕业设计记录表的时候取到数据库记录中的最大ID，管理员不能进行手动插入ID号
	 * @return
	 */
	public String getMaxIdNumAboutGraduateGrade() {
		// TODO Auto-generated method stub
		
		List list=this.getHibernateTemplate().find("select max(idnum) from EMS_GraduateGrade");
		return (String) list.get(0);
	}

	/**
	 * 判断该学生是否已经选择了毕业设计
	 * @param studentId
	 * @return
	 */
	public boolean findStudentIdFromGraduateGrade(String studentId) {
		// TODO Auto-generated method stub
		List list=this.getSession().createQuery("from EMS_GraduateGrade where studentId.idnum='"+studentId+"'").list();
		if(list.size()!=0) 
			return true;
		else return false;
	}

	/**
	 * 查看我的毕业设计
	 * @param studentId
	 * @return
	 */
	public EMS_GraduateGrade listMyGraduationProject(String studentId) {
		// TODO Auto-generated method stub
		System.out.println("Dao:"+studentId);
		List list=this.getSession().createQuery("from EMS_GraduateGrade where studentId.idnum='"+studentId+"'").list();
		if(list.size()!=0)
		{
			VOconver.voConverMyProject((EMS_GraduateGrade) list.get(0));
			return (EMS_GraduateGrade) list.get(0);
		}
		else return null;
	}

	/**
	 * 通过毕业设计编号读取该条记录
	 * @param gidnum
	 * @return
	 */
	public EMS_Graduation findGraduationById(String gidnum) {
		// TODO Auto-generated method stub
		List list=this.getSession().createQuery("from EMS_Graduation where idnum='"+gidnum+"'").list();
		return (EMS_Graduation) list.get(0);
	}

	/**
	 * 通过专业号查找这条专业记录
	 * @param proName
	 * @return
	 */
	public ProfessionVO findProfessionByProName(String proName) {
		// TODO Auto-generated method stub
		List list=this.getSession().createQuery("from ProfessionVO where idnum='"+proName+"'").list();
		return (ProfessionVO) list.get(0);
	}

	/**
	 * 通过教师编号查找这条教师记录
	 * @param tName
	 * @return
	 */
//	public Ems_Teacher_VO findTeacherById(String tName) {
//		// TODO Auto-generated method stub
//		List list=this.getSession().createQuery("from Ems_Teacher_VO where idnum='"+tName+"'").list();
//		return (Ems_Teacher_VO) list.get(0);
//	}
	
	/**
	 * 查看是否已经申请自拟题目的毕业设计，如果是则不能再选择毕业设计
	 * @param userId
	 * @return
	 */
	public String isApply(String userId) {
		// TODO Auto-generated method stub
		String sql = "from ApplyModifyLogVO ap where ap.fortable='ems_graduation' and ap.modifyResult='审核中' and ap.remark='"+userId+"'";
		List list=this.getSession().createQuery(sql).list();
		if(list.size()!=0)
			 return "true";
		else return null;
	}
	
	/**
	 * 查看学生所选毕业设计的导师
	 * @return
	 */
	public Ems_Teacher_VO findTName(EMS_GraduateGrade gvo) {
		// TODO Auto-generated method stub
		EMS_Graduation list=(EMS_Graduation)this.getSession().createQuery("from EMS_Graduation where idnum='"+gvo.getGidnum().getIdnum()+"'").list().get(0);
		Ems_Teacher_VO tvo=(Ems_Teacher_VO)this.getSession().createQuery("from Ems_Teacher_VO where idnum='"+list.getTeacherId().getIdnum()+"'").list().get(0);
		return tvo;
	}

/********************************************留言板管理************************************************/
	/**
	 * 用于添加留言表的时候取到数据库记录中的最大ID
	 * @return
	 */
	public String getMaxIdNumAboutMessage() {
		// TODO Auto-generated method stub
		List list=this.getHibernateTemplate().find("select max(idnum) from MessageVO");
		return (String) list.get(0);
	}
	
	/**
	 * 显示学生留言信息
	 * @param idnum
	 * @return
	 */
	public List<MessageVO> showMessage(String idnum, String status) {
		// TODO Auto-generated method stub
		if(status=="已回复")
		{
			List<MessageVO> list=this.getSession().createQuery("from MessageVO where sidnum='"+idnum+"' and status='已回复'").list();
			VOconver.voConverdisAlreadyReplyMessage(list);
			return list;
		}
		else if(status=="看留言")
		{
			List<MessageVO> list=this.getSession().createQuery("from MessageVO where idnum='"+idnum+"'").list();
			VOconver.voConverdisAlreadyReplyMessage(list);
			return list;
		}
		else
		{
			List<MessageVO> list=this.getSession().createQuery("from MessageVO where sidnum='"+idnum+"'").list();
			VOconver.voConverdisAlreadyReplyMessage(list);
			return list;
		}
		
	}
/********************************************个人信息申请修改************************************************/
	/**
	 * 查看申请信息
	 * @return
	 */
	public List<ApplyModifyLogVO> displayAlreadyApplyStatus(String idnum) {
		// TODO Auto-generated method stub
		List<ApplyModifyLogVO> list=this.getSession().createQuery( "from ApplyModifyLogVO where applyUserId='"+idnum+"'").list();
		return list;
	}

	/**
	 * 查看申请信息
	 * @return
	 */
	public List<ApplyModifyLogVO> displayAlreadyApplyStatusSelf(String idnum) {
		// TODO Auto-generated method stub
		List<ApplyModifyLogVO> list=this.getSession().createQuery( "from ApplyModifyLogVO where remark='"+idnum+"'").list();
		return list;
	}
	
	/**
	 * 检查是否有请求的状态时“审核中”
	 * @param idnum
	 * @param string 
	 * @return
	 */
	public boolean checkApply(String idnum,String string) {
		// TODO Auto-generated method stub
		List<ApplyModifyLogVO> list =this.getSession().createQuery("from ApplyModifyLogVO where remark='"+idnum+"' and modifyResult='审核中'"+" and fortable='"+string+"'").list();
		if(list.size()!=0)
			return true;
		return false;
	}

	//判断现在是否是选择毕业设计时间
	public boolean isGraAvailable() {
		// TODO Auto-generated method stub
		Date date=new Date();
		System.out.println("Date Now:"+date);
//String year=date.substring(0,4);
		EMS_Systemset selectTime=(EMS_Systemset) this.getSession().createQuery("from EMS_Systemset where syssetname='学生毕业设计选题时间设置'").list().get(0);
		String start=DateConventer.timestampToStr(selectTime.getTimestart());
		Date startTime=DateConventer.strToDate(start);
		String end=DateConventer.timestampToStr(selectTime.getTimeend());
		Date endTime=DateConventer.strToDate(end);
		
		if(date.after(startTime)&&date.before(endTime))
			return true;
		else
			return false;
	}

	/**
	 * 判断是否已经添加了自拟选题
	 * @return
	 */
	public boolean isApplying(String remark) {
		// TODO Auto-generated method stub
		List<ApplyModifyLogVO> list =this.getSession().createQuery("from ApplyModifyLogVO where remark='"+remark+"' and modifyResult='未审核'"+" and fortable='ems_graduation'").list();
		return false;
	}

	/**
	 * 得到毕业设计记录数
	 */
	public int getGrauationCounts() {
		// TODO Auto-generated method stub
		List list=this.getHibernateTemplate().find("from EMS_Graduation where flag='teacher'");
		return list.size();
	}

	/**
	 * 通过学年和学期查询课程
	 * @param xnName
	 * @param term
	 * @return
	 */
	public List<EMS_Class> indexCourse(String xnName, String term,String idnum) {
		// TODO Auto-generated method stub
		List<EMS_Class> classList=null;
		
		String hql="from EMS_Class cc where cc.classId in ( select c.classId from EMS_CourseRecord c where (c.studentId='"+idnum+"')) ";
		
		if(xnName.equals(""))
		{
			hql+="";
		}
		else
		{
			String temp1=xnName.substring(0,4);
			String temp2=xnName.substring(5,9);
			
			if(!xnName.equals("")&&term.equals(""))
			{
				hql+=" and ((to_char(cc.recordTime,'YYYY')='"+temp1 +"' and cc.term='1' )or(to_char(cc.recordTime,'YYYY')='"+temp2 +"' and cc.term='2' ))";
			}
			if(!xnName.equals("")&&!term.equals("")){
				if(term.equals("1"))
				{
					hql+=" and (to_char(cc.recordTime,'YYYY')='"+temp1 +"' and cc.term='1' )";
				}
				else
					hql+=" and (to_char(cc.recordTime,'YYYY')='"+temp2 +"' and cc.term='2' )";
				
			}
		}
		
		
		 classList=(List<EMS_Class>)this.getSession().createQuery(hql).list();
		
		 VOconver.voConverClass(classList);
		 
		return classList;
	}


	public int getCourseCounts(String idnum) {
		// TODO Auto-generated method stub
		List list=this.getHibernateTemplate().find("from EMS_CourseRecord where studentId='"+idnum+"'");
		return list.size();
	}


	public int getScoreCounts(String idnum) {
		// TODO Auto-generated method stub
		List list=this.getHibernateTemplate().find("from EMS_CourseRecord where studentId='"+idnum+"' and grade is not null");
		return list.size();
	}


	public boolean isGraMaAvailable() {
		// TODO Auto-generated method stub
		Date date=new Date();
		System.out.println("Date Now:"+date);
//String year=date.substring(0,4);
		EMS_Systemset selectTime=(EMS_Systemset) this.getSession().createQuery("from EMS_Systemset where syssetname='学生毕业设计管理时间设置'").list().get(0);
		String start=DateConventer.timestampToStr(selectTime.getTimestart());
		Date startTime=DateConventer.strToDate(start);
		String end=DateConventer.timestampToStr(selectTime.getTimeend());
		Date endTime=DateConventer.strToDate(end);
		
		if(date.after(startTime)&&date.before(endTime))
			return true;
		else
			return false;
	}


	public int indexGraduationProject(String teacherIdnum,String glevel) {
		// TODO Auto-generated method stub
		
		String hql="from EMS_Graduation g where 1=1";
		
		if(!teacherIdnum.equals("")&&teacherIdnum!=null){
			hql+=" and g.teacherId.idnum='"+teacherIdnum +"'";
		}
		if(!glevel.equals("")&&glevel!=null){
			hql+=" and  g.glevel ='" +glevel +"'";
		}
		
		List<EMS_Graduation> list=(List<EMS_Graduation>)this.getSession().createQuery(hql+"and g.flag='teacher'").list();
		
	     VOconver.voConverGaS(list);
		
		return  list.size();
	}


	public int getGraCount() {
		// TODO Auto-generated method stub
		return 0;
	}

/**
 * 通过毕业设计编号，得到教师编号
 */
	public String findTeacherById(String projectId) {
		// TODO Auto-generated method stub
		List list=this.getHibernateTemplate().find("from EMS_Graduation where idnum='"+projectId+"'");
		
		return ((EMS_Graduation)list.get(0)).getTeacherId().getIdnum();
	}

	/**
	 * WORD转HTML
	 * 
	 * @param docfile
	 *            WORD文件全路径
	 * @param htmlfile
	 *            转换后HTML存放路径
	 */
public boolean wordToHtml(String docfile, String htmlfile) {
	// TODO Auto-generated method stub
	ActiveXComponent app = new ActiveXComponent("Word.Application"); // 启动word
	try {
		app.setProperty("Visible", new Variant(false));
		Dispatch docs = app.getProperty("Documents").toDispatch();
		Dispatch doc = Dispatch.invoke(docs,"Open",Dispatch.Method,
				new Object[] { docfile, new Variant(false),
						new Variant(true) }, new int[1]).toDispatch();
		Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {
				htmlfile, new Variant(8) }, new int[1]);
		Variant f = new Variant(false);
		Dispatch.call(doc, "Close", f);
		return true;
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	} finally {
		app.invoke("Quit", new Variant[] {});
	}

}


	
	

	


	





	

	


	
	

	

	
}
