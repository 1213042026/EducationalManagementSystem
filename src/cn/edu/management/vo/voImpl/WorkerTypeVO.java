package cn.edu.management.vo.voImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import cn.edu.management.vo.VO;
/**
 * 
 * 作品类型表
 * @author tq
 *
 */
@Entity
@Table(name="EMS_WORKERTYPE")
public class WorkerTypeVO implements VO {
	
	@Id
	@Column(length = 20)
	@AccessType(value = "property")	
	private String  idnum ; //varchar2(20) primary key , --Pk
	
	@Column
    private String works_name ; //varchar2(100),  --作品类型名 
	
	@Column
    private String remarks ; //varchar2(500)  --备注
    
    //-------------all getter and setter ---------------------------------------
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	
	public String getWorks_name() {
		return works_name;
	}
	public void setWorks_name(String worksName) {
		works_name = worksName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
    
    
}
