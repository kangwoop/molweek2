package com.example.place;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.searchPlace.SearchPlaceAdapter;
import com.example.searchPlace.SearchPlaceData;
import com.example.searchPlace.SearchPlaceRes;
import com.example.test1.MainActivity;
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

public class PlaceActivity extends AppCompatActivity implements GoogleMap.OnInfoWindowClickListener{
    MapView sView = null;
    private ActivityPlaceinfoBinding binding;
    private ArrayList<PostingData> dataList;
    private PostingAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private Retrofit retrofit;
    private RetrofitAPIInterface service;
    private static final String Tag = "PlaceActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaceinfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String placename = getIntent().getStringExtra("placename");
        retrofit = ((MainActivity) MainActivity.context_main).retrofit;
        service = ((MainActivity) MainActivity.context_main).service;
        HashMap<String, Object> param = new HashMap<>();
        param.put("placename",placename);
        linearLayoutManager = new LinearLayoutManager(this);
        binding.placerecycler.setLayoutManager(linearLayoutManager);
        dataList = new ArrayList<>();
        adapter = new PostingAdapter(dataList);
        binding.placerecycler.setAdapter(adapter);

        Call<PlaceRes> call_post = service.PlaceFunc("places",param);
        call_post.enqueue(new Callback<PlaceRes>() {
            @Override
            public void onResponse(Call<PlaceRes> call, Response<PlaceRes> response) {
                if(response.isSuccessful()){
                    PlaceRes result = response.body();
                    Log.v(Tag, "search result : " + result.toString());

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
        Call<ArrayList<PostingInPlaceRes>> call_post2 = service.PostingInPlaceReqFunc("sitereviewlist",placename);
        call_post2.enqueue(new Callback<ArrayList<PostingInPlaceRes>>() {
            @Override
            public void onResponse(Call<ArrayList<PostingInPlaceRes>> call, Response<ArrayList<PostingInPlaceRes>> response) {
                if(response.isSuccessful()){
                    ArrayList<PostingInPlaceRes> result = response.body();
                    Log.v(Tag, "search result : " + result.toString());

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
}
