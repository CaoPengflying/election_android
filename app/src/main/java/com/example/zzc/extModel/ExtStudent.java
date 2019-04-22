package com.example.zzc.extModel;


import com.example.zzc.model.Student;

/**
 * @author caopengflying
 * @time 2019/1/30 13:35
 */
public class ExtStudent extends Student {
    private Integer offset;
    private Integer limit;
    private String gradeName;

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
