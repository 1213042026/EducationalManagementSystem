package cn.edu.management.service;

import cn.edu.management.vo.voImpl.Ems_Manager_VO;
import cn.edu.management.vo.voImpl.Ems_Student_VO;
import cn.edu.management.vo.voImpl.Ems_Teacher_VO;
import cn.edu.management.vo.voImpl.UserVO;

public interface UserService extends Service{
	/**
	 * ��������Ѿ�������
	 * @param vo
	 * @return
	 */
	public UserVO login( UserVO vo );
	
	/**
	 * ����Ա��¼��ʱ�������֤
	 * @param managerVO
	 * @return
	 */
	public Ems_Manager_VO validate_manager( Ems_Manager_VO managerVO );
	
	/**
	 * ��ʦ���е�¼ ��ʱ�������֤
	 * @param teacherVO
	 * @return
	 */
	public Ems_Teacher_VO validate_teacher( Ems_Teacher_VO teacherVO );
	
	
	/**
	 * ѧ�����е�¼��ʱ�������֤
	 * @param studentVO
	 * @return
	 */
	public Ems_Student_VO validate_student( Ems_Student_VO studentVO );
	
}
