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
import com.example.demo_app.models.OfferModel;
import com.example.demo_app.viewAll;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {

    Context context;
    List<OfferModel> offerModelList;

    public OfferAdapter(Context context, List<OfferModel> offerModelList) {
        this.context = context;
        this.offerModelList = offerModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(offerModelList.get(position).getImg_url()).into(holder.popImg);
        holder.name.setText(offerModelList.get(position).getName());
        holder.discount.setText(offerModelList.get(position).getDiscount());

        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, viewAll.class);
                i.putExtra("type",offerModelList.get(position).getType());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return offerModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name,description,rating,discount,shop_name,shop_location;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popImg = itemView.findViewById(R.id.off_img);
            name = itemView.findViewById(R.id.off_name);
//            description = itemView.findViewById(R.id.pop_desc);
//            rating = itemView.findViewById(R.id.pop_rating);
            discount = itemView.findViewById(R.id.discount);
//            shop_name = itemView.findViewById(R.id.shop_name);
//            shop_location = itemView.findViewById(R.id.shop_location);
        }
    }
}
