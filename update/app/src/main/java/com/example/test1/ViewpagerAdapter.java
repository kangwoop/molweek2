package com.example.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewpagerAdapter extends RecyclerView.Adapter<ViewHolderPage>{
    private ArrayList<String> sitelist;

    public ViewpagerAdapter(ArrayList<String> sitelist) {
        this.sitelist = sitelist;
    }


    @Override
    public ViewHolderPage onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.viewpage, parent, false);
        return new ViewHolderPage(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderPage holder, int position) {
        if(holder instanceof ViewHolderPage){
            ViewHolderPage viewHolder = (ViewHolderPage) holder;
            viewHolder.onBind(sitelist.get(position));
            //Log.i("bind", Integer.toString(position));
        }
    }

    @Override
    public int getItemCount() {
        return sitelist.size();
    }


}
