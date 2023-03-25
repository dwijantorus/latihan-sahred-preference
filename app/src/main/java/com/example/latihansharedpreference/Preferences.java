package com.example.latihansharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Preferences {
    SharedPreferences pref;
    Editor editor;
    private final String sessionLogin= "session_login";
    private final String token= "token";

    public Preferences(Context context) {
        pref = context.getSharedPreferences("MyPref", context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setSessionLogin(boolean value) {
        editor.putBoolean(sessionLogin, value);
        editor.commit();
    }

    public boolean getSessionLogin() {
        return pref.getBoolean(sessionLogin, false);
    }

    public void setToken(String value) {
        editor.putString(token, value);
        editor.commit();
    }

    public String getToken() {
        return pref.getString(token, "");
    }

}
