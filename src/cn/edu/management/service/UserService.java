package cn.edu.management.service;

import cn.edu.management.vo.voImpl.Ems_Manager_VO;
import cn.edu.management.vo.voImpl.Ems_Student_VO;
import cn.edu.management.vo.voImpl.Ems_Teacher_VO;
import cn.edu.management.vo.voImpl.UserVO;

public interface UserService extends Service{
	/**
	 * 这个方法已经废弃了
	 * @param vo
	 * @return
	 */
	public UserVO login( UserVO vo );
	
	/**
	 * 管理员登录的时候进行验证
	 * @param managerVO
	 * @return
	 */
	public Ems_Manager_VO validate_manager( Ems_Manager_VO managerVO );
	
	/**
	 * 老师进行登录 的时候进行验证
	 * @param teacherVO
	 * @return
	 */
	public Ems_Teacher_VO validate_teacher( Ems_Teacher_VO teacherVO );
	
	
	/**
	 * 学生进行登录地时候进行验证
	 * @param studentVO
	 * @return
	 */
	public Ems_Student_VO validate_student( Ems_Student_VO studentVO );
	
}
