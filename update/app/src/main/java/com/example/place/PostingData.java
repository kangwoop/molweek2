package com.example.place;

public class PostingData {
    String Posting_date;
    int star;
    String Posting;

    public int getStar() {
        return star;
    }

    public String getPosting() {
        return Posting;
    }

    public String getPosting_date() {
        return Posting_date;
    }

    public void setPosting(String posting) {
        Posting = posting;
    }

    public void setPosting_date(String posting_date) {
        Posting_date = posting_date;
    }

    public void setStar(int star) {
        this.star = star;
    }
    public PostingData(String posting,String posting_date,int star){
        this.Posting = posting;
        this.Posting_date = posting_date;
        this.star = star;
    }
}
