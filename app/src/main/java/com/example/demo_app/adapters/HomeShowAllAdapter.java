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
import com.example.demo_app.HomeShowAllDetailedActivity;
import com.example.demo_app.R;
import com.example.demo_app.models.HomeShowAllModel;

import java.util.List;

public class HomeShowAllAdapter extends RecyclerView.Adapter<HomeShowAllAdapter.ViewHolder> {

    Context context;
    List<HomeShowAllModel> homeShowAllModelList;

    public HomeShowAllAdapter(Context context, List<HomeShowAllModel> homeShowAllModelList) {
        this.context = context;
        this.homeShowAllModelList = homeShowAllModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(homeShowAllModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(homeShowAllModelList.get(position).getName());
        holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");

        if(homeShowAllModelList.get(position).getType().equals("egg")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/dozen");
        }if(homeShowAllModelList.get(position).getType().equals("milk")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/litre");
        }if(homeShowAllModelList.get(position).getType().equals("fruit")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("vegetable")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("bakery")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/packet or dozen");
        }if(homeShowAllModelList.get(position).getType().equals("breakfast")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("meat")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("drink")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/litre");
        }if(homeShowAllModelList.get(position).getType().equals("biscuit")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/packet");
        }if(homeShowAllModelList.get(position).getType().equals("bread")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/packet");
        }if(homeShowAllModelList.get(position).getType().equals("apple")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("avocados")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("banana")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("berry")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("citrus")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("melon")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("stonefruit")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("tropicalfruit")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("allium")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("cruciferous")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("edible")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("leafygreen")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/piece");
        }if(homeShowAllModelList.get(position).getType().equals("gourd")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("root")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }if(homeShowAllModelList.get(position).getType().equals("juice")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/litre");
        }if(homeShowAllModelList.get(position).getType().equals("soda")){
            holder.price.setText(homeShowAllModelList.get(position).getPrice()+"/kg");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, HomeShowAllDetailedActivity.class);
                i.putExtra("detailshowallhome",homeShowAllModelList.get(position));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return homeShowAllModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,description,price,rating,shop_name,shop_location;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.show_view_img);
            name = itemView.findViewById(R.id.show_name);
//            description = itemView.findViewById(R.id.show_description);
            price = itemView.findViewById(R.id.show_price);
//            rating = itemView.findViewById(R.id.show_rating);
//            shop_name = itemView.findViewById(R.id.shop_name);
//            shop_location = itemView.findViewById(R.id.shop_location);
        }
    }
}
