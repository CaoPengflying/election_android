package com.example.zzc.util;

import android.app.Application;
import android.content.Context;

/**
 * ��ȡ��ǰӦ�õ�������
 * @author Administrator
 *
 */
public class MyApplication extends Application {

	    private static Context context; 

	    public void onCreate(){ 
	        super.onCreate(); 
	        MyApplication.context = getApplicationContext(); 
	    } 

	    public static Context getAppContext() { 
	        return MyApplication.context; 
	    } 
	    
	    
}

