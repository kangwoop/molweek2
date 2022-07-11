package com.example.test1;

import com.google.gson.annotations.SerializedName;
import kotlinx.parcelize.Parcelize;

@Parcelize
public class RankRes {
    @SerializedName("PlaceName")
    public String PlaceName;

    @SerializedName("PicturePath")
    public String PicturePath;

    @SerializedName("Information")
    public String Information;

    @SerializedName("star")
    public String star;

    @SerializedName("Posting_date")
    public String Posting_date;

    @Override
    public String toString() {
        return "{" +
                "PlaceName=" + PlaceName +
                "Information=" + Information +
                "star=" + star +
                "Posting_date=" + Posting_date +
                ", PicturePath ='" + PicturePath + '\'' +
                '}';
    }
}

