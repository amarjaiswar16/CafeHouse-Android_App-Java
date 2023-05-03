package com.amrdroid.cafehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView sendToRegister_txt;
    TextView f_txt; //facebook
    TextView g_txt; //google

    //SharedPreference
    EditText log_email_edt;
    EditText log_pass_edt;
    Button signIn_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sendToRegister_txt = findViewById(R.id.sendToRegister_txt);
        f_txt =findViewById(R.id.f_txt);
        g_txt = findViewById(R.id.g_txt);
        log_email_edt = findViewById(R.id.log_email_edt);
        log_pass_edt = findViewById(R.id.log_pass_edt);
        signIn_btn = findViewById(R.id.signIn_btn);


        getSupportActionBar().setTitle("Sign In");  //for title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //for back arrow

        sendToRegister_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iReg = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(iReg);
                finish();
            }
        });
        f_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(intent,"Login using");
                startActivity(shareIntent);
                //Toast.makeText(LoginActivity.this, "Facebook Login!", Toast.LENGTH_SHORT).show();
            }
        });
        g_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(intent,"Google");
                startActivity(shareIntent);
               // Toast.makeText(LoginActivity.this, "Google Login!", Toast.LENGTH_SHORT).show();
            }
        });

        SharedPreferences pref = getSharedPreferences("Data",MODE_PRIVATE);
        String name = pref.getString("Name","");
        String number = pref.getString("Number","");
        String email = pref.getString("Email","");
        String password = pref.getString("Password","");

//        Log.d("Name",name);
//        Log.d("Number",number);
//        Log.d("Email",email);
//        Log.d("Password",password);

        Intent fromSignUp = getIntent();
        String emailValue = fromSignUp.getStringExtra("Email");
        String passValue = fromSignUp.getStringExtra("Password");
        log_email_edt.setText(emailValue);
        log_pass_edt.setText(passValue);




            signIn_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email="";
                    String password="";
                    if(log_email_edt.getText().toString().length()>0 &&
                            log_pass_edt.getText().toString().length()>0) {

                        email = log_email_edt.getText().toString();
                        password = log_pass_edt.getText().toString();

                        Intent intent = new Intent(LoginActivity.this,FoodMainActivity.class);
                        startActivity(intent);
                      //  Toast.makeText(LoginActivity.this, "Email: " + email + ", Pass: " + password, Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Please enter complete details!", Toast.LENGTH_SHORT).show();
                    }


                }
            });






        
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}