package com.example.place;

import com.google.gson.annotations.SerializedName;

import kotlinx.parcelize.Parcelize;

@Parcelize
public class PostingInPlaceRes {
    @SerializedName("name")
    public String name;
    @SerializedName("star")
    public String Star;
    @SerializedName("Posting")
    public String Posting;
    @SerializedName("Posting_date")
    public String Posting_date;

    @Override
    public String toString() {
        return "{" +
                "name=" + name +
                ", star ='" + Star + '\'' +
                ", Posting ='" + Posting + '\'' +
                ", Posting_date ='" + Posting_date + '\'' +
                '}';
    }
}