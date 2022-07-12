package com.example.test1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.place.PostingData;

import java.util.ArrayList;

public class MyreviewAdapter extends RecyclerView.Adapter<MyreviewAdapter.ViewHolder>{

    private ArrayList<PostingData> localDataSet;

    //===== 뷰홀더 클래스 =====================================================
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView placetextView;
        private TextView postingtextView;
        private TextView postdatetextView;
        private RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placetextView = itemView.findViewById(R.id.Place);
            postingtextView = itemView.findViewById(R.id.Posting);
            postdatetextView = itemView.findViewById(R.id.Postdate);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
        public TextView getTextView() {
            return placetextView;
        }
    }
    //========================================================================
    public MyreviewAdapter(ArrayList<PostingData> dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override   // ViewHolder 객체를 생성하여 리턴한다.
    public MyreviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myreviewlist, parent, false);
        MyreviewAdapter.ViewHolder viewHolder = new MyreviewAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override   // ViewHolder안의 내용을 position에 해당되는 데이터로 교체한다.
    public void onBindViewHolder(@NonNull MyreviewAdapter.ViewHolder holder, int position) {
        String text = localDataSet.get(position).getName();
        holder.placetextView.setText(text);
        holder.postingtextView.setText(localDataSet.get(position).getPosting());
        holder.postdatetextView.setText(localDataSet.get(position).getPosting_date());
        holder.ratingBar.setRating(localDataSet.get(position).getStar());
    }

    @Override   // 전체 데이터의 갯수를 리턴한다.
    public int getItemCount() {
        return localDataSet.size();
    }
}
