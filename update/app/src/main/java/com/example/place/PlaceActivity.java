package com.example.place;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.login.KakaoApplication;
import com.example.login.LoginActivity;
import com.example.searchPlace.SearchPlaceAdapter;
import com.example.searchPlace.SearchPlaceData;
import com.example.searchPlace.SearchPlaceRes;
import com.example.searchcountry.SearchActivity;
import com.example.test1.HomeActivity;
import com.example.test1.LoginRes;
import com.example.test1.MainActivity;
import com.example.test1.MypageActivity;
import com.example.test1.R;
import com.example.test1.RetrofitAPIInterface;
import com.example.test1.WriteActivity;
import com.example.test1.databinding.ActivityPlaceBinding;
import com.example.test1.databinding.ActivityPlaceinfoBinding;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PlaceActivity extends AppCompatActivity implements OnMapReadyCallback {
    MapView mapView = null;
    private ActivityPlaceinfoBinding binding;
    private ArrayList<PostingData> dataList;
    private PostingAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private boolean heart = false;
    private Retrofit retrofit;
    private RetrofitAPIInterface service;
    private static final String Tag = "PlaceActivity";
    private GoogleMap map;
    double lat = 0;
    double lon = 0;
    private Context context;
    String token;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaceinfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = getApplicationContext();
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        KakaoApplication myApp = (KakaoApplication)getApplicationContext();
        token = myApp.getToken();
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>()
                {
                    @Override
                    public void onActivityResult(ActivityResult data)
                    {
                        Log.d("TAG", "data : " + data);
                        if (data.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent intent = data.getData();
                            int star = intent.getIntExtra("star",0);
                            String name = intent.getStringExtra ("name");
                            String posting = intent.getStringExtra("posting");
                            String date = intent.getStringExtra("date");
                            PostingData data1 = new PostingData(posting,date,star,name);
                            dataList.add(data1);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
        String placename = getIntent().getStringExtra("placename");
        Log.i(Tag, "placename" + placename);
        retrofit = ((MainActivity) MainActivity.context_main).retrofit;
        service = ((MainActivity) MainActivity.context_main).service;
        final Geocoder geocoder = new Geocoder(this);
        HashMap<String, Object> param2 = new HashMap<>();
        param2.put("Place_name", placename);
        param2.put("Htoken", token);

        Call<LoginRes> call_post2 = service.LoginReqFunc("isfavorite", param2);
        call_post2.enqueue(new Callback<LoginRes>() {
            @Override
            public void onResponse(Call<LoginRes> call, retrofit2.Response<LoginRes> response) {
                if (response.isSuccessful()) {
                    String result = response.body().result;
                    Log.v(Tag, "result = " + result);
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    if (result.equals("true")) {
                        heart = true;
                        binding.heart.setImageResource(R.drawable.heart);
                    } else {
                        heart = false;
                        binding.heart.setImageResource(R.drawable.emptyheart);
                    }
                } else {
                    Log.v(Tag, "error = " + String.valueOf(response.code()));
                    Toast.makeText(getApplicationContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRes> call, Throwable t) {
                Log.v(Tag, "Fail");
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });


        HashMap<String, Object> param = new HashMap<>();
        param.put("Place_name", placename);
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

                Call<LoginRes> call_post3 = service.LoginReqFunc("checkfavorite", param3);
                call_post3.enqueue(new Callback<LoginRes>() {
                    @Override
                    public void onResponse(Call<LoginRes> call, retrofit2.Response<LoginRes> response) {
                        if (response.isSuccessful()) {
                            String result = response.body().toString();
                            Log.v(Tag, "result = " + result);
                        } else {
                            Log.v(Tag, "error = " + String.valueOf(response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginRes> call, Throwable t) {
                        Log.v(Tag, "Fail");
                        Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
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
        Call<PlaceRes> call_post = service.PlaceFunc("places", param);
        call_post.enqueue(new Callback<PlaceRes>() {
            @Override
            public void onResponse(Call<PlaceRes> call, Response<PlaceRes> response) {
                if (response.isSuccessful()) {
                    PlaceRes result = response.body();
                    Log.v(Tag, "place result : " + result.toString());
                    //현재 placeres에 text그림 다 추가해보자
                    binding.address.setText(result.Address);
                    binding.countryplace.setText(result.Country);
                    binding.Placesname.setText(result.PlaceName);
                    binding.information.setText(result.Information);
                    //place사진들 넣기 // 생각해보니까 이거 뷰페이져?
                    Glide.with(context).load(result.Picturepath).into(binding.placespicture);

                    List<Address>list = null;
                    String str = result.Address;
                    try {
                        list = geocoder.getFromLocationName(str,10);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(list != null){
                        lat = list.get(0).getLatitude();
                        lon = list.get(0).getLongitude();
                    }
                    onMapReady(map);
                    HashMap<String, Object> param1 = new HashMap<>();
                    param1.put("Place_name", placename);
                    Call<ArrayList<PostingInPlaceRes>> call_post2 = service.PostingInPlaceReqFunc("sitereviewlist", param1);
                    call_post2.enqueue(new Callback<ArrayList<PostingInPlaceRes>>() {
                        @Override
                        public void onResponse(Call<ArrayList<PostingInPlaceRes>> call, Response<ArrayList<PostingInPlaceRes>> response) {
                            if (response.isSuccessful()) {
                                ArrayList<PostingInPlaceRes> result = response.body();
                                Log.v(Tag, "search result : " + result.toString());
                                for (int i = 0; i < result.size(); i++) {
                                    PostingData data = new PostingData(result.get(i).Posting, result.get(i).Posting_date, Integer.parseInt(result.get(i).Star), result.get(i).name);
                                    dataList.add(data);
                                    adapter.notifyDataSetChanged();
                                }
                            } else {
                                Log.v(Tag, "error = " + String.valueOf(response.code()));
                                Toast.makeText(getApplicationContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ArrayList<PostingInPlaceRes>> call, Throwable t) {
                            Log.v(Tag, "Fail in search");
                            Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    Log.v(Tag, "error = " + String.valueOf(response.code()));
                    Toast.makeText(getApplicationContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PlaceRes> call, Throwable t) {
                Log.v(Tag, "Fail in search");
                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), WriteActivity.class);
                // passing array index
                i.putExtra("placename",binding.Placesname.getText());
                launcher.launch(i);
            }
        });
        mapView.getMapAsync(this::onMapReady);


        binding.gotomypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MypageActivity.class);
                // passing array index
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
//        HashMap<String, Object> param1 = new HashMap<>();
//        param1.put("Place_name", binding.Placesname.getText());
//        Call<ArrayList<PostingInPlaceRes>> call_post2 = service.PostingInPlaceReqFunc("sitereviewlist", param1);
//        call_post2.enqueue(new Callback<ArrayList<PostingInPlaceRes>>() {
//            @Override
//            public void onResponse(Call<ArrayList<PostingInPlaceRes>> call, Response<ArrayList<PostingInPlaceRes>> response) {
//                if (response.isSuccessful()) {
//                    ArrayList<PostingInPlaceRes> result = response.body();
//                    Log.v(Tag, "search result : " + result.toString());
//                    for (int i = 0; i < result.size(); i++) {
//                        PostingData data = new PostingData(result.get(i).Posting, result.get(i).Posting_date, Integer.parseInt(result.get(i).Star), result.get(i).name);
//                        dataList.add(data);
//                        adapter.notifyDataSetChanged();
//                    }
//                } else {
//                    Log.v(Tag, "error = " + String.valueOf(response.code()));
//                    Toast.makeText(getApplicationContext(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<PostingInPlaceRes>> call, Throwable t) {
//                Log.v(Tag, "Fail in search");
//                Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
    @Override
    public void onMapReady(GoogleMap googleMap){
        map = googleMap;
        LatLng latLng = new LatLng(lat,lon);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title((String) binding.Placesname.getText());
        markerOptions.position(latLng);
        googleMap.addMarker(markerOptions);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();   //현재 액티비티 종료
    }

}
