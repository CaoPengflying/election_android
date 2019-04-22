package com.example.zzc.extModel;


import com.example.zzc.model.Activity;

/**
 * @author caopengflying
 * @time 2019/1/30 13:34
 */
public class ExtActivity extends Activity {
    private Integer offset;
    private Integer limit;
    private Integer leftVotes;
    private Integer studentId;
    private String createUserName;
    private String createGradeName;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getCreateGradeName() {
        return createGradeName;
    }

    public void setCreateGradeName(String createGradeName) {
        this.createGradeName = createGradeName;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }


    public Integer getLeftVotes() {
        return leftVotes;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setLeftVotes(Integer leftVotes) {
        this.leftVotes = leftVotes;
    }
}
