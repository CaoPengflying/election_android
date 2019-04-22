package com.example.zzc.model;

import java.util.Date;

public class Activity {
	private Integer activityId;//
	private String activityName;//
	private Date startTime;//
	private Date endTime;//
	private String activityDescribe;//活动描述
	private Integer createUser;//活动创建人
	private Integer joinGrade;//参与班级
	private Integer num;//获奖人数
	private Integer votes;//学生拥有的票数
	private String ef01;//
	private String ef02;//
	private String ef03;//


	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setActivityDescribe(String activityDescribe) {
		this.activityDescribe = activityDescribe;
	}
	public String getActivityDescribe() {
		return activityDescribe;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setJoinGrade(Integer joinGrade) {
		this.joinGrade = joinGrade;
	}
	public Integer getJoinGrade() {
		return joinGrade;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getNum() {
		return num;
	}
	public void setVotes(Integer votes) {
		this.votes = votes;
	}
	public Integer getVotes() {
		return votes;
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
}

