package cn.edu.management.vo.voImpl;

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

/**
 * 
 * @author tq
 * 
 *
 */
@Entity
@Table(name="ems_class")
public class EMS_Class implements VO {
	@Id
	@Column(length = 20)
	@AccessType(value = "property")	
	private String classId ; // varchar2(20), --课程编号ＰＫ
	
	@Column
    private String className ; //varchar2(100), --课程名称
	
	
//    private String classType ; //varchar2(20),  --课程类型ＦＫ
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name = "classType" ,updatable=true , insertable=true)
	private ClassTypeVO classType ;
	
//    private String teacherId ; // varchar2(20),  --教师编号fk
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name = "teacherId" ,updatable=true , insertable=true)
	private Ems_Teacher_VO teacherId ;

    
	@Column
    private String term ; // varchar2(10),  --学期
	
	@Column
    private String grade ; // varchar2(20),  --所属年级
    
    
//    private String professionId ; //varchar2(20), --所属专业fk
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name = "professionId" ,updatable=true , insertable=true)
	private ProfessionVO professionId ;
	
	 @Column
     private String  kCount ;//varchar2(10),                  --可选人数
	 
	 @Column
     private String sCount;// varchar2(10),                  --剩于人数
    
    @Column
    private Timestamp recordTime ; //timestamp,  --录入课程时间
    
    @Column
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

	public ClassTypeVO getClassType() {
		return classType;
	}

	public void setClassType(ClassTypeVO classType) {
		this.classType = classType;
	}

	public Ems_Teacher_VO getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Ems_Teacher_VO teacherId) {
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

	public ProfessionVO getProfessionId() {
		return professionId;
	}

	public void setProfessionId(ProfessionVO professionId) {
		this.professionId = professionId;
	}

	public String getkCount() {
		return kCount;
	}

	public void setkCount(String kCount) {
		this.kCount = kCount;
	}

	public String getsCount() {
		return sCount;
	}

	public void setsCount(String sCount) {
		this.sCount = sCount;
	}

	public Timestamp getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Timestamp recordTime) {
		this.recordTime = recordTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
