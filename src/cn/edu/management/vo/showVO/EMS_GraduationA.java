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
 * @author by --��ʦ�鿴��ѡ��ҵ���
 */
public class EMS_GraduationA {
	
	
	
	private String idnum;// varchar2(20), --���P K
	
	
	private String gname; // gname varchar2(100),      --��ҵ�������




	private String gyear; // gyear varchar2(10),     --ѧ��




	private String grade;// grade varchar2(20),      --�����꼶
	
	
	private String flag;// varchar(20),                     --��־��ѧ���������student,��ʦ����teacher��
	private String  glevel ;//varchar(20),                   --���ף�һ�㣬����  
	 


	private String gcount; // gcount varchar2(10),         --��ѡ����


	private String rcount; //  rcount varchar2(10),        --ʣ������


	private String recordTime; // recordTime timestamp,    --¼��ʱ��


	private String remarks; // remark varchar2(300),   --��ע


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




	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
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

	
	
	
	
	
	 
	 
	 
	 
	 

}
