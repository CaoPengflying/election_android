package com.example.zzc.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zzc.election_application.LoginActivity;
import com.example.zzc.election_application.MainActivity;
import com.example.zzc.election_application.R;
import com.example.zzc.extModel.ExtStudent;
import com.example.zzc.util.GetUser;


public class MineInfoFragment extends Fragment implements View.OnClickListener {
    private TextView tvStudentNo, tvGradeName, tvStudentName, tvRole, tvExit;
    private ExtStudent user;
    private SharedPreferences sp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine_info, container, false);
        user = new GetUser(getActivity().getApplicationContext()).getinfo();
        init(view);
        return view;
    }

    /**
     * 初始化控件
     * @param view
     */
    private void init(View view) {
        tvStudentNo = (TextView) view.findViewById(R.id.tv_studentNo_FrPerson);
        tvStudentName = (TextView) view.findViewById(R.id.tv_name_FrPerson);
        tvRole = (TextView) view.findViewById(R.id.tv_role_AtMineInfo);
        tvGradeName = (TextView) view.findViewById(R.id.tv_gradeName_AtMineInfo);
        tvExit = (TextView) view.findViewById(R.id.tv_exit_AtLogin);

        tvStudentNo.setText(user.getStudentNo());
        tvGradeName.setText(user.getGradeName());
        if (user.getRole().equals(1)){
            tvRole.setText("班干");
        }else {
            tvRole.setText("学生");
        }
        tvStudentName.setText(user.getStudentName());
        tvExit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_exit_AtLogin:
                sp = getActivity().getApplication().getSharedPreferences("user",getActivity().getApplication().MODE_PRIVATE);
                sp.edit().clear().commit();
                Intent intent = new Intent(getActivity().getApplicationContext(),LoginActivity.class);
                startActivity(intent);
        }
    }
}
