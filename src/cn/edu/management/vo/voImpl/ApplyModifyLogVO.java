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
import org.hibernate.annotations.OrderBy;
import org.springframework.core.annotation.Order;

import cn.edu.management.vo.VO;

/**
 * 
 * @author tq
 *
 */
@Entity
@Table(name="Ems_applyModifyLog")
public class ApplyModifyLogVO implements VO {
	
	@Id	
	@Column(length = 50)
	@AccessType(value = "property")
	@Order
	private String idnum ; // varchar2(20),  --PK
	
	@Column
    private String applyUserId ; //varchar2(20),  --修改用户ID 
	@Column
    private Timestamp applyDate ; //date,  --申请时间
	@Column
    private Timestamp auditDate ; //date,  --审核时间 
	@Column
    private String modifyResult ; //integer, --审核结果
	@Column
    private String fortable ; //varchar2(100),  --用户所在表（ems_student，ems_teacher）
	//
	@Column
	private String auditMan ;// --审核人      F K
	

	@Column
	private String remark ;//varchar(300),             --备注:记录不通过原因等.
     
	@Column
	private String remarks ;//varchar(300),             --备注:记录不通过原因等.
    
	//--------all getter and setter --------------------------------------------- 	
    
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIdnum() {
		return idnum;
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
	public Timestamp getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Timestamp applyDate) {
		this.applyDate = applyDate;
	}
	public Timestamp getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Timestamp auditDate) {
		this.auditDate = auditDate;
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

	
	public String getAuditMan() {
		return auditMan;
	}
	public void setAuditMan(String auditMan) {
		this.auditMan = auditMan;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
  
    
	
}
