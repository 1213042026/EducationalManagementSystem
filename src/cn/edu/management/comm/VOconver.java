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
 * �����ڸ�VO��֮��ת��
 * 
 * Ҳ���ǽ�����Ӧ��VO�࣬תΪ������ʾ��VO�࣬һ�������ھ�������յı���
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
	 * @param source   ��ת����VO��
	 * @return         Ŀ��VO��
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
			
//			System.out.println("רҵ�ţ�"+
//				 if(	sourStu.getProfession().getPro_name() == null)
//					 System.out.println("��");
//				 else
//					 System.out.println("����");
//				.getPro_name());
			
			destStu.setProf_Name(sourStu.getProfession().getPro_name());// �õ������רҵ��
			destStu.setAddress(sourStu.getAddress());
			destStu.setNation(sourStu.getNation());
			destStu.setEntranceDate(sourStu.getEntranceDate());
			destStu.setFlag(sourStu.getFlag());

			studentvo.add(destStu);// ����뼯��
		}

		return studentvo;
	}
	
	
	/**
	 * ����ҵ��Ʊ������--��ʾ��ʦ����ӵı�ҵ��Ƶ���ʾVO��
	 * @param source
	 * @return
	 */
	public static List<EMS_GraduationA> voConverGa(List<EMS_Graduation> source) {

		List<EMS_GraduationA> graduationtvo = new ArrayList<EMS_GraduationA>();

		for (int i = 0; i <source.size(); i++) {
			EMS_GraduationA destStu = new EMS_GraduationA();
			EMS_Graduation sourStu = source.get(i);

//			private String idnum;// varchar2(20), --���P K
//			private String gname; // gname varchar2(100),      --��ҵ�������
//			private String gyear; // gyear varchar2(10),     --ѧ��
//			private String term;// term varchar2(10),      --ѧ��
//			private String grade;// grade varchar2(20),      --�����꼶
//			private String professionId; // professionId    --����רҵF K
//			private String gcount; // gcount varchar2(10),         --��ѡ����
//			private String rcount; //  rcount varchar2(10),        --ʣ������
//			private Timestamp recordTime; // recordTime timestamp,    --¼��ʱ��
//			private String remarks; // remark varchar2(300),   --��ע
			
			destStu.setIdnum(sourStu.getIdnum());
			destStu.setGname(sourStu.getGname());
			
			//�õ�ѧ��
			String gyear = DateConventer.timestampToStrYear(sourStu.getRecordTime());
			destStu.setGyear(gyear);

//			Integer ggrade = Integer.valueOf(gyear);
//			
//			destStu.setGrade(sourStu.getGrade());
//			destStu.setProfessionId(sourStu.getProfessionId().getPro_name());//���
//			destStu.setProf_Name(sourStu.getProfession().getPro_name());// �õ������רҵ��
			destStu.setGlevel(sourStu.getGlevel());
			destStu.setFlag(sourStu.getFlag());
			
			destStu.setGcount(sourStu.getGcount());
			destStu.setRcount(sourStu.getRcount());
			 destStu.setRecordTime(DateConventer.timestampToStrNo(sourStu.getRecordTime()));
			destStu.setRemarks(sourStu.getRemark());
           
            graduationtvo.add(destStu);// ����뼯��
		}

		return graduationtvo;
	}
	
	
	/**
	 * ����ҵ��Ʊ������--��ʾѧ���Ա�ҵ��ƽ���ѡ�����ʾVO��
	 * @param source
	 * @return
	 */
	public static List<EMS_GraduationB> voConverGaS(List<EMS_Graduation> source) {

		List<EMS_GraduationB> graduationtvo = new ArrayList<EMS_GraduationB>();

		for (int i = 0; i <source.size(); i++) {
			EMS_GraduationB destStu = new EMS_GraduationB();
			EMS_Graduation sourStu = (EMS_Graduation)source.get(i);

//			private String idnum;// varchar2(20), --���P K
//			private String gname; // gname varchar2(100),      --��ҵ�������
//			private String teacherId;// teacherId
//			private String gyear; // gyear varchar2(10),     --ѧ��
//			private String flag;// varchar(20),                     --��־��ѧ���������student,��ʦ����teacher��
//			private String  glevel ;//varchar(20),                   --���ף�һ�㣬����  
//			private String grade;// grade varchar2(20),      --�����꼶
//			private String professionId; // professionId    --����רҵF K
//			private String gcount; // gcount varchar2(10),         --��ѡ����
//			private String rcount; //  rcount varchar2(10),        --ʣ������
//			private String recordTime; // recordTime timestamp,    --¼��ʱ��
//			private String remarks; // remark varchar2(300),   --��ע
			
			destStu.setIdnum(sourStu.getIdnum());
			destStu.setGname(sourStu.getGname());
			
			//�õ�ѧ��
			String gyear = DateConventer.timestampToStrYear(sourStu.getRecordTime());
			destStu.setGyear(gyear);

//			Integer ggrade = Integer.valueOf(gyear);
//			
//			destStu.setGrade(sourStu.getGrade());
			
			destStu.setTeacherId(sourStu.getTeacherId().getName());
			
//destStu.setProfessionId(sourStu.getProfessionId().getPro_name());//���
//			destStu.setProf_Name(sourStu.getProfession().getPro_name());// �õ������רҵ��
			destStu.setGlevel(sourStu.getGlevel());
			destStu.setFlag(sourStu.getFlag());
			destStu.setGcount(sourStu.getGcount());
			destStu.setRcount(sourStu.getRcount());
			 destStu.setRecordTime(DateConventer.timestampToStrNo(sourStu.getRecordTime()));
			destStu.setRemarks(sourStu.getRemark());
           
            graduationtvo.add(destStu);// ����뼯��
		}

		return graduationtvo;
	}
	
	
	/**
	 * ����ҵ��Ʊ������--��ʾѧ���鿴��ѡ��ı�ҵ�����ʾVO��
	 * @param source
	 * @return
	 */
	public static EMS_GraduateGradeA voConverMyProject(EMS_GraduateGrade source) {

		EMS_GraduateGradeA destGra = new EMS_GraduateGradeA();
		EMS_GraduateGrade sourceGra = source;

//		private String gname; // ��ҵ������� FK
//		private String grade; // varchar2(3), --�ɼ�
//		private String teacherName; //ָ����ʦ
//		Timestamp gxtime;//ѡ��ʱ��
//		private String remark; // varchar2(500), --��ע


		//���ñ�ҵ��Ʊ��
		destGra.setGidnum(sourceGra.getGidnum().getIdnum());
		//���ñ�ҵ�������
		destGra.setGname(sourceGra.getGidnum().getGname());
		//���óɼ�
		destGra.setGrade(sourceGra.getGrade());
		//����ָ����ʦ
		destGra.setTeacherName(sourceGra.getGidnum().getTeacherId().getName());
		//����ѡ��ʱ��
		destGra.setGxtime(DateConventer.timestampToStr(sourceGra.getGxtime()));
		//���ñ�ע
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
			classVO.add(destVO);// ����뼯��
		}
		return classVO;
	}
	
	

	/**
	 * �������¼�������--��ʾ�����ѧ����Ϣ����ʾVO��
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
			destStu.setRemarks(sourStu.getRemarks());//��ҵѡ����˼�¼
			destStu.setModifyResult(sourStu.getModifyResult());
			destStu.setRemark(sourStu.getRemark());
			//           
            graduationtvo.add(destStu);// ����뼯��
		}

		return graduationtvo;
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * @param source   ��ת����VO��
	 * @return         Ŀ��VO��
	 */
	public static List<showTeacher_VO> voConverTeacher(List<Ems_Teacher_VO> source) {

//	     idnum varchar2(20),         --��ʦ��ţ�˳��������Ϊ����pk
//	       password varchar2(20),      --����
//	       name varchar2(100),         --����
//	       name_temp varchar2(100),    --��������
//	       sex varchar2(2),            --�Ա�
//	       idcard varchar2(18),        --���֤����
//	       idcard_temp varchar2(18),   --���֤���뱸��
//	       address varchar2(200),      --��ͥסַ
//	       address_temp varchar2(200), --��ͥסַ����
//	       profession  varchar2(20),  --רҵ��
//	       nation varchar2(50),        --����
//	       scientific varchar2(5),     --ѧ��  
//	       title varchar2(5),          --ְ�� 
//	       flag number default 0,      --�޸ı�־����ʱû��
	      
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
			destStu.setProfession(sourStu.getProfession().getPro_name());// �õ������רҵ��
			
//			System.out.println("רҵ�ţ�"+
//				 if(	sourStu.getProfession().getPro_name() == null)
//					 System.out.println("��");
//				 else
//					 System.out.println("����");
//				.getPro_name());
			
			
			destStu.setAddress(sourStu.getAddress());
			destStu.setNation(sourStu.getNation());
			destStu.setScientific(sourStu.getScientific());
			destStu.setTitle(sourStu.getTitle());
			destStu.setFlag(sourStu.getFlag());

			teacherVO.add(destStu);// ����뼯��
		}

		return teacherVO;
	}
	

	/**
	 *  ������ʾ����ӿγ̽���
	 * 
	 * 
	 * @param source   ��ת����VO��
	 * @return         Ŀ��VO��
	 */
	public static List<showAlreadyAddCourse> voConverdisAlreadyCourse(List<EMS_Class> source) {

//	     idnum varchar2(20),         --��ʦ��ţ�˳��������Ϊ����pk
//	       password varchar2(20),      --����
//	       name varchar2(100),         --����
//	       name_temp varchar2(100),    --��������
//	       sex varchar2(2),            --�Ա�
//	       idcard varchar2(18),        --���֤����
//	       idcard_temp varchar2(18),   --���֤���뱸��
//	       address varchar2(200),      --��ͥסַ
//	       address_temp varchar2(200), --��ͥסַ����
//	       profession  varchar2(20),  --רҵ��
//	       nation varchar2(50),        --����
//	       scientific varchar2(5),     --ѧ��  
//	       title varchar2(5),          --ְ�� 
//	       flag number default 0,      --�޸ı�־����ʱû��
	      
		List<showAlreadyAddCourse> courseVO = new ArrayList<showAlreadyAddCourse>();

		for (int i = 0; i <source.size(); i++) {
			showAlreadyAddCourse destStu = new showAlreadyAddCourse();
			EMS_Class sourStu = source.get(i);

//			private String classId ; // varchar2(20), --�γ̱�ţУ�
//		    private String className ; //varchar2(100), --�γ�����
//			private String classType ;//�γ�������//FK
//			private String teacherId ; //��ʦ����    //FK
//		    private String term ; // varchar2(10),  --ѧ��	
//		    private String grade ; // varchar2(20),  --�����꼶    
//			private String  professionId ;//רҵ����	FK
//		    private String  kCount ;//varchar2(10),                  --��ѡ����
//		    private String sCount;// varchar2(10),                  --ʣ������    
//		    private String recordTime ; //timestamp,  --¼��γ�ʱ��    
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
		    	destStu.setProfessionId("����ѡ�޿�");
		    	
		    }
		    destStu.setKkCount(sourStu.getkCount());
		    destStu.setSsCount(sourStu.getsCount());
		    destStu.setRecordTime( DateConventer.timestampToStr(sourStu.getRecordTime()));
            destStu.setRemark(sourStu.getRemark());

			courseVO.add(destStu);// ����뼯��
		}

		return courseVO;
	}
	
	/**
	 *  ������ʾ�ѻظ�����
	 * 
	 * 
	 * @param source   ��ת����VO��
	 * @return         Ŀ��VO��
	 */
	public static List<ShowMessageVO> voConverdisAlreadyReplyMessage(List<MessageVO> source) {

//		  idnum    varchar2(20) primary key,  ��--��� PK
//		  sidnum   varchar2(20), �������������� --ѧ����� FK
//		  tidnum   varchar2(20),    ������������--��ʦ��� FK
//		  title  varchar2(500),   ������������  --��������
//		  content  varchar2(500),   ������������--��������
//		  mtime    timestamp,       ������������--����ʱ��
//		  status   varchar2(20),    ������������--�ظ�״̬(δ�ظ����ѻظ�)
//		  rcontent varchar2(500),   ������������--�ظ�����
//		  rtime    timestamp,       ������������--�ظ�ʱ��
//		  remark   varchar(100),                --��ע   
	      
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

			courseVO.add(destStu);// ����뼯��
		}

		return courseVO;
	}
	
	
	

}
