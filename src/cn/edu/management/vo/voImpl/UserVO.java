package cn.edu.management.vo.voImpl;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import cn.edu.management.vo.VO;


@Entity
@Table(name="ems_user")
public class UserVO implements VO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1932821153791425032L;

	@Id
	@Column(length = 50)
	@AccessType(value = "property")
	private String userid;
	
	@Column(length = 50)
	private String password;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
