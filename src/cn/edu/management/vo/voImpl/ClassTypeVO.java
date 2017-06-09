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
 *--课程类型表  表五
 */
@Entity
@Table(name="EMS_CLASSTYPE")
public class ClassTypeVO implements VO {
	@Id
	@Column(length = 20)
	@AccessType(value = "property")	
	private String idnum ;//varchar2(20),  --课程类型编号pk
	
	@Column
    private String classtypename; //varchar2(100),  --课程类型名
	
	@Column
    private String remarks ; // varchar2(500),  --备注
    
    
    //----all getter and setter --------------------------------------------------
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getClasstypename() {
		return classtypename;
	}

	public void setClasstypename(String classtypename) {
		this.classtypename = classtypename;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
     
    
    
}
