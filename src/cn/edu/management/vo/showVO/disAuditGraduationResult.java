package cn.edu.management.vo.showVO;


/**
 * 
 *��ʦ�鿴��ҵ�����˽��
 *
 */

public class disAuditGraduationResult{
	
	private String sidnum;//ѧ��
	
    private String graduation ; //integer, --��ҵ�������

    private String applyDate ; //date,  --����ʱ�� 
    
    private String auditDate ; //date,  --���ʱ��    


    private String result ; //��˽��
    
    private String suggest;//������
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
