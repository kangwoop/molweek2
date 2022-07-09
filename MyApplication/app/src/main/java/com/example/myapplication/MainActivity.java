package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Intent intent;
    private final String Tag = "MainActivityLog";
    private final String URL = "http://192.249.18.171:80";

    private Retrofit retrofit;
    private APIInterface service;

    private Button btn_get,btn_post,btn_delete, btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstInit();
        btn_get.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_post.setOnClickListener(this);
        btn_update.setOnClickListener(this);
    }

    private void firstInit() {
        btn_get = (Button) findViewById(R.id.GET);
        btn_post = (Button) findViewById(R.id.POST);
        btn_update = (Button) findViewById(R.id.UPDATE);
        btn_delete = (Button) findViewById(R.id.DELETE);
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APIInterface.class);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.GET:
                Call<ResponseBody> call_get = service.getFunc("get data");
                call_get.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            try{
                                String result  = response.body().string();
                                Log.v(Tag,"result = " + result);
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            Log.v(Tag,"error = " + String.valueOf(response.code()));
                            Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.v(Tag,"Fail");
                        Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.POST:
                HashMap<String, Object> param = new HashMap<>();
                param.put("var1", 1);
                param.put("var2", 2);
                Call<postResponse> call_post = service.postFunc("login" ,param);
                call_post.enqueue(new Callback<postResponse>() {
                    @Override
                    public void onResponse(Call<postResponse> call, Response<postResponse> response) {
                        if(response.isSuccessful()){
                            //String result  = response.body().string();
                            String result  = response.body().toString();
                            Log.v(Tag,"result = " + result);
                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Log.v(Tag,"error = " + String.valueOf(response.code()));
                            Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<postResponse> call, Throwable t) {
                        Log.v(Tag,"Fail");
                        Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.UPDATE:
                Call<ResponseBody> call_put = service.putFunc("board","put data");
                call_put.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            try{
                                String result  = response.body().string();
                                Log.v(Tag,"result = " + result);
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            Log.v(Tag,"error = " + String.valueOf(response.code()));
                            Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.v(Tag,"Fail");
                        Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.DELETE:
                Call<ResponseBody> call_delete = service.deleteFunc("board");
                call_delete.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                String result = response.body().string();
                                Log.v(Tag, "result = " + result);
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.v(Tag, "error = " + String.valueOf(response.code()));
                            Toast.makeText(getApplicationContext(), "error = " + String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.v(Tag, "Fail");
                        Toast.makeText(getApplicationContext(), "Response Fail", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                break;
        }
    }
}