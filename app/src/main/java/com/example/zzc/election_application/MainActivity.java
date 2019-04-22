package com.example.zzc.election_application;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zzc.extModel.ExtStudent;
import com.example.zzc.fragment.ActivityListFragment;
import com.example.zzc.fragment.MineInfoFragment;
import com.example.zzc.util.GetUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private LinearLayout ll_activity_list, ll_mine_info;
    private TextView tv_activity_list, tv_mine_info;
    private ImageView iv_activity_list, iv_mine_info;
    private ExtStudent user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = new GetUser(this).getinfo();
        init();
    }

    /**
     * 初始化主页的控件
     */
    private void init() {
        if (0 == user.getStudentId()){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
        //首页
        ll_activity_list = (LinearLayout) findViewById(R.id.ll_activity_list_main);
        tv_activity_list = (TextView) findViewById(R.id.tv_activity_list_main);
        iv_activity_list = (ImageView) findViewById(R.id.iv_activity_list_main);
        //我的
        ll_mine_info = (LinearLayout) findViewById(R.id.ll_mine_info_main);
        tv_mine_info = (TextView) findViewById(R.id.tv_mine_info_main);
        iv_mine_info = (ImageView) findViewById(R.id.iv_mine_info_main);

        //为下面的导航栏添加点击事件
        ll_activity_list.setOnClickListener(this);
        ll_mine_info.setOnClickListener(this);

        //获取导航栏Fragment中控件
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.replace(R.id.fragment_main, new ActivityListFragment());
        beginTransaction.commit();

    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        //初始化导航栏所有的Fragment
        ActivityListFragment activityListFragment = new ActivityListFragment();
        MineInfoFragment mineInfoFragment = new MineInfoFragment();
        switch (v.getId()) {
            case R.id.ll_activity_list_main:
                changeSelected(0);
                beginTransaction.replace(R.id.fragment_main, activityListFragment);
                break;
            case R.id.ll_mine_info_main:
                changeSelected(2);
                beginTransaction.replace(R.id.fragment_main, mineInfoFragment);
                break;
        }
        beginTransaction.commit();

    }

    /**
     * 更改首页导航栏的图标颜色 文字颜色
     *
     * @param i 0：活动列表   2我的
     */
    private void changeSelected(int i) {
        Drawable home, mine, home_selected, mine_selected;
        List<Drawable> drawables = new ArrayList<>();
        //获取首页的图片
        home = getResources().getDrawable(R.drawable.homeing);
        home.setBounds(0, 0, home.getMinimumWidth(), home.getMinimumHeight());

        //获取我的图片
        mine = getResources().getDrawable(R.drawable.personing);
        mine.setBounds(0, 0, mine.getMinimumWidth(), mine.getMinimumHeight());

        //获取首页被选中的图片
        home_selected = getResources().getDrawable(R.drawable.homeed);
        home_selected.setBounds(0, 0, home_selected.getMinimumWidth(), home_selected.getMinimumHeight());
        //获取我被选中的图片
        mine_selected = getResources().getDrawable(R.drawable.personed);
        mine_selected.setBounds(0, 0, mine_selected.getMinimumWidth(), mine_selected.getMinimumHeight());
        drawables.add(home);
        drawables.add(mine);
        drawables.add(home_selected);
        drawables.add(mine_selected);
        switch (i) {
            case 0:
                setDefault(drawables);
                iv_activity_list.setImageDrawable(home);
                tv_activity_list.setTextColor(getResources().getColor(R.color.blue));
                break;

            case 2:
                setDefault(drawables);
                iv_mine_info.setImageDrawable(mine);
                tv_mine_info.setTextColor(getResources().getColor(R.color.blue));
                break;
        }

    }

    private void setDefault(List<Drawable> drawables) {
        iv_activity_list.setImageDrawable(drawables.get(2));
        iv_mine_info.setImageDrawable(drawables.get(3));

        tv_activity_list.setTextColor(getResources().getColor(R.color.gray));
        tv_mine_info.setTextColor(getResources().getColor(R.color.gray));
    }
}
