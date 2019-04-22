package com.example.zzc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zzc.election_application.R;
import com.example.zzc.extModel.ExtActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaoPengfei on 2019/4/14.
 */

public class ActivityListAdapter extends BaseAdapter {
    List<ExtActivity> extActivityList = new ArrayList<>();
    private Context context;

    public ActivityListAdapter(Context context) {
        this.context = context;
    }

    public ActivityListAdapter(List<ExtActivity> extActivityList, Context context) {
        this.context = context;
        this.extActivityList = extActivityList;
    }

    @Override
    public int getCount() {
        return extActivityList == null ? 0 : extActivityList.size();
    }

    @Override
    public Object getItem(int position) {
        return extActivityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_activity_list, parent, false);
            holderView = new HolderView();
            holderView.txt_activity_name = (TextView) convertView.findViewById(R.id.tv_ap_activity_name);
            holderView.txt_activity_begin_time = (TextView) convertView.findViewById(R.id.tv_ap_activity_end_time);
            holderView.txt_activity_end_time = (TextView) convertView.findViewById(R.id.tv_ap_activity_begin_time);
            convertView.setTag(holderView);
        }else {
            holderView = (HolderView) convertView.getTag();
        }
        setText(position,holderView);
        return convertView;
    }

    /**
     * 设置每行的文字
     * @param position
     * @param holderView
     */
    private void setText(int position, HolderView holderView) {
        holderView.txt_activity_name.setText(extActivityList.get(position).getActivityName());
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-dd-MM");
        holderView.txt_activity_begin_time.setText(sdf.format(extActivityList.get(position).getStartTime()));
        holderView.txt_activity_end_time.setText(sdf.format(extActivityList.get(position).getEndTime()));
    }

    public class HolderView {
        TextView txt_activity_name;
        TextView txt_activity_begin_time;
        TextView txt_activity_end_time;

    }

    public List<ExtActivity> getExtActivityList() {
        return extActivityList;
    }

    public void setExtActivityList(List<ExtActivity> extActivityList) {
        this.extActivityList = extActivityList;
    }
}
