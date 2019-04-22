package com.example.zzc.extModel;


import com.example.zzc.model.ActivityUser;

/**
 * @author caopengflying
 * @time 2019/1/23 17:44
 */
public class ExtActivityUser extends ActivityUser {

    private Integer limit;
    private Integer offset;
    private Boolean selectFlag;
    private String studentName;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Boolean getSelectFlag() {
        return selectFlag;
    }

    public void setSelectFlag(Boolean selectFlag) {
        this.selectFlag = selectFlag;
    }

}
