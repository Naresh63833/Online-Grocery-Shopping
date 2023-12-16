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
import com.example.demo_app.DetailedCategoryActivity;
import com.example.demo_app.R;
import com.example.demo_app.models.NavCategoryDetailedModel;

import java.util.List;

public class NavCategoryDetailedAdapter extends RecyclerView.Adapter<NavCategoryDetailedAdapter.ViewHolder> {

    Context context;
    List<NavCategoryDetailedModel> navCategoryDetailedModelList;

    public NavCategoryDetailedAdapter(Context context, List<NavCategoryDetailedModel> navCategoryDetailedModelList) {
        this.context = context;
        this.navCategoryDetailedModelList = navCategoryDetailedModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_detailed_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(navCategoryDetailedModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(navCategoryDetailedModelList.get(position).getName());
        holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/litre");
        holder.rating.setText(navCategoryDetailedModelList.get(position).getRating());
        holder.shop_name.setText(navCategoryDetailedModelList.get(position).getShop_name());
        holder.shop_location.setText(navCategoryDetailedModelList.get(position).getShop_location());
        holder.description.setText(navCategoryDetailedModelList.get(position).getDescription());

        if(navCategoryDetailedModelList.get(position).getType().equals("egg")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/dozen");
        }if(navCategoryDetailedModelList.get(position).getType().equals("milk")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/litre");
        }if(navCategoryDetailedModelList.get(position).getType().equals("fruit")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("vegetable")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("bakery")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/packet or dozen");
        }if(navCategoryDetailedModelList.get(position).getType().equals("breakfast")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("meat")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("drink")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/litre");
        }if(navCategoryDetailedModelList.get(position).getType().equals("biscuit")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/packet");
        }if(navCategoryDetailedModelList.get(position).getType().equals("bread")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/packet");
        }if(navCategoryDetailedModelList.get(position).getType().equals("apple")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("avocados")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("banana")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("berry")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("citrus")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("melon")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("stonefruit")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("tropicalfruit")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("allium")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("cruciferous")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("edible")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("leafygreen")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/piece");
        }if(navCategoryDetailedModelList.get(position).getType().equals("gourd")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("root")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }if(navCategoryDetailedModelList.get(position).getType().equals("juice")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/litre");
        }if(navCategoryDetailedModelList.get(position).getType().equals("soda")){
            holder.price.setText(navCategoryDetailedModelList.get(position).getPrice()+"/kg");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailedCategoryActivity.class);
                i.putExtra("detailcat", navCategoryDetailedModelList.get(position));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return navCategoryDetailedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,price,rating,shop_name,shop_location,description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.cat_nav_detailed_img);
            name = itemView.findViewById(R.id.nav_cat_detailed_name);
            price = itemView.findViewById(R.id.nav_cat_detailed_price);
            rating = itemView.findViewById(R.id.nav_cat_rating);
            shop_name = itemView.findViewById(R.id.shop_name);
            shop_location = itemView.findViewById(R.id.shop_location);
            description = itemView.findViewById(R.id.description);
        }
    }
}
