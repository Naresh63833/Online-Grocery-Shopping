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
import com.example.demo_app.models.AdminDrinkModel;

import java.util.List;

public class AdminDrinkAdapter extends RecyclerView.Adapter<AdminDrinkAdapter.ViewHolder> {
    Context context;
    List<AdminDrinkModel> adminDrinkModelList;

    public AdminDrinkAdapter(Context context, List<AdminDrinkModel> adminDrinkModelList) {
        this.context = context;
        this.adminDrinkModelList = adminDrinkModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_details_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(adminDrinkModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(adminDrinkModelList.get(position).getName());
        holder.description.setText(adminDrinkModelList.get(position).getDescription());
        holder.price.setText(adminDrinkModelList.get(position).getPrice()+"/kg");
        holder.rating.setText(adminDrinkModelList.get(position).getRating());
        holder.shop_name.setText(adminDrinkModelList.get(position).getShop_name());
        holder.shop_location.setText(adminDrinkModelList.get(position).getShop_location());

       if(adminDrinkModelList.get(position).getType().equals("drink")){
            holder.price.setText(adminDrinkModelList.get(position).getPrice()+"/litre");
        }

    }

    @Override
    public int getItemCount() {
        return adminDrinkModelList.size();
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
