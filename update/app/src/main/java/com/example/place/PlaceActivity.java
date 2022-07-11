package com.example.place;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.LoginActivity;
import com.example.searchPlace.SearchPlaceAdapter;
import com.example.searchPlace.SearchPlaceData;
import com.example.searchPlace.SearchPlaceRes;
import com.example.test1.HomeActivity;
import com.example.test1.LoginRes;
import com.example.test1.MainActivity;
import com.example.test1.R;
import com.example.test1.RetrofitAPIInterface;
import com.example.test1.databinding.ActivityPlaceBinding;
import com.example.test1.databinding.ActivityPlaceinfoBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PlaceActivity extends AppCompatActivity{
    MapView sView = null;
    private ActivityPlaceinfoBinding binding;
    private ArrayList<PostingData> dataList;
    private PostingAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private boolean heart = false;
    private Retrofit retrofit;
    private RetrofitAPIInterface service;
    private static final String Tag = "PlaceActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaceinfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String placename = getIntent().getStringExtra("placename");
        Log.i(Tag,"placename" + placename);
        retrofit = ((MainActivity) MainActivity.context_main).retrofit;
        service = ((MainActivity) MainActivity.context_main).service;
        HashMap<String, Object> param2 = new HashMap<>();
        param2.put("Place_name", placename);
        param2.put("Htoken", "$2a$10$OorQ/m8VtpEX8Xzg/zXzI.zkPfNRxpvegGcN1CWncwljw6FM9aTau");

        Call<LoginRes> call_post2 = service.LoginReqFunc("isfavorite",param2);
        call_post2.enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(Call<LoginRes> call, retrofit2.Response<LoginRes> response) {
                if(response.isSuccessful()){
                    String result  = response.body().result;
                    Log.v(Tag,"result = " + result);
                    Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                    if(result.equals("true")) {
                        heart = true;
                        binding.heart.setImageResource(R.drawable.heart);
                    }
                    else {
                        heart = false;
                        binding.heart.setImageResource(R.drawable.emptyheart);
                    }
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


    HashMap<String, Object> param = new HashMap<>();
        param.put("Place_name",placename);
        linearLayoutManager = new LinearLayoutManager(this);
        binding.placerecycler.setLayoutManager(linearLayoutManager);
        dataList = new ArrayList<>();
        adapter = new PostingAdapter(dataList);
        binding.placerecycler.setAdapter(adapter);
        binding.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> param3 = new HashMap<>();
                param3.put("Place_name", placename);
                param3.put("Htoken", "$2a$10$OorQ/m8VtpEX8Xzg/zXzI.zkPfNRxpvegGcN1CWncwljw6FM9aTau");

                Call<LoginRes> call_post3 = service.LoginReqFunc("checkfavorite",param3);
                call_post3.enqueue(new Callback<LoginRes>() {
                    @Override
                    public void onResponse(Call<LoginRes> call, retrofit2.Response<LoginRes> response) {
                        if(response.isSuccessful()){
                            String result  = response.body().toString();
                            Log.v(Tag,"result = " + result);
                        }
                        else{
                            Log.v(Tag,"error = " + String.valueOf(response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginRes> call, Throwable t) {
                        Log.v(Tag,"Fail");
                        Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
                    }
                });
                if (heart) {
                    binding.heart.setImageResource(R.drawable.emptyheart);
                    heart = false;
                } else {
                    binding.heart.setImageResource(R.drawable.heart);
                    heart = true;
                }
            }
        });
        //버튼 설정해야댐
        Call<PlaceRes> call_post = service.PlaceFunc("places",param);
        call_post.enqueue(new Callback<PlaceRes>() {
            @Override
            public void onResponse(Call<PlaceRes> call, Response<PlaceRes> response) {
                if(response.isSuccessful()){
                    PlaceRes result = response.body();
                    Log.v(Tag, "place result : " + result.toString());
                    //현재 placeres에 text그림 다 추가해보자
                    binding.address.setText(result.Address);
                    binding.countryplace.setText(result.Country);
                    binding.Placesname.setText(result.PlaceName);
                    binding.information.setText(result.Information);
                    //place사진들 넣기 // 생각해보니까 이거 뷰페이져?
                    binding.placespicture.setImageResource(R.drawable.heart);
                    HashMap<String, Object> param1 = new HashMap<>();
                    param1.put("Place_name",placename);
                    Call<ArrayList<PostingInPlaceRes>> call_post2 = service.PostingInPlaceReqFunc("sitereviewlist",param1);
                    call_post2.enqueue(new Callback<ArrayList<PostingInPlaceRes>>() {
                        @Override
                        public void onResponse(Call<ArrayList<PostingInPlaceRes>> call, Response<ArrayList<PostingInPlaceRes>> response) {
                            if(response.isSuccessful()){
                                ArrayList<PostingInPlaceRes> result = response.body();
                                Log.v(Tag, "search result : " + result.toString());
                                for(int i = 0; i < result.size(); i++){
                                    PostingData data = new PostingData(result.get(i).Posting,result.get(i).Posting_date, Integer.parseInt(result.get(i).Star),result.get(i).name);
                                    dataList.add(data);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                            else{
                                Log.v(Tag,"error = " + String.valueOf(response.code()));
                                Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<PostingInPlaceRes>> call, Throwable t) {
                            Log.v(Tag,"Fail in search");
                            Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                else{
                    Log.v(Tag,"error = " + String.valueOf(response.code()));
                    Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PlaceRes> call, Throwable t) {
                Log.v(Tag,"Fail in search");
                Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
