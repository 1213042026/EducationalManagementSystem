package cn.edu.management.vo.showVO;

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
 * @author tq
 */

public class showTeacher_VO implements VO {

	
	private String idnum;// varchar2(20), --老师编号：顺序生成做为索引pk
	

    private String password ; // varchar2(20), --密码
	

    private String name ; // varchar2(100), --姓名
	

    private String name_temp ; //varchar2(100),--姓名备份
	
	
    private String sex ; // varchar2(2),--性别
	
	
    private String idcard ; // varchar2(18), --身份证号码
	

    private String idcard_temp ; //varchar2(18),--身份证号码备份
	
	
    private String address ; // varchar2(200),--家庭住址
	
	
    private String address_temp ;//varchar2(200),--家庭住址备份
	

	private String  profession ;//专业名
	
	
    private String nation ; //varchar2(50), --民族
	
	
    private String scientific ; //varchar2(5), --学历
	

    private String title ; // varchar2(5), --职称
	

    private int flag; // number, --修改标志：当修改后的标志0表示正常 ，1表示修改通过，-1表示修改未通过*/


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


	public String getName_temp() {
		return name_temp;
	}


	public void setName_temp(String nameTemp) {
		name_temp = nameTemp;
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


	public String getIdcard_temp() {
		return idcard_temp;
	}


	public void setIdcard_temp(String idcardTemp) {
		idcard_temp = idcardTemp;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getAddress_temp() {
		return address_temp;
	}


	public void setAddress_temp(String addressTemp) {
		address_temp = addressTemp;
	}


	public String getProfession() {
		return profession;
	}


	public void setProfession(String profession) {
		this.profession = profession;
	}


	public String getNation() {
		return nation;
	}


	public void setNation(String nation) {
		this.nation = nation;
	}


	public String getScientific() {
		return scientific;
	}


	public void setScientific(String scientific) {
		this.scientific = scientific;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}
	
    
    
    
    
  
    
    
}
