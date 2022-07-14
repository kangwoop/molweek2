package com.example.place;


import com.google.gson.annotations.SerializedName;

import kotlinx.parcelize.Parcelize;

@Parcelize
public class PlaceRes {
    @SerializedName("PlaceName")
    public String PlaceName;
    @SerializedName("Country")
    public String Country;
    @SerializedName("Picturepath")
    public String Picturepath;
    @SerializedName("Information")
    public String Information;
    @SerializedName("Address")
    public String Address;

    @Override
    public String toString() {
        return "{" +
                "PlaceName=" + PlaceName +
                ", Country ='" + Country + '\'' +
                ", Picturepath ='" + Picturepath + '\'' +
                ", Information ='" + Information + '\'' +
                ", Address ='" + Address + '\'' +
                '}';
    }
}
