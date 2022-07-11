package com.example.test1;

import com.example.place.PlaceRes;
import com.example.place.PostingInPlaceRes;
import com.example.searchPlace.SearchPlaceRes;
import com.example.searchcountry.SearchRes;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPIInterface {
    @FormUrlEncoded
    @POST("{path}")
    Call<LoginRes> LoginReqFunc(@Path("path") String path ,@FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("{path}")
    Call<LoginRes> JoinReqFunc(@Path("path") String path ,@FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("{path}")
    Call<ArrayList<RankRes>> RankReqFunc(@Path("path") String path , @FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("{path}")
    Call<ArrayList<SearchRes>> CountryReqFunc(@Path("path") String path , @FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("{path}")
    Call<ArrayList<SearchPlaceRes>> SearchPlaceReqFunc(@Path("path") String path , @FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("{path}")
    Call<ArrayList<PostingInPlaceRes>> PostingInPlaceReqFunc(@Path("path") String path , @FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("{path}")
    Call<PlaceRes> PlaceFunc(@Path("path") String path , @FieldMap HashMap<String, Object> param);
}
