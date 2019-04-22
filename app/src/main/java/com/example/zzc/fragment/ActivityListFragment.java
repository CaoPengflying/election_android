package com.example.zzc.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSONObject;
import com.example.zzc.adapter.ActivityListAdapter;
import com.example.zzc.election_application.ActivityActivityDetail;
import com.example.zzc.election_application.AddActivityActivity;
import com.example.zzc.election_application.MainActivity;
import com.example.zzc.election_application.R;
import com.example.zzc.extModel.ActivityForm;
import com.example.zzc.extModel.ExtActivity;
import com.example.zzc.extModel.ExtStudent;
import com.example.zzc.util.FastJsonTools;
import com.example.zzc.util.GetUser;
import com.example.zzc.util.MyConfigUtil;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ActivityListFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {
    private OkHttpClient mOkHttpClient = new OkHttpClient();
    private ListView lv_activityList;
    private ActivityListAdapter activityListAdapter;
    private List<ExtActivity> extActivityList = new ArrayList<>();
    private ExtStudent extStudent;
    private ImageView ivAddActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity_list, null);
        extStudent = new GetUser(getActivity().getApplicationContext()).getinfo();
        init(view);
        getActivityList(view);
        return view;
    }

    /**
     * 初始化活动列表页面
     *
     * @param view
     */
    private void init(View view) {
        lv_activityList = (ListView) view.findViewById(R.id.lv_activity_list);
        ivAddActivity = (ImageView) view.findViewById(R.id.iv_fg_al_add);
        activityListAdapter = new ActivityListAdapter(getActivity().getApplicationContext());
        lv_activityList.setAdapter(activityListAdapter);
        lv_activityList.setOnItemClickListener(this);
        if (extStudent.getRole().equals(1)){
            ivAddActivity.setOnClickListener(this);
        }else {
            ivAddActivity.setVisibility(View.GONE);
        }
    }

    private static final int COMPLETED = 0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == COMPLETED) {
                activityListAdapter.notifyDataSetChanged();
            }
        }
    };

    /**
     * 获取所有的活动列表
     *
     * @param view
     */
    public void getActivityList(final View view) {
        String activity_url = MyConfigUtil.URL + "activity/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("joinGrade", extStudent.getGradeId());
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
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    String result = response.body().string();
                    Map<String, Object> map = FastJsonTools.getMap(result);
                    String data = map.get("t").toString();
                    extActivityList = FastJsonTools.getBeans(data, ExtActivity.class);
                    activityListAdapter.setExtActivityList(extActivityList);
                    Message msg = new Message();
                    msg.what = COMPLETED;
                    handler.sendMessage(msg);
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
        String activity_url = MyConfigUtil.URL + "activity/get";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("activityId",extActivityList.get(position).getActivityId());
        jsonObject.put("studentId",extStudent.getStudentId());
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
                if (response.isSuccessful()){
                    String result = response.body().string();
                    Intent intent = new Intent(getActivity().getApplicationContext(), ActivityActivityDetail.class);
                    intent.putExtra("activityForm",result);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_fg_al_add:
                Intent intent = new Intent(getActivity().getApplicationContext(), AddActivityActivity.class);
                startActivity(intent);
        }
    }
}
