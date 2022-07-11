package com.example.test1;

public class RankData {
    String imagePath;
    String PlaceName;
    public String getImagePath(){
        return imagePath;
    }
    public String getPlaceName(){
        return PlaceName;
    }
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }
    public void setPlaceName(String placeName){ this.PlaceName = placeName; }
    public RankData(String imagePath, String placeName){
        this.PlaceName = placeName;
        this.imagePath = imagePath;
    }
}
