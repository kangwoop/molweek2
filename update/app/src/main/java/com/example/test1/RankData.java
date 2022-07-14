package com.example.test1;

public class RankData {
    String imagePath;
    String PlaceName;
    int avgstar;
    String postingdate;
    public String getImagePath(){
        return imagePath;
    }
    public String getPlaceName(){
        return PlaceName;
    }
    public int getavgstar(){
        return avgstar;
    }
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }
    public void setPlaceName(String placeName){ this.PlaceName = placeName; }
    public void setavgstar(int avgstar){ this.avgstar = avgstar; }
    public RankData(String imagePath, String placeName, int avgstar){
        this.imagePath = imagePath;
        this.PlaceName = placeName;
        this.avgstar = avgstar;
    }
}
