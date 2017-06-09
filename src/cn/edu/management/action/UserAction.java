package cn.edu.management.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.edu.management.service.UserService;
import cn.edu.management.vo.voImpl.Ems_Manager_VO;
import cn.edu.management.vo.voImpl.Ems_Student_VO;
import cn.edu.management.vo.voImpl.Ems_Teacher_VO;
import cn.edu.management.vo.voImpl.UserVO;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author tq
 * ��Ҫ�����ڴ��������û���¼ʱ�������Ϣ
 */
@Component
@Scope("prototype")
public class UserAction extends ActionSupport {
	
	UserService userService;
	/**
	 * ��¼
	 * @return
	 */
	public String login(){
		System.out.println("--------UserAction--------login----------");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = org.apache.struts2.ServletActionContext.getRequest().getSession(); 
		
		//ÿ�δ򿪵�½ҳ��ʱ���������session�е�ֵ///////////////////////////////////////////////////////////////////
		session.removeAttribute("msg");
		session.removeAttribute("system");
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String identity = request.getParameter("identity");  //��¼ʱ�õ����
		
		int ident_ = Integer.valueOf( identity );
		switch( ident_ ){
		case 1 :    //ѧ����¼
			Ems_Student_VO studentVO = new Ems_Student_VO();
			studentVO.setIdnum( userId );
			studentVO.setPassword( password );
			studentVO = this.userService.validate_student( studentVO );
			if( null == studentVO ){
				request.setAttribute("msg", "aaa");//�����жϲ�����
				return "failer";
			}else{
				session.setAttribute("idnum", studentVO.getIdnum());//�����̨��ʾ
				session.setAttribute("user", studentVO.getName());//�����̨��ʾ
				return "studentLoginSuccess";
			}
			
		case 2 :   //��ʦ��¼
			
			Ems_Teacher_VO teacherVO = new Ems_Teacher_VO();
			teacherVO.setIdnum( userId );
			teacherVO.setPassword( password );
			teacherVO = this.userService.validate_teacher( teacherVO );
			if( null == teacherVO ){
				request.setAttribute("msg", "aaa");//�����жϲ�����
				return "failer";
			}else{
				
				session.setAttribute("teacheruser", teacherVO.getName());//�����̨��ʾ
				session.setAttribute("teacheridnum", teacherVO.getIdnum());
			
//				session.setAttribute("idnum",teacherVO.getIdnum());
				
				return "teacherLoginSuccess";
			}
			
		case 3 :   //����Ա��¼
			Ems_Manager_VO managerVO = new Ems_Manager_VO();
			managerVO.setIdnum( userId );
			managerVO.setPassword( password );
			managerVO = this.userService.validate_manager( managerVO );
			if( null == managerVO ){
				request.setAttribute("msg", "aaa");//�����жϲ�����
				return "failer";  //��¼ʧ��
			}else{
				String user = managerVO.getIdnum();
				if(user.equals("system")) {//�����system�û�
					System.out.println("ϵͳ�û���"+user);
					session.setAttribute("system", "system");
				}
//				request.setAttribute("user",managerVO.getName());//requestȡ����
				session.setAttribute("manageruser",managerVO.getName());//�����̨��ʾ
				session.setAttribute("manageridnum", managerVO.getIdnum());
				return "managerLoginSuccess";  //��¼�ɹ�
			}
		}
		
		return null;
		
	}
	
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
