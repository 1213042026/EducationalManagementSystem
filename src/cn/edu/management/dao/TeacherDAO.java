package cn.edu.management.dao;

import java.util.List;

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
import cn.edu.management.vo.voImpl.Ems_Teacher_VO;
import cn.edu.management.vo.voImpl.MessageVO;

public interface TeacherDAO extends DAO {
	/****************************************** ������Ϣά�� ***********************************************/
	/***** �鿴������Ϣ *****/
	public Ems_Teacher_VO displayPersonInformation(String idnum);

	/****************************************** ��ҵ��ƹ��� ***********************************************/
	/***** �鿴����ӱ�ҵ��� *****/
	public List<EMS_Graduation> displayAlreadyAdd(String idnum);

	/**
	 * ȡ�����б�ҵ����б������һ��,�Ա���ȡ����һ��
	 * 
	 * @return
	 */
	public String getMaxIdNumAboutGraduate();

	/**
	 * ȡ�����б�ҵ����б������һ��,�Ա���ȡ����һ��
	 * 
	 * @return
	 */
	public String getMaxIdNumAboutApplyModify();

	// �ҳ���Ӧ��������¼
	public List<ApplyModifyLogVO> displayAlreadyApplyStatus(String idnum);

	/**
	 * ���������밴ťʱ
	 * 
	 * �������¼������Ƿ��������ʦ�ļ�¼����˽��Ϊ������С�
	 */
	public ApplyModifyLogVO addModifyApplycheck(String applyUserId);

	/**
	 * �õ���ͱ�ŵı�ҵ���
	 * 
	 * @param idnum
	 * @return
	 */
	public EMS_Graduation getModifyGraduationInfo(String idnum);

	/**
	 * ��ѯ��ҵ���ѧ����Ϣ
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearch(String teacherid);

	/**
	 * ѧ���ļ�����
	 * 
	 * @return
	 */
	public List<StudentGraduationWorksMange> studentFileManage(String teacherid);

	/**
	 * ������ ems_graduation ems_graduationgrade ems_student
	 */
	public List<Object[]> graStuGragrade(String teacherid);

	// ��ʾҪ��˵ı�ҵ�����Ŀ
	public List<disAuditGraduationTitle> disAuditGraduationTitle(String tidnum);

	// �õ�����Ŵӱ�ҵ��Ƽ�¼����
	public String getMaxIdNumAboutGraduateGrade();

	// �õ���˼�¼
	public List<disAuditGraduationResult> disAditInfor(String tidnum);

	/**
	 * ѧ����ҵ��Ƴɼ�¼��
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeInput(String teacherid);

	/**
	 * �õ�ָ����ҵ��Ƽ�¼���еļ�¼
	 * 
	 * @param ggidnum
	 * @return
	 */
	public EMS_GraduateGrade getSpecifyGraduationG(String ggidnum);

	/**
	 * ѧ����ҵ��Ƴɼ�¼��
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeupdate(String teacherid);

	/**
	 * ��ʾ����ӿγ�
	 * 
	 * @param teacherid
	 * @return
	 */
	public List<showAlreadyAddCourse> displayAlreadyAddCourse(String teacherid);

	/**
	 * �õ��γ̱������
	 */
	public String getMaxIdNumAboutCourse();

	/**
	 * �õ��γ�����
	 */
	public List<ClassTypeVO> listClassType();

	/**
	 * �õ���Ӧ��ŵĿγ�
	 */
	public EMS_Class getModifyCourseInfo(String cidnum);

	/**
	 * ��ѯѡ��ѧ����Ϣ
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearchCourse(
			String teacherid);

	/**
	 * ��ѯѡ��ѧ����Ϣ�ɼ�
	 */
	public List<Object[]> graStuGragradeCourse(String teacherid);

	/**
	 * ѧ����ҵ��Ƴɼ�¼��
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeInput(String teacherid);

	/**
	 * �õ���Ӧ��ŵĿγ̼�¼
	 */
	public EMS_CourseRecord getSpecifyCourseRecord(String cidnum);

	/**
	 * ѧ���γ̳ɼ��޸�
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeupdate(
			String teacherid);

	/**
	 * ��ʾ�ѻظ�����
	 */
	public List<ShowMessageVO> disAlreadyReplyMessage(String tidnum,
			String status);

	/**
	 * �õ�ָ����ŵ�������Ϣ
	 */
	public MessageVO getMessageVO(String idnum);

	/**
	 * �õ���ҵ��Ƽ�¼��
	 */
	public int getGrauationCounts(String teacherid);

	/***** �鿴���б�ҵ��� *****/
	public List<EMS_Graduation> displayAlreadyAddPage(String idnum, int pageNow);

	/**
	 * ��ҳ--��ѯ��ҵ���ѧ����Ϣ
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearchPage(
			String teacherid, int nowPage);

	/**
	 * �õ���ҵ��Ʊ��¼��
	 * 
	 * @return
	 */
	public int studentInforSearchCounts(String teacherid);

	/**
	 * �õ���ҵ��Ʊ��¼��
	 * 
	 * @return
	 */
	public int studentFileManageCounts(String teacherid);

	/**
	 * ѧ���ļ����� -��ҳ
	 * 
	 * @return
	 */
	public List<StudentGraduationWorksMange> studentFileManagePage(
			String teacherid, int nowPage);

	/**
	 * �õ�ѧ���ɼ���ѯ��¼��
	 * 
	 * @return
	 */
	public int studentGradeSearchCounts(String teacherid);

	/**
	 * ��ѯѡ��ѧ����Ϣ�ɼ�--��ҳ
	 */
	public List<Object[]> graStuGragradeCourse(String teacherid, int nowPage);

	/**
	 * ������ ems_graduation ems_graduationgrade ems_student
	 */
	public List<Object[]> graStuGragrade(String teacherid, int nowPage);

	/**
	 * �õ�ѧ���������¼��
	 * 
	 * @return
	 */
	public int studentGradeInputCounts(String teacherid);

	/**
	 * ѧ����ҵ��Ƴɼ�¼��--��ҳ
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeInput(String teacherid,
			int nowPage);

	/**
	 * �õ�ѧ���ɼ��޸ļ�¼��
	 * 
	 * @return
	 */
	public int studentGradeupdateCounts(String teacherid);

	/**
	 * ѧ����ҵ��Ƴɼ��޸�-��ҳ
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeupdate(String teacherid,
			int nowPage);

	/**
	 * ��ʾ����ӿγ�--��ҳ
	 * 
	 * @param teacherid
	 * @return
	 */
	public List<showAlreadyAddCourse> displayAlreadyAddCourse(String teacherid,
			int nowPage);

	/**
	 * �õ��γ̱��¼��
	 * 
	 * @return
	 */
	public int CourseCounts(String teacherid);

	/**
	 * �õ�ѡ ��ѧ����Ϣ��¼��
	 * 
	 * @return
	 */
	public int studentInforSearchCourseCounts(String teacherid);

	/**
	 * ��ѯѡ��ѧ����Ϣ--��ҳ
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearchCourse(
			String teacherid, int nowPage);

	/**
	 * �õ�ѡ ��ѧ���ɼ���¼��
	 * 
	 * @return
	 */
	public int studentGradeSearchCourseCounts(String teacherid);

	/**
	 * ��ѯ¼��γ̳ɼ�--��ҳ
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeInput(String teacherid,int nowPage);

	/**
	 * �õ�¼��γ̳ɼ���¼��
	 * @return
	 */
	public int studentCourseGradeInputCounts(String teacherid);
	
	/**
	 * ��ѯ�޸Ŀγ̳ɼ�--��ҳ
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeupdate(String teacherid,int nowPage);

	/**
	 * �õ��޸Ŀγ̳ɼ���¼��
	 * @return
	 */
	public int studentCourseGradeupdateCounts(String teacherid);
	
	
	/**
	 * �鿴������״̬--��ҳ
	 * @return
	 */
	public List<ApplyModifyLogVO> displayAlreadyApplyStatus(String teacherid,int nowPage);

	/**
	 * �õ��鿴������״̬��¼��
	 * @return
	 */	
	public int displayAlreadyApplyStatusCounts(String teacherid);

	/**
	 * �鿴�ѻظ�����--��ҳ
	 * @return
	 */
	public List<ShowMessageVO> disAlreadyReplyMessage(String teacherid,String status,int nowPage);

	/**
	 * �õ��ѻظ����Լ�¼��
	 * @return
	 */
	public int disAlreadyReplyMessageCounts(String teacherid,String status);
	

	/**
	 * ϵͳʱ������
	 * @param status
	 * @return
	 */
	public boolean checkTime(String status);
	
	/**
	 * ��ѯѡ��ѧ����Ϣ�ɼ�--����

	 */
	public List<Object[]>  graStuGragradeCourse(String teacherid,String nowPage,int nowPag);
	/**
	 * �ʺŲ���
	 * @return
	 */
	public boolean checkGname(String account);
	
}
