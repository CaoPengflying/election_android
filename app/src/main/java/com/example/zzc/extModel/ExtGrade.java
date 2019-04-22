package com.example.zzc.extModel;


import com.example.zzc.model.Grade;

/**
 * @author caopengflying
 * @time 2019/1/30 13:35
 */
public class ExtGrade extends Grade {
    private Integer limit;
    private Integer offset;

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
}
