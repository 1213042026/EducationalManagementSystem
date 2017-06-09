package cn.edu.management.dao;

import java.util.List;

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

public interface StudentDAO extends DAO {
	
	
	/******************************************个人信息维护***********************************************/
	/*****查看个人信息*****/
	public Ems_Student_VO  displayPersonInformation(String idnum);
	
	
	/****************************************** 信息查询 ***********************************************/
	/***** 在校成绩查询 *****/
	public String findScoreByCourserecord(String classId,Ems_Student_VO vo);
	
	/**所有选课情况查询**/
	public List<EMS_Class> listMyAllCourse(String idnum, String grade,
			String profession,int pageNow);
	
	/** 得到学生选课的学年：**/
	public String selectCourseTime(EMS_Class classVO, Ems_Student_VO vo);
	
	/******************************************网上选课***********************************************/

	/**
	 * 判断现在是否是学生选课时间
	 */
	public boolean isAvailable();
	
	/*****选修课程*****/
		/**
		 * 查看可选修的课程
		 * @param idnum
		 * @param grade
		 * @param profession 
		 * @return
		 */
	public List<EMS_Class> listCourse(String idnum, String grade, String profession);
	
	/**
	 * 得到课程记录表的最大ID
	 * @return
	 */
	public String getMaxIdnumAboutCourseRecord();
	
	/**
	 * 查看我已经选修的课程
	 * @return
	 */
	public List<EMS_Class> listMyCourse(String idnum, String grade,String profession);

	/**
	 * 选择学生的一条选课记录
	 * @param classId
	 * @param studentId
	 * @return
	 */
	public EMS_CourseRecord findCRecord(String classId, String studentId);
	
	
	

	/******************************************毕业设计管理***********************************************/
	/*****查看所有毕业设计*****/
	public List<EMS_Graduation> selectGraduationProject(String idnum ,int pageNow);
	/**
	 * 拿到毕业设计表中所有的老师
	 * @return
	 */
	public List<Ems_Teacher_VO> getGraduateTeacher();

	/**
	 * 通过老师名字，专业检索毕业设计
	 * @param teacherIdnum
	 * @param glevel
	 * @return
	 */
	public List<EMS_Graduation> indexGraduationProject(String teacherIdnum,String glevel,int pageNow);
	
	public int indexGraduationProject(String teacherIdnum,String glevel);

//	/**
//	 * 拿到毕业设计表中所有的专业
//	 * @return
//	 */
//	public List<ProfessionVO> gerProfession();

//	/**
//	 * 选择毕业设计，将学生选择的毕业设计追加到毕业设计记录表中
//	 * @return
//	 */
//	public void addGraduateProject(String graduationId, String studentId);

	/**
	 * 用于添加毕业设计记录表的时候取到数据库记录中的最大ID，管理员不能进行手动插入ID号
	 * @return
	 */
	public String getMaxIdNumAboutGraduateGrade();

	/**
	 * 判断该学生是否已经选择了毕业设计
	 * @param studentId
	 * @return
	 */
	public boolean findStudentIdFromGraduateGrade(String studentId);

	/**
	 * 查看我的毕业设计
	 * @param studentId
	 * @return
	 */
	public EMS_GraduateGrade listMyGraduationProject(String studentId);

	/**
	 * 通过毕业设计编号读取该条记录
	 * @param gidnum
	 * @return
	 */
	public EMS_Graduation findGraduationById(String gidnum);

	
	/**
	 * 通过专业号查找这条专业记录
	 * @param proName
	 * @return
	 */
	public ProfessionVO findProfessionByProName(String proName);
	
	
	/**
	 * 查看是否已经申请自拟题目的毕业设计，如果是则不能再选择毕业设计
	 * @param userId
	 * @return
	 */
	public String isApply(String userId);
	

	/**
	 * 查看学生所选毕业设计的导师
	 * @return
	 */
	public Ems_Teacher_VO findTName(EMS_GraduateGrade gvo);
	
/********************************************留言板管理************************************************/
	/**
	 * 用于添加留言表的时候取到数据库记录中的最大ID
	 * @return
	 */
	public String getMaxIdNumAboutMessage();
	
	/**
	 * 显示学生留言信息
	 * @param idnum
	 * @return
	 */
	public List<MessageVO> showMessage(String idnum, String status);
/********************************************个人信息申请修改************************************************/
	/**
	 * 查看申请信息
	 * @return
	 */
	public List<ApplyModifyLogVO> displayAlreadyApplyStatus(String idnum);
	/**
	 * 查看毕业设计选题申请信息
	 * @return
	 */
	public List<ApplyModifyLogVO> displayAlreadyApplyStatusSelf(String idnum);


	/**
	 * 检查是否有请求的状态时“审核中”
	 * @param idnum
	 * @param string 
	 * @return
	 */
	public boolean checkApply(String idnum, String string);

	/**
	 * 通过课程号查找这条课程记录
	 * @param classId
	 * @return
	 */
	public EMS_Class findClassById(String classId);

	//判断现在是否是选择毕业设计时间
	public boolean isGraAvailable();

	/**
	 * 判断是否已经添加了自拟选题
	 * @return
	 */
	public boolean isApplying(String remark);

	/**
	 * 得到毕业设计记录数
	 */
	public int getGrauationCounts();

	/**
	 * 通过学年和学期查询课程
	 * @param xnName
	 * @param term
	 * @return
	 */
	public List<EMS_Class> indexCourse(String xnName, String term,String idnum);


	public int getCourseCounts(String idnum);


	public int getScoreCounts(String idnum);


	public boolean isGraMaAvailable();


	public int getGraCount();


	public String findTeacherById(String projectId);


	public boolean wordToHtml(String docfile, String htmlfile);


	public List<EMS_Class> listMyAllCourse1(String idnum, String grade,
			String profession, int pageNow);

	









	

	



	

	



	

	

}
