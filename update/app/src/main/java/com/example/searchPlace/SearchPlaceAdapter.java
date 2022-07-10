package com.example.searchPlace;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.R;
import com.example.test1.databinding.PlaceItemBinding;

import java.util.ArrayList;

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
        binding.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.like.setImageResource(R.drawable.heart);
            }
        });
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
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Toast.makeText(binding.placename.getContext(),pos + "아이템",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
