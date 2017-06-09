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
 *
 */
@Entity
@Table(name="Ems_Manager")
public class Ems_Manager_VO implements VO {
	@Id
	@Column(length = 20)
	@AccessType(value = "property")
	private String idnum ; //varchar2(20), --����Ա��ţ�˳��������Ϊ����pk
	
	@Column
    private String password ;//varchar2(20), --����
	
	@Column
    private String name ; // varchar2(100), --����
    
	@Column
	private String sex ; // varchar2(2), --�Ա�
	
	@Column
    private String idcard ;// varchar2(18), --���֤����
	
	@Column
    private String address ; // varchar2(200), --��ͥסַ
	
	@Column
    private String nation ; // varchar2(50), --����*/
     
    //===========all getter and setter============================================================ 
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
     
     
     
}
