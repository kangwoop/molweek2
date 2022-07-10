package com.example.searchPlace;

public class SearchPlaceData {
    String imagePath;
    String PlaceName;
    int AvgStar;
    public String getImagePath(){
        return imagePath;
    }
    public String getPlaceName(){
        return PlaceName;
    }
    public int getAvgStar(){
        return AvgStar;
    }
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }
    public void setPlaceName(String placeName){ this.PlaceName = placeName; }
    public void setAvgStar(int avgstar){
        this.AvgStar = avgstar;
    }
    public SearchPlaceData(String imagePath,String placeName,int avgstar){
        this.PlaceName = placeName;
        this.AvgStar = avgstar;
        this.imagePath = imagePath;
    }
}
