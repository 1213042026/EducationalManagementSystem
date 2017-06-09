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
import org.hibernate.annotations.OrderBy;
import org.springframework.core.annotation.Order;

import cn.edu.management.vo.VO;

/**
 * 
 *
 */
@Entity
@Table(name="ems_message")
public class MessageVO implements VO {
//	
//	  idnum    varchar2(20) primary key,  　
//	  sidnum   varchar2(20), 　　　　　　　 --
//	  tidnum   varchar2(20),    　　　　　　--
//	  title  varchar2(500),   　　　　　　  --
//	  content  varchar2(500),   　　　　　　--
//	  mtime    timestamp,       　　　　　
//	  status   varchar2(20),    　　　　　　
//	  rcontent varchar2(500),   　　　　
//	  rtime    timestamp,       　　　　　
	   
	@Id	
	@Column(length = 50)
	@AccessType(value = "property")
	@Order
	private String idnum ; // v--编号 PK
	
	
	//
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name = "sidnum" ,updatable=true , insertable=true)
	private Ems_Student_VO sidnum ; //学生编号 FK
	
	//
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn( name = "tidnum" ,updatable=true , insertable=true)
	private Ems_Teacher_VO tidnum ; //教师编号 FK
	
	@Column
    private String title ; //留言主题
	
	@Column
    private String content ; //留言内容
	
	@Column
    private Timestamp mtime ; //　--留言时间
	
	@Column
    private String status ; //--回复状态(未回复，已回复)
	
	@Column
    private String rcontent ; //　　--回复内容
	
	@Column
    private Timestamp rtime ; //　--回复时间
	
	@Column
    private String remark ; //　--备注
	
	
	 
	//--------all getter and setter --------------------------------------------- 	

	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}

	public Ems_Student_VO getSidnum() {
		return sidnum;
	}

	public void setSidnum(Ems_Student_VO sidnum) {
		this.sidnum = sidnum;
	}

	public Ems_Teacher_VO getTidnum() {
		return tidnum;
	}

	public void setTidnum(Ems_Teacher_VO tidnum) {
		this.tidnum = tidnum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getMtime() {
		return mtime;
	}

	public void setMtime(Timestamp mtime) {
		this.mtime = mtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRcontent() {
		return rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	public Timestamp getRtime() {
		return rtime;
	}

	public void setRtime(Timestamp rtime) {
		this.rtime = rtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
     

    
	
    
	
}
