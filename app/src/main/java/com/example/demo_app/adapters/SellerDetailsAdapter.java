package com.example.demo_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_app.R;
import com.example.demo_app.models.SellerDetailsModel;

import java.util.List;

public class SellerDetailsAdapter extends RecyclerView.Adapter<SellerDetailsAdapter.ViewHolder> {

    Context context;
    List<SellerDetailsModel> sellerDetailsModelList;

    public SellerDetailsAdapter(Context context, List<SellerDetailsModel> sellerDetailsModelList) {
        this.context = context;
        this.sellerDetailsModelList = sellerDetailsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.Name.setText(sellerDetailsModelList.get(position).getName());
        holder.Email.setText(sellerDetailsModelList.get(position).getEmail());
        holder.Phone_Number.setText(sellerDetailsModelList.get(position).getPhone_Number());
        holder.Password.setText(sellerDetailsModelList.get(position).getPassword());
        holder.Shop_Name.setText(sellerDetailsModelList.get(position).getShop_Name());
        holder.Shop_Address.setText(sellerDetailsModelList.get(position).getShop_Address());
        holder.Type.setText(sellerDetailsModelList.get(position).getType());

    }

    @Override
    public int getItemCount() {
        return sellerDetailsModelList.size();
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
