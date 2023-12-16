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
import com.example.demo_app.ShowAllDetailedActivity;
import com.example.demo_app.models.ShowAllModel;

import java.util.List;

public class ShowAllAdapter extends RecyclerView.Adapter<ShowAllAdapter.ViewHolder> {

    Context context;
    List<ShowAllModel> showAllModelList;

    public ShowAllAdapter(Context context, List<ShowAllModel> showAllModelList) {
        this.context = context;
        this.showAllModelList = showAllModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(showAllModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(showAllModelList.get(position).getName());
        holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");

        if(showAllModelList.get(position).getType().equals("egg")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/dozen");
        }if(showAllModelList.get(position).getType().equals("milk")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/litre");
        }if(showAllModelList.get(position).getType().equals("fruit")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("vegetable")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("bakery")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/packet or dozen");
        }if(showAllModelList.get(position).getType().equals("breakfast")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("meat")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("drink")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/litre");
        }if(showAllModelList.get(position).getType().equals("biscuit")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/packet");
        }if(showAllModelList.get(position).getType().equals("bread")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/packet");
        }if(showAllModelList.get(position).getType().equals("apple")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("avocados")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("banana")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("berry")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("citrus")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("melon")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("stonefruit")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("tropicalfruit")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("allium")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("cruciferous")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("edible")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("leafygreen")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/piece");
        }if(showAllModelList.get(position).getType().equals("gourd")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("root")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }if(showAllModelList.get(position).getType().equals("juice")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/litre");
        }if(showAllModelList.get(position).getType().equals("soda")){
            holder.price.setText(showAllModelList.get(position).getPrice()+"/kg");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ShowAllDetailedActivity.class);
                i.putExtra("detailshowall",showAllModelList.get(position));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return showAllModelList.size();
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
