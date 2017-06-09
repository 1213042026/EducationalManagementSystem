package cn.edu.management.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.edu.management.comm.Page;
import cn.edu.management.dao.StudentDAO;
import cn.edu.management.service.StudentService;
import cn.edu.management.service.SuperService;
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

@Component("studentService")
public class StudentServiceImpl extends SuperService implements StudentService {
	private StudentDAO studentDao;

	@Resource
	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}
	
	
	/******************************************������Ϣά��***********************************************/
	/*****�鿴������Ϣ*****/
	public Ems_Student_VO  displayPersonInformation(String idnum) {
		
		return this.studentDao.displayPersonInformation(idnum);
	}
	
	/**
	 * �鿴��ҵ���ѡ��������Ϣ
	 * @return
	 */
	public List<ApplyModifyLogVO> displayAlreadyApplyStatusSelf(String idnum){
		return this.studentDao.displayAlreadyApplyStatusSelf(idnum);
	};

	
	/****************************************** ��Ϣ��ѯ ***********************************************/
	/***** ��У�ɼ���ѯ *****/
	public String findScoreByCourserecord(String classId,Ems_Student_VO vo) {
		// TODO Auto-generated method stub
		return this.studentDao.findScoreByCourserecord(classId,vo);
	}

	/**����ѡ�������ѯ**/
	public List<EMS_Class> listMyAllCourse(String idnum, String grade,
			String profession,int pageNow) {
		// TODO Auto-generated method stub
		return this.studentDao.listMyAllCourse( idnum, grade,profession,pageNow);
	}

	/** �õ�ѧ��ѡ�ε�ѧ�꣺**/
	public String selectCourseTime(EMS_Class classVO, Ems_Student_VO vo) {
		// TODO Auto-generated method stub
		return this.studentDao.selectCourseTime(classVO,vo);
	}
	
	
	/******************************************����ѡ��***********************************************/

	/**
	 * �ж������Ƿ���ѧ��ѡ��ʱ��
	 */
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return this.studentDao.isAvailable();
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
		return this.studentDao.listCourse( idnum,  grade,profession);
	}

	/**
	 * �õ��γ̼�¼������ID
	 * @return
	 */
	public String getMaxIdnumAboutCourseRecord() {
		// TODO Auto-generated method stub
		return this.studentDao.getMaxIdnumAboutCourseRecord();
	}

	/**
	 * �鿴���Ѿ�ѡ�޵Ŀγ�
	 * @return
	 */
	public List<EMS_Class> listMyCourse(String idnum, String grade,String profession) {
		// TODO Auto-generated method stub
		return this.studentDao.listMyCourse( idnum, grade,profession);
	}

	/**
	 * ѡ��ѧ����һ��ѡ�μ�¼
	 * @param classId
	 * @param studentId
	 * @return
	 */
	public EMS_CourseRecord findCRecord(String classId, String studentId) {
		// TODO Auto-generated method stub
		return this.studentDao.findCRecord(classId, studentId);
	}
	
	
	
	
	/******************************************��ҵ��ƹ���***********************************************/
	/*****�鿴���б�ҵ���*****/
	public List<EMS_Graduation> selectGraduationProject(String idnum,int pageNow) {
		// TODO Auto-generated method stub
		return this.studentDao.selectGraduationProject(idnum,pageNow);
	}
	
	/**
	 * �õ���ҵ��Ʊ������е���ʦ
	 * @return
	 */
	public List<Ems_Teacher_VO> getGraduateTeacher()
	{
		return this.studentDao.getGraduateTeacher();
	}

	/**
	 * ͨ����ʦ���֣�רҵ������ҵ���
	 * @param teacherIdnum
	 * @param profIdnum
	 * @return
	 */
	public List<EMS_Graduation> indexGraduationProject(String teacherIdnum, String profIdnum,int pageNow) {
		// TODO Auto-generated method stub
		return this.studentDao.indexGraduationProject(teacherIdnum,profIdnum,pageNow);
	}
	
	public int indexGraduationProject(String teacherIdnum, String profIdnum) {
		// TODO Auto-generated method stub
		return this.studentDao.indexGraduationProject(teacherIdnum,profIdnum);
	}

//	/**
//	 * �õ���ҵ��Ʊ������е�רҵ
//	 * @return
//	 */
//	public List<ProfessionVO> getProfession() {
//		// TODO Auto-generated method stub
//		return this.studentDao.gerProfession();
//	}

//	/**
//	 * ѡ���ҵ��ƣ���ѧ��ѡ��ı�ҵ���׷�ӵ���ҵ��Ƽ�¼����
//	 * @return
//	 */
//	public void addGraduateProject(String graduationId, String studentId) {
//		// TODO Auto-generated method stub
//		this.studentDao.addGraduateProject(graduationId,studentId);
//	}

	/**
	 * ������ӱ�ҵ��Ƽ�¼���ʱ��ȡ�����ݿ��¼�е����ID������Ա���ܽ����ֶ�����ID��
	 * @return
	 */
	public String getMaxIdNumAboutGraduateGrade() {
		// TODO Auto-generated method stub
		return this.studentDao.getMaxIdNumAboutGraduateGrade();
	}

	/**
	 * �жϸ�ѧ���Ƿ��Ѿ�ѡ���˱�ҵ���
	 * @param studentId
	 * @return
	 */
	public boolean findStudentIdFromGraduateGrade(String studentId) {
		// TODO Auto-generated method stub
		return this.studentDao.findStudentIdFromGraduateGrade(studentId);
	}

	/**
	 * �鿴�ҵı�ҵ���
	 * @param studentId
	 * @return
	 */
	public EMS_GraduateGrade listMyGraduationProject(String studentId) {
		// TODO Auto-generated method stub
		return this.studentDao.listMyGraduationProject( studentId);
	}

	/**
	 * ͨ����ҵ��Ʊ�Ŷ�ȡ������¼
	 * @param gidnum
	 * @return
	 */
	public EMS_Graduation findGraduationById(String gidnum) {
		// TODO Auto-generated method stub
		return this.studentDao.findGraduationById(gidnum);
	}

	/**
	 * ͨ��רҵ�Ų�������רҵ��¼
	 * @param proName
	 * @return
	 */
	public ProfessionVO findProfessionByProName(String proName) {
		// TODO Auto-generated method stub
		return this.studentDao.findProfessionByProName( proName);
	}


	/**
	 * �鿴�Ƿ��Ѿ�����������Ŀ�ı�ҵ��ƣ������������ѡ���ҵ���
	 * @param userId
	 * @return
	 */
	public String isApply(String userId) {
		// TODO Auto-generated method stub
		return this.studentDao.isApply(userId);
	}


	/**
	 * �鿴ѧ����ѡ��ҵ��Ƶĵ�ʦ
	 * @return
	 */
	public Ems_Teacher_VO findTName(EMS_GraduateGrade gvo) {
		// TODO Auto-generated method stub
		return this.studentDao.findTName(gvo);
	}

/********************************************���԰����************************************************/
	/**
	 * ����������Ա��ʱ��ȡ�����ݿ��¼�е����ID
	 * @return
	 */
	public String getMaxIdNumAboutMessage() {
		// TODO Auto-generated method stub
		return this.studentDao.getMaxIdNumAboutMessage();
	}

	/**
	 * ��ʾѧ��������Ϣ
	 * @param idnum
	 * @return
	 */
	public List<MessageVO> showMessage(String idnum , String status) {
		// TODO Auto-generated method stub
		return this.studentDao.showMessage(idnum,status);
	}

	
/********************************************������Ϣ�����޸�************************************************/
	/**
	 * �鿴������Ϣ
	 * @return
	 */
	public List<ApplyModifyLogVO> displayAlreadyApplyStatus(String idnum) {
		// TODO Auto-generated method stub
		return this.studentDao.displayAlreadyApplyStatus(idnum);
	}


	/**
	 * ����Ƿ��������״̬ʱ������С�
	 * @param idnum
	 * @param string 
	 * @return
	 */
	public boolean checkApply(String idnum, String string) {
		// TODO Auto-generated method stub
		return this.studentDao.checkApply(idnum,string);
	}

	/**
	 * ͨ���γ̺Ų��������γ̼�¼
	 * @param classId
	 * @return
	 */
	public EMS_Class findClassById(String classId) {
		// TODO Auto-generated method stub
		return this.studentDao.findClassById( classId);
	}

	//�ж������Ƿ���ѡ���ҵ���ʱ��
	public boolean isGraAvailable() {
		// TODO Auto-generated method stub
		return this.studentDao.isGraAvailable();
	}

	public boolean isGraMaAvailable() {
		// TODO Auto-generated method stub
		return this.studentDao.isGraMaAvailable();
	}
	
	/**
	 * �ж��Ƿ��Ѿ����������ѡ��
	 * @return
	 */
	public boolean isApplying(String remark) {
		// TODO Auto-generated method stub
		return this.studentDao.isApplying(remark);
	}

	/**
	 * �õ���ҵ��Ƽ�¼��
	 */
	public int getGrauationCounts() {
		// TODO Auto-generated method stub
		return this.studentDao.getGrauationCounts();
	}

	/**
	 * ͨ��ѧ���ѧ�ڲ�ѯ�γ�
	 * @param xnName
	 * @param term
	 * @return
	 */
	public List<EMS_Class> indexCourse(String xnName, String term,String idnum) {
		// TODO Auto-generated method stub
		return this.studentDao.indexCourse(xnName, term ,idnum);
	}


	public int getCourseCounts(String idnum) {
		// TODO Auto-generated method stub
		return this.studentDao.getCourseCounts(idnum);
	}


	public int getScoreCounts(String idnum) {
		// TODO Auto-generated method stub
		return this.studentDao.getScoreCounts(idnum);
	}


	public int getGraCount() {
		// TODO Auto-generated method stub
		return this.studentDao.getGraCount();
	}


	public String findTeacherById(String projectId) {
		// TODO Auto-generated method stub
		return this.studentDao.findTeacherById(projectId);
	}


	public boolean wordToHtml(String docfile, String htmlfile) {
		// TODO Auto-generated method stub
		return this.studentDao.wordToHtml(docfile, htmlfile);
	}


	public List<EMS_Class> listMyAllCourse1(String idnum, String grade,
			String profession, int pageNow) {
		// TODO Auto-generated method stub
		return this.studentDao.listMyAllCourse1(idnum, grade, profession, pageNow);
	}


	



	

	

	
	
	

	

	
	
	



	
}
