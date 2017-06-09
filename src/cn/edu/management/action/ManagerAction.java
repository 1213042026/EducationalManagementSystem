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
	
	/*����ҳ���ȡ������ʾ*/
	private List<Ems_Manager_VO> managerList ;//����Ա
	private List<Ems_Teacher_VO> teacherList ;//��ʦ
	private List<StudentVO> studentList ;//ѧ��
	private List<ProfessionVO>   professionList;//רҵ
	private List<WorkerTypeVO>   workertypeList;//��Ʒ����
	private List<ClassTypeVO>   classtypeList;//�γ�����
	private List<showApplyModifyLogVO> applyModifyList;//������Ϣ��¼��
	private List<showTeacher_VO>  showTeacherList;//���������ʾ��ʦ
	
	
	
	/**
	 *  �ֲ�ˢ����֤
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
			String msg = "{result:false,desc:\"ʧ��\"}";
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(msg);
		}	   	
	}
	
	
	
	
	/**
	 *  ������֤����Ա����Ƿ�Ϸ�
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
			String msg = "{result:false,desc:\"ʧ��\"}";
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(msg);
		}	   	
	}
	
	
	/*****�鿴������Ϣ*****/
	public String displayPersonInformation() {

		System.out
				.println("=========TeacherAction==========displayPersonInformation===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		 //��request�еõ�session
		  HttpSession session = request.getSession(); 
		 String idnum = (String)session.getAttribute("manageridnum");/////////
		 System.out.println("�õ��ı�ţ�"+idnum);		 
         
		 Ems_Manager_VO vo = (Ems_Manager_VO) this.managerService.getModifyManagerInfo(idnum);
      
       
		request.setAttribute("managerVO", vo);
	
		return "PersonInformationQuery";
	}
	
	
	
	/**
	 * 
	 *�޸�����
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
			request.setAttribute("msg", "wrong");//���ھ����벻��ȷ
			return "prePasswordWorng";
		}else{

			//�����ݿ���ȡ�������ŵ�������Ϣ
			Ems_Manager_VO vo = (Ems_Manager_VO) this.managerService.getModifyManagerInfo(userId);
			vo.setPassword(newpwd);//����Ҫ�޸ĵ�������
			this.managerService.update(vo);//��������
			
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
			managerVO.setSex("Ů");
			break;
		case 2 :
			managerVO.setSex("��");
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
			managerVO.setSex("Ů");
			break;
		case 2 :
			managerVO.setSex("��");
			break;
		}
		managerVO.setIdcard(idcard);
		managerVO.setAddress(address);
		managerVO.setNation(nation);
		this.managerService.update( managerVO);
		return "updateManagerSuccess";
		
	}
	/**
	 * ������ӹ���Ա��ʱ��ȡ�����ݿ��¼�е����ID������Ա���ܽ����ֶ�����ID��
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
	 * �г�����Ա����Ϣ
	 * @return
	 */
	public String listManager(){
		System.out.println("=====ManagerAction=listManager==============");
		List<Ems_Manager_VO> list = this.managerService.listManager();
		
		for( Ems_Manager_VO vo : list ){  //system ����ʾ
			if( "system".equals( vo.getIdnum()) ){
				list.remove(vo);
				break;
			}
		}
		this.setManagerList( list );
		
		return "listManager";
	}
	
	/**
	 * ��ȡ���Ա�ɾ���Ĺ���Ա��Ϣ
	 * @return
	 */
	public String ListDeleteManager(){
		System.out.println("=====ManagerAction=ListDeleteManager==============");
		List<Ems_Manager_VO> list = this.managerService.listManager();
		for( Ems_Manager_VO vo : list ){  //system ����ʾ
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
	 * �г����п����޸ĵĹ���Ա����Ϣ
	 * @return
	 */
	public String listModifyManager(){
		System.out.println("=========ManagerAction==========listModifyManager===========");
		List<Ems_Manager_VO> list = this.managerService.listManager();
		for( Ems_Manager_VO vo : list ){  //system ����ʾ
			if( "system".equals( vo.getIdnum()) ){
				list.remove(vo);
				break;
			}
		}
		this.setManagerList( list );
		return "listModifyManager";
	}
	
	/**
	 * ȡ�������Ҫ�޸ĵĹ���Ա����Ϣ
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
	
	
	
	/***********************************��ʦ*****************************************/
	
	/**
	 * �鿴-�г���ʦ����Ϣ
	 * @return
	 */
	public String listTeacher(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("=====ManagerAction=listManager==============");
//////////////////////1/////	
	       int counts=this.managerService.getTeacherCounts();	//�õ����м�¼��	
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
//		System.out.println("���룺"+str);����������
		//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		return "listTeacher";
	}
	
	/**
	 * ������ӽ�ʦ��ʱ��ȡ�����ݿ��¼�е����ID������Ա���ܽ����ֶ�����ID��
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
	 * ��ӽ�ʦ
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
			teacherVO.setSex("Ů");
			break;
		case 2 :
			teacherVO.setSex("��");
			break;
		}
		//�õ����
		String professionid = request.getParameter("pro_name");		
		ProfessionVO proVO = new ProfessionVO();
	    proVO.setIdnum(professionid);
	    //����һ�������,������������� ������ֵ
	    teacherVO.setProfession(proVO) ;      //����רҵ���
		teacherVO.setIdcard(idcard);
		teacherVO.setAddress(address);
		teacherVO.setNation(nation);
		teacherVO.setScientific(scientific);
		teacherVO.setTitle(title);
		this.managerService.insert(teacherVO);
		return "addTeacherSuccess";
	}
	
	
	/**
	 * ��ȡ���Ա�ɾ���Ľ�ʦ��Ϣ
	 * @return
	 */
	public String ListDeleteTeacher(){
		
		System.out.println("=====ManagerAction=ListDeleteTeacher==============");
		HttpServletRequest request = ServletActionContext.getRequest();
//////////////////////1/////	
int counts=this.managerService.getTeacherCounts();	//�õ����м�¼��	
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
	 * ɾ����ʦ- ʵ�ְ�ť����
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
		
		////////////////////////////ɾ��һ����ʦʱ���������б�ҵ��Ƶ��ļ���Ϣ��Ҫ��ɾ��///////////////////////////////////////////////////
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/teacher/"
				+idnum);
		System.out.println("realpath:"+realpath);
		File teacherFile = new File(realpath);
		if(teacherFile.exists()){//����ļ�����
			System.out.println("realpath1:"+realpath);
			boolean ff = FileManage.delete(realpath);//ɾ��Ŀ¼,Ϊʲô��־����Ϊ��
			System.out.println("��־��"+ff);
		
		}
	
		return "deleteTeacherSuccess";
	}
	
	
	
	/**
	 * �г����п����޸ĵĽ�ʦ����Ϣ
	 * @return
	 */
	public String listModifyTeacher(){
		System.out.println("=========ManagerAction==========listModifyManager===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		
//////////////////////1/////	
		int counts=this.managerService.getTeacherCounts();	//�õ����м�¼��	
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
//		for( Ems_Teacher_VO vo : list ){  //admin ���ܱ�ɾ��
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
	 * ȡ�������Ҫ�޸ĵĽ�ʦ����Ϣ
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
	 * update ����ʦ�޸İ�ťʵ��
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
			teacherVO.setSex("Ů");
			break;
		case 2 :
			teacherVO.setSex("��");
			break;
		}
		//�õ����
		String professionid = request.getParameter("pro_name");		
		ProfessionVO proVO = new ProfessionVO();
	    proVO.setIdnum(professionid);
	    //����һ�������,������������� ������ֵ
	    teacherVO.setProfession(proVO) ;      //����רҵ���
		teacherVO.setIdcard(idcard);
		teacherVO.setAddress(address);
		teacherVO.setNation(nation);
		teacherVO.setScientific(scientific);
		teacherVO.setTitle(title);
		this.managerService.update( teacherVO);
		return "updateTeacherSuccess";
		
	}
	
	
	
	
	
	/***********************************ѧ��****************************************/
	/**
	 * �鿴-�г�ѧ������Ϣ
	 * @return
	 */
	public String listStudent(){
		System.out.println("=====ManagerAction=listManager==============");
		HttpServletRequest request = ServletActionContext.getRequest();
		//////////////////////1/////	
	       int counts=this.managerService.listStudentCounts();	//�õ����м�¼��	
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
//		System.out.println("���룺"+str);//����������
		//////////////////////////////////3///////////////////////////////////
		request.setAttribute( "pageCount", page.getPageCount() );
		request.setAttribute( "pageNow", page.getPageNow() );
		request.setAttribute( "maxRowCount", page.getMaxRowCount() );
		request.setAttribute( "pageSize", page.getPageSize() );
		//////////////////////////////////////////////////////////////////////
		return "listStudent";
	}
	
	
	/**
	 * ��ȡ���Ա�ɾ����ѧ����Ϣ
	 * @return
	 */
	public String ListDeleteStudent(){
		System.out.println("=====ManagerAction=ListDeleteStudent==============");
		HttpServletRequest request = ServletActionContext.getRequest();
		//////////////////////1/////	
		//////////////////////1/////	
	       int counts=this.managerService.listStudentCounts();	//�õ����м�¼��	
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
//		for( StudentVO vo : list ){  //admin ���ܱ�ɾ��
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
	 * ɾ��ѧ��- ʵ�ְ�ť����
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
		
		////////////////////////////ɾ��һ��ѧ��ʱ�������������ҵ��Ƶ��ļ���Ϣ��Ҫ��ɾ��///////////////////////////////////////////////////
		String realpath = ServletActionContext.getServletContext().getRealPath("/fileManage/student/"
				+idnum);
		System.out.println("realpath:"+realpath);
		File teacherFile = new File(realpath);
		if(teacherFile.exists()){//����ļ�����
			System.out.println("realpath1:"+realpath);
			boolean ff = FileManage.delete(realpath);//ɾ��Ŀ¼,Ϊʲô��־����Ϊ��
			System.out.println("��־��"+ff);
		
		}
		
		return "deleteStudentSuccess";
	}
	
	
	
	
	/**
	 * �������ѧ����ʱ��ȡ�����ݿ��¼רҵ����
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
	 * ���ѧ��
	 * 
	 * @return
	 */
	public String insertStudent(){
		
		HttpServletRequest request = ServletActionContext.getRequest();		
		
		
		
		//ͨ��רҵ���Ƶõ����
//		String professionid = this.managerService.getProfession(pro_name);
		
//		System.out.println("Action�㣺"+professionid);
		
	
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
			studentVO.setSex("Ů");
			break;
		case 2 :
			studentVO.setSex("��");
			break;
		}
		studentVO.setIdcard(idcard);
		studentVO.setAddress(address);
		
		//�õ����
		String professionid = request.getParameter("pro_name");		
		ProfessionVO proVO = new ProfessionVO();
	    proVO.setIdnum(professionid);
	    //����һ�������,������������� ������ֵ
		studentVO.setProfession(proVO) ;      //����רҵ���
		
		studentVO.setNation(nation);
		studentVO.setEntranceDate(entranceDate);
		this.managerService.insert(studentVO);
		return "addStudentSuccess";
	
	}
	
	
	/**
	 * �г����п����޸ĵ�ѧ������Ϣ
	 * @return
	 */
	public String listModifyStudent(){
		System.out.println("=========ManagerAction==========listModifyStudent===========");
		HttpServletRequest request = ServletActionContext.getRequest();
		//////////////////////1/////	
		//////////////////////1/////	
	       int counts=this.managerService.listStudentCounts();	//�õ����м�¼��	
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
//		for( StudentVO vo : list ){  //admin ���ܱ�ɾ��
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
	 * ȡ�������Ҫ�޸ĵ�ѧ������Ϣ
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
		//�����ݿ��еõ� רҵ�б�����
		List<ProfessionVO> list = this.managerService.listProfession();
		this.setProfessionList(list);
		
		//��������ȡֵ 
		request.setAttribute("studentVO", vo);
		
		return "showStudentForModify";
	}
	
	/**
	 * update ��ѧ���޸İ�ťʵ��
	 * @return
	 */
	public String updateStudent(){
		System.out.println("====manageraction====updateStudent=======");
	HttpServletRequest request = ServletActionContext.getRequest();		
		
		
		
		//ͨ��רҵ���Ƶõ����
//		String professionid = this.managerService.getProfession(pro_name);
		
//		System.out.println("Action�㣺"+professionid);
		
	
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
			studentVO.setSex("Ů");
			break;
		case 2 :
			studentVO.setSex("��");
			break;
		}
		studentVO.setIdcard(idcard);
		studentVO.setAddress(address);
		
		//ͨ��רҵ���Ƶõ����
		String professionid = request.getParameter("pro_name");		
		ProfessionVO proVO = new ProfessionVO();
	    proVO.setIdnum(professionid);
		studentVO.setProfession(proVO) ;      //����γ̱��
		
		studentVO.setNation(nation);
		studentVO.setEntranceDate(entranceDate);
		
		
		this.managerService.update( studentVO);
		return "updateStudentSuccess";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/***************************************ϵͳ����****************************************/
	
	/**************��Ʒ����*****************/
	/**
	 * ��ȡ���Ա�ɾ������Ʒ������Ϣ
	 * @return
	 */
	public String WorksTypeManage(){
		System.out.println("=====ManagerAction=WorksTypeManage==============");
		List<WorkerTypeVO> list = this.managerService.listWorkertype();	
		
//		for( WorkerTypeVO vo : list ){  //admin ���ܱ�ɾ��
//			if( "0000".equals( vo.getIdnum()) ){
//				list.remove(vo);
//				break;
//			}
//		}
		
		this.setWorkertypeList( list );
		
//		ProfessionVO test = professionList.get(0);
//		String str = test.getIdnum();
//		System.out.println("רҵ�ţ�"+str);//����������
		return "WorkstypeManage";
	}
	
	/**
	 * 
	 * ɾ����Ʒ����- ʵ�ְ�ť����
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
	 * �����Ʒ����
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
	
	
	
	

	/**************�γ�����*****************/
	/**
	 * ��ȡ���Ա�ɾ���Ŀγ�������Ϣ
	 * @return
	 */
	public String ClassTypeManage(){
		System.out.println("=====ManagerAction=ClassTypeManage==============");
		List<ClassTypeVO> list = this.managerService.listClasstype();	
		
		
		
		this.setClasstypeList( list );
		
//		ProfessionVO test = professionList.get(0);
//		String str = test.getIdnum();
//		System.out.println("רҵ�ţ�"+str);//����������
		return "ClasstypeManage";
	}
	
	/**
	 * 
	 * ɾ���γ�����- ʵ�ְ�ť����
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
	 * ��ӿγ�����
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
	
	
	
	
	
	/**************רҵ����*****************/
	
	/**
	 * ��ȡ���Ա�ɾ����רҵ��Ϣ
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
//		System.out.println("רҵ�ţ�"+str);//����������
		return "ProfessionManage";
	}
	
	/**
	 * 
	 * ɾ��רҵ- ʵ�ְ�ť����
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
	 * ���רҵ
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
	
	

	/**************��ʦ��ӿγ�ʱ������       ��ʱ������*****************/
	/**
	 * ��ȡ���ݿ�����ֹʱ����Ϣ
	 * @return
	 */
	public String teacherAddCourse(){
		System.out.println("=====ManagerAction=teacherAddCourse==============");
		HttpServletRequest request = ServletActionContext.getRequest();
//		//�õ�ѡ��ı��
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
		
		
		//Ĭ����ʾ����0001����Ϣ
		if(idnum==null || idnum.equals("")){
			idnum ="0001";
			}
		
		
		EMS_Systemset vo = this.managerService.disTeacherAddTime(idnum);	
		System.out.println("ʱ�����ã�"+vo.getTimestart().toString());
		
		Ems_systemset disvo = new Ems_systemset();
		disvo.setStart(DateConventer.timestampToStr(vo.getTimestart()));
		disvo.setEnd(DateConventer.timestampToStr(vo.getTimeend()));
		
				request.setAttribute("teacherAddCourse", disvo);
		return "teacherAddCourse";
	}
	
	/**
	 * update ���޸����ݿ�����ֹʱ����Ϣ
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

		//�������///////////////////////////////////////////////////////////////////////
		String name  = request.getParameter("cname");
		String cname = new String(name.getBytes("ISO-8859-1"),"utf-8");
		System.out.println("����cname:"+cname);
	///////////////////////////////////////////////////////////////////////////	
//		��������
//		if(idnum.equals("0001")){
//			cname = "��ʦ��ӿγ�ʱ������";
//		}else if(idnum.equals("0001")){
//			cname = "��ʦ��ӱ�ҵ���ʱ������";
//		}else if(idnum.equals("0001")){
//			cname = "��ʦ¼��γ̳ɼ�ʱ������";
//		}else if(idnum.equals("0001")){
//			cname = "��ʦ¼���ҵ��Ƴɼ�ʱ������";
//		}else if(idnum.equals("0001")){
//			cname = "ѧ��ѡ��ʱ������";
//		}else if(idnum.equals("0001")){
//			cname = "ѧ����ҵ���ѡ��ʱ������";
//		}
		String startTime = request.getParameter("start");
		String endTime = request.getParameter("end");
		
		//String name = request.getParameter("name");

		System.out.println("start:"+startTime);
		System.out.println("end:" + endTime);
	//	System.out.println("name:" + name);
		
		EMS_Systemset vo = new EMS_Systemset();
		
		//���ò���
		vo.setTimestart(DateConventer.strToTimestamp(startTime));
		vo.setTimeend(DateConventer.strToTimestamp(endTime));
		///////////////////////////////////////////////////////////////////////////////////////////////////
		vo.setIdnum(idnum);//01�Ǳ�׼�涨
		vo.setSyssetname(cname);//��ϵͳ��������
	
		this.managerService.update( vo);
		
		Ems_systemset disvo = new Ems_systemset();
		disvo.setStart(DateConventer.timestampToStr(vo.getTimestart()));
		disvo.setEnd(DateConventer.timestampToStr(vo.getTimeend()));
		request.setAttribute("teacherAddCourse", disvo);
		
		return "updateTeacherAddCourseSucc";
		
	}
	
	
	/**************��ʦ¼��ɼ�ʱ������*****************/
	/**
	 * ��ȡ���ݿ�����ֹʱ����Ϣ
	 * @return
	 */
	public String teacherInputGrade(){
		System.out.println("=====ManagerAction=teacherInputGrade==============");
		EMS_Systemset vo = this.managerService.disTeacherInputGrade();	
		System.out.println("��ʦ¼��ɼ�ʱ�����ã�"+vo.getTimestart().toString());
		
		Ems_systemset disvo = new Ems_systemset();		
		disvo.setStart(DateConventer.timestampToStr(vo.getTimestart()));
		disvo.setEnd(DateConventer.timestampToStr(vo.getTimeend()));
		
		HttpServletRequest request = ServletActionContext.getRequest();		
		request.setAttribute("teacherInputGrade", disvo);
		return "teacherInputGrade";
	}
	
	/**
	 * update ���޸����ݿ�����ֹʱ����Ϣ
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
		vo.setIdnum("02");//02�Ǳ�׼�涨
		
	
		this.managerService.update( vo);
		Ems_systemset disvo = new Ems_systemset();		
		disvo.setStart(DateConventer.timestampToStr(vo.getTimestart()));
		disvo.setEnd(DateConventer.timestampToStr(vo.getTimeend()));
		request.setAttribute("teacherInputGrade", disvo);
		
		return "updateTeacherInputGradeSucc";
		
	}
	
	
	
	/**************ѧ��ѡ��ʱ������*****************/
	/**
	 * ��ȡ���ݿ�����ֹʱ����Ϣ
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
	 * update ���޸����ݿ�����ֹʱ����Ϣ
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
		vo.setIdnum("03");//03�Ǳ�׼�涨
		
	
		this.managerService.update( vo);
		
		Ems_systemset disvo = new Ems_systemset();	
		disvo.setStart(DateConventer.timestampToStr(vo.getTimestart()));
		disvo.setEnd(DateConventer.timestampToStr(vo.getTimeend()));
		request.setAttribute("studentTakeCourse", disvo);
	
		return "updateStudentTakeCourseSucc";
		
	}
	
	
	/******************************����������**********************************************/
	/*******���ѧ��������Ϣ***********/
	public String auditStudentInfor() {
		List<ApplyModifyLogVO> applyModify = this.managerService.disAditInfor("�����","ems_student");
		
		List<showApplyModifyLogVO> disvo = VOconver.voConverGb(applyModify);
		this.setApplyModifyList(disvo);
		
		return "auditStudentInfor";
	}
	
	/*********�����ʦ������Ϣ*********/
	public String auditTeacherInfor() {
		List<ApplyModifyLogVO> applyModify = this.managerService.disAditInfor("�����","ems_teacher");
		
		List<showApplyModifyLogVO> disvo = VOconver.voConverGb(applyModify);
		this.setApplyModifyList(disvo);
		
		return "auditTeacherInfor";
	}
	/*******��ѯѧ����˼�¼***********/
	public String searchStudentInfor() {
		List<ApplyModifyLogVO> applyModify = this.managerService.disAditInfor("δͨ��","ems_student");
		List<ApplyModifyLogVO> applyModify1 = this.managerService.disAditInfor("��ͨ��","ems_student");
		applyModify.addAll(applyModify1);
		
		//����
		 Collections.sort(applyModify,new ApplyTeacherComparator());
		 
		List<showApplyModifyLogVO> disvo = VOconver.voConverGb(applyModify);
		
		this.setApplyModifyList(disvo);
		
		return "searchStudentInfor";
	}
	
	/*******��ѯ��ʦ��˼�¼***********/
	public String searchTeacherInfor() {
		List<ApplyModifyLogVO> applyModify = this.managerService.disAditInfor("δͨ��","ems_teacher");
		List<ApplyModifyLogVO> applyModify1 = this.managerService.disAditInfor("��ͨ��","ems_teacher");
		applyModify.addAll(applyModify1);
		
		//����
		 Collections.sort(applyModify,new ApplyTeacherComparator());
		 
		List<showApplyModifyLogVO> disvo = VOconver.voConverGb(applyModify);
		this.setApplyModifyList(disvo);
		
		return "searchTeacherInfor";
	}

	/*******���ѧ������ͨ����ťʵ��
	 * @throws UnsupportedEncodingException ***********/
	public String managerAuditStudentPass() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//������б��]
		 String applyid = request.getParameter("applyid");
		 System.out.println("������:"+applyid);
		
		String studentid = request.getParameter("studentid");
		String name_temp = request.getParameter("username_temp");
		//���ı��봦������
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
		//���״̬
		applyVO.setModifyResult("��ͨ��");
		Timestamp auditTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		//���ʱ�� 
		applyVO.setAuditDate(auditTime);
		//�����
		String auditMan = (String)session.getAttribute("manageridnum");
		System.out.println("�����:" +auditMan);
		//Ems_Manager_VO managerVO = new Ems_Manager_VO();
		//managerVO.setIdnum(auditMan);
		applyVO.setAuditMan(auditMan);
		
		 System.out.println("�û����"+applyVO.getApplyUserId());

		 this.managerService.update(studentVO);
		 this.managerService.update(applyVO);
	
	
		 return "auditSuccess";
		
	}
	/*******���ѧ�����ܲ�ͨ����ťʵ��
	 * @throws UnsupportedEncodingException ***********/
	public String managerAuditStudentNoPass() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//������б��]
		 String applyid = request.getParameter("applyid");
		 System.out.println("������:"+applyid);
		
		String studentid = request.getParameter("studentid");
		///String name_temp = request.getParameter("username_temp");
		//���ı��봦������
		//String cname = new String(name.getBytes("ISO-8859-1"),"utf-8");
		///name_temp = new String(name_temp.getBytes("ISO-8859-1"),"utf-8");
		//String idcard_temp = request.getParameter("idcard_temp");
		//��˵����
		String attitude = request.getParameter("attitude");
		attitude = new String(attitude.getBytes("ISO-8859-1"),"utf-8");
		
		Ems_Student_VO studentVO = this.managerService.getModifyStudentInfo(studentid);
		//studentVO.setIdnum(studentid);
		//studentVO.setName(name_temp);
	//	studentVO.setIdcard(idcard_temp);
		studentVO.setIdcrad_temp("");
		studentVO.setUsername_temp("");
		ApplyModifyLogVO applyVO = this.managerService.getAuditInfor(applyid);
		//���״̬
		applyVO.setModifyResult("δͨ��");
		Timestamp auditTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		//���ʱ�� 
		applyVO.setAuditDate(auditTime);
		//��˵����
		applyVO.setRemark(attitude);	
		//����ˣ����������
		String auditMan = (String)session.getAttribute("manageridnum");
		System.out.println("�����:" +auditMan);
	//	Ems_Manager_VO managerVO = new Ems_Manager_VO();
		//managerVO.setIdnum(auditMan);
		applyVO.setAuditMan(auditMan);
		
		 System.out.println("�û����"+applyVO.getApplyUserId());

		 this.managerService.update(studentVO);
		 this.managerService.update(applyVO);
	
	
		return "auditSuccess";
		
	}
	/**
	 * ѧ������˰�ť
	 * @return
	 */
   public String auditStudent(){
	   HttpServletRequest request = ServletActionContext.getRequest();
	   String studentid = request.getParameter("user");
	   String applyid = request.getParameter("idnum");
	   System.out.println("�������"+applyid);
	   System.out.println("ѧ��"+studentid);
	   Ems_Student_VO studentVO = this.managerService.getModifyStudentInfo(studentid);
	   request.setAttribute("studentvo", studentVO);
	   request.setAttribute("applyid", applyid);
	   return "auditStudentView";	   
   }
   
   /**
    * ��ʦ����˰�ť
    * @return
    */
  public String auditTeacher(){
	   
	  HttpServletRequest request = ServletActionContext.getRequest();
	   String teacherid = request.getParameter("user");
	   String applyid = request.getParameter("idnum");
	   System.out.println("�������"+applyid);
	   System.out.println("��ʦ���"+teacherid);
	   Ems_Teacher_VO teacherVO = this.managerService.getModifyTeacherInfo(teacherid);
	   request.setAttribute("teacherVO", teacherVO);
	   request.setAttribute("applyid", applyid);
	   return "auditTeacherView";	     
   }
   
  /*******��˽�ʦ����ͨ����ťʵ��
	 * @throws UnsupportedEncodingException ***********/
	public String managerAuditTeacherPass() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//������б��]
		 String applyid = request.getParameter("applyid");
		 System.out.println("������:"+applyid);
		
		String teacherid = request.getParameter("studentid");
		String name_temp = request.getParameter("username_temp");
		//���ı��봦������
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
		//���״̬
		applyVO.setModifyResult("��ͨ��");
		Timestamp auditTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		//���ʱ�� 
		applyVO.setAuditDate(auditTime);
		//�����
		String auditMan = (String)session.getAttribute("manageridnum");
		System.out.println("�����:" +auditMan);
	//	Ems_Manager_VO managerVO = new Ems_Manager_VO();
		//managerVO.setIdnum(auditMan);
		applyVO.setAuditMan(auditMan);
		
		 System.out.println("�û����"+applyVO.getApplyUserId());

		 this.managerService.update(teacherVO);
		 this.managerService.update(applyVO);
	
	
		 return "auditSuccess";
		
	}
	/*******��˽�ʦ���ܲ�ͨ����ťʵ��
	 * @throws UnsupportedEncodingException ***********/
	public String managerAuditTeacherNoPass() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		//������б��]
		 String applyid = request.getParameter("applyid");
		 System.out.println("������:"+applyid);
		
		String teacherid = request.getParameter("studentid");
		///String name_temp = request.getParameter("username_temp");
		//���ı��봦������
		//String cname = new String(name.getBytes("ISO-8859-1"),"utf-8");
		///name_temp = new String(name_temp.getBytes("ISO-8859-1"),"utf-8");
		//String idcard_temp = request.getParameter("idcard_temp");
		//��˵����
		String attitude = request.getParameter("attitude");
		attitude = new String(attitude.getBytes("ISO-8859-1"),"utf-8");
		
		Ems_Teacher_VO teacherVO = this.managerService.getModifyTeacherInfo(teacherid);
		//studentVO.setIdnum(studentid);
		//studentVO.setName(name_temp);
	//	studentVO.setIdcard(idcard_temp);
		teacherVO.setIdcard_temp("");
		teacherVO.setName_temp("");
		ApplyModifyLogVO applyVO = this.managerService.getAuditInfor(applyid);
		//���״̬
		applyVO.setModifyResult("δͨ��");
		Timestamp auditTime = DateConventer.strToTimestamp(DateConventer.dateToStr(new Date()));
		//���ʱ�� 
		applyVO.setAuditDate(auditTime);
		//��˵����
		applyVO.setRemark(attitude);	
		//����ˣ����������
		String auditMan = (String)session.getAttribute("manageridnum");
		System.out.println("�����:" +auditMan);
		//Ems_Manager_VO managerVO = new Ems_Manager_VO();
		//managerVO.setIdnum(auditMan);
		applyVO.setAuditMan(auditMan);
		
		 System.out.println("�û����"+applyVO.getApplyUserId());

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
