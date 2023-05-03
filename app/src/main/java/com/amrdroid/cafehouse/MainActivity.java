package com.amrdroid.cafehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login_btn;
    Button register_btn;
    TextView skip_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_btn = findViewById(R.id.login_btn);
        register_btn = findViewById(R.id.register_btn);
        skip_txt = findViewById(R.id.skip_txt);

        //Hiding appbar as well as making notification bar transparent
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        getSupportActionBar().hide();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Login has been clicked", Toast.LENGTH_SHORT).show();
                Intent iLogin = new Intent(MainActivity.this,LoginActivity.class);
                iLogin.putExtra("FromLogin","Login");
                startActivity(iLogin);
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Register has been clicked", Toast.LENGTH_SHORT).show();
                Intent iRegister = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(iRegister);
            }
        });

        skip_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FoodMainActivity.class);
                //Intent intent = new Intent(MainActivity.this,OrderActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onBackPressed() {
         new AlertDialog.Builder(this)
                .setTitle("Exit?")
                .setIcon(R.drawable.ic_baseline_exit_to_app_24)
                .setMessage("Are you sure want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Welcome Back!", Toast.LENGTH_SHORT).show();

                    }
                }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {

                     }
                 }).show();


    }
}