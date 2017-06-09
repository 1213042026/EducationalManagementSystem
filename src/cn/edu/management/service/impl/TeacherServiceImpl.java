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
	
	
	
	/******************************************������Ϣά��***********************************************/
	/*****�鿴������Ϣ*****/
	public Ems_Teacher_VO  displayPersonInformation(String idnum) {
		
		return this.teacherDao.displayPersonInformation(idnum);
	}

	/******************************************��ҵ��ƹ���***********************************************/
	/*****�鿴����ӱ�ҵ���*****/
	public List<EMS_Graduation>  displayAlreadyAdd(String idnum){
		return this.teacherDao.displayAlreadyAdd(idnum);
	};	
		
	/**
	 * ȡ�����б�ҵ����б������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutGraduate(){
		return this.teacherDao.getMaxIdNumAboutGraduate();
	};

	
	/**
	 * ȡ�����б�ҵ����б������һ��,�Ա���ȡ����һ��
	 * @return
	 */
	public String getMaxIdNumAboutApplyModify(){
		return this.teacherDao.getMaxIdNumAboutApplyModify();
	};
	
	
	//�ҳ���Ӧ��������¼
	public List<ApplyModifyLogVO>  displayAlreadyApplyStatus(String idnum){
		return this.teacherDao.displayAlreadyApplyStatus(idnum);
	};
	
	/**
	 * ���������밴ťʱ
	 * 
	 * �������¼������Ƿ��������ʦ�ļ�¼����˽��Ϊ������С�
	 */
	public ApplyModifyLogVO addModifyApplycheck(String applyUserId){
		return this.teacherDao.addModifyApplycheck(applyUserId);
	};
	
	/**
	 * �õ���ͱ�ŵı�ҵ���
	 * @param idnum
	 * @return
	 */
	public EMS_Graduation getModifyGraduationInfo(String idnum){
		return this.teacherDao.getModifyGraduationInfo(idnum);
	};
	

	/**
	 * ��ѯ��ҵ���ѧ����Ϣ
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearch(String teacherid){
		return this.teacherDao.studentInforSearch(teacherid);
	};
	/**
	 * ѧ���ļ����� 
	 * @return
	 */
	public List<StudentGraduationWorksMange> studentFileManage(String teacherid){
		return this.teacherDao.studentFileManage(teacherid);
	};
	
	/**
	 * ������
	 *ems_graduation
	 *ems_graduationgrade
	 *ems_student 
	 */
	public List<Object[]>  graStuGragrade(String teacherid){
		return this.teacherDao.graStuGragrade(teacherid);
	} 
	
	/**
	 * ������
	 *ems_graduation
	 *ems_graduationgrade
	 *ems_student 
	 */
	public List<Object[]>  graStuGragrade(String teacherid,int nowPage){
		return this.teacherDao.graStuGragrade(teacherid,nowPage);
	} 
	//��ʾҪ��˵ı�ҵ�����Ŀ
	public List<disAuditGraduationTitle>disAuditGraduationTitle(String tidnum){
	  return this.teacherDao.disAuditGraduationTitle(tidnum);
	};
	
	//�õ�����Ŵӱ�ҵ��Ƽ�¼����
	public String getMaxIdNumAboutGraduateGrade(){
		return this.teacherDao.getMaxIdNumAboutGraduateGrade();
	};
	//�õ���˼�¼
	public  List<disAuditGraduationResult> disAditInfor(String tidnum){
		return this.teacherDao.disAditInfor(tidnum);
	};
	/**
	 * ѧ����ҵ��Ƴɼ�¼��
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeInput(String teacherid){
		return this.teacherDao.studentGradeInput(teacherid);
	};
	
	/**
	 * �õ�ָ����ҵ��Ƽ�¼���еļ�¼
	 * @param ggidnum
	 * @return
	 */
	public EMS_GraduateGrade getSpecifyGraduationG(String ggidnum){
		return this.teacherDao.getSpecifyGraduationG(ggidnum);
	};
	/**
	 * ѧ����ҵ��Ƴɼ��޸�
	 * @return
	 */
	public List<GraduationStudentInfor> studentGradeupdate(String teacherid){
		return this.teacherDao.studentGradeupdate(teacherid);
	};
	
	/**
	 * ��ʾ����ӿγ�
	 * @param teacherid
	 * @return
	 */
	public List<showAlreadyAddCourse> displayAlreadyAddCourse(String teacherid){
		return this.teacherDao.displayAlreadyAddCourse(teacherid);
	};
	
	/**
	 * �õ��γ̱������
	 */
	public String getMaxIdNumAboutCourse(){
		return this.teacherDao.getMaxIdNumAboutCourse();
		
	};
	
	/**
	 * �õ��γ�����
	 */
    public List<ClassTypeVO> listClassType(){
    	return this.teacherDao.listClassType();
    };
    
    /**
     * �õ���Ӧ��ŵĿγ�
     */
    public EMS_Class getModifyCourseInfo(String cidnum){
    return this.teacherDao.getModifyCourseInfo(cidnum);
    };
    
    
    
    /**
	 * ��ѯѡ��ѧ����Ϣ
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearchCourse(String teacherid){
		return this.teacherDao.studentInforSearchCourse(teacherid);
	};
	
	/**
	 * ��ѯѡ��ѧ����Ϣ�ɼ�

	 */
	public List<Object[]>  graStuGragradeCourse(String teacherid){
		return this.teacherDao.graStuGragradeCourse(teacherid);
	} 
	
	
	/**
	 * ѧ���γ̳ɼ�¼��
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeInput(String teacherid){
		return this.teacherDao.studentCourseGradeInput(teacherid);
	};
	
	 /**
     * �õ���Ӧ��ŵĿγ̼�¼
     */
    public EMS_CourseRecord getSpecifyCourseRecord(String cidnum){
    return this.teacherDao.getSpecifyCourseRecord(cidnum);
    };
    
	/**
	 * ѧ����ҵ��Ƴɼ��޸�
	 * @return
	 */
	public List<GraduationStudentInfor> studentCourseGradeupdate(String teacherid){
		return this.teacherDao.studentCourseGradeupdate(teacherid);
	};
	
	/**
	 * ��ʾ�ѻظ�����
	 */
	public List<ShowMessageVO> disAlreadyReplyMessage(String tidnum,String status){
		return this.teacherDao.disAlreadyReplyMessage(tidnum, status);
	};
	
	/**
	 * �õ�ָ����ŵ�������Ϣ
	 */
	public MessageVO getMessageVO(String idnum){
		return this.teacherDao.getMessageVO(idnum);
	};	
	
	/**
	 * �õ���ҵ��Ƽ�¼��
	 */
	public int getGrauationCounts(String teacherid) {
		// TODO Auto-generated method stub
		return this.teacherDao.getGrauationCounts(teacherid);
	}
	/*****�鿴���б�ҵ���*****/
	public List<EMS_Graduation> displayAlreadyAddPage(String idnum,int pageNow) {
		// TODO Auto-generated method stub
		return this.teacherDao.displayAlreadyAddPage(idnum,pageNow);
	}
	/**
	 * ��ҳ--��ѯ��ҵ���ѧ����Ϣ
	 * @return
	 */
	public List<GraduationStudentInfor> studentInforSearchPage(String teacherid,int nowPage){
		return this.teacherDao.studentInforSearchPage(teacherid,nowPage);
	};
	
	/**
	 * �õ���ҵ��Ƽ�¼��
	 */
	public int studentInforSearchCounts(String teacherid) {
		// TODO Auto-generated method stub
		return this.teacherDao.studentInforSearchCounts( teacherid);
	}
	
	/**
	 * �õ���ҵ��Ƽ�¼��
	 */
	public int studentFileManageCounts(String teacherid) {
		// TODO Auto-generated method stub
		return this.teacherDao.studentFileManageCounts( teacherid);
	}
	/**
	 * �õ�ѧ���ɼ���ѯ��¼��
	 * @return
	 */
		public int studentGradeSearchCounts(String teacherid){
			return this.teacherDao.studentGradeSearchCounts(teacherid);
		};
	
	/**
	 * ѧ���ļ����� -��ҳ
	 * @return
	 */
	public List<StudentGraduationWorksMange> studentFileManagePage(String teacherid,int nowPage){
		return this.teacherDao.studentFileManagePage(teacherid, nowPage);
	};
	/**
	 * ��ѯѡ��ѧ����Ϣ�ɼ�--��ҳ

	 */
	public List<Object[]>  graStuGragradeCourse(String teacherid,int nowPage){
		return this.teacherDao.graStuGragradeCourse(teacherid, nowPage);
		
	};
	
	/**
	 * �õ�ѧ���������¼��
	 * @return
	 */
		public int studentGradeInputCounts(String teacherid){
			return this.teacherDao.studentGradeInputCounts(teacherid);
		};
		/**
		 * ѧ����ҵ��Ƴɼ�¼��--��ҳ
		 * @return
		 */
		public List<GraduationStudentInfor> studentGradeInput(String teacherid,int nowPage){
			return this.teacherDao.studentGradeInput(teacherid, nowPage);
		};
		/**
		 * �õ�ѧ���ɼ��޸ļ�¼��
		 * @return
		 */
public int studentGradeupdateCounts(String teacherid){
	return this.teacherDao.studentGradeupdateCounts(teacherid);
};
/**
 * ѧ����ҵ��Ƴɼ��޸�-��ҳ
 * @return
 */
public List<GraduationStudentInfor> studentGradeupdate(String teacherid,int nowPage){
	return this.teacherDao.studentGradeupdate(teacherid, nowPage);
};

/**
 * ��ʾ����ӿγ�--��ҳ
 * @param teacherid
 * @return
 */
public List<showAlreadyAddCourse> displayAlreadyAddCourse(String teacherid,int nowPage){
	return this.teacherDao.displayAlreadyAddCourse(teacherid, nowPage);
};
/**
 * �õ��γ̱��¼��
 * @return
 */
	public int CourseCounts(String teacherid){
		return this.teacherDao.CourseCounts(teacherid);
	};
	
	/**
	 * �õ�ѡ ��ѧ����Ϣ��¼��
	 * @return
	 */
public int studentInforSearchCourseCounts(String teacherid){
	return this.teacherDao.studentInforSearchCourseCounts(teacherid);
};

	    
	    /**
		 * ��ѯѡ��ѧ����Ϣ--��ҳ
		 * @return
		 */
public List<GraduationStudentInfor> studentInforSearchCourse(String teacherid,int nowPage){
	return this.teacherDao.studentInforSearchCourse(teacherid, nowPage);
};

/**
 * �õ�ѡ ��ѧ���ɼ���¼��
 * 
 * @return
 */
public int studentGradeSearchCourseCounts(String teacherid){
	return this.teacherDao.studentGradeSearchCourseCounts(teacherid);
};
	

/**
 * ��ѯ¼��γ̳ɼ�--��ҳ
 * @return
 */
public List<GraduationStudentInfor> studentCourseGradeInput(String teacherid,int nowPage){
	return this.teacherDao.studentCourseGradeInput(teacherid, nowPage);
};

/**
 * �õ�¼��γ̳ɼ���¼��
 * @return
 */
public int studentCourseGradeInputCounts(String teacherid){
  return this.teacherDao.studentCourseGradeInputCounts(teacherid);
};	
	
/**
 * ��ѯ�޸Ŀγ̳ɼ�--��ҳ
 * @return
 */
public List<GraduationStudentInfor> studentCourseGradeupdate(String teacherid,int nowPage){
	return this.teacherDao.studentCourseGradeupdate(teacherid, nowPage);
};

/**
 * �õ��޸Ŀγ̳ɼ���¼��
 * @return
 */
public int studentCourseGradeupdateCounts(String teacherid){
	return this.teacherDao.studentCourseGradeupdateCounts(teacherid);
};	

/**
 * �鿴������״̬--��ҳ
 * @return
 */
public List<ApplyModifyLogVO> displayAlreadyApplyStatus(String teacherid,int nowPage){
	return this.teacherDao.displayAlreadyApplyStatus(teacherid, nowPage);
};

/**
 * �õ��鿴������״̬��¼��
 * @return
 */
public int displayAlreadyApplyStatusCounts(String teacherid){
	return this.teacherDao.displayAlreadyApplyStatusCounts(teacherid);
};

/**
 * �鿴�ѻظ�����--��ҳ
 * @return
 */
public List<ShowMessageVO> disAlreadyReplyMessage(String teacherid,String status,int nowPage){
	return this.teacherDao.disAlreadyReplyMessage(teacherid, status, nowPage);
	
};

/**
 * �õ��ѻظ����Լ�¼��
 * @return
 */
public int disAlreadyReplyMessageCounts(String teacherid,String status){
	return this.teacherDao.disAlreadyReplyMessageCounts(teacherid, status);
};

/**
 * ϵͳʱ������
 * @param status
 * @return
 */
public boolean checkTime(String status){
	return this.teacherDao.checkTime(status);
};

/**
 * ��ѯѡ��ѧ����Ϣ�ɼ�--����

 */
public List<Object[]>  graStuGragradeCourse(String teacherid,String nowPage,int nowPag){
	return this.teacherDao.graStuGragradeCourse(teacherid,nowPage,nowPag);
};

/**
 * �ʺŲ���
 * @return
 */
public boolean checkGname(String account){
	return this.teacherDao.checkGname(account);
};
}
	
