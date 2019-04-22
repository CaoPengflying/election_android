package com.example.zzc.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.zzc.extModel.ExtStudent;
import com.example.zzc.model.Student;

public class GetUser {
	private Context context;

	private SharedPreferences sp; 

	public GetUser(Context context){
		this.context = context;
	}
	
	public ExtStudent getinfo(){
		sp = context.getSharedPreferences("user",context.MODE_PRIVATE);
		ExtStudent user = new ExtStudent();
		user.setGradeId(sp.getInt("gradeId", 0));
		user.setGradeName(sp.getString("gradeName",null));
		user.setRole(sp.getInt("role", 0));
		user.setStudentName(sp.getString("studentName", null));
		user.setStudentId(sp.getInt("studentId", 0));
		user.setStudentPassword(sp.getString("password", null));
		user.setStudentNo(sp.getString("studentNo", null));
		user.setHeadUrl(sp.getString("headUrl", null));
		return user;
		
	}
			
}
