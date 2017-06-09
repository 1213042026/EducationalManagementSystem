package cn.edu.management.vo.showVO;

public class disAuditGraduationTitle {

	private String sidnum ; // varchar2(20),  -学号
	

    private String gidnum; //varchar2(20),  --毕业设计编号 
    
    private String ggidnum;//毕业记录表编号

    private String applyDate ; //date,  --申请时间

    private String auditDate ; //date,  --审核时间 

    private String modifyResult ; //integer, --审核结果

    private String fortable ; //varchar2(100),  --用户所在表（ems_graduation）
	//
	
	private String auditMan ;// --审核人      F K
	
    private String profession;//专业

	private String remark ;//varchar(300),             --备注:记录不通过原因等.

	
	
	


	public String getSidnum() {
		return sidnum;
	}

	public void setSidnum(String sidnum) {
		this.sidnum = sidnum;
	}

	public String getGidnum() {
		return gidnum;
	}

	public void setGidnum(String gidnum) {
		this.gidnum = gidnum;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public String getModifyResult() {
		return modifyResult;
	}

	public void setModifyResult(String modifyResult) {
		this.modifyResult = modifyResult;
	}

	public String getFortable() {
		return fortable;
	}

	public void setFortable(String fortable) {
		this.fortable = fortable;
	}

	public String getAuditMan() {
		return auditMan;
	}

	public void setAuditMan(String auditMan) {
		this.auditMan = auditMan;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGgidnum() {
		return ggidnum;
	}

	public void setGgidnum(String ggidnum) {
		this.ggidnum = ggidnum;
	}
	
	
	
     
}
