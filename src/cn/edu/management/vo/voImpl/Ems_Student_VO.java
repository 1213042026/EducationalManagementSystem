package cn.edu.management.vo.voImpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import cn.edu.management.vo.VO;
/**
 * 
 * @author tq
 */
@Entity
@Table(name="EMS_STUDENT")
public class Ems_Student_VO implements VO {

	@Id
	@Column(length = 50)
	@AccessType(value = "property")	
	private String idnum ; // varchar2(20),  --学 号：顺序生成做为索引pk
	

	@Column
    private String password ; //varchar2(20),  --用户密码
	
	@Column
	private String name;// varchar2(100),  --姓名
	
	@Column
	private String username_temp ; //varchar2(100),  --姓名备份
	
	@Column
	private String sex ; // varchar2(2), --性别
	
	@Column
	private String idcard ; // varchar2(18), --身份证号
	
	@Column
	private String idcrad_temp ; //varchar2(18),  --身份证号备份
	
	//
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name = "profession" ,updatable=true , insertable=true)
	private ProfessionVO profession ;
	
	/*@Column
	private String profession ; // varchar2(20),   --专业   fk
*/	
	@Column
	private String address ; //varchar2(200),  --家庭住址
	
	@Column
	private String address_temp ; //varchar2(200), --家庭住址备份
	
	@Column
	private String nation ; //varchar2(50),  --民族
	
	@Column
	private String entranceDate ; //ENTRANCEDATE VARCHAR2(10),  --入学年份
	
	@Column
	private int flag ; // number,  --修改标志：当修改后的标志0表示正常，1表示修改通过，-1表示修改未通过
	
	//-----------all getter and setter -------------------------------------------------------------
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
	public String getUsername_temp() {
		return username_temp;
	}
	public void setUsername_temp(String usernameTemp) {
		username_temp = usernameTemp;
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
	public String getIdcrad_temp() {
		return idcrad_temp;
	}
	public void setIdcrad_temp(String idcradTemp) {
		idcrad_temp = idcradTemp;
	}
	/*public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}*/
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
	public String getEntranceDate() {
		return entranceDate;
	}
	public void setEntranceDate(String entranceDate) {
		this.entranceDate = entranceDate;
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
