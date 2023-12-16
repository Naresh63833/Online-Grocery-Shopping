package com.example.demo_app.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo_app.R;
import com.example.demo_app.models.AllProductsModel;
import com.example.demo_app.viewAll;

import java.util.List;

public class AllProductsAdapter extends RecyclerView.Adapter<AllProductsAdapter.ViewHolder> {

    Context context;
    List<AllProductsModel> allProductsModelList;

    public AllProductsAdapter(Context context, List<AllProductsModel> allProductsModelList) {
        this.context = context;
        this.allProductsModelList = allProductsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.all_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(allProductsModelList.get(position).getImg_url()).into(holder.allImg);
        holder.name.setText(allProductsModelList.get(position).getName());
        holder.description.setText(allProductsModelList.get(position).getDescription());
        holder.discount.setText(allProductsModelList.get(position).getDiscount());
        holder.rating.setText(allProductsModelList.get(position).getRating());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, viewAll.class);
                i.putExtra("type",allProductsModelList.get(position).getType());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allProductsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView allImg;
        TextView name,description,rating,discount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            allImg = itemView.findViewById(R.id.all_img);
            name = itemView.findViewById(R.id.all_name);
            description = itemView.findViewById(R.id.all_desc);
            rating = itemView.findViewById(R.id.all_rating);
            discount = itemView.findViewById(R.id.all_discount);
        }
    }
}
