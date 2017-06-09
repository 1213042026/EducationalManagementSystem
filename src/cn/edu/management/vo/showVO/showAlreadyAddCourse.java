package cn.edu.management.vo.showVO;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.AccessType;

import cn.edu.management.vo.VO;

public class showAlreadyAddCourse implements VO {

	private String classId ; // varchar2(20), --�γ̱�ţУ�
    private String className ; //varchar2(100), --�γ�����
	private String classType ;//�γ�������
	private String teacherId ; //��ʦ����    
    private String term ; // varchar2(10),  --ѧ��	
    private String grade ; // varchar2(20),  --�����꼶    
	private String  professionId ;//רҵ����	
	/**
	 * ��ʱ������ݿ��ж�����������Action�к�,�ڽ��������������������Ϊ�������⣮
	 */
    private String  kkCount ;//varchar2(10),                  --��ѡ����
    private String ssCount;// varchar2(10),                  --ʣ������    
    private String recordTime ; //timestamp,  --¼��γ�ʱ��    
    private String remark;
    
    
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getProfessionId() {
		return professionId;
	}
	public void setProfessionId(String professionId) {
		this.professionId = professionId;
	}

	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getKkCount() {
		return kkCount;
	}
	public void setKkCount(String kkCount) {
		this.kkCount = kkCount;
	}
	public String getSsCount() {
		return ssCount;
	}
	public void setSsCount(String ssCount) {
		this.ssCount = ssCount;
	}
    
    
    
  
    
//    classId varchar2(20) primary key ,    --�γ̱�ţУ�
//    className varchar2(100),              --�γ�����
//    classType varchar2(20),               --�γ����ͣƣ�
//    teacherId varchar2(20),               --��ʦ���F K    
//    term varchar2(10),                    --ѧ��
//    grade varchar2(20),                   --�����꼶
  //  professionId varchar2(20),            --����רҵF K(������Ϊ�������޿Σ�ע��һ��)
    //kCount varchar2(10),                  --��ѡ����
   // sCount varchar2(10),                  --ʣ������
   // recordTime timestamp,                 --¼��γ�ʱ��
   // remark  varchar2(300),                --��ע
  
    
    
    //-------------all getter and setter --------------------------------------------

    
	
}
