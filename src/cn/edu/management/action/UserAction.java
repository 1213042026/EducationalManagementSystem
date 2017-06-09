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
 * 主要是用于处理三类用户登录时处理的信息
 */
@Component
@Scope("prototype")
public class UserAction extends ActionSupport {
	
	UserService userService;
	/**
	 * 登录
	 * @return
	 */
	public String login(){
		System.out.println("--------UserAction--------login----------");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = org.apache.struts2.ServletActionContext.getRequest().getSession(); 
		
		//每次打开登陆页面时，都会清空session中的值///////////////////////////////////////////////////////////////////
		session.removeAttribute("msg");
		session.removeAttribute("system");
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String identity = request.getParameter("identity");  //登录时用的身份
		
		int ident_ = Integer.valueOf( identity );
		switch( ident_ ){
		case 1 :    //学生登录
			Ems_Student_VO studentVO = new Ems_Student_VO();
			studentVO.setIdnum( userId );
			studentVO.setPassword( password );
			studentVO = this.userService.validate_student( studentVO );
			if( null == studentVO ){
				request.setAttribute("msg", "aaa");//用于判断不存在
				return "failer";
			}else{
				session.setAttribute("idnum", studentVO.getIdnum());//方便后台显示
				session.setAttribute("user", studentVO.getName());//方便后台显示
				return "studentLoginSuccess";
			}
			
		case 2 :   //老师登录
			
			Ems_Teacher_VO teacherVO = new Ems_Teacher_VO();
			teacherVO.setIdnum( userId );
			teacherVO.setPassword( password );
			teacherVO = this.userService.validate_teacher( teacherVO );
			if( null == teacherVO ){
				request.setAttribute("msg", "aaa");//用于判断不存在
				return "failer";
			}else{
				
				session.setAttribute("teacheruser", teacherVO.getName());//方便后台显示
				session.setAttribute("teacheridnum", teacherVO.getIdnum());
			
//				session.setAttribute("idnum",teacherVO.getIdnum());
				
				return "teacherLoginSuccess";
			}
			
		case 3 :   //管理员登录
			Ems_Manager_VO managerVO = new Ems_Manager_VO();
			managerVO.setIdnum( userId );
			managerVO.setPassword( password );
			managerVO = this.userService.validate_manager( managerVO );
			if( null == managerVO ){
				request.setAttribute("msg", "aaa");//用于判断不存在
				return "failer";  //登录失败
			}else{
				String user = managerVO.getIdnum();
				if(user.equals("system")) {//如果是system用户
					System.out.println("系统用户："+user);
					session.setAttribute("system", "system");
				}
//				request.setAttribute("user",managerVO.getName());//request取不到
				session.setAttribute("manageruser",managerVO.getName());//方便后台显示
				session.setAttribute("manageridnum", managerVO.getIdnum());
				return "managerLoginSuccess";  //登录成功
			}
		}
		
		return null;
		
	}
	
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
