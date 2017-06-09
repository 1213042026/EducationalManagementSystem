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

/**
 * 
 * @author horse --学生对毕业设计进行选题
 */
public class EMS_GraduationB {
	
	
	
	private String idnum;// varchar2(20), --编号P K
	
	
	private String gname; // gname varchar2(100),      --毕业设计名称

	private String teacherId;// teacherId



	private String gyear; // gyear varchar2(10),     --学年

	private String flag;// varchar(20),                     --标志（学生申请插入student,老师插入teacher）
	private String  glevel ;//varchar(20),                   --容易，一般，困难  
	 

	private String grade;// grade varchar2(20),      --所属年级
	
	
	private String professionId; // professionId    --所属专业F K


	private String gcount; // gcount varchar2(10),         --可选人数


	private String rcount; //  rcount varchar2(10),        --剩于人数


	private String recordTime; // recordTime timestamp,    --录入时间


	private String remarks; // remark varchar2(300),   --备注


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


	public String getGyear() {
		return gyear;
	}


	public void setGyear(String gyear) {
		this.gyear = gyear;
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




	public String getRecordTime() {
		return recordTime;
	}


	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	public String getTeacherId() {
		return teacherId;
	}


	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	
	
	 
	 
	 
	 
	 

}
