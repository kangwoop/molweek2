package com.example.searchPlace;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.place.PlaceActivity;
import com.example.searchcountry.SearchActivity;
import com.example.test1.LoginRes;
import com.example.test1.MainActivity;
import com.example.test1.R;
import com.example.test1.RetrofitAPIInterface;
import com.example.test1.databinding.PlaceItemBinding;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class SearchPlaceAdapter extends RecyclerView.Adapter<SearchPlaceAdapter.AdapterViewHolder> {
    private PlaceItemBinding binding;
    private ArrayList<SearchPlaceData> dataArrayList;
    public SearchPlaceAdapter(ArrayList<SearchPlaceData>arrayList){
        this.dataArrayList = arrayList;
    }

    Context context;
    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = PlaceItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        View view = binding.getRoot();
        context = parent.getContext();
        AdapterViewHolder holder = new AdapterViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        binding.placename.setText(dataArrayList.get(position).PlaceName);
        Glide.with(context).load(dataArrayList.get(position).imagePath).into(binding.placepicture);

        binding.ratingBar3.setRating((float) dataArrayList.get(position).AvgStar);

        if(dataArrayList.get(position).isEmpty){
            binding.like.setImageResource(R.drawable.heart);
        }
        else{
            binding.like.setImageResource(R.drawable.emptyheart);
        }
    }
    @Override
    public int getItemCount() {
        return (null != dataArrayList ? dataArrayList.size() : 0);
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        PlaceItemBinding binding;
        public AdapterViewHolder(@NonNull PlaceItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.like.setImageResource(R.drawable.emptyheart);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Toast.makeText(binding.placename.getContext(),pos + "아이템",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(binding.placename.getContext().getApplicationContext(), PlaceActivity.class);
                        i.putExtra("placename", dataArrayList.get(pos).PlaceName);
                        binding.placepicture.getContext().startActivity(i);
                    }
                }
            });

        }
    }
}
