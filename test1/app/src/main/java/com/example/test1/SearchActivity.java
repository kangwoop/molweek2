package com.example.test1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.test1.databinding.CountrysBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchActivity extends Activity {
    private CountrysBinding binding;
    private ArrayList<SearchCountryData> dataList;
    private  SearchAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private Retrofit retrofit;
    private RetrofitAPIInterface service;
    private static final String Tag = "SearchActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = CountrysBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int position = getIntent().getIntExtra("id",-1);
        retrofit = ((MainActivity)MainActivity.context_main).retrofit;
        service = ((MainActivity)MainActivity.context_main).service;
        HashMap<String, Object> param = new HashMap<>();
        param.put("pos", position);

        Call<ArrayList<SearchRes>> call_post = service.CountryReqFunc("countrywithpos",param);
        call_post.enqueue(new Callback<ArrayList<SearchRes>>() {
            @Override
            public void onResponse(Call<ArrayList<SearchRes>> call, Response<ArrayList<SearchRes>> response) {
                if(response.isSuccessful()){
                    ArrayList<SearchRes> result = response.body();
                    Log.v(Tag, "search result : " + result.toString());
                    for(int i = 0; i < result.size(); i++){
                        SearchCountryData data = new SearchCountryData(result.get(i).PicturePath,result.get(i).Name,result.get(i).placenames);
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
            public void onFailure(Call<ArrayList<SearchRes>> call, Throwable t) {
                Log.v(Tag,"Fail in search");
                Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();

            }
        });
        linearLayoutManager = new LinearLayoutManager(this);
        binding.countrys.setLayoutManager(linearLayoutManager);
        dataList = new ArrayList<>();
        adapter = new SearchAdapter(dataList);
        binding.countrys.setAdapter(adapter);

    }
}


