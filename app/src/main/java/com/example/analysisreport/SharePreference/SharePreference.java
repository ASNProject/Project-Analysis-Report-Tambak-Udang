package com.example.analysisreport.SharePreference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreference {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int private_mode = 0;
    private static final String PREF_NAME= "TambakUdang";


    public SharePreference(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, private_mode);
        editor = pref.edit();
    }

    public void setDatas (String datas){
        editor.putString("datas", datas);
        editor.commit();
    }
    public String getDatas (){
        return pref.getString("datas", null);
    }

    public void setDetailkolam (String detailair){
        editor.putString("detailair", detailair);
        editor.commit();
    }
    public String getDetailkolam (){
        return pref.getString("detailair", null);
    }
    public void setDetailpakan (String detailpakan){
        editor.putString("detailpakan", detailpakan);
        editor.commit();
    }
    public String getDetailpakan (){
        return pref.getString("detailpakan", null);
    }

    public void setEmail (String email){
        editor.putString("email", email);
        editor.commit();
    }
    public String getEmail (){
        return pref.getString("email", null);
    }

    public void setPassword (String password){
        editor.putString("password", password);
        editor.commit();
    }
    public String getPassword (){
        return pref.getString("password", null);
    }

}
