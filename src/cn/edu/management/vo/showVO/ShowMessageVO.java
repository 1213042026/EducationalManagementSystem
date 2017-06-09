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
import org.hibernate.annotations.OrderBy;
import org.springframework.core.annotation.Order;

import cn.edu.management.vo.VO;


public class ShowMessageVO implements VO {

	private String idnum ; // v--编号 PK	
	private String sidnum ; //学生编号 FK	
	private String tidnum ; //教师编号 FK
    private String title ; //留言主题
    private String content ; //留言内容
    private String mtime ; //　--留言时间	date
    private String status ; //--回复状态(未回复，已回复)
	private String rcontent ; //　　--回复内容		
    private String rtime ; //　--回复时间 date
    private String remark;//备注
	
	
	
	 
	//--------all getter and setter --------------------------------------------- 	

	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getSidnum() {
		return sidnum;
	}
	public void setSidnum(String sidnum) {
		this.sidnum = sidnum;
	}
	public String getTidnum() {
		return tidnum;
	}
	public void setTidnum(String tidnum) {
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
	public String getMtime() {
		return mtime;
	}
	public void setMtime(String mtime) {
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
	public String getRtime() {
		return rtime;
	}
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
     

    
	
    
	
}
