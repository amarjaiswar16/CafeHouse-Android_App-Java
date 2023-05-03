package com.amrdroid.cafehouse.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amrdroid.cafehouse.DetailActivity;
import com.amrdroid.cafehouse.Models.FoodMainModel;
import com.amrdroid.cafehouse.R;

import java.util.ArrayList;

public class FoodMainAdapter extends RecyclerView.Adapter<FoodMainAdapter.ViewHolder> {

    private int lastPosition = -1;
    Context context;
    ArrayList<FoodMainModel> arrayList;
    public FoodMainAdapter(Context context, ArrayList<FoodMainModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.samplefood_cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.foodImage.setImageResource(arrayList.get(position).getImage());
        holder.foodName.setText(arrayList.get(position).getName());
        holder.foodPrice.setText(arrayList.get(position).getPrice());
        holder.foodDesc.setText(arrayList.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDetail = new Intent(context, DetailActivity.class);
                iDetail.putExtra("image",arrayList.get(position).getImage());
                iDetail.putExtra("name",arrayList.get(position).getName());
                iDetail.putExtra("price",arrayList.get(position).getPrice());
                iDetail.putExtra("description",arrayList.get(position).getDescription());
                iDetail.putExtra("type",1);
                context.startActivity(iDetail);

            }
        });


        setAnimation(holder.itemView,position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView foodImage;
        TextView foodName;
        TextView foodPrice;
        TextView foodDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.food_img);
            foodName = itemView.findViewById(R.id.foodName_txt);
            foodPrice = itemView.findViewById(R.id.foodPrice_txt);
            foodDesc = itemView.findViewById(R.id.foodDesc_txt);


        }
    }
    public void setAnimation(View viewToAnimate, int position) {
        if(position>lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
