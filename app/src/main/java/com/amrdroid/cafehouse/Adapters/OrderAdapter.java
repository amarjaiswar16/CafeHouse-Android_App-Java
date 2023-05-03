package com.amrdroid.cafehouse.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amrdroid.cafehouse.DetailActivity;
import com.amrdroid.cafehouse.Models.OrderModel;
import com.amrdroid.cafehouse.MyDBHelper;
import com.amrdroid.cafehouse.OrderActivity;
import com.amrdroid.cafehouse.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Context context;
    ArrayList<OrderModel> list;
    public OrderAdapter(Context context, ArrayList<OrderModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sampleorder_cardview,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.orderImage.setImageResource(list.get(position).getOrderImage());
        holder.orderName.setText(list.get(position).getOrderName());
        holder.orderNumber.setText(list.get(position).getOrderNumber());
        holder.orderPrice.setText(list.get(position).getOrderPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id",Integer.parseInt(list.get(position).getOrderNumber()));
                intent.putExtra("type",2);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setMessage("Are you sure want to delete?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MyDBHelper helper = new MyDBHelper(context);
                                if(helper.deleteOrder(list.get(position).getOrderNumber()) > 0) {
                                    Toast.makeText(context, "Item Deleted!", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(context, OrderActivity.class);
//                                    context.startActivity(intent);

                                }else {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();


                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView orderImage;
        TextView orderName;
        TextView orderNumber;
        TextView orderPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           orderImage = itemView.findViewById(R.id.orderImage);
           orderName = itemView.findViewById(R.id.orderName);
           orderNumber = itemView.findViewById(R.id.orderNumber);
           orderPrice = itemView.findViewById(R.id.orderPrice);

        }
    }
}
