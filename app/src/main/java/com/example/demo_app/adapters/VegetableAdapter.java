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
import com.example.demo_app.models.VegetableModel;
import com.example.demo_app.viewAll;

import java.util.List;

public class VegetableAdapter extends RecyclerView.Adapter<VegetableAdapter.ViewHolder> {

    Context context;
    List<VegetableModel> vegetableModelList;

    public VegetableAdapter(Context context, List<VegetableModel> vegetableModelList) {
        this.context = context;
        this.vegetableModelList = vegetableModelList;
    }

    @NonNull
    @Override
    public VegetableAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VegetableAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(vegetableModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(vegetableModelList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, viewAll.class);
                i.putExtra("type",vegetableModelList.get(position).getType());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return vegetableModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,description,price,rating,shop_name,shop_location;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.pro_image);
            name = itemView.findViewById(R.id.pro_name);
//            description = itemView.findViewById(R.id.pro_desc);
//            price = itemView.findViewById(R.id.pro_price);
//            rating = itemView.findViewById(R.id.pro_rating);
//            shop_name = itemView.findViewById(R.id.pro_shop_name);
//            shop_location = itemView.findViewById(R.id.pro_shop_location);
        }
    }
}
