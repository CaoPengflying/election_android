package com.example.zzc.election_application;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.zzc.extModel.ActivityForm;
import com.example.zzc.extModel.ExtActivity;
import com.example.zzc.extModel.ExtActivityUser;
import com.example.zzc.extModel.ExtStudent;
import com.example.zzc.model.Search;
import com.example.zzc.util.BasisTimesUtils;
import com.example.zzc.util.FastJsonTools;
import com.example.zzc.util.GetUser;
import com.example.zzc.util.MyConfigUtil;
import com.example.zzc.view.MultiSelectPopupWindows;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddActivityActivity extends Activity implements View.OnClickListener {
    private LinearLayout llSelectStudent;
    private ExtStudent extStudent;
    private MultiSelectPopupWindows productsMultiSelectPopupWindows;
    private TextView tvStartTime, tvEndTime, tvSure;
    private EditText etActivityName, etActivityNum, etActivityVote;
    private List<ExtStudent> extStudentList;
    private List<Search> dataList;
    private OkHttpClient mOkHttpClient = new OkHttpClient();
    private static int START_TIME_MENU = 1;
    private static int END_TIME_MENU = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);
        extStudent = new GetUser(this).getinfo();
        init();
    }

    private void init() {
        llSelectStudent = (LinearLayout) findViewById(R.id.linearLayout_product_type);
        tvEndTime = (TextView) findViewById(R.id.tv_av_add_activity_end_time);
        tvStartTime = (TextView) findViewById(R.id.tv_av_add_activity_start_time);
        tvSure = (TextView) findViewById(R.id.tv_av_add_activity_sure);
        etActivityName = (EditText) findViewById(R.id.et_at_add_activity_activity_name);
        etActivityNum = (EditText) findViewById(R.id.et_at_add_activity_num);
        etActivityVote = (EditText) findViewById(R.id.et_at_add_activity_vote);
        llSelectStudent.setOnClickListener(this);
        tvStartTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        tvSure.setOnClickListener(this);
        dataList = new ArrayList<>();

        getStudentList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.linearLayout_product_type:
                productsMultiSelectPopupWindows = new MultiSelectPopupWindows(this, llSelectStudent, 110, dataList);
                productsMultiSelectPopupWindows.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                    }
                });
                break;
            case R.id.tv_av_add_activity_end_time:
                showYearMonthDayPicker(END_TIME_MENU);
                break;
            case R.id.tv_av_add_activity_start_time:
                showYearMonthDayPicker(START_TIME_MENU);
                break;
            case R.id.tv_av_add_activity_sure:
                try {
                    createActivity();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void createActivity() throws ParseException {
        String activity_url = MyConfigUtil.URL + "activity/create";
        JSONObject jsonObject = new JSONObject();
        ActivityForm activityForm = new ActivityForm();
        ExtActivity extActivity = new ExtActivity();
        List<ExtActivityUser>extActivityUserList = new ArrayList<>();
        for (Search search : dataList) {
            if (search.isChecked()){
                ExtActivityUser extActivityUser = new ExtActivityUser();
                extActivityUser.setStudentId(Integer.parseInt(search.getNo()));
                extActivityUser.setVote(0);
                extActivityUserList.add(extActivityUser);
            }
        }
        extActivity.setActivityName(etActivityName.getText().toString());
        extActivity.setVotes(Integer.parseInt(etActivityName.getText().toString()));
        extActivity.setNum(Integer.parseInt(etActivityName.getText().toString()));
        extActivity.setCreateUser(extStudent.getStudentId());
        extActivity.setJoinGrade(extStudent.getGradeId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        extActivity.setStartTime(sdf.parse(tvStartTime.getText().toString()));
        extActivity.setEndTime(sdf.parse(tvEndTime.getText().toString()));

        jsonObject.put("extActivity",extActivity);
        jsonObject.put("extActivityUserList", extActivityUserList);
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
                        Toast.makeText(getApplicationContext(), "添加活动成功", Toast.LENGTH_SHORT).show();
                        AddActivityActivity.this.finish();
                    } else {
                        Toast.makeText(getApplicationContext(), map.get("text").toString(), Toast.LENGTH_SHORT).show();
                    }
                    Looper.loop();

                }
            }
        });
    }


    /**
     * 获取学生列表
     */
    public void getStudentList() {
        String activity_url = MyConfigUtil.URL + "student/list";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("gradeId", extStudent.getGradeId());
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
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Map<String, Object> map = FastJsonTools.getMap(result);
                    String data = map.get("status").toString();
                    if (data.equals("00000")) {
                        extStudentList= FastJsonTools.getBeans(map.get("t").toString(), ExtStudent.class);
                        for (ExtStudent student : extStudentList) {
                            Search search = new Search(student.getStudentName(),false,student.getStudentId().toString());
                            dataList.add(search);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), map.get("text").toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    /**
     * 年月日选择
     */
    private void showYearMonthDayPicker(final int menu) {
        Calendar calendar = Calendar.getInstance();
        BasisTimesUtils.showDatePickerDialog(this, BasisTimesUtils.THEME_DEVICE_DEFAULT_DARK, "请选择年月日", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), new BasisTimesUtils.OnDatePickerListener() {

            @Override
            public void onConfirm(int year, int month, int dayOfMonth) {
                if (menu == START_TIME_MENU){
                    tvStartTime.setText(year + "年"+month + "月"+ dayOfMonth + "日");
                }else {
                    tvEndTime.setText(year + "年"+month + "月"+ dayOfMonth + "日");
                }
            }

            @Override
            public void onCancel() {
            }
        });
    }
}
