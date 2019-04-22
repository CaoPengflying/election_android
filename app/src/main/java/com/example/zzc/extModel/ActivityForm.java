package com.example.zzc.extModel;


import java.util.List;

/**
 * @author caopengflying
 * @time 2019/1/31 11:16
 */
public class ActivityForm {
    private ExtActivity extActivity;
    private List<ExtActivityUser> extActivityUserList;

    public ExtActivity getExtActivity() {
        return extActivity;
    }

    public void setExtActivity(ExtActivity extActivity) {
        this.extActivity = extActivity;
    }

    public List<ExtActivityUser> getExtActivityUserList() {
        return extActivityUserList;
    }

    public void setExtActivityUserList(List<ExtActivityUser> extActivityUserList) {
        this.extActivityUserList = extActivityUserList;
    }
}
