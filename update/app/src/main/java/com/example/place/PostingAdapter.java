package com.example.place;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchPlace.SearchPlaceData;
import com.example.searchcountry.SearchActivity;
import com.example.test1.R;
import com.example.test1.databinding.PlaceItemBinding;
import com.example.test1.databinding.PostingItemInplaceBinding;

import java.util.ArrayList;

public class PostingAdapter extends RecyclerView.Adapter<PostingAdapter.AdapterViewHolder> {
    private PostingItemInplaceBinding binding;
    private ArrayList<PostingData> dataArrayList;
    public PostingAdapter(ArrayList<PostingData>arrayList){
        this.dataArrayList = arrayList;
    }

    @NonNull
    @Override
    public PostingAdapter.AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = PostingItemInplaceBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        View view = binding.getRoot();
        PostingAdapter.AdapterViewHolder holder = new PostingAdapter.AdapterViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostingAdapter.AdapterViewHolder holder, int position) {
        binding.Posting.setText(dataArrayList.get(position).Posting);
        binding.Postingdate.setText(dataArrayList.get(position).Posting_date);
        binding.WhoPosting.setText(dataArrayList.get(position).name);
        binding.ratingBar.setRating(dataArrayList.get(position).star);
    }
    @Override
    public int getItemCount() {
        return (null != dataArrayList ? dataArrayList.size() : 0);
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        PostingItemInplaceBinding binding;
        public AdapterViewHolder(@NonNull PostingItemInplaceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
