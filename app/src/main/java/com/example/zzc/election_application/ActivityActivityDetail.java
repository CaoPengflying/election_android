package com.example.zzc.election_application;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.zzc.adapter.ActivityDetailAdapter;
import com.example.zzc.adapter.ActivityListAdapter;
import com.example.zzc.extModel.ActivityForm;
import com.example.zzc.extModel.ExtActivity;
import com.example.zzc.extModel.ExtStudent;
import com.example.zzc.model.ActivityUser;
import com.example.zzc.util.FastJsonTools;
import com.example.zzc.util.GetUser;
import com.example.zzc.util.MyConfigUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ActivityActivityDetail extends Activity implements ActivityDetailAdapter.InnerItemOnclickListener, View.OnClickListener {
    private ActivityForm activityForm;
    private TextView tvActivityName, tvStartTime, tvEndTime, tvCreateUserName, tvGradeName;
    private ImageView ivBack, ivGooded;
    private ListView lvActivityUser;
    private ActivityDetailAdapter activityDetailAdapter;
    private ExtStudent extStudent;
    private OkHttpClient mOkHttpClient = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_detail);
        extStudent = new GetUser(this).getinfo();
        init();

    }

    /**
     * 初始化控件
     */
    private void init() {
        tvActivityName = (TextView) findViewById(R.id.tv_av_activity_detail_activity_name);
        tvStartTime = (TextView) findViewById(R.id.tv_av_activity_detail_begin_time);
        tvEndTime = (TextView) findViewById(R.id.tv_av_activity_detail_end_time);
        tvCreateUserName = (TextView) findViewById(R.id.tv_av_activity_detail_create_user_name);
        tvGradeName = (TextView) findViewById(R.id.tv_av_activity_detail_grade_name);
        ivBack = (ImageView) findViewById(R.id.iv_av_activity_detail_back);
        ivGooded = (ImageView) findViewById(R.id.iv_ap_activity_detail_good);
        lvActivityUser = (ListView) findViewById(R.id.lv_av_detail_vode_list);
        //获取活动详情内容
        String result = getIntent().getStringExtra("activityForm");
        Map<String, Object> map = FastJsonTools.getMap(result);
        String data = map.get("t").toString();
        activityForm = FastJsonTools.getBean(data, ActivityForm.class);
        //渲染页面
        tvActivityName.setText(activityForm.getExtActivity().getActivityName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        tvStartTime.setText(sdf.format(activityForm.getExtActivity().getStartTime()));
        tvEndTime.setText(sdf.format(activityForm.getExtActivity().getEndTime()));
        tvCreateUserName.setText(activityForm.getExtActivity().getCreateUserName());
        tvGradeName.setText(activityForm.getExtActivity().getCreateGradeName());

        activityDetailAdapter = new ActivityDetailAdapter(activityForm.getExtActivityUserList(), this.getApplicationContext());
        activityDetailAdapter.setOnInnerItemOnClickListener(this);
        ivBack.setOnClickListener(this);
        lvActivityUser.setAdapter(activityDetailAdapter);
        Message msg = new Message();
        msg.what = COMPLETED;
        handler.sendMessage(msg);

    }

    private static final int COMPLETED = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == COMPLETED) {
                activityDetailAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    public void itemClick(View v) {
        final int position;
        final View gooded = v;
        position = (Integer) v.getTag();
        String activity_url = MyConfigUtil.URL + "user/update";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", activityForm.getExtActivityUserList().get(position).getUserId());
        jsonObject.put("studentId", extStudent.getStudentId());
        jsonObject.put("vote", activityForm.getExtActivityUserList().get(position).getVote() + 1);
        RequestBody requestBodyPost = new FormBody.Builder()
                .add("jsonObject", jsonObject.toJSONString())
                .build();
        Request requestPost = new Request.Builder()
                .url(activity_url)
                .post(requestBodyPost)
                .build();
        mOkHttpClient.newCall(requestPost).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Toast.makeText(view.getContext(), "获取列表失败", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Map<String, Object> map = FastJsonTools.getMap(result);
                    String data = map.get("status").toString();
                    Looper.prepare();
                    if (data.equals("00000")) {
                        activityForm.getExtActivityUserList().get(position).setSelectFlag(true);
                        activityDetailAdapter.setExtActivityUserList(activityForm.getExtActivityUserList());
                        Message msg = new Message();
                        msg.what = COMPLETED;
                        handler.sendMessage(msg);
                        Toast.makeText(getApplicationContext(), "选票成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), map.get("text").toString(), Toast.LENGTH_SHORT).show();
                    }
                    Looper.loop();

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_av_activity_detail_back:
                this.finish();
        }
    }
}
