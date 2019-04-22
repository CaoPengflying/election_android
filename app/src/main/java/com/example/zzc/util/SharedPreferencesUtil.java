package com.example.zzc.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/**
 * SharedPreference存取数据工具
 * @author Administrator：mhm 2016.06.01
 *
 */
public class SharedPreferencesUtil {
	public static SharedPreferences settings = PreferenceManager  
            .getDefaultSharedPreferences(MyApplication.getAppContext());
	 /*
     * 查看是否有这个键对应的值，返回boolean 
     */
      public static boolean myHasKey(final SharedPreferences preferences,final String key) {  
          return preferences.contains(  
                  key);  
      }  
	/*
     * 根据键获取int型的值，自定义方法可另创文件)
     */
      public static int getMyPrefInt(final SharedPreferences preferences,final String key,  
              final int defaultValue) {
          return preferences.getInt(key, defaultValue);  
      }  
      /*
       * 根据键和值写入int型的值自定义方法可另创文件)
       */
        public static void setMyPrefInt(final SharedPreferences preferences,final String key,  
                final int value) {  
        	preferences.edit().putInt(key, value).commit();  
        }  
	
	/*
	 * 根据传入的键得到字符串，（自定义方法）
	 */
        public  static String getMyPrefString(final SharedPreferences preferences,final String key,  
                final String defaultValue) {  
            return preferences.getString(key, defaultValue);  
        }  
        /*
         * 根据传入的键得到字符串�?（自定义�?
         */
        public  static void setMyPrefString(final SharedPreferences preferences,final String key,  
        		final String value){  
        	preferences.edit().putString(key, value).commit();    
        }  
        /*
         * 根据传入的键得到字符串�?
         */
        public  static String getPrefString(String key,  
        		final String defaultValue) {  
        	return settings.getString(key, defaultValue);  
        }  
      /*
       * 传入键�?对的字符串，存入内存
       */
        public static void setPrefString(final String key,  
                final String value) {  
            settings.edit().putString(key, value).commit();  
        }  
      /*
       * 根据boolean键得到boolean�?
       */
        public static boolean getPrefBoolean(final String key,  
                final boolean defaultValue) {  
            return settings.getBoolean(key, defaultValue);  
        }  
      /*
       * 查看是否有这个键对应的�?，返回boolean�?
       */
        public static boolean hasKey(final String key) {  
            return settings.contains(  
                    key);  
        }  
      /*
       * 设置boolean值到内存中根据传入的键�?�?
       */
        public static void setPrefBoolean(final String key,  
                final boolean value) {  
            settings.edit().putBoolean(key, value).commit();  
        }  
      /*
       * 根据int型键值对写入值到内存
       */
        public static void setPrefInt(final String key,  
                final int value) {  
            settings.edit().putInt(key, value).commit();  
        }  
      /*
       * 根据键获取int型的�?
       */
        public static int getPrefInt(final String key,  
                final int defaultValue) {  
            return settings.getInt(key, defaultValue);  
        }  
      /*
       * 根据传入的浮点型数据键�?对存入内�?
       */
        public static void setPrefFloat(final String key,  
                final float value) {  
            settings.edit().putFloat(key, value).commit();  
        }  
        /*
         * 根据键名获取对应的浮点型数据
         */
        public static float getPrefFloat(final String key,  
                final float defaultValue) {  
             return settings.getFloat(key, defaultValue);  
        }  
        /*
         * 存入�?��长整型数据键值对
         */
        public static void setSettingLong(final String key,  
                final long value) {  
            settings.edit().putLong(key, value).commit();  
        }  
        /*
         * 根据键获取一个长整型数据
         */
        public static long getPrefLong(final String key,  
                final long defaultValue) {  
            return settings.getLong(key, defaultValue);  
        }  
        /*
         * 清空内存中的已有文件
         */
        public static void clearPreference(final SharedPreferences p){  
            final Editor editor = p.edit();  
            editor.clear();  
            editor.commit();  
        }  
        /*
         * 清空内存中的已有文件(自定�?
         */
        public static void myclearPreference(final SharedPreferences preferences){  
        	final Editor editor = preferences.edit();  
        	editor.clear();  
        	editor.commit();  
        }  

}
