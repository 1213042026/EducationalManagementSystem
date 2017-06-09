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
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.OrderBy;

import cn.edu.management.vo.VO;

/**
 * 
 * @author tq
 *
 */

public class showApplyModifyLogVO implements VO {
	
	
	private String idnum ; // varchar2(20),  --PK
	

    private String applyUserId ; //varchar2(20),  --�޸��û�ID 

    private String applyDate ; //date,  --����ʱ��

    private String auditDate ; //date,  --���ʱ�� 

    private String modifyResult ; //integer, --��˽��

    private String fortable ; //varchar2(100),  --�û����ڱ�ems_student��ems_teacher��
	//
	
	private String auditMan ;// --�����      F K
	


	private String remark ;//varchar(300),             --��ע:��¼��ͨ��ԭ���.
	
	private String remarks;//��ҵѡ��������
     
	 
	//--------all getter and setter --------------------------------------------- 	
    
	
	public String getIdnum() {
		return idnum;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getApplyUserId() {
		return applyUserId;
	}
	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}
	

	public String getModifyResult() {
		return modifyResult;
	}
	public void setModifyResult(String modifyResult) {
		this.modifyResult = modifyResult;
	}
	public String getFortable() {
		return fortable;
	}
	public void setFortable(String fortable) {
		this.fortable = fortable;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getAuditMan() {
		return auditMan;
	}
	public void setAuditMan(String auditMan) {
		this.auditMan = auditMan;
	}
	
  
    
	
}
