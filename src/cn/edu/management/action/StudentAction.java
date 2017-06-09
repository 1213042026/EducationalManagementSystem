package cn.edu.management.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import cn.edu.management.comm.Page;
import cn.edu.management.comm.VOconver;
import cn.edu.management.service.ManagerService;
import cn.edu.management.service.StudentService;
import cn.edu.management.service.TeacherService;
import cn.edu.management.service.UserService;
import cn.edu.management.vo.showVO.EMS_GraduateGradeA;
import cn.edu.management.vo.showVO.EMS_GraduationB;
import cn.edu.management.vo.showVO.ShowMessageVO;
import cn.edu.management.vo.showVO.StudentVO;
import cn.edu.management.vo.showVO.showApplyModifyLogVO;
import cn.edu.management.vo.showVO.showEmsClass;
import cn.edu.management.vo.voImpl.ApplyModifyLogVO;
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
public class StudentAction extends ActionSupport {

	private List<showApplyModifyLogVO> applyList;//申请修改记录表
	private List<EMS_GraduateGrade> graduateGradeList;//毕业设计选课记录表
	private List<EMS_GraduationB> graduationList;
	private List<showEmsClass> classList; //课程表

	private List<Ems_Teacher_VO>  teacherList ;       //教师
	private List<ProfessionVO>   professionList;      //专业
	private List<ShowMessageVO>  showMessages;       //留言
	
	private ManagerService managerService;
	private StudentService studentService;
	private TeacherService teacherService;
	private UserService userService;
	private String[] fileList;   //显示文件列表
	private String[] grades;    //显示学年
	
	/**上传文件的接收类型，名字要与表单相应元素的name属性相同*/
	private File image;
	/**上传文件的名字，必须为表单的name加上FileName字符串*/
	private String imageFileName;
	private String htmlFile;
	
	@Resource
	public void setManagerService(ManagerService managerService) {
		this.managerService = managerService;
	}

	@Resource
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Resource
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	/****************************************** 个人信息维护 ***********************************************/
	/***** 查看个人信息 *****/
	public String displayPersonInformation() {

		System.out
				.println("=========StudentAction==========displayPersonInformation===========");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String idnum = (String) session.getAttribute("idnum");
		System.out.println("得到的编号：" + idnum);

		Ems_Student_VO vo = (Ems_Student_VO) this.studentService
				.displayPersonInformation(idnum);
		List<Ems_Student_VO> liststudents = new ArrayList<Ems_Student_VO>();
		liststudents.add(vo);
//		List<StudentVO> liststudentd = new ArrayList<StudentVO>();
		StudentVO studentVO = VOconver.voConver(liststudents).get(0);

		request.setAttribute("studentVO", studentVO);
		session.setAttribute("pwd", studentVO.getPassword());

		//return "listStudent";
		return "displayPersonInformation";
	}

	/****修改密码****/
	public String modifyPassword()
	{
		System.out.println("====studentAction====modifyPassword=======");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		Ems_Student_VO studentVO = new Ems_Student_VO();
		String userId = (String)session.getAttribute("idnum");
		String password = request.getParameter("prePassword");
		String newpwd = request.getParameter("newPassword");
		studentVO.setIdnum( userId );
		studentVO.setPassword( password );
		
		studentVO = this.userService.validate_student( studentVO );
		
		if( null == studentVO ){
			request.setAttribute("msg", "wrong");//用于旧密码不正确
			return "prePasswordWrong";
		}else{

			//从数据库中取出这个编号的所有信息
			Ems_Student_VO vo = (Ems_Student_VO) this.studentService.displayPersonInformation(userId);
			vo.setPassword(newpwd);//设置要修改的新密码
			this.studentService.update(vo);//更新密码
			
		}
		return "success";
	}
	
/****************************************** 信息查询 ***********************************************/
/***** 在校成绩查询 *****/
	public String listMyScore()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		//得到学号
		String idnum=(String) session.getAttribute("idnum");
		System.out.println("学号："+idnum);
		//得到学生年级
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("年级："+grade);
		
		String[] grades=new String[4];
		String xnn=grade;
		for(int i=0;i<4;i++)
		{
			grades[i]=xnn+"~"+String.valueOf(Integer.valueOf(xnn)+1);
			
			xnn=String.valueOf(Integer.valueOf(xnn)+1);
		}
		this.setGrades(grades);
		
		//分页
		int counts=this.studentService.getScoreCounts(idnum);
		Page page=new Page(10,counts);
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

		
		//得到专业
		String profession=vo.getProfession().getIdnum();
		
		List<EMS_Class> classVO  = this.studentService.listMyAllCourse1(idnum,grade,profession,page.getPageNow());
		
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		
		
		//System.out.println(classList.get(0).getkCount());
		for(int i=0;i<cl.size();i++)
		{
			showEmsClass temp=cl.get(i);
			String score=this.studentService.findScoreByCourserecord(temp.getClassId(),vo);
			if(score==null||score=="")
			{
				//这里将下一个填充到前面来了，所以i要减一
				cl.remove(i);
				i--;
				continue;
			}
			temp.setScore(score);
		}
		
		//得到学生选课时间和学期，这里i<classVO.size()改为i<cl.size()，因为cl小些，而且界面显示的也是cl
		//this.setClassList(VOconver.voConverClass(classVO));
		for(int i=0;i<cl.size();i++)
		{
			System.out.println(classVO.get(i).getRecordTime().toString());
			
			String xn=DateConventer.timestampToStr(classVO.get(i).getRecordTime());
			//String xn=this.studentService.selectCourseTime( classVO.get(i), vo);
			if(Integer.valueOf(xn.substring(5,7))>=1&&Integer.valueOf(xn.substring(5,7))<=6)
				cl.get(i).setTerm("2");
			else cl.get(i).setTerm("1");
			cl.get(i).setXueNian(xn.substring(0,4));
		}
		 //cl=this.studentService.selectCourseTime( cl, classVO, vo);
		this.setClassList(cl);
		
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );

		return "listScore";
	}
	
	public String indexScore()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		//得到学号
		String idnum=(String) session.getAttribute("idnum");
		//得到学生年级
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("年级："+grade);

		String[] grades=new String[4];
		String xnn=grade;
		for(int i=0;i<4;i++)
		{
			grades[i]=xnn+"~"+String.valueOf(Integer.valueOf(xnn)+1);
			
			xnn=String.valueOf(Integer.valueOf(xnn)+1);
		}
		this.setGrades(grades);
		
		String xnName=request.getParameter("allXn");
		String term=request.getParameter("term_name");
		if(term.equals("请选择") ) 
		   term="";
		if(xnName.equals("请选择"))
			xnName="";
		
		List<EMS_Class> classVO  = this.studentService.indexCourse(xnName,term,idnum);
		
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		for(int i=0;i<cl.size();i++)
		{
			showEmsClass temp=cl.get(i);
			String score=this.studentService.findScoreByCourserecord(temp.getClassId(),vo);
			if(score==null||score=="")
			{
				//这里将下一个填充到前面来了，所以i要减一
				cl.remove(i);
				i--;
				continue;
			}
			temp.setScore(score);
		}
		
		//得到学生选课时间和学期，这里i<classVO.size()改为i<cl.size()，因为cl小些，而且界面显示的也是cl
		//this.setClassList(VOconver.voConverClass(classVO));
		for(int i=0;i<cl.size();i++)
		{
			String xn=this.studentService.selectCourseTime( classVO.get(i), vo);
			if(Integer.valueOf(xn.substring(5,7))>=1&&Integer.valueOf(xn.substring(5,7))<=6)
				cl.get(i).setTerm("2");
			else cl.get(i).setTerm("1");
			cl.get(i).setXueNian(xn.substring(0,4));
		}
		 //cl=this.studentService.selectCourseTime( cl, classVO, vo);
		this.setClassList(cl);
		request.setAttribute("test", "test");
		return "listScore";
	}
	
	//已选课程
	public String alreadySelectedCourse()
	{
		//判断现在是否是选课时间
//		if(!this.studentService.isAvailable())
//			return "notAvailable";
		return listAlreadyCourse();
	}
	
	//当前选课情况查询
	public String currentSelectedCourse()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		//得到学号
		String idnum=(String) session.getAttribute("idnum");
		//得到学生年级
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("年级："+grade);
		//得到专业
		String profession=vo.getProfession().getIdnum();
		
		List<EMS_Class> classVO  = this.studentService.listMyCourse(idnum,grade,profession);
		
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		this.setClassList(VOconver.voConverClass(classVO));
		
		//得到学生选课时间和学期，这里i<classVO.size()改为i<cl.size()，因为cl小些，而且界面显示的也是cl
		//this.setClassList(VOconver.voConverClass(classVO));
		for(int i=0;i<cl.size();i++)
		{
			String xn=this.studentService.selectCourseTime( classVO.get(i), vo);
			if(Integer.valueOf(xn.substring(5,7))>=1&&Integer.valueOf(xn.substring(5,7))<=6)
				cl.get(i).setTerm("2");
			else cl.get(i).setTerm("1");
			cl.get(i).setXueNian(xn.substring(0,4));
		}
		 //cl=this.studentService.selectCourseTime( cl, classVO, vo);
		this.setClassList(cl);
		//System.out.println(classList.get(0).getkCount());
		
		return "currentSelectedCourse";
	}
	
	//选课情况查询
	public String listAllAlreadyCourse()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		//得到学号
		String idnum=(String) session.getAttribute("idnum");
		//得到学生年级
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("年级："+grade);

		//分页
		int counts=this.studentService.getCourseCounts(idnum);
		Page page=new Page(10,counts);
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

		String[] grades=new String[4];
		String xnn=grade;
		for(int i=0;i<4;i++)
		{
			grades[i]=xnn+"~"+String.valueOf(Integer.valueOf(xnn)+1);
			
			xnn=String.valueOf(Integer.valueOf(xnn)+1);
		}
		this.setGrades(grades);
		
		//得到专业
		String profession=vo.getProfession().getIdnum();
		
		List<EMS_Class> classVO  = this.studentService.listMyAllCourse(idnum,grade,profession,page.getPageNow());
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		
		//得到学生学年和学期
		//this.setClassList(VOconver.voConverClass(classVO));
		for(int i=0;i<cl.size();i++)
		{
			String xn=this.studentService.selectCourseTime( classVO.get(i), vo);
			if(Integer.valueOf(xn.substring(5,7))>=1&&Integer.valueOf(xn.substring(5,7))<=6)
				cl.get(i).setTerm("2");
			else cl.get(i).setTerm("1");
			cl.get(i).setXueNian(xn.substring(0,4));
		}
		 //cl=this.studentService.selectCourseTime( cl, classVO, vo);
		this.setClassList(cl);
		
		
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		
		return "listAlreadyCourse";
	}
	
	
	public String listDownCourse()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		//得到学号
		String idnum=(String) session.getAttribute("idnum");
		//得到学生年级
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("年级："+grade);
		//得到专业
		String profession=vo.getProfession().getIdnum();
		
		List<EMS_Class> classVO  = this.studentService.listMyCourse(idnum,grade,profession);
		
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		//得到学生学年和学期
		//this.setClassList(VOconver.voConverClass(classVO));
		for(int i=0;i<cl.size();i++)
		{
			String xn=this.studentService.selectCourseTime( classVO.get(i), vo);
			if(Integer.valueOf(xn.substring(5,7))>=1&&Integer.valueOf(xn.substring(5,7))<=6)
				cl.get(i).setTerm("2");
			else cl.get(i).setTerm("1");
			cl.get(i).setXueNian(xn.substring(0,4));
		}
		 //cl=this.studentService.selectCourseTime( cl, classVO, vo);
		this.setClassList(cl);
		
		//System.out.println(classList.get(0).getkCount());
		
		
		return "listDownCourse";
	}
	
	
	//已选课程
	public String listAlreadyCourse()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		//得到学号
		String idnum=(String) session.getAttribute("idnum");
		//得到学生年级
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("年级："+grade);
		//得到专业
		String profession=vo.getProfession().getIdnum();
		
		List<EMS_Class> classVO  = this.studentService.listMyCourse(idnum,grade,profession);
		
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		//得到学生学年和学期
		//this.setClassList(VOconver.voConverClass(classVO));
		for(int i=0;i<cl.size();i++)
		{
			String xn=this.studentService.selectCourseTime( classVO.get(i), vo);
			if(Integer.valueOf(xn.substring(5,7))>=1&&Integer.valueOf(xn.substring(5,7))<=6)
				cl.get(i).setTerm("2");
			else cl.get(i).setTerm("1");
			cl.get(i).setXueNian(xn.substring(0,4));
		}
		 //cl=this.studentService.selectCourseTime( cl, classVO, vo);
		this.setClassList(cl);
		
		//System.out.println(classList.get(0).getkCount());
		
		return "listAlreadyCourse";
	}
	
	
	public String indexCourse()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		//得到学号
		String idnum=(String) session.getAttribute("idnum");
		//得到学生年级
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("年级："+grade);

		String[] grades=new String[4];
		String xnn=grade;
		for(int i=0;i<4;i++)
		{
			grades[i]=xnn+"~"+String.valueOf(Integer.valueOf(xnn)+1);
			
			xnn=String.valueOf(Integer.valueOf(xnn)+1);
		}
		this.setGrades(grades);
		
		String xnName=request.getParameter("allXn");
		String term=request.getParameter("term_name");
		if(term.equals("请选择") ) 
		   term="";
		if(xnName.equals("请选择"))
			xnName="";
		
		List<EMS_Class> classVO  = this.studentService.indexCourse(xnName,term,idnum);
		
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		//得到学生学年和学期
		//this.setClassList(VOconver.voConverClass(classVO));
		for(int i=0;i<cl.size();i++)
		{
			String xn=this.studentService.selectCourseTime( classVO.get(i), vo);
			if(Integer.valueOf(xn.substring(5,7))>=1&&Integer.valueOf(xn.substring(5,7))<=6)
				cl.get(i).setTerm("2");
			else cl.get(i).setTerm("1");
			cl.get(i).setXueNian(xn.substring(0,4));
		}
		 //cl=this.studentService.selectCourseTime( cl, classVO, vo);
		this.setClassList(cl);
		request.setAttribute("test", "test");
		return "listAlreadyCourse";
	}
	
	
	
/********************************************网上选课************************************************/
	
	//判断现在是否是选课时间
	public String selectCourseTime()
	{
		//判断现在是否是选课时间
		if(!this.studentService.isAvailable()){
			try {
				ServletActionContext.getResponse().getWriter().print("1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}else{
		
		try {
			ServletActionContext.getResponse().getWriter().print("0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return null;
	}
	
/****查看可选修的课程****/
	public String listCourse()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		//得到学号
		String idnum=(String) session.getAttribute("idnum");
		//得到学生年级
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("年级："+grade);
		
		//得到专业
		String profession=vo.getProfession().getIdnum();
		
		List<EMS_Class> classVO  = this.studentService.listCourse(idnum,grade,profession);
		
		if(classVO==null) return "listCourse"; 
		
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		this.setClassList(VOconver.voConverClass(classVO));
		
		//System.out.println(classList.get(0).getkCount());
	
		return "listCourse";
	}
	
/**
 * 得到大学四年的学年，如grade=2008，则学年为2008~2009,2009~2010,2010~1011,2011~2102	
 * @param grade
 * @return
 */
//private List<String> getGrades(String grade) {
//	// TODO Auto-generated method stub
//	String xn=grade;
//	String temp=null;
//	List<String> grades=null;
//	for(int i=1;i<=4;i++)
//	{
//		temp=xn+"~"+String.valueOf(Integer.valueOf(xn)+1);
//		grades.add(temp);
//		xn=String.valueOf(Integer.valueOf(xn)+1);
//	}
//	return grades;
//}

/**
 * 选择一门课程
 * @return
 * @throws UnsupportedEncodingException 
 */
	public String selectProject() throws UnsupportedEncodingException
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session =request.getSession();
		
		String studentId=(String) session.getAttribute("idnum");
		Ems_Student_VO vo=this.studentService.displayPersonInformation(studentId);
		String classId=request.getParameter("classId");
		//String className = new String(classId.getBytes("ISO-8859-1"),"utf-8");
		String maxIdnum=this.studentService.getMaxIdnumAboutCourseRecord();
		if(  maxIdnum ==null ){
			maxIdnum="0001";
			
		}
		else {int id = Integer.valueOf(maxIdnum) + 1 ;
		DecimalFormat d = new DecimalFormat("0000");
		maxIdnum = d.format(id);}
			
		
		//将这门课程的剩余人数减1
		EMS_Class cVO=this.studentService.findClassById(classId);
		cVO.setsCount((Integer.valueOf(cVO.getsCount())-1)+"");
		this.studentService.update(cVO);
		
		//更新选课记录表
		EMS_CourseRecord cour=new EMS_CourseRecord();
		cour.setIdnum(maxIdnum);
		cour.setClassId(cVO);
		cour.setStudentId(vo);
		//Timestamp s=DateConventer.strToTimestamp(recordTime);
		cour.setChooseTime(DateConventer.strToTimestamp(DateConventer.dateToStr(new Date())));
		this.studentService.insert(cour);
		
		listCourse();
		
		request.setAttribute("Choice","CHOICE" );
		return "listCourse";
	}
	
	/**
	 * 查看我已经选修的课程
	 * @return
	 */
	public String listMyCourse()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		//得到学号
		String idnum=(String) session.getAttribute("idnum");
		//得到学生年级
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("年级："+grade);

		//得到专业
		String profession=vo.getProfession().getIdnum();
		
		List<EMS_Class> classVO  = this.studentService.listMyCourse(idnum,grade,profession);
		
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		this.setClassList(VOconver.voConverClass(classVO));
		
		//System.out.println(classList.get(0).getkCount());
		
		return "listCourse";
	}
	
	/**
	 * 取消选择一门课程   将选课记录从选课记录表中删除，并更新课程表，即余量加1
	 * @return
	 */
	public String deleteCourse()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session =request.getSession();
		
		String studentId=(String) session.getAttribute("idnum");
		Ems_Student_VO vo=this.studentService.displayPersonInformation(studentId);
		String classId=request.getParameter("classId");

		//将这门课程的剩余人数加1
		EMS_Class cVO=this.studentService.findClassById(classId);
		cVO.setsCount((Integer.valueOf(cVO.getsCount())+1)+"");
		this.studentService.update(cVO);
		
		//更新选课记录表
		EMS_CourseRecord cour=this.studentService.findCRecord(classId,studentId);
		//cour.setIdnum(maxIdnum);
		this.studentService.delete(cour);
		
		listDownCourse();
		
		request.setAttribute("DEL", "test");
		return "listDownCourse";
	}
	
	
	/********************************************毕业设计管理************************************************/
	//判断现在是否是选择毕业设计时间
	public String selectGraduationTime()
	{
		//判断现在是否是选择毕业设计时间
		if(!this.studentService.isGraAvailable()){
			try {
				ServletActionContext.getResponse().getWriter().print("1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
		
		try {
			ServletActionContext.getResponse().getWriter().print("0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return null;
	}
	
	//判断现在是否是管理毕业设计时间
	public String selectGraduationManagementTime()
	{
		if(!this.studentService.isGraMaAvailable()){
			try {
				ServletActionContext.getResponse().getWriter().print("1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
		
		try {
			ServletActionContext.getResponse().getWriter().print("0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return null;
	}
	
	/**
	 * 作品管理，学生上传下载删除自己的毕业设计
	 */
	public String projectManagement()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String idnum=(String) session.getAttribute("idnum");
//		
//		if(selectGraduationTime()=="1")
//		{
//			return "NotTiem";
//		}
//		
		
		String realpath=ServletActionContext.getServletContext().getRealPath("/fileManage/student/"+idnum);
		//新建一个目录实例
		File source=new File(realpath); 
		String[] fileLists=null;
//		该目录或文件是否存在
		System.out.println("realpath:"+realpath);
		if(source.exists())
		{
			fileLists=source.list(); // 返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中的文件和目录
		}
		else 
		{
			System.out.println("抽象路径名表示的目录下面没有子目录或文件");
		}
		
		this.setFileList(fileLists);
		return "projectManagement";
	}
	/**
	 * 上传毕业设计
	 * @throws IOException 
	 */
	public String uploadFiles() throws IOException
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String idnum=(String) session.getAttribute("idnum");
		String realpath=ServletActionContext.getServletContext().getRealPath("/fileManage/student/"+idnum);
		if(image!=null)
		{
			File savefile=new File(realpath);
			if(!savefile.exists())
				savefile.mkdirs();
			File savefiles=new File(savefile,imageFileName);//在服务器的images目录中建立相应的文件
			FileUtils.copyFile(image, savefiles);//复制文件，将要传的文件的内容复制到服务器上建好的文件
		}
		
		projectManagement();
		return "projectManagement";
	}
	
	/**
	 * 删除上传的文件，毕业设计
	 * @throws UnsupportedEncodingException 
	 */
	public String delelteFile() throws UnsupportedEncodingException
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String idnum=(String)session.getAttribute("idnum");
		String fileName1=(String)request.getParameter("gname");
		String fileName = new String(fileName1.getBytes("ISO-8859-1"),"utf-8");
		System.out.println("要删除的文件名："+fileName);
		String realpath=ServletActionContext.getServletContext().getRealPath("/fileManage/student/"+idnum);
		//File realPath=new File(realpath);
		File delFile=new File(new File(realpath),fileName);
		System.out.println("删除文件的路径："+delFile);
		delFile.delete();
		
		projectManagement();
		
		return "projectManagement";
	}
	
	/**
	 * 下载毕业设计文件
	 * @throws IOException 
	 */
	public String downFile() throws IOException
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpSession session=request.getSession();
		String idnum=(String)session.getAttribute("idnum");
		String fileName1=(String)request.getParameter("gname");
		String fileName = new String(fileName1.getBytes("ISO-8859-1"),"utf-8");
		System.out.println("要下载的文件名："+fileName);
		String realpath=ServletActionContext.getServletContext().getRealPath("/fileManage/student/"+idnum);
		//File realPath=new File(realpath);
		File downFile=new File(new File(realpath),fileName);
		System.out.println("下载的文件的路径："+downFile);
		InputStream in=new FileInputStream(downFile);
		OutputStream os=response.getOutputStream();
		

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
	
	/****查看所有毕业设计****/
	public String selectGraduationProject()
	{
		System.out.println("============StudentAction   selectGraduationProject================================");
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String userId=(String)session.getAttribute("idnum");
		
		int counts=this.studentService.getGrauationCounts();
		
		Page page=new Page(10,counts);
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

		
		System.out.println("user："+ userId);
		try
		{
			String b=this.studentService.isApply(userId);
			request.setAttribute("ss", b);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		List<EMS_Graduation> graduation =(List<EMS_Graduation>)studentService.selectGraduationProject(userId,page.getPageNow());
		
		List<Ems_Teacher_VO> listTeacher=(List<Ems_Teacher_VO>)studentService.getGraduateTeacher();
//List<ProfessionVO> listProfession=(List<ProfessionVO>)studentService.getProfession();

		this.setGraduationList(VOconver.voConverGaS(graduation));
//this.setProfessionList(listProfession);
		this.setTeacherList(listTeacher);
		
		
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );

		return "selectGraduationProject";
	}
	
	/**
	 * 获得毕业设计表的最大编号
	 * @return
	 */
	public String getMaxIdnumAboutGraduationa()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String studentId=(String) session.getAttribute("idnum");
		
		//检查是否有已经选择了毕业设计，若有，则不能添加新的申请
		if(this.studentService.findStudentIdFromGraduateGrade(studentId))
		{
			return "alreadySelected";
		}
		
		//检查是否有请求还未审核，若有，则不能添加新的申请
		if(this.studentService.checkApply(studentId,"ems_graduation"))
		{
			return "CannotAddApply";
		}
		
		
		String maxIdnum=this.teacherService.getMaxIdNumAboutGraduate();
		if(  maxIdnum ==null ){
			maxIdnum="0001";
			
		}
		else {
			int id = Integer.valueOf(maxIdnum) + 1 ;
			DecimalFormat d = new DecimalFormat("0000");
			maxIdnum = d.format(id);
			}
		
		request.setAttribute("idnum", maxIdnum);
		
		List<Ems_Teacher_VO> teacherList=this.managerService.listTeacher();
		this.setTeacherList(teacherList);
		
		List<ProfessionVO> professionList=this.managerService.listProfession();
		this.setProfessionList(professionList);
		
		return "addMyTitle";
	}
	
	/**
	 * 用于添加毕业设计记录表的时候取到数据库记录中的最大ID，管理员不能进行手动插入ID号
	 * @return
	 */
	public String getMaxIdNumAboutGraduateGrade(){
		System.out.println("=============studentAction========getMaxIdNum=========");
//		
//		//HttpServletResponse response = ServletActionContext.getResponse();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpSession session=request.getSession();
		
		String maxIdnum = this.studentService.getMaxIdNumAboutGraduateGrade();
		if(  maxIdnum ==null ){
			maxIdnum="0001";
			
		}
		else {int id = Integer.valueOf(maxIdnum) + 1 ;
		DecimalFormat d = new DecimalFormat("0000");
		maxIdnum = d.format(id);}
	//	session.setAttribute("maxIdnum", maxIdnum);
		System.out.println("maxIdnum : "+maxIdnum);
		
		return maxIdnum;
	}
	
	/**
	 * 选择毕业设计，将学生选择的毕业设计追加到毕业设计记录表中，并更新毕业设计表，即余量减1
	 * @return
	 */
	public String addGraduateProject()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String gidnum = request.getParameter("idnum");       //毕业设计编号
		String studentId=(String)session.getAttribute("idnum");    //学号
		if(this.studentService.findStudentIdFromGraduateGrade(studentId))
		{
			return "alreadySelected";
		}
		String idnum=getMaxIdNumAboutGraduateGrade(); //毕业设计记录表编号
		//String gxtime = request.getParameter("recordTime");
		
		EMS_GraduateGrade graduateRecord=new EMS_GraduateGrade();
		graduateRecord.setIdnum(idnum);                //毕业设计记录表编号
		EMS_Graduation gvo=new EMS_Graduation();
		gvo.setIdnum(gidnum);
		gvo.getGcount();
		graduateRecord.setGidnum(gvo);                //毕业设计编号
		
		Ems_Student_VO svo=new Ems_Student_VO();
		svo.setIdnum(studentId);
		graduateRecord.setStudentId(svo);             //学号
		Date date=new Date();
//System.out.println(date.toString());
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		graduateRecord.setGxtime(DateConventer.strToTimestamp(f.format(date.getTime()),"yyyy-MM-dd HH:mm:ss"));             //录入时间
		
		//更新毕业设计表的余量
		EMS_Graduation gv=this.studentService.findGraduationById(gidnum);
		//如果余量为0则不更新
		if((Integer.valueOf(gv.getRcount())==0))
			return "alreadyFull";
		//如果余量不为0则更新
		gv.setRcount((Integer.valueOf(gv.getRcount())-1)+"");
		this.studentService.update(gv);
		this.studentService.insert(graduateRecord);
		
	
		
		return "addGraduateProjectSuccess";
	}
	
	/**
	 * 删除选中的毕业设计，将学生选择毕业设计的记录从毕业设计记录表中删除,并更新毕业设计表，即余量加1
	 * @return
	 */
	public String deleteGraduateProject()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String studentId=(String) session.getAttribute("idnum");
		
		EMS_GraduateGrade vo=(EMS_GraduateGrade) this.studentService.listMyGraduationProject(studentId);
		this.studentService.delete(vo);
		//更新毕业设计表的余量
		EMS_Graduation gv=this.studentService.findGraduationById(vo.getGidnum().getIdnum());
		gv.setRcount((Integer.valueOf(gv.getRcount())+1)+"");
		this.studentService.update(gv);
		
		return "deleteGraduationSuccess";
	}
	
	/**
	 * 通过老师名字，专业检索毕业设计
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String index() throws UnsupportedEncodingException
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String TeacherIdnum=request.getParameter("tea_name");
		System.out.println("tea_name："+TeacherIdnum);
		String glevel=request.getParameter("glevel");
		///////////////////////////////////////////////////加的////////////
		String status = "";
        if(glevel.equals("easy")){
        	status="容易";
        	System.out.println("glevel："+glevel);	
        }else if(glevel.equals("often")){
        	status="一般";
        	System.out.println("glevel："+glevel);	
        }else if(glevel.equals("diff")){
        	status="困难";
        	System.out.println("glevel："+glevel);	
        }	
        //////////////////////////////////////////////////////////////////
		//if(!glevel.equals("一般"))
	//	glevel = new String(glevel.getBytes("ISO-8859-1"),"utf-8");
		
		System.out.println(glevel);
		if(glevel.equals("请选择") ) 
		   glevel="";
		
		
        int counts=this.studentService.indexGraduationProject(TeacherIdnum, status);
		
		Page page=new Page(10,counts);
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

		List<EMS_Graduation> graduation =(List<EMS_Graduation>)studentService.indexGraduationProject(TeacherIdnum,status,page.getPageNow());
		
       List<Ems_Teacher_VO> listTeacher=(List<Ems_Teacher_VO>)studentService.getGraduateTeacher();
//List<ProfessionVO> listProfession=(List<ProfessionVO>)studentService.getProfession();

		this.setGraduationList(VOconver.voConverGaS(graduation));
		this.setTeacherList(listTeacher);
		
		request.setAttribute("test", "test");
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		 //////////////////////////////////////////////////////加的////////////
		request.setAttribute("tea_name", TeacherIdnum);
		request.setAttribute("glevel", glevel);
		 //////////////////////////////////////////////////////////////////
		return "selectGraduationProject";
	}
	
	public String MyGraduationProjecttt()
	{
		System.out
		.println("=========StudentAction==========listMyGraduationProject===========");
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String studentId=(String) session.getAttribute("idnum");
		System.out.println("学生的学号："+studentId);
		
		String id = request.getParameter("user");//学生身份验证
		if(id!=null && id.equals("student")){
			System.out.println("学生");
			request.setAttribute("studentID", "value");//学生页面显示的jsp
		}

		EMS_GraduateGrade vo=(EMS_GraduateGrade) this.studentService.listMyGraduationProject(studentId);
		if(vo!=null)
		{
			EMS_GraduateGradeA temp=VOconver.voConverMyProject(vo);
			request.setAttribute("myGraduateProject", temp);
		}
		

		return "listMyProjecttt";
	}
	
	/**
	 * 查看我的毕业设计
	 * @return
	 */
	public String MyGraduationProject()
	{
		System.out
		.println("=========StudentAction==========listMyGraduationProject===========");
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String studentId=(String) session.getAttribute("idnum");
		System.out.println("学生的学号："+studentId);
		
		String id = request.getParameter("user");//学生身份验证
		if(id!=null && id.equals("student")){
			System.out.println("学生");
			request.setAttribute("studentID", "value");//学生页面显示的jsp
		}

		EMS_GraduateGrade vo=(EMS_GraduateGrade) this.studentService.listMyGraduationProject(studentId);
		if(vo!=null)
		{
			EMS_GraduateGradeA temp=VOconver.voConverMyProject(vo);
			request.setAttribute("myGraduateProject", temp);
		}
		

		

		return "listMyProject";
	}
	
	/**
	 * 添加自拟题目的毕业设计 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String addMyTitle() throws UnsupportedEncodingException
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String studentId=(String) session.getAttribute("idnum");
		String idnum=request.getParameter("idnum");//得到毕业设计编号
		String gName=request.getParameter("gname");//得到毕业设计名称
		String gname= new String(gName.getBytes("ISO-8859-1"),"utf-8");
		String kCount=request.getParameter("kCount");//得到毕业设计可选人数
		String t_name =request.getParameter("t_name");//得到毕业设计指导老师
		String g_level=request.getParameter("glevel");//得到毕业设计难易程度
		String glevel= new String(g_level.getBytes("ISO-8859-1"),"utf-8");
		String context=request.getParameter("context");
		String att = new String(context.getBytes("ISO-8859-1"),"utf-8");//得到选题须知
		System.out.println("备注："+att);
		EMS_Graduation graduation =new EMS_Graduation();
		graduation.setIdnum(idnum);
		graduation.setGname(gname);
		graduation.setGlevel(glevel);
		graduation.setFlag("student");
		graduation.setGcount(kCount);
		graduation.setRcount(kCount);
		try{
			Ems_Teacher_VO teacher =this.teacherService.displayPersonInformation(t_name);
			graduation.setTeacherId(teacher);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
//		try{
//			ProfessionVO profession=this.studentService.findProfessionByProName(pro_name);
//			graduation.setGlevel(glevel);
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		
		
		graduation.setRecordTime(DateConventer.strToTimestamp(DateConventer.dateToStr(new Date())));
		graduation.setRemark(att);
		
		//将添加的毕业设计追加到毕业设计表中
		try{
		this.studentService.insert(graduation);
		}catch(Exception e){
			
			e.printStackTrace();
		}
		//将此条申请加入申请记录表中
		ApplyModifyLogVO applyVO =new ApplyModifyLogVO();
		String fortable="ems_graduation";
		String modifyResult="审核中";
		String maxIdnum=this.teacherService.getMaxIdNumAboutApplyModify();
		if( ! "".equals( maxIdnum ) ){
			int id = Integer.valueOf(maxIdnum) + 1 ;
			DecimalFormat d = new DecimalFormat("0000");
			maxIdnum = d.format(id);
		}
		else 
			maxIdnum="0001";
		applyVO.setIdnum(maxIdnum);
		applyVO.setApplyUserId(idnum);
		Timestamp applyTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		applyVO.setApplyDate(applyTime);
		applyVO.setModifyResult(modifyResult);
		applyVO.setRemark(studentId);
		applyVO.setFortable(fortable);
		this.studentService.insert(applyVO);
		
		
		return "addTitleSuccess";
	}
	
	/**
	 * 判断是否已经添加了自拟选题
	 * @return
	 */
	public String isApplying()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String studentId=(String) session.getAttribute("idnum");
		if(this.studentService.checkApply(studentId,"ems_graduation")){
			try {
				ServletActionContext.getResponse().getWriter().print("1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		}else{
		
		try {
			ServletActionContext.getResponse().getWriter().print("0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return null;
	}
	
	/**
	 * 显示毕业设计详情
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String showProjectDetail() throws UnsupportedEncodingException
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String idnum=(String) session.getAttribute("idnum");
		String projectId=request.getParameter("idnum");
		String gname1=request.getParameter("gname");
		String gname= new String(gname1.getBytes("ISO-8859-1"),"utf-8");
		
		EMS_Graduation graduation=this.studentService.findGraduationById(projectId);
		String tname=this.teacherService.displayPersonInformation(graduation.getTeacherId().getIdnum()).getName();
		
		request.setAttribute("tname", tname);
		request.setAttribute("graduation", graduation);
		
		String teaId=this.studentService.findTeacherById(projectId);
		//String teaId=this.teacherService.
		String realpath=ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"+teaId+"/"+projectId+"/"+gname+".doc");
		String newPath=ServletActionContext.getServletContext().getRealPath("\\fileManage\\HTMLfile");
		File savefile=new File(newPath);
		if(!savefile.exists())
			savefile.mkdirs();
		String htmlFile=newPath+"\\"+gname+".html";
		
		this.setHtmlFile(gname+".html");
		
		if(this.studentService.wordToHtml(realpath,htmlFile))
			return "showHTML";
		else 
			return "graduationDetail";
	}
	
	/********************************************留言板管理************************************************/
	/**
	 * 用于添加留言表的时候取到数据库记录中的最大ID
	 * @return
	 */
	public String getMaxIdNumAboutMessage(){
		System.out.println("=============studentAction========getMaxIdNum=========");
		
		String maxIdnum = this.studentService.getMaxIdNumAboutMessage();
		if(  maxIdnum ==null ){
			maxIdnum="0001";
			
		}
		else {int id = Integer.valueOf(maxIdnum) + 1 ;
		DecimalFormat d = new DecimalFormat("0000");
		maxIdnum = d.format(id);}
	//	session.setAttribute("maxIdnum", maxIdnum);
		System.out.println("maxIdnum : "+maxIdnum);
		
		return maxIdnum;
	}
	
	
	
	/****显示添加留言界面****/
	public String addMessage()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String sidnum=(String)session.getAttribute("idnum");
		request.setAttribute("studentId", sidnum);
		
		//判断学生是否选择了毕业设计
		EMS_GraduateGrade gvo=(EMS_GraduateGrade) this.studentService.listMyGraduationProject(sidnum);
		if(gvo==null)
		{
			return "haveNotProject";
		}
		
		//得到学生姓名
		Ems_Student_VO vo = (Ems_Student_VO) this.studentService.displayPersonInformation(sidnum);
		request.setAttribute("sName", vo.getName());
		//得到教师姓名
		Ems_Teacher_VO t_name=this.studentService.findTName(gvo);
		request.setAttribute("tName", t_name.getName());
		request.setAttribute("tIdnum", t_name.getIdnum());
		
		
		//得到留言编号
		String maxIdnum=this.studentService.getMaxIdNumAboutMessage();
		if(  maxIdnum ==null ){
			maxIdnum="0001";
			
		}
		else {
			int id = Integer.valueOf(maxIdnum) + 1 ;
			DecimalFormat d = new DecimalFormat("0000");
			maxIdnum = d.format(id);
			}
		
		request.setAttribute("idnum", maxIdnum);
		
		return "addMessage";
	}
	
	/****添加留言
	 * @throws UnsupportedEncodingException ****/
	public String insertMessage() throws UnsupportedEncodingException
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String studentId=(String) session.getAttribute("idnum");
		Ems_Student_VO vo = (Ems_Student_VO) this.studentService.displayPersonInformation(studentId);
		String MessageId=request.getParameter("MessageId");//得到留言编号
		String tIdnum=request.getParameter("tIdnum");//得到教师编号
		Ems_Teacher_VO tvo=this.teacherService.displayPersonInformation(tIdnum);
	//	String tName=request.getParameter("tName");//得到教师
		String tit=request.getParameter("title");//得到留言主题
		String title=new String(tit.getBytes("ISO-8859-1"),"utf-8");
		String context=request.getParameter("content");
		String content = new String(context.getBytes("ISO-8859-1"),"utf-8");//得到留言内容
		System.out.println("留言内容："+content);
		MessageVO message =new MessageVO();
		
		message.setIdnum(MessageId);
		message.setSidnum(vo);
		message.setTidnum(tvo);
		message.setTitle(title);
		message.setStatus("未回复");
		message.setContent(content);
		
		message.setMtime(DateConventer.strToTimestamp(DateConventer.dateToStr(new Date())));
		
		//将此条留言加入留言表中
		this.studentService.insert(message);
		
		return "addMessageSuccess";
	}
	
	/**
	 * 查看留言记录
	 */
	public String showMessage()
	{
		   HttpServletRequest request = ServletActionContext.getRequest();
		   HttpSession session = request.getSession();
		   String idnum = (String)session.getAttribute("idnum");
		   
		   List<MessageVO> mVOList = this.studentService.showMessage(idnum,"");
		   //System.out.println("action:"+mVOList.get(0).getIdnum());
		   List<ShowMessageVO> showMessageList=VOconver.voConverdisAlreadyReplyMessage(mVOList);
		   this.setShowMessages(showMessageList);
		   
		   return "showMessage";
	}
	
	/**
	 * 查看已回复的留言
	 */
	public String showReply()
	{
		   HttpServletRequest request = ServletActionContext.getRequest();
		   HttpSession session = request.getSession();
		   String idnum = (String)session.getAttribute("idnum");
		   
		   List<MessageVO> mVOList = this.studentService.showMessage(idnum,"已回复");
		   //System.out.println("action:"+mVOList.get(0).getIdnum());
		   List<ShowMessageVO> showMessageList=VOconver.voConverdisAlreadyReplyMessage(mVOList);
		   this.setShowMessages(showMessageList);
		return "showReply";
	}
	
	/**
	 * 查看回复详情
	 */
	public String showReplyDetail()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String sidnum=(String)session.getAttribute("idnum");
		request.setAttribute("studentId", sidnum);
		//得到留言编号
		String idnum=request.getParameter("idnum");
		List<MessageVO> mVOList = this.studentService.showMessage(idnum,"看留言");
		List<ShowMessageVO> showMessageList=VOconver.voConverdisAlreadyReplyMessage(mVOList);
		
		//得到学生姓名
		Ems_Student_VO vo = (Ems_Student_VO) this.studentService.displayPersonInformation(sidnum);
		request.setAttribute("sName", vo.getName());
		//得到教师姓名
		Ems_Teacher_VO t_name=this.teacherService.displayPersonInformation(mVOList.get(0).getTidnum().getIdnum());
		request.setAttribute("tName", t_name.getName());
		request.setAttribute("title", showMessageList.get(0).getTitle());
		request.setAttribute("contentMessage", showMessageList.get(0).getContent());
		System.out.println(showMessageList.get(0).getContent());
		request.setAttribute("rcontentMessage", showMessageList.get(0).getRcontent());
		
		return "showReplyDetail";
	}
	
	
	
	/********************************************个人信息申请修改************************************************/
	/****点击左边菜单添加申请所做的事****/
	public String addModifyApplication()
	{
		System.out
		.println("=========StudentAction==========addModifyApplication===========");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String idnum = (String) session.getAttribute("idnum");
		System.out.println("得到的编号：" + idnum);
		
		//检查是否有请求还未审核，若有，则不能添加新的申请
		if(this.studentService.checkApply(idnum,"ems_student"))
		{
			return "CannotAddApply";
		}

		Ems_Student_VO vo = (Ems_Student_VO) this.studentService.displayPersonInformation(idnum);
		List<Ems_Student_VO> liststudents = new ArrayList<Ems_Student_VO>();
		liststudents.add(vo);
		StudentVO studentVO = VOconver.voConver(liststudents).get(0);

//System.out.println("专业："+studentVO.getProf_Name());
//System.out.println("IdCard："+studentVO.getIdcard());
		request.setAttribute("studentVO", studentVO);
		session.setAttribute("pwd", studentVO.getPassword());
		return "modifyStudentInfo";
	}

	/**
	 * 将申请的记录插入的申请记录表中
	 * @return
	 */
	public String insertModifyApply()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String applyUserId=(String) session.getAttribute("idnum");
		String name_temp=request.getParameter("name_temp");
		String address_temp=request.getParameter("address_temp");
		String idcard_temp=request.getParameter("idcard_temp");
		
		//将备份信息写到学生表中（EMS_Student_VO）
		Ems_Student_VO studentVO=this.studentService.displayPersonInformation(applyUserId);
		studentVO.setUsername_temp(name_temp);
		studentVO.setAddress_temp(address_temp);
		studentVO.setIdcrad_temp(idcard_temp);
		this.studentService.update(studentVO);
		
		//将申请记录插入申请记录表中（ApplyModifyLogVO）
		ApplyModifyLogVO applyVO =new ApplyModifyLogVO();
		String fortable="ems_student";
		String modifyResult="审核中";
		String maxIdnum=this.teacherService.getMaxIdNumAboutApplyModify();
		if( ! "".equals( maxIdnum ) ){
			int id = Integer.valueOf(maxIdnum) + 1 ;
			DecimalFormat d = new DecimalFormat("0000");
			maxIdnum = d.format(id);
		}
		else 
			maxIdnum="0001";
		applyVO.setIdnum(maxIdnum);
		applyVO.setApplyUserId(applyUserId);
		Timestamp applyTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		applyVO.setApplyDate(applyTime);
		applyVO.setModifyResult(modifyResult);
		applyVO.setFortable(fortable);
		this.studentService.insert(applyVO);
		
		return "addModifyApplySuccess";
	}
	
	/**
	 * 查看申请信息
	 * @return
	 */
	public String listModifyApplication()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		String idnum = (String)session.getAttribute("idnum");
		
		//找出相应的申请表记录
		List<ApplyModifyLogVO> applyModify = this.studentService.displayAlreadyApplyStatus(idnum);
		//排序
		 Collections.sort(applyModify,new ApplyTeacherComparator());
		List<showApplyModifyLogVO> disvo = VOconver.voConverGb(applyModify);
		this.setApplyList(disvo);
		return "listModifyApplication";
	}
	
	/**
	 * 查看毕业设计自拟选题申请信息
	 * @return
	 */
	public String listModifyApplicationSelf()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		String idnum = (String)session.getAttribute("idnum");
		
		//找出相应的申请表记录
		List<ApplyModifyLogVO> applyModify = this.studentService.displayAlreadyApplyStatusSelf(idnum);
		//排序
		 Collections.sort(applyModify,new ApplyTeacherComparator());
		List<showApplyModifyLogVO> disvo = VOconver.voConverGb(applyModify);
		this.setApplyList(disvo);
		return "listModifyApplicationSelf";
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

	

	public List<EMS_GraduateGrade> getGraduateGradeList() {
		return graduateGradeList;
	}

	public void setGraduateGradeList(List<EMS_GraduateGrade> graduateGradeList) {
		this.graduateGradeList = graduateGradeList;
	}


	public List<EMS_GraduationB> getGraduationList() {
		return graduationList;
	}

	public void setGraduationList(List<EMS_GraduationB> graduationList) {
		this.graduationList = graduationList;
	}

	public List<showApplyModifyLogVO> getApplyList() {
		return applyList;
	}

	public void setApplyList(List<showApplyModifyLogVO> applyList) {
		this.applyList = applyList;
	}

	public List<showEmsClass> getClassList() {
		return classList;
	}

	public void setClassList(List<showEmsClass> classList) {
		this.classList = classList;
	}

	public String[] getFileList() {
		return fileList;
	}

	public void setFileList(String[] fileList) {
		this.fileList = fileList;
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

	public List<ShowMessageVO> getShowMessages() {
		return showMessages;
	}

	public void setShowMessages(List<ShowMessageVO> showMessages) {
		this.showMessages = showMessages;
	}

	public String[] getGrades() {
		return grades;
	}

	public void setGrades(String[] grades) {
		this.grades = grades;
	}

	public String getHtmlFile() {
		return htmlFile;
	}

	public void setHtmlFile(String htmlFile) {
		this.htmlFile = htmlFile;
	}
	
	

}
