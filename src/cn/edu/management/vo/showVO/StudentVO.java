package cn.edu.management.vo.showVO;

import cn.edu.management.vo.VO;

public class StudentVO implements VO {

	
	
	private String idnum ; // varchar2(20),  --ѧ �ţ�˳��������Ϊ����pk
	
    private String password ; //varchar2(20),  --�û�����
	
	private String name;// varchar2(100),  --����
	
	private String username_temp ; //varchar2(100),  --��������
	
	private String sex ; // varchar2(2), --�Ա�
	
	private String idcard ; // varchar2(18), --���֤��
	
	private String idcrad_temp ; //varchar2(18),  --���֤�ű���
	
	private String prof_Name ; // varchar2(20),   --רҵ  ���� fk
	
	private String address ; //varchar2(200),  --��ͥסַ
	
	private String address_temp ; //varchar2(200), --��ͥסַ����
	
	private String nation ; //varchar2(50),  --����
	
	private String entranceDate ; //ENTRANCEDATE VARCHAR2(10),  --��ѧ���
	
	private int flag ; // number,  --�޸ı�־�����޸ĺ�ı�־0��ʾ������1��ʾ�޸�ͨ����-1��ʾ�޸�δͨ��

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

	public String getProf_Name() {
		return prof_Name;
	}

	public void setProf_Name(String profName) {
		prof_Name = profName;
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
	
	
	
	
	
}
