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
import com.example.demo_app.DetailedActivity;
import com.example.demo_app.R;
import com.example.demo_app.models.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.Viewholder> {

    Context context;
    List<ViewAllModel> viewAllModeList;

    public ViewAllAdapter(Context context, List<ViewAllModel> viewAllModeList) {
        this.context = context;
        this.viewAllModeList = viewAllModeList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(viewAllModeList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(viewAllModeList.get(position).getName());
        holder.description.setText(viewAllModeList.get(position).getDescription());
        holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        holder.rating.setText(viewAllModeList.get(position).getRating());
        holder.shop_name.setText(viewAllModeList.get(position).getShop_name());
        holder.shop_location.setText(viewAllModeList.get(position).getShop_location());

        if(viewAllModeList.get(position).getName().equals("Blue Egg")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/dozen");
        }
        if(viewAllModeList.get(position).getType().equals("egg")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/dozen");
        }if(viewAllModeList.get(position).getType().equals("milk")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/litre");
        }if(viewAllModeList.get(position).getType().equals("fruit")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("vegetable")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("bakery")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/packet or dozen");
        }if(viewAllModeList.get(position).getType().equals("breakfast")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("meat")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("drink")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/litre");
        }if(viewAllModeList.get(position).getType().equals("biscuit")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/packet");
        }if(viewAllModeList.get(position).getType().equals("bread")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/packet");
        }if(viewAllModeList.get(position).getType().equals("apple")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("avocados")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("banana")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("berry")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("citrus")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("melon")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("stonefruit")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("tropicalfruit")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("allium")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("cruciferous")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("edible")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("leafygreen")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/piece");
        }if(viewAllModeList.get(position).getType().equals("gourd")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("root")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }if(viewAllModeList.get(position).getType().equals("juice")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/litre");
        }if(viewAllModeList.get(position).getType().equals("soda")){
            holder.price.setText(viewAllModeList.get(position).getPrice()+"/kg");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailedActivity.class);
                i.putExtra("detail",viewAllModeList.get(position));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewAllModeList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,description,price,rating,shop_name,shop_location;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.view_img);
            name = itemView.findViewById(R.id.view_name);
            description = itemView.findViewById(R.id.view_description);
            price = itemView.findViewById(R.id.view_price);
            rating = itemView.findViewById(R.id.view_rating);
            shop_name = itemView.findViewById(R.id.shop_name);
            shop_location = itemView.findViewById(R.id.shop_location);
        }
    }
}
