package com.example.analysisreport.SharePreference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreference {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int private_Mode = 0;
    private static final String PREF_NAME= "Database";

    public SharePreference (Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, private_Mode);
        editor = pref.edit();
    }

    public void setKolam (String kolam){
        editor.putString("kolam", kolam);
        editor.commit();
    }

    public String getKolam()
    {
        return pref.getString("kolam", null);
    }

}
