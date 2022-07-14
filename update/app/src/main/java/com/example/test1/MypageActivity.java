package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.example.login.KakaoApplication;
import com.google.android.material.tabs.TabLayout;

public class MypageActivity extends AppCompatActivity {

    TabLayout tabs;
    FavoriteFragment favoriteFragment;
    MyreviewFragment myreviewFragment;

    int position;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        Log.i("onCreateMain", "");

        KakaoApplication myApp = (KakaoApplication)getApplicationContext();
        token = myApp.getToken();
        Log.i("Tokenisisisis", token);

        //getSupportActionBar().setTitle("Favorites");
        tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Favorites"));
        tabs.addTab(tabs.newTab().setText("MyReview"));
        tabs.setTabTextColors(Color.rgb(0,0,0), Color.rgb(0,0,150));

        favoriteFragment = new FavoriteFragment();
        myreviewFragment = new MyreviewFragment();

        Bundle bundle = new Bundle(1);
        bundle.putString("token", token);
        favoriteFragment.setArguments(bundle);
        myreviewFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.containers, favoriteFragment).commit();
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();

                Fragment selected = null;

                if(position==0){
                    selected = favoriteFragment;
                    //getSupportActionBar().setTitle("Favorites");
                }else if(position==1) {
                    selected = myreviewFragment;
                    //getSupportActionBar().setTitle("MyReview");
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.containers, selected).commit();

            }
        });
        }
}