package com.example.searchPlace;

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

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = PlaceItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        View view = binding.getRoot();
        AdapterViewHolder holder = new AdapterViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        binding.placename.setText(dataArrayList.get(position).PlaceName);
        binding.placepicture.setImageResource(R.drawable.heart);
        if (dataArrayList.get(position).getAvgStar() == 0) {

        } else if (dataArrayList.get(position).getAvgStar() == 1) {
            binding.star1.setImageResource(R.drawable.star);
        } else if (dataArrayList.get(position).getAvgStar() == 2) {
            binding.star1.setImageResource(R.drawable.star);
            binding.star2.setImageResource(R.drawable.star);
        } else if (dataArrayList.get(position).getAvgStar() == 3) {
            binding.star1.setImageResource(R.drawable.star);
            binding.star2.setImageResource(R.drawable.star);
            binding.star3.setImageResource(R.drawable.star);
        } else if (dataArrayList.get(position).getAvgStar() == 4) {
            binding.star1.setImageResource(R.drawable.star);
            binding.star2.setImageResource(R.drawable.star);
            binding.star3.setImageResource(R.drawable.star);
            binding.star4.setImageResource(R.drawable.star);
        } else {
            binding.star1.setImageResource(R.drawable.star);
            binding.star2.setImageResource(R.drawable.star);
            binding.star3.setImageResource(R.drawable.star);
            binding.star4.setImageResource(R.drawable.star);
            binding.star5.setImageResource(R.drawable.star);
        }
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
