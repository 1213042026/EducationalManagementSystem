package cn.edu.management.vo.showVO;



public class StudentGraduationWorksMange  {
	
    private String gidnum;//毕业设计编号 

	private String gname;//毕业设计名称
	
	private String sidnum ; // varchar2(20),  --学 号：  
	
	private String sname;//学生姓名
	
    private String pro_name;//学生专业 

	public String getGidnum() {
		return gidnum;
	}

	public void setGidnum(String gidnum) {
		this.gidnum = gidnum;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getSidnum() {
		return sidnum;
	}

	public void setSidnum(String sidnum) {
		this.sidnum = sidnum;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String proName) {
		pro_name = proName;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
	
    
    
    
}
