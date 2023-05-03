package com.amrdroid.cafehouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.amrdroid.cafehouse.Adapters.OrderAdapter;
import com.amrdroid.cafehouse.Models.OrderModel;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
RecyclerView order_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        order_rv = findViewById(R.id.order_rv);
        getSupportActionBar().setTitle("Orders");


//        list.add(new OrderModel(R.drawable.burger,"Burger","6547987","400"));
//        list.add(new OrderModel(R.drawable.burger,"Burger","6547987","400"));
//        list.add(new OrderModel(R.drawable.burger,"Burger","6547987","400"));
//        list.add(new OrderModel(R.drawable.burger,"Burger","6547987","400"));
//        list.add(new OrderModel(R.drawable.burger,"Burger","6547987","400"));
//        list.add(new OrderModel(R.drawable.burger,"Burger","6547987","400"));
//        list.add(new OrderModel(R.drawable.burger,"Burger","6547987","400"));
//        list.add(new OrderModel(R.drawable.burger,"Burger","6547987","400"));
//        list.add(new OrderModel(R.drawable.burger,"Burger","6547987","400"));
//        list.add(new OrderModel(R.drawable.burger,"Burger","6547987","400"));
//        list.add(new OrderModel(R.drawable.burger,"Burger","6547987","400"));

        MyDBHelper helper = new MyDBHelper(this);
        ArrayList<OrderModel> list = helper.getOrders();

        OrderAdapter adapter = new OrderAdapter(OrderActivity.this,list);
        order_rv.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        order_rv.setLayoutManager(layoutManager);


    }
}