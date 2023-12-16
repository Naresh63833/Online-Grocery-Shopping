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
import com.example.demo_app.admin.UsersDetailsAdminActivity;
import com.example.demo_app.models.UserItemsModel;

import java.util.List;

public class UserItemsAdapter extends RecyclerView.Adapter<UserItemsAdapter.ViewHolder> {

    Context context;
    List<UserItemsModel> userItemsModelList;

    public UserItemsAdapter(Context context, List<UserItemsModel> userItemsModelList) {
        this.context = context;
        this.userItemsModelList = userItemsModelList;
    }

    @NonNull
    @Override
    public UserItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.userText.setText(userItemsModelList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, UsersDetailsAdminActivity.class);
                i.putExtra("Type",userItemsModelList.get(position).getType());
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return userItemsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userText = itemView.findViewById(R.id.usertext);
        }
    }
}
