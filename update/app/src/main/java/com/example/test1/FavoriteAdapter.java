package com.example.test1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{

    private ArrayList<RankData> localDataSet;

    //===== 뷰홀더 클래스 =====================================================
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        private RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.placename);
            imageView = itemView.findViewById(R.id.placepicture);
            ratingBar = itemView.findViewById(R.id.ratingBar2);
        }
        public TextView getTextView() {
            return textView;
        }
        public ImageView getImageView() {
            return imageView;
        }
        public RatingBar getRatingBar() {return ratingBar;}
    }
    //========================================================================
    public FavoriteAdapter (ArrayList<RankData> dataSet) {
        localDataSet = dataSet;
    }

    Context context;
    @NonNull
    @Override   // ViewHolder 객체를 생성하여 리턴한다.
    public FavoriteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favoritelist, parent, false);
        FavoriteAdapter.ViewHolder viewHolder = new FavoriteAdapter.ViewHolder(view);
        context = parent.getContext();
        return viewHolder;
    }

    @Override   // ViewHolder안의 내용을 position에 해당되는 데이터로 교체한다.
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        Log.i("HHHHH", localDataSet.get(position).getImagePath());
        String text = localDataSet.get(position).getPlaceName();
        Glide.with(context).load(localDataSet.get(position).getImagePath()).into(holder.imageView);
        holder.textView.setText(text);
        holder.ratingBar.setRating(localDataSet.get(position).getavgstar());
    }

    @Override   // 전체 데이터의 갯수를 리턴한다.
    public int getItemCount() {
        return localDataSet.size();
    }
}
