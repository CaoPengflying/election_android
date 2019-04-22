package com.example.zzc.election_application;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.zzc.extModel.ExtStudent;
import com.example.zzc.model.Student;
import com.example.zzc.util.FastJsonTools;
import com.example.zzc.util.GetUser;
import com.example.zzc.util.MyConfigUtil;
import com.example.zzc.util.PhoneFormatCheckUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class LoginActivity extends Activity implements View.OnClickListener {
    private ImageView Im_back_AtLogin;
    private EditText et_phone_AtLogin;
    private TextView tv_enter_AtLogin;
    private TextView tv_toas_Atlogin;
    private EditText et_password_AtLogin;
    private OkHttpClient mOkHttpClient = new OkHttpClient();
    private SharedPreferences sp;
    private Student oldUser;
    private Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sp = getSharedPreferences("user", MODE_PRIVATE);

        init();
        oldUser = new GetUser(this).getinfo();
        if(!(oldUser.getStudentId()==0)){
            et_password_AtLogin.setText(oldUser.getStudentPassword());
            et_phone_AtLogin.setText(oldUser.getStudentNo());
        }
    }

    private void init() {
        Im_back_AtLogin = (ImageView) findViewById(R.id.Im_back_AtLogin);
        et_phone_AtLogin = (EditText) findViewById(R.id.et_phone_AtLogin);
        tv_enter_AtLogin = (TextView) findViewById(R.id.tv_enter_AtLogin);
        tv_toas_Atlogin = (TextView) findViewById(R.id.tv_toas_Atlogin);
        et_password_AtLogin = (EditText) findViewById(R.id.et_password_AtLogin);

        Im_back_AtLogin.setOnClickListener(this);
        tv_enter_AtLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Im_back_AtLogin:
                this.finish();
                break;
            case R.id.tv_enter_AtLogin:
                send();
                break;

        }
    }

    private void send() {
        if(et_password_AtLogin.getText().toString().equals("")||et_phone_AtLogin.getText().toString().equals("")){
            tv_toas_Atlogin.setText("学生号和密码不能为空");
            return;
        }
        String activity_url = MyConfigUtil.URL + "student/login";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("studentNo", et_phone_AtLogin.getText());
        jsonObject.put("studentPassword", et_password_AtLogin.getText());
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
                    if (data.equals("00000")) {
                        ExtStudent extStudent =  FastJsonTools.getBean(map.get("t").toString(), ExtStudent.class);
                        editor = sp.edit();
//					if(user.getType()==null||user.getAreaID()==null||user.getId()==null||user.getAreaName()==null
//							||user.getName()==null||user.getPassword()==null||user.getPhone()==null||user.getHeader()==null
//							||user.getPoliceID()==null||user.getPoliceName()==null||user.getIsDelete()==null){
//						return;
//					}
                        editor.putInt("studentId", extStudent.getStudentId());
                        editor.putString("studentNo", extStudent.getStudentNo());
                        editor.putString("studentPassword", extStudent.getStudentPassword());
                        editor.putString("studentName",extStudent.getStudentName());
                        editor.putString("headUrl", extStudent.getHeadUrl());
                        editor.putInt("role", extStudent.getRole());
                        editor.putInt("gradeId",extStudent.getGradeId());
                        editor.putString("gradeName",extStudent.getStudentName());
                        editor.commit();

                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    } else {
                        Looper.prepare();
                        Toast.makeText(getApplicationContext(), map.get("text").toString(), Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }
            }
        });
    }
}
