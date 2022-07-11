package com.example.searchcountry;

public class SearchCountryData {
    String imagePath;
    String countryName;
    String placeList;
    public String getImagePath(){
        return imagePath;
    }
    public String getCountryName(){
        return countryName;
    }
    public String getPlaceList(){
        return placeList;
    }
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }
    public void setCountryName(String countryName){
        this.countryName = countryName;
    }
    public void setPlaceList(String placeList){
        this.placeList = placeList;
    }
    public SearchCountryData(String imagePath,String countryName,String placeList){
        this.countryName = countryName;
        this.placeList = placeList;
        this.imagePath = imagePath;
    }
}