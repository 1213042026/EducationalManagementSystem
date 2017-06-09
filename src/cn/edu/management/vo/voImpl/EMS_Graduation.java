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
 * @author by --��ҵ��Ʊ�
 */
@Entity
@Table(name = "ems_graduation")
public class EMS_Graduation implements VO{
	
	
	@Id
	@Column(length = 20)
	@AccessType(value = "property")
	private String idnum;// varchar2(20), --���P K
	
	@Column
	private String gname; // gname varchar2(100),      --��ҵ�������

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacherId", updatable = true, insertable = true)
	
	private Ems_Teacher_VO teacherId; // teacherId--��ʦ��� F K


	
	@Column
	private String gcount; // gcount varchar2(10),         --��ѡ����

	@Column
	private String rcount; //  rcount varchar2(10),        --ʣ������

	@Column
	private Timestamp recordTime; // recordTime timestamp,    --¼��ʱ��

	@Column
	private String flag; //  --��־��ѧ���������student,��ʦ����teacher��

	@Column
	private String glevel; // --���ף�һ�㣬����  
	
	@Column
	private String remark; // remark varchar2(300),   --��ע
	              
	
	
	
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
