package com.example.login;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        KakaoSdk.init(this, "b69ceb8f338be08dd833fec279f388d6");
    }
}
