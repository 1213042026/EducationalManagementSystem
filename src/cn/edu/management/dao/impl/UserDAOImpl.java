package cn.edu.management.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import cn.edu.management.dao.SuperDAO;
import cn.edu.management.dao.UserDAO;
import cn.edu.management.vo.voImpl.Ems_Manager_VO;
import cn.edu.management.vo.voImpl.Ems_Student_VO;
import cn.edu.management.vo.voImpl.Ems_Teacher_VO;
import cn.edu.management.vo.voImpl.UserVO;
/**
 * 
 * @author tq
 *
 */
@Component("userDao")
public class UserDAOImpl extends SuperDAO implements UserDAO{
	
	/**
	 *  登录
	 */
	public UserVO login( final UserVO vo ){
		System.out.println("---------UserDAOImpl----login---------");
		HibernateTemplate ht = this.getHibernateTemplate();
		
		Query query = this.getSession().createSQLQuery("select * from ems_user where userid='" + vo.getUserid() + "' and password='" + vo.getPassword() +"'");
		
		UserVO temp = new UserVO();
		
		List<Object[]> list = query.list();
		if( list.size() > 0 ){
			
			temp.setUserid(list.get(0)[0].toString());
			temp.setPassword(list.get(0)[1].toString());
			
			if( vo.getPassword().equals(temp.getPassword()) ){
				return temp;
			}
		}
		
		

		return null;
	}
	
	/**
	 * 管理员登录的时候进行验证
	 * @param managerVO
	 * @return
	 */
	public Ems_Manager_VO validate_manager(Ems_Manager_VO managerVO) {
		// TODO Auto-generated method stub
		HibernateTemplate hibernate = this.getHibernateTemplate();
		Ems_Manager_VO vo = (Ems_Manager_VO) hibernate.get( Ems_Manager_VO.class , managerVO.getIdnum());
		List list = hibernate.find("from Ems_Manager_VO where idnum='" + managerVO.getIdnum() + "' and password='" + managerVO.getPassword() + "'" );
		if( null == list || list.size()<= 0 ){
			return null;
		}else{ 
			return (Ems_Manager_VO) list.get(0);
		}
		
	}
	
	/**
	 * 学生进行登录地时候进行验证
	 * @param studentVO
	 * @return
	 */
	public Ems_Student_VO validate_student(Ems_Student_VO studentVO) {
		// TODO Auto-generated method stub
		HibernateTemplate hibernate = this.getHibernateTemplate();
		/*Query query = this.getSession().createSQLQuery("select * from Ems_Student where iDnum='" + studentVO.getiDnum() + "' and password='" + studentVO.getPassword() + "'"   );
		List list = query.list();*/
		List list = hibernate.find( "from Ems_Student_VO where iDnum='" + studentVO.getIdnum() + "' and password='" + studentVO.getPassword() + "'" );
		if( null == list || list.size() <= 0 ){
			return null;
		}else{
			return (Ems_Student_VO) list.get(0);
		}
	}
	
	/**
	 * 老师进行登录 的时候进行验证
	 * @param teacherVO
	 * @return
	 */
	public Ems_Teacher_VO validate_teacher(Ems_Teacher_VO teacherVO) {
		// TODO Auto-generated method stub
		HibernateTemplate hibernate = this.getHibernateTemplate();
		List list = hibernate.find( "from Ems_Teacher_VO where iDnum='" + teacherVO.getIdnum() + "' and password='" + teacherVO.getPassword() + "'" );
		/*Query query = this.getSession().createSQLQuery( "select * from Ems_Teacher where iDnum='" + teacherVO.getiDnum() + "' and password='" + teacherVO.getPassword() + "'" );
		List list = query.list();*/
		if( null == list || list.size() <= 0 ){
			return null;
		}else{
			return (Ems_Teacher_VO) list.get(0);
		}
	}
}
