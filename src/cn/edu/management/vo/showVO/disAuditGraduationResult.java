package cn.edu.management.vo.showVO;


/**
 * 
 *教师查看毕业设计审核结果
 *
 */

public class disAuditGraduationResult{
	
	private String sidnum;//学号
	
    private String graduation ; //integer, --毕业设计名称

    private String applyDate ; //date,  --申请时间 
    
    private String auditDate ; //date,  --审核时间    


    private String result ; //审核结果
    
    private String suggest;//审核意见
	//

    

	public String getSidnum() {
		return sidnum;
	}


	public String getSuggest() {
		return suggest;
	}


	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}


	public void setSidnum(String sidnum) {
		this.sidnum = sidnum;
	}


	public String getGraduation() {
		return graduation;
	}


	public void setGraduation(String graduation) {
		this.graduation = graduation;
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


	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}
	

	//--------all getter and setter --------------------------------------------- 	
    
	
    
	
}
