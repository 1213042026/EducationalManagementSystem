package cn.edu.management.vo.showVO;

/**
 * 
 * @author --�γ̱�  
 *
 */

public class showEmsClass  {
	
	

	      String  classId;                  //varchar2(20) primary key ,    --�γ̱�ţУ�
	      String  className;               // varchar2(100),              --�γ�����
	      String  classType;               // varchar2(20),               --�γ����ͣƣ�
	      String  teacherId;              // varchar2(20),               --��ʦ���F K    
	      String  term;                  // varchar2(10),                    --ѧ��
	      String  grade;                 // varchar2(20),                   --�����꼶
	      String  professionId;          // varchar2(20),            --����רҵF K(������Ϊ�������޿Σ�ע��һ��)
	      String  m;                  // varchar2(10),                  --��ѡ����
	      String  n;               // varchar2(10),                  --ʣ������
	      String  recordTime;           // timestamp,                 --¼��γ�ʱ��
	      String  remark;              //  varchar2(300),                --��ע
	      String  score;        //�ɼ�
	      String  xueNian;         //ѡ�ε��꼶����һ�����������
	      
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
