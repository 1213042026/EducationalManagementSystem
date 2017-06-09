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
	 * 遍历所有的管理员
	 * @return
	 */
	public List<Ems_Manager_VO> listManager();
	/**
	 * 取到所有管理员编号中最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutManager();
	/**
	 * 取出这个需要修改的管理员的信息
	 * @return
	 */
	public Ems_Manager_VO getModifyManagerInfo( String idnum  );
	
	/********************************教师********************************************/
	/**
	 * 遍历所有的教师
	 * @return
	 */
	public List<Ems_Teacher_VO> listTeacher();
	
	/**
	 * 取到所有教师编号中最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutTeacher();
	/**
	 * 取出这个需要修改的教师的信息
	 * @return
	 */
	public Ems_Teacher_VO getModifyTeacherInfo( String idnum  );
	
	
	/********************************学生********************************************/
	/**
	 * 遍历所有的学生
	 * @return
	 */
	public List<StudentVO> listStudent();	
	

	/**
	 * 取出这个需要修改的学生的的信息
	 * @return
	 */
	public Ems_Student_VO getModifyStudentInfo( String idnum  );
	
	
	
	
	/********************************系统设置********************************************/
	
	/******************作品类型*************************/
	/**
	 * 遍历所有的作品类型
	 * @return
	 */
	public List<WorkerTypeVO> listWorkertype();
	
	/**
	 * 取到所有作品类型中最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutWorkstype();
	
	
	
	/******************课程类型*************************/
	/**
	 * 遍历所有的课程类型
	 * @return
	 */
	public List<ClassTypeVO> listClasstype();
	
	/**
	 * 取到所有课程类型中最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutClasstype();
	
	
	
	
	/******************专业管理*************************/
	/**
	 * 遍历所有的专业
	 * @return
	 */
	public List<ProfessionVO> listProfession();
	
	/**
	 * 取到所有专业编号中最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutProfession();
	
	
	
	
	/**************老师添加课程时间设置*****************/
	/**
	 * 列取数据库中起止时间信息
	 * @return
	 */
	public EMS_Systemset disTeacherAddTime(String idnum);			

	
	
	/**************老师录入成绩时间设置*****************/
	/**
	 * 列取数据库中起止时间信息
	 * @return
	 */
	public EMS_Systemset disTeacherInputGrade();	
		
	
	
	/**************学生选课时间设置*****************/
	/**
	 * 列取数据库中起止时间信息
	 * @return
	 */
	public EMS_Systemset disStudentTakeCourse();	
	
	/******************************审核请求管理**********************************************/
	/*******查看信息***********/
	public List<ApplyModifyLogVO> disAditInfor(String state,String table);
	
		/***********得到相应编号的申请表信息记录*********/
	public ApplyModifyLogVO getAuditInfor(String applyid);
	
	/**
	 * 查看教师信息--分页
	 * @return
	 */
	public List<Ems_Teacher_VO> listTeacher(int nowPage);

	/**
	 * 得到教师信息记录数
	 * @return
	 */
	public int getTeacherCounts();
	/**
	 * 查看学生信息--分页
	 * @return
	 */
	public List<StudentVO> listStudent(int nowPage);

	/**
	 * 得到学生信息记录数
	 * @return
	 */
	public int listStudentCounts();
	
	/**
	 * 帐号测试
	 * @return
	 */
	public boolean checkAccountIsExist(String account);
	
	/**
	 * 测试验证管理员编号是否合法
	 * @return
	 */
	public boolean checkManager(String account);

}
