package cn.edu.management.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.edu.management.dao.TeacherDAO;
import cn.edu.management.service.SuperService;
import cn.edu.management.service.TeacherService;
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

@Component("teacherService")
public class TeacherServiceImpl extends SuperService implements TeacherService {
	private TeacherDAO teacherDao;

	
	@Resource
	public void setTeacherDao(TeacherDAO teacherDao) {
		this.teacherDao = teacherDao;
	}
	
	
	
	/******************************************个人信息维护***********************************************/
	/*****查看个人信息*****/
	public Ems_Teacher_VO  displayPersonInformation(String idnum) {
		
		return this.teacherDao.displayPersonInformation(idnum);
	}

	/******************************************毕业设计管理***********************************************/
	/*****查看已添加毕业设计*****/
	public List<EMS_Graduation>  displayAlreadyAdd(String idnum){
		return this.teacherDao.displayAlreadyAdd(idnum);
	};	
		
	/**
	 * 取到所有毕业设计中编号最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutGraduate(){
		return this.teacherDao.getMaxIdNumAboutGraduate();
	};

	
	/**
	 * 取到所有毕业设计中编号最大的一个,以便于取到下一个
	 * @return
	 */
	public String getMaxIdNumAboutApplyModify(){
		return this.teacherDao.getMaxIdNumAboutApplyModify();
	};
	
	
	//找出相应的申请表记录
	public List<ApplyModifyLogVO>  displayAlreadyApplyStatus(String idnum){
		return this.teacherDao.displayAlreadyApplyStatus(idnum);
	};
	
	/**
	 * 点击添加申请按钮时
	 * 
	 * 在申请记录表查找是否有这个老师的记录且审核结果为“审核中”
	 */
	public ApplyModifyLogVO addModifyApplycheck(String applyUserId){
		return this.teacherDao.addModifyApplycheck(applyUserId);
	};
	
	/**
	 * 得到相就编号的毕业设计
	 * @param idnum
	 * @return
	 */
	public EMS_Graduation getModifyGraduationInfo(String idnum){
		return this.teacherDao.getModifyGraduationInfo(idnum);
	};
	

	/**
	 * 查询毕业设计学生信息
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearch(String teacherid){
		return this.teacherDao.studentInforSearch(teacherid);
	};
	/**
	 * 学生文件管理 
	 * @return
	 */
	public List<StudentGraduationWorksMange> studentFileManage(String teacherid){
		return this.teacherDao.studentFileManage(teacherid);
	};
	
	/**
	 * 多表操作
	 *ems_graduation
	 *ems_graduationgrade
	 *ems_student 
	 */
	public List<Object[]>  graStuGragrade(String teacherid){
		return this.teacherDao.graStuGragrade(teacherid);
	} 
	
	/**
	 * 多表操作
	 *ems_graduation
	 *ems_graduationgrade
	 *ems_student 
	 */
	public List<Object[]>  graStuGragrade(String teacherid,int nowPage){
		return this.teacherDao.graStuGragrade(teacherid,nowPage);
	} 
	//显示要审核的毕业设计题目
	public List<disAuditGraduationTitle>disAuditGraduationTitle(String tidnum){
	  return this.teacherDao.disAuditGraduationTitle(tidnum);
	};
	
	//得到最大编号从毕业设计记录表中
	public String getMaxIdNumAboutGraduateGrade(){
		return this.teacherDao.getMaxIdNumAboutGraduateGrade();
	};
	//得到审核记录
	public  List<disAuditGraduationResult> disAditInfor(String tidnum){
		return this.teacherDao.disAditInfor(tidnum);
	};
	/**
	 * 学生毕业设计成绩录入
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeInput(String teacherid){
		return this.teacherDao.studentGradeInput(teacherid);
	};
	
	/**
	 * 得到指定毕业设计记录表中的记录
	 * @param ggidnum
	 * @return
	 */
	public EMS_GraduateGrade getSpecifyGraduationG(String ggidnum){
		return this.teacherDao.getSpecifyGraduationG(ggidnum);
	};
	/**
	 * 学生毕业设计成绩修改
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeupdate(String teacherid){
		return this.teacherDao.studentGradeupdate(teacherid);
	};
	
	/**
	 * 显示已添加课程
	 * @param teacherid
	 * @return
	 */
	public List<showAlreadyAddCourse> displayAlreadyAddCourse(String teacherid){
		return this.teacherDao.displayAlreadyAddCourse(teacherid);
	};
	
	/**
	 * 得到课程表的最大号
	 */
	public String getMaxIdNumAboutCourse(){
		return this.teacherDao.getMaxIdNumAboutCourse();
		
	};
	
	/**
	 * 得到课程类型
	 */
    public List<ClassTypeVO> listClassType(){
    	return this.teacherDao.listClassType();
    };
    
    /**
     * 得到相应编号的课程
     */
    public EMS_Class getModifyCourseInfo(String cidnum){
    return this.teacherDao.getModifyCourseInfo(cidnum);
    };
    
    
    
    /**
	 * 查询选课学生信息
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearchCourse(String teacherid){
		return this.teacherDao.studentInforSearchCourse(teacherid);
	};
	
	/**
	 * 查询选课学生信息成绩

	 */
	public List<Object[]>  graStuGragradeCourse(String teacherid){
		return this.teacherDao.graStuGragradeCourse(teacherid);
	} 
	
	
	/**
	 * 学生课程成绩录入
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeInput(String teacherid){
		return this.teacherDao.studentCourseGradeInput(teacherid);
	};
	
	 /**
     * 得到相应编号的课程记录
     */
    public EMS_CourseRecord getSpecifyCourseRecord(String cidnum){
    return this.teacherDao.getSpecifyCourseRecord(cidnum);
    };
    
	/**
	 * 学生毕业设计成绩修改
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeupdate(String teacherid){
		return this.teacherDao.studentCourseGradeupdate(teacherid);
	};
	
	/**
	 * 显示已回复留言
	 */
	public List<ShowMessageVO> disAlreadyReplyMessage(String tidnum,String status){
		return this.teacherDao.disAlreadyReplyMessage(tidnum, status);
	};
	
	/**
	 * 得到指定编号的留言信息
	 */
	public MessageVO getMessageVO(String idnum){
		return this.teacherDao.getMessageVO(idnum);
	};	
	
	/**
	 * 得到毕业设计记录数
	 */
	public int getGrauationCounts(String teacherid) {
		// TODO Auto-generated method stub
		return this.teacherDao.getGrauationCounts(teacherid);
	}
	/*****查看所有毕业设计*****/
	public List<EMS_Graduation> displayAlreadyAddPage(String idnum,int pageNow) {
		// TODO Auto-generated method stub
		return this.teacherDao.displayAlreadyAddPage(idnum,pageNow);
	}
	/**
	 * 分页--查询毕业设计学生信息
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearchPage(String teacherid,int nowPage){
		return this.teacherDao.studentInforSearchPage(teacherid,nowPage);
	};
	
	/**
	 * 得到毕业设计记录数
	 */
	public int studentInforSearchCounts(String teacherid) {
		// TODO Auto-generated method stub
		return this.teacherDao.studentInforSearchCounts( teacherid);
	}
	
	/**
	 * 得到毕业设计记录数
	 */
	public int studentFileManageCounts(String teacherid) {
		// TODO Auto-generated method stub
		return this.teacherDao.studentFileManageCounts( teacherid);
	}
	/**
	 * 得到学生成绩查询记录数
	 * @return
	 */
		public int studentGradeSearchCounts(String teacherid){
			return this.teacherDao.studentGradeSearchCounts(teacherid);
		};
	
	/**
	 * 学生文件管理 -分页
	 * @return
	 */
	public List<StudentGraduationWorksMange> studentFileManagePage(String teacherid,int nowPage){
		return this.teacherDao.studentFileManagePage(teacherid, nowPage);
	};
	/**
	 * 查询选课学生信息成绩--分页

	 */
	public List<Object[]>  graStuGragradeCourse(String teacherid,int nowPage){
		return this.teacherDao.graStuGragradeCourse(teacherid, nowPage);
		
	};
	
	/**
	 * 得到学生成输入记录数
	 * @return
	 */
		public int studentGradeInputCounts(String teacherid){
			return this.teacherDao.studentGradeInputCounts(teacherid);
		};
		/**
		 * 学生毕业设计成绩录入--分页
		 * @return
		 */
		public List<GraduationStudentInfor> studentGradeInput(String teacherid,int nowPage){
			return this.teacherDao.studentGradeInput(teacherid, nowPage);
		};
		/**
		 * 得到学生成绩修改记录数
		 * @return
		 */
public int studentGradeupdateCounts(String teacherid){
	return this.teacherDao.studentGradeupdateCounts(teacherid);
};
/**
 * 学生毕业设计成绩修改-分页
 * @return
 */
public List<GraduationStudentInfor> studentGradeupdate(String teacherid,int nowPage){
	return this.teacherDao.studentGradeupdate(teacherid, nowPage);
};

/**
 * 显示已添加课程--分页
 * @param teacherid
 * @return
 */
public List<showAlreadyAddCourse> displayAlreadyAddCourse(String teacherid,int nowPage){
	return this.teacherDao.displayAlreadyAddCourse(teacherid, nowPage);
};
/**
 * 得到课程表记录数
 * @return
 */
	public int CourseCounts(String teacherid){
		return this.teacherDao.CourseCounts(teacherid);
	};
	
	/**
	 * 得到选 课学生信息记录数
	 * @return
	 */
public int studentInforSearchCourseCounts(String teacherid){
	return this.teacherDao.studentInforSearchCourseCounts(teacherid);
};

	    
	    /**
		 * 查询选课学生信息--分页
		 * @return
		 */
public List<GraduationStudentInfor> studentInforSearchCourse(String teacherid,int nowPage){
	return this.teacherDao.studentInforSearchCourse(teacherid, nowPage);
};

/**
 * 得到选 课学生成绩记录数
 * 
 * @return
 */
public int studentGradeSearchCourseCounts(String teacherid){
	return this.teacherDao.studentGradeSearchCourseCounts(teacherid);
};
	

/**
 * 查询录入课程成绩--分页
 * @return
 */
public List<GraduationStudentInfor> studentCourseGradeInput(String teacherid,int nowPage){
	return this.teacherDao.studentCourseGradeInput(teacherid, nowPage);
};

/**
 * 得到录入课程成绩记录数
 * @return
 */
public int studentCourseGradeInputCounts(String teacherid){
  return this.teacherDao.studentCourseGradeInputCounts(teacherid);
};	
	
/**
 * 查询修改课程成绩--分页
 * @return
 */
public List<GraduationStudentInfor> studentCourseGradeupdate(String teacherid,int nowPage){
	return this.teacherDao.studentCourseGradeupdate(teacherid, nowPage);
};

/**
 * 得到修改课程成绩记录数
 * @return
 */
public int studentCourseGradeupdateCounts(String teacherid){
	return this.teacherDao.studentCourseGradeupdateCounts(teacherid);
};	

/**
 * 查看已申请状态--分页
 * @return
 */
public List<ApplyModifyLogVO> displayAlreadyApplyStatus(String teacherid,int nowPage){
	return this.teacherDao.displayAlreadyApplyStatus(teacherid, nowPage);
};

/**
 * 得到查看已申请状态记录数
 * @return
 */
public int displayAlreadyApplyStatusCounts(String teacherid){
	return this.teacherDao.displayAlreadyApplyStatusCounts(teacherid);
};

/**
 * 查看已回复留言--分页
 * @return
 */
public List<ShowMessageVO> disAlreadyReplyMessage(String teacherid,String status,int nowPage){
	return this.teacherDao.disAlreadyReplyMessage(teacherid, status, nowPage);
	
};

/**
 * 得到已回复留言记录数
 * @return
 */
public int disAlreadyReplyMessageCounts(String teacherid,String status){
	return this.teacherDao.disAlreadyReplyMessageCounts(teacherid, status);
};

/**
 * 系统时间设置
 * @param status
 * @return
 */
public boolean checkTime(String status){
	return this.teacherDao.checkTime(status);
};

/**
 * 查询选课学生信息成绩--条件

 */
public List<Object[]>  graStuGragradeCourse(String teacherid,String nowPage,int nowPag){
	return this.teacherDao.graStuGragradeCourse(teacherid,nowPage,nowPag);
};

/**
 * 帐号测试
 * @return
 */
public boolean checkGname(String account){
	return this.teacherDao.checkGname(account);
};
}
	
