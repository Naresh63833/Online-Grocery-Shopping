package com.example.demo_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_app.R;
import com.example.demo_app.models.AdminAccessModel;

import java.util.List;

public class AdminAccessAdapter extends RecyclerView.Adapter<AdminAccessAdapter.ViewHolder> {

    Context context;
    List<AdminAccessModel> adminAccessModelList;

    public AdminAccessAdapter(Context context, List<AdminAccessModel> adminAccessModelList) {
        this.context = context;
        this.adminAccessModelList = adminAccessModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.Name.setText(adminAccessModelList.get(position).getName());
        holder.Email.setText(adminAccessModelList.get(position).getEmail());
        holder.Phone_Number.setText(adminAccessModelList.get(position).getPhone_Number());
        holder.Password.setText(adminAccessModelList.get(position).getPassword());
        holder.Shop_Name.setText(adminAccessModelList.get(position).getShop_Name());
        holder.Shop_Address.setText(adminAccessModelList.get(position).getShop_Address());
        holder.Type.setText(adminAccessModelList.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return adminAccessModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Name,Email,Phone_Number,Password,Shop_Name,Shop_Address,Type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Name = itemView.findViewById(R.id.name);
            Email = itemView.findViewById(R.id.email);
            Phone_Number = itemView.findViewById(R.id.phone_admin);
            Password = itemView.findViewById(R.id.password);
            Shop_Name = itemView.findViewById(R.id.shopname);
            Shop_Address = itemView.findViewById(R.id.shopaddress);
            Type = itemView.findViewById(R.id.type);
        }
    }
}
