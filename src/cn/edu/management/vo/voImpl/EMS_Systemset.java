package cn.edu.management.vo.voImpl;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import cn.edu.management.vo.VO;

/**
 * 
 * @author tq
 *--�γ����ͱ�  ����
 */
@Entity
@Table(name="EMS_SYSTEMSET")
public class EMS_Systemset implements VO {
	@Id
	@Column(length = 20)
	@AccessType(value = "property")	
	private String idnum ;//varchar2(20),  --���pk
	
	@Column
    private String syssetname; //syssetname varchar2(100), --ϵͳ������Ŀ����
	
	@Column	
	private Timestamp timestart;// timestamp,   --��Ŀ��ʼʱ��
	
	@Column
	private Timestamp timeend;// timestamp,  --��Ŀ��ֹʱ��
	
	@Column
    private String remarks ; // varchar2(500),  --��ע
    
    
    //----all getter and setter --------------------------------------------------
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public String getSyssetname() {
		return syssetname;
	}
	public void setSyssetname(String syssetname) {
		this.syssetname = syssetname;
	}
	public Timestamp getTimestart() {
		return timestart;
	}
	public void setTimestart(Timestamp timestart) {
		this.timestart = timestart;
	}
	public Timestamp getTimeend() {
		return timeend;
	}
	public void setTimeend(Timestamp timeend) {
		this.timeend = timeend;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
     
    
    
}
