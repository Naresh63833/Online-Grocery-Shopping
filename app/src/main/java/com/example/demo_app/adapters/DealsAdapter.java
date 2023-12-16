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
import com.example.demo_app.models.DealsModel;
import com.example.demo_app.viewAll;

import java.util.List;

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.ViewHolder> {

    Context context;
    List<DealsModel> dealsModelList;

    public DealsAdapter(Context context, List<DealsModel> dealsModelList) {
        this.context = context;
        this.dealsModelList = dealsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.deals_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(dealsModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(dealsModelList.get(position).getName());
        holder.description.setText(dealsModelList.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, viewAll.class);
                i.putExtra("type",dealsModelList.get(position).getType());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dealsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name,description,rating,discount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.deal_img);
            name = itemView.findViewById(R.id.deal_name);
            description = itemView.findViewById(R.id.deal_desc);
//            rating = itemView.findViewById(R.id.deal_rating);
//            discount = itemView.findViewById(R.id.deal_discount);
        }
    }
}
