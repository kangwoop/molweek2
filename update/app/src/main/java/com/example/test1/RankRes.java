package com.example.test1;

import com.google.gson.annotations.SerializedName;
import kotlinx.parcelize.Parcelize;

@Parcelize
public class RankRes {
    @SerializedName("PlaceName")
    public String PlaceName;

    @SerializedName("PicturePath")
    public String PicturePath;

    @Override
    public String toString() {
        return "{" +
                "PlaceName=" + PlaceName +
                ", PicturePath ='" + PicturePath + '\'' +
                '}';
    }
}

