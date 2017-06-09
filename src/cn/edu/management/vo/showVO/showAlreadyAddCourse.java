package cn.edu.management.vo.showVO;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import cn.edu.management.vo.VO;

public class showAlreadyAddCourse implements VO {

	private String classId ; // varchar2(20), --课程编号ＰＫ
    private String className ; //varchar2(100), --课程名称
	private String classType ;//课程类型名
	private String teacherId ; //教师姓名    
    private String term ; // varchar2(10),  --学期	
    private String grade ; // varchar2(20),  --所属年级    
	private String  professionId ;//专业名称	
	/**
	 * 有时候从数据库中读出来，放入Action中后,在界面读不出来，可能是因为命名问题．
	 */
    private String  kkCount ;//varchar2(10),                  --可选人数
    private String ssCount;// varchar2(10),                  --剩于人数    
    private String recordTime ; //timestamp,  --录入课程时间    
    private String remark;
    
    
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getProfessionId() {
		return professionId;
	}
	public void setProfessionId(String professionId) {
		this.professionId = professionId;
	}

	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getKkCount() {
		return kkCount;
	}
	public void setKkCount(String kkCount) {
		this.kkCount = kkCount;
	}
	public String getSsCount() {
		return ssCount;
	}
	public void setSsCount(String ssCount) {
		this.ssCount = ssCount;
	}
    
    
    
  
    
//    classId varchar2(20) primary key ,    --课程编号ＰＫ
//    className varchar2(100),              --课程名称
//    classType varchar2(20),               --课程类型ＦＫ
//    teacherId varchar2(20),               --教师编号F K    
//    term varchar2(10),                    --学期
//    grade varchar2(20),                   --所属年级
  //  professionId varchar2(20),            --所属专业F K(当类型为公共必修课，注意一下)
    //kCount varchar2(10),                  --可选人数
   // sCount varchar2(10),                  --剩于人数
   // recordTime timestamp,                 --录入课程时间
   // remark  varchar2(300),                --备注
  
    
    
    //-------------all getter and setter --------------------------------------------

    
	
}
