package com.example.demo_app.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_app.R;
import com.example.demo_app.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> cartModelList;
    int totalAmount = 0;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public MyCartAdapter(Context context, List<MyCartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(cartModelList.get(position).getProductName());
        holder.price.setText(cartModelList.get(position).getProductPrice());
        holder.date.setText(cartModelList.get(position).getCurrentDate());
        holder.time.setText(cartModelList.get(position).getCurrentTime());
        holder.quantity.setText(cartModelList.get(position).getTotalQuantity());
        holder.totalPrice.setText(String.valueOf(cartModelList.get(position).getTotalPrice()));
        holder.shop_name.setText(cartModelList.get(position).getShop_name());
        holder.shop_location.setText(cartModelList.get(position).getShop_location());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.custom_removecart_dialodue,null);
                TextView textView1;
                ImageView imageViewset;
                TextView textViewNo;
                TextView textViewYes;

                View dialogView1 = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.custom_succes_dialog,null);

                textView1 = dialogView.findViewById(R.id.textview1);
                imageViewset = dialogView.findViewById(R.id.imageViewset);
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
                                .collection("AddToCart")
                                .document(cartModelList.get(position).getDocumentId())
                                .delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            cartModelList.remove(cartModelList.get(position));
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

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                View dialogView = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.custom_removecart_dialodue,null);
                TextView textView1;
                ImageView imageViewset;
                TextView textViewNo;
                TextView textViewYes;

                View dialogView1 = LayoutInflater.from(v.getRootView().getContext()).inflate(R.layout.custom_succes_dialog,null);

                textView1 = dialogView.findViewById(R.id.textview1);
                imageViewset = dialogView.findViewById(R.id.imageViewset);
                textViewNo = dialogView.findViewById(R.id.textViewNo);
                textViewYes = dialogView.findViewById(R.id.textViewYes);

                textViewNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        builder.getContext();
                    }
                });

                textViewYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                                .collection("AddToCart")
                                .document(cartModelList.get(position).getDocumentId())
                                .delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            cartModelList.remove(cartModelList.get(position));
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
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,date,time,quantity,totalPrice,shop_name,shop_location;
        ImageView delete;
        Button remove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            date = itemView.findViewById(R.id.current_date);
            time = itemView.findViewById(R.id.current_time);
            quantity = itemView.findViewById(R.id.total_quantity);
            totalPrice = itemView.findViewById(R.id.total_price);
            delete = itemView.findViewById(R.id.delete);
            remove = itemView.findViewById(R.id.remove);
            shop_name = itemView.findViewById(R.id.shop_name);
            shop_location = itemView.findViewById(R.id.shop_location);
        }
    }
}
