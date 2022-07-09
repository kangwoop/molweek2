package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class postResponse {
    @SerializedName("result")
    public String result;

    @SerializedName("userId")
    public int userId;

    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("body")
    public String bodyValue;

    @Override
    public String toString() {
        return "PostResult{" +
                "result=" + result +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", bodyValue='" + bodyValue + '\'' +
                '}';
    }
}
