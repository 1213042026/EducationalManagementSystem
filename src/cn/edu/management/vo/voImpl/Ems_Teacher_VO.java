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
	private String idnum;// varchar2(20), --��ʦ��ţ�˳��������Ϊ����pk
	
	@Column
    private String password ; // varchar2(20), --����
	
	@Column
    private String name ; // varchar2(100), --����
	
	@Column
    private String name_temp ; //varchar2(100),--��������
	
	@Column
    private String sex ; // varchar2(2),--�Ա�
	
	@Column
    private String idcard ; // varchar2(18), --���֤����
	
	@Column
    private String idcard_temp ; //varchar2(18),--���֤���뱸��
	
	@Column
    private String address ; // varchar2(200),--��ͥסַ
	
	@Column
    private String address_temp ;//varchar2(200),--��ͥסַ����
	
	//
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name = "profession" ,updatable=true , insertable=true)
	private ProfessionVO profession ;
	
	@Column
    private String nation ; //varchar2(50), --����
	
	@Column
    private String scientific ; //varchar2(5), --ѧ��
	
	@Column
    private String title ; // varchar2(5), --ְ��
	
	@Column
    private int flag; // number, --�޸ı�־�����޸ĺ�ı�־0��ʾ���� ��1��ʾ�޸�ͨ����-1��ʾ�޸�δͨ��*/
	
    
    
    
    
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
