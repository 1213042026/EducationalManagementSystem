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
 * @author by --毕业设计表
 */
@Entity
@Table(name = "ems_graduation")
public class EMS_Graduation implements VO{
	
	
	@Id
	@Column(length = 20)
	@AccessType(value = "property")
	private String idnum;// varchar2(20), --编号P K
	
	@Column
	private String gname; // gname varchar2(100),      --毕业设计名称

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacherId", updatable = true, insertable = true)
	
	private Ems_Teacher_VO teacherId; // teacherId--教师编号 F K


	
	@Column
	private String gcount; // gcount varchar2(10),         --可选人数

	@Column
	private String rcount; //  rcount varchar2(10),        --剩于人数

	@Column
	private Timestamp recordTime; // recordTime timestamp,    --录入时间

	@Column
	private String flag; //  --标志（学生申请插入student,老师插入teacher）

	@Column
	private String glevel; // --容易，一般，困难  
	
	@Column
	private String remark; // remark varchar2(300),   --备注
	              
	
	
	
	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public Ems_Teacher_VO getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Ems_Teacher_VO teacherId) {
		this.teacherId = teacherId;
	}

	public String getGcount() {
		return gcount;
	}

	public void setGcount(String gcount) {
		this.gcount = gcount;
	}

	public String getRcount() {
		return rcount;
	}

	public void setRcount(String rcount) {
		this.rcount = rcount;
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getGlevel() {
		return glevel;
	}

	public void setGlevel(String glevel) {
		this.glevel = glevel;
	}	 
	 
	 
	 
	 
	 

}
