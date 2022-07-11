package com.example.test1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyreviewAdapter extends RecyclerView.Adapter<MyreviewAdapter.ViewHolder>{

    private ArrayList<String> localDataSet;

    //===== 뷰홀더 클래스 =====================================================
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
        public TextView getTextView() {
            return textView;
        }
    }
    //========================================================================
    public MyreviewAdapter(ArrayList<String> dataSet) {
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
        String text = localDataSet.get(position);
        holder.textView.setText(text);
    }

    @Override   // 전체 데이터의 갯수를 리턴한다.
    public int getItemCount() {
        return localDataSet.size();
    }
}