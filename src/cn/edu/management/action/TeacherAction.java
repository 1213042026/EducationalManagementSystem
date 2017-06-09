package cn.edu.management.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.edu.management.comm.ApplyTeacherComparator;
import cn.edu.management.comm.DateConventer;
import cn.edu.management.comm.FileManage;
import cn.edu.management.comm.Page;
import cn.edu.management.comm.VOconver;
import cn.edu.management.comm.disAuditGraduationResultComparator;
import cn.edu.management.service.ManagerService;
import cn.edu.management.service.TeacherService;
import cn.edu.management.service.UserService;
import cn.edu.management.vo.showVO.EMS_GraduationA;
import cn.edu.management.vo.showVO.GraduationStudentInfor;
import cn.edu.management.vo.showVO.ShowMessageVO;
import cn.edu.management.vo.showVO.StudentGraduationWorksMange;
import cn.edu.management.vo.showVO.disAuditGraduationResult;
import cn.edu.management.vo.showVO.disAuditGraduationTitle;
import cn.edu.management.vo.showVO.showAlreadyAddCourse;
import cn.edu.management.vo.showVO.showApplyModifyLogVO;
import cn.edu.management.vo.showVO.studentGradeSearch;
import cn.edu.management.vo.voImpl.ApplyModifyLogVO;
import cn.edu.management.vo.voImpl.ClassTypeVO;
import cn.edu.management.vo.voImpl.EMS_Class;
import cn.edu.management.vo.voImpl.EMS_CourseRecord;
import cn.edu.management.vo.voImpl.EMS_GraduateGrade;
import cn.edu.management.vo.voImpl.EMS_Graduation;
import cn.edu.management.vo.voImpl.Ems_Student_VO;
import cn.edu.management.vo.voImpl.Ems_Teacher_VO;
import cn.edu.management.vo.voImpl.MessageVO;
import cn.edu.management.vo.voImpl.ProfessionVO;

import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class TeacherAction extends ActionSupport {

	private List<EMS_GraduationA> graduateaList;//方便界面显示
	private List<showApplyModifyLogVO> applyModifyList;//申请信息记录表
	private List<ProfessionVO>   professionList;//专业
	private List<Ems_Teacher_VO> teacherList ;//教师
	private List<GraduationStudentInfor>  ggStudentInforList;//毕业设计学生信息界面类
	private List<StudentGraduationWorksMange>  StudentGraduationWorksMange;//毕业设计学生信息界面类
	private List<studentGradeSearch>  studentGradeSearchList;//毕业设计学生信息界面类
	private List<disAuditGraduationTitle> disAuditGraTit;//显示学生毕业设计申请题目
	private List<disAuditGraduationResult> disAuditGraResult;//显示学生毕业设计审核结果
	//private List<InputGraduationGrade>   disInputGraduationGrade;//显示录入毕业设计成绩界面类
	
	private List<ShowMessageVO> disMessageVO;//显示已回复留言
	
	private List<showAlreadyAddCourse> courseList;//显示已添加课程
	private List<ClassTypeVO> courseTypeLit;
	
	private String[] fileList;   //显示文件列表使用
	
	/**上传文件的接收类型，名字要与表单相应元素的name属性值*/
	private File image;
	/**上传文件的名字，必须为表单的name加上FileName字符串*/
	private String imageFileName;

	
	
	private TeacherService teacherService;
	private UserService userService;
	private ManagerService managerService;
	
	@Resource
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}
	
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Resource
	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}

	/**
	 *  局部刷新验证毕业设计名称是否存在
	 * @return
	 * @throws IOException 
	 */
	public void checkGname() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String account = request.getParameter("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		try	{
			
			response.setCharacterEncoding("utf-8");
			if(account.equals("")||account==null)
				response.getWriter().print("{\"result\":\"space\"}");
			else
			{
				boolean flag = this.teacherService.checkGname(account);
				System.out.println("flag:"+flag);
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
	
	
	
	
	
	/******************************************个人信息维护***********************************************/
	/*****查看个人信息*****/
	public String displayPersonInformation() {

		System.out
				.println("=========TeacherAction==========displayPersonInformation===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		 //从request中得到session
		  HttpSession session = request.getSession(); 
		 String idnum = (String)session.getAttribute("teacheridnum");/////////
		 System.out.println("得到的编号："+idnum);		 
         
		Ems_Teacher_VO vo = (Ems_Teacher_VO) this.teacherService.displayPersonInformation(idnum);
        String profession = vo.getProfession().getPro_name();
        request.setAttribute("profession", profession);
		request.setAttribute("teacherVO", vo);
		
		//因为在显示时没有密码,方便修改时使用
        session.setAttribute("pwd", vo.getPassword());
		return "PersonInformationQuery";
	}
	
	
	/**
	 * update 　教师查看个人信息修改地址按钮实现
	 * @return
	 */
	public String updateTeacherPersonInfor(){
		System.out.println("====teacheraction====updateTeacherPersonInfor=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession   session = request.getSession();
		
		String idnum = request.getParameter("idnum");
		String address = request.getParameter("address");
		//////////////////////////////////////////////////////////
//		String password = request.getParameter("password");
		String password = (String)session.getAttribute("pwd");
		
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String idcard = request.getParameter("idcard");
		String nation = request.getParameter("nation");
		String scientific = request.getParameter("scientific");
		String title = request.getParameter("title");
		
		Ems_Teacher_VO teacherVO = this.managerService.getModifyTeacherInfo(idnum);
		///teacherVO.setIdnum(idnum);
		///teacherVO.setPassword(password);
		///teacherVO.setName(name);
	//	switch( Integer.valueOf( sex ) ){
		//case 1 :
			teacherVO.setSex(sex);
		//	break;
		//case 2 :
		//	teacherVO.setSex("男");
		//	break;
		//}
		///teacherVO.setIdcard(idcard);
		teacherVO.setAddress(address);
		///teacherVO.setNation(nation);
		///teacherVO.setScientific(scientific);
		///teacherVO.setTitle(title);	
		request.setAttribute("teacherVO", teacherVO);//方便界面显示修改的
		this.teacherService.update( teacherVO);
		return "updateTeacherPersonInfor";
		
	}
	
	/**
	 * 
	 *修改密码
	 * @return
	 */
	public String updateTeacherPassword() {
		System.out.println("====teacheraction====updateTeacherPassword=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Ems_Teacher_VO teacherVO = new Ems_Teacher_VO();
		String userId = (String)session.getAttribute("teacheridnum");
		String password = request.getParameter("prePassword");
		String newpwd = request.getParameter("newPassword");
		teacherVO.setIdnum( userId );
		teacherVO.setPassword( password );
		
		teacherVO = this.userService.validate_teacher( teacherVO );
		
		if( null == teacherVO ){
			request.setAttribute("msg", "wrong");//用于旧密码不正确
			return "prePasswordWorng";
		}else{

			//从数据库中取出这个编号的所有信息
			Ems_Teacher_VO vo = (Ems_Teacher_VO) this.teacherService.displayPersonInformation(userId);
			vo.setPassword(newpwd);//设置要修改的新密码
			this.teacherService.update(vo);//更新密码
			
		}
		return "updatePwdSuccess";
	}
	

	/******************************************毕业设计管理***********************************************/
	/**
	 * 
	 * 查看已添加毕业设计
	 * 
	 * @return
	 */
	public String displayAlreadyAdd(){
		System.out.println("====teacheraction====displayAlreadyAdd=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("teacheridnum");
		System.out.println("用户名："+userId);
		
	//////////////////////1/////	
       int counts=this.teacherService.getGrauationCounts(userId);		
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
		
		List<EMS_Graduation> graduate = this.teacherService.displayAlreadyAddPage(userId,page.getPageNow());//2也改了
		
		//将得到表VO类，转为显示的界面VO类
		this.setGraduateaList(VOconver.voConverGa(graduate));
		
		
		//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		
		
		return "displayAlreadyAdd";
	}

	//getMaxIdNumAboutGraduate
	/**
	 * 用于添加毕业题目的时候取到数据库记录中的最大ID，管理员不能进行手动插入ID号
	 * @return
	 */
	public String getMaxIdNumAboutGraduate(){
		System.out.println("=============managerAction========getMaxIdNum=========");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		///////////////////////////////////////////////////////////////////////////
		boolean result = this.teacherService.checkTime("老师添加毕业设计时间设置");
		if(!result)
		  return "noAddGraduation";
		
		String maxIdnum = this.teacherService.getMaxIdNumAboutGraduate();
		if( maxIdnum!=null ){
			int id = Integer.valueOf(maxIdnum) + 1 ;
			DecimalFormat d = new DecimalFormat("0000");
			maxIdnum = d.format(id);
		}else{
			maxIdnum = "0001";
		}
		request.setAttribute("maxIdnum", maxIdnum);
		//得到专业
		List<ProfessionVO> list = this.managerService.listProfession();	
		this.setProfessionList(list);
		//得到教师
		List<Ems_Teacher_VO> teacherLit = this.managerService.listTeacher();
		this.setTeacherList(teacherLit);
		
		return "AddGraduationTitle";
	}
	
	/*************添加毕业设计按钮功能实现
	 * @throws UnsupportedEncodingException ************/
	public String AddGraduationTitle() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String idnum = request.getParameter("idnum");//编号 
		String diff1 = request.getParameter("diff");//难易程度
		String diff = new String(diff1.getBytes("ISO-8859-1"),"utf-8");
		System.out.println("程度："+diff);
		String gname1 = request.getParameter("name");//名称
		String gname = new String(gname1.getBytes("ISO-8859-1"),"utf-8");
		String kman = request.getParameter("kman");//可选 人数
		String tname = (String)session.getAttribute("teacheridnum");//教师编号 
		//String pname = request.getParameter("pro_name");//专业编号
	    String att1 = request.getParameter("att");
		String att = new String(att1.getBytes("ISO-8859-1"),"utf-8");//得到选题须知
		
		 EMS_Graduation graduationVO = new EMS_Graduation();
		 graduationVO.setGlevel(diff);
		 graduationVO.setFlag("teacher");
		 graduationVO.setIdnum(idnum);//
		 graduationVO.setGname(gname);//
		 Ems_Teacher_VO teacherVO = new Ems_Teacher_VO();
		 teacherVO.setIdnum(tname);
		 graduationVO.setTeacherId(teacherVO);//
		// ProfessionVO professionVO = new ProfessionVO();
		// professionVO.setIdnum(pname);
	//	 graduationVO.setProfessionId(professionVO);//
		 graduationVO.setGcount(kman);//
		 graduationVO.setRcount(kman);//
		 graduationVO.setRecordTime(DateConventer.strToTimestamp(DateConventer.dateToStr(new Date())));//
		 graduationVO.setRemark(att);//须知
	
		 this.teacherService.insert(graduationVO);
		 
		 return "AddGraduationTitleSucc";
	}
	
	/**
	 * 
	 * 显示修改已添加毕业设计列表
	 * 
	 * @return
	 */
	public String updateAlreadyAdd(){
		
		System.out.println("====teacheraction====displayAlreadyAdd=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("teacheridnum");
		System.out.println("用户名："+userId);
		//
		int counts=this.teacherService.getGrauationCounts(userId);		
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
		}//
		
		
		List<EMS_Graduation> graduate = this.teacherService.displayAlreadyAddPage(userId,page.getPageNow());
		//将得到表VO类，转为显示的界面VO类
		this.setGraduateaList(VOconver.voConverGa(graduate));
//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		return "updateAlreadyAdd";
	}

	/**
	 * 取出这个需要修改的毕业设计的信息
	 * 
	 * @return
	 */
	public String disupdateAlreadyAdd() {
		System.out
				.println("=========TeacherAction==========disupdateAlreadyAdd===========");
		HttpServletRequest request = ServletActionContext.getRequest();

		String idnum = request.getParameter("idnum");

		EMS_Graduation graduationVO = (EMS_Graduation) this.teacherService
		.getModifyGraduationInfo(idnum);
		
		//得到专业
		List<ProfessionVO> list = this.managerService.listProfession();	
		this.setProfessionList(list);
		//得到教师
		List<Ems_Teacher_VO> teacherLit = this.managerService.listTeacher();
		this.setTeacherList(teacherLit);

		// 方便界面获取值
		request.setAttribute("graduationVO", graduationVO);

		return "showGraduationForModify";
	}
	
	/**
	 * 修改按钮毕业设计功能按钮实现
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String updateButtonGraduation() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String idnum = request.getParameter("idnum");//编号 
		String gname1 = request.getParameter("name");//名称
		String gname = new String(gname1.getBytes("ISO-8859-1"),"utf-8");//得到选题须知
		String kman = request.getParameter("kman");//可选 人数
		
		String diff1 = request.getParameter("diff");//难易程度
		String diff = new String(diff1.getBytes("ISO-8859-1"),"utf-8");
		
		String pname = request.getParameter("pro_name");//专业编号
	    String att1 = request.getParameter("att");
		String att = new String(att1.getBytes("ISO-8859-1"),"utf-8");//得到选题须知
		
		 EMS_Graduation graduationVO = this.teacherService.getModifyGraduationInfo(idnum);	
		
		 graduationVO.setGname(gname);//
		 
		 graduationVO.setGlevel(diff);
		 graduationVO.setFlag("teacher");
	
		 ProfessionVO professionVO = new ProfessionVO();
		 professionVO.setIdnum(pname);
		// graduationVO.setProfessionId(professionVO);//
		 //这种做法,只适合于学生还没有开始选课的情况下
		 graduationVO.setGcount(kman);//
		 graduationVO.setRcount(kman);//		 
		
		 graduationVO.setRemark(att);//须知
	
		 this.teacherService.update(graduationVO);
		 
		 return "updateGraduationTitleSucc";
	}
	
	
	/**
	 * 
	 * 显示要删除已添加毕业设计列表
	 * 
	 * @return
	 */
	public String deleteAlreadyAdd(){
		System.out.println("====teacheraction====deleteAlreadyAdd=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("teacheridnum");
		System.out.println("用户名："+userId);
		
		 int counts=this.teacherService.getGrauationCounts(userId);		
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
			
			List<EMS_Graduation> graduate = this.teacherService.displayAlreadyAddPage(userId,page.getPageNow());//2也改了
		//将得到表VO类，转为显示的界面VO类
		this.setGraduateaList(VOconver.voConverGa(graduate));
		
//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		
		return "deleteAlreadyAdd";
	}

	/****
	 * 删除毕业设计功能按钮实现
	 */
	public String disdeleteAlreadyAdd() {
		System.out.println("====teacheraction====disdeleteAlreadyAdd=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
	    String idnum = request.getParameter("idnum");
	    String tidnum = (String)session.getAttribute("teacheridnum");
	    EMS_Graduation graduationVO = new EMS_Graduation();
	    graduationVO.setIdnum(idnum);
	    this.teacherService.delete(graduationVO);
	    
		////////////////////////////删除一个毕业设计时，这个老师他关于这个毕业设计的所有文件信息都要被删除///////////////////////////////////////////////////
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"
				+tidnum+"/"+idnum);
		System.out.println("realpath:"+realpath);
		File teacherFile = new File(realpath);
		if(teacherFile.exists()){//如果文件存在
			System.out.println("realpath1:"+realpath);
			boolean ff = FileManage.delete(realpath);//删除目录,为什么标志返回为假
			System.out.println("标志："+ff);
		
		}
		return "disdeleteAlreadyAdd";
	}
	
	/**
	 * 老师文件管理页面
	 */
	public String teacherFileManage(){
		System.out.println("====teacheraction====teacherFileManage=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//得到老师编号
		String userId = (String)session.getAttribute("teacheridnum");
		System.out.println("用户名："+userId);
//////////////////////1/////	
	       int counts=this.teacherService.getGrauationCounts(userId);		
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
			
			List<EMS_Graduation> graduate = this.teacherService.displayAlreadyAddPage(userId,page.getPageNow());//2也改了
			
//////////////////////////////////3///////////////////////////////////
			request.setAttribute( "pageCount", page.getPageCount() );
			request.setAttribute( "pageNow", page.getPageNow() );
			request.setAttribute( "maxRowCount", page.getMaxRowCount() );
			request.setAttribute( "pageSize", page.getPageSize() );
			//////////////////////////////////////////////////////////////////////
		//将得到表VO类，转为显示的界面VO类
		this.setGraduateaList(VOconver.voConverGa(graduate));
		return "teacherFileManage";
	}
	
	
	/**
	 * 显示老师文件的详细列表
	 * @return
	 */
	public String disTeacherFileList(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String gidnum = request.getParameter("idnum");//得到毕业设计编号
		String id = request.getParameter("user");//学生身份验证
		if(id!=null && id.equals("student")){
			System.out.println("学生");
			request.setAttribute("studentID", "value");//学生页面显示的jsp
		}
		System.out.println("毕设编号："+gidnum);
		String tidnum = (String)session.getAttribute("teacheridnum");//得到老师编号
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"+tidnum +"/"+ gidnum);
		File soure = new File(realpath);
		String[]fileLista = null;
		if(soure.exists()){//如果目录不为空，就从指定目录获取 
		fileLista  = soure.list();			
		}else{
			System.out.println("空");
		}
		//将文件对象argF所对应的目录下的所以文件与都变成文件对象
		//File[]file = argF.listFiles();	
		//System.out.println("文件："+fileLista[0]);
	//	request.setAttribute("fileList", fileLista);
		//request.setAttribute("a", "a");
		request.setAttribute("graID", gidnum);
		this.setFileList(fileLista);
		return "fileUpLoadTest";
	}

	
	
	/**
	 * 文件上传test
	 * @throws IOException 
	 */
	public String fileUpLoadTest() throws IOException {
		
		//得到应用程序的绝对路径再与用户的目录组成字符串
		//那个教师，第一个0001应该是得到的教师的编号，可以通过session得到
		//那个毕业设计，每二个0001应该是毕业设计的编号,可以通过单条列表记录得到
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String gidnum = request.getParameter("graID");//得到毕业设计编号
		System.out.println("gidnum:"+gidnum);
		String tidnum = (String)session.getAttribute("teacheridnum");//得到老师编号
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"+tidnum +"/"+ gidnum);
	    System.out.println("realpath:"+realpath);
//		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/0001/0001");
//      /  System.out.println(realpath);		
	    if(image != null) {
	    	File savefi = new File(realpath);
	    	if(!savefi.exists())
	    		savefi.mkdirs();
	    	//在服务器的images目录中建立相应的文件
	    	File savefile = new File(savefi,imageFileName);
	    	//如何父路径不存在，就在服务器上建立好目录
	    	//if(!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
	    	
	    	//复制文件，将要传的文件的内容复制到服务器上建好的文件
	    	FileUtils.copyFile(image, savefile);
	    		    	
	    }

	    request.setAttribute("idnum", gidnum);//方便返回时使用
	return "fileUpLoadTestSucc";	
	}
	
	/**
	 * 添加毕业设计是时，文件上传test
	 * @throws IOException 
	 */
	public void fileUpLoadTestAdd() throws IOException {
		
		//得到应用程序的绝对路径再与用户的目录组成字符串
		//那个教师，第一个0001应该是得到的教师的编号，可以通过session得到
		//那个毕业设计，每二个0001应该是毕业设计的编号,可以通过单条列表记录得到
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String gidnum = request.getParameter("graID");//得到毕业设计编号
		System.out.println("gidnum:"+gidnum);
		String tidnum = (String)session.getAttribute("teacheridnum");//得到老师编号
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"+tidnum +"/"+ gidnum);
	    System.out.println("realpath:"+realpath);
//		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/0001/0001");
//      /  System.out.println(realpath);		
	    if(image != null) {
	    	File savefi = new File(realpath);
	    	if(!savefi.exists())
	    		savefi.mkdirs();
	    	//在服务器的images目录中建立相应的文件
	    	File savefile = new File(savefi,imageFileName);
	    	//如何父路径不存在，就在服务器上建立好目录
	    	//if(!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
	    	
	    	//复制文件，将要传的文件的内容复制到服务器上建好的文件
	    	FileUtils.copyFile(image, savefile);
	    		    	
	    }

	 //   request.setAttribute("idnum", gidnum);//方便返回时使用
	//return "fileUpLoadTestAdd";	
	}
	
	/**
	 * 删除老师上传的文件
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String delTeacherUpFile() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String gidnum = request.getParameter("gidnum");//得到毕业设计编号
		System.out.println("DELgidnum:"+gidnum);
		String tidnum = (String)session.getAttribute("teacheridnum");//得到老师编号
		String gname1 = request.getParameter("gname");//得到毕设名
		String gname = new String(gname1.getBytes("ISO-8859-1"),"utf-8");//得到选题须知
		System.out.println("Gname:"+gname);
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"+tidnum +"/"+ gidnum);
		System.out.println("realpath:"+realpath);
		File realPath =new File(realpath);
		File delFile = new File(realPath,gname);
		delFile.delete();
		 request.setAttribute("idnum", gidnum);//方便返回时使用
	  return "delTeacherUpFileSucc";
	}
	

	
	public String downfiletest1() throws IOException {
	
//		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpSession session = request.getSession();
//		String gidnum = request.getParameter("gidnum");//得到毕业设计编号
//		System.out.println("DELgidnum:"+gidnum);
//		String tidnum = (String)session.getAttribute("idnum");//得到老师编号
//		String gname1 = request.getParameter("gname");//得到毕设名
//		String gname = new String(gname1.getBytes("ISO-8859-1"),"utf-8");//得到选题须知
//		System.out.println("Gname:"+gname);
//		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"+tidnum +"/"+ gidnum);
//		System.out.println("realpath:"+realpath);
//		File realPath =new File(realpath);
//		File delFile = new File(realPath,gname);
		
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	String gidnum = request.getParameter("gidnum");//得到毕业设计编号
	System.out.println("DOWNgidnum:"+gidnum);
	String tidnum = (String)session.getAttribute("teacheridnum");//得到老师编号
	String gname1 = request.getParameter("gname");//得到毕设名
	String gname = new String(gname1.getBytes("ISO-8859-1"),"utf-8");//得到选题须知
	System.out.println("Gname:"+gname);
	
	String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"+tidnum +"/"+ gidnum);
	System.out.println("realpath:"+realpath);
	File realPath =new File(realpath);
	File downFile = new File(realPath,gname);//下载完整路径
	
//	String path=request.getParameter("path");		//获取上传文件的路径
//	path=new String(path.getBytes("iso-8859-1"));
//	File file = new File(path);						//根据该路径创建文件对象
	
//	InputStream in = new FileInputStream(file);		//创建文件字节输入流
	InputStream in = new FileInputStream(downFile);		//创建文件字节输入流
//	OutputStream os = response.getOutputStream();	//创建输出流对象
	OutputStream os = response.getOutputStream();	//创建输出流对象
	
//	response.addHeader("Content-Disposition", "attachment;filename="
//			+ new String(file.getName().getBytes("gbk"),"iso-8859-1"));	//设置应答头信息
//	response.addHeader("Content-Length", file.length() + "");
//	response.setCharacterEncoding("gbk");		
//	response.setContentType("application/octet-stream");
//	int data = 0;
//	while ((data = in.read()) != -1) {				//循环读取文件
//		os.write(data);								//向指定目录中写文件
//	}
//	os.close();										//关闭流
//	in.close();

	response.addHeader("Content-Disposition", "attachment;filename="
			+ new String(downFile.getName().getBytes("gbk"),"ISO-8859-1"));	//设置应答头信息
	response.addHeader("Content-Length", downFile.length() + "");
	response.setCharacterEncoding("UTF-8");		
	response.setContentType("application/octet-stream");
	int data = 0;
	while ((data = in.read()) != -1) {				//循环读取文件
		os.write(data);								//向指定目录中写文件
	}
	os.close();										//关闭流
	in.close();

	
	
	      	return null;
	}
	
	
	/****************************************学生文件管理**********************************************/
	/**
	 * 显示学生详单
	 */
	public String disStudentFileList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String sidnum = request.getParameter("sidnum");//得到学生编号
		System.out.println("学生编号："+sidnum);
		//String tidnum = (String)session.getAttribute("idnum");//得到老师编号
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/student/"+sidnum);
		System.out.println("学生路径："+realpath);
		File soure = new File(realpath);
		String[]fileLista = null;
	
		if(soure.exists()){//如果目录不为空，就从指定目录获取 
		fileLista  = soure.list();			
		}else{
			System.out.println("目录为空");
		}
		//将文件对象argF所对应的目录下的所以文件与都变成文件对象
		//File[]file = argF.listFiles();	
		//System.out.println("文件："+fileLista[0]);
	//	request.setAttribute("fileList", fileLista);
		//request.setAttribute("a", "a");
		request.setAttribute("sidnum", sidnum);//方便下载，删除时调用
		this.setFileList(fileLista);
		//fileUpLoadTest
		return "StudentFileList";
		
	}
	
	
	/**
	 * 学生文件管理 
	 * @return
	 */
	public String studentFileManage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("老师编号："+teacherid);
		
//////////////////////1/////	
	       int counts=this.teacherService.studentFileManageCounts(teacherid);		
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
		
		List<StudentGraduationWorksMange>  gStudentInforList = this.teacherService.studentFileManagePage(teacherid,page.getPageNow());
		this.setStudentGraduationWorksMange(gStudentInforList);
		
		//String gn = this.ggStudentInforList.get(0).getGname();
		//System.out.println("GN:"+gn);
		

		//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		return "studentFileManage";
	}
	
	/**
	 * 删除学生文件
	 * @throws UnsupportedEncodingException 
	 */
   public String delStudentUpFile() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String sidnum = request.getParameter("sidnum");//得到学生编号
		System.out.println("DELsidnum:"+sidnum);
	
		String sname1 = request.getParameter("sname");//得到学生文件名
		String sname = new String(sname1.getBytes("ISO-8859-1"),"utf-8");//得到选题须知
		System.out.println("Gname:"+sname);
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/student/"+sidnum);
		System.out.println("realpath:"+realpath);
		File realPath =new File(realpath);
		File delFile = new File(realPath,sname);
		delFile.delete();
		 request.setAttribute("sidnum", sidnum);//方便返回时使用
	  return "delStudentUpFileSucc";
	   
   }	

   
   /**
    * 下载学生文件
 * @throws IOException 
    */
   public String downStudentFile() throws IOException{
	   HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String sidnum = request.getParameter("sidnum");//得到学生编号
		System.out.println("DOWNgidnum:"+sidnum);
		
		String gname1 = request.getParameter("sname");//得到下载文件
		String stuFile = new String(gname1.getBytes("ISO-8859-1"),"utf-8");
		System.out.println("Sname:"+stuFile);
		
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/student/"+sidnum);
		System.out.println("realpath:"+realpath);
		File realPath =new File(realpath);
		File downFile = new File(realPath,stuFile);//下载完整路径
		InputStream in = new FileInputStream(downFile);		//创建文件字节输入流
//		OutputStream os = response.getOutputStream();	//创建输出流对象
		OutputStream os = response.getOutputStream();	//创建输出流对象


		response.addHeader("Content-Disposition", "attachment;filename="
				+ new String(downFile.getName().getBytes("gbk"),"ISO-8859-1"));	//设置应答头信息
		response.addHeader("Content-Length", downFile.length() + "");
		response.setCharacterEncoding("UTF-8");		
		response.setContentType("application/octet-stream");
		int data = 0;
		while ((data = in.read()) != -1) {				//循环读取文件
			os.write(data);								//向指定目录中写文件
		}
		os.close();										//关闭流
		in.close();

		
		
		      	return null;
   }
	/*************************************************************************
	 * 查询毕业设计学生信息
	 * @return
	 */
	public String studentInforSearch() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("老师编号："+teacherid);
//////////////////////1/////	
	       int counts=this.teacherService.studentInforSearchCounts(teacherid);		
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
		List<GraduationStudentInfor>  gStudentInforList = this.teacherService.studentInforSearchPage(teacherid,page.getPageNow());
		this.setGgStudentInforList(gStudentInforList);
		
//		String gn = this.ggStudentInforList.get(0).getGname();
//		System.out.println("GN:"+gn);
//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		return "studentInforSearch";
	}
	
	
	/**
	 * 学生成绩查询
	 * 
	 * 在外面封装试试
	 * 
	 */
	public String studentGradeSearch() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("老师编号："+teacherid);
/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
	       int counts=this.teacherService.studentGradeSearchCounts(teacherid);	//得到所有记录数	
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

		
		List<Object[]>  list = this.teacherService.graStuGragrade(teacherid,page.getPageNow());
		
		List<studentGradeSearch> gStudentInforList = new ArrayList<studentGradeSearch>();//界面类
		for(Object[] obj:list){
			studentGradeSearch gStudentInfor = new studentGradeSearch();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			EMS_GraduateGrade gg = (EMS_GraduateGrade)obj[1];
					
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			System.out.println("毕名："+g.getGname());
			gStudentInfor.setGname(g.getGname());//毕名
			gStudentInfor.setSidnum(s.getIdnum());//学号
			gStudentInfor.setSname(s.getName());//学生姓名
			gStudentInfor.setGidnum(g.getIdnum());//毕编号
			gStudentInfor.setGrade(gg.getGrade());//成绩
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
		
		this.setStudentGradeSearchList(gStudentInforList);
//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		return "studentGradeSearch";
	}
	
	
	/**************************学生成绩录入*************************************************/
	/**
	 * 显示录入成绩列表
	 */
	public String studentGradeInput(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("老师编号："+teacherid);

/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
int counts=this.teacherService.studentGradeInputCounts(teacherid);	//得到所有记录数	
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

		List<GraduationStudentInfor>  gStudentInforList = this.teacherService.studentGradeInput(teacherid,page.getPageNow());
		this.setGgStudentInforList(gStudentInforList);
		
//		String gn = this.ggStudentInforList.get(0).getGname();
//		System.out.println("GN:"+gn);
		
//////////////////////////////////3///////////////////////////////////
request.setAttribute( "pageCount", page.getPageCount() );
request.setAttribute( "pageNow", page.getPageNow() );
request.setAttribute( "maxRowCount", page.getMaxRowCount() );
request.setAttribute( "pageSize", page.getPageSize() );


//////////////////////////////////////////////////////////////////////
		return "disStudentGradeInput";
	}
	
	/**
	 * 录入成绩功能按钮实现
	 * @return
	 */
	public String studentGradeInputSubmit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String sidnum= request.getParameter("sidnum");
		String grade = request.getParameter(sidnum);//得到成绩
		String ggidnum = request.getParameter("ggidnum");//得到毕业设计记录表编号
		System.out.println("学号："+sidnum);//学号
		System.out.println("成绩："+grade);//成绩
		System.out.println("毕业记录编号："+ggidnum);
		
		EMS_GraduateGrade gg = this.teacherService.getSpecifyGraduationG(ggidnum);
		gg.setIdnum(ggidnum);//毕业设计记录表编号
		gg.setGrade(grade);//成绩
		this.teacherService.update(gg);
		return "studentGradeInputSubmit";
	}
	
	/**************************学生成绩修改*************************************************/
	/**
	 * 显示修改成绩列表
	 */
	public String studentGradeupdate(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("老师编号："+teacherid);
		   int counts=this.teacherService.studentGradeupdateCounts(teacherid);	

		 //得到所有记录数	
		 			Page page=new Page(6,counts);
		 			int jumpPage=1;
		 			if(request.getParameter("jumpPage")!=null ){
		 				jumpPage=Integer.valueOf(request.getParameter

		 ("jumpPage"));
		 			}
		 			if( jumpPage < 1 ){//
		 				page.setPageNow(1);
		 			}else if( jumpPage >= page.getPageCount() ){
		 				page.setPageNow( page.getPageCount());
		 			}else{
		 				page.setPageNow( jumpPage );
		 			}
		 			///////////////////////

		List<GraduationStudentInfor>  gStudentInforList = this.teacherService.studentGradeupdate(teacherid,page.getPageNow());
		this.setGgStudentInforList(gStudentInforList);
		
//		String gn = this.ggStudentInforList.get(0).getGname();
//		System.out.println("GN:"+gn);
//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		

//////////////////////////////////////////////////////////////////////
		return "studentGradeupdate";
	}
	
	/**
	 * 修改成绩功能按钮实现
	 * @return
	 */
	public String studentGradeUpdateSubmit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String sidnum= request.getParameter("sidnum");
		String grade = request.getParameter(sidnum);//得到成绩
		String ggidnum = request.getParameter("ggidnum");//得到毕业设计记录表编号
		System.out.println("学号："+sidnum);//学号
		System.out.println("成绩："+grade);//成绩
		System.out.println("毕业记录编号："+ggidnum);
		
		EMS_GraduateGrade gg = this.teacherService.getSpecifyGraduationG(ggidnum);
		gg.setIdnum(ggidnum);//毕业设计记录表编号
		gg.setGrade(grade);//成绩
		this.teacherService.update(gg);
		return "studentGradeUpdateSubmit";
	}
	
	/**********************************课程管理**************************************/
	/**
	 * 显示已添加课程
	 */
	public String displayAlreadyAddCourse() {
		System.out.println("====teacheraction====displayAlreadyAddCourse=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("teacheridnum");
		System.out.println("用户名："+userId);
	       int counts=this.teacherService.CourseCounts(userId);	

	     //得到所有记录数	
	     			Page page=new Page(6,counts);
	     			int jumpPage=1;
	     			if(request.getParameter("jumpPage")!=null ){
	     				jumpPage=Integer.valueOf(request.getParameter

	     ("jumpPage"));
	     			}
	     			if( jumpPage < 1 ){//
	     				page.setPageNow(1);
	     			}else if( jumpPage >= page.getPageCount() ){
	     				page.setPageNow( page.getPageCount());
	     			}else{
	     				page.setPageNow( jumpPage );
	     			}
	     			///////////////////////

		List<showAlreadyAddCourse> course = this.teacherService.displayAlreadyAddCourse(userId,page.getPageNow());
		//将得到表VO类，转为显示的界面VO类
		this.setCourseList(course);
//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		return "displayAlreadyAddCourse";
		
	}
	
	/**
	 * 　得到最大编号，跳到添加界面
	 * @return
	 */
	public String getMaxIdNumAboutCourse(){
System.out.println("=============teacherAction========getMaxIdNumAboutCourse=========");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		boolean result = this.teacherService.checkTime("老师添加课程时间设置");
		if(!result)
		  return "noAddCourse";
		
		String maxIdnum = this.teacherService.getMaxIdNumAboutCourse();
		if( maxIdnum!=null ){
			int id = Integer.valueOf(maxIdnum) + 1 ;
			DecimalFormat d = new DecimalFormat("0000");
			maxIdnum = d.format(id);
		}else{
			maxIdnum = "0001";
		}
		request.setAttribute("maxIdnum", maxIdnum);
		//得到专业
		List<ProfessionVO> list = this.managerService.listProfession();	
		this.setProfessionList(list);
		//得到课程类型
		List<ClassTypeVO> teacherLit = this.teacherService.listClassType();
		
		this.setCourseTypeLit(teacherLit);
		
		return "AddCourse";
	}
	
	
	/**
	 * 插入课程按钮实现
	 * @return
	 */
	public String insertCourse() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
//	       classId varchar2(20) primary key ,    --课程编号ＰＫ
//	       className varchar2(100),              --课程名称
//	       classType varchar2(20),               --课程类型ＦＫ
//	       teacherId varchar2(20),               --教师编号F K    
//	       term varchar2(10),                    --学期
//	       grade varchar2(20),                   --所属年级
//	       professionId varchar2(20),            --所属专业F K(当类型为公共必修课，注意一下)
//	       kCount varchar2(10),                  --可选人数
//	       sCount varchar2(10),                  --剩于人数
//	       recordTime timestamp,                 --录入课程时间
//	       remark  varchar2(300),                --备注
	     
		EMS_Class classVO = new EMS_Class();
		
		String idnum = request.getParameter("idnum");//
		classVO.setClassId(idnum);
		
		String name = request.getParameter("name");//
		classVO.setClassName(name);

		String tidnum = (String)session.getAttribute("teacheridnum");
		Ems_Teacher_VO tVO =  new Ems_Teacher_VO();
		tVO.setIdnum(tidnum);//fk		
		classVO.setTeacherId(tVO);
		
		
		String professionid = request.getParameter("profession");	
		System.out.println("专业："+professionid);
		ProfessionVO proVO = new ProfessionVO();
	    proVO.setIdnum(professionid);//fk
		classVO.setProfessionId(proVO);
	
		String courseType = request.getParameter("courseType"); 
		System.out.println("课程类型："+courseType);
		ClassTypeVO ctypeVO = new ClassTypeVO();
		ctypeVO.setIdnum(courseType);//fk
		classVO.setClassType(ctypeVO);
		
		
	
		//得到学期
		String term = "2";
		Calendar cale = Calendar.getInstance();
		int nowMonth = cale.get(Calendar.MONTH);
		System.out.println("月份："+nowMonth);
	    if(nowMonth>=6){
	    	term = "1";//如果是6月以后的月份，则为第一学期,java中1月表示0
	    }
		classVO.setTerm(term);
		
		String grade = request.getParameter("grade");//
		classVO.setGrade(grade);
	
		String kxPeople = request.getParameter("kxPeople");//
		//剩余人数一样
		classVO.setkCount(kxPeople);
		classVO.setsCount(kxPeople);
		
		classVO.setRecordTime(DateConventer.strToTimestamp(DateConventer.dateToStr(new Date())));//
		
		String remark = request.getParameter("remark");
		classVO.setRemark(remark);	

		


		this.teacherService.insert(classVO);
		return "AddGraduationTitleSucc";
	}
	
	
	
	/**
	 * 显示修改课程列表
	 */
	public String updateAlreadyAddCourse(){
		
		System.out.println("====teacheraction====displayAlreadyAddCourse=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("teacheridnum");
		System.out.println("用户名："+userId);

/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
int counts=this.teacherService.CourseCounts(userId);	//得到所有记录数	
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
		List<showAlreadyAddCourse> course = this.teacherService.displayAlreadyAddCourse(userId,page.getPageNow());
		//将得到表VO类，转为显示的界面VO类
		this.setCourseList(course);
//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		

//////////////////////////////////////////////////////////////////////
	 return "displayupdateCourse";
	}
	
	/**
	 *  显示单个课程修改界面
	 */
	public String disupdateCourseList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String cidnum = request.getParameter("cidnum");
		System.out.println("课程编号:"+cidnum);
	    EMS_Class courseClass = this.teacherService.getModifyCourseInfo(cidnum);
	    request.setAttribute("KXRS", courseClass.getkCount());//可选 人数
	    request.setAttribute("courseClass", courseClass);
	  //得到专业
		List<ProfessionVO> list = this.managerService.listProfession();	
		this.setProfessionList(list);
		//得到课程类型
		List<ClassTypeVO> teacherLit = this.teacherService.listClassType();
		this.setCourseTypeLit(teacherLit);
	    return "disupdateCoursePerson";
	}
	
	/**
	 * 修改按钮功能实现
	 */
	public String updateCourseButton() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String idnum = request.getParameter("idnum");//
	    EMS_Class classVO = this.teacherService.getModifyCourseInfo(idnum);//从数据库中得到相应编号的课程
		
		//String idnum = request.getParameter("idnum");//
		classVO.setClassId(idnum);
		
		String name = request.getParameter("name");//
		classVO.setClassName(name);

		String tidnum = (String)session.getAttribute("teacheridnum");
		Ems_Teacher_VO tVO =  new Ems_Teacher_VO();
		tVO.setIdnum(tidnum);//fk		
		classVO.setTeacherId(tVO);
		
		
		String professionid = request.getParameter("profession");	
		System.out.println("专业："+professionid);
		ProfessionVO proVO = new ProfessionVO();
	    proVO.setIdnum(professionid);//fk
		classVO.setProfessionId(proVO);
	
		String courseType = request.getParameter("courseType"); 
		System.out.println("课程类型："+courseType);
		ClassTypeVO ctypeVO = new ClassTypeVO();
		ctypeVO.setIdnum(courseType);//fk
		classVO.setClassType(ctypeVO);
		
		//String term = request.getParameter("term");	//
		//classVO.setTerm(term);
		
		String grade = request.getParameter("grade");//
		classVO.setGrade(grade);
	
		String kxPeople = request.getParameter("kxPeople");//
		//剩余人数一样
		classVO.setkCount(kxPeople);
		classVO.setsCount(kxPeople);
		
		classVO.setRecordTime(DateConventer.strToTimestamp(DateConventer.dateToStr(new Date())));//
		
		String remark = request.getParameter("remark");
		classVO.setRemark(remark);	

		


		this.teacherService.update(classVO);
		return "updateGraduationTitleSucc";
	}
	
	/**
	 * 显示删除课程界面
	 */
	public String deleteAlreadyAddCourse() {
		System.out.println("====teacheraction====displayAlreadyAddCourse=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("teacheridnum");
		
		System.out.println("用户名："+userId);

/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
int counts=this.teacherService.CourseCounts(userId);	//得到所有记录数	
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
		List<showAlreadyAddCourse> course = this.teacherService.displayAlreadyAddCourse(userId,page.getPageNow());
		//将得到表VO类，转为显示的界面VO类
		this.setCourseList(course);
//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		

//////////////////////////////////////////////////////////////////////
		return "deleteAlreadyAddCourse";
	}
	
	/**
	 * 删除功能按钮实现
	 */
	public String disdeleteCourseButton(){
		System.out.println("====teacheraction====disupdateCourseButton=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
	    String cidnum = request.getParameter("cidnum");
	    String tidnum = (String)session.getAttribute("teacheridnum");
	    EMS_Class courseClass = new EMS_Class();
	    courseClass.setClassId(cidnum);
	    this.teacherService.delete(courseClass);
	  
		return "disdeleteAlreadyAdd";
		
	}
	
	
	
	/**
	 * 选课学生信息查询
	 */
	public String studentInforSearchCourse() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("老师编号："+teacherid);

/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
int counts=this.teacherService.studentInforSearchCourseCounts(teacherid);	

//得到所有记录数	
Page page=new Page(6,counts);
int jumpPage=1;
if(request.getParameter("jumpPage")!=null ){
jumpPage=Integer.valueOf(request.getParameter

("jumpPage"));
}
if( jumpPage < 1 ){//
page.setPageNow(1);
}else if( jumpPage >= page.getPageCount() ){
page.setPageNow( page.getPageCount());
}else{
page.setPageNow( jumpPage );
}
///////////////////////
		List<GraduationStudentInfor>  gStudentInforList = this.teacherService.studentInforSearchCourse(teacherid,page.getPageNow());
		this.setGgStudentInforList(gStudentInforList);
		
//		String gn = this.ggStudentInforList.get(0).getGname();
//		System.out.println("GN:"+gn);
//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		

//////////////////////////////////////////////////////////////////////
		return "studentInforSearchCourse";
	}
	
	
	/**
	 * 选课学生成绩查询
	 * @return
	 */
	public String studentGradeSearchCourse() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("老师编号："+teacherid);
		

/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
int counts=this.teacherService.studentGradeSearchCourseCounts(teacherid);	

//得到所有记录数	
Page page=new Page(6,counts);
int jumpPage=1;
if(request.getParameter("jumpPage")!=null ){
jumpPage=Integer.valueOf(request.getParameter

("jumpPage"));
}
if( jumpPage < 1 ){//
page.setPageNow(1);
}else if( jumpPage >= page.getPageCount() ){
page.setPageNow( page.getPageCount());
}else{
page.setPageNow( jumpPage );
}
///////////////////////
/////////////////////////////////////////////////////////////////////////////////////
		List<Object[]>  list = this.teacherService.graStuGragradeCourse(teacherid,page.getPageNow());
		
		List<studentGradeSearch> gStudentInforList = new ArrayList<studentGradeSearch>();//界面类
		for(Object[] obj:list){
			studentGradeSearch gStudentInfor = new studentGradeSearch();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
					
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
//			System.out.println("毕名："+g.getGname());
			gStudentInfor.setGname(g.getClassName());//课程名
			gStudentInfor.setSidnum(s.getIdnum());//学号
			gStudentInfor.setSname(s.getName());//学生姓名
			gStudentInfor.setGidnum(g.getClassId());//毕编号,课程编号
			gStudentInfor.setGrade(gg.getGrade());//成绩
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
		
		this.setStudentGradeSearchList(gStudentInforList);
//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		

//////////////////////////////////////////////////////////////////////
	return "studentGradeSearchCourse";
	}
	
	
	
	public String studentGradeSearchCourseCondition() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("老师编号："+teacherid);
		String sidnum = request.getParameter("sidnum");


/////////////////////////////////////////////////////////////////////////////////////
		List<Object[]>  list = this.teacherService.graStuGragradeCourse(teacherid,sidnum,0);
		
		List<studentGradeSearch> gStudentInforList = new ArrayList<studentGradeSearch>();//界面类
		for(Object[] obj:list){
			studentGradeSearch gStudentInfor = new studentGradeSearch();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
					
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
//			System.out.println("毕名："+g.getGname());
			gStudentInfor.setGname(g.getClassName());//课程名
			gStudentInfor.setSidnum(s.getIdnum());//学号
			gStudentInfor.setSname(s.getName());//学生姓名
			gStudentInfor.setGidnum(g.getClassId());//毕编号,课程编号
			gStudentInfor.setGrade(gg.getGrade());//成绩
			
			gStudentInforList.add(gStudentInfor);//加入单条记录
		}
		
		this.setStudentGradeSearchList(gStudentInforList);


//////////////////////////////////////////////////////////////////////
	return "studentGradeSearchCourseCon";
	
		
	}
	
	/**************************学生成绩录入*************************************************/
	/**
	 * 显示录入成绩列表
	 */
	public String studentCourseGradeInput(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("老师编号："+teacherid);
		

/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
int counts=this.teacherService.studentCourseGradeInputCounts(teacherid);	

//得到所有记录数	
Page page=new Page(6,counts);
int jumpPage=1;
if(request.getParameter("jumpPage")!=null ){
jumpPage=Integer.valueOf(request.getParameter

("jumpPage"));
}
if( jumpPage < 1 ){//
page.setPageNow(1);
}else if( jumpPage >= page.getPageCount() ){
page.setPageNow( page.getPageCount());
}else{
page.setPageNow( jumpPage );
}
///////////////////////

		List<GraduationStudentInfor>  gStudentInforList = this.teacherService.studentCourseGradeInput(teacherid,page.getPageNow());
		this.setGgStudentInforList(gStudentInforList);
		
//		String gn = this.ggStudentInforList.get(0).getGname();
//		System.out.println("GN:"+gn);
//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		

//////////////////////////////////////////////////////////////////////
		return "disStudentCourseGradeInput";
	}
	
	/**
	 * 录入成绩功能按钮实现
	 * @return
	 */
	public String studentCourseGradeInputSubmit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String sidnum= request.getParameter("sidnum");
		String grade = request.getParameter(sidnum);//得到成绩
		String ggidnum = request.getParameter("ggidnum");//得到选课记录表编号
		System.out.println("学号："+sidnum);//学号
		System.out.println("成绩："+grade);//成绩
		System.out.println("毕业记录编号："+ggidnum);
		
		EMS_CourseRecord gg = this.teacherService.getSpecifyCourseRecord(ggidnum);
		gg.setIdnum(ggidnum);//选课记录表编号
		gg.setGrade(grade);//成绩
		this.teacherService.update(gg);
		return "studentCourseGradeInputSubmit";
	}
	
	/**************************学生成绩修改*************************************************/
	/**
	 * 显示修改成绩列表
	 */
	public String studentCourseGradeupdate(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("老师编号："+teacherid);

/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
int counts=this.teacherService.studentCourseGradeupdateCounts(teacherid);	

//得到所有记录数	
Page page=new Page(6,counts);
int jumpPage=1;
if(request.getParameter("jumpPage")!=null ){
jumpPage=Integer.valueOf(request.getParameter

("jumpPage"));
}
if( jumpPage < 1 ){//
page.setPageNow(1);
}else if( jumpPage >= page.getPageCount() ){
page.setPageNow( page.getPageCount());
}else{
page.setPageNow( jumpPage );
}
///////////////////////

		List<GraduationStudentInfor>  gStudentInforList = this.teacherService.studentCourseGradeupdate(teacherid,page.getPageNow());
		this.setGgStudentInforList(gStudentInforList);
		
//		String gn = this.ggStudentInforList.get(0).getGname();
//		System.out.println("GN:"+gn);
		
//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		

//////////////////////////////////////////////////////////////////////
		return "studentCourseGradeupdate";
	}
	
	/**
	 * 修改成绩功能按钮实现
	 * @return
	 */
	public String studentCourseGradeUpdateSubmit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String sidnum= request.getParameter("sidnum");
		String grade = request.getParameter(sidnum);//得到成绩
		String ggidnum = request.getParameter("ggidnum");//得到毕业设计记录表编号
		System.out.println("学号："+sidnum);//学号
		System.out.println("成绩："+grade);//成绩
		System.out.println("毕业记录编号："+ggidnum);
		
		EMS_CourseRecord gg = this.teacherService.getSpecifyCourseRecord(ggidnum);
		gg.setIdnum(ggidnum);//选课记录表编号
		gg.setGrade(grade);//成绩
		this.teacherService.update(gg);
		return "studentCourseGradeUpdateSubmit";
	}
	
	/********************************************申请管理******************************************************/
	
	/**
	 * 点击添加申请按钮时
	 * 
	 * 验证是否可以，添加申请 
	 */
	public String addModifyApplycheck(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String idnum = (String)session.getAttribute("teacheridnum");
		ApplyModifyLogVO applyVO = this.teacherService.addModifyApplycheck(idnum);
		if(applyVO != null) {//已添加申请的情况
			System.out.println("不能加");
			//request.setAttribute("check", "check");//方便页面验证
			return "checkWrong";
		}
		System.out.println("能加");
		return "addModifyApplycheck";
	}
	
	/*****添加申请****/
	public String insertApplyInfor(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		String idnum = (String)session.getAttribute("teacheridnum");
		String name_temp = request.getParameter("name_temp");
		String idcard_temp = request.getParameter("idcard_temp");
		System.out.println("老师编号："+idnum);
		//1.写入到老师表
		//得到老师信息
		Ems_Teacher_VO teacherVO = this.teacherService.displayPersonInformation(idnum);
		teacherVO.setName_temp(name_temp);//备注姓名
		teacherVO.setIdcard_temp(idcard_temp);//备注时间
		//执行
		this.teacherService.update(teacherVO);
		//2.添加到申请表
		String maxIdnum = this.teacherService.getMaxIdNumAboutApplyModify();
		if( ! "".equals( maxIdnum ) ){
			int id = Integer.valueOf(maxIdnum) + 1 ;
			DecimalFormat d = new DecimalFormat("0000");
			maxIdnum = d.format(id);
		}
		ApplyModifyLogVO applyVO = new ApplyModifyLogVO();
		applyVO.setIdnum(maxIdnum);//编号
		applyVO.setApplyUserId(idnum);//申请用户ID
		Timestamp applyTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		applyVO.setApplyDate(applyTime);//添加申请时间
		applyVO.setFortable("ems_teacher");//用户 所在表
		applyVO.setModifyResult("审核中");//审核状态
		//执行
		this.teacherService.insert(applyVO);
		return "insertApplyInforSuccess";
	}
	
	
	/*****查看已申请状态****/
	public String displayAlreadyApplyStatus() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		String idnum = (String)session.getAttribute("teacheridnum");
		//////////////////////1/////	
	       int counts=this.teacherService.displayAlreadyApplyStatusCounts(idnum);	//得到所有记录数	
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
		//找出相应的申请表记录
		List<ApplyModifyLogVO> applyModify = this.teacherService.displayAlreadyApplyStatus(idnum,page.getPageNow());
		//排序
		 Collections.sort(applyModify,new ApplyTeacherComparator());
		List<showApplyModifyLogVO> disvo = VOconver.voConverGb(applyModify);
		this.setApplyModifyList(disvo);
		//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		return "displayAlreadyApplyStatus";
	}
	
	
	
	/*******************************审核管理************************************************/
	//显示要审核的毕业设计题目
    public String disAuditGraduationTitle(){
	   HttpServletRequest request = ServletActionContext.getRequest();
	   HttpSession session = request.getSession();
	   String tidnum = (String)session.getAttribute("teacheridnum");		
		List<disAuditGraduationTitle> gStudentInforList = this.teacherService.disAuditGraduationTitle(tidnum);
		this.setDisAuditGraTit(gStudentInforList);
		return "auditGraduationInfor";
    }
    
   /**
    * 点击审核按钮,显示审核信息 
    * @return
    */
   public String auditGraduation(){
	   HttpServletRequest request = ServletActionContext.getRequest();
	   String sidnum = request.getParameter("sidnum");//学号
	   String gidnum = request.getParameter("gidnum");//毕业设计号
	   System.out.println("点击审核按钮:"+gidnum);
	   String ggidnum = request.getParameter("ggidnum");//毕业设计记录表号
	   String  applyDate = request.getParameter("applyDate");//申请时间
	   EMS_Graduation applyGra = this.teacherService.getModifyGraduationInfo(gidnum);
	   //去掉学号	 
	   request.setAttribute("graduationVO", applyGra);
	   request.setAttribute("applyid", applyDate);//
	   request.setAttribute("sidnum", sidnum);//
	   request.setAttribute("gidnum", applyGra.getIdnum());//
	   request.setAttribute("ggidnum", ggidnum);//
	   request.setAttribute("applyDate", applyDate);//
	   return "disAuditGraduationInfor";
   }
   
   /**
    * 点击审核毕业设计---通过---- 按钮
    * 1.更改申请表的记录
	* 2.修改毕业设计表中的标志,并且可选 人数减1
    * 3.在毕业设计选题记录表中将这个学生的选题记录加入表中  
    * @return
    */
   public String teacherAuditGraduationPass(){
	   HttpServletRequest request = ServletActionContext.getRequest();
	   HttpSession session = request.getSession();
	   String sidnum = request.getParameter("sidnum");//学号
	   String gidnum = request.getParameter("gidnum");//毕业设计号
	   System.out.println("毕业设计编号:"+gidnum);
	   String ggidnum = request.getParameter("ggidnum");//毕业设计记录表号
	   String  applyDate = request.getParameter("applyid");//申请时间
	   //1申请表
		ApplyModifyLogVO applyVO = this.managerService.getAuditInfor(ggidnum);//1
		//审核状态
		applyVO.setModifyResult("已通过");
		Timestamp auditTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		//审核时间 
		applyVO.setAuditDate(auditTime);
		//审核人
		String auditMan = (String)session.getAttribute("teacheridnum");
	//	System.out.println("审核人:" +auditMan);
		applyVO.setAuditMan(auditMan);
	//	applyVO.setRemark("");//备注,去除学号
		this.teacherService.update(applyVO);//
		
		//2毕业设计表
		EMS_Graduation graduation = this.teacherService.getModifyGraduationInfo(gidnum);//2
		int kCount = Integer.valueOf(graduation.getRcount()) - 1;
		graduation.setRcount(String.valueOf(kCount));//剩余人数减1
		graduation.setFlag("teacher");
		this.teacherService.update(graduation);//
		
		//3毕业设计选题记录表
		String maxIdnum = this.teacherService.getMaxIdNumAboutGraduateGrade();
		if( maxIdnum!=null ){
			int id = Integer.valueOf(maxIdnum) + 1 ;
			DecimalFormat d = new DecimalFormat("0000");
			maxIdnum = d.format(id);
		}else{
			maxIdnum = "0001";
		}
		EMS_GraduateGrade graduateGrade = new EMS_GraduateGrade();
		graduateGrade.setIdnum(maxIdnum);//编号		
		EMS_Graduation gra = new EMS_Graduation();
		gra.setIdnum(gidnum); 
		graduateGrade.setGidnum(gra);//毕业设计编号
		Ems_Student_VO emsStu = new Ems_Student_VO();
		emsStu.setIdnum(sidnum);
		graduateGrade.setStudentId(emsStu);//学号
		Timestamp selectTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		graduateGrade.setGxtime(selectTime);//选题时间 
		this.teacherService.insert(graduateGrade);
		return "teacherAuditGraduationSucc";
   }
   
   /**
    * 点击审核毕业设计 ---不通过---- 按钮
    * 1.更改申请表的记录
	* 2.修改毕业设计表中的标志,删除那条记录    *   
    * @return
 * @throws UnsupportedEncodingException 
    */
   public String teacherAuditGraduationNoPass() throws UnsupportedEncodingException {
	   HttpServletRequest request = ServletActionContext.getRequest();
	   HttpSession session = request.getSession();
	   String sidnum = request.getParameter("sidnum");//学号
	   String gidnum = request.getParameter("gidnum");//毕业设计号
	   System.out.println("毕业设计编号:"+gidnum);
	   String ggidnum = request.getParameter("ggidnum");//毕业设计记录表号
	   String  applyDate = request.getParameter("applyid");//申请时间
	   String  attitude1 = request.getParameter("att");//审核意见
	   System.out.println("白attitude:"+attitude1);
		String attitude = new String(attitude1.getBytes("ISO-8859-1"),"utf-8");//得到选题须知
	   //1申请表
		ApplyModifyLogVO applyVO = this.managerService.getAuditInfor(ggidnum);//1
		//审核状态
		applyVO.setModifyResult("未通过");
		Timestamp auditTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		//审核时间 
		applyVO.setAuditDate(auditTime);
		//审核人
		String auditMan = (String)session.getAttribute("teacheridnum");
	//	System.out.println("审核人:" +auditMan);
		applyVO.setAuditMan(auditMan);
		
		applyVO.setRemarks(attitude);//审核意见
		this.teacherService.update(applyVO);//
		
		//2毕业设计表
		EMS_Graduation graduation = this.teacherService.getModifyGraduationInfo(gidnum);//2		
		this.teacherService.delete(graduation);//
		
	
		return "teacherAuditGraduationSucc";
   }
   
   /**
    * 查看审核毕业设计结果
    * @return
    */
   public String disAuditGraduationStatus(){
	   HttpServletRequest request = ServletActionContext.getRequest();
	   HttpSession session  = request.getSession();
	   String tidnum = (String)session.getAttribute("teacheridnum");
	   List<disAuditGraduationResult> applyModify = this.teacherService.disAditInfor(tidnum);
		
		
		//排序
		 Collections.sort(applyModify,new disAuditGraduationResultComparator());		 
	
		this.setDisAuditGraResult(applyModify);
		 return "disAuditGraduationStatus";
	   
   }
   
	/*******************************留言管理************************************************/
	//显示已回复留言
   public String disAlreadyReplyMessage(){
	   
	   HttpServletRequest request = ServletActionContext.getRequest();
	   HttpSession session = request.getSession();
	   String tidnum = (String)session.getAttribute("teacheridnum");
		
		//////////////////////1/////	
	       int counts=this.teacherService.disAlreadyReplyMessageCounts(tidnum,"已回复");	//得到所有记录数	
			Page page=new Page(2,counts);
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
	   List<ShowMessageVO> mVOList = this.teacherService.disAlreadyReplyMessage(tidnum,"已回复",page.getPageNow());
	   //System.out.println("action:"+mVOList.get(0).getIdnum());
	   this.setDisMessageVO(mVOList);
		//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
	   return "disAlreadyReplyMessage";
   }
   
   //回复留言
   public String disReplyMessage() {
	   HttpServletRequest request = ServletActionContext.getRequest();
	   HttpSession session = request.getSession();
	   String tidnum = (String)session.getAttribute("teacheridnum");
	   
	   List<ShowMessageVO> mVOList = this.teacherService.disAlreadyReplyMessage(tidnum,"未回复");
	   //System.out.println("action:"+mVOList.get(0).getIdnum());
	   this.setDisMessageVO(mVOList);
	   
	   return "disReplyMessage";
   }
   
   /**
    * 回复确定按钮的实现
    * 
    */
   public String disReplyMessageSubmit() throws UnsupportedEncodingException{
	   HttpServletRequest request = ServletActionContext.getRequest();
	   String idnum = request.getParameter("idnum");//得到编号
	   String rcontent1 = request.getParameter("rcontent");//得到回复内容
	   String rcontent = new String(rcontent1.getBytes("ISO-8859-1"),"utf-8");
	   //System.out.println("Action:"+idnum);
	   MessageVO mvo = this.teacherService.getMessageVO(idnum);
	   
		//String sname1 = request.getParameter("sname");//得到学生文件名
		//String sname = new String(sname1.getBytes("ISO-8859-1"),"utf-8");//得到选题须知
	   
	   mvo.setRcontent(rcontent);//内容
	   mvo.setRtime(DateConventer.strToTimestamp(DateConventer.dateToStr(new Date())));//回复时间
	   mvo.setStatus("已回复");//回复状态
	   this.teacherService.update(mvo);//回复
	   return "disReplyMessageSubmit";
   }
   
   
  public String studentGradeInputx(){
		boolean result = this.teacherService.checkTime("老师录入毕业设计成绩时间设置");
		if(!result)
		  return "nostudentGradeInputx";  
		return "studentGradeInputx";
  }
   
   public String studentCourseGradeInputx(){
		boolean result = this.teacherService.checkTime("老师录入课程成绩时间设置");
		if(!result)
		  return "nostudentCourseGradeInputx";
		return "studentCourseGradeInputx";
   }
	/********************************gets   and sets*****************************************/
	public List<EMS_GraduationA> getGraduateaList() {
		return graduateaList;
	}

	public void setGraduateaList(List<EMS_GraduationA> graduateaList) {
		this.graduateaList = graduateaList;
	}

	public List<showApplyModifyLogVO> getApplyModifyList() {
		return applyModifyList;
	}

	public void setApplyModifyList(List<showApplyModifyLogVO> applyModifyList) {
		this.applyModifyList = applyModifyList;
	}

	public List<ProfessionVO> getProfessionList() {
		return professionList;
	}

	public void setProfessionList(List<ProfessionVO> professionList) {
		this.professionList = professionList;
	}

	public List<Ems_Teacher_VO> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Ems_Teacher_VO> teacherList) {
		this.teacherList = teacherList;
	}

	public List<GraduationStudentInfor> getGgStudentInforList() {
		return ggStudentInforList;
	}

	public void setGgStudentInforList(
			List<GraduationStudentInfor> ggStudentInforList) {
		this.ggStudentInforList = ggStudentInforList;
	}

	public List<StudentGraduationWorksMange> getStudentGraduationWorksMange() {
		return StudentGraduationWorksMange;
	}

	public void setStudentGraduationWorksMange(
			List<StudentGraduationWorksMange> studentGraduationWorksMange) {
		StudentGraduationWorksMange = studentGraduationWorksMange;
	}

	public List<studentGradeSearch> getStudentGradeSearchList() {
		return studentGradeSearchList;
	}

	public void setStudentGradeSearchList(
			List<studentGradeSearch> studentGradeSearchList) {
		this.studentGradeSearchList = studentGradeSearchList;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String[] getFileList() {
		return fileList;
	}

	public void setFileList(String[] fileList) {
		this.fileList = fileList;
	}

	public List<disAuditGraduationTitle> getDisAuditGraTit() {
		return disAuditGraTit;
	}

	public void setDisAuditGraTit(List<disAuditGraduationTitle> disAuditGraTit) {
		this.disAuditGraTit = disAuditGraTit;
	}

	public List<disAuditGraduationResult> getDisAuditGraResult() {
		return disAuditGraResult;
	}

	public void setDisAuditGraResult(
			List<disAuditGraduationResult> disAuditGraResult) {
		this.disAuditGraResult = disAuditGraResult;
	}

	public List<showAlreadyAddCourse> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<showAlreadyAddCourse> courseList) {
		this.courseList = courseList;
	}

	public List<ClassTypeVO> getCourseTypeLit() {
		return courseTypeLit;
	}

	public void setCourseTypeLit(List<ClassTypeVO> courseTypeLit) {
		this.courseTypeLit = courseTypeLit;
	}

	public List<ShowMessageVO> getDisMessageVO() {
		return disMessageVO;
	}

	public void setDisMessageVO(List<ShowMessageVO> disMessageVO) {
		this.disMessageVO = disMessageVO;
	}

	

	
	
}
