package com.example.test1;

import java.util.ArrayList;
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

public interface RetrofitAPIInterface {
    @FormUrlEncoded
    @POST("{path}")
    Call<LoginRes> LoginReqFunc(@Path("path") String path ,@FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("{path}")
    Call<LoginRes> JoinReqFunc(@Path("path") String path ,@FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("{path}")
    Call<ArrayList<SearchRes>> CountryReqFunc(@Path("path") String path , @FieldMap HashMap<String, Object> param);

}
