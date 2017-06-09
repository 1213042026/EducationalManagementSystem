package cn.edu.management.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.edu.management.comm.ApplyTeacherComparator;
import cn.edu.management.comm.DateConventer;
import cn.edu.management.comm.FileManage;
import cn.edu.management.comm.Page;
import cn.edu.management.comm.VOconver;
import cn.edu.management.service.ManagerService;
import cn.edu.management.service.UserService;
import cn.edu.management.vo.showVO.Ems_systemset;
import cn.edu.management.vo.showVO.StudentVO;
import cn.edu.management.vo.showVO.showApplyModifyLogVO;
import cn.edu.management.vo.showVO.showTeacher_VO;
import cn.edu.management.vo.voImpl.ApplyModifyLogVO;
import cn.edu.management.vo.voImpl.ClassTypeVO;
import cn.edu.management.vo.voImpl.EMS_Systemset;
import cn.edu.management.vo.voImpl.Ems_Manager_VO;
import cn.edu.management.vo.voImpl.Ems_Student_VO;
import cn.edu.management.vo.voImpl.Ems_Teacher_VO;
import cn.edu.management.vo.voImpl.ProfessionVO;
import cn.edu.management.vo.voImpl.WorkerTypeVO;

import com.opensymphony.xwork2.ActionSupport;


@Component
@Scope("prototype")
public class ManagerAction extends ActionSupport {
	
	private ManagerService managerService;
	private UserService userService;
	
	@Resource
	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/*方便页面获取迭代显示*/
	private List<Ems_Manager_VO> managerList ;//管理员
	private List<Ems_Teacher_VO> teacherList ;//教师
	private List<StudentVO> studentList ;//学生
	private List<ProfessionVO>   professionList;//专业
	private List<WorkerTypeVO>   workertypeList;//作品类型
	private List<ClassTypeVO>   classtypeList;//课程类型
	private List<showApplyModifyLogVO> applyModifyList;//申请信息记录表
	private List<showTeacher_VO>  showTeacherList;//方便界面显示老师
	
	
	
	/**
	 *  局部刷新验证
	 * @return
	 * @throws IOException 
	 */
	public void checkAccount() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String account = request.getParameter("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		try	{
			
			response.setCharacterEncoding("utf-8");
			if(account.equals(""))
				response.getWriter().print("{\"result\":\"space\"}");
			else
			{
				boolean flag = this.managerService.checkAccountIsExist(account);
				
				if(flag)
				{
					response.getWriter().print("{\"result\":\"true\"}");
				}
				else
				{
					response.getWriter().print("{\"result\":\"false\"}");
				}
			}
			
		
		} catch(Exception e) {
			String msg = "{result:false,desc:\"失败\"}";
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(msg);
		}	   	
	}
	
	
	
	
	/**
	 *  测试验证管理员编号是否合法
	 * @return
	 * @throws IOException 
	 */
	public void checkManager() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String account = request.getParameter("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		try	{
			
			response.setCharacterEncoding("utf-8");
			if(account.equals(""))
				response.getWriter().print("{\"result\":\"space\"}");
			else
			{
				boolean flag = this.managerService.checkManager(account);
				
				if(flag)
				{
					response.getWriter().print("{\"result\":\"true\"}");
				}
				else
				{
					response.getWriter().print("{\"result\":\"false\"}");
				}
			}
			
		
		} catch(Exception e) {
			String msg = "{result:false,desc:\"失败\"}";
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(msg);
		}	   	
	}
	
	
	/*****查看个人信息*****/
	public String displayPersonInformation() {

		System.out
				.println("=========TeacherAction==========displayPersonInformation===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		 //从request中得到session
		  HttpSession session = request.getSession(); 
		 String idnum = (String)session.getAttribute("manageridnum");/////////
		 System.out.println("得到的编号："+idnum);		 
         
		 Ems_Manager_VO vo = (Ems_Manager_VO) this.managerService.getModifyManagerInfo(idnum);
      
       
		request.setAttribute("managerVO", vo);
	
		return "PersonInformationQuery";
	}
	
	
	
	/**
	 * 
	 *修改密码
	 * @return
	 */
	public String updateManagerPersonPassword() {
		System.out.println("====mangeraction====updateManagerPersonPassword=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Ems_Manager_VO managerVO = new Ems_Manager_VO();
		String userId = (String)session.getAttribute("manageridnum");
		String password = request.getParameter("prePassword");
		String newpwd = request.getParameter("newPassword");
		managerVO.setIdnum( userId );
		managerVO.setPassword( password );
		
		managerVO = this.userService.validate_manager( managerVO );
		
		if( null == managerVO ){
			request.setAttribute("msg", "wrong");//用于旧密码不正确
			return "prePasswordWorng";
		}else{

			//从数据库中取出这个编号的所有信息
			Ems_Manager_VO vo = (Ems_Manager_VO) this.managerService.getModifyManagerInfo(userId);
			vo.setPassword(newpwd);//设置要修改的新密码
			this.managerService.update(vo);//更新密码
			
		}
		return "successPWD";
	}
	
	/**
	 * 
	 * @return
	 */
	public String insert(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String idnum = request.getParameter("iDnum");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String idcard = request.getParameter("idcard");
		String address = request.getParameter("address");
		String nation = request.getParameter("nation");
		
		Ems_Manager_VO managerVO = new Ems_Manager_VO();
		
		managerVO.setIdnum(idnum);
		managerVO.setPassword(password);
		managerVO.setName(name);
		switch( Integer.valueOf( sex ) ){
		case 1 :
			managerVO.setSex("女");
			break;
		case 2 :
			managerVO.setSex("男");
			break;
		}
		managerVO.setIdcard(idcard);
		managerVO.setAddress(address);
		managerVO.setNation(nation);
		this.managerService.insert(managerVO);
		return "addManagerSuccess";
	}

	/**
	 * update 
	 * @return
	 */
	public String update(){
		System.out.println("====manageraction====update=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String idnum = request.getParameter("iDnum");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String idcard = request.getParameter("idcard");
		String address = request.getParameter("address");
		String nation = request.getParameter("nation");
		
		Ems_Manager_VO managerVO = new Ems_Manager_VO();
		
		managerVO.setIdnum(idnum);
		managerVO.setPassword(password);
		managerVO.setName(name);
		switch( Integer.valueOf( sex ) ){
		case 1 :
			managerVO.setSex("女");
			break;
		case 2 :
			managerVO.setSex("男");
			break;
		}
		managerVO.setIdcard(idcard);
		managerVO.setAddress(address);
		managerVO.setNation(nation);
		this.managerService.update( managerVO);
		return "updateManagerSuccess";
		
	}
	/**
	 * 用于添加管理员的时候取到数据库记录中的最大ID，管理员不能进行手动插入ID号
	 * @return
	 */
	public String getMaxIdNumAboutManager(){
		System.out.println("=============managerAction========getMaxIdNum=========");
		
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String maxIdnum = this.managerService.getMaxIdNumAboutManager();
		if(maxIdnum!=null ){
			int id = Integer.valueOf(maxIdnum) + 1 ;
			DecimalFormat d = new DecimalFormat("0000");
			maxIdnum = d.format(id);
			
		}else{
			maxIdnum="0001";
		}
		request.setAttribute("maxIdnum", maxIdnum);

		
		return "getMaxIdForAddManager";
	}
	
	/**
	 * 列出管理员的信息
	 * @return
	 */
	public String listManager(){
		System.out.println("=====ManagerAction=listManager==============");
		List<Ems_Manager_VO> list = this.managerService.listManager();
		
		for( Ems_Manager_VO vo : list ){  //system 不显示
			if( "system".equals( vo.getIdnum()) ){
				list.remove(vo);
				break;
			}
		}
		this.setManagerList( list );
		
		return "listManager";
	}
	
	/**
	 * 列取可以被删除的管理员信息
	 * @return
	 */
	public String ListDeleteManager(){
		System.out.println("=====ManagerAction=ListDeleteManager==============");
		List<Ems_Manager_VO> list = this.managerService.listManager();
		for( Ems_Manager_VO vo : list ){  //system 不显示
			if( "system".equals( vo.getIdnum()) ){
				list.remove(vo);
				break;
			}
		}
		
		this.setManagerList( list );
		
		return "ListDeleteManager";
	}
	
	/**
	 * 
	 * @return
	 */
	public String deleteManager(){
		System.out.println("=========ManagerAction==========deleteManager===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		String idnum = request.getParameter( "idnum" );
		Ems_Manager_VO vo = new Ems_Manager_VO();
		vo.setIdnum(idnum);
		
		boolean flag = this.managerService.delete( vo );
		
		
		if( false == flag ){
			return null;
		}
	
		return "deleteMangerSuccess";
	}
	
	/**
	 * 列出所有可以修改的管理员的信息
	 * @return
	 */
	public String listModifyManager(){
		System.out.println("=========ManagerAction==========listModifyManager===========");
		List<Ems_Manager_VO> list = this.managerService.listManager();
		for( Ems_Manager_VO vo : list ){  //system 不显示
			if( "system".equals( vo.getIdnum()) ){
				list.remove(vo);
				break;
			}
		}
		this.setManagerList( list );
		return "listModifyManager";
	}
	
	/**
	 * 取出这个需要修改的管理员的信息
	 * @return
	 */
	public String getModifyManagerInfo(){
		System.out.println("=========ManagerAction==========listModifyManager===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String idnum = request.getParameter("idnum");
		
		Ems_Manager_VO vo = (Ems_Manager_VO) this.managerService.getModifyManagerInfo(idnum);
		
		request.setAttribute("managerVO", vo);
		return "showManagerForModify";
	}
	
	
	
	/***********************************教师*****************************************/
	
	/**
	 * 查看-列出教师的信息
	 * @return
	 */
	public String listTeacher(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("=====ManagerAction=listManager==============");
//////////////////////1/////	
	       int counts=this.managerService.getTeacherCounts();	//得到所有记录数	
			Page page=new Page(6,counts);
			int jumpPage=1;
			if(request.getParameter("jumpPage")!=null ){
				jumpPage=Integer.valueOf(request.getParameter("jumpPage"));
			}
			if( jumpPage < 1 ){//
				page.setPageNow(1);
			}else if( jumpPage >= page.getPageCount() ){
				page.setPageNow( page.getPageCount());
			}else{
				page.setPageNow( jumpPage );
			}
			///////////////////////
		List<Ems_Teacher_VO> list = this.managerService.listTeacher(page.getPageNow()); 
		List<showTeacher_VO> lista = VOconver.voConverTeacher(list);
		this.setShowTeacherList( lista );
//		Ems_Teacher_VO test = teacherList.get(0);
//		String str = test.getiDnum();
//		System.out.println("输入："+str);正常输出编号
		//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		return "listTeacher";
	}
	
	/**
	 * 用于添加教师的时候取到数据库记录中的最大ID，管理员不能进行手动插入ID号
	 * @return
	 */
	public String getMaxIdNumAboutTeacher(){
		System.out.println("=============managerAction========getMaxIdNum=========");
		
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String maxIdnum = this.managerService.getMaxIdNumAboutTeacher();
		if(maxIdnum!=null ){
			int id = Integer.valueOf(maxIdnum) + 1 ;
			DecimalFormat d = new DecimalFormat("0000");
			maxIdnum = d.format(id);
		}else{
			maxIdnum = "0001";
		}
		List<ProfessionVO> list = this.managerService.listProfession();			
		this.setProfessionList(list);
		
		request.setAttribute("maxIdnum", maxIdnum);

		
		return "getMaxIdForAddTeacher";
	}
	
	
	/**
	 * 
	 * 添加教师
	 * 
	 * @return
	 */
	public String insertTeacher(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String idnum = request.getParameter("idnum");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String idcard = request.getParameter("idcard");
		String address = request.getParameter("address");
		String nation = request.getParameter("nation");
		String scientific = request.getParameter("scientific");
		String title = request.getParameter("title");
		
		Ems_Teacher_VO teacherVO = new Ems_Teacher_VO();
		
		teacherVO.setIdnum(idnum);
		teacherVO.setPassword(password);
		teacherVO.setName(name);
		switch( Integer.valueOf( sex ) ){
		case 1 :
			teacherVO.setSex("女");
			break;
		case 2 :
			teacherVO.setSex("男");
			break;
		}
		//得到编号
		String professionid = request.getParameter("pro_name");		
		ProfessionVO proVO = new ProfessionVO();
	    proVO.setIdnum(professionid);
	    //放入一个表对象,这个表对象的主键 必须有值
	    teacherVO.setProfession(proVO) ;      //插入专业编号
		teacherVO.setIdcard(idcard);
		teacherVO.setAddress(address);
		teacherVO.setNation(nation);
		teacherVO.setScientific(scientific);
		teacherVO.setTitle(title);
		this.managerService.insert(teacherVO);
		return "addTeacherSuccess";
	}
	
	
	/**
	 * 列取可以被删除的教师信息
	 * @return
	 */
	public String ListDeleteTeacher(){
		
		System.out.println("=====ManagerAction=ListDeleteTeacher==============");
		HttpServletRequest request = ServletActionContext.getRequest();
//////////////////////1/////	
int counts=this.managerService.getTeacherCounts();	//得到所有记录数	
Page page=new Page(6,counts);
int jumpPage=1;
if(request.getParameter("jumpPage")!=null ){
	jumpPage=Integer.valueOf(request.getParameter("jumpPage"));
}
if( jumpPage < 1 ){//
	page.setPageNow(1);
}else if( jumpPage >= page.getPageCount() ){
	page.setPageNow( page.getPageCount());
}else{
	page.setPageNow( jumpPage );
}
///////////////////////
		List<Ems_Teacher_VO> list = this.managerService.listTeacher(page.getPageNow());
		List<showTeacher_VO> showList = VOconver.voConverTeacher(list);
//		for( Ems_Teacher_VO vo : list ){  
//			if( "0000".equals( vo.getIdnum()) ){
//				list.remove(vo);
//				break;
//			}
//		}
		this.setShowTeacherList(showList);
		//this.setTeacherList( list );
		//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		return "ListDeleteTeacher";
	}

	

	/**
	 * 
	 * 删除教师- 实现按钮功能
	 * @return
	 */
	public String deleteTeacher(){
		System.out.println("=========ManagerAction==========deleteTeacher===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		String idnum = request.getParameter( "idnum" );
		Ems_Teacher_VO vo = new Ems_Teacher_VO();
		vo.setIdnum(idnum);
		boolean flag = this.managerService.delete( vo );
		if( false == flag ){
			return null;
		}
		
		////////////////////////////删除一个老师时，他的所有毕业设计的文件信息都要被删除///////////////////////////////////////////////////
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"
				+idnum);
		System.out.println("realpath:"+realpath);
		File teacherFile = new File(realpath);
		if(teacherFile.exists()){//如果文件存在
			System.out.println("realpath1:"+realpath);
			boolean ff = FileManage.delete(realpath);//删除目录,为什么标志返回为假
			System.out.println("标志："+ff);
		
		}
	
		return "deleteTeacherSuccess";
	}
	
	
	
	/**
	 * 列出所有可以修改的教师的信息
	 * @return
	 */
	public String listModifyTeacher(){
		System.out.println("=========ManagerAction==========listModifyManager===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		
//////////////////////1/////	
		int counts=this.managerService.getTeacherCounts();	//得到所有记录数	
		Page page=new Page(6,counts);
		int jumpPage=1;
		if(request.getParameter("jumpPage")!=null ){
			jumpPage=Integer.valueOf(request.getParameter("jumpPage"));
		}
		if( jumpPage < 1 ){//
			page.setPageNow(1);
		}else if( jumpPage >= page.getPageCount() ){
			page.setPageNow( page.getPageCount());
		}else{
			page.setPageNow( jumpPage );
		}
		///////////////////////
		List<Ems_Teacher_VO> list = this.managerService.listTeacher(page.getPageNow());
//		for( Ems_Teacher_VO vo : list ){  //admin 不能被删除
//			if( "0000".equals( vo.getIdnum()) ){
//				list.remove(vo);
//				break;
//			}
//		}
		List<showTeacher_VO> showList = VOconver.voConverTeacher(list);
//		for( Ems_Teacher_VO vo : list ){  
//			if( "0000".equals( vo.getIdnum()) ){
//				list.remove(vo);
//				break;
//			}
//		}
		this.setShowTeacherList(showList);
		//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		return "listModifyTeacher";
	}

	/**
	 * 取出这个需要修改的教师的信息
	 * @return
	 */
	public String getModifyTeacherInfo(){
		System.out.println("=========ManagerAction==========listModifyTeacher===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String idnum = request.getParameter("idnum");
		
		Ems_Teacher_VO vo = (Ems_Teacher_VO) this.managerService.getModifyTeacherInfo(idnum);
		
		request.setAttribute("teacherVO", vo);
		List<ProfessionVO> list = this.managerService.listProfession();			
		this.setProfessionList(list);
		return "showTeacherForModify";
	}
	
	/**
	 * update 　教师修改按钮实现
	 * @return
	 */
	public String updateTeacher(){
		System.out.println("====manageraction====updateTeacher=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String idnum = request.getParameter("idnum");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String idcard = request.getParameter("idcard");
		String address = request.getParameter("address");
		String nation = request.getParameter("nation");
		String scientific = request.getParameter("scientific");
		String title = request.getParameter("title");
		
		Ems_Teacher_VO teacherVO = new Ems_Teacher_VO();
		
		teacherVO.setIdnum(idnum);
		teacherVO.setPassword(password);
		teacherVO.setName(name);
		switch( Integer.valueOf( sex ) ){
		case 1 :
			teacherVO.setSex("女");
			break;
		case 2 :
			teacherVO.setSex("男");
			break;
		}
		//得到编号
		String professionid = request.getParameter("pro_name");		
		ProfessionVO proVO = new ProfessionVO();
	    proVO.setIdnum(professionid);
	    //放入一个表对象,这个表对象的主键 必须有值
	    teacherVO.setProfession(proVO) ;      //插入专业编号
		teacherVO.setIdcard(idcard);
		teacherVO.setAddress(address);
		teacherVO.setNation(nation);
		teacherVO.setScientific(scientific);
		teacherVO.setTitle(title);
		this.managerService.update( teacherVO);
		return "updateTeacherSuccess";
		
	}
	
	
	
	
	
	/***********************************学生****************************************/
	/**
	 * 查看-列出学生的信息
	 * @return
	 */
	public String listStudent(){
		System.out.println("=====ManagerAction=listManager==============");
		HttpServletRequest request = ServletActionContext.getRequest();
		//////////////////////1/////	
	       int counts=this.managerService.listStudentCounts();	//得到所有记录数	
			Page page=new Page(6,counts);
			int jumpPage=1;
			if(request.getParameter("jumpPage")!=null ){
				jumpPage=Integer.valueOf(request.getParameter("jumpPage"));
			}
			if( jumpPage < 1 ){//
				page.setPageNow(1);
			}else if( jumpPage >= page.getPageCount() ){
				page.setPageNow( page.getPageCount());
			}else{
				page.setPageNow( jumpPage );
			}
			///////////////////////
		List<StudentVO> list = this.managerService.listStudent(page.getPageNow());
		this.setStudentList( list );
		
//		Ems_Student_VO test = studentList.get(0);
//		String str = test.getiDnum();
//		System.out.println("输入："+str);//正常输出编号
		//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		return "listStudent";
	}
	
	
	/**
	 * 列取可以被删除的学生信息
	 * @return
	 */
	public String ListDeleteStudent(){
		System.out.println("=====ManagerAction=ListDeleteStudent==============");
		HttpServletRequest request = ServletActionContext.getRequest();
		//////////////////////1/////	
		//////////////////////1/////	
	       int counts=this.managerService.listStudentCounts();	//得到所有记录数	
			Page page=new Page(6,counts);
			int jumpPage=1;
			if(request.getParameter("jumpPage")!=null ){
				jumpPage=Integer.valueOf(request.getParameter("jumpPage"));
			}
			if( jumpPage < 1 ){//
				page.setPageNow(1);
			}else if( jumpPage >= page.getPageCount() ){
				page.setPageNow( page.getPageCount());
			}else{
				page.setPageNow( jumpPage );
			}
			///////////////////////
		List<StudentVO> list = this.managerService.listStudent(page.getPageNow());
//		for( StudentVO vo : list ){  //admin 不能被删除
//			if( "0000".equals( vo.getIdnum()) ){
//				list.remove(vo);
//				break;
//			}
//		}
		
		this.setStudentList( list );
		//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		return "ListDeleteStudent";
	}
	
	/**
	 * 
	 * 删除学生- 实现按钮功能
	 * @return
	 */
	public String deleteStudent(){
		System.out.println("=========ManagerAction==========deleteStudent===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		String idnum = request.getParameter( "idnum" );
		Ems_Student_VO vo = new Ems_Student_VO();
		vo.setIdnum(idnum);
		boolean flag = this.managerService.delete( vo );
		if( false == flag ){
			return null;
		}
		
		////////////////////////////删除一个学生时，他的所有与毕业设计的文件信息都要被删除///////////////////////////////////////////////////
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/student/"
				+idnum);
		System.out.println("realpath:"+realpath);
		File teacherFile = new File(realpath);
		if(teacherFile.exists()){//如果文件存在
			System.out.println("realpath1:"+realpath);
			boolean ff = FileManage.delete(realpath);//删除目录,为什么标志返回为假
			System.out.println("标志："+ff);
		
		}
		
		return "deleteStudentSuccess";
	}
	
	
	
	
	/**
	 * 用于添加学生的时候取到数据库记录专业名称
	 * @return
	 */
	public String getProfessionForAddStudent(){
		System.out.println("=============managerAction========getMaxIdNum=========");
		
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		List<ProfessionVO> list = this.managerService.listProfession();	
		

		this.setProfessionList(list);
		return "getProfessionForAddStudent";
	}
	
	
	/**
	 * 
	 * 添加学生
	 * 
	 * @return
	 */
	public String insertStudent(){
		
		HttpServletRequest request = ServletActionContext.getRequest();		
		
		
		
		//通过专业名称得到编号
//		String professionid = this.managerService.getProfession(pro_name);
		
//		System.out.println("Action层："+professionid);
		
	
		String idnum = request.getParameter("idnum");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String idcard = request.getParameter("idcard");		
		String address = request.getParameter("address");
		
	
		String nation = request.getParameter("nation");		
		String entranceDate = request.getParameter("entranceDate");
		
		
		
		Ems_Student_VO studentVO = new Ems_Student_VO();
		
		
		studentVO.setIdnum(idnum);
		studentVO.setPassword(password);
		studentVO.setName(name);
		switch( Integer.valueOf( sex ) ){
		case 1 :
			studentVO.setSex("女");
			break;
		case 2 :
			studentVO.setSex("男");
			break;
		}
		studentVO.setIdcard(idcard);
		studentVO.setAddress(address);
		
		//得到编号
		String professionid = request.getParameter("pro_name");		
		ProfessionVO proVO = new ProfessionVO();
	    proVO.setIdnum(professionid);
	    //放入一个表对象,这个表对象的主键 必须有值
		studentVO.setProfession(proVO) ;      //插入专业编号
		
		studentVO.setNation(nation);
		studentVO.setEntranceDate(entranceDate);
		this.managerService.insert(studentVO);
		return "addStudentSuccess";
	
	}
	
	
	/**
	 * 列出所有可以修改的学生的信息
	 * @return
	 */
	public String listModifyStudent(){
		System.out.println("=========ManagerAction==========listModifyStudent===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		//////////////////////1/////	
		//////////////////////1/////	
	       int counts=this.managerService.listStudentCounts();	//得到所有记录数	
			Page page=new Page(6,counts);
			int jumpPage=1;
			if(request.getParameter("jumpPage")!=null ){
				jumpPage=Integer.valueOf(request.getParameter("jumpPage"));
			}
			if( jumpPage < 1 ){//
				page.setPageNow(1);
			}else if( jumpPage >= page.getPageCount() ){
				page.setPageNow( page.getPageCount());
			}else{
				page.setPageNow( jumpPage );
			}
		List<StudentVO> list = this.managerService.listStudent(page.getPageNow());
//		for( StudentVO vo : list ){  //admin 不能被删除
//			if( "0000".equals( vo.getIdnum()) ){
//				list.remove(vo);
//				break;
//			}
//		}
		this.setStudentList( list );
		//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		return "listModifyStudent";
	}
	
	
	/**
	 * 取出这个需要修改的学生的信息
	 * 
	 * @return
	 */
	public String getModifyStudentInfo() {
		System.out
				.println("=========ManagerAction==========getModifyStudentInfo===========");
		HttpServletRequest request = ServletActionContext.getRequest();

		String idnum = request.getParameter("idnum");

		Ems_Student_VO vo = (Ems_Student_VO) this.managerService
				.getModifyStudentInfo(idnum);
		//从数据库中得到 专业列表名称
		List<ProfessionVO> list = this.managerService.listProfession();
		this.setProfessionList(list);
		
		//方便界面获取值 
		request.setAttribute("studentVO", vo);
		
		return "showStudentForModify";
	}
	
	/**
	 * update 　学生修改按钮实现
	 * @return
	 */
	public String updateStudent(){
		System.out.println("====manageraction====updateStudent=======");
	HttpServletRequest request = ServletActionContext.getRequest();		
		
		
		
		//通过专业名称得到编号
//		String professionid = this.managerService.getProfession(pro_name);
		
//		System.out.println("Action层："+professionid);
		
	
		String idnum = request.getParameter("idnum");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String idcard = request.getParameter("idcard");		
		String address = request.getParameter("address");
		
	
		String nation = request.getParameter("nation");		
		String entranceDate = request.getParameter("entranceDate");
		
		
		
		Ems_Student_VO studentVO = new Ems_Student_VO();
		
		
		studentVO.setIdnum(idnum);
		studentVO.setPassword(password);
		studentVO.setName(name);
		switch( Integer.valueOf( sex ) ){
		case 1 :
			studentVO.setSex("女");
			break;
		case 2 :
			studentVO.setSex("男");
			break;
		}
		studentVO.setIdcard(idcard);
		studentVO.setAddress(address);
		
		//通过专业名称得到编号
		String professionid = request.getParameter("pro_name");		
		ProfessionVO proVO = new ProfessionVO();
	    proVO.setIdnum(professionid);
		studentVO.setProfession(proVO) ;      //插入课程编号
		
		studentVO.setNation(nation);
		studentVO.setEntranceDate(entranceDate);
		
		
		this.managerService.update( studentVO);
		return "updateStudentSuccess";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/***************************************系统设置****************************************/
	
	/**************作品类型*****************/
	/**
	 * 列取可以被删除的作品类型信息
	 * @return
	 */
	public String WorksTypeManage(){
		System.out.println("=====ManagerAction=WorksTypeManage==============");
		List<WorkerTypeVO> list = this.managerService.listWorkertype();	
		
//		for( WorkerTypeVO vo : list ){  //admin 不能被删除
//			if( "0000".equals( vo.getIdnum()) ){
//				list.remove(vo);
//				break;
//			}
//		}
		
		this.setWorkertypeList( list );
		
//		ProfessionVO test = professionList.get(0);
//		String str = test.getIdnum();
//		System.out.println("专业号："+str);//正常输出编号
		return "WorkstypeManage";
	}
	
	/**
	 * 
	 * 删除作品类型- 实现按钮功能
	 * @return
	 */
	public String deleteWorkstype(){
		System.out.println("=========ManagerAction==========deleteWorkstype===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		String idnum = request.getParameter( "idnum" );
		WorkerTypeVO vo = new WorkerTypeVO();
		vo.setIdnum(idnum);
		boolean flag = this.managerService.delete( vo );
		if( false == flag ){
			return null;
		}
		
		return "deleteWorkstypeSuccess";
	}
	
	
	/**
	 * 
	 * 添加作品类型
	 * 
	 * @return
	 */
	public String insertWorkstype(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String maxIdnum = this.managerService.getMaxIdNumAboutWorkstype();
		if( maxIdnum!=null ){
			int id = Integer.valueOf(maxIdnum) + 1 ;
			DecimalFormat d = new DecimalFormat("0000");
			maxIdnum = d.format(id);
		}else{
			maxIdnum = "0001";
				
		}		
		
		String idnum = maxIdnum;		
		String worksName = request.getParameter("worksName");	
		
		WorkerTypeVO workertypeVO = new WorkerTypeVO();		
		workertypeVO.setIdnum(idnum);		
		workertypeVO.setWorks_name(worksName);		
		
		this.managerService.insert(workertypeVO);
		return "addWorkstypeSuccess";
	}
	
	
	
	

	/**************课程类型*****************/
	/**
	 * 列取可以被删除的课程类型信息
	 * @return
	 */
	public String ClassTypeManage(){
		System.out.println("=====ManagerAction=ClassTypeManage==============");
		List<ClassTypeVO> list = this.managerService.listClasstype();	
		
		
		
		this.setClasstypeList( list );
		
//		ProfessionVO test = professionList.get(0);
//		String str = test.getIdnum();
//		System.out.println("专业号："+str);//正常输出编号
		return "ClasstypeManage";
	}
	
	/**
	 * 
	 * 删除课程类型- 实现按钮功能
	 * @return
	 */
	public String deleteClasstype(){
		System.out.println("=========ManagerAction==========deleteClasstype===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		String idnum = request.getParameter( "idnum" );
		ClassTypeVO vo = new ClassTypeVO();
		vo.setIdnum(idnum);
		boolean flag = this.managerService.delete( vo );
		if( false == flag ){
			return null;
		}
		
		return "deleteClasstypeSuccess";
	}
	
	/**
	 * 
	 * 添加课程类型
	 * 
	 * @return
	 */
	public String insertClasstype(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String maxIdnum = this.managerService.getMaxIdNumAboutClasstype();
		if( maxIdnum!=null ){
			int id = Integer.valueOf(maxIdnum) + 1 ;
			DecimalFormat d = new DecimalFormat("0000");
			maxIdnum = d.format(id);
		}else{
			maxIdnum = "0001";
		}		
		
		String idnum = maxIdnum;		
		String classtypename = request.getParameter("classtypename");	
		
		ClassTypeVO classtypeVO = new ClassTypeVO();		
		classtypeVO.setIdnum(idnum);		
		classtypeVO.setClasstypename(classtypename);		
		
		this.managerService.insert(classtypeVO);
		return "addClasstypeSuccess";
	}
	
	
	
	
	
	/**************专业管理*****************/
	
	/**
	 * 列取可以被删除的专业信息
	 * @return
	 */
	public String ProfessionManage(){
		System.out.println("=====ManagerAction=ProfessionManage==============");
		List<ProfessionVO> list = this.managerService.listProfession();	
		
//		for( ProfessionVO vo : list ){  
//			if( "0000".equals( vo.getIdnum()) ){
//				list.remove(vo);
//				break;
//			}
//		}
//		
		this.setProfessionList( list );
		
//		ProfessionVO test = professionList.get(0);
//		String str = test.getIdnum();
//		System.out.println("专业号："+str);//正常输出编号
		return "ProfessionManage";
	}
	
	/**
	 * 
	 * 删除专业- 实现按钮功能
	 * @return
	 */
	public String deleteProfession(){
		System.out.println("=========ManagerAction==========deleteProfession===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		String idnum = request.getParameter( "idnum" );
		ProfessionVO vo = new ProfessionVO();
		vo.setIdnum(idnum);
		boolean flag = this.managerService.delete( vo );
		if( false == flag ){
			return null;
		}
		
		return "deleteProfessionSuccess";
	}
	
	
	
	/**
	 * 
	 * 添加专业
	 * 
	 * @return
	 */
	public String insertProfession(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String maxIdnum = this.managerService.getMaxIdNumAboutProfession();
		if(maxIdnum!=null ){
			int id = Integer.valueOf(maxIdnum) + 1 ;
			DecimalFormat d = new DecimalFormat("0000");
			maxIdnum = d.format(id);
		}else{
			maxIdnum = "0001";
		}		
		
		String idnum = maxIdnum;		
		String pro_name = request.getParameter("pro_name");	
		
		ProfessionVO professionVO = new ProfessionVO();		
		professionVO.setIdnum(idnum);		
		professionVO.setPro_name(pro_name);		
		
		this.managerService.insert(professionVO);
		return "addProfessionSuccess";
	}
	
	

	/**************老师添加课程时间设置       总时间设置*****************/
	/**
	 * 列取数据库中起止时间信息
	 * @return
	 */
	public String teacherAddCourse(){
		System.out.println("=====ManagerAction=teacherAddCourse==============");
		HttpServletRequest request = ServletActionContext.getRequest();
//		//得到选择的编号
//		String option = request.getParameter("");
		String idnum = request.getParameter("idnum");
		if(idnum==null || idnum.equals("")){
		request.setAttribute("a", "selected='selected'");
		}else if(idnum.equals("0001")){
			request.setAttribute("a", "selected='selected'");	
		}else if(idnum.equals("0002")){
			request.setAttribute("b", "selected='selected'");	
		}else if(idnum.equals("0003")){
			request.setAttribute("c", "selected='selected'");	
		}else if(idnum.equals("0004")){
			request.setAttribute("d", "selected='selected'");	
		}else if(idnum.equals("0005")){
			request.setAttribute("e", "selected='selected'");	
		}else if(idnum.equals("0006")){
			request.setAttribute("f", "selected='selected'");	
		}
		
		
		//默认显示的是0001的信息
		if(idnum==null || idnum.equals("")){
			idnum ="0001";
			}
		
		
		EMS_Systemset vo = this.managerService.disTeacherAddTime(idnum);	
		System.out.println("时间设置："+vo.getTimestart().toString());
		
		Ems_systemset disvo = new Ems_systemset();
		disvo.setStart(DateConventer.timestampToStr(vo.getTimestart()));
		disvo.setEnd(DateConventer.timestampToStr(vo.getTimeend()));
		
				request.setAttribute("teacherAddCourse", disvo);
		return "teacherAddCourse";
	}
	
	/**
	 * update 　修改数据库中起止时间信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String updateTeacherAddCourse() throws UnsupportedEncodingException{
		System.out.println("====manageraction====updateTeacherAddCourse=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
	
		//request.setCharacterEncoding("UTF-8");
		
		//String ttime = request.getParameter("time");
//		System.out.println("TIME:"+ttime);
		String idnum = request.getParameter("idnum");
		System.out.println("idnum:"+idnum);

		//获得名称///////////////////////////////////////////////////////////////////////
		String name  = request.getParameter("cname");
		String cname = new String(name.getBytes("ISO-8859-1"),"utf-8");
		System.out.println("名称cname:"+cname);
	///////////////////////////////////////////////////////////////////////////	
//		废弃死的
//		if(idnum.equals("0001")){
//			cname = "老师添加课程时间设置";
//		}else if(idnum.equals("0001")){
//			cname = "老师添加毕业设计时间设置";
//		}else if(idnum.equals("0001")){
//			cname = "老师录入课程成绩时间设置";
//		}else if(idnum.equals("0001")){
//			cname = "老师录入毕业设计成绩时间设置";
//		}else if(idnum.equals("0001")){
//			cname = "学生选课时间设置";
//		}else if(idnum.equals("0001")){
//			cname = "学生毕业设计选题时间设置";
//		}
		String startTime = request.getParameter("start");
		String endTime = request.getParameter("end");
		
		//String name = request.getParameter("name");

		System.out.println("start:"+startTime);
		System.out.println("end:" + endTime);
	//	System.out.println("name:" + name);
		
		EMS_Systemset vo = new EMS_Systemset();
		
		//设置参数
		vo.setTimestart(DateConventer.strToTimestamp(startTime));
		vo.setTimeend(DateConventer.strToTimestamp(endTime));
		///////////////////////////////////////////////////////////////////////////////////////////////////
		vo.setIdnum(idnum);//01是标准规定
		vo.setSyssetname(cname);//昨到系统设置名称
	
		this.managerService.update( vo);
		
		Ems_systemset disvo = new Ems_systemset();
		disvo.setStart(DateConventer.timestampToStr(vo.getTimestart()));
		disvo.setEnd(DateConventer.timestampToStr(vo.getTimeend()));
		request.setAttribute("teacherAddCourse", disvo);
		
		return "updateTeacherAddCourseSucc";
		
	}
	
	
	/**************老师录入成绩时间设置*****************/
	/**
	 * 列取数据库中起止时间信息
	 * @return
	 */
	public String teacherInputGrade(){
		System.out.println("=====ManagerAction=teacherInputGrade==============");
		EMS_Systemset vo = this.managerService.disTeacherInputGrade();	
		System.out.println("老师录入成绩时间设置："+vo.getTimestart().toString());
		
		Ems_systemset disvo = new Ems_systemset();		
		disvo.setStart(DateConventer.timestampToStr(vo.getTimestart()));
		disvo.setEnd(DateConventer.timestampToStr(vo.getTimeend()));
		
		HttpServletRequest request = ServletActionContext.getRequest();		
		request.setAttribute("teacherInputGrade", disvo);
		return "teacherInputGrade";
	}
	
	/**
	 * update 　修改数据库中起止时间信息
	 * @return
	 */
	public String updateTeacherInputGrade(){
		System.out.println("====manageraction====updateTeacherInputGrade=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String startTime = request.getParameter("start");
		String endTime = request.getParameter("end");

		
		
		EMS_Systemset vo = new EMS_Systemset();
		vo.setTimestart(DateConventer.strToTimestamp(startTime));
		vo.setTimeend(DateConventer.strToTimestamp(endTime));
		vo.setIdnum("02");//02是标准规定
		
	
		this.managerService.update( vo);
		Ems_systemset disvo = new Ems_systemset();		
		disvo.setStart(DateConventer.timestampToStr(vo.getTimestart()));
		disvo.setEnd(DateConventer.timestampToStr(vo.getTimeend()));
		request.setAttribute("teacherInputGrade", disvo);
		
		return "updateTeacherInputGradeSucc";
		
	}
	
	
	
	/**************学生选课时间设置*****************/
	/**
	 * 列取数据库中起止时间信息
	 * @return
	 */
	public String studentTakeCourse(){
		System.out.println("=====ManagerAction=studentTakeCourse==============");
		EMS_Systemset vo = this.managerService.disStudentTakeCourse();	
		
		Ems_systemset disvo = new Ems_systemset();	
		disvo.setStart(DateConventer.timestampToStr(vo.getTimestart()));
		disvo.setEnd(DateConventer.timestampToStr(vo.getTimeend()));
	
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("studentTakeCourse", disvo);
		return "studentTakeCourse";
	}
	
	/**
	 * update 　修改数据库中起止时间信息
	 * @return
	 */
	public String updateStudentTakeCourse(){
		System.out.println("====manageraction====updateStudentTakeCourse=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String startTime = request.getParameter("start");
		String endTime = request.getParameter("end");

		
		
		EMS_Systemset vo = new EMS_Systemset();
		vo.setTimestart(DateConventer.strToTimestamp(startTime));
		vo.setTimeend(DateConventer.strToTimestamp(endTime));
		vo.setIdnum("03");//03是标准规定
		
	
		this.managerService.update( vo);
		
		Ems_systemset disvo = new Ems_systemset();	
		disvo.setStart(DateConventer.timestampToStr(vo.getTimestart()));
		disvo.setEnd(DateConventer.timestampToStr(vo.getTimeend()));
		request.setAttribute("studentTakeCourse", disvo);
	
		return "updateStudentTakeCourseSucc";
		
	}
	
	
	/******************************审核请求管理**********************************************/
	/*******审核学生请求信息***********/
	public String auditStudentInfor() {
		List<ApplyModifyLogVO> applyModify = this.managerService.disAditInfor("审核中","ems_student");
		
		List<showApplyModifyLogVO> disvo = VOconver.voConverGb(applyModify);
		this.setApplyModifyList(disvo);
		
		return "auditStudentInfor";
	}
	
	/*********审核老师请求信息*********/
	public String auditTeacherInfor() {
		List<ApplyModifyLogVO> applyModify = this.managerService.disAditInfor("审核中","ems_teacher");
		
		List<showApplyModifyLogVO> disvo = VOconver.voConverGb(applyModify);
		this.setApplyModifyList(disvo);
		
		return "auditTeacherInfor";
	}
	/*******查询学生审核记录***********/
	public String searchStudentInfor() {
		List<ApplyModifyLogVO> applyModify = this.managerService.disAditInfor("未通过","ems_student");
		List<ApplyModifyLogVO> applyModify1 = this.managerService.disAditInfor("已通过","ems_student");
		applyModify.addAll(applyModify1);
		
		//排序
		 Collections.sort(applyModify,new ApplyTeacherComparator());
		 
		List<showApplyModifyLogVO> disvo = VOconver.voConverGb(applyModify);
		
		this.setApplyModifyList(disvo);
		
		return "searchStudentInfor";
	}
	
	/*******查询老师审核记录***********/
	public String searchTeacherInfor() {
		List<ApplyModifyLogVO> applyModify = this.managerService.disAditInfor("未通过","ems_teacher");
		List<ApplyModifyLogVO> applyModify1 = this.managerService.disAditInfor("已通过","ems_teacher");
		applyModify.addAll(applyModify1);
		
		//排序
		 Collections.sort(applyModify,new ApplyTeacherComparator());
		 
		List<showApplyModifyLogVO> disvo = VOconver.voConverGb(applyModify);
		this.setApplyModifyList(disvo);
		
		return "searchTeacherInfor";
	}

	/*******审核学生功能通过按钮实现
	 * @throws UnsupportedEncodingException ***********/
	public String managerAuditStudentPass() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//申请表中编号]
		 String applyid = request.getParameter("applyid");
		 System.out.println("申请编号:"+applyid);
		
		String studentid = request.getParameter("studentid");
		String name_temp = request.getParameter("username_temp");
		//中文编码处理问题
		//String cname = new String(name.getBytes("ISO-8859-1"),"utf-8");
		name_temp = new String(name_temp.getBytes("ISO-8859-1"),"utf-8");
		String idcard_temp = request.getParameter("idcard_temp");
		
		Ems_Student_VO studentVO = this.managerService.getModifyStudentInfo(studentid);
		//studentVO.setIdnum(studentid);
		studentVO.setName(name_temp);
		studentVO.setIdcard(idcard_temp);
		studentVO.setIdcrad_temp("");
		studentVO.setUsername_temp("");
		ApplyModifyLogVO applyVO = this.managerService.getAuditInfor(applyid);
		//审核状态
		applyVO.setModifyResult("已通过");
		Timestamp auditTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		//审核时间 
		applyVO.setAuditDate(auditTime);
		//审核人
		String auditMan = (String)session.getAttribute("manageridnum");
		System.out.println("审核人:" +auditMan);
		//Ems_Manager_VO managerVO = new Ems_Manager_VO();
		//managerVO.setIdnum(auditMan);
		applyVO.setAuditMan(auditMan);
		
		 System.out.println("用户编号"+applyVO.getApplyUserId());

		 this.managerService.update(studentVO);
		 this.managerService.update(applyVO);
	
	
		 return "auditSuccess";
		
	}
	/*******审核学生功能不通过按钮实现
	 * @throws UnsupportedEncodingException ***********/
	public String managerAuditStudentNoPass() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//申请表中编号]
		 String applyid = request.getParameter("applyid");
		 System.out.println("申请编号:"+applyid);
		
		String studentid = request.getParameter("studentid");
		///String name_temp = request.getParameter("username_temp");
		//中文编码处理问题
		//String cname = new String(name.getBytes("ISO-8859-1"),"utf-8");
		///name_temp = new String(name_temp.getBytes("ISO-8859-1"),"utf-8");
		//String idcard_temp = request.getParameter("idcard_temp");
		//审核的意见
		String attitude = request.getParameter("attitude");
		attitude = new String(attitude.getBytes("ISO-8859-1"),"utf-8");
		
		Ems_Student_VO studentVO = this.managerService.getModifyStudentInfo(studentid);
		//studentVO.setIdnum(studentid);
		//studentVO.setName(name_temp);
	//	studentVO.setIdcard(idcard_temp);
		studentVO.setIdcrad_temp("");
		studentVO.setUsername_temp("");
		ApplyModifyLogVO applyVO = this.managerService.getAuditInfor(applyid);
		//审核状态
		applyVO.setModifyResult("未通过");
		Timestamp auditTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		//审核时间 
		applyVO.setAuditDate(auditTime);
		//审核的意见
		applyVO.setRemark(attitude);	
		//审核人，有外键参照
		String auditMan = (String)session.getAttribute("manageridnum");
		System.out.println("审核人:" +auditMan);
	//	Ems_Manager_VO managerVO = new Ems_Manager_VO();
		//managerVO.setIdnum(auditMan);
		applyVO.setAuditMan(auditMan);
		
		 System.out.println("用户编号"+applyVO.getApplyUserId());

		 this.managerService.update(studentVO);
		 this.managerService.update(applyVO);
	
	
		return "auditSuccess";
		
	}
	/**
	 * 学生的审核按钮
	 * @return
	 */
   public String auditStudent(){
	   HttpServletRequest request = ServletActionContext.getRequest();
	   String studentid = request.getParameter("user");
	   String applyid = request.getParameter("idnum");
	   System.out.println("申请表编号"+applyid);
	   System.out.println("学号"+studentid);
	   Ems_Student_VO studentVO = this.managerService.getModifyStudentInfo(studentid);
	   request.setAttribute("studentvo", studentVO);
	   request.setAttribute("applyid", applyid);
	   return "auditStudentView";	   
   }
   
   /**
    * 教师的审核按钮
    * @return
    */
  public String auditTeacher(){
	   
	  HttpServletRequest request = ServletActionContext.getRequest();
	   String teacherid = request.getParameter("user");
	   String applyid = request.getParameter("idnum");
	   System.out.println("申请表编号"+applyid);
	   System.out.println("教师编号"+teacherid);
	   Ems_Teacher_VO teacherVO = this.managerService.getModifyTeacherInfo(teacherid);
	   request.setAttribute("teacherVO", teacherVO);
	   request.setAttribute("applyid", applyid);
	   return "auditTeacherView";	     
   }
   
  /*******审核教师功能通过按钮实现
	 * @throws UnsupportedEncodingException ***********/
	public String managerAuditTeacherPass() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//申请表中编号]
		 String applyid = request.getParameter("applyid");
		 System.out.println("申请编号:"+applyid);
		
		String teacherid = request.getParameter("studentid");
		String name_temp = request.getParameter("username_temp");
		//中文编码处理问题
		//String cname = new String(name.getBytes("ISO-8859-1"),"utf-8");
		name_temp = new String(name_temp.getBytes("ISO-8859-1"),"utf-8");
		String idcard_temp = request.getParameter("idcard_temp");
		
		Ems_Teacher_VO teacherVO = this.managerService.getModifyTeacherInfo(teacherid);
		//studentVO.setIdnum(studentid);
		teacherVO.setName(name_temp);
		teacherVO.setIdcard(idcard_temp);
		teacherVO.setIdcard_temp("");
		teacherVO.setName_temp("");
		ApplyModifyLogVO applyVO = this.managerService.getAuditInfor(applyid);
		//审核状态
		applyVO.setModifyResult("已通过");
		Timestamp auditTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		//审核时间 
		applyVO.setAuditDate(auditTime);
		//审核人
		String auditMan = (String)session.getAttribute("manageridnum");
		System.out.println("审核人:" +auditMan);
	//	Ems_Manager_VO managerVO = new Ems_Manager_VO();
		//managerVO.setIdnum(auditMan);
		applyVO.setAuditMan(auditMan);
		
		 System.out.println("用户编号"+applyVO.getApplyUserId());

		 this.managerService.update(teacherVO);
		 this.managerService.update(applyVO);
	
	
		 return "auditSuccess";
		
	}
	/*******审核教师功能不通过按钮实现
	 * @throws UnsupportedEncodingException ***********/
	public String managerAuditTeacherNoPass() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//申请表中编号]
		 String applyid = request.getParameter("applyid");
		 System.out.println("申请编号:"+applyid);
		
		String teacherid = request.getParameter("studentid");
		///String name_temp = request.getParameter("username_temp");
		//中文编码处理问题
		//String cname = new String(name.getBytes("ISO-8859-1"),"utf-8");
		///name_temp = new String(name_temp.getBytes("ISO-8859-1"),"utf-8");
		//String idcard_temp = request.getParameter("idcard_temp");
		//审核的意见
		String attitude = request.getParameter("attitude");
		attitude = new String(attitude.getBytes("ISO-8859-1"),"utf-8");
		
		Ems_Teacher_VO teacherVO = this.managerService.getModifyTeacherInfo(teacherid);
		//studentVO.setIdnum(studentid);
		//studentVO.setName(name_temp);
	//	studentVO.setIdcard(idcard_temp);
		teacherVO.setIdcard_temp("");
		teacherVO.setName_temp("");
		ApplyModifyLogVO applyVO = this.managerService.getAuditInfor(applyid);
		//审核状态
		applyVO.setModifyResult("未通过");
		Timestamp auditTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		//审核时间 
		applyVO.setAuditDate(auditTime);
		//审核的意见
		applyVO.setRemark(attitude);	
		//审核人，有外键参照
		String auditMan = (String)session.getAttribute("manageridnum");
		System.out.println("审核人:" +auditMan);
		//Ems_Manager_VO managerVO = new Ems_Manager_VO();
		//managerVO.setIdnum(auditMan);
		applyVO.setAuditMan(auditMan);
		
		 System.out.println("用户编号"+applyVO.getApplyUserId());

		 this.managerService.update(teacherVO);
		 this.managerService.update(applyVO);
	
	
		return "auditSuccess";
		
	}

 
  
	//------------------all getter and setter 
	
	
	public List<ProfessionVO> getProfessionList() {
		return professionList;
	}

	public void setProfessionList(List<ProfessionVO> professionList) {
		this.professionList = professionList;
	}

	
	public List<Ems_Manager_VO> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<Ems_Manager_VO> managerList) {
		this.managerList = managerList;
	}
	
	
	public List<Ems_Teacher_VO> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Ems_Teacher_VO> teacherList) {
		this.teacherList = teacherList;
	}

	public List<StudentVO> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentVO> studentList) {
		this.studentList = studentList;
	}

	public List<WorkerTypeVO> getWorkertypeList() {
		return workertypeList;
	}

	public void setWorkertypeList(List<WorkerTypeVO> workertypeList) {
		this.workertypeList = workertypeList;
	}

	public List<ClassTypeVO> getClasstypeList() {
		return classtypeList;
	}

	public void setClasstypeList(List<ClassTypeVO> classtypeList) {
		this.classtypeList = classtypeList;
	}
	public List<showApplyModifyLogVO> getApplyModifyList() {
		return applyModifyList;
	}
	public void setApplyModifyList(List<showApplyModifyLogVO> applyModifyList) {
		this.applyModifyList = applyModifyList;
	}
	public List<showTeacher_VO> getShowTeacherList() {
		return showTeacherList;
	}
	public void setShowTeacherList(List<showTeacher_VO> showTeacherList) {
		this.showTeacherList = showTeacherList;
	}

	


	
}
