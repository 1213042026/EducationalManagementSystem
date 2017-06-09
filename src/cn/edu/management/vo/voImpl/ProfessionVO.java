package cn.edu.management.vo.voImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import cn.edu.management.vo.VO;

/**
 * 
 * @author tq
 * --专业表  表三
 */
@Entity
@Table(name="EMS_PROFESSION")
public class ProfessionVO implements VO {
	@Id
	@Column(length = 20)
	@AccessType(value = "property")	
	private String idnum ;//varchar2(20),  --专业编号pk
	
	@Column
    private String pro_name ; //varchar2(100),  --专业名称
    
	@Column
    private String remarks ; //varchar2(500),  --备注*/
    
    //-----------all getter and setter-----------------------------------------------------

	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String proName) {
		pro_name = proName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
    
    
}
