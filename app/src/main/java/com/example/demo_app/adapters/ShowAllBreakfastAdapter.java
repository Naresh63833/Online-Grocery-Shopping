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
import com.example.demo_app.ShowAllDetailed2Activity;
import com.example.demo_app.models.ShowAllBreakfastModel;

import java.util.List;

public class ShowAllBreakfastAdapter extends RecyclerView.Adapter<ShowAllBreakfastAdapter.ViewHolder> {

    Context context;

    public ShowAllBreakfastAdapter(Context context, List<ShowAllBreakfastModel> showAllBreakfastModelList) {
        this.context = context;
        this.showAllBreakfastModelList = showAllBreakfastModelList;
    }

    List<ShowAllBreakfastModel> showAllBreakfastModelList
            ;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.show_all_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(showAllBreakfastModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(showAllBreakfastModelList.get(position).getName());
        holder.price.setText(showAllBreakfastModelList.get(position).getPrice()+"/kg");

        if(showAllBreakfastModelList.get(position).getType().equals("breakfast")){
            holder.price.setText(showAllBreakfastModelList.get(position).getPrice()+"/kg");
        }
        if(showAllBreakfastModelList.get(position).getType().equals("breakfast")){
            holder.price.setText(showAllBreakfastModelList.get(position).getPrice()+"/kg");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ShowAllDetailed2Activity.class);
                i.putExtra("detailshowallbreak",showAllBreakfastModelList.get(position));
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return showAllBreakfastModelList.size();
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
