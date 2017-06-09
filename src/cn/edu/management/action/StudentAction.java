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

	private List<showApplyModifyLogVO> applyList;//�����޸ļ�¼��
	private List<EMS_GraduateGrade> graduateGradeList;//��ҵ���ѡ�μ�¼��
	private List<EMS_GraduationB> graduationList;
	private List<showEmsClass> classList; //�γ̱�

	private List<Ems_Teacher_VO>  teacherList ;       //��ʦ
	private List<ProfessionVO>   professionList;      //רҵ
	private List<ShowMessageVO>  showMessages;       //����
	
	private ManagerService managerService;
	private StudentService studentService;
	private TeacherService teacherService;
	private UserService userService;
	private String[] fileList;   //��ʾ�ļ��б�
	private String[] grades;    //��ʾѧ��
	
	/**�ϴ��ļ��Ľ������ͣ�����Ҫ�����ӦԪ�ص�name������ͬ*/
	private File image;
	/**�ϴ��ļ������֣�����Ϊ����name����FileName�ַ���*/
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

	/****************************************** ������Ϣά�� ***********************************************/
	/***** �鿴������Ϣ *****/
	public String displayPersonInformation() {

		System.out
				.println("=========StudentAction==========displayPersonInformation===========");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String idnum = (String) session.getAttribute("idnum");
		System.out.println("�õ��ı�ţ�" + idnum);

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

	/****�޸�����****/
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
			request.setAttribute("msg", "wrong");//���ھ����벻��ȷ
			return "prePasswordWrong";
		}else{

			//�����ݿ���ȡ�������ŵ�������Ϣ
			Ems_Student_VO vo = (Ems_Student_VO) this.studentService.displayPersonInformation(userId);
			vo.setPassword(newpwd);//����Ҫ�޸ĵ�������
			this.studentService.update(vo);//��������
			
		}
		return "success";
	}
	
/****************************************** ��Ϣ��ѯ ***********************************************/
/***** ��У�ɼ���ѯ *****/
	public String listMyScore()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		//�õ�ѧ��
		String idnum=(String) session.getAttribute("idnum");
		System.out.println("ѧ�ţ�"+idnum);
		//�õ�ѧ���꼶
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("�꼶��"+grade);
		
		String[] grades=new String[4];
		String xnn=grade;
		for(int i=0;i<4;i++)
		{
			grades[i]=xnn+"~"+String.valueOf(Integer.valueOf(xnn)+1);
			
			xnn=String.valueOf(Integer.valueOf(xnn)+1);
		}
		this.setGrades(grades);
		
		//��ҳ
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

		
		//�õ�רҵ
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
				//���ｫ��һ����䵽ǰ�����ˣ�����iҪ��һ
				cl.remove(i);
				i--;
				continue;
			}
			temp.setScore(score);
		}
		
		//�õ�ѧ��ѡ��ʱ���ѧ�ڣ�����i<classVO.size()��Ϊi<cl.size()����ΪclСЩ�����ҽ�����ʾ��Ҳ��cl
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
		
		//�õ�ѧ��
		String idnum=(String) session.getAttribute("idnum");
		//�õ�ѧ���꼶
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("�꼶��"+grade);

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
		if(term.equals("��ѡ��") ) 
		   term="";
		if(xnName.equals("��ѡ��"))
			xnName="";
		
		List<EMS_Class> classVO  = this.studentService.indexCourse(xnName,term,idnum);
		
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		for(int i=0;i<cl.size();i++)
		{
			showEmsClass temp=cl.get(i);
			String score=this.studentService.findScoreByCourserecord(temp.getClassId(),vo);
			if(score==null||score=="")
			{
				//���ｫ��һ����䵽ǰ�����ˣ�����iҪ��һ
				cl.remove(i);
				i--;
				continue;
			}
			temp.setScore(score);
		}
		
		//�õ�ѧ��ѡ��ʱ���ѧ�ڣ�����i<classVO.size()��Ϊi<cl.size()����ΪclСЩ�����ҽ�����ʾ��Ҳ��cl
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
	
	//��ѡ�γ�
	public String alreadySelectedCourse()
	{
		//�ж������Ƿ���ѡ��ʱ��
//		if(!this.studentService.isAvailable())
//			return "notAvailable";
		return listAlreadyCourse();
	}
	
	//��ǰѡ�������ѯ
	public String currentSelectedCourse()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		//�õ�ѧ��
		String idnum=(String) session.getAttribute("idnum");
		//�õ�ѧ���꼶
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("�꼶��"+grade);
		//�õ�רҵ
		String profession=vo.getProfession().getIdnum();
		
		List<EMS_Class> classVO  = this.studentService.listMyCourse(idnum,grade,profession);
		
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		this.setClassList(VOconver.voConverClass(classVO));
		
		//�õ�ѧ��ѡ��ʱ���ѧ�ڣ�����i<classVO.size()��Ϊi<cl.size()����ΪclСЩ�����ҽ�����ʾ��Ҳ��cl
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
	
	//ѡ�������ѯ
	public String listAllAlreadyCourse()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		//�õ�ѧ��
		String idnum=(String) session.getAttribute("idnum");
		//�õ�ѧ���꼶
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("�꼶��"+grade);

		//��ҳ
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
		
		//�õ�רҵ
		String profession=vo.getProfession().getIdnum();
		
		List<EMS_Class> classVO  = this.studentService.listMyAllCourse(idnum,grade,profession,page.getPageNow());
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		
		//�õ�ѧ��ѧ���ѧ��
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
		//�õ�ѧ��
		String idnum=(String) session.getAttribute("idnum");
		//�õ�ѧ���꼶
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("�꼶��"+grade);
		//�õ�רҵ
		String profession=vo.getProfession().getIdnum();
		
		List<EMS_Class> classVO  = this.studentService.listMyCourse(idnum,grade,profession);
		
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		//�õ�ѧ��ѧ���ѧ��
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
	
	
	//��ѡ�γ�
	public String listAlreadyCourse()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		//�õ�ѧ��
		String idnum=(String) session.getAttribute("idnum");
		//�õ�ѧ���꼶
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("�꼶��"+grade);
		//�õ�רҵ
		String profession=vo.getProfession().getIdnum();
		
		List<EMS_Class> classVO  = this.studentService.listMyCourse(idnum,grade,profession);
		
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		//�õ�ѧ��ѧ���ѧ��
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
		
		//�õ�ѧ��
		String idnum=(String) session.getAttribute("idnum");
		//�õ�ѧ���꼶
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("�꼶��"+grade);

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
		if(term.equals("��ѡ��") ) 
		   term="";
		if(xnName.equals("��ѡ��"))
			xnName="";
		
		List<EMS_Class> classVO  = this.studentService.indexCourse(xnName,term,idnum);
		
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		//�õ�ѧ��ѧ���ѧ��
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
	
	
	
/********************************************����ѡ��************************************************/
	
	//�ж������Ƿ���ѡ��ʱ��
	public String selectCourseTime()
	{
		//�ж������Ƿ���ѡ��ʱ��
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
	
/****�鿴��ѡ�޵Ŀγ�****/
	public String listCourse()
	{
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		//�õ�ѧ��
		String idnum=(String) session.getAttribute("idnum");
		//�õ�ѧ���꼶
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("�꼶��"+grade);
		
		//�õ�רҵ
		String profession=vo.getProfession().getIdnum();
		
		List<EMS_Class> classVO  = this.studentService.listCourse(idnum,grade,profession);
		
		if(classVO==null) return "listCourse"; 
		
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		this.setClassList(VOconver.voConverClass(classVO));
		
		//System.out.println(classList.get(0).getkCount());
	
		return "listCourse";
	}
	
/**
 * �õ���ѧ�����ѧ�꣬��grade=2008����ѧ��Ϊ2008~2009,2009~2010,2010~1011,2011~2102	
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
 * ѡ��һ�ſγ�
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
			
		
		//�����ſγ̵�ʣ��������1
		EMS_Class cVO=this.studentService.findClassById(classId);
		cVO.setsCount((Integer.valueOf(cVO.getsCount())-1)+"");
		this.studentService.update(cVO);
		
		//����ѡ�μ�¼��
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
	 * �鿴���Ѿ�ѡ�޵Ŀγ�
	 * @return
	 */
	public String listMyCourse()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		//�õ�ѧ��
		String idnum=(String) session.getAttribute("idnum");
		//�õ�ѧ���꼶
		Ems_Student_VO vo=this.studentService.displayPersonInformation(idnum);
		String grade=vo.getEntranceDate().substring(0, 4);
		System.out.println("�꼶��"+grade);

		//�õ�רҵ
		String profession=vo.getProfession().getIdnum();
		
		List<EMS_Class> classVO  = this.studentService.listMyCourse(idnum,grade,profession);
		
		List<showEmsClass> cl=VOconver.voConverClass(classVO);
		this.setClassList(VOconver.voConverClass(classVO));
		
		//System.out.println(classList.get(0).getkCount());
		
		return "listCourse";
	}
	
	/**
	 * ȡ��ѡ��һ�ſγ�   ��ѡ�μ�¼��ѡ�μ�¼����ɾ���������¿γ̱���������1
	 * @return
	 */
	public String deleteCourse()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session =request.getSession();
		
		String studentId=(String) session.getAttribute("idnum");
		Ems_Student_VO vo=this.studentService.displayPersonInformation(studentId);
		String classId=request.getParameter("classId");

		//�����ſγ̵�ʣ��������1
		EMS_Class cVO=this.studentService.findClassById(classId);
		cVO.setsCount((Integer.valueOf(cVO.getsCount())+1)+"");
		this.studentService.update(cVO);
		
		//����ѡ�μ�¼��
		EMS_CourseRecord cour=this.studentService.findCRecord(classId,studentId);
		//cour.setIdnum(maxIdnum);
		this.studentService.delete(cour);
		
		listDownCourse();
		
		request.setAttribute("DEL", "test");
		return "listDownCourse";
	}
	
	
	/********************************************��ҵ��ƹ���************************************************/
	//�ж������Ƿ���ѡ���ҵ���ʱ��
	public String selectGraduationTime()
	{
		//�ж������Ƿ���ѡ���ҵ���ʱ��
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
	
	//�ж������Ƿ��ǹ����ҵ���ʱ��
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
	 * ��Ʒ����ѧ���ϴ�����ɾ���Լ��ı�ҵ���
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
		//�½�һ��Ŀ¼ʵ��
		File source=new File(realpath); 
		String[] fileLists=null;
//		��Ŀ¼���ļ��Ƿ����
		System.out.println("realpath:"+realpath);
		if(source.exists())
		{
			fileLists=source.list(); // ����һ���ַ������飬��Щ�ַ���ָ���˳���·������ʾ��Ŀ¼�е��ļ���Ŀ¼
		}
		else 
		{
			System.out.println("����·������ʾ��Ŀ¼����û����Ŀ¼���ļ�");
		}
		
		this.setFileList(fileLists);
		return "projectManagement";
	}
	/**
	 * �ϴ���ҵ���
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
			File savefiles=new File(savefile,imageFileName);//�ڷ�������imagesĿ¼�н�����Ӧ���ļ�
			FileUtils.copyFile(image, savefiles);//�����ļ�����Ҫ�����ļ������ݸ��Ƶ��������Ͻ��õ��ļ�
		}
		
		projectManagement();
		return "projectManagement";
	}
	
	/**
	 * ɾ���ϴ����ļ�����ҵ���
	 * @throws UnsupportedEncodingException 
	 */
	public String delelteFile() throws UnsupportedEncodingException
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String idnum=(String)session.getAttribute("idnum");
		String fileName1=(String)request.getParameter("gname");
		String fileName = new String(fileName1.getBytes("ISO-8859-1"),"utf-8");
		System.out.println("Ҫɾ�����ļ�����"+fileName);
		String realpath=ServletActionContext.getServletContext().getRealPath("/fileManage/student/"+idnum);
		//File realPath=new File(realpath);
		File delFile=new File(new File(realpath),fileName);
		System.out.println("ɾ���ļ���·����"+delFile);
		delFile.delete();
		
		projectManagement();
		
		return "projectManagement";
	}
	
	/**
	 * ���ر�ҵ����ļ�
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
		System.out.println("Ҫ���ص��ļ�����"+fileName);
		String realpath=ServletActionContext.getServletContext().getRealPath("/fileManage/student/"+idnum);
		//File realPath=new File(realpath);
		File downFile=new File(new File(realpath),fileName);
		System.out.println("���ص��ļ���·����"+downFile);
		InputStream in=new FileInputStream(downFile);
		OutputStream os=response.getOutputStream();
		

		response.addHeader("Content-Disposition", "attachment;filename="
				+ new String(downFile.getName().getBytes("gbk"),"ISO-8859-1"));	//����Ӧ��ͷ��Ϣ
		response.addHeader("Content-Length", downFile.length() + "");
		response.setCharacterEncoding("UTF-8");		
		response.setContentType("application/octet-stream");
		int data = 0;
		while ((data = in.read()) != -1) {				//ѭ����ȡ�ļ�
			os.write(data);								//��ָ��Ŀ¼��д�ļ�
		}
		os.close();										//�ر���
		in.close();

		
		return null;
	}
	
	/****�鿴���б�ҵ���****/
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

		
		System.out.println("user��"+ userId);
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
	 * ��ñ�ҵ��Ʊ�������
	 * @return
	 */
	public String getMaxIdnumAboutGraduationa()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String studentId=(String) session.getAttribute("idnum");
		
		//����Ƿ����Ѿ�ѡ���˱�ҵ��ƣ����У���������µ�����
		if(this.studentService.findStudentIdFromGraduateGrade(studentId))
		{
			return "alreadySelected";
		}
		
		//����Ƿ�������δ��ˣ����У���������µ�����
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
	 * ������ӱ�ҵ��Ƽ�¼���ʱ��ȡ�����ݿ��¼�е����ID������Ա���ܽ����ֶ�����ID��
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
	 * ѡ���ҵ��ƣ���ѧ��ѡ��ı�ҵ���׷�ӵ���ҵ��Ƽ�¼���У������±�ҵ��Ʊ���������1
	 * @return
	 */
	public String addGraduateProject()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String gidnum = request.getParameter("idnum");       //��ҵ��Ʊ��
		String studentId=(String)session.getAttribute("idnum");    //ѧ��
		if(this.studentService.findStudentIdFromGraduateGrade(studentId))
		{
			return "alreadySelected";
		}
		String idnum=getMaxIdNumAboutGraduateGrade(); //��ҵ��Ƽ�¼����
		//String gxtime = request.getParameter("recordTime");
		
		EMS_GraduateGrade graduateRecord=new EMS_GraduateGrade();
		graduateRecord.setIdnum(idnum);                //��ҵ��Ƽ�¼����
		EMS_Graduation gvo=new EMS_Graduation();
		gvo.setIdnum(gidnum);
		gvo.getGcount();
		graduateRecord.setGidnum(gvo);                //��ҵ��Ʊ��
		
		Ems_Student_VO svo=new Ems_Student_VO();
		svo.setIdnum(studentId);
		graduateRecord.setStudentId(svo);             //ѧ��
		Date date=new Date();
//System.out.println(date.toString());
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		graduateRecord.setGxtime(DateConventer.strToTimestamp(f.format(date.getTime()),"yyyy-MM-dd HH:mm:ss"));             //¼��ʱ��
		
		//���±�ҵ��Ʊ������
		EMS_Graduation gv=this.studentService.findGraduationById(gidnum);
		//�������Ϊ0�򲻸���
		if((Integer.valueOf(gv.getRcount())==0))
			return "alreadyFull";
		//���������Ϊ0�����
		gv.setRcount((Integer.valueOf(gv.getRcount())-1)+"");
		this.studentService.update(gv);
		this.studentService.insert(graduateRecord);
		
	
		
		return "addGraduateProjectSuccess";
	}
	
	/**
	 * ɾ��ѡ�еı�ҵ��ƣ���ѧ��ѡ���ҵ��Ƶļ�¼�ӱ�ҵ��Ƽ�¼����ɾ��,�����±�ҵ��Ʊ���������1
	 * @return
	 */
	public String deleteGraduateProject()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String studentId=(String) session.getAttribute("idnum");
		
		EMS_GraduateGrade vo=(EMS_GraduateGrade) this.studentService.listMyGraduationProject(studentId);
		this.studentService.delete(vo);
		//���±�ҵ��Ʊ������
		EMS_Graduation gv=this.studentService.findGraduationById(vo.getGidnum().getIdnum());
		gv.setRcount((Integer.valueOf(gv.getRcount())+1)+"");
		this.studentService.update(gv);
		
		return "deleteGraduationSuccess";
	}
	
	/**
	 * ͨ����ʦ���֣�רҵ������ҵ���
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String index() throws UnsupportedEncodingException
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String TeacherIdnum=request.getParameter("tea_name");
		System.out.println("tea_name��"+TeacherIdnum);
		String glevel=request.getParameter("glevel");
		///////////////////////////////////////////////////�ӵ�////////////
		String status = "";
        if(glevel.equals("easy")){
        	status="����";
        	System.out.println("glevel��"+glevel);	
        }else if(glevel.equals("often")){
        	status="һ��";
        	System.out.println("glevel��"+glevel);	
        }else if(glevel.equals("diff")){
        	status="����";
        	System.out.println("glevel��"+glevel);	
        }	
        //////////////////////////////////////////////////////////////////
		//if(!glevel.equals("һ��"))
	//	glevel = new String(glevel.getBytes("ISO-8859-1"),"utf-8");
		
		System.out.println(glevel);
		if(glevel.equals("��ѡ��") ) 
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
		 //////////////////////////////////////////////////////�ӵ�////////////
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
		System.out.println("ѧ����ѧ�ţ�"+studentId);
		
		String id = request.getParameter("user");//ѧ�������֤
		if(id!=null && id.equals("student")){
			System.out.println("ѧ��");
			request.setAttribute("studentID", "value");//ѧ��ҳ����ʾ��jsp
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
	 * �鿴�ҵı�ҵ���
	 * @return
	 */
	public String MyGraduationProject()
	{
		System.out
		.println("=========StudentAction==========listMyGraduationProject===========");
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String studentId=(String) session.getAttribute("idnum");
		System.out.println("ѧ����ѧ�ţ�"+studentId);
		
		String id = request.getParameter("user");//ѧ�������֤
		if(id!=null && id.equals("student")){
			System.out.println("ѧ��");
			request.setAttribute("studentID", "value");//ѧ��ҳ����ʾ��jsp
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
	 * ���������Ŀ�ı�ҵ��� 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String addMyTitle() throws UnsupportedEncodingException
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String studentId=(String) session.getAttribute("idnum");
		String idnum=request.getParameter("idnum");//�õ���ҵ��Ʊ��
		String gName=request.getParameter("gname");//�õ���ҵ�������
		String gname= new String(gName.getBytes("ISO-8859-1"),"utf-8");
		String kCount=request.getParameter("kCount");//�õ���ҵ��ƿ�ѡ����
		String t_name =request.getParameter("t_name");//�õ���ҵ���ָ����ʦ
		String g_level=request.getParameter("glevel");//�õ���ҵ������׳̶�
		String glevel= new String(g_level.getBytes("ISO-8859-1"),"utf-8");
		String context=request.getParameter("context");
		String att = new String(context.getBytes("ISO-8859-1"),"utf-8");//�õ�ѡ����֪
		System.out.println("��ע��"+att);
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
		
		//����ӵı�ҵ���׷�ӵ���ҵ��Ʊ���
		try{
		this.studentService.insert(graduation);
		}catch(Exception e){
			
			e.printStackTrace();
		}
		//������������������¼����
		ApplyModifyLogVO applyVO =new ApplyModifyLogVO();
		String fortable="ems_graduation";
		String modifyResult="�����";
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
	 * �ж��Ƿ��Ѿ����������ѡ��
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
	 * ��ʾ��ҵ�������
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
	
	/********************************************���԰����************************************************/
	/**
	 * ����������Ա��ʱ��ȡ�����ݿ��¼�е����ID
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
	
	
	
	/****��ʾ������Խ���****/
	public String addMessage()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String sidnum=(String)session.getAttribute("idnum");
		request.setAttribute("studentId", sidnum);
		
		//�ж�ѧ���Ƿ�ѡ���˱�ҵ���
		EMS_GraduateGrade gvo=(EMS_GraduateGrade) this.studentService.listMyGraduationProject(sidnum);
		if(gvo==null)
		{
			return "haveNotProject";
		}
		
		//�õ�ѧ������
		Ems_Student_VO vo = (Ems_Student_VO) this.studentService.displayPersonInformation(sidnum);
		request.setAttribute("sName", vo.getName());
		//�õ���ʦ����
		Ems_Teacher_VO t_name=this.studentService.findTName(gvo);
		request.setAttribute("tName", t_name.getName());
		request.setAttribute("tIdnum", t_name.getIdnum());
		
		
		//�õ����Ա��
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
	
	/****�������
	 * @throws UnsupportedEncodingException ****/
	public String insertMessage() throws UnsupportedEncodingException
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		String studentId=(String) session.getAttribute("idnum");
		Ems_Student_VO vo = (Ems_Student_VO) this.studentService.displayPersonInformation(studentId);
		String MessageId=request.getParameter("MessageId");//�õ����Ա��
		String tIdnum=request.getParameter("tIdnum");//�õ���ʦ���
		Ems_Teacher_VO tvo=this.teacherService.displayPersonInformation(tIdnum);
	//	String tName=request.getParameter("tName");//�õ���ʦ
		String tit=request.getParameter("title");//�õ���������
		String title=new String(tit.getBytes("ISO-8859-1"),"utf-8");
		String context=request.getParameter("content");
		String content = new String(context.getBytes("ISO-8859-1"),"utf-8");//�õ���������
		System.out.println("�������ݣ�"+content);
		MessageVO message =new MessageVO();
		
		message.setIdnum(MessageId);
		message.setSidnum(vo);
		message.setTidnum(tvo);
		message.setTitle(title);
		message.setStatus("δ�ظ�");
		message.setContent(content);
		
		message.setMtime(DateConventer.strToTimestamp(DateConventer.dateToStr(new Date())));
		
		//���������Լ������Ա���
		this.studentService.insert(message);
		
		return "addMessageSuccess";
	}
	
	/**
	 * �鿴���Լ�¼
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
	 * �鿴�ѻظ�������
	 */
	public String showReply()
	{
		   HttpServletRequest request = ServletActionContext.getRequest();
		   HttpSession session = request.getSession();
		   String idnum = (String)session.getAttribute("idnum");
		   
		   List<MessageVO> mVOList = this.studentService.showMessage(idnum,"�ѻظ�");
		   //System.out.println("action:"+mVOList.get(0).getIdnum());
		   List<ShowMessageVO> showMessageList=VOconver.voConverdisAlreadyReplyMessage(mVOList);
		   this.setShowMessages(showMessageList);
		return "showReply";
	}
	
	/**
	 * �鿴�ظ�����
	 */
	public String showReplyDetail()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		String sidnum=(String)session.getAttribute("idnum");
		request.setAttribute("studentId", sidnum);
		//�õ����Ա��
		String idnum=request.getParameter("idnum");
		List<MessageVO> mVOList = this.studentService.showMessage(idnum,"������");
		List<ShowMessageVO> showMessageList=VOconver.voConverdisAlreadyReplyMessage(mVOList);
		
		//�õ�ѧ������
		Ems_Student_VO vo = (Ems_Student_VO) this.studentService.displayPersonInformation(sidnum);
		request.setAttribute("sName", vo.getName());
		//�õ���ʦ����
		Ems_Teacher_VO t_name=this.teacherService.displayPersonInformation(mVOList.get(0).getTidnum().getIdnum());
		request.setAttribute("tName", t_name.getName());
		request.setAttribute("title", showMessageList.get(0).getTitle());
		request.setAttribute("contentMessage", showMessageList.get(0).getContent());
		System.out.println(showMessageList.get(0).getContent());
		request.setAttribute("rcontentMessage", showMessageList.get(0).getRcontent());
		
		return "showReplyDetail";
	}
	
	
	
	/********************************************������Ϣ�����޸�************************************************/
	/****�����߲˵����������������****/
	public String addModifyApplication()
	{
		System.out
		.println("=========StudentAction==========addModifyApplication===========");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String idnum = (String) session.getAttribute("idnum");
		System.out.println("�õ��ı�ţ�" + idnum);
		
		//����Ƿ�������δ��ˣ����У���������µ�����
		if(this.studentService.checkApply(idnum,"ems_student"))
		{
			return "CannotAddApply";
		}

		Ems_Student_VO vo = (Ems_Student_VO) this.studentService.displayPersonInformation(idnum);
		List<Ems_Student_VO> liststudents = new ArrayList<Ems_Student_VO>();
		liststudents.add(vo);
		StudentVO studentVO = VOconver.voConver(liststudents).get(0);

//System.out.println("רҵ��"+studentVO.getProf_Name());
//System.out.println("IdCard��"+studentVO.getIdcard());
		request.setAttribute("studentVO", studentVO);
		session.setAttribute("pwd", studentVO.getPassword());
		return "modifyStudentInfo";
	}

	/**
	 * ������ļ�¼����������¼����
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
		
		//��������Ϣд��ѧ�����У�EMS_Student_VO��
		Ems_Student_VO studentVO=this.studentService.displayPersonInformation(applyUserId);
		studentVO.setUsername_temp(name_temp);
		studentVO.setAddress_temp(address_temp);
		studentVO.setIdcrad_temp(idcard_temp);
		this.studentService.update(studentVO);
		
		//�������¼���������¼���У�ApplyModifyLogVO��
		ApplyModifyLogVO applyVO =new ApplyModifyLogVO();
		String fortable="ems_student";
		String modifyResult="�����";
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
	 * �鿴������Ϣ
	 * @return
	 */
	public String listModifyApplication()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		String idnum = (String)session.getAttribute("idnum");
		
		//�ҳ���Ӧ��������¼
		List<ApplyModifyLogVO> applyModify = this.studentService.displayAlreadyApplyStatus(idnum);
		//����
		 Collections.sort(applyModify,new ApplyTeacherComparator());
		List<showApplyModifyLogVO> disvo = VOconver.voConverGb(applyModify);
		this.setApplyList(disvo);
		return "listModifyApplication";
	}
	
	/**
	 * �鿴��ҵ�������ѡ��������Ϣ
	 * @return
	 */
	public String listModifyApplicationSelf()
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		String idnum = (String)session.getAttribute("idnum");
		
		//�ҳ���Ӧ��������¼
		List<ApplyModifyLogVO> applyModify = this.studentService.displayAlreadyApplyStatusSelf(idnum);
		//����
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
