package cn.edu.management.vo.showVO;

public class disAuditGraduationTitle {

	private String sidnum ; // varchar2(20),  -ѧ��
	

    private String gidnum; //varchar2(20),  --��ҵ��Ʊ�� 
    
    private String ggidnum;//��ҵ��¼����

    private String applyDate ; //date,  --����ʱ��

    private String auditDate ; //date,  --���ʱ�� 

    private String modifyResult ; //integer, --��˽��

    private String fortable ; //varchar2(100),  --�û����ڱ�ems_graduation��
	//
	
	private String auditMan ;// --�����      F K
	
    private String profession;//רҵ

	private String remark ;//varchar(300),             --��ע:��¼��ͨ��ԭ���.

	
	
	


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
