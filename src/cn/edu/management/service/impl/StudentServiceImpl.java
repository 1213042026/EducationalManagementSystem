package cn.edu.management.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.edu.management.comm.Page;
import cn.edu.management.dao.StudentDAO;
import cn.edu.management.service.StudentService;
import cn.edu.management.service.SuperService;
import cn.edu.management.vo.showVO.ShowMessageVO;
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

@Component("studentService")
public class StudentServiceImpl extends SuperService implements StudentService {
	private StudentDAO studentDao;

	@Resource
	public void setStudentDao(StudentDAO studentDao) {
		this.studentDao = studentDao;
	}
	
	
	/******************************************个人信息维护***********************************************/
	/*****查看个人信息*****/
	public Ems_Student_VO  displayPersonInformation(String idnum) {
		
		return this.studentDao.displayPersonInformation(idnum);
	}
	
	/**
	 * 查看毕业设计选题申请信息
	 * @return
	 */
	public List<ApplyModifyLogVO> displayAlreadyApplyStatusSelf(String idnum){
		return this.studentDao.displayAlreadyApplyStatusSelf(idnum);
	};

	
	/****************************************** 信息查询 ***********************************************/
	/***** 在校成绩查询 *****/
	public String findScoreByCourserecord(String classId,Ems_Student_VO vo) {
		// TODO Auto-generated method stub
		return this.studentDao.findScoreByCourserecord(classId,vo);
	}

	/**所有选课情况查询**/
	public List<EMS_Class> listMyAllCourse(String idnum, String grade,
			String profession,int pageNow) {
		// TODO Auto-generated method stub
		return this.studentDao.listMyAllCourse( idnum, grade,profession,pageNow);
	}

	/** 得到学生选课的学年：**/
	public String selectCourseTime(EMS_Class classVO, Ems_Student_VO vo) {
		// TODO Auto-generated method stub
		return this.studentDao.selectCourseTime(classVO,vo);
	}
	
	
	/******************************************网上选课***********************************************/

	/**
	 * 判断现在是否是学生选课时间
	 */
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return this.studentDao.isAvailable();
	}
	
	/*****选修课程*****/
	/**
	 * 查看可选修的课程
	 * @param idnum
	 * @param grade
	 * @param profession 
	 * @return
	 */
	public List<EMS_Class> listCourse(String idnum, String grade,String profession) {
		// TODO Auto-generated method stub
		return this.studentDao.listCourse( idnum,  grade,profession);
	}

	/**
	 * 得到课程记录表的最大ID
	 * @return
	 */
	public String getMaxIdnumAboutCourseRecord() {
		// TODO Auto-generated method stub
		return this.studentDao.getMaxIdnumAboutCourseRecord();
	}

	/**
	 * 查看我已经选修的课程
	 * @return
	 */
	public List<EMS_Class> listMyCourse(String idnum, String grade,String profession) {
		// TODO Auto-generated method stub
		return this.studentDao.listMyCourse( idnum, grade,profession);
	}

	/**
	 * 选择学生的一条选课记录
	 * @param classId
	 * @param studentId
	 * @return
	 */
	public EMS_CourseRecord findCRecord(String classId, String studentId) {
		// TODO Auto-generated method stub
		return this.studentDao.findCRecord(classId, studentId);
	}
	
	
	
	
	/******************************************毕业设计管理***********************************************/
	/*****查看所有毕业设计*****/
	public List<EMS_Graduation> selectGraduationProject(String idnum,int pageNow) {
		// TODO Auto-generated method stub
		return this.studentDao.selectGraduationProject(idnum,pageNow);
	}
	
	/**
	 * 拿到毕业设计表中所有的老师
	 * @return
	 */
	public List<Ems_Teacher_VO> getGraduateTeacher()
	{
		return this.studentDao.getGraduateTeacher();
	}

	/**
	 * 通过老师名字，专业检索毕业设计
	 * @param teacherIdnum
	 * @param profIdnum
	 * @return
	 */
	public List<EMS_Graduation> indexGraduationProject(String teacherIdnum, String profIdnum,int pageNow) {
		// TODO Auto-generated method stub
		return this.studentDao.indexGraduationProject(teacherIdnum,profIdnum,pageNow);
	}
	
	public int indexGraduationProject(String teacherIdnum, String profIdnum) {
		// TODO Auto-generated method stub
		return this.studentDao.indexGraduationProject(teacherIdnum,profIdnum);
	}

//	/**
//	 * 拿到毕业设计表中所有的专业
//	 * @return
//	 */
//	public List<ProfessionVO> getProfession() {
//		// TODO Auto-generated method stub
//		return this.studentDao.gerProfession();
//	}

//	/**
//	 * 选择毕业设计，将学生选择的毕业设计追加到毕业设计记录表中
//	 * @return
//	 */
//	public void addGraduateProject(String graduationId, String studentId) {
//		// TODO Auto-generated method stub
//		this.studentDao.addGraduateProject(graduationId,studentId);
//	}

	/**
	 * 用于添加毕业设计记录表的时候取到数据库记录中的最大ID，管理员不能进行手动插入ID号
	 * @return
	 */
	public String getMaxIdNumAboutGraduateGrade() {
		// TODO Auto-generated method stub
		return this.studentDao.getMaxIdNumAboutGraduateGrade();
	}

	/**
	 * 判断该学生是否已经选择了毕业设计
	 * @param studentId
	 * @return
	 */
	public boolean findStudentIdFromGraduateGrade(String studentId) {
		// TODO Auto-generated method stub
		return this.studentDao.findStudentIdFromGraduateGrade(studentId);
	}

	/**
	 * 查看我的毕业设计
	 * @param studentId
	 * @return
	 */
	public EMS_GraduateGrade listMyGraduationProject(String studentId) {
		// TODO Auto-generated method stub
		return this.studentDao.listMyGraduationProject( studentId);
	}

	/**
	 * 通过毕业设计编号读取该条记录
	 * @param gidnum
	 * @return
	 */
	public EMS_Graduation findGraduationById(String gidnum) {
		// TODO Auto-generated method stub
		return this.studentDao.findGraduationById(gidnum);
	}

	/**
	 * 通过专业号查找这条专业记录
	 * @param proName
	 * @return
	 */
	public ProfessionVO findProfessionByProName(String proName) {
		// TODO Auto-generated method stub
		return this.studentDao.findProfessionByProName( proName);
	}


	/**
	 * 查看是否已经申请自拟题目的毕业设计，如果是则不能再选择毕业设计
	 * @param userId
	 * @return
	 */
	public String isApply(String userId) {
		// TODO Auto-generated method stub
		return this.studentDao.isApply(userId);
	}


	/**
	 * 查看学生所选毕业设计的导师
	 * @return
	 */
	public Ems_Teacher_VO findTName(EMS_GraduateGrade gvo) {
		// TODO Auto-generated method stub
		return this.studentDao.findTName(gvo);
	}

/********************************************留言板管理************************************************/
	/**
	 * 用于添加留言表的时候取到数据库记录中的最大ID
	 * @return
	 */
	public String getMaxIdNumAboutMessage() {
		// TODO Auto-generated method stub
		return this.studentDao.getMaxIdNumAboutMessage();
	}

	/**
	 * 显示学生留言信息
	 * @param idnum
	 * @return
	 */
	public List<MessageVO> showMessage(String idnum , String status) {
		// TODO Auto-generated method stub
		return this.studentDao.showMessage(idnum,status);
	}

	
/********************************************个人信息申请修改************************************************/
	/**
	 * 查看申请信息
	 * @return
	 */
	public List<ApplyModifyLogVO> displayAlreadyApplyStatus(String idnum) {
		// TODO Auto-generated method stub
		return this.studentDao.displayAlreadyApplyStatus(idnum);
	}


	/**
	 * 检查是否有请求的状态时“审核中”
	 * @param idnum
	 * @param string 
	 * @return
	 */
	public boolean checkApply(String idnum, String string) {
		// TODO Auto-generated method stub
		return this.studentDao.checkApply(idnum,string);
	}

	/**
	 * 通过课程号查找这条课程记录
	 * @param classId
	 * @return
	 */
	public EMS_Class findClassById(String classId) {
		// TODO Auto-generated method stub
		return this.studentDao.findClassById( classId);
	}

	//判断现在是否是选择毕业设计时间
	public boolean isGraAvailable() {
		// TODO Auto-generated method stub
		return this.studentDao.isGraAvailable();
	}

	public boolean isGraMaAvailable() {
		// TODO Auto-generated method stub
		return this.studentDao.isGraMaAvailable();
	}
	
	/**
	 * 判断是否已经添加了自拟选题
	 * @return
	 */
	public boolean isApplying(String remark) {
		// TODO Auto-generated method stub
		return this.studentDao.isApplying(remark);
	}

	/**
	 * 得到毕业设计记录数
	 */
	public int getGrauationCounts() {
		// TODO Auto-generated method stub
		return this.studentDao.getGrauationCounts();
	}

	/**
	 * 通过学年和学期查询课程
	 * @param xnName
	 * @param term
	 * @return
	 */
	public List<EMS_Class> indexCourse(String xnName, String term,String idnum) {
		// TODO Auto-generated method stub
		return this.studentDao.indexCourse(xnName, term ,idnum);
	}


	public int getCourseCounts(String idnum) {
		// TODO Auto-generated method stub
		return this.studentDao.getCourseCounts(idnum);
	}


	public int getScoreCounts(String idnum) {
		// TODO Auto-generated method stub
		return this.studentDao.getScoreCounts(idnum);
	}


	public int getGraCount() {
		// TODO Auto-generated method stub
		return this.studentDao.getGraCount();
	}


	public String findTeacherById(String projectId) {
		// TODO Auto-generated method stub
		return this.studentDao.findTeacherById(projectId);
	}


	public boolean wordToHtml(String docfile, String htmlfile) {
		// TODO Auto-generated method stub
		return this.studentDao.wordToHtml(docfile, htmlfile);
	}


	public List<EMS_Class> listMyAllCourse1(String idnum, String grade,
			String profession, int pageNow) {
		// TODO Auto-generated method stub
		return this.studentDao.listMyAllCourse1(idnum, grade, profession, pageNow);
	}


	



	

	

	
	
	

	

	
	
	



	
}
