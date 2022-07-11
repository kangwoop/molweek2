package com.example.searchPlace;

public class SearchPlaceData {
    String imagePath;
    String PlaceName;
    double AvgStar;
    boolean isEmpty;
    public String getImagePath(){
        return imagePath;
    }
    public String getPlaceName(){
        return PlaceName;
    }
    public double getAvgStar(){
        return AvgStar;
    }
    public boolean getisEmpty() { return isEmpty;}
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }
    public void setPlaceName(String placeName){ this.PlaceName = placeName; }
    public void setAvgStar(double avgstar){
        this.AvgStar = avgstar;
    }
    public void setIsEmpty(boolean isEmpty){ this.isEmpty = isEmpty;}
    public SearchPlaceData(String imagePath,String placeName,double avgstar, boolean isEmpty){
        this.PlaceName = placeName;
        this.AvgStar = avgstar;
        this.imagePath = imagePath;
        this.isEmpty = isEmpty;
    }
}
