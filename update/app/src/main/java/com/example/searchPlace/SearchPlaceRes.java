package com.example.searchPlace;

import com.google.gson.annotations.SerializedName;

import kotlinx.parcelize.Parcelize;

@Parcelize
public class SearchPlaceRes {
    @SerializedName("avgstar")
    public String AvgStar;
    @SerializedName("PicturePath")
    public String PicturePath;
    @SerializedName("PlaceName")
    public String PlaceName;

    @Override
    public String toString() {
        return "{" +
                "Name=" + AvgStar +
                ", PicturePath ='" + PicturePath + '\'' +
                ", PlaceName ='" + PlaceName + '\'' +
                '}';
    }
}