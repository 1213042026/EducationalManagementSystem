package cn.edu.management.vo.showVO;

public class studentGradeSearch {
	private String gidnum;// 毕业设计编号

	private String gname;// 毕业设计名称

	private String sidnum; // varchar2(20), --学 号：

	private String sname;//  学生姓名
	
	private String grade;// 成绩

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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	
	
	

}
