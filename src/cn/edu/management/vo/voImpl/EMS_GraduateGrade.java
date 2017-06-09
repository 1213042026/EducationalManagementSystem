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
 * @author by --毕业业设计选择记录表
 */
@Entity
@Table(name = "ems_graduateGrade")
public class EMS_GraduateGrade implements VO {
	@Id
	@Column(length = 20)
	@AccessType(value = "property")
	private String idnum;// varchar2(20), --编号P K

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gidnum", updatable = true, insertable = true)
	private EMS_Graduation gidnum; // 毕业设计编号F K

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "studentId", updatable = true, insertable = true)
	private Ems_Student_VO studentId; // --学生编号　F K

	@Column
	private String grade; // varchar2(3), --成绩

	@Column
	Timestamp gxtime;//选题时间
	
	@Column
	private String remark; // varchar2(500), --备注

	
		
	
	
	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public EMS_Graduation getGidnum() {
		return gidnum;
	}

	public void setGidnum(EMS_Graduation gidnum) {
		this.gidnum = gidnum;
	}

	public Ems_Student_VO getStudentId() {
		return studentId;
	}

	public void setStudentId(Ems_Student_VO studentId) {
		this.studentId = studentId;
	}


	public Timestamp getGxtime() {
		return gxtime;
	}

	public void setGxtime(Timestamp gxtime) {
		this.gxtime = gxtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}


	
	
	

}
