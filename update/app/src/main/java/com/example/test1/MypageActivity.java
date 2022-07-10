package com.example.test1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class MypageActivity extends AppCompatActivity {

    TabLayout tabs;
    FavoriteFragment favoriteFragment;
    MyreviewFragment myreviewFragment;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        Log.i("onCreateMain", "");

        getSupportActionBar().setTitle("Favorites");
        tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Favorites"));
        tabs.addTab(tabs.newTab().setText("MyReview"));
        tabs.setTabTextColors(Color.rgb(0,0,0), Color.rgb(0,0,150));

        favoriteFragment = new FavoriteFragment();
        myreviewFragment = new MyreviewFragment();

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
                    getSupportActionBar().setTitle("Favorites");
                }else if(position==1) {
                    selected = myreviewFragment;
                    getSupportActionBar().setTitle("MyReview");
                    getSupportFragmentManager().beginTransaction().replace(R.id.containers, selected).commit();
                }
            }
        });
        }
}