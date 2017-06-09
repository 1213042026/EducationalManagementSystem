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

	private List<EMS_GraduationA> graduateaList;//���������ʾ
	private List<showApplyModifyLogVO> applyModifyList;//������Ϣ��¼��
	private List<ProfessionVO>   professionList;//רҵ
	private List<Ems_Teacher_VO> teacherList ;//��ʦ
	private List<GraduationStudentInfor>  ggStudentInforList;//��ҵ���ѧ����Ϣ������
	private List<StudentGraduationWorksMange>  StudentGraduationWorksMange;//��ҵ���ѧ����Ϣ������
	private List<studentGradeSearch>  studentGradeSearchList;//��ҵ���ѧ����Ϣ������
	private List<disAuditGraduationTitle> disAuditGraTit;//��ʾѧ����ҵ���������Ŀ
	private List<disAuditGraduationResult> disAuditGraResult;//��ʾѧ����ҵ�����˽��
	//private List<InputGraduationGrade>   disInputGraduationGrade;//��ʾ¼���ҵ��Ƴɼ�������
	
	private List<ShowMessageVO> disMessageVO;//��ʾ�ѻظ�����
	
	private List<showAlreadyAddCourse> courseList;//��ʾ����ӿγ�
	private List<ClassTypeVO> courseTypeLit;
	
	private String[] fileList;   //��ʾ�ļ��б�ʹ��
	
	/**�ϴ��ļ��Ľ������ͣ�����Ҫ�����ӦԪ�ص�name����ֵ*/
	private File image;
	/**�ϴ��ļ������֣�����Ϊ����name����FileName�ַ���*/
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
	 *  �ֲ�ˢ����֤��ҵ��������Ƿ����
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
			String msg = "{result:false,desc:\"ʧ��\"}";
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(msg);
		}	   	
	}
	
	
	
	
	
	/******************************************������Ϣά��***********************************************/
	/*****�鿴������Ϣ*****/
	public String displayPersonInformation() {

		System.out
				.println("=========TeacherAction==========displayPersonInformation===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		 //��request�еõ�session
		  HttpSession session = request.getSession(); 
		 String idnum = (String)session.getAttribute("teacheridnum");/////////
		 System.out.println("�õ��ı�ţ�"+idnum);		 
         
		Ems_Teacher_VO vo = (Ems_Teacher_VO) this.teacherService.displayPersonInformation(idnum);
        String profession = vo.getProfession().getPro_name();
        request.setAttribute("profession", profession);
		request.setAttribute("teacherVO", vo);
		
		//��Ϊ����ʾʱû������,�����޸�ʱʹ��
        session.setAttribute("pwd", vo.getPassword());
		return "PersonInformationQuery";
	}
	
	
	/**
	 * update ����ʦ�鿴������Ϣ�޸ĵ�ַ��ťʵ��
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
		//	teacherVO.setSex("��");
		//	break;
		//}
		///teacherVO.setIdcard(idcard);
		teacherVO.setAddress(address);
		///teacherVO.setNation(nation);
		///teacherVO.setScientific(scientific);
		///teacherVO.setTitle(title);	
		request.setAttribute("teacherVO", teacherVO);//���������ʾ�޸ĵ�
		this.teacherService.update( teacherVO);
		return "updateTeacherPersonInfor";
		
	}
	
	/**
	 * 
	 *�޸�����
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
			request.setAttribute("msg", "wrong");//���ھ����벻��ȷ
			return "prePasswordWorng";
		}else{

			//�����ݿ���ȡ�������ŵ�������Ϣ
			Ems_Teacher_VO vo = (Ems_Teacher_VO) this.teacherService.displayPersonInformation(userId);
			vo.setPassword(newpwd);//����Ҫ�޸ĵ�������
			this.teacherService.update(vo);//��������
			
		}
		return "updatePwdSuccess";
	}
	

	/******************************************��ҵ��ƹ���***********************************************/
	/**
	 * 
	 * �鿴����ӱ�ҵ���
	 * 
	 * @return
	 */
	public String displayAlreadyAdd(){
		System.out.println("====teacheraction====displayAlreadyAdd=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("teacheridnum");
		System.out.println("�û�����"+userId);
		
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
		
		List<EMS_Graduation> graduate = this.teacherService.displayAlreadyAddPage(userId,page.getPageNow());//2Ҳ����
		
		//���õ���VO�࣬תΪ��ʾ�Ľ���VO��
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
	 * ������ӱ�ҵ��Ŀ��ʱ��ȡ�����ݿ��¼�е����ID������Ա���ܽ����ֶ�����ID��
	 * @return
	 */
	public String getMaxIdNumAboutGraduate(){
		System.out.println("=============managerAction========getMaxIdNum=========");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		///////////////////////////////////////////////////////////////////////////
		boolean result = this.teacherService.checkTime("��ʦ��ӱ�ҵ���ʱ������");
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
		//�õ�רҵ
		List<ProfessionVO> list = this.managerService.listProfession();	
		this.setProfessionList(list);
		//�õ���ʦ
		List<Ems_Teacher_VO> teacherLit = this.managerService.listTeacher();
		this.setTeacherList(teacherLit);
		
		return "AddGraduationTitle";
	}
	
	/*************��ӱ�ҵ��ư�ť����ʵ��
	 * @throws UnsupportedEncodingException ************/
	public String AddGraduationTitle() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String idnum = request.getParameter("idnum");//��� 
		String diff1 = request.getParameter("diff");//���׳̶�
		String diff = new String(diff1.getBytes("ISO-8859-1"),"utf-8");
		System.out.println("�̶ȣ�"+diff);
		String gname1 = request.getParameter("name");//����
		String gname = new String(gname1.getBytes("ISO-8859-1"),"utf-8");
		String kman = request.getParameter("kman");//��ѡ ����
		String tname = (String)session.getAttribute("teacheridnum");//��ʦ��� 
		//String pname = request.getParameter("pro_name");//רҵ���
	    String att1 = request.getParameter("att");
		String att = new String(att1.getBytes("ISO-8859-1"),"utf-8");//�õ�ѡ����֪
		
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
		 graduationVO.setRemark(att);//��֪
	
		 this.teacherService.insert(graduationVO);
		 
		 return "AddGraduationTitleSucc";
	}
	
	/**
	 * 
	 * ��ʾ�޸�����ӱ�ҵ����б�
	 * 
	 * @return
	 */
	public String updateAlreadyAdd(){
		
		System.out.println("====teacheraction====displayAlreadyAdd=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("teacheridnum");
		System.out.println("�û�����"+userId);
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
		//���õ���VO�࣬תΪ��ʾ�Ľ���VO��
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
	 * ȡ�������Ҫ�޸ĵı�ҵ��Ƶ���Ϣ
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
		
		//�õ�רҵ
		List<ProfessionVO> list = this.managerService.listProfession();	
		this.setProfessionList(list);
		//�õ���ʦ
		List<Ems_Teacher_VO> teacherLit = this.managerService.listTeacher();
		this.setTeacherList(teacherLit);

		// ��������ȡֵ
		request.setAttribute("graduationVO", graduationVO);

		return "showGraduationForModify";
	}
	
	/**
	 * �޸İ�ť��ҵ��ƹ��ܰ�ťʵ��
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String updateButtonGraduation() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String idnum = request.getParameter("idnum");//��� 
		String gname1 = request.getParameter("name");//����
		String gname = new String(gname1.getBytes("ISO-8859-1"),"utf-8");//�õ�ѡ����֪
		String kman = request.getParameter("kman");//��ѡ ����
		
		String diff1 = request.getParameter("diff");//���׳̶�
		String diff = new String(diff1.getBytes("ISO-8859-1"),"utf-8");
		
		String pname = request.getParameter("pro_name");//רҵ���
	    String att1 = request.getParameter("att");
		String att = new String(att1.getBytes("ISO-8859-1"),"utf-8");//�õ�ѡ����֪
		
		 EMS_Graduation graduationVO = this.teacherService.getModifyGraduationInfo(idnum);	
		
		 graduationVO.setGname(gname);//
		 
		 graduationVO.setGlevel(diff);
		 graduationVO.setFlag("teacher");
	
		 ProfessionVO professionVO = new ProfessionVO();
		 professionVO.setIdnum(pname);
		// graduationVO.setProfessionId(professionVO);//
		 //��������,ֻ�ʺ���ѧ����û�п�ʼѡ�ε������
		 graduationVO.setGcount(kman);//
		 graduationVO.setRcount(kman);//		 
		
		 graduationVO.setRemark(att);//��֪
	
		 this.teacherService.update(graduationVO);
		 
		 return "updateGraduationTitleSucc";
	}
	
	
	/**
	 * 
	 * ��ʾҪɾ������ӱ�ҵ����б�
	 * 
	 * @return
	 */
	public String deleteAlreadyAdd(){
		System.out.println("====teacheraction====deleteAlreadyAdd=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("teacheridnum");
		System.out.println("�û�����"+userId);
		
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
			
			List<EMS_Graduation> graduate = this.teacherService.displayAlreadyAddPage(userId,page.getPageNow());//2Ҳ����
		//���õ���VO�࣬תΪ��ʾ�Ľ���VO��
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
	 * ɾ����ҵ��ƹ��ܰ�ťʵ��
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
	    
		////////////////////////////ɾ��һ����ҵ���ʱ�������ʦ�����������ҵ��Ƶ������ļ���Ϣ��Ҫ��ɾ��///////////////////////////////////////////////////
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"
				+tidnum+"/"+idnum);
		System.out.println("realpath:"+realpath);
		File teacherFile = new File(realpath);
		if(teacherFile.exists()){//����ļ�����
			System.out.println("realpath1:"+realpath);
			boolean ff = FileManage.delete(realpath);//ɾ��Ŀ¼,Ϊʲô��־����Ϊ��
			System.out.println("��־��"+ff);
		
		}
		return "disdeleteAlreadyAdd";
	}
	
	/**
	 * ��ʦ�ļ�����ҳ��
	 */
	public String teacherFileManage(){
		System.out.println("====teacheraction====teacherFileManage=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//�õ���ʦ���
		String userId = (String)session.getAttribute("teacheridnum");
		System.out.println("�û�����"+userId);
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
			
			List<EMS_Graduation> graduate = this.teacherService.displayAlreadyAddPage(userId,page.getPageNow());//2Ҳ����
			
//////////////////////////////////3///////////////////////////////////
			request.setAttribute( "pageCount", page.getPageCount() );
			request.setAttribute( "pageNow", page.getPageNow() );
			request.setAttribute( "maxRowCount", page.getMaxRowCount() );
			request.setAttribute( "pageSize", page.getPageSize() );
			//////////////////////////////////////////////////////////////////////
		//���õ���VO�࣬תΪ��ʾ�Ľ���VO��
		this.setGraduateaList(VOconver.voConverGa(graduate));
		return "teacherFileManage";
	}
	
	
	/**
	 * ��ʾ��ʦ�ļ�����ϸ�б�
	 * @return
	 */
	public String disTeacherFileList(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String gidnum = request.getParameter("idnum");//�õ���ҵ��Ʊ��
		String id = request.getParameter("user");//ѧ�������֤
		if(id!=null && id.equals("student")){
			System.out.println("ѧ��");
			request.setAttribute("studentID", "value");//ѧ��ҳ����ʾ��jsp
		}
		System.out.println("�����ţ�"+gidnum);
		String tidnum = (String)session.getAttribute("teacheridnum");//�õ���ʦ���
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"+tidnum +"/"+ gidnum);
		File soure = new File(realpath);
		String[]fileLista = null;
		if(soure.exists()){//���Ŀ¼��Ϊ�գ��ʹ�ָ��Ŀ¼��ȡ 
		fileLista  = soure.list();			
		}else{
			System.out.println("��");
		}
		//���ļ�����argF����Ӧ��Ŀ¼�µ������ļ��붼����ļ�����
		//File[]file = argF.listFiles();	
		//System.out.println("�ļ���"+fileLista[0]);
	//	request.setAttribute("fileList", fileLista);
		//request.setAttribute("a", "a");
		request.setAttribute("graID", gidnum);
		this.setFileList(fileLista);
		return "fileUpLoadTest";
	}

	
	
	/**
	 * �ļ��ϴ�test
	 * @throws IOException 
	 */
	public String fileUpLoadTest() throws IOException {
		
		//�õ�Ӧ�ó���ľ���·�������û���Ŀ¼����ַ���
		//�Ǹ���ʦ����һ��0001Ӧ���ǵõ��Ľ�ʦ�ı�ţ�����ͨ��session�õ�
		//�Ǹ���ҵ��ƣ�ÿ����0001Ӧ���Ǳ�ҵ��Ƶı��,����ͨ�������б��¼�õ�
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String gidnum = request.getParameter("graID");//�õ���ҵ��Ʊ��
		System.out.println("gidnum:"+gidnum);
		String tidnum = (String)session.getAttribute("teacheridnum");//�õ���ʦ���
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"+tidnum +"/"+ gidnum);
	    System.out.println("realpath:"+realpath);
//		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/0001/0001");
//      /  System.out.println(realpath);		
	    if(image != null) {
	    	File savefi = new File(realpath);
	    	if(!savefi.exists())
	    		savefi.mkdirs();
	    	//�ڷ�������imagesĿ¼�н�����Ӧ���ļ�
	    	File savefile = new File(savefi,imageFileName);
	    	//��θ�·�������ڣ����ڷ������Ͻ�����Ŀ¼
	    	//if(!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
	    	
	    	//�����ļ�����Ҫ�����ļ������ݸ��Ƶ��������Ͻ��õ��ļ�
	    	FileUtils.copyFile(image, savefile);
	    		    	
	    }

	    request.setAttribute("idnum", gidnum);//���㷵��ʱʹ��
	return "fileUpLoadTestSucc";	
	}
	
	/**
	 * ��ӱ�ҵ�����ʱ���ļ��ϴ�test
	 * @throws IOException 
	 */
	public void fileUpLoadTestAdd() throws IOException {
		
		//�õ�Ӧ�ó���ľ���·�������û���Ŀ¼����ַ���
		//�Ǹ���ʦ����һ��0001Ӧ���ǵõ��Ľ�ʦ�ı�ţ�����ͨ��session�õ�
		//�Ǹ���ҵ��ƣ�ÿ����0001Ӧ���Ǳ�ҵ��Ƶı��,����ͨ�������б��¼�õ�
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String gidnum = request.getParameter("graID");//�õ���ҵ��Ʊ��
		System.out.println("gidnum:"+gidnum);
		String tidnum = (String)session.getAttribute("teacheridnum");//�õ���ʦ���
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"+tidnum +"/"+ gidnum);
	    System.out.println("realpath:"+realpath);
//		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/0001/0001");
//      /  System.out.println(realpath);		
	    if(image != null) {
	    	File savefi = new File(realpath);
	    	if(!savefi.exists())
	    		savefi.mkdirs();
	    	//�ڷ�������imagesĿ¼�н�����Ӧ���ļ�
	    	File savefile = new File(savefi,imageFileName);
	    	//��θ�·�������ڣ����ڷ������Ͻ�����Ŀ¼
	    	//if(!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
	    	
	    	//�����ļ�����Ҫ�����ļ������ݸ��Ƶ��������Ͻ��õ��ļ�
	    	FileUtils.copyFile(image, savefile);
	    		    	
	    }

	 //   request.setAttribute("idnum", gidnum);//���㷵��ʱʹ��
	//return "fileUpLoadTestAdd";	
	}
	
	/**
	 * ɾ����ʦ�ϴ����ļ�
	 * 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String delTeacherUpFile() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String gidnum = request.getParameter("gidnum");//�õ���ҵ��Ʊ��
		System.out.println("DELgidnum:"+gidnum);
		String tidnum = (String)session.getAttribute("teacheridnum");//�õ���ʦ���
		String gname1 = request.getParameter("gname");//�õ�������
		String gname = new String(gname1.getBytes("ISO-8859-1"),"utf-8");//�õ�ѡ����֪
		System.out.println("Gname:"+gname);
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"+tidnum +"/"+ gidnum);
		System.out.println("realpath:"+realpath);
		File realPath =new File(realpath);
		File delFile = new File(realPath,gname);
		delFile.delete();
		 request.setAttribute("idnum", gidnum);//���㷵��ʱʹ��
	  return "delTeacherUpFileSucc";
	}
	

	
	public String downfiletest1() throws IOException {
	
//		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpSession session = request.getSession();
//		String gidnum = request.getParameter("gidnum");//�õ���ҵ��Ʊ��
//		System.out.println("DELgidnum:"+gidnum);
//		String tidnum = (String)session.getAttribute("idnum");//�õ���ʦ���
//		String gname1 = request.getParameter("gname");//�õ�������
//		String gname = new String(gname1.getBytes("ISO-8859-1"),"utf-8");//�õ�ѡ����֪
//		System.out.println("Gname:"+gname);
//		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"+tidnum +"/"+ gidnum);
//		System.out.println("realpath:"+realpath);
//		File realPath =new File(realpath);
//		File delFile = new File(realPath,gname);
		
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpSession session = request.getSession();
	String gidnum = request.getParameter("gidnum");//�õ���ҵ��Ʊ��
	System.out.println("DOWNgidnum:"+gidnum);
	String tidnum = (String)session.getAttribute("teacheridnum");//�õ���ʦ���
	String gname1 = request.getParameter("gname");//�õ�������
	String gname = new String(gname1.getBytes("ISO-8859-1"),"utf-8");//�õ�ѡ����֪
	System.out.println("Gname:"+gname);
	
	String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"+tidnum +"/"+ gidnum);
	System.out.println("realpath:"+realpath);
	File realPath =new File(realpath);
	File downFile = new File(realPath,gname);//��������·��
	
//	String path=request.getParameter("path");		//��ȡ�ϴ��ļ���·��
//	path=new String(path.getBytes("iso-8859-1"));
//	File file = new File(path);						//���ݸ�·�������ļ�����
	
//	InputStream in = new FileInputStream(file);		//�����ļ��ֽ�������
	InputStream in = new FileInputStream(downFile);		//�����ļ��ֽ�������
//	OutputStream os = response.getOutputStream();	//�������������
	OutputStream os = response.getOutputStream();	//�������������
	
//	response.addHeader("Content-Disposition", "attachment;filename="
//			+ new String(file.getName().getBytes("gbk"),"iso-8859-1"));	//����Ӧ��ͷ��Ϣ
//	response.addHeader("Content-Length", file.length() + "");
//	response.setCharacterEncoding("gbk");		
//	response.setContentType("application/octet-stream");
//	int data = 0;
//	while ((data = in.read()) != -1) {				//ѭ����ȡ�ļ�
//		os.write(data);								//��ָ��Ŀ¼��д�ļ�
//	}
//	os.close();										//�ر���
//	in.close();

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
	
	
	/****************************************ѧ���ļ�����**********************************************/
	/**
	 * ��ʾѧ���굥
	 */
	public String disStudentFileList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String sidnum = request.getParameter("sidnum");//�õ�ѧ�����
		System.out.println("ѧ����ţ�"+sidnum);
		//String tidnum = (String)session.getAttribute("idnum");//�õ���ʦ���
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/student/"+sidnum);
		System.out.println("ѧ��·����"+realpath);
		File soure = new File(realpath);
		String[]fileLista = null;
	
		if(soure.exists()){//���Ŀ¼��Ϊ�գ��ʹ�ָ��Ŀ¼��ȡ 
		fileLista  = soure.list();			
		}else{
			System.out.println("Ŀ¼Ϊ��");
		}
		//���ļ�����argF����Ӧ��Ŀ¼�µ������ļ��붼����ļ�����
		//File[]file = argF.listFiles();	
		//System.out.println("�ļ���"+fileLista[0]);
	//	request.setAttribute("fileList", fileLista);
		//request.setAttribute("a", "a");
		request.setAttribute("sidnum", sidnum);//�������أ�ɾ��ʱ����
		this.setFileList(fileLista);
		//fileUpLoadTest
		return "StudentFileList";
		
	}
	
	
	/**
	 * ѧ���ļ����� 
	 * @return
	 */
	public String studentFileManage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("��ʦ��ţ�"+teacherid);
		
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
	 * ɾ��ѧ���ļ�
	 * @throws UnsupportedEncodingException 
	 */
   public String delStudentUpFile() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String sidnum = request.getParameter("sidnum");//�õ�ѧ�����
		System.out.println("DELsidnum:"+sidnum);
	
		String sname1 = request.getParameter("sname");//�õ�ѧ���ļ���
		String sname = new String(sname1.getBytes("ISO-8859-1"),"utf-8");//�õ�ѡ����֪
		System.out.println("Gname:"+sname);
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/student/"+sidnum);
		System.out.println("realpath:"+realpath);
		File realPath =new File(realpath);
		File delFile = new File(realPath,sname);
		delFile.delete();
		 request.setAttribute("sidnum", sidnum);//���㷵��ʱʹ��
	  return "delStudentUpFileSucc";
	   
   }	

   
   /**
    * ����ѧ���ļ�
 * @throws IOException 
    */
   public String downStudentFile() throws IOException{
	   HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		String sidnum = request.getParameter("sidnum");//�õ�ѧ�����
		System.out.println("DOWNgidnum:"+sidnum);
		
		String gname1 = request.getParameter("sname");//�õ������ļ�
		String stuFile = new String(gname1.getBytes("ISO-8859-1"),"utf-8");
		System.out.println("Sname:"+stuFile);
		
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/student/"+sidnum);
		System.out.println("realpath:"+realpath);
		File realPath =new File(realpath);
		File downFile = new File(realPath,stuFile);//��������·��
		InputStream in = new FileInputStream(downFile);		//�����ļ��ֽ�������
//		OutputStream os = response.getOutputStream();	//�������������
		OutputStream os = response.getOutputStream();	//�������������


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
	/*************************************************************************
	 * ��ѯ��ҵ���ѧ����Ϣ
	 * @return
	 */
	public String studentInforSearch() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("��ʦ��ţ�"+teacherid);
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
	 * ѧ���ɼ���ѯ
	 * 
	 * �������װ����
	 * 
	 */
	public String studentGradeSearch() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("��ʦ��ţ�"+teacherid);
/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
	       int counts=this.teacherService.studentGradeSearchCounts(teacherid);	//�õ����м�¼��	
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
		
		List<studentGradeSearch> gStudentInforList = new ArrayList<studentGradeSearch>();//������
		for(Object[] obj:list){
			studentGradeSearch gStudentInfor = new studentGradeSearch();
			EMS_Graduation g = (EMS_Graduation)obj[0];
			EMS_GraduateGrade gg = (EMS_GraduateGrade)obj[1];
					
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
			System.out.println("������"+g.getGname());
			gStudentInfor.setGname(g.getGname());//����
			gStudentInfor.setSidnum(s.getIdnum());//ѧ��
			gStudentInfor.setSname(s.getName());//ѧ������
			gStudentInfor.setGidnum(g.getIdnum());//�ϱ��
			gStudentInfor.setGrade(gg.getGrade());//�ɼ�
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
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
	
	
	/**************************ѧ���ɼ�¼��*************************************************/
	/**
	 * ��ʾ¼��ɼ��б�
	 */
	public String studentGradeInput(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("��ʦ��ţ�"+teacherid);

/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
int counts=this.teacherService.studentGradeInputCounts(teacherid);	//�õ����м�¼��	
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
	 * ¼��ɼ����ܰ�ťʵ��
	 * @return
	 */
	public String studentGradeInputSubmit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String sidnum= request.getParameter("sidnum");
		String grade = request.getParameter(sidnum);//�õ��ɼ�
		String ggidnum = request.getParameter("ggidnum");//�õ���ҵ��Ƽ�¼����
		System.out.println("ѧ�ţ�"+sidnum);//ѧ��
		System.out.println("�ɼ���"+grade);//�ɼ�
		System.out.println("��ҵ��¼��ţ�"+ggidnum);
		
		EMS_GraduateGrade gg = this.teacherService.getSpecifyGraduationG(ggidnum);
		gg.setIdnum(ggidnum);//��ҵ��Ƽ�¼����
		gg.setGrade(grade);//�ɼ�
		this.teacherService.update(gg);
		return "studentGradeInputSubmit";
	}
	
	/**************************ѧ���ɼ��޸�*************************************************/
	/**
	 * ��ʾ�޸ĳɼ��б�
	 */
	public String studentGradeupdate(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("��ʦ��ţ�"+teacherid);
		   int counts=this.teacherService.studentGradeupdateCounts(teacherid);	

		 //�õ����м�¼��	
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
	 * �޸ĳɼ����ܰ�ťʵ��
	 * @return
	 */
	public String studentGradeUpdateSubmit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String sidnum= request.getParameter("sidnum");
		String grade = request.getParameter(sidnum);//�õ��ɼ�
		String ggidnum = request.getParameter("ggidnum");//�õ���ҵ��Ƽ�¼����
		System.out.println("ѧ�ţ�"+sidnum);//ѧ��
		System.out.println("�ɼ���"+grade);//�ɼ�
		System.out.println("��ҵ��¼��ţ�"+ggidnum);
		
		EMS_GraduateGrade gg = this.teacherService.getSpecifyGraduationG(ggidnum);
		gg.setIdnum(ggidnum);//��ҵ��Ƽ�¼����
		gg.setGrade(grade);//�ɼ�
		this.teacherService.update(gg);
		return "studentGradeUpdateSubmit";
	}
	
	/**********************************�γ̹���**************************************/
	/**
	 * ��ʾ����ӿγ�
	 */
	public String displayAlreadyAddCourse() {
		System.out.println("====teacheraction====displayAlreadyAddCourse=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("teacheridnum");
		System.out.println("�û�����"+userId);
	       int counts=this.teacherService.CourseCounts(userId);	

	     //�õ����м�¼��	
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
		//���õ���VO�࣬תΪ��ʾ�Ľ���VO��
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
	 * ���õ�����ţ�������ӽ���
	 * @return
	 */
	public String getMaxIdNumAboutCourse(){
System.out.println("=============teacherAction========getMaxIdNumAboutCourse=========");
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		boolean result = this.teacherService.checkTime("��ʦ��ӿγ�ʱ������");
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
		//�õ�רҵ
		List<ProfessionVO> list = this.managerService.listProfession();	
		this.setProfessionList(list);
		//�õ��γ�����
		List<ClassTypeVO> teacherLit = this.teacherService.listClassType();
		
		this.setCourseTypeLit(teacherLit);
		
		return "AddCourse";
	}
	
	
	/**
	 * ����γ̰�ťʵ��
	 * @return
	 */
	public String insertCourse() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
//	       classId varchar2(20) primary key ,    --�γ̱�ţУ�
//	       className varchar2(100),              --�γ�����
//	       classType varchar2(20),               --�γ����ͣƣ�
//	       teacherId varchar2(20),               --��ʦ���F K    
//	       term varchar2(10),                    --ѧ��
//	       grade varchar2(20),                   --�����꼶
//	       professionId varchar2(20),            --����רҵF K(������Ϊ�������޿Σ�ע��һ��)
//	       kCount varchar2(10),                  --��ѡ����
//	       sCount varchar2(10),                  --ʣ������
//	       recordTime timestamp,                 --¼��γ�ʱ��
//	       remark  varchar2(300),                --��ע
	     
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
		System.out.println("רҵ��"+professionid);
		ProfessionVO proVO = new ProfessionVO();
	    proVO.setIdnum(professionid);//fk
		classVO.setProfessionId(proVO);
	
		String courseType = request.getParameter("courseType"); 
		System.out.println("�γ����ͣ�"+courseType);
		ClassTypeVO ctypeVO = new ClassTypeVO();
		ctypeVO.setIdnum(courseType);//fk
		classVO.setClassType(ctypeVO);
		
		
	
		//�õ�ѧ��
		String term = "2";
		Calendar cale = Calendar.getInstance();
		int nowMonth = cale.get(Calendar.MONTH);
		System.out.println("�·ݣ�"+nowMonth);
	    if(nowMonth>=6){
	    	term = "1";//�����6���Ժ���·ݣ���Ϊ��һѧ��,java��1�±�ʾ0
	    }
		classVO.setTerm(term);
		
		String grade = request.getParameter("grade");//
		classVO.setGrade(grade);
	
		String kxPeople = request.getParameter("kxPeople");//
		//ʣ������һ��
		classVO.setkCount(kxPeople);
		classVO.setsCount(kxPeople);
		
		classVO.setRecordTime(DateConventer.strToTimestamp(DateConventer.dateToStr(new Date())));//
		
		String remark = request.getParameter("remark");
		classVO.setRemark(remark);	

		


		this.teacherService.insert(classVO);
		return "AddGraduationTitleSucc";
	}
	
	
	
	/**
	 * ��ʾ�޸Ŀγ��б�
	 */
	public String updateAlreadyAddCourse(){
		
		System.out.println("====teacheraction====displayAlreadyAddCourse=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("teacheridnum");
		System.out.println("�û�����"+userId);

/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
int counts=this.teacherService.CourseCounts(userId);	//�õ����м�¼��	
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
		//���õ���VO�࣬תΪ��ʾ�Ľ���VO��
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
	 *  ��ʾ�����γ��޸Ľ���
	 */
	public String disupdateCourseList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String cidnum = request.getParameter("cidnum");
		System.out.println("�γ̱��:"+cidnum);
	    EMS_Class courseClass = this.teacherService.getModifyCourseInfo(cidnum);
	    request.setAttribute("KXRS", courseClass.getkCount());//��ѡ ����
	    request.setAttribute("courseClass", courseClass);
	  //�õ�רҵ
		List<ProfessionVO> list = this.managerService.listProfession();	
		this.setProfessionList(list);
		//�õ��γ�����
		List<ClassTypeVO> teacherLit = this.teacherService.listClassType();
		this.setCourseTypeLit(teacherLit);
	    return "disupdateCoursePerson";
	}
	
	/**
	 * �޸İ�ť����ʵ��
	 */
	public String updateCourseButton() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String idnum = request.getParameter("idnum");//
	    EMS_Class classVO = this.teacherService.getModifyCourseInfo(idnum);//�����ݿ��еõ���Ӧ��ŵĿγ�
		
		//String idnum = request.getParameter("idnum");//
		classVO.setClassId(idnum);
		
		String name = request.getParameter("name");//
		classVO.setClassName(name);

		String tidnum = (String)session.getAttribute("teacheridnum");
		Ems_Teacher_VO tVO =  new Ems_Teacher_VO();
		tVO.setIdnum(tidnum);//fk		
		classVO.setTeacherId(tVO);
		
		
		String professionid = request.getParameter("profession");	
		System.out.println("רҵ��"+professionid);
		ProfessionVO proVO = new ProfessionVO();
	    proVO.setIdnum(professionid);//fk
		classVO.setProfessionId(proVO);
	
		String courseType = request.getParameter("courseType"); 
		System.out.println("�γ����ͣ�"+courseType);
		ClassTypeVO ctypeVO = new ClassTypeVO();
		ctypeVO.setIdnum(courseType);//fk
		classVO.setClassType(ctypeVO);
		
		//String term = request.getParameter("term");	//
		//classVO.setTerm(term);
		
		String grade = request.getParameter("grade");//
		classVO.setGrade(grade);
	
		String kxPeople = request.getParameter("kxPeople");//
		//ʣ������һ��
		classVO.setkCount(kxPeople);
		classVO.setsCount(kxPeople);
		
		classVO.setRecordTime(DateConventer.strToTimestamp(DateConventer.dateToStr(new Date())));//
		
		String remark = request.getParameter("remark");
		classVO.setRemark(remark);	

		


		this.teacherService.update(classVO);
		return "updateGraduationTitleSucc";
	}
	
	/**
	 * ��ʾɾ���γ̽���
	 */
	public String deleteAlreadyAddCourse() {
		System.out.println("====teacheraction====displayAlreadyAddCourse=======");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("teacheridnum");
		
		System.out.println("�û�����"+userId);

/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
int counts=this.teacherService.CourseCounts(userId);	//�õ����м�¼��	
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
		//���õ���VO�࣬תΪ��ʾ�Ľ���VO��
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
	 * ɾ�����ܰ�ťʵ��
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
	 * ѡ��ѧ����Ϣ��ѯ
	 */
	public String studentInforSearchCourse() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("��ʦ��ţ�"+teacherid);

/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
int counts=this.teacherService.studentInforSearchCourseCounts(teacherid);	

//�õ����м�¼��	
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
	 * ѡ��ѧ���ɼ���ѯ
	 * @return
	 */
	public String studentGradeSearchCourse() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("��ʦ��ţ�"+teacherid);
		

/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
int counts=this.teacherService.studentGradeSearchCourseCounts(teacherid);	

//�õ����м�¼��	
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
		
		List<studentGradeSearch> gStudentInforList = new ArrayList<studentGradeSearch>();//������
		for(Object[] obj:list){
			studentGradeSearch gStudentInfor = new studentGradeSearch();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
					
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
//			System.out.println("������"+g.getGname());
			gStudentInfor.setGname(g.getClassName());//�γ���
			gStudentInfor.setSidnum(s.getIdnum());//ѧ��
			gStudentInfor.setSname(s.getName());//ѧ������
			gStudentInfor.setGidnum(g.getClassId());//�ϱ��,�γ̱��
			gStudentInfor.setGrade(gg.getGrade());//�ɼ�
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
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
		System.out.println("��ʦ��ţ�"+teacherid);
		String sidnum = request.getParameter("sidnum");


/////////////////////////////////////////////////////////////////////////////////////
		List<Object[]>  list = this.teacherService.graStuGragradeCourse(teacherid,sidnum,0);
		
		List<studentGradeSearch> gStudentInforList = new ArrayList<studentGradeSearch>();//������
		for(Object[] obj:list){
			studentGradeSearch gStudentInfor = new studentGradeSearch();
			EMS_Class g = (EMS_Class)obj[0];
			EMS_CourseRecord gg = (EMS_CourseRecord)obj[1];
					
			Ems_Student_VO s = (Ems_Student_VO)obj[2];
//			System.out.println("������"+g.getGname());
			gStudentInfor.setGname(g.getClassName());//�γ���
			gStudentInfor.setSidnum(s.getIdnum());//ѧ��
			gStudentInfor.setSname(s.getName());//ѧ������
			gStudentInfor.setGidnum(g.getClassId());//�ϱ��,�γ̱��
			gStudentInfor.setGrade(gg.getGrade());//�ɼ�
			
			gStudentInforList.add(gStudentInfor);//���뵥����¼
		}
		
		this.setStudentGradeSearchList(gStudentInforList);


//////////////////////////////////////////////////////////////////////
	return "studentGradeSearchCourseCon";
	
		
	}
	
	/**************************ѧ���ɼ�¼��*************************************************/
	/**
	 * ��ʾ¼��ɼ��б�
	 */
	public String studentCourseGradeInput(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("��ʦ��ţ�"+teacherid);
		

/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
int counts=this.teacherService.studentCourseGradeInputCounts(teacherid);	

//�õ����м�¼��	
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
	 * ¼��ɼ����ܰ�ťʵ��
	 * @return
	 */
	public String studentCourseGradeInputSubmit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String sidnum= request.getParameter("sidnum");
		String grade = request.getParameter(sidnum);//�õ��ɼ�
		String ggidnum = request.getParameter("ggidnum");//�õ�ѡ�μ�¼����
		System.out.println("ѧ�ţ�"+sidnum);//ѧ��
		System.out.println("�ɼ���"+grade);//�ɼ�
		System.out.println("��ҵ��¼��ţ�"+ggidnum);
		
		EMS_CourseRecord gg = this.teacherService.getSpecifyCourseRecord(ggidnum);
		gg.setIdnum(ggidnum);//ѡ�μ�¼����
		gg.setGrade(grade);//�ɼ�
		this.teacherService.update(gg);
		return "studentCourseGradeInputSubmit";
	}
	
	/**************************ѧ���ɼ��޸�*************************************************/
	/**
	 * ��ʾ�޸ĳɼ��б�
	 */
	public String studentCourseGradeupdate(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String teacherid = (String)session.getAttribute("teacheridnum");
		System.out.println("��ʦ��ţ�"+teacherid);

/////////////////////////////////////////////////////////////////////////////////////
//////////////////////1/////	
int counts=this.teacherService.studentCourseGradeupdateCounts(teacherid);	

//�õ����м�¼��	
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
	 * �޸ĳɼ����ܰ�ťʵ��
	 * @return
	 */
	public String studentCourseGradeUpdateSubmit(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String sidnum= request.getParameter("sidnum");
		String grade = request.getParameter(sidnum);//�õ��ɼ�
		String ggidnum = request.getParameter("ggidnum");//�õ���ҵ��Ƽ�¼����
		System.out.println("ѧ�ţ�"+sidnum);//ѧ��
		System.out.println("�ɼ���"+grade);//�ɼ�
		System.out.println("��ҵ��¼��ţ�"+ggidnum);
		
		EMS_CourseRecord gg = this.teacherService.getSpecifyCourseRecord(ggidnum);
		gg.setIdnum(ggidnum);//ѡ�μ�¼����
		gg.setGrade(grade);//�ɼ�
		this.teacherService.update(gg);
		return "studentCourseGradeUpdateSubmit";
	}
	
	/********************************************�������******************************************************/
	
	/**
	 * ���������밴ťʱ
	 * 
	 * ��֤�Ƿ���ԣ�������� 
	 */
	public String addModifyApplycheck(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String idnum = (String)session.getAttribute("teacheridnum");
		ApplyModifyLogVO applyVO = this.teacherService.addModifyApplycheck(idnum);
		if(applyVO != null) {//�������������
			System.out.println("���ܼ�");
			//request.setAttribute("check", "check");//����ҳ����֤
			return "checkWrong";
		}
		System.out.println("�ܼ�");
		return "addModifyApplycheck";
	}
	
	/*****�������****/
	public String insertApplyInfor(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		String idnum = (String)session.getAttribute("teacheridnum");
		String name_temp = request.getParameter("name_temp");
		String idcard_temp = request.getParameter("idcard_temp");
		System.out.println("��ʦ��ţ�"+idnum);
		//1.д�뵽��ʦ��
		//�õ���ʦ��Ϣ
		Ems_Teacher_VO teacherVO = this.teacherService.displayPersonInformation(idnum);
		teacherVO.setName_temp(name_temp);//��ע����
		teacherVO.setIdcard_temp(idcard_temp);//��עʱ��
		//ִ��
		this.teacherService.update(teacherVO);
		//2.��ӵ������
		String maxIdnum = this.teacherService.getMaxIdNumAboutApplyModify();
		if( ! "".equals( maxIdnum ) ){
			int id = Integer.valueOf(maxIdnum) + 1 ;
			DecimalFormat d = new DecimalFormat("0000");
			maxIdnum = d.format(id);
		}
		ApplyModifyLogVO applyVO = new ApplyModifyLogVO();
		applyVO.setIdnum(maxIdnum);//���
		applyVO.setApplyUserId(idnum);//�����û�ID
		Timestamp applyTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		applyVO.setApplyDate(applyTime);//�������ʱ��
		applyVO.setFortable("ems_teacher");//�û� ���ڱ�
		applyVO.setModifyResult("�����");//���״̬
		//ִ��
		this.teacherService.insert(applyVO);
		return "insertApplyInforSuccess";
	}
	
	
	/*****�鿴������״̬****/
	public String displayAlreadyApplyStatus() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		String idnum = (String)session.getAttribute("teacheridnum");
		//////////////////////1/////	
	       int counts=this.teacherService.displayAlreadyApplyStatusCounts(idnum);	//�õ����м�¼��	
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
		//�ҳ���Ӧ��������¼
		List<ApplyModifyLogVO> applyModify = this.teacherService.displayAlreadyApplyStatus(idnum,page.getPageNow());
		//����
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
	
	
	
	/*******************************��˹���************************************************/
	//��ʾҪ��˵ı�ҵ�����Ŀ
    public String disAuditGraduationTitle(){
	   HttpServletRequest request = ServletActionContext.getRequest();
	   HttpSession session = request.getSession();
	   String tidnum = (String)session.getAttribute("teacheridnum");		
		List<disAuditGraduationTitle> gStudentInforList = this.teacherService.disAuditGraduationTitle(tidnum);
		this.setDisAuditGraTit(gStudentInforList);
		return "auditGraduationInfor";
    }
    
   /**
    * �����˰�ť,��ʾ�����Ϣ 
    * @return
    */
   public String auditGraduation(){
	   HttpServletRequest request = ServletActionContext.getRequest();
	   String sidnum = request.getParameter("sidnum");//ѧ��
	   String gidnum = request.getParameter("gidnum");//��ҵ��ƺ�
	   System.out.println("�����˰�ť:"+gidnum);
	   String ggidnum = request.getParameter("ggidnum");//��ҵ��Ƽ�¼���
	   String  applyDate = request.getParameter("applyDate");//����ʱ��
	   EMS_Graduation applyGra = this.teacherService.getModifyGraduationInfo(gidnum);
	   //ȥ��ѧ��	 
	   request.setAttribute("graduationVO", applyGra);
	   request.setAttribute("applyid", applyDate);//
	   request.setAttribute("sidnum", sidnum);//
	   request.setAttribute("gidnum", applyGra.getIdnum());//
	   request.setAttribute("ggidnum", ggidnum);//
	   request.setAttribute("applyDate", applyDate);//
	   return "disAuditGraduationInfor";
   }
   
   /**
    * �����˱�ҵ���---ͨ��---- ��ť
    * 1.���������ļ�¼
	* 2.�޸ı�ҵ��Ʊ��еı�־,���ҿ�ѡ ������1
    * 3.�ڱ�ҵ���ѡ���¼���н����ѧ����ѡ���¼�������  
    * @return
    */
   public String teacherAuditGraduationPass(){
	   HttpServletRequest request = ServletActionContext.getRequest();
	   HttpSession session = request.getSession();
	   String sidnum = request.getParameter("sidnum");//ѧ��
	   String gidnum = request.getParameter("gidnum");//��ҵ��ƺ�
	   System.out.println("��ҵ��Ʊ��:"+gidnum);
	   String ggidnum = request.getParameter("ggidnum");//��ҵ��Ƽ�¼���
	   String  applyDate = request.getParameter("applyid");//����ʱ��
	   //1�����
		ApplyModifyLogVO applyVO = this.managerService.getAuditInfor(ggidnum);//1
		//���״̬
		applyVO.setModifyResult("��ͨ��");
		Timestamp auditTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		//���ʱ�� 
		applyVO.setAuditDate(auditTime);
		//�����
		String auditMan = (String)session.getAttribute("teacheridnum");
	//	System.out.println("�����:" +auditMan);
		applyVO.setAuditMan(auditMan);
	//	applyVO.setRemark("");//��ע,ȥ��ѧ��
		this.teacherService.update(applyVO);//
		
		//2��ҵ��Ʊ�
		EMS_Graduation graduation = this.teacherService.getModifyGraduationInfo(gidnum);//2
		int kCount = Integer.valueOf(graduation.getRcount()) - 1;
		graduation.setRcount(String.valueOf(kCount));//ʣ��������1
		graduation.setFlag("teacher");
		this.teacherService.update(graduation);//
		
		//3��ҵ���ѡ���¼��
		String maxIdnum = this.teacherService.getMaxIdNumAboutGraduateGrade();
		if( maxIdnum!=null ){
			int id = Integer.valueOf(maxIdnum) + 1 ;
			DecimalFormat d = new DecimalFormat("0000");
			maxIdnum = d.format(id);
		}else{
			maxIdnum = "0001";
		}
		EMS_GraduateGrade graduateGrade = new EMS_GraduateGrade();
		graduateGrade.setIdnum(maxIdnum);//���		
		EMS_Graduation gra = new EMS_Graduation();
		gra.setIdnum(gidnum); 
		graduateGrade.setGidnum(gra);//��ҵ��Ʊ��
		Ems_Student_VO emsStu = new Ems_Student_VO();
		emsStu.setIdnum(sidnum);
		graduateGrade.setStudentId(emsStu);//ѧ��
		Timestamp selectTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		graduateGrade.setGxtime(selectTime);//ѡ��ʱ�� 
		this.teacherService.insert(graduateGrade);
		return "teacherAuditGraduationSucc";
   }
   
   /**
    * �����˱�ҵ��� ---��ͨ��---- ��ť
    * 1.���������ļ�¼
	* 2.�޸ı�ҵ��Ʊ��еı�־,ɾ��������¼    *   
    * @return
 * @throws UnsupportedEncodingException 
    */
   public String teacherAuditGraduationNoPass() throws UnsupportedEncodingException {
	   HttpServletRequest request = ServletActionContext.getRequest();
	   HttpSession session = request.getSession();
	   String sidnum = request.getParameter("sidnum");//ѧ��
	   String gidnum = request.getParameter("gidnum");//��ҵ��ƺ�
	   System.out.println("��ҵ��Ʊ��:"+gidnum);
	   String ggidnum = request.getParameter("ggidnum");//��ҵ��Ƽ�¼���
	   String  applyDate = request.getParameter("applyid");//����ʱ��
	   String  attitude1 = request.getParameter("att");//������
	   System.out.println("��attitude:"+attitude1);
		String attitude = new String(attitude1.getBytes("ISO-8859-1"),"utf-8");//�õ�ѡ����֪
	   //1�����
		ApplyModifyLogVO applyVO = this.managerService.getAuditInfor(ggidnum);//1
		//���״̬
		applyVO.setModifyResult("δͨ��");
		Timestamp auditTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		//���ʱ�� 
		applyVO.setAuditDate(auditTime);
		//�����
		String auditMan = (String)session.getAttribute("teacheridnum");
	//	System.out.println("�����:" +auditMan);
		applyVO.setAuditMan(auditMan);
		
		applyVO.setRemarks(attitude);//������
		this.teacherService.update(applyVO);//
		
		//2��ҵ��Ʊ�
		EMS_Graduation graduation = this.teacherService.getModifyGraduationInfo(gidnum);//2		
		this.teacherService.delete(graduation);//
		
	
		return "teacherAuditGraduationSucc";
   }
   
   /**
    * �鿴��˱�ҵ��ƽ��
    * @return
    */
   public String disAuditGraduationStatus(){
	   HttpServletRequest request = ServletActionContext.getRequest();
	   HttpSession session  = request.getSession();
	   String tidnum = (String)session.getAttribute("teacheridnum");
	   List<disAuditGraduationResult> applyModify = this.teacherService.disAditInfor(tidnum);
		
		
		//����
		 Collections.sort(applyModify,new disAuditGraduationResultComparator());		 
	
		this.setDisAuditGraResult(applyModify);
		 return "disAuditGraduationStatus";
	   
   }
   
	/*******************************���Թ���************************************************/
	//��ʾ�ѻظ�����
   public String disAlreadyReplyMessage(){
	   
	   HttpServletRequest request = ServletActionContext.getRequest();
	   HttpSession session = request.getSession();
	   String tidnum = (String)session.getAttribute("teacheridnum");
		
		//////////////////////1/////	
	       int counts=this.teacherService.disAlreadyReplyMessageCounts(tidnum,"�ѻظ�");	//�õ����м�¼��	
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
	   List<ShowMessageVO> mVOList = this.teacherService.disAlreadyReplyMessage(tidnum,"�ѻظ�",page.getPageNow());
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
   
   //�ظ�����
   public String disReplyMessage() {
	   HttpServletRequest request = ServletActionContext.getRequest();
	   HttpSession session = request.getSession();
	   String tidnum = (String)session.getAttribute("teacheridnum");
	   
	   List<ShowMessageVO> mVOList = this.teacherService.disAlreadyReplyMessage(tidnum,"δ�ظ�");
	   //System.out.println("action:"+mVOList.get(0).getIdnum());
	   this.setDisMessageVO(mVOList);
	   
	   return "disReplyMessage";
   }
   
   /**
    * �ظ�ȷ����ť��ʵ��
    * 
    */
   public String disReplyMessageSubmit() throws UnsupportedEncodingException{
	   HttpServletRequest request = ServletActionContext.getRequest();
	   String idnum = request.getParameter("idnum");//�õ����
	   String rcontent1 = request.getParameter("rcontent");//�õ��ظ�����
	   String rcontent = new String(rcontent1.getBytes("ISO-8859-1"),"utf-8");
	   //System.out.println("Action:"+idnum);
	   MessageVO mvo = this.teacherService.getMessageVO(idnum);
	   
		//String sname1 = request.getParameter("sname");//�õ�ѧ���ļ���
		//String sname = new String(sname1.getBytes("ISO-8859-1"),"utf-8");//�õ�ѡ����֪
	   
	   mvo.setRcontent(rcontent);//����
	   mvo.setRtime(DateConventer.strToTimestamp(DateConventer.dateToStr(new Date())));//�ظ�ʱ��
	   mvo.setStatus("�ѻظ�");//�ظ�״̬
	   this.teacherService.update(mvo);//�ظ�
	   return "disReplyMessageSubmit";
   }
   
   
  public String studentGradeInputx(){
		boolean result = this.teacherService.checkTime("��ʦ¼���ҵ��Ƴɼ�ʱ������");
		if(!result)
		  return "nostudentGradeInputx";  
		return "studentGradeInputx";
  }
   
   public String studentCourseGradeInputx(){
		boolean result = this.teacherService.checkTime("��ʦ¼��γ̳ɼ�ʱ������");
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
