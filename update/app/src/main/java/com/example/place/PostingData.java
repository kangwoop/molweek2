package com.example.place;

public class PostingData {
    String Posting_date;
    int star;
    String Posting;
    String name;
    public int getStar() {
        return star;
    }

    public String getPosting() {
        return Posting;
    }

    public String getPosting_date() {
        return Posting_date;
    }

    public String getName(){ return name;}

    public void setPosting(String posting) {
        Posting = posting;
    }

    public void setPosting_date(String posting_date) {
        Posting_date = posting_date;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public void setName(String name){ this.name = name; }

    public PostingData(String posting,String posting_date,int star,String name){
        this.Posting = posting;
        this.Posting_date = posting_date;
        this.star = star;
        this.name = name;
    }
}
