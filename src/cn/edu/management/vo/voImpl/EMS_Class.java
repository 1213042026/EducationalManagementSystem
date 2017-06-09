package cn.edu.management.vo.voImpl;

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

/**
 * 
 * @author tq
 * 
 *
 */
@Entity
@Table(name="ems_class")
public class EMS_Class implements VO {
	@Id
	@Column(length = 20)
	@AccessType(value = "property")	
	private String classId ; // varchar2(20), --�γ̱�ţУ�
	
	@Column
    private String className ; //varchar2(100), --�γ�����
	
	
//    private String classType ; //varchar2(20),  --�γ����ͣƣ�
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name = "classType" ,updatable=true , insertable=true)
	private ClassTypeVO classType ;
	
//    private String teacherId ; // varchar2(20),  --��ʦ���fk
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name = "teacherId" ,updatable=true , insertable=true)
	private Ems_Teacher_VO teacherId ;

    
	@Column
    private String term ; // varchar2(10),  --ѧ��
	
	@Column
    private String grade ; // varchar2(20),  --�����꼶
    
    
//    private String professionId ; //varchar2(20), --����רҵfk
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name = "professionId" ,updatable=true , insertable=true)
	private ProfessionVO professionId ;
	
	 @Column
     private String  kCount ;//varchar2(10),                  --��ѡ����
	 
	 @Column
     private String sCount;// varchar2(10),                  --ʣ������
    
    @Column
    private Timestamp recordTime ; //timestamp,  --¼��γ�ʱ��
    
    @Column
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

	public ClassTypeVO getClassType() {
		return classType;
	}

	public void setClassType(ClassTypeVO classType) {
		this.classType = classType;
	}

	public Ems_Teacher_VO getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Ems_Teacher_VO teacherId) {
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

	public ProfessionVO getProfessionId() {
		return professionId;
	}

	public void setProfessionId(ProfessionVO professionId) {
		this.professionId = professionId;
	}

	public String getkCount() {
		return kCount;
	}

	public void setkCount(String kCount) {
		this.kCount = kCount;
	}

	public String getsCount() {
		return sCount;
	}

	public void setsCount(String sCount) {
		this.sCount = sCount;
	}

	public Timestamp getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Timestamp recordTime) {
		this.recordTime = recordTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
