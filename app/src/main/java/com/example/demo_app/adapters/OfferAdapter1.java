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
import com.example.demo_app.models.OfferModel1;
import com.example.demo_app.viewAll;

import java.util.List;

public class OfferAdapter1 extends RecyclerView.Adapter<OfferAdapter1.ViewHolder> {

    Context context;
    List<OfferModel1> offerModel1List;

    public OfferAdapter1(Context context, List<OfferModel1> offerModel1List) {
        this.context = context;
        this.offerModel1List = offerModel1List;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(offerModel1List.get(position).getImg_url()).into(holder.offImg);
        holder.name.setText(offerModel1List.get(position).getName());
        holder.discount.setText(offerModel1List.get(position).getDiscount());

        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, viewAll.class);
                i.putExtra("type",offerModel1List.get(position).getType());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return offerModel1List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView offImg;
        TextView name,description,rating,discount,shop_name,shop_location;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            offImg = itemView.findViewById(R.id.off_img);
            name = itemView.findViewById(R.id.off_name);
//            description = itemView.findViewById(R.id.pop_desc);
//            rating = itemView.findViewById(R.id.pop_rating);
            discount = itemView.findViewById(R.id.discount);
//            shop_name = itemView.findViewById(R.id.shop_name);
//            shop_location = itemView.findViewById(R.id.shop_location);
        }
    }
}
