package com.example.test1;

import com.google.gson.annotations.SerializedName;

public class LoginRes {
    @SerializedName("result")
    public String result;

    @SerializedName("token")
    public String token;

    @Override
    public String toString() {
        return "PostResult{" +
                "result=" + result +
                ", token='" + token + '\'' +
                '}';
    }
}
