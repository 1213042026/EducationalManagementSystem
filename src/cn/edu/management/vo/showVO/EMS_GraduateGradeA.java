package cn.edu.management.vo.showVO;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.edu.management.vo.VO;

/**
 * 
 * @author horse --ѧ���鿴�Լ�ѡ��ı�ҵ���
 */
@Entity
@Table(name = "ems_graduateGrade")
public class EMS_GraduateGradeA implements VO {
	
	private String idnum;// varchar2(20), --���P K

	private String gidnum;//��ҵ��Ʊ��
	

	private String gname; // ��ҵ������� FK

	private String grade; // varchar2(3), --�ɼ�
	
	private String teacherName; //ָ����ʦ

	private String gxtime;//ѡ��ʱ��
	
	private String remark; // varchar2(500), --��ע

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getGxtime() {
		return gxtime;
	}

	public void setGxtime(String gxtime) {
		this.gxtime = gxtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGidnum() {
		return gidnum;
	}

	public void setGidnum(String gidnum) {
		this.gidnum = gidnum;
	}
	

}
