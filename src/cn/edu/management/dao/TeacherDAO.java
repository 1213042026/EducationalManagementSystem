package cn.edu.management.dao;

import java.util.List;

import cn.edu.management.vo.showVO.GraduationStudentInfor;
import cn.edu.management.vo.showVO.ShowMessageVO;
import cn.edu.management.vo.showVO.StudentGraduationWorksMange;
import cn.edu.management.vo.showVO.disAuditGraduationResult;
import cn.edu.management.vo.showVO.disAuditGraduationTitle;
import cn.edu.management.vo.showVO.showAlreadyAddCourse;
import cn.edu.management.vo.voImpl.ApplyModifyLogVO;
import cn.edu.management.vo.voImpl.ClassTypeVO;
import cn.edu.management.vo.voImpl.EMS_Class;
import cn.edu.management.vo.voImpl.EMS_CourseRecord;
import cn.edu.management.vo.voImpl.EMS_GraduateGrade;
import cn.edu.management.vo.voImpl.EMS_Graduation;
import cn.edu.management.vo.voImpl.Ems_Teacher_VO;
import cn.edu.management.vo.voImpl.MessageVO;

public interface TeacherDAO extends DAO {
	/****************************************** 个人信息维护 ***********************************************/
	/***** 查看个人信息 *****/
	public Ems_Teacher_VO displayPersonInformation(String idnum);

	/****************************************** 毕业设计管理 ***********************************************/
	/***** 查看已添加毕业设计 *****/
	public List<EMS_Graduation> displayAlreadyAdd(String idnum);

	/**
	 * 取到所有毕业设计中编号最大的一个,以便于取到下一个
	 * 
	 * @return
	 */
	public String getMaxIdNumAboutGraduate();

	/**
	 * 取到所有毕业设计中编号最大的一个,以便于取到下一个
	 * 
	 * @return
	 */
	public String getMaxIdNumAboutApplyModify();

	// 找出相应的申请表记录
	public List<ApplyModifyLogVO> displayAlreadyApplyStatus(String idnum);

	/**
	 * 点击添加申请按钮时
	 * 
	 * 在申请记录表查找是否有这个老师的记录且审核结果为“审核中”
	 */
	public ApplyModifyLogVO addModifyApplycheck(String applyUserId);

	/**
	 * 得到相就编号的毕业设计
	 * 
	 * @param idnum
	 * @return
	 */
	public EMS_Graduation getModifyGraduationInfo(String idnum);

	/**
	 * 查询毕业设计学生信息
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearch(String teacherid);

	/**
	 * 学生文件管理
	 * 
	 * @return
	 */
	public List<StudentGraduationWorksMange> studentFileManage(String teacherid);

	/**
	 * 多表操作 ems_graduation ems_graduationgrade ems_student
	 */
	public List<Object[]> graStuGragrade(String teacherid);

	// 显示要审核的毕业设计题目
	public List<disAuditGraduationTitle> disAuditGraduationTitle(String tidnum);

	// 得到最大编号从毕业设计记录表中
	public String getMaxIdNumAboutGraduateGrade();

	// 得到审核记录
	public List<disAuditGraduationResult> disAditInfor(String tidnum);

	/**
	 * 学生毕业设计成绩录入
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeInput(String teacherid);

	/**
	 * 得到指定毕业设计记录表中的记录
	 * 
	 * @param ggidnum
	 * @return
	 */
	public EMS_GraduateGrade getSpecifyGraduationG(String ggidnum);

	/**
	 * 学生毕业设计成绩录入
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeupdate(String teacherid);

	/**
	 * 显示已添加课程
	 * 
	 * @param teacherid
	 * @return
	 */
	public List<showAlreadyAddCourse> displayAlreadyAddCourse(String teacherid);

	/**
	 * 得到课程表的最大号
	 */
	public String getMaxIdNumAboutCourse();

	/**
	 * 得到课程类型
	 */
	public List<ClassTypeVO> listClassType();

	/**
	 * 得到相应编号的课程
	 */
	public EMS_Class getModifyCourseInfo(String cidnum);

	/**
	 * 查询选课学生信息
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearchCourse(
			String teacherid);

	/**
	 * 查询选课学生信息成绩
	 */
	public List<Object[]> graStuGragradeCourse(String teacherid);

	/**
	 * 学生毕业设计成绩录入
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeInput(String teacherid);

	/**
	 * 得到相应编号的课程记录
	 */
	public EMS_CourseRecord getSpecifyCourseRecord(String cidnum);

	/**
	 * 学生课程成绩修改
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeupdate(
			String teacherid);

	/**
	 * 显示已回复留言
	 */
	public List<ShowMessageVO> disAlreadyReplyMessage(String tidnum,
			String status);

	/**
	 * 得到指定编号的留言信息
	 */
	public MessageVO getMessageVO(String idnum);

	/**
	 * 得到毕业设计记录数
	 */
	public int getGrauationCounts(String teacherid);

	/***** 查看所有毕业设计 *****/
	public List<EMS_Graduation> displayAlreadyAddPage(String idnum, int pageNow);

	/**
	 * 分页--查询毕业设计学生信息
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearchPage(
			String teacherid, int nowPage);

	/**
	 * 得到毕业设计表记录数
	 * 
	 * @return
	 */
	public int studentInforSearchCounts(String teacherid);

	/**
	 * 得到毕业设计表记录数
	 * 
	 * @return
	 */
	public int studentFileManageCounts(String teacherid);

	/**
	 * 学生文件管理 -分页
	 * 
	 * @return
	 */
	public List<StudentGraduationWorksMange> studentFileManagePage(
			String teacherid, int nowPage);

	/**
	 * 得到学生成绩查询记录数
	 * 
	 * @return
	 */
	public int studentGradeSearchCounts(String teacherid);

	/**
	 * 查询选课学生信息成绩--分页
	 */
	public List<Object[]> graStuGragradeCourse(String teacherid, int nowPage);

	/**
	 * 多表操作 ems_graduation ems_graduationgrade ems_student
	 */
	public List<Object[]> graStuGragrade(String teacherid, int nowPage);

	/**
	 * 得到学生成输入记录数
	 * 
	 * @return
	 */
	public int studentGradeInputCounts(String teacherid);

	/**
	 * 学生毕业设计成绩录入--分页
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeInput(String teacherid,
			int nowPage);

	/**
	 * 得到学生成绩修改记录数
	 * 
	 * @return
	 */
	public int studentGradeupdateCounts(String teacherid);

	/**
	 * 学生毕业设计成绩修改-分页
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeupdate(String teacherid,
			int nowPage);

	/**
	 * 显示已添加课程--分页
	 * 
	 * @param teacherid
	 * @return
	 */
	public List<showAlreadyAddCourse> displayAlreadyAddCourse(String teacherid,
			int nowPage);

	/**
	 * 得到课程表记录数
	 * 
	 * @return
	 */
	public int CourseCounts(String teacherid);

	/**
	 * 得到选 课学生信息记录数
	 * 
	 * @return
	 */
	public int studentInforSearchCourseCounts(String teacherid);

	/**
	 * 查询选课学生信息--分页
	 * 
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearchCourse(
			String teacherid, int nowPage);

	/**
	 * 得到选 课学生成绩记录数
	 * 
	 * @return
	 */
	public int studentGradeSearchCourseCounts(String teacherid);

	/**
	 * 查询录入课程成绩--分页
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeInput(String teacherid,int nowPage);

	/**
	 * 得到录入课程成绩记录数
	 * @return
	 */
	public int studentCourseGradeInputCounts(String teacherid);
	
	/**
	 * 查询修改课程成绩--分页
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeupdate(String teacherid,int nowPage);

	/**
	 * 得到修改课程成绩记录数
	 * @return
	 */
	public int studentCourseGradeupdateCounts(String teacherid);
	
	
	/**
	 * 查看已申请状态--分页
	 * @return
	 */
	public List<ApplyModifyLogVO> displayAlreadyApplyStatus(String teacherid,int nowPage);

	/**
	 * 得到查看已申请状态记录数
	 * @return
	 */	
	public int displayAlreadyApplyStatusCounts(String teacherid);

	/**
	 * 查看已回复留言--分页
	 * @return
	 */
	public List<ShowMessageVO> disAlreadyReplyMessage(String teacherid,String status,int nowPage);

	/**
	 * 得到已回复留言记录数
	 * @return
	 */
	public int disAlreadyReplyMessageCounts(String teacherid,String status);
	

	/**
	 * 系统时间设置
	 * @param status
	 * @return
	 */
	public boolean checkTime(String status);
	
	/**
	 * 查询选课学生信息成绩--条件

	 */
	public List<Object[]>  graStuGragradeCourse(String teacherid,String nowPage,int nowPag);
	/**
	 * 帐号测试
	 * @return
	 */
	public boolean checkGname(String account);
	
}
