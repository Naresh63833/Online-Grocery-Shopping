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
import com.example.demo_app.models.AdminBakeryModel;

import java.util.List;

public class AdminBakeryAdapter extends RecyclerView.Adapter<AdminBakeryAdapter.ViewHolder> {

    Context context;
    List<AdminBakeryModel> adminBakeryModelList;

    public AdminBakeryAdapter(Context context, List<AdminBakeryModel> adminBakeryModelList) {
        this.context = context;
        this.adminBakeryModelList = adminBakeryModelList;
    }

    @NonNull
    @Override
    public AdminBakeryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_details_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminBakeryAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(adminBakeryModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(adminBakeryModelList.get(position).getName());
        holder.description.setText(adminBakeryModelList.get(position).getDescription());
        holder.price.setText(adminBakeryModelList.get(position).getPrice()+"/kg");
        holder.rating.setText(adminBakeryModelList.get(position).getRating());
        holder.shop_name.setText(adminBakeryModelList.get(position).getShop_name());
        holder.shop_location.setText(adminBakeryModelList.get(position).getShop_location());

       if(adminBakeryModelList.get(position).getType().equals("bakery")){
            holder.price.setText(adminBakeryModelList.get(position).getPrice()+"/piece/dozen");
        }

    }

    @Override
    public int getItemCount() {
        return adminBakeryModelList.size();
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
