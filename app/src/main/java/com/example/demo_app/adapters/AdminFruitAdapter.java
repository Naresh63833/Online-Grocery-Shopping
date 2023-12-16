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
import com.example.demo_app.models.AdminFruitModel;

import java.util.List;

public class AdminFruitAdapter extends RecyclerView.Adapter<AdminFruitAdapter.ViewHolder> {
    Context context;
    List<AdminFruitModel> adminFruitModelList;

    public AdminFruitAdapter(Context context, List<AdminFruitModel> adminFruitModelList) {
        this.context = context;
        this.adminFruitModelList = adminFruitModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_details_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(adminFruitModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(adminFruitModelList.get(position).getName());
        holder.description.setText(adminFruitModelList.get(position).getDescription());
        holder.price.setText(adminFruitModelList.get(position).getPrice()+"/kg");
        holder.rating.setText(adminFruitModelList.get(position).getRating());
        holder.shop_name.setText(adminFruitModelList.get(position).getShop_name());
        holder.shop_location.setText(adminFruitModelList.get(position).getShop_location());

        if(adminFruitModelList.get(position).getType().equals("fruit")){
            holder.price.setText(adminFruitModelList.get(position).getPrice()+"/kg");
        }

    }

    @Override
    public int getItemCount() {
        return adminFruitModelList.size();
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
