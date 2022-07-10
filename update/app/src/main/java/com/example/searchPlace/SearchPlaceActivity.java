package com.example.searchPlace;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.test1.SearchAdapter;
import com.example.test1.MainActivity;
import com.example.test1.RetrofitAPIInterface;
import com.example.test1.databinding.ActivityPlaceBinding;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchPlaceActivity extends Activity {
    private ActivityPlaceBinding binding;
    private ArrayList<SearchPlaceData> dataList;
    private SearchPlaceAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private Retrofit retrofit;
    private RetrofitAPIInterface service;
    private static final String Tag = "SearchPlaceActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String country = getIntent().getStringExtra("country");
        retrofit = ((MainActivity)MainActivity.context_main).retrofit;
        service = ((MainActivity)MainActivity.context_main).service;
        HashMap<String, Object> param = new HashMap<>();
        param.put("country", country);

        Call<ArrayList<SearchPlaceRes>> call_post = service.SearchPlaceReqFunc("mysite",param);
        call_post.enqueue(new Callback<ArrayList<SearchPlaceRes>>() {
            @Override
            public void onResponse(Call<ArrayList<SearchPlaceRes>> call, Response<ArrayList<SearchPlaceRes>> response) {
                if(response.isSuccessful()){
                    ArrayList<SearchPlaceRes> result = response.body();
                    Log.v(Tag, "search result : " + result.toString());
                    for(int i = 0; i < result.size(); i++){
                        SearchPlaceData data = new SearchPlaceData(result.get(i).PicturePath,result.get(i).PlaceName,result.get(i).AvgStar);
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
            public void onFailure(Call<ArrayList<SearchPlaceRes>> call, Throwable t) {
                Log.v(Tag,"Fail in search");
                Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();

            }
        });
        linearLayoutManager = new LinearLayoutManager(this);
        binding.places.setLayoutManager(linearLayoutManager);
        dataList = new ArrayList<>();
        adapter = new SearchPlaceAdapter(dataList);
        binding.places.setAdapter(adapter);

    }
}


