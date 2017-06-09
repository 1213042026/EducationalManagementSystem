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
	 * 遍历所有的管理员
	 * 
	 * @return
	 */
	public List<Ems_Manager_VO> listManager(){
		return this.managerDao.listManager();
	}
	/**
	 * 取到所有管理员编号中最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutManager(){
		return this.managerDao.getMaxIdNumAboutManager();
	}
	/**
	 * 取出这个需要修改的管理员的信息
	 * @return
	 */
	public Ems_Manager_VO getModifyManagerInfo( String idnum  ){
		return this.managerDao.getModifyManagerInfo( idnum  );
	}

	/*********************************教师******************************************/
	/**
	 * 遍历所有的教师
	 * 
	 * @return
	 */
	public List<Ems_Teacher_VO> listTeacher() {
		return this.managerDao.listTeacher();
	}
	
	/**
	 * 取到所有教师编号中最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutTeacher(){
		return this.managerDao.getMaxIdNumAboutTeacher();
	}
	
	/**
	 * 取出这个需要修改的教师的信息
	 * @return
	 */
	public Ems_Teacher_VO getModifyTeacherInfo( String idnum  ){
		return this.managerDao.getModifyTeacherInfo( idnum  );
	}
	
	
	
	
	
	/*********************************学生******************************************/
	/**
	 * 遍历所有的学生
	 * @return
	 */
	public List<StudentVO> listStudent(){
		return this.managerDao.listStudent();
	}

	/**
	 * 取出这个需要修改的学生的的信息
	 * @return
	 */
	public Ems_Student_VO getModifyStudentInfo( String idnum  ){
		
		return this.managerDao.getModifyStudentInfo( idnum  );
		
	};
	
	
	
	
	
	/*********************************系统设置******************************************/
	
	/***************作品类型********************/
	/**
	 * 遍历所有的作品类型
	 * @return
	 */
	public List<WorkerTypeVO> listWorkertype(){
		return this.managerDao.listWorkertype();
	};
	
	/**
	 * 取到所有作品类型中最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutWorkstype(){
		return this.managerDao.getMaxIdNumAboutWorkstype();
	};
	
	
	
	
	/***************课程类型********************/
	/**
	 * 遍历所有的课程类型
	 * @return
	 */
	public List<ClassTypeVO> listClasstype(){
		return this.managerDao.listClasstype();
	};
	
	/**
	 * 取到所有课程类型中最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutClasstype(){
		return this.managerDao.getMaxIdNumAboutClasstype();
	};
	
	
	
	/***************专业管理********************/
	/**
	 * 遍历所有的专业
	 * 
	 * @return
	 */
	public List<ProfessionVO> listProfession(){
		return this.managerDao.listProfession();
	}
	
	/**
	 * 取到所有专业编号中最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutProfession(){
		return this.managerDao.getMaxIdNumAboutProfession();
	};
	
	
	/**************老师添加课程时间设置*****************/
	/**
	 * 列取数据库中起止时间信息
	 * @return
	 */
	public EMS_Systemset disTeacherAddTime(String idnum){
		return this.managerDao.disTeacherAddTime(idnum);
	};			

	
	
	/**************老师录入成绩时间设置*****************/
	/**
	 * 列取数据库中起止时间信息
	 * @return
	 */
	public EMS_Systemset disTeacherInputGrade(){
		return this.managerDao.disTeacherInputGrade();
		
	};	
		
	
	
	/**************学生选课时间设置*****************/
	/**
	 * 列取数据库中起止时间信息
	 * @return
	 */
	public EMS_Systemset disStudentTakeCourse(){
		return this.managerDao.disStudentTakeCourse();
		
	};	
	
	/******************************审核请求管理**********************************************/
	
	/**
	 * *****查看信息***********
	 * @param state 表示审核状态
	 * @param table 表示属于什么学中的
	 */
	public List<ApplyModifyLogVO> disAditInfor(String state,String table){
		return this.managerDao.disAditInfor(state,table);
	};
	

	
	/***********得到相应编号的申请表信息记录*********/
	public ApplyModifyLogVO getAuditInfor(String applyid){
		return this.managerDao.getAuditInfor(applyid);
	};
	
	
	/**
	 * 查看教师信息--分页
	 * @return
	 */
	public List<Ems_Teacher_VO> listTeacher(int nowPage){
		return this.managerDao.listTeacher(nowPage);
	};

	/**
	 * 得到教师信息记录数
	 * @return
	 */
	public int getTeacherCounts(){
		return this.managerDao.getTeacherCounts();
	};
	/**
	 * 查看学生信息--分页
	 * @return
	 */
	public List<StudentVO> listStudent(int nowPage){
		return this.managerDao.listStudent(nowPage);
	};

	/**
	 * 得到学生信息记录数
	 * @return
	 */
	public int listStudentCounts(){
		return this.managerDao.listStudentCounts();
	};
	/**
	 * 帐号测试
	 * @return
	 */
	public boolean checkAccountIsExist(String account){
		return this.managerDao.checkAccountIsExist(account);
	};
	/**
	 * 测试验证管理员编号是否合法
	 * @return
	 */
	public boolean checkManager(String account){
		return this.managerDao.checkManager(account);
	};
}
