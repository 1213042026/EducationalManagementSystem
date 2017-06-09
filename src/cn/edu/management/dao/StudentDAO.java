package cn.edu.management.dao;

import java.util.List;

import cn.edu.management.vo.showVO.ShowMessageVO;
import cn.edu.management.vo.showVO.showEmsClass;
import cn.edu.management.vo.voImpl.ApplyModifyLogVO;
import cn.edu.management.vo.voImpl.EMS_Class;
import cn.edu.management.vo.voImpl.EMS_CourseRecord;
import cn.edu.management.vo.voImpl.EMS_GraduateGrade;
import cn.edu.management.vo.voImpl.EMS_Graduation;
import cn.edu.management.vo.voImpl.Ems_Student_VO;
import cn.edu.management.vo.voImpl.Ems_Teacher_VO;
import cn.edu.management.vo.voImpl.MessageVO;
import cn.edu.management.vo.voImpl.ProfessionVO;

public interface StudentDAO extends DAO {
	
	
	/******************************************������Ϣά��***********************************************/
	/*****�鿴������Ϣ*****/
	public Ems_Student_VO  displayPersonInformation(String idnum);
	
	
	/****************************************** ��Ϣ��ѯ ***********************************************/
	/***** ��У�ɼ���ѯ *****/
	public String findScoreByCourserecord(String classId,Ems_Student_VO vo);
	
	/**����ѡ�������ѯ**/
	public List<EMS_Class> listMyAllCourse(String idnum, String grade,
			String profession,int pageNow);
	
	/** �õ�ѧ��ѡ�ε�ѧ�꣺**/
	public String selectCourseTime(EMS_Class classVO, Ems_Student_VO vo);
	
	/******************************************����ѡ��***********************************************/

	/**
	 * �ж������Ƿ���ѧ��ѡ��ʱ��
	 */
	public boolean isAvailable();
	
	/*****ѡ�޿γ�*****/
		/**
		 * �鿴��ѡ�޵Ŀγ�
		 * @param idnum
		 * @param grade
		 * @param profession 
		 * @return
		 */
	public List<EMS_Class> listCourse(String idnum, String grade, String profession);
	
	/**
	 * �õ��γ̼�¼������ID
	 * @return
	 */
	public String getMaxIdnumAboutCourseRecord();
	
	/**
	 * �鿴���Ѿ�ѡ�޵Ŀγ�
	 * @return
	 */
	public List<EMS_Class> listMyCourse(String idnum, String grade,String profession);

	/**
	 * ѡ��ѧ����һ��ѡ�μ�¼
	 * @param classId
	 * @param studentId
	 * @return
	 */
	public EMS_CourseRecord findCRecord(String classId, String studentId);
	
	
	

	/******************************************��ҵ��ƹ���***********************************************/
	/*****�鿴���б�ҵ���*****/
	public List<EMS_Graduation> selectGraduationProject(String idnum ,int pageNow);
	/**
	 * �õ���ҵ��Ʊ������е���ʦ
	 * @return
	 */
	public List<Ems_Teacher_VO> getGraduateTeacher();

	/**
	 * ͨ����ʦ���֣�רҵ������ҵ���
	 * @param teacherIdnum
	 * @param glevel
	 * @return
	 */
	public List<EMS_Graduation> indexGraduationProject(String teacherIdnum,String glevel,int pageNow);
	
	public int indexGraduationProject(String teacherIdnum,String glevel);

//	/**
//	 * �õ���ҵ��Ʊ������е�רҵ
//	 * @return
//	 */
//	public List<ProfessionVO> gerProfession();

//	/**
//	 * ѡ���ҵ��ƣ���ѧ��ѡ��ı�ҵ���׷�ӵ���ҵ��Ƽ�¼����
//	 * @return
//	 */
//	public void addGraduateProject(String graduationId, String studentId);

	/**
	 * ������ӱ�ҵ��Ƽ�¼���ʱ��ȡ�����ݿ��¼�е����ID������Ա���ܽ����ֶ�����ID��
	 * @return
	 */
	public String getMaxIdNumAboutGraduateGrade();

	/**
	 * �жϸ�ѧ���Ƿ��Ѿ�ѡ���˱�ҵ���
	 * @param studentId
	 * @return
	 */
	public boolean findStudentIdFromGraduateGrade(String studentId);

	/**
	 * �鿴�ҵı�ҵ���
	 * @param studentId
	 * @return
	 */
	public EMS_GraduateGrade listMyGraduationProject(String studentId);

	/**
	 * ͨ����ҵ��Ʊ�Ŷ�ȡ������¼
	 * @param gidnum
	 * @return
	 */
	public EMS_Graduation findGraduationById(String gidnum);

	
	/**
	 * ͨ��רҵ�Ų�������רҵ��¼
	 * @param proName
	 * @return
	 */
	public ProfessionVO findProfessionByProName(String proName);
	
	
	/**
	 * �鿴�Ƿ��Ѿ�����������Ŀ�ı�ҵ��ƣ������������ѡ���ҵ���
	 * @param userId
	 * @return
	 */
	public String isApply(String userId);
	

	/**
	 * �鿴ѧ����ѡ��ҵ��Ƶĵ�ʦ
	 * @return
	 */
	public Ems_Teacher_VO findTName(EMS_GraduateGrade gvo);
	
/********************************************���԰����************************************************/
	/**
	 * ����������Ա��ʱ��ȡ�����ݿ��¼�е����ID
	 * @return
	 */
	public String getMaxIdNumAboutMessage();
	
	/**
	 * ��ʾѧ��������Ϣ
	 * @param idnum
	 * @return
	 */
	public List<MessageVO> showMessage(String idnum, String status);
/********************************************������Ϣ�����޸�************************************************/
	/**
	 * �鿴������Ϣ
	 * @return
	 */
	public List<ApplyModifyLogVO> displayAlreadyApplyStatus(String idnum);
	/**
	 * �鿴��ҵ���ѡ��������Ϣ
	 * @return
	 */
	public List<ApplyModifyLogVO> displayAlreadyApplyStatusSelf(String idnum);


	/**
	 * ����Ƿ��������״̬ʱ������С�
	 * @param idnum
	 * @param string 
	 * @return
	 */
	public boolean checkApply(String idnum, String string);

	/**
	 * ͨ���γ̺Ų��������γ̼�¼
	 * @param classId
	 * @return
	 */
	public EMS_Class findClassById(String classId);

	//�ж������Ƿ���ѡ���ҵ���ʱ��
	public boolean isGraAvailable();

	/**
	 * �ж��Ƿ��Ѿ����������ѡ��
	 * @return
	 */
	public boolean isApplying(String remark);

	/**
	 * �õ���ҵ��Ƽ�¼��
	 */
	public int getGrauationCounts();

	/**
	 * ͨ��ѧ���ѧ�ڲ�ѯ�γ�
	 * @param xnName
	 * @param term
	 * @return
	 */
	public List<EMS_Class> indexCourse(String xnName, String term,String idnum);


	public int getCourseCounts(String idnum);


	public int getScoreCounts(String idnum);


	public boolean isGraMaAvailable();


	public int getGraCount();


	public String findTeacherById(String projectId);


	public boolean wordToHtml(String docfile, String htmlfile);


	public List<EMS_Class> listMyAllCourse1(String idnum, String grade,
			String profession, int pageNow);

	









	

	



	

	



	

	

}
