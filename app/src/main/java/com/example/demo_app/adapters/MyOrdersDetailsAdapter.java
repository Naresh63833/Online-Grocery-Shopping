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
import com.example.demo_app.models.MyOrderDetailsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyOrdersDetailsAdapter extends RecyclerView.Adapter<MyOrdersDetailsAdapter.ViewHolder> {

    Context context;
    List<MyOrderDetailsModel> myOrderDetailsModelList;
    int totalAmount = 0;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public MyOrdersDetailsAdapter(Context context, List<MyOrderDetailsModel> myOrderDetailsModelList) {
        this.context = context;
        this.myOrderDetailsModelList = myOrderDetailsModelList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_orderdetails_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(myOrderDetailsModelList.get(position).getName());
        holder.phone.setText(myOrderDetailsModelList.get(position).getPhone());
        holder.address.setText(myOrderDetailsModelList.get(position).getAddress());
        holder.details.setText(myOrderDetailsModelList.get(position).getProducts());
        holder.amount.setText(myOrderDetailsModelList.get(position).getTotal());
        holder.date.setText(myOrderDetailsModelList.get(position).getCurrentDate());
        holder.time.setText(myOrderDetailsModelList.get(position).getCurrentTime());

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
                                .collection("MyOrderDetails")
                                .document(myOrderDetailsModelList.get(position).getDocumentId())
                                .delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            myOrderDetailsModelList.remove(myOrderDetailsModelList.get(position));
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
        return myOrderDetailsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone,address,details,amount,date,time;
        Button cancel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone_no);
            address = itemView.findViewById(R.id.address);
            details = itemView.findViewById(R.id.details);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            cancel = itemView.findViewById(R.id.cancel);

        }
    }
}
