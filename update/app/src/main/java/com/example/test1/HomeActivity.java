package com.example.test1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.login.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity {
    private ViewPager2 mPager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 5;
    private CircleIndicator3 mIndicator;
    private Retrofit retrofit;
    private RetrofitAPIInterface service;
    private static final String Tag = "HomeActivity";
    ViewpagerAdapter siteadapter;
    ViewPager2 viewpager;
    ArrayList<RankData> site_list = new ArrayList<>();
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        retrofit = ((MainActivity) MainActivity.context_main).retrofit;
        service = ((MainActivity) MainActivity.context_main).service;
        HashMap<String, Object> param = new HashMap<>();
        //param.put("pos", position);
        Call<ArrayList<RankRes>> call_post = service.RankReqFunc("rankinfo",param);
        call_post.enqueue(new Callback<ArrayList<RankRes>>() {
            @Override
            public void onResponse(Call<ArrayList<RankRes>> call, Response<ArrayList<RankRes>> response) {
                if(response.isSuccessful()){
                    ArrayList<RankRes> result = response.body();
                    Log.v(Tag, "search result : " + result.toString());
                    for(int i = 0; i < result.size(); i++){
                        site_list.add(new RankData(result.get(i).Picturepath, result.get(i).PlaceName));
                        siteadapter.notifyDataSetChanged();
                    }

                }
                else{
                    Log.v(Tag,"error = " + String.valueOf(response.code()));
                    Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<RankRes>> call, Throwable t) {
                Log.v(Tag,"Fail in search");
                Toast.makeText(getApplicationContext(),"Fail",Toast.LENGTH_SHORT).show();

            }
        });

        viewpager = findViewById(R.id.viewPager2);
        siteadapter = new ViewpagerAdapter(site_list);
        viewpager.setAdapter(siteadapter);



        GridView gridView = (GridView) findViewById(R.id.grid_view);

        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this));

        /**
         * On Click event for Single Gridview Item
         * */
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
    }

}
