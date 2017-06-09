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

	/****************************************** ������Ϣά�� ***********************************************/
	/***** �鿴������Ϣ *****/
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
	

	/****************************************** ��Ϣ��ѯ ***********************************************/
	/***** ��У�ɼ���ѯ *****/
	public String findScoreByCourserecord(String classId,Ems_Student_VO vo) {
		// TODO Auto-generated method stub
		EMS_CourseRecord list=(EMS_CourseRecord) this.getSession().createQuery("from EMS_CourseRecord where classId='"+classId+"' and studentId='"+vo.getIdnum()+"'").list().get(0);
		String score=list.getGrade();
		if(score!=null)
		     return score;
		else return null;
	}
	
	/**����ѡ�������ѯ**/
	public List<EMS_Class> listMyAllCourse(String idnum, String grade,
			String profession,int pageNow) {
		// TODO Auto-generated method stub
		
		//�ӿγ̱���ѡ��ѧ��ѡ�Ŀγ̣��γ̺���ѡ�μ�¼����ɸѡ����������ѯ������ѧ����ѡ�Ŀ�����ѡ��ʱ�䣨��ʦ��ӿγ̵��·ݣ�֮�ڵ�
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
		
		//�ӿγ̱���ѡ��ѧ��ѡ�Ŀγ̣��γ̺���ѡ�μ�¼����ɸѡ����������ѯ������ѧ����ѡ�Ŀ�����ѡ��ʱ�䣨��ʦ��ӿγ̵��·ݣ�֮�ڵ�
		   Query query=this.getSession().createQuery("from EMS_Class cc where cc.classId in ( select c.classId from EMS_CourseRecord c where (c.studentId='"+idnum+"') and c.grade is not null)");
		  
		   query.setMaxResults(10);
		   query.setFirstResult((pageNow-1)*10);
		   List<EMS_Class> classList=query.list();
		    System.out.println(classList.get(0).getRecordTime());
		   VOconver.voConverClass(classList);
		 
		   return classList;
	}
	
	/**
	 * �õ�ĳ�ſγ̵�ѡ��ʱ��
	 */
	public String selectCourseTime(EMS_Class classVo,Ems_Student_VO vo)
	{
		String Xgrade,t;
		String cid=classVo.getClassId();
		EMS_CourseRecord temp=null;

		temp=(EMS_CourseRecord)this.getSession().createQuery("from EMS_CourseRecord c where studentId='"+vo.getIdnum()+"' and classId='"+cid+"'").list().get(0);
		t=DateConventer.timestampToStrNo(temp.getChooseTime());
		Xgrade=t.substring(0,t.length()-3);
		System.out.println("ѡ��ʱ�䣺"+Xgrade);
		System.out.println("year:"+Xgrade.substring(0,4));

		return Xgrade;
	}
	
	
	/******************************************����ѡ��***********************************************/

	/**
	 * �ж������Ƿ���ѧ��ѡ��ʱ��
	 */
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		Date date=new Date();
		System.out.println("Date Now:"+date);
//String year=date.substring(0,4);
		EMS_Systemset selectTime=(EMS_Systemset) this.getSession().createQuery("from EMS_Systemset where syssetname='ѧ��ѡ��ʱ������'").list().get(0);
		String start=DateConventer.timestampToStr(selectTime.getTimestart());
		Date startTime=DateConventer.strToDate(start);
		String end=DateConventer.timestampToStr(selectTime.getTimeend());
		Date endTime=DateConventer.strToDate(end);
		
		if(date.after(startTime)&&date.before(endTime))
			return true;
		else
			return false;
	}

	
	/*****ѡ�޿γ�*****/
	/**
	 * �鿴��ѡ�޵Ŀγ�
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
		
		//�г��������޿κ�����ѡ�޿Σ�רҵ���޿Σ����꼶��ѧ�ڣ�ʣ������>0���ÿγ�¼��ʱ������ͬһ�꣬���Ҹ�ѧ����û��ѡ�Ŀγ̣�ѡ�˵Ĳ��г���(���Լ������޹��Ŀγ�Ҳ���г�����ͨ���ɼ������Ϊ�����жϣ�   ����Ҫ��ͨ��ǰ���¼��ʱ���Ѿ�ɸѡ��)
		// classList=(List<EMS_Class>)this.getSession().createQuery(" from EMS_Class c where (c.classType  in('0001','0004') or c.professionId='"+profession+"') and c.grade='"+grade+"'  and to_number(c.sCount)>0 and  c.term='"+term+"' and to_char(c.recordTime,'YYYY')='"+year+"' and classId not in(select cc.classId  from EMS_CourseRecord cc where to_char(cc.chooseTime,'YYYY')='"+year+"' and cc.studentId='"+idnum+"'  and cc.grade is not null)" ).list();
		   List list=this.getSession().createQuery(" from EMS_Class c where (c.classType  in('0001','0004') or c.professionId='"+profession+"') and c.grade='"+grade+"'  and to_number(c.sCount)>0 and  c.term='"+term+"' and to_char(c.recordTime,'YYYY')='"+year+"' and classId not in(select cc.classId  from EMS_CourseRecord cc where cc.studentId='"+idnum+"' )" ).list();
		   if(list.size()==0)
			   return null;
		   classList=(List<EMS_Class>)list;
		   VOconver.voConverClass(classList);
		 
		return classList;
	}

	/**
	 * �õ��γ̼�¼������ID
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
	 * �鿴����ѧ���Ѿ�ѡ�޵Ŀγ�
	 * @return
	 */
	public List<EMS_Class> listMyCourse(String idnum, String grade,String profession) {
		// TODO Auto-generated method stub
		
		EMS_Systemset sys=(EMS_Systemset) this.getSession().createQuery("from EMS_Systemset where syssetname='��ʦ��ӿγ�ʱ������'").list().get(0);
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
		
		//�ӿγ̱���ѡ��ѧ��ѡ�Ŀγ̣��γ̺���ѡ�μ�¼����ɸѡ����������ѯ������ѧ����ѡ�Ŀ�����ѡ��ʱ�䣨��ʦ��ӿγ̵��·ݣ�֮�ڵ�
		 classList=(List<EMS_Class>)this.getSession().createQuery("from EMS_Class cc where cc.classId in ( select c.classId from EMS_CourseRecord c where (c.studentId='"+idnum+"'  and  to_char(c.chooseTime,'yyyy-mm-dd HH:mm:ss') between  '"+timestart.toString().substring(0,timestart.toString().length()-2 )+"'  and  '"+timeEnd.toString().substring(0,timeEnd.toString().length()-2 ) +"' ))").list();
		
		 VOconver.voConverClass(classList);
		 
		return classList;
	}
	
	/**
	 * ͨ���γ̺Ų��������γ̼�¼
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
	 * ѡ��ѧ����һ��ѡ�μ�¼
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
	
	
	
	
	/******************************************��ҵ��ƹ���***********************************************/
	/*****�鿴���б�ҵ���*****/
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
	 * �õ���ҵ��Ʊ������е���ʦ
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
//	 * �õ���ҵ��Ʊ������е�רҵ
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
	 * ͨ����ʦ���֣����׳̶ȼ�����ҵ���
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
	 * ������ӱ�ҵ��Ƽ�¼���ʱ��ȡ�����ݿ��¼�е����ID������Ա���ܽ����ֶ�����ID��
	 * @return
	 */
	public String getMaxIdNumAboutGraduateGrade() {
		// TODO Auto-generated method stub
		
		List list=this.getHibernateTemplate().find("select max(idnum) from EMS_GraduateGrade");
		return (String) list.get(0);
	}

	/**
	 * �жϸ�ѧ���Ƿ��Ѿ�ѡ���˱�ҵ���
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
	 * �鿴�ҵı�ҵ���
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
	 * ͨ����ҵ��Ʊ�Ŷ�ȡ������¼
	 * @param gidnum
	 * @return
	 */
	public EMS_Graduation findGraduationById(String gidnum) {
		// TODO Auto-generated method stub
		List list=this.getSession().createQuery("from EMS_Graduation where idnum='"+gidnum+"'").list();
		return (EMS_Graduation) list.get(0);
	}

	/**
	 * ͨ��רҵ�Ų�������רҵ��¼
	 * @param proName
	 * @return
	 */
	public ProfessionVO findProfessionByProName(String proName) {
		// TODO Auto-generated method stub
		List list=this.getSession().createQuery("from ProfessionVO where idnum='"+proName+"'").list();
		return (ProfessionVO) list.get(0);
	}

	/**
	 * ͨ����ʦ��Ų���������ʦ��¼
	 * @param tName
	 * @return
	 */
//	public Ems_Teacher_VO findTeacherById(String tName) {
//		// TODO Auto-generated method stub
//		List list=this.getSession().createQuery("from Ems_Teacher_VO where idnum='"+tName+"'").list();
//		return (Ems_Teacher_VO) list.get(0);
//	}
	
	/**
	 * �鿴�Ƿ��Ѿ�����������Ŀ�ı�ҵ��ƣ������������ѡ���ҵ���
	 * @param userId
	 * @return
	 */
	public String isApply(String userId) {
		// TODO Auto-generated method stub
		String sql = "from ApplyModifyLogVO ap where ap.fortable='ems_graduation' and ap.modifyResult='�����' and ap.remark='"+userId+"'";
		List list=this.getSession().createQuery(sql).list();
		if(list.size()!=0)
			 return "true";
		else return null;
	}
	
	/**
	 * �鿴ѧ����ѡ��ҵ��Ƶĵ�ʦ
	 * @return
	 */
	public Ems_Teacher_VO findTName(EMS_GraduateGrade gvo) {
		// TODO Auto-generated method stub
		EMS_Graduation list=(EMS_Graduation)this.getSession().createQuery("from EMS_Graduation where idnum='"+gvo.getGidnum().getIdnum()+"'").list().get(0);
		Ems_Teacher_VO tvo=(Ems_Teacher_VO)this.getSession().createQuery("from Ems_Teacher_VO where idnum='"+list.getTeacherId().getIdnum()+"'").list().get(0);
		return tvo;
	}

/********************************************���԰����************************************************/
	/**
	 * ����������Ա��ʱ��ȡ�����ݿ��¼�е����ID
	 * @return
	 */
	public String getMaxIdNumAboutMessage() {
		// TODO Auto-generated method stub
		List list=this.getHibernateTemplate().find("select max(idnum) from MessageVO");
		return (String) list.get(0);
	}
	
	/**
	 * ��ʾѧ��������Ϣ
	 * @param idnum
	 * @return
	 */
	public List<MessageVO> showMessage(String idnum, String status) {
		// TODO Auto-generated method stub
		if(status=="�ѻظ�")
		{
			List<MessageVO> list=this.getSession().createQuery("from MessageVO where sidnum='"+idnum+"' and status='�ѻظ�'").list();
			VOconver.voConverdisAlreadyReplyMessage(list);
			return list;
		}
		else if(status=="������")
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
/********************************************������Ϣ�����޸�************************************************/
	/**
	 * �鿴������Ϣ
	 * @return
	 */
	public List<ApplyModifyLogVO> displayAlreadyApplyStatus(String idnum) {
		// TODO Auto-generated method stub
		List<ApplyModifyLogVO> list=this.getSession().createQuery( "from ApplyModifyLogVO where applyUserId='"+idnum+"'").list();
		return list;
	}

	/**
	 * �鿴������Ϣ
	 * @return
	 */
	public List<ApplyModifyLogVO> displayAlreadyApplyStatusSelf(String idnum) {
		// TODO Auto-generated method stub
		List<ApplyModifyLogVO> list=this.getSession().createQuery( "from ApplyModifyLogVO where remark='"+idnum+"'").list();
		return list;
	}
	
	/**
	 * ����Ƿ��������״̬ʱ������С�
	 * @param idnum
	 * @param string 
	 * @return
	 */
	public boolean checkApply(String idnum,String string) {
		// TODO Auto-generated method stub
		List<ApplyModifyLogVO> list =this.getSession().createQuery("from ApplyModifyLogVO where remark='"+idnum+"' and modifyResult='�����'"+" and fortable='"+string+"'").list();
		if(list.size()!=0)
			return true;
		return false;
	}

	//�ж������Ƿ���ѡ���ҵ���ʱ��
	public boolean isGraAvailable() {
		// TODO Auto-generated method stub
		Date date=new Date();
		System.out.println("Date Now:"+date);
//String year=date.substring(0,4);
		EMS_Systemset selectTime=(EMS_Systemset) this.getSession().createQuery("from EMS_Systemset where syssetname='ѧ����ҵ���ѡ��ʱ������'").list().get(0);
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
	 * �ж��Ƿ��Ѿ����������ѡ��
	 * @return
	 */
	public boolean isApplying(String remark) {
		// TODO Auto-generated method stub
		List<ApplyModifyLogVO> list =this.getSession().createQuery("from ApplyModifyLogVO where remark='"+remark+"' and modifyResult='δ���'"+" and fortable='ems_graduation'").list();
		return false;
	}

	/**
	 * �õ���ҵ��Ƽ�¼��
	 */
	public int getGrauationCounts() {
		// TODO Auto-generated method stub
		List list=this.getHibernateTemplate().find("from EMS_Graduation where flag='teacher'");
		return list.size();
	}

	/**
	 * ͨ��ѧ���ѧ�ڲ�ѯ�γ�
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
		EMS_Systemset selectTime=(EMS_Systemset) this.getSession().createQuery("from EMS_Systemset where syssetname='ѧ����ҵ��ƹ���ʱ������'").list().get(0);
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
 * ͨ����ҵ��Ʊ�ţ��õ���ʦ���
 */
	public String findTeacherById(String projectId) {
		// TODO Auto-generated method stub
		List list=this.getHibernateTemplate().find("from EMS_Graduation where idnum='"+projectId+"'");
		
		return ((EMS_Graduation)list.get(0)).getTeacherId().getIdnum();
	}

	/**
	 * WORDתHTML
	 * 
	 * @param docfile
	 *            WORD�ļ�ȫ·��
	 * @param htmlfile
	 *            ת����HTML���·��
	 */
public boolean wordToHtml(String docfile, String htmlfile) {
	// TODO Auto-generated method stub
	ActiveXComponent app = new ActiveXComponent("Word.Application"); // ����word
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
