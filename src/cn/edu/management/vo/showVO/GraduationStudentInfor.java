package cn.edu.management.vo.showVO;



public class GraduationStudentInfor  {

   private String ggidnum;//��ҵ��Ƽ�¼����
	private String gname;//��ҵ�������
	
	private String idnum ; // varchar2(20),  --ѧ �ţ�  
	
	private String name;// varchar2(100),  --����
	
	private String grade;//�ɼ�
	
	private String sex ; // varchar2(2), --�Ա�
	
	private String idcard ; // varchar2(18), --���֤��	
	
	private String prof_Name ; // varchar2(20),   --רҵ  ���� fk
	
	private String address ; //varchar2(200),  --��ͥסַ
	
	private String nation ; //varchar2(50),  --����

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
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

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getGgidnum() {
		return ggidnum;
	}

	public void setGgidnum(String ggidnum) {
		this.ggidnum = ggidnum;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	

	
}
