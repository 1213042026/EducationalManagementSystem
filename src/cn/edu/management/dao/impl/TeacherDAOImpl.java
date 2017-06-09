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

	/****************************************** ������Ϣά�� ***********************************************/
	/***** �鿴������Ϣ *****/
	public Ems_Teacher_VO displayPersonInformation(String idnum) {
        System.out.println("DAO��ţ�"+idnum);
		HibernateTemplate hibernate = this.getHibernateTemplate();
		Ems_Teacher_VO vo = new Ems_Teacher_VO();
		vo.setIdnum(idnum);
		List list =  this.getSession().createQuery("from Ems_Teacher_VO where idnum='"+idnum +"'").list();
		Ems_Teacher_VO teachervo = (Ems_Teacher_VO) list.get(0);
		teachervo.getProfession().getPro_name();
		return teachervo;
	}	
	
	/******************************************��ҵ��ƹ���***********************************************/
	/*****�鿴����ӱ�ҵ���*****/
	public List<EMS_Graduation>  displayAlreadyAdd(String idnum){
//		List<EMS_Graduation> list = this.getHibernateTemplate().loadAll( EMS_Graduation.class );
		List<EMS_Graduation> list = this.getSession().createQuery("from EMS_Graduation where teacherId ='"+idnum +"' and flag='teacher'" ).list();
		VOconver.voConverGa(list);
		return list ;
	};	
		
	/**
	 * ȡ�����б�ҵ����б������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutGraduate(){
		
		List list = this.getSession().createQuery("select max(idnum) from EMS_Graduation t").list();
		 if(list == null || list.size() <= 0)		
			 return null;
		return (String) list.get(0);

		
	};

	/**
	 * ȡ�����б�ҵ����б������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutApplyModify(){
		List list = this.getSession().createQuery("select max(idnum) from ApplyModifyLogVO t").list();
		System.out.println("DAO�����޸ı�ţ�"+(String)list.get(0));
		return (String)list.get(0);
	};
	
	//�ҳ���Ӧ��������¼
	public List<ApplyModifyLogVO>  displayAlreadyApplyStatus(String idnum){
		String sql = "from ApplyModifyLogVO where applyUserId='"+idnum+"'";
		System.out.println("IdnumSql:"+sql);
		List<ApplyModifyLogVO> list = this.getSession().createQuery(sql).list();
		
		return list;
	};
	
	
	/**
	 * ���������밴ťʱ
	 * 
	 * �������¼������Ƿ��������ʦ�ļ�¼����˽��Ϊ������С�
	 */
	public ApplyModifyLogVO addModifyApplycheck(String applyUserId){
		String sql = "from ApplyModifyLogVO where applyUserId='"+ applyUserId +
		"'and modifyResult='�����'";
		System.out.println("���������ʾ"+sql);
		 
		 List list = this.getHibernateTemplate().find(sql);
		 if(list == null || list.size() <= 0)		
		 return null;
		 
		 return (ApplyModifyLogVO)list.get(0);
	};
	
	/**
	 * �õ���Ӧ��ŵı�ҵ���
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
//		System.out.println("�ɼ���"+g.getGrade());
//		
//	}
	/**
	 * ��ѯ��ҵ���ѧ����Ϣ
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearch(String teacherid){
		
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//������
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			EMS_GraduateGrade gg = (EMS_GraduateGrade)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//��ҵ��Ƽ�¼����
			System.out.println("������"+g.getGname());
			gStudentInfor.setGname(g.getGname());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("������"+g.getGname());
	//	System.out.println("ѧ������:"+s.getName());
	
		return gStudentInforList;
	
	};
	
	/**
	 * ��ҳ--��ѯ��ҵ���ѧ����Ϣ
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearchPage(String teacherid,int nowPage){
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum"; 
		Query query = this.getSession().createQuery(sql);
		query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);
		
		List<Object[]> list = query.list();
		
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//������
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			EMS_GraduateGrade gg = (EMS_GraduateGrade)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//��ҵ��Ƽ�¼����
			System.out.println("������"+g.getGname());
			gStudentInfor.setGname(g.getGname());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("������"+g.getGname());
	//	System.out.println("ѧ������:"+s.getName());
	
		return gStudentInforList;
	
	};
	  
	/**
	 * ѧ���ļ����� 
	 * @return
	 */
	public List<StudentGraduationWorksMange> studentFileManage(String teacherid){
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<StudentGraduationWorksMange> gStudentInforList = new ArrayList<StudentGraduationWorksMange>();//������
		for(Object[] obj:list){
			StudentGraduationWorksMange gStudentInfor = new StudentGraduationWorksMange();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			System.out.println("������"+g.getGname());
			gStudentInfor.setGname(g.getGname());//����
			gStudentInfor.setPro_name(s.getProfession().getPro_name());//רҵ��
			gStudentInfor.setSidnum(s.getIdnum());//ѧ��
			gStudentInfor.setSname(s.getName());//ѧ������
			gStudentInfor.setGidnum(g.getIdnum());//�ϱ��
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("������"+g.getGname());
	//	System.out.println("ѧ������:"+s.getName());
	
		return gStudentInforList;
	};
	/**
	 * ѧ���ļ����� 
	 * @return
	 */
	public List<StudentGraduationWorksMange> studentFileManagePage(String teacherid,int pageNow){
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum"; 
		
		Query query = this.getSession().createQuery(sql);
		  query.setMaxResults(6);
		   query.setFirstResult((pageNow-1)*6);
		   
		List<Object[]> list = query.list();
		
		List<StudentGraduationWorksMange> gStudentInforList = new ArrayList<StudentGraduationWorksMange>();//������
		for(Object[] obj:list){
			StudentGraduationWorksMange gStudentInfor = new StudentGraduationWorksMange();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			System.out.println("������"+g.getGname());
			gStudentInfor.setGname(g.getGname());//����
			gStudentInfor.setPro_name(s.getProfession().getPro_name());//רҵ��
			gStudentInfor.setSidnum(s.getIdnum());//ѧ��
			gStudentInfor.setSname(s.getName());//ѧ������
			gStudentInfor.setGidnum(g.getIdnum());//�ϱ��
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("������"+g.getGname());
	//	System.out.println("ѧ������:"+s.getName());
	
		return gStudentInforList;
	};
	
	
	/**
	 * ������
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
	 * ������
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
	//��ʾҪ��˵ı�ҵ�����Ŀ
	public List<disAuditGraduationTitle> disAuditGraduationTitle(String tidnum){
		String sql = "from ApplyModifyLogVO ap,Ems_Student_VO es,EMS_Graduation eg where ap.fortable='ems_graduation' and ap.modifyResult='�����' and ap.remark=es.idnum"
			+" and eg.idnum=ap.applyUserId and eg.teacherId='"+tidnum+"'"	;
		
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<disAuditGraduationTitle> gStudentInforList = new ArrayList<disAuditGraduationTitle>();
		for(Object[] obj:list){
			disAuditGraduationTitle gStudentInfor = new disAuditGraduationTitle();
			ApplyModifyLogVO ap = (ApplyModifyLogVO)obj[0];
			Ems_Student_VO es = (Ems_Student_VO)obj[1];
			
			//System.out.println("�Ϻţ�"+ap.getApplyUserId());
			gStudentInfor.setGidnum(ap.getApplyUserId());//��ҵ��ƺ�,���ڱ��Ϊteacher
		//	System.out.println("ѧ�ţ�"+ap.getRemark());
			gStudentInfor.setSidnum(ap.getRemark());//ѧ��,����д��ѡ��ҵ��Ƽ�¼����		
			gStudentInfor.setGgidnum(ap.getIdnum());// ��ҵ��Ƽ�¼����,���ڸ���Ϊ��˽��
			gStudentInfor.setProfession(es.getProfession().getPro_name());//רҵ
			gStudentInfor.setApplyDate(DateConventer.timestampToStr(ap.getApplyDate()));//����ʱ��
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
		return gStudentInforList;
	}
	
	
	//�õ�����Ŵӱ�ҵ��Ƽ�¼����
	public String getMaxIdNumAboutGraduateGrade(){
		List list = this.getSession().createQuery("select max(idnum) from EMS_GraduateGrade t").list();
		System.out.println("DAO��ҵ��Ƽ�¼���ţ�"+(String)list.get(0));
		return (String)list.get(0);
	};
	
	//�õ���˼�¼
	public  List<disAuditGraduationResult> disAditInfor(String tidnum){
	
			String sql = "from ApplyModifyLogVO ap where fortable='ems_graduation'" +
					" and modifyResult!='�����' and auditMan='"+tidnum+"' ";
			System.out.println("disAditInfor��sql:"+sql);
			
			List list = this.getSession().createQuery(sql).list();
//			private String sidnum;//ѧ��			
//		    private String graduation ; //integer, --��ҵ�������
//		    private String applyDate ; //date,  --����ʱ�� 		    
//		    private String auditDate ; //date,  --���ʱ��    
//		    private String result ; //��˽��
			List<disAuditGraduationResult> gStudentInforList = new ArrayList<disAuditGraduationResult>();
			for(Object obj:list){
				disAuditGraduationResult gStudentInfor = new disAuditGraduationResult();
				ApplyModifyLogVO ap = (ApplyModifyLogVO)obj;				
				
				//System.out.println("�Ϻţ�"+ap.getApplyUserId());
				gStudentInfor.setSidnum(ap.getRemark());//ѧ��
			//	System.out.println("ѧ�ţ�"+ap.getRemark());
			  //  gStudentInfor.setGraduation(eg.getGname());//��ҵ�������				
				gStudentInfor.setApplyDate(DateConventer.timestampToStr(ap.getApplyDate()));//����ʱ��
				gStudentInfor.setAuditDate(DateConventer.timestampToStr(ap.getAuditDate()));//���ʱ��
				gStudentInfor.setResult(ap.getModifyResult());//��˽��
				gStudentInfor.setSuggest(ap.getRemarks());//��˽���
				gStudentInforList.add(gStudentInfor);//���뵥����¼
			}
			
			return  gStudentInforList ;
		
		
	};
	
	/**
	 * ѧ����ҵ��Ƴɼ�¼��
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeInput(String teacherid){
		
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum and gg.grade=null"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//������
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			EMS_GraduateGrade gg = (EMS_GraduateGrade)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//��ҵ��Ƽ�¼����
			System.out.println("������"+g.getGname());
			gStudentInfor.setGname(g.getGname());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("������"+g.getGname());
	//	System.out.println("ѧ������:"+s.getName());
	
		return gStudentInforList;
	};
	
	/**
	 * ѧ����ҵ��Ƴɼ�¼��--��ҳ
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeInput(String teacherid,int nowPage){
		
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum and gg.grade=null"; 
		Query query = this.getSession().createQuery(sql);
		   query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);
		List<Object[]> list = query.list();
		
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//������
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			EMS_GraduateGrade gg = (EMS_GraduateGrade)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//��ҵ��Ƽ�¼����
			System.out.println("������"+g.getGname());
			gStudentInfor.setGname(g.getGname());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("������"+g.getGname());
	//	System.out.println("ѧ������:"+s.getName());
	
		return gStudentInforList;
	};
	
	/**
	 * �õ�ָ����ҵ��Ƽ�¼���еļ�¼
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
	 * ѧ����ҵ��Ƴɼ��޸�
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeupdate(String teacherid){
		
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum and gg.grade!=null"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//������
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			EMS_GraduateGrade gg = (EMS_GraduateGrade)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//��ҵ��Ƽ�¼����
			gStudentInfor.setGrade(gg.getGrade());//�ɼ�
			System.out.println("������"+g.getGname());
			gStudentInfor.setGname(g.getGname());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("������"+g.getGname());
	//	System.out.println("ѧ������:"+s.getName());
	
		return gStudentInforList;
	};
	
	
	/**
	 * ��ʾ����ӿγ�
	 * @param teacherid
	 * @return
	 */
	public List<showAlreadyAddCourse> displayAlreadyAddCourse(String teacherid){
      String sql = "from EMS_Class where teacherId='" + teacherid +"'";
      List<EMS_Class> list = this.getSession().createQuery(sql).list();
      //��Dao�����ת��
		return VOconver.voConverdisAlreadyCourse(list);
	};
	
	/**
	 * �õ��γ̱������
	 */
	public String getMaxIdNumAboutCourse(){
		List list = this.getSession().createQuery("select max(classId) from EMS_Class t").list();
		 if(list == null || list.size() <= 0)		
			 return null;
		return (String) list.get(0);
		
	};
	
	/**
	 * �õ��γ�����
	 */
    public List<ClassTypeVO> listClassType(){
    	List<ClassTypeVO> list = this.getHibernateTemplate().loadAll( ClassTypeVO.class );
    	return list;
    };
	
    
    /**
     * �õ���Ӧ��ŵĿγ�
     */
    public EMS_Class getModifyCourseInfo(String cidnum){
     List list = this.getSession().createQuery("from EMS_Class where classId='"+cidnum+"'").list();
		
		if(list ==null || list.size() <= 0)
			return null;
		return (EMS_Class)list.get(0);
    };
    
    
    
    /**
	 * ��ѯѡ��ѧ����Ϣ
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearchCourse(String teacherid){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//������
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			//gStudentInfor.setGgidnum(gg.getIdnum());//��ҵ��Ƽ�¼����
			System.out.println("�γ����ƣ�"+g.getClassName());
			gStudentInfor.setGname(g.getClassName());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("������"+g.getGname());
	//	System.out.println("ѧ������:"+s.getName());
	
		return gStudentInforList;
	
	};
	
	/**
	 * ��ѯѡ��ѧ����Ϣ�ɼ�

	 */
	@SuppressWarnings("unchecked")
public List<Object[]>  graStuGragradeCourse(String teacherid,String nowPage,int nowPag){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.grade!=null and gg.studentId=s.idnum and s.idnum='"+nowPage+"'"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		return list;
	} 
	/**
	 * ��ѯѡ��ѧ����Ϣ�ɼ�

	 */
	@SuppressWarnings("unchecked")
	public List<Object[]>  graStuGragradeCourse(String teacherid){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.grade!=null and gg.studentId=s.idnum"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		return list;
	} 
	/**
	 * ѧ����ҵ��Ƴɼ�¼��
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeInput(String teacherid){
		
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum and gg.grade=null"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//������
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//ѡ�μ�¼����
		//	System.out.println("������"+g.getGname());
			gStudentInfor.setGname(g.getClassName());//�γ���
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("������"+g.getGname());
	//	System.out.println("ѧ������:"+s.getName());
	
		return gStudentInforList;
	};
	
	
    /**
     * �õ���Ӧ��ŵĿγ�
     */
    public EMS_CourseRecord getSpecifyCourseRecord(String cidnum){
     List list = this.getSession().createQuery("from EMS_CourseRecord where idnum='"+cidnum+"'").list();
		
		if(list ==null || list.size() <= 0)
			return null;
		return (EMS_CourseRecord)list.get(0);
    };
    
    /**
	 * ѧ����ҵ��Ƴɼ��޸�
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeupdate(String teacherid){
		
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum and gg.grade!=null"; 
		List<Object[]> list = this.getSession().createQuery(sql).list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//������
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//ѡ�μ�¼����
			gStudentInfor.setGrade(gg.getGrade());//�ɼ�
			//System.out.println("������"+g.getGname());
			gStudentInfor.setGname(g.getClassName());//�γ�����
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("������"+g.getGname());
	//	System.out.println("ѧ������:"+s.getName());
	
		return gStudentInforList;
	};
    
	/**
	 * ��ʾ�ѻظ�����
	 */
	public List<ShowMessageVO> disAlreadyReplyMessage(String tidnum,String status){
		String sql = "from MessageVO where tidnum='"+tidnum+
		"' and status='"+status+"'";
		System.out.println("disAlreadyReplyMessage��sql:"+sql);
		List<MessageVO> list = this.getSession().createQuery(sql).list();
		
        // System.out.println(list.get(0).getTidnum().getIdnum());		
		return  VOconver.voConverdisAlreadyReplyMessage(list) ;
	};
	
	/**
	 * �õ�ָ����ŵ�������Ϣ
	 */
	public MessageVO getMessageVO(String idnum){
		 List list = this.getSession().createQuery("from MessageVO where idnum='"+idnum+"'").list();
			
			if(list ==null || list.size() <= 0)
				return null;
			return (MessageVO)list.get(0);
		
	};	
	
	/**
	 * �õ���ҵ��Ƽ�¼��
	 */
	public int getGrauationCounts(String teacherid) {
		// TODO Auto-generated method stub
		List list=this.getHibernateTemplate().find("from EMS_Graduation where flag='teacher' and teacherId='"+teacherid+"'");
		return list.size();
	}
	
	/*****�鿴���б�ҵ���--��ҳ*****/
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
	 * �õ�ѡ��ѧ����Ϣ��¼��
	 */
	public int studentInforSearchCounts(String teacherid) {
		// TODO Auto-generated method stub
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum"; 
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	}
	
	/**
	 * �õ�ѧ���ļ������¼��
	 */
	public int studentFileManageCounts(String teacherid) {
		// TODO Auto-generated method stub
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum"; 
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	}
	/**
	 * �õ�ѧ���ɼ���ѯ��¼��
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
		 * ��ѯѡ��ѧ����Ϣ�ɼ�--��ҳ

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
		 * �õ�ѧ���������¼��
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
			 * �õ�ѧ���ɼ��޸ļ�¼��
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
	 * ѧ����ҵ��Ƴɼ��޸�-��ҳ
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeupdate(String teacherid,int nowPage){
		String sql = "from  EMS_Graduation g,EMS_GraduateGrade gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.idnum=gg.gidnum and gg.studentId=s.idnum and gg.grade!=null"; 

		Query query = this.getSession().createQuery(sql);
				   query.setMaxResults(6);
				   query.setFirstResult((nowPage-1)*6);

		List<Object[]> list = query.list();
		
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//������
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			EMS_GraduateGrade gg = (EMS_GraduateGrade)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//��ҵ��Ƽ�¼����
			gStudentInfor.setGrade(gg.getGrade());//�ɼ�
			System.out.println("������"+g.getGname());
			gStudentInfor.setGname(g.getGname());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("������"+g.getGname());
	//	System.out.println("ѧ������:"+s.getName());
	
		return gStudentInforList;
	};
	
	
	/**
	 * ��ʾ����ӿγ�--��ҳ
	 * @param teacherid
	 * @return
	 */
	public List<showAlreadyAddCourse> displayAlreadyAddCourse(String teacherid,int nowPage){
		 String sql = "from EMS_Class where teacherId='" + teacherid +"'";
		 Query query = this.getSession().createQuery(sql);
		   query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);
	      List<EMS_Class> list = query.list();
	      //��Dao�����ת��
			return VOconver.voConverdisAlreadyCourse(list);
	};
	/**
	 * �õ��γ̱��¼��
	 * @return
	 */
		public int CourseCounts(String teacherid){
			// TODO Auto-generated method stub
			List list=this.getHibernateTemplate().find("from EMS_Class where teacherId='" + teacherid +"'");
			return list.size();
		};
		
		
		/**
		 * �õ�ѡ ��ѧ����Ϣ��¼��
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
			 * ��ѯѡ��ѧ����Ϣ--��ҳ
			 * @return
			 */
	public List<GraduationStudentInfor> studentInforSearchCourse(String teacherid,int nowPage){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum"; 
		Query query = this.getSession().createQuery(sql);
		   query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);

		List<Object[]> list = query.list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//������
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			//gStudentInfor.setGgidnum(gg.getIdnum());//��ҵ��Ƽ�¼����
			System.out.println("�γ����ƣ�"+g.getClassName());
			gStudentInfor.setGname(g.getClassName());
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("������"+g.getGname());
	//	System.out.println("ѧ������:"+s.getName());
	
		return gStudentInforList;
	};
	/**
	 * �õ�ѡ ��ѧ���ɼ���¼��
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
	 * ��ѯ¼��γ̳ɼ�--��ҳ
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeInput(String teacherid,int nowPage){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum and gg.grade=null"; 
		Query query = this.getSession().createQuery(sql);
		   query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);
		List<Object[]> list = query.list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//������
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//ѡ�μ�¼����
		//	System.out.println("������"+g.getGname());
			gStudentInfor.setGname(g.getClassName());//�γ���
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("������"+g.getGname());
	//	System.out.println("ѧ������:"+s.getName());
	
		return gStudentInforList;
	};

	/**
	 * �õ�¼��γ̳ɼ���¼��
	 * @return
	 */
	public int studentCourseGradeInputCounts(String teacherid){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum and gg.grade=null"; 
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	};	
		
	/**
	 * ��ѯ�޸Ŀγ̳ɼ�--��ҳ
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeupdate(String teacherid,int nowPage){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum and gg.grade!=null"; 
		Query query = this.getSession().createQuery(sql);
		   query.setMaxResults(6);
		   query.setFirstResult((nowPage-1)*6);
		List<Object[]> list = query.list();
		List<GraduationStudentInfor> gStudentInforList = new ArrayList<GraduationStudentInfor>();//������
		for(Object[] obj:list){
			GraduationStudentInfor gStudentInfor = new GraduationStudentInfor();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			gStudentInfor.setGgidnum(gg.getIdnum());//ѡ�μ�¼����
			gStudentInfor.setGrade(gg.getGrade());//�ɼ�
			//System.out.println("������"+g.getGname());
			gStudentInfor.setGname(g.getClassName());//�γ�����
			gStudentInfor.setProf_Name(s.getProfession().getPro_name());
			gStudentInfor.setIdnum(s.getIdnum());
			gStudentInfor.setName(s.getName());
			gStudentInfor.setIdcard(s.getIdcard());
			gStudentInfor.setSex(s.getSex());
			gStudentInfor.setNation(s.getNation());
			gStudentInfor.setAddress(s.getAddress());
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
//		Object[] obj = (Object[])list.get(0);
//		EMS_Graduation g = (EMS_Graduation)obj[0];
//		Ems_Student_VO s = (Ems_Student_VO)obj[2];
	//	System.out.println("������"+g.getGname());
	//	System.out.println("ѧ������:"+s.getName());
	
		return gStudentInforList;
	};

	/**
	 * �õ��޸Ŀγ̳ɼ���¼��
	 * @return
	 */
	public int studentCourseGradeupdateCounts(String teacherid){
		String sql = "from  EMS_Class g,EMS_CourseRecord gg,Ems_Student_VO s where g.teacherId='"+teacherid+
		"' and g.classId=gg.idnum and gg.studentId=s.idnum and gg.grade!=null"; 
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	};		
	
	
	/**
	 * �鿴������״̬--��ҳ
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
	 * �õ��鿴������״̬��¼��
	 * @return
	 */
	public int displayAlreadyApplyStatusCounts(String teacherid){
		String sql = "from ApplyModifyLogVO where applyUserId='"+teacherid+"'";
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	};
	
	
	/**
	 * �鿴�ѻظ�����--��ҳ
	 * @return
	 */
	public List<ShowMessageVO> disAlreadyReplyMessage(String teacherid,String status,int nowPage){
		String sql = "from MessageVO where tidnum='"+teacherid+
		"' and status='"+status+"'";
		System.out.println("disAlreadyReplyMessage��sql:"+sql);
		

	  	Query query = this.getSession().createQuery(sql);
	   query.setMaxResults(2);
	   query.setFirstResult((nowPage-1)*2);	   

		List<MessageVO> list = query.list();		
        // System.out.println(list.get(0).getTidnum().getIdnum());		
		return  VOconver.voConverdisAlreadyReplyMessage(list) ;
		
	};

	/**
	 * �õ��ѻظ����Լ�¼��
	 * @return
	 */
	public int disAlreadyReplyMessageCounts(String teacherid,String status){
		String sql = "from MessageVO where tidnum='"+teacherid+
		"' and status='"+status+"'";
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	};

	/**
	 * ϵͳʱ������
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
	 * �ʺŲ���
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
