package com.example.test1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewpagerAdapter extends RecyclerView.Adapter<ViewHolderPage>{
    private ArrayList<RankData> sitelist;

    public ViewpagerAdapter(ArrayList<RankData> sitelist) {
        this.sitelist = sitelist;
    }

    Context context;

    @Override
    public ViewHolderPage onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.viewpage, parent, false);
        return new ViewHolderPage(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderPage holder, int position) {
        if(holder instanceof ViewHolderPage){
            ViewHolderPage viewHolder = (ViewHolderPage) holder;
            viewHolder.onBind(sitelist.get(position), context);
            //Log.i("bind", Integer.toString(position));
        }
    }

    @Override
    public int getItemCount() {
        return sitelist.size();
    }


}
