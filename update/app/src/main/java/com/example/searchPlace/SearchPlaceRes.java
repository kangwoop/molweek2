package com.example.searchPlace;

import com.google.gson.annotations.SerializedName;

import kotlinx.parcelize.Parcelize;

@Parcelize
public class SearchPlaceRes {
    @SerializedName("avgstar")
    public String avgStar;
    @SerializedName("PicturePath")
    public String PicturePath;
    @SerializedName("PlaceName")
    public String PlaceName;
    @SerializedName("favorite")
    public String favorite;

    @Override
    public String toString() {
        return "{" +
                "Name=" + avgStar +
                ", PicturePath ='" + PicturePath + '\'' +
                ", PlaceName ='" + PlaceName + '\'' +
                '}';
    }
}