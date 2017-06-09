package cn.edu.management.vo.voImpl;

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
@Entity
@Table(name="EMS_TEACHER")
public class Ems_Teacher_VO implements VO {

	@Id
	@Column(length = 20)
	@AccessType(value = "property")
	private String idnum;// varchar2(20), --老师编号：顺序生成做为索引pk
	
	@Column
    private String password ; // varchar2(20), --密码
	
	@Column
    private String name ; // varchar2(100), --姓名
	
	@Column
    private String name_temp ; //varchar2(100),--姓名备份
	
	@Column
    private String sex ; // varchar2(2),--性别
	
	@Column
    private String idcard ; // varchar2(18), --身份证号码
	
	@Column
    private String idcard_temp ; //varchar2(18),--身份证号码备份
	
	@Column
    private String address ; // varchar2(200),--家庭住址
	
	@Column
    private String address_temp ;//varchar2(200),--家庭住址备份
	
	//
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name = "profession" ,updatable=true , insertable=true)
	private ProfessionVO profession ;
	
	@Column
    private String nation ; //varchar2(50), --民族
	
	@Column
    private String scientific ; //varchar2(5), --学历
	
	@Column
    private String title ; // varchar2(5), --职称
	
	@Column
    private int flag; // number, --修改标志：当修改后的标志0表示正常 ，1表示修改通过，-1表示修改未通过*/
	
    
    
    
    
    //---------------all setter and getter------------------------------------------------------
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
	public ProfessionVO getProfession() {
		return profession;
	}
	public void setProfession(ProfessionVO profession) {
		this.profession = profession;
	}
    
    
    
    
}
