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
	private String idnum ; // varchar2(20),  --ѧ �ţ�˳��������Ϊ����pk
	

	@Column
    private String password ; //varchar2(20),  --�û�����
	
	@Column
	private String name;// varchar2(100),  --����
	
	@Column
	private String username_temp ; //varchar2(100),  --��������
	
	@Column
	private String sex ; // varchar2(2), --�Ա�
	
	@Column
	private String idcard ; // varchar2(18), --���֤��
	
	@Column
	private String idcrad_temp ; //varchar2(18),  --���֤�ű���
	
	//
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name = "profession" ,updatable=true , insertable=true)
	private ProfessionVO profession ;
	
	/*@Column
	private String profession ; // varchar2(20),   --רҵ   fk
*/	
	@Column
	private String address ; //varchar2(200),  --��ͥסַ
	
	@Column
	private String address_temp ; //varchar2(200), --��ͥסַ����
	
	@Column
	private String nation ; //varchar2(50),  --����
	
	@Column
	private String entranceDate ; //ENTRANCEDATE VARCHAR2(10),  --��ѧ���
	
	@Column
	private int flag ; // number,  --�޸ı�־�����޸ĺ�ı�־0��ʾ������1��ʾ�޸�ͨ����-1��ʾ�޸�δͨ��
	
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
