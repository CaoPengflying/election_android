package com.example.zzc.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.zzc.election_application.ActivityActivityDetail;
import com.example.zzc.election_application.R;
import com.example.zzc.extModel.ExtActivityUser;
import com.example.zzc.util.FastJsonTools;
import com.example.zzc.util.MyConfigUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by CaoPengfei on 2019/4/20.
 */

public class ActivityDetailAdapter extends BaseAdapter implements View.OnClickListener {
    private List<ExtActivityUser> extActivityUserList;
    private Context context;

    private InnerItemOnclickListener mListener;


    public ActivityDetailAdapter(List<ExtActivityUser> extActivityUserList, Context context) {
        this.extActivityUserList = extActivityUserList;
        this.context = context;
    }

    public ActivityDetailAdapter(Context context) {
        this.context = context;
    }

    public ActivityDetailAdapter(List<ExtActivityUser> extActivityUserList) {
        this.extActivityUserList = extActivityUserList;
    }

    @Override
    public int getCount() {
        return extActivityUserList == null ? 0 : extActivityUserList.size();
    }

    @Override
    public Object getItem(int position) {
        return extActivityUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final HolderView holderView;
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_activity_detail, parent, false);
            holderView = new HolderView();
            holderView.txt_user_name = (TextView) convertView.findViewById(R.id.tv_ap_activity_detail_voted_name);
            holderView.txt_number = (TextView) convertView.findViewById(R.id.tv_ap__activity_detail_voted_num);
            holderView.iv_select = (ImageView) convertView.findViewById(R.id.iv_ap_activity_detail_good);
            convertView.setTag(holderView);
        }else {
            holderView = (HolderView) convertView.getTag();
        }
        setContent(position,holderView);
        final int num = position;
        holderView.iv_select.setOnClickListener(this);
        holderView.iv_select.setTag(position);
        return convertView;
    }

    /**
     * 设置当前行展示的内容
     * @param position
     * @param holderView
     */
    private void setContent(int position, HolderView holderView) {
        ExtActivityUser extActivityUser = extActivityUserList.get(position);
        holderView.txt_user_name.setText(extActivityUser.getStudentName());
        holderView.txt_number.setText(extActivityUser.getVote() + "票");
        if (extActivityUser.getSelectFlag()){
            holderView.iv_select.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.gooded));
        }else {
            holderView.iv_select.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.good));
        }
    }
    public interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener){
        this.mListener=listener;
    }
    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }

    public class HolderView {
        TextView txt_user_name;
        TextView txt_number;
        ImageView iv_select;
    }

    public List<ExtActivityUser> getExtActivityUserList() {
        return extActivityUserList;
    }

    public void setExtActivityUserList(List<ExtActivityUser> extActivityUserList) {
        this.extActivityUserList = extActivityUserList;
    }
}
