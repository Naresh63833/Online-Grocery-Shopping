package com.example.demo_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demo_app.R;
import com.example.demo_app.models.AdminVegetableModel;

import java.util.List;

public class AdminVegAdapter extends RecyclerView.Adapter<AdminVegAdapter.ViewHolder> {
    Context context;
    List<AdminVegetableModel> adminVegetableModelList;

    public AdminVegAdapter(Context context, List<AdminVegetableModel> adminVegetableModelList) {
        this.context = context;
        this.adminVegetableModelList = adminVegetableModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_details_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(adminVegetableModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(adminVegetableModelList.get(position).getName());
        holder.description.setText(adminVegetableModelList.get(position).getDescription());
        holder.price.setText(adminVegetableModelList.get(position).getPrice()+"/kg");
        holder.rating.setText(adminVegetableModelList.get(position).getRating());
        holder.shop_name.setText(adminVegetableModelList.get(position).getShop_name());
        holder.shop_location.setText(adminVegetableModelList.get(position).getShop_location());

        if(adminVegetableModelList.get(position).getType().equals("vegetable")){
            holder.price.setText(adminVegetableModelList.get(position).getPrice()+"/kg");
        }

    }

    @Override
    public int getItemCount() {
        return adminVegetableModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,description,price,rating,shop_name,shop_location;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pro_image);
            name = itemView.findViewById(R.id.pro_name);
            description = itemView.findViewById(R.id.pro_desc);
            price = itemView.findViewById(R.id.pro_price);
            rating = itemView.findViewById(R.id.pro_rating);
            shop_name = itemView.findViewById(R.id.pro_shop_name);
            shop_location = itemView.findViewById(R.id.pro_shop_location);
        }
    }
}
