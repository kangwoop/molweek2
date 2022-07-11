package com.example.searchcountry;

import com.google.gson.annotations.SerializedName;

import kotlinx.parcelize.Parcelize;

@Parcelize
public class SearchRes {
    @SerializedName("Name")
    public String Name;

    @SerializedName("PicturePath")
    public String PicturePath;
    @SerializedName("placenames")
    public String placenames;

    @Override
    public String toString() {
        return "{" +
                "Name=" + Name +
                ", PicturePath ='" + PicturePath + '\'' +
                ", placenames ='" + placenames + '\'' +
                '}';
    }
}