package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.login.KakaoApplication;
import com.example.login.LoginActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class WriteActivity extends AppCompatActivity {

    RatingBar ratingBar;
    EditText contentsInput;
    String Name;
    String Token;

    private Retrofit retrofit;
    private RetrofitAPIInterface service;
    private static final String Tag = "WriteActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        retrofit = ((MainActivity) MainActivity.context_main).retrofit;
        service = ((MainActivity) MainActivity.context_main).service;

        KakaoApplication myApp = (KakaoApplication)getApplicationContext();
        Name = myApp.getName();
        Token = myApp.getToken();

        ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        contentsInput = (EditText) findViewById(R.id.contentsinput);
        Button saveButton = (Button) findViewById(R.id.savebutton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contents = contentsInput.getText().toString();
                int ratingbarupdate = (int) ratingBar.getRating();

                String place = getIntent().getStringExtra("placename");

                HashMap<String, Object> param = new HashMap<>();
                param.put("Htoken",Token);
                param.put("star", ratingbarupdate);
                param.put("name", Name);
                param.put("Posting", contents);
                param.put("PlaceName", place);

                Call<LoginRes> call_post = service.LoginReqFunc("write",param);
                call_post.enqueue(new Callback<LoginRes>() {
                    @Override
                    public void onResponse(Call<LoginRes> call, retrofit2.Response<LoginRes> response) {
                        if(response.isSuccessful()){
                            Log.v(Tag,"success = " + String.valueOf(response.code()));
                        }
                        else{
                            Log.v(Tag,"error = " + String.valueOf(response.code()));
                            Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginRes> call, Throwable t) {
                        Log.v(Tag,"Fail");
                        Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}