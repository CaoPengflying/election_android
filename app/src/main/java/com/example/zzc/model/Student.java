package com.example.zzc.model;

public class Student {
	private Integer studentId;//
	private String studentNo;//学号
	private String studentName;//
	private String studentPassword;//
	private String sex;//
	private String idCard;//身份证号
	private String headUrl;//
	private Integer gradeId;//
	private Integer role;//
	private String ef01;//
	private String ef02;//
	private String ef03;//


	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Integer getRole() {
		return role;
	}
	public void setEf01(String ef01) {
		this.ef01 = ef01;
	}
	public String getEf01() {
		return ef01;
	}
	public void setEf02(String ef02) {
		this.ef02 = ef02;
	}
	public String getEf02() {
		return ef02;
	}
	public void setEf03(String ef03) {
		this.ef03 = ef03;
	}
	public String getEf03() {
		return ef03;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
}

