package cn.edu.management.vo.showVO;

/**
 * 
 * @author --课程表  
 *
 */

public class showEmsClass  {
	
	

	      String  classId;                  //varchar2(20) primary key ,    --课程编号ＰＫ
	      String  className;               // varchar2(100),              --课程名称
	      String  classType;               // varchar2(20),               --课程类型ＦＫ
	      String  teacherId;              // varchar2(20),               --教师编号F K    
	      String  term;                  // varchar2(10),                    --学期
	      String  grade;                 // varchar2(20),                   --所属年级
	      String  professionId;          // varchar2(20),            --所属专业F K(当类型为公共必修课，注意一下)
	      String  m;                  // varchar2(10),                  --可选人数
	      String  n;               // varchar2(10),                  --剩于人数
	      String  recordTime;           // timestamp,                 --录入课程时间
	      String  remark;              //  varchar2(300),                --备注
	      String  score;        //成绩
	      String  xueNian;         //选课的年级，大一，大二，大三
	      
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
		public String getM() {
			return m;
		}
		public void setM(String m) {
			this.m = m;
		}
		public String getN() {
			return n;
		}
		public void setN(String n) {
			this.n = n;
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
		public String getScore() {
			return score;
		}
		public void setScore(String score) {
			this.score = score;
		}
		public String getXueNian() {
			return xueNian;
		}
		public void setXueNian(String xueNian) {
			this.xueNian = xueNian;
		}
		
		
	      
}
