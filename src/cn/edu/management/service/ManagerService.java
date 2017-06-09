package cn.edu.management.service;

import java.util.List;

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

public interface ManagerService extends Service {
	/**
	 * add user 
	 * @param vo
	 * @return
	 */
	public boolean add( UserVO vo );
	/**
	 * �������еĹ���Ա
	 * @return
	 */
	public List<Ems_Manager_VO> listManager();
	/**
	 * ȡ�����й���Ա���������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutManager();
	/**
	 * ȡ�������Ҫ�޸ĵĹ���Ա����Ϣ
	 * @return
	 */
	public Ems_Manager_VO getModifyManagerInfo( String idnum  );
	
	/********************************��ʦ********************************************/
	/**
	 * �������еĽ�ʦ
	 * @return
	 */
	public List<Ems_Teacher_VO> listTeacher();
	
	/**
	 * ȡ�����н�ʦ���������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutTeacher();
	/**
	 * ȡ�������Ҫ�޸ĵĽ�ʦ����Ϣ
	 * @return
	 */
	public Ems_Teacher_VO getModifyTeacherInfo( String idnum  );
	
	
	/********************************ѧ��********************************************/
	/**
	 * �������е�ѧ��
	 * @return
	 */
	public List<StudentVO> listStudent();	
	

	/**
	 * ȡ�������Ҫ�޸ĵ�ѧ���ĵ���Ϣ
	 * @return
	 */
	public Ems_Student_VO getModifyStudentInfo( String idnum  );
	
	
	
	
	/********************************ϵͳ����********************************************/
	
	/******************��Ʒ����*************************/
	/**
	 * �������е���Ʒ����
	 * @return
	 */
	public List<WorkerTypeVO> listWorkertype();
	
	/**
	 * ȡ��������Ʒ����������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutWorkstype();
	
	
	
	/******************�γ�����*************************/
	/**
	 * �������еĿγ�����
	 * @return
	 */
	public List<ClassTypeVO> listClasstype();
	
	/**
	 * ȡ�����пγ�����������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutClasstype();
	
	
	
	
	/******************רҵ����*************************/
	/**
	 * �������е�רҵ
	 * @return
	 */
	public List<ProfessionVO> listProfession();
	
	/**
	 * ȡ������רҵ���������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutProfession();
	
	
	
	
	/**************��ʦ��ӿγ�ʱ������*****************/
	/**
	 * ��ȡ���ݿ�����ֹʱ����Ϣ
	 * @return
	 */
	public EMS_Systemset disTeacherAddTime(String idnum);			

	
	
	/**************��ʦ¼��ɼ�ʱ������*****************/
	/**
	 * ��ȡ���ݿ�����ֹʱ����Ϣ
	 * @return
	 */
	public EMS_Systemset disTeacherInputGrade();	
		
	
	
	/**************ѧ��ѡ��ʱ������*****************/
	/**
	 * ��ȡ���ݿ�����ֹʱ����Ϣ
	 * @return
	 */
	public EMS_Systemset disStudentTakeCourse();	
	
	/******************************����������**********************************************/
	/*******�鿴��Ϣ***********/
	public List<ApplyModifyLogVO> disAditInfor(String state,String table);
	
		/***********�õ���Ӧ��ŵ��������Ϣ��¼*********/
	public ApplyModifyLogVO getAuditInfor(String applyid);
	
	/**
	 * �鿴��ʦ��Ϣ--��ҳ
	 * @return
	 */
	public List<Ems_Teacher_VO> listTeacher(int nowPage);

	/**
	 * �õ���ʦ��Ϣ��¼��
	 * @return
	 */
	public int getTeacherCounts();
	/**
	 * �鿴ѧ����Ϣ--��ҳ
	 * @return
	 */
	public List<StudentVO> listStudent(int nowPage);

	/**
	 * �õ�ѧ����Ϣ��¼��
	 * @return
	 */
	public int listStudentCounts();
	
	/**
	 * �ʺŲ���
	 * @return
	 */
	public boolean checkAccountIsExist(String account);
	
	/**
	 * ������֤����Ա����Ƿ�Ϸ�
	 * @return
	 */
	public boolean checkManager(String account);

}
