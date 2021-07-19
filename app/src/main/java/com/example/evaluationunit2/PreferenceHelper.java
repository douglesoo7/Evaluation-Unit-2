package com.example.evaluationunit2;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private static final String SHARED_PREFERENCE_KEY="com.example.evaluationunit2";

    public static SharedPreferences getSharedPreference(Context context){
        return context.getSharedPreferences(SHARED_PREFERENCE_KEY,Context.MODE_PRIVATE);
    }

    public static void writeUserNameToPreference(Context context, String key, String value){
        SharedPreferences.Editor editor= getSharedPreference(context).edit();
        editor.putString(key,value);
        editor.apply();
    }

    public static void writePasswordToPreference(Context context,String key, String value){
        SharedPreferences.Editor editor=getSharedPreference(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void checked(Context context,String key, boolean value){
        SharedPreferences.Editor editor=getSharedPreference(context).edit();
        editor.putBoolean(key,value);
        editor.apply();
    }

    public static boolean isChecked(Context context,String key){
        return getSharedPreference(context).getBoolean(key,false);
    }
}
