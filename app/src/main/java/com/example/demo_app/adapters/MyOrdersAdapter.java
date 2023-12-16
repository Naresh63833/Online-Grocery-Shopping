package com.example.demo_app.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_app.R;
import com.example.demo_app.models.MyOrdersModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyOrdersAdapter extends RecyclerView.Adapter<MyOrdersAdapter.ViewHolder> {

    Context context;
    List<MyOrdersModel> myOrdersModelList;
    int totalAmount = 0;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public MyOrdersAdapter(Context context, List<MyOrdersModel> myOrdersModelList) {
        this.context = context;
        this.myOrdersModelList = myOrdersModelList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_orders_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(myOrdersModelList.get(position).getProductName());
        holder.price.setText(myOrdersModelList.get(position).getProductPrice());
        holder.date.setText(myOrdersModelList.get(position).getCurrentDate());
        holder.time.setText(myOrdersModelList.get(position).getCurrentTime());
        holder.quantity.setText(myOrdersModelList.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(myOrdersModelList.get(position).getTotalPrice()));
        holder.shop_name.setText(myOrdersModelList.get(position).getShop_name());
        holder.shop_location.setText(myOrdersModelList.get(position).getShop_location());


        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingInflatedId")
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.custom_cancelorder_dialogue,null);

                TextView textViewNo;
                TextView textViewYes;

                View dialogView1 = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.custom_ordercancel_dialog,null);

                textViewNo = dialogView.findViewById(R.id.textViewNo);
                textViewYes = dialogView.findViewById(R.id.textViewYes);

                textViewNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                textViewYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                                .collection("MyOrder")
                                .document(myOrdersModelList.get(position).getDocumentId())
                                .delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            myOrdersModelList.remove(myOrdersModelList.get(position));
                                            notifyDataSetChanged();
                                            Toast.makeText(context, "Item Removed", Toast.LENGTH_SHORT).show();
                                            builder.setView(dialogView1);
                                            builder.show();
                                        } else {
                                            Toast.makeText(context, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                });

                builder.setView(dialogView);
                builder.setCancelable(true);
                builder.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return myOrdersModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,date,time,quantity,totalPrice,shop_name,shop_location;
        Button cancel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            quantity = itemView.findViewById(R.id.total_quantity);
            totalPrice = itemView.findViewById(R.id.total_price);
            cancel = itemView.findViewById(R.id.cancel);
            shop_name = itemView.findViewById(R.id.shop_name);
            shop_location = itemView.findViewById(R.id.shop_location);
        }
    }
}
