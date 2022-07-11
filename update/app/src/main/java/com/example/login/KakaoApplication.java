package com.example.login;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    private String token;
    private String name;
    @Override
    public void onCreate(){
        token = "";
        name = "";
        KakaoSdk.init(this, "b69ceb8f338be08dd833fec279f388d6");
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
