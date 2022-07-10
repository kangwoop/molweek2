package com.example.test1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test1.databinding.CountrylistBinding;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.AdapterViewHolder> {
    private CountrylistBinding binding;
    private ArrayList<SearchCountryData> dataArrayList;
    public SearchAdapter(ArrayList<SearchCountryData>arrayList){
        this.dataArrayList = arrayList;
    }
    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = CountrylistBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        View view = binding.getRoot();
        AdapterViewHolder holder = new AdapterViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.binding.imageView.setImageResource(R.drawable.img);
        holder.binding.country.setText(dataArrayList.get(position).getCountryName());
        holder.binding.places.setText(dataArrayList.get(position).getPlaceList());
    }

    @Override
    public int getItemCount() {
        return (null != dataArrayList ? dataArrayList.size() : 0);
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        CountrylistBinding binding;
        public AdapterViewHolder(@NonNull CountrylistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        Toast.makeText(binding.country.getContext(),pos + "아이템",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
