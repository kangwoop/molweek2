package com.example.test1;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderPage extends RecyclerView.ViewHolder{

    private ImageView imageview;
    private TextView textview;

    public ViewHolderPage(@NonNull View itemView) {
        super(itemView);
        textview = itemView.findViewById(R.id.textview);
        imageview = itemView.findViewById(R.id.imageview);
    }

    public void onBind(/*ImageView site_img,*/ String site_name){
        /*imageview.setImageDrawable();*/
        textview.setText(site_name);
    }
}
