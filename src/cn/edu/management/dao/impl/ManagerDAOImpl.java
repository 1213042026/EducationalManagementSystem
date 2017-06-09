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
	 * �ʺŲ���
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
	 * ������֤����Ա����Ƿ�Ϸ�
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
	 * �������еĹ���Ա
	 * @return
	 */
	public List<Ems_Manager_VO> listManager(){
		
		List<Ems_Manager_VO> list = this.getHibernateTemplate().loadAll( Ems_Manager_VO.class );
		
		////////////////////////////////////////////////////////////////////////
		//����
		 Collections.sort(list,new ManagerComparator());
		return list;
	}
	
	/**
	 * ȡ�����й���Ա���������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutManager(){
		//��hibernate2X����ʹ��find����ִ��HQL,����hibernate3.x����ʹ��
		//org.hibernate.Query�ӿ���ִ��HQL��.
		//��ʽһ:ͨ���õ�HibernateTemplate,���治��ʹ��list��������list
		List list = this.getHibernateTemplate().find( "select max(idnum) from Ems_Manager_VO t  where idnum!='system' " );
		 if(list == null || list.size() <= 0)		
			 return null;
		return (String) list.get(0);
	}
	
	/**
	 * ȡ�������Ҫ�޸ĵĹ���Ա����Ϣ
	 * @return
	 */
	public Ems_Manager_VO getModifyManagerInfo( String idnum  ){
		HibernateTemplate hibernate = this.getHibernateTemplate();
		Ems_Manager_VO vo = new Ems_Manager_VO();
		vo.setIdnum(idnum);
		//��ʽ��:ͨ���õ�Session,�ٵõ�Queryִ��HQL����Ҫ�������Ҫʹ��list��������list
		List list =  this.getSession().createQuery("from Ems_Manager_VO where idnum='"+idnum +"'").list();
		 if(list == null || list.size() <= 0)		
			 return null;
		return (Ems_Manager_VO) list.get(0);
	}

	
	/*******************************��ʦ***********************************************/
	/**
	 * �������еĽ�ʦ
	 * @return
	 */
	public List<Ems_Teacher_VO> listTeacher() {
		List<Ems_Teacher_VO> list = this.getHibernateTemplate().loadAll( Ems_Teacher_VO.class );
		VOconver.voConverTeacher(list);
        ////////////////////////////////////////////////////////////////////////
		//����
		 Collections.sort(list,new TeacherComparator());
		
//		Ems_Teacher_VO test = list.get(0);
//		String str = test.getiDnum();
//		System.out.println("�����"+str);�����Կ���ȡ�ñ��
		return list;
		
	}

	/**
	 * ȡ�����н�ʦ���������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutTeacher(){
		
		List list = this.getHibernateTemplate().find( "select max(idnum) from Ems_Teacher_VO t " );
		 if(list == null || list.size() <= 0)		
			 return null;
		return (String) list.get(0);
	}
	
	
	/**
	 * ȡ�������Ҫ�޸ĵĽ�ʦ����Ϣ
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

	
	
	/*******************************ѧ��***********************************************/
	

	
	
	/**
	 * �������е�ѧ��
	 * @return
	 */
	public List<StudentVO> listStudent() {
		List<Ems_Student_VO> sourelist = this.getHibernateTemplate().loadAll( Ems_Student_VO.class );
		
		List<StudentVO> destlist = VOconver.voConver(sourelist);
		////////////////////////////////////////////////////////////////////////
		//����
		 Collections.sort(destlist,new StudentComparator());
//		Ems_Teacher_VO test = list.get(0);
//		String str = test.getiDnum();
//		System.out.println("�����"+str);�����Կ���ȡ�ñ��
		return destlist;
		
	}

	/**
	 * ͨ��רҵ���Ƶõ�רҵ���
	 * @return
	 */
//	public String getProfession(String pro_name){
//		//SQL��䲻��ִ��
//		String sql = "select idnum from ProfessionVO t where pro_name= '" +pro_name +
//		"'";
//		List list = this.getHibernateTemplate().find(sql);
//		System.out.println("Dao��:" +(String) list.get(0));
//		return (String) list.get(0);
//		
//	
//	};
	
	/**
	 * ȡ�������Ҫ�޸ĵ�ѧ���ĵ���Ϣ
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
	
	
	
	
/*****************************ϵͳ����************************************/
	
	/***************��Ʒ����********************/
	/**
	 * �������е���Ʒ����
	 * @return
	 */
	public List<WorkerTypeVO> listWorkertype(){
		List<WorkerTypeVO> list = this.getHibernateTemplate().loadAll( WorkerTypeVO.class );
		////////////////////////////////////////////////////////////////////////
		//����
		 Collections.sort(list,new WorkerTypeVOComparator());
//		System.out.println("���ȣ�"+ list.size());
//		ProfessionVO test = list.get(0);
//		String str = test.getIdnum();
//		System.out.println("Daoרҵ��ţ�"+str);//�����Կ���ȡ�ñ��
		return list;
	};
	/**
	 * ȡ�����н�ʦ���������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutWorkstype(){
		//SQL��䲻��ִ��
		List list = this.getHibernateTemplate().find( "select max(idnum) from WorkerTypeVO t " );
		System.out.println("Dao�����:" +(String) list.get(0));
		 if(list == null || list.size() <= 0)		
			 return null;
		return (String) list.get(0);
	};
	
	
	/***************�γ�����********************/
	/**
	 * �������еĿγ�����
	 * @return
	 */	
	public List<ClassTypeVO> listClasstype(){
		List<ClassTypeVO> list = this.getHibernateTemplate().loadAll( ClassTypeVO.class );
////    ////////////////////////////////////////////////////////////////////
		//����
		 Collections.sort(list,new ClassTypeVOComparator());
//		System.out.println("���ȣ�"+ list.size());
//		ProfessionVO test = list.get(0);
//		String str = test.getIdnum();
//		System.out.println("Daoרҵ��ţ�"+str);//�����Կ���ȡ�ñ��
		return list;
	};
	
	/**
	 * ȡ�����н�ʦ���������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutClasstype(){
		//SQL��䲻��ִ��
		
		List list = this.getHibernateTemplate().find( "select max(idnum) from ClassTypeVO t" );
		 if(list == null || list.size() <= 0)		
			 return null;
		System.out.println("Dao�����:" +(String) list.get(0));
		
		return (String) list.get(0);
	};
	
	
	
	/***************רҵ����********************/
	/**
	 * �������е�רҵ
	 * @return
	 */
	public List<ProfessionVO> listProfession(){
		List<ProfessionVO> list = this.getHibernateTemplate().loadAll( ProfessionVO.class );
		 ////////////////////////////////////////////////////////////////////
		//����
		 Collections.sort(list,new ProfessionVOComparator());
//		System.out.println("���ȣ�"+ list.size());
//		ProfessionVO test = list.get(0);
//		String str = test.getIdnum();
//		System.out.println("Daoרҵ��ţ�"+str);//�����Կ���ȡ�ñ��
		return list;
	}
	/**
	 * ȡ������רҵ���������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutProfession(){
		//SQL��䲻��ִ��
		List list = this.getHibernateTemplate().find( "select max(idnum) from ProfessionVO t " );
		 if(list == null || list.size() <= 0)		
			 return null;
		System.out.println("Dao�����:" +(String) list.get(0));
		return (String) list.get(0);
	};
	
	
	/**************��ʦ��ӿγ�ʱ������************************************************************************************************/
	/**
	 * ��ȡ���ݿ�����ֹʱ����Ϣ
	 * @return
	 */
	public EMS_Systemset disTeacherAddTime(String idnum){
		HibernateTemplate hibernate = this.getHibernateTemplate();	
		//Ĭ����ʾ����0001��ʱ�䣬Ҳ������ʦ��ӿγ�ʱ������
		List list =  this.getSession().createQuery("from EMS_Systemset where idnum='"+idnum +"'").list();
		 if(list == null || list.size() <= 0)		
			 return null;
		return (EMS_Systemset) list.get(0);
	};			

	
	
	/**************��ʦ¼��ɼ�ʱ������*****************/
	/**
	 * 
	 * �ѷ���
	 * 
	 * ��ȡ���ݿ�����ֹʱ����Ϣ
	 * @return
	 */
	public EMS_Systemset disTeacherInputGrade(){
		HibernateTemplate hibernate = this.getHibernateTemplate();	
		//02�����趨�õı�׼
		List list =  this.getSession().createQuery("from EMS_Systemset where idnum='"+"02" +"'").list();
		 if(list == null || list.size() <= 0)		
			 return null;
		return (EMS_Systemset) list.get(0);
		
	};	
		
	
	
	/**************ѧ��ѡ��ʱ������*****************/
	/**
	 * 
	 * �ѷ���
	 * 
	 * ��ȡ���ݿ�����ֹʱ����Ϣ
	 * @return
	 */
	public EMS_Systemset disStudentTakeCourse(){
		HibernateTemplate hibernate = this.getHibernateTemplate();	
		//03�����趨�õı�׼
		List list =  this.getSession().createQuery("from EMS_Systemset where idnum='"+"03" +"'").list();
		
		

		
		return (EMS_Systemset) list.get(0);
		
		
		
	};	
	
	
	
	
	/******************************����������**********************************************/
	/*******�鿴��Ϣ***********/
	public List<ApplyModifyLogVO> disAditInfor(String state,String table){
		String sql = "from ApplyModifyLogVO where fortable='"+table+
		"' and modifyResult='"+state+"'";
		System.out.println("disAditInfor��sql:"+sql);
		List<ApplyModifyLogVO> list = this.getSession().createQuery(sql).list();
//			this.getHibernateTemplate().find(sql); 
			//
//		List a = this.getSession().createQuery(sql).list();
		// VOconver.voConverGb(list);
		return  list ;
	};
	

	
	/***********�õ���Ӧ��ŵ��������Ϣ��¼*********/
	public ApplyModifyLogVO getAuditInfor(String applyid){
	//	HibernateTemplate hibernate = this.getHibernateTemplate();
		List list = this.getSession().createQuery("from ApplyModifyLogVO where idnum='"+applyid+"'").list();
		 if(list == null || list.size() <= 0)		
			 return null;
		return (ApplyModifyLogVO)list.get(0);
	
	};
	
	
	/**
	 * �鿴��ʦ��Ϣ--��ҳ
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
		//����
		 Collections.sort(list,new TeacherComparator());
		
//		Ems_Teacher_VO test = list.get(0);
//		String str = test.getiDnum();
//		System.out.println("�����"+str);�����Կ���ȡ�ñ��
		return list;
	};

	/**
	 * �õ���ʦ��Ϣ��¼��
	 * @return
	 */
	public int getTeacherCounts(){
		String sql = "from Ems_Teacher_VO";
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
		
	};
	
	/**
	 * �鿴ѧ����Ϣ--��ҳ
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
		//����
		 Collections.sort(destlist,new StudentComparator());
//		Ems_Teacher_VO test = list.get(0);
//		String str = test.getiDnum();
//		System.out.println("�����"+str);�����Կ���ȡ�ñ��
		return destlist;
	};

	/**
	 * �õ�ѧ����Ϣ��¼��
	 * @return
	 */
	public int listStudentCounts(){
		String sql = "from Ems_Student_VO";
		List list=this.getHibernateTemplate().find(sql);
		return list.size();
	};
}


