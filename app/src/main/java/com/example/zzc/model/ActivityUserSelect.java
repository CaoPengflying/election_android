package com.example.zzc.model;

public class ActivityUserSelect {
	private Integer activityUserSelectId;//

	private Integer userId;//待选举人
	private Integer studentId;//投票人


	public void setActivityUserSelectId(Integer activityUserSelectId) {
		this.activityUserSelectId = activityUserSelectId;
	}
	public Integer getActivityUserSelectId() {
		return activityUserSelectId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getStudentId() {
		return studentId;
	}
}

