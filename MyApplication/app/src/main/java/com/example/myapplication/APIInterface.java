package com.example.myapplication;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("/retrofit/get")
    Call<ResponseBody> getFunc(@Query("data") String data);

    @FormUrlEncoded
    @POST("{path}")
    Call<postResponse> postFunc(@Path("path") String path ,@FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @PUT("/retrofit/put/{id}")
    Call<ResponseBody> putFunc(@Path("id") String id, @Field("data") String data);

    @DELETE("/retrofit/delete/{id}")
    Call<ResponseBody> deleteFunc(@Path("id") String id);

}
