package cn.edu.management.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.edu.management.dao.ManagerDAO;
import cn.edu.management.service.ManagerService;
import cn.edu.management.service.SuperService;
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

@Component("managerService")
public class ManagerServiceImpl extends SuperService implements ManagerService {
	
	private ManagerDAO managerDao;
	
	public boolean add(UserVO vo) {
		// TODO Auto-generated method stub
		return this.managerDao.add(vo);
	}
	
	@Resource
	public void setManagerDao(ManagerDAO managerDao) {
		this.managerDao = managerDao;
	}

	/**
	 * �������еĹ���Ա
	 * 
	 * @return
	 */
	public List<Ems_Manager_VO> listManager(){
		return this.managerDao.listManager();
	}
	/**
	 * ȡ�����й���Ա���������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutManager(){
		return this.managerDao.getMaxIdNumAboutManager();
	}
	/**
	 * ȡ�������Ҫ�޸ĵĹ���Ա����Ϣ
	 * @return
	 */
	public Ems_Manager_VO getModifyManagerInfo( String idnum  ){
		return this.managerDao.getModifyManagerInfo( idnum  );
	}

	/*********************************��ʦ******************************************/
	/**
	 * �������еĽ�ʦ
	 * 
	 * @return
	 */
	public List<Ems_Teacher_VO> listTeacher() {
		return this.managerDao.listTeacher();
	}
	
	/**
	 * ȡ�����н�ʦ���������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutTeacher(){
		return this.managerDao.getMaxIdNumAboutTeacher();
	}
	
	/**
	 * ȡ�������Ҫ�޸ĵĽ�ʦ����Ϣ
	 * @return
	 */
	public Ems_Teacher_VO getModifyTeacherInfo( String idnum  ){
		return this.managerDao.getModifyTeacherInfo( idnum  );
	}
	
	
	
	
	
	/*********************************ѧ��******************************************/
	/**
	 * �������е�ѧ��
	 * @return
	 */
	public List<StudentVO> listStudent(){
		return this.managerDao.listStudent();
	}

	/**
	 * ȡ�������Ҫ�޸ĵ�ѧ���ĵ���Ϣ
	 * @return
	 */
	public Ems_Student_VO getModifyStudentInfo( String idnum  ){
		
		return this.managerDao.getModifyStudentInfo( idnum  );
		
	};
	
	
	
	
	
	/*********************************ϵͳ����******************************************/
	
	/***************��Ʒ����********************/
	/**
	 * �������е���Ʒ����
	 * @return
	 */
	public List<WorkerTypeVO> listWorkertype(){
		return this.managerDao.listWorkertype();
	};
	
	/**
	 * ȡ��������Ʒ����������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutWorkstype(){
		return this.managerDao.getMaxIdNumAboutWorkstype();
	};
	
	
	
	
	/***************�γ�����********************/
	/**
	 * �������еĿγ�����
	 * @return
	 */
	public List<ClassTypeVO> listClasstype(){
		return this.managerDao.listClasstype();
	};
	
	/**
	 * ȡ�����пγ�����������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutClasstype(){
		return this.managerDao.getMaxIdNumAboutClasstype();
	};
	
	
	
	/***************רҵ����********************/
	/**
	 * �������е�רҵ
	 * 
	 * @return
	 */
	public List<ProfessionVO> listProfession(){
		return this.managerDao.listProfession();
	}
	
	/**
	 * ȡ������רҵ���������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutProfession(){
		return this.managerDao.getMaxIdNumAboutProfession();
	};
	
	
	/**************��ʦ��ӿγ�ʱ������*****************/
	/**
	 * ��ȡ���ݿ�����ֹʱ����Ϣ
	 * @return
	 */
	public EMS_Systemset disTeacherAddTime(String idnum){
		return this.managerDao.disTeacherAddTime(idnum);
	};			

	
	
	/**************��ʦ¼��ɼ�ʱ������*****************/
	/**
	 * ��ȡ���ݿ�����ֹʱ����Ϣ
	 * @return
	 */
	public EMS_Systemset disTeacherInputGrade(){
		return this.managerDao.disTeacherInputGrade();
		
	};	
		
	
	
	/**************ѧ��ѡ��ʱ������*****************/
	/**
	 * ��ȡ���ݿ�����ֹʱ����Ϣ
	 * @return
	 */
	public EMS_Systemset disStudentTakeCourse(){
		return this.managerDao.disStudentTakeCourse();
		
	};	
	
	/******************************����������**********************************************/
	
	/**
	 * *****�鿴��Ϣ***********
	 * @param state ��ʾ���״̬
	 * @param table ��ʾ����ʲôѧ�е�
	 */
	public List<ApplyModifyLogVO> disAditInfor(String state,String table){
		return this.managerDao.disAditInfor(state,table);
	};
	

	
	/***********�õ���Ӧ��ŵ��������Ϣ��¼*********/
	public ApplyModifyLogVO getAuditInfor(String applyid){
		return this.managerDao.getAuditInfor(applyid);
	};
	
	
	/**
	 * �鿴��ʦ��Ϣ--��ҳ
	 * @return
	 */
	public List<Ems_Teacher_VO> listTeacher(int nowPage){
		return this.managerDao.listTeacher(nowPage);
	};

	/**
	 * �õ���ʦ��Ϣ��¼��
	 * @return
	 */
	public int getTeacherCounts(){
		return this.managerDao.getTeacherCounts();
	};
	/**
	 * �鿴ѧ����Ϣ--��ҳ
	 * @return
	 */
	public List<StudentVO> listStudent(int nowPage){
		return this.managerDao.listStudent(nowPage);
	};

	/**
	 * �õ�ѧ����Ϣ��¼��
	 * @return
	 */
	public int listStudentCounts(){
		return this.managerDao.listStudentCounts();
	};
	/**
	 * �ʺŲ���
	 * @return
	 */
	public boolean checkAccountIsExist(String account){
		return this.managerDao.checkAccountIsExist(account);
	};
	/**
	 * ������֤����Ա����Ƿ�Ϸ�
	 * @return
	 */
	public boolean checkManager(String account){
		return this.managerDao.checkManager(account);
	};
}
