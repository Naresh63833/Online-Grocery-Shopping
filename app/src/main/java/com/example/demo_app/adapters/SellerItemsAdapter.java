package com.example.demo_app.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_app.R;
import com.example.demo_app.admin.SellerDetailsAdminActivity;
import com.example.demo_app.models.SellerItemsModel;

import java.util.List;

public class SellerItemsAdapter extends RecyclerView.Adapter<SellerItemsAdapter.ViewHolder> {

    Context context;
    List<SellerItemsModel> sellerItemsModelList;

    public SellerItemsAdapter(Context context, List<SellerItemsModel> sellerItemsModelList) {
        this.context = context;
        this.sellerItemsModelList = sellerItemsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.userText.setText(sellerItemsModelList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, SellerDetailsAdminActivity.class);
                i.putExtra("Type",sellerItemsModelList.get(position).getType());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sellerItemsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userText = itemView.findViewById(R.id.usertext);
        }
    }
}
