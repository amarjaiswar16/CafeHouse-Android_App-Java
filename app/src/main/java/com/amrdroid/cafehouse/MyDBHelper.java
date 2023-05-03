package com.amrdroid.cafehouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.amrdroid.cafehouse.Models.OrderModel;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FoodDB";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "orders";

    public MyDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " (id integer primary key autoincrement, name text, phone text, price int, quantity int, image int, description text, foodname text)");               //

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertOrder(String name,String phone,String price,int image,String desc,String foodName,int quantity) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("foodname",foodName);
        values.put("quantity",quantity);

        //db.insert(TABLE_NAME,null,values);
        long id = db.insert(TABLE_NAME,null,values);
        if(id <= 0) {
            return false;
        }else {
            return true;
        }


    }
    public ArrayList<OrderModel> getOrders() {
        ArrayList<OrderModel> orderList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select id,foodname,image,price from "+TABLE_NAME,null);
        if(cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                OrderModel orderModel = new OrderModel();
                orderModel.setOrderNumber(cursor.getInt(0)+"");
                orderModel.setOrderName(cursor.getString(1));
                orderModel.setOrderImage(cursor.getInt(2));
                orderModel.setOrderPrice(cursor.getInt(3)+"");
                orderList.add(orderModel);
            }
        }
        cursor.close();
        db.close();

        return orderList;
    }
    public Cursor getOrderBy(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where id = "+ id,null);

        if(cursor != null)
            cursor.moveToFirst();

//        cursor.close();
//        db.close();
        return cursor;
    }
    public boolean updateOrder(String name,String phone,String price,int image,String desc,String foodName,int quantity,int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("foodname",foodName);
        values.put("quantity",quantity);

        //db.insert(TABLE_NAME,null,values);
        long raw = db.update(TABLE_NAME,values, "id="+id,null);
        if(raw <= 0) {
            return false;
        }else {
            return true;
        }


    }

    public int deleteOrder(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"id="+id,null);
    }



}
