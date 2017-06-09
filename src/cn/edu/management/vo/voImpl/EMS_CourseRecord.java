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
 * 选课记录表
 * @author 
 * 
 *
 */
@Entity
@Table(name="ems_courserecord")
public class EMS_CourseRecord implements VO {
	@Id
	@Column(length = 20)
	@AccessType(value = "property")	
	private String idnum ; //idnum varchar2(20) primary key  ,      --编号P K
	

//    private String studentId ; //varchar2(20), --学号 F K
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name = "studentId" ,updatable=true , insertable=true)
	private Ems_Student_VO studentId ;
	
//    private String classId ; //varchar2(20),  --课程编号ＦＫ
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name = "classId" ,updatable=true , insertable=true)
	private EMS_Class classId ;
	
	@Column
    private String grade;   //varchar2(3)       --成绩
	
	@Column
    private Timestamp chooseTime ; //timestamp,  --选课时间
    
	@Column
    private String remarks;      //varchar2(300)  -- 备注 

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public Ems_Student_VO getStudentId() {
		return studentId;
	}

	public void setStudentId(Ems_Student_VO studentId) {
		this.studentId = studentId;
	}

	public EMS_Class getClassId() {
		return classId;
	}

	public void setClassId(EMS_Class classId) {
		this.classId = classId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Timestamp getChooseTime() {
		return chooseTime;
	}

	public void setChooseTime(Timestamp chooseTime) {
		this.chooseTime = chooseTime;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
    
  
    
//    studentId varchar2(20),             --学号 F K
//    classId varchar2(20),               --课程编号 F K
//    grade  varchar2(3),                 --成绩   
//    chooseTime timestamp,               --选课时间
//    remarks varchar2(300),             -- 备注 
    
    //-------------all getter and setter --------------------------------------------
	
}
