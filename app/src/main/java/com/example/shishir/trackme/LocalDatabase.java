package com.example.shishir.trackme;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Shishir on 11/13/2017.
 */

public class LocalDatabase {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public LocalDatabase(Context context) {
        sharedPreferences = context.getSharedPreferences("MY_PREFER_SAVE_ME", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setInterVal(String interVal) {
        editor.putString("interval", interVal).commit();
    }

    public String getInterval() {
        return sharedPreferences.getString("interval", "");
    }

    public void setServiceRunning(boolean b) {
        editor.putBoolean("running", b).commit();
    }

    public boolean serviceIsRunning() {
        return sharedPreferences.getBoolean("running", false);
    }

    public void setOnboarding(boolean visited) {
        editor.putBoolean("visited", visited).commit();
    }

    public boolean OnBoardingIsVisited() {
        return sharedPreferences.getBoolean("visited", false);
    }

    public void setMessageFormat(String smsFormat) {
        editor.putString("sms", smsFormat).commit();
    }

    public String getSmsFormat() {
        return sharedPreferences.getString("sms", "");
    }

    public void setContactOne(String contactOne) {
        editor.putString("c1",contactOne).commit();
    }

    public void setContactTwo(String contactTwo) {
        editor.putString("c2",contactTwo).commit();
    }
    public String getContactOne(){
        return sharedPreferences.getString("c1","");
    }
    public String getContactTwo(){
        return sharedPreferences.getString("c2","");
    }
}
