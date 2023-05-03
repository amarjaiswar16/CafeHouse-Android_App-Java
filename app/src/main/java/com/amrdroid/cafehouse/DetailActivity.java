package com.amrdroid.cafehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    ImageView detailFoodImage_img;
    TextView detailFoodName_txt;
    TextView detailFoodDesc_txt;
    TextView detailFoodPrice_txt;
    EditText detailName_edt;
    EditText detailNumber_edt;
    Button orderNow_btn;
    int orderNo=1;

    //update
    ImageView minus_img;
    ImageView plus_img;
    TextView update_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailFoodImage_img = findViewById(R.id.detailFoodImage);
        detailFoodName_txt = findViewById(R.id.detailFoodName_txt);
        detailFoodDesc_txt = findViewById(R.id.detailFoodDesc_txt);
        detailName_edt = findViewById(R.id.detailName_edt);
        detailNumber_edt = findViewById(R.id.detailNumber_edt);
        orderNow_btn = findViewById(R.id.orderNow_btn);
        detailFoodPrice_txt = findViewById(R.id.detailFoodPrice_txt);

        minus_img = findViewById(R.id.minus_img);
        plus_img = findViewById(R.id.plus_img);
        update_txt = findViewById(R.id.update_txt);

        //start
        plus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNo++;
                update_txt.setText(String.valueOf(orderNo));
            }
        });
        minus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderNo--;
                update_txt.setText(String.valueOf(orderNo));
            }
        });
        //end

        //Database
        MyDBHelper helper = new MyDBHelper(DetailActivity.this);

        if(getIntent().getIntExtra("type",0) == 1) {

            Intent iFromFM = getIntent(); //from main food adapter
            int image = iFromFM.getIntExtra("image",0);
            String name = iFromFM.getStringExtra("name");
            //int priceInt = Integer.parseInt(iFromFM.getStringExtra("price"));
            String price = iFromFM.getStringExtra("price");
            String description = iFromFM.getStringExtra("description");

            getSupportActionBar().setTitle("Place Order");


            //setting the data
            detailFoodImage_img.setImageResource(image);
            detailFoodName_txt.setText(name);
           // detailFoodPrice_txt.setText(String.format("%d",price));
             detailFoodPrice_txt.setText(price);
            detailFoodDesc_txt.setText(description);




            orderNow_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String UserName="";
                    String number="";
                    int quantity = 1;
                    if(detailName_edt.getText().toString().length()>0 &&
                            detailNumber_edt.getText().toString().length()>0) {
                        UserName = detailName_edt.getText().toString();
                        number = detailNumber_edt.getText().toString();
                        quantity = Integer.parseInt(update_txt.getText().toString());

                        Boolean isInserted = helper.insertOrder(UserName,number,price,image,description,name,quantity);
                        if(isInserted) {
                            Toast.makeText(DetailActivity.this, "Data Success!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(DetailActivity.this,OrderActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else
                            Toast.makeText(DetailActivity.this, "Error!", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(DetailActivity.this, "Please enter details!", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else {

             int id = getIntent().getIntExtra("id",0);
             Cursor cursor = helper.getOrderBy(id);
           // Toast.makeText(this, cursor.getString(1), Toast.LENGTH_SHORT).show();
            int image = cursor.getInt(5);
            detailFoodImage_img.setImageResource(cursor.getInt(5));
            detailFoodName_txt.setText(cursor.getString(7));
             detailFoodPrice_txt.setText(String.format("%d",cursor.getInt(3)));
            //detailFoodPrice_txt.setText(price);
            detailFoodDesc_txt.setText(cursor.getString(6));
            update_txt.setText(String.format("%d",cursor.getInt(4)));

            detailName_edt.setText(cursor.getString(1));
            detailNumber_edt.setText(cursor.getString(2));

            getSupportActionBar().setTitle("Update Order");
            orderNow_btn.setText("Update Now");
            orderNow_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String UserName="";
                    String number="";
                    int quantity = 1;
                    if(detailName_edt.getText().toString().length()>0 &&
                            detailNumber_edt.getText().toString().length()>0) {
                        UserName = detailName_edt.getText().toString();
                        number = detailNumber_edt.getText().toString();
                        quantity = Integer.parseInt(update_txt.getText().toString());

                        //Boolean isInserted = helper.insertOrder(UserName,number,price,image,description,name,quantity);
                        Boolean isUpdated = helper.updateOrder(UserName,number,detailFoodPrice_txt.getText().toString(),image,detailFoodDesc_txt.getText().toString(),detailFoodName_txt.getText().toString(),quantity,id);             //
                        if(isUpdated)
                            Toast.makeText(DetailActivity.this, "Updated!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(DetailActivity.this, "Failed!", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(DetailActivity.this, "Please enter details!", Toast.LENGTH_SHORT).show();
                    }



                }
            });



        }


    }
}