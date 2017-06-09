package cn.edu.management.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.edu.management.dao.UserDAO;
import cn.edu.management.service.SuperService;
import cn.edu.management.service.UserService;
import cn.edu.management.vo.voImpl.Ems_Manager_VO;
import cn.edu.management.vo.voImpl.Ems_Student_VO;
import cn.edu.management.vo.voImpl.Ems_Teacher_VO;
import cn.edu.management.vo.voImpl.UserVO;

/**
 * 
 * @author Administrator
 *
 */
@Component("userService")
public class UserServiceImpl extends SuperService implements UserService {

	private UserDAO userDao;
	
	/**
	 * ��¼
	 */
	public UserVO login(UserVO vo) {
		System.out.println("--------------UserServiceImpl-----login-----------");
		// TODO Auto-generated method stub
		return userDao.login(vo);
	}

	@Resource
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	/**
	 * ����Ա��¼��ʱ�������֤
	 * @param managerVO
	 * @return
	 */
	public Ems_Manager_VO validate_manager( Ems_Manager_VO managerVO ){
		return this.userDao.validate_manager( managerVO );
	}
	
	/**
	 * ��ʦ���е�¼ ��ʱ�������֤
	 * @param teacherVO
	 * @return
	 */
	public Ems_Teacher_VO validate_teacher( Ems_Teacher_VO teacherVO ){
		return this.userDao.validate_teacher( teacherVO );
	}
	
	/**
	 * ѧ�����е�¼��ʱ�������֤
	 * @param studentVO
	 * @return
	 */
	public Ems_Student_VO validate_student( Ems_Student_VO studentVO ){
		return this.userDao.validate_student( studentVO );
	}
	
	

}
