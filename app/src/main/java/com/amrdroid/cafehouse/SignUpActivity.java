package com.amrdroid.cafehouse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.internal.ViewUtils;

public class SignUpActivity extends AppCompatActivity {
    TextView alreadySignIn_txt;
    TextView fb_txt;
    TextView google_txt;
    ImageView person_img;
    TextView upload_txt;
    private final int GALLERY_REQ_CODE = 1000;

    //SharedPreference
    EditText name_edt;
    EditText number_edt;
    EditText email_edt;
    EditText password_edt;
    Button signUp_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        alreadySignIn_txt = findViewById(R.id.alreadyLogin_txt);
        fb_txt = findViewById(R.id.fb_txt);
        google_txt = findViewById(R.id.google_txt);
        name_edt = findViewById(R.id.name_edt);
        number_edt = findViewById(R.id.number_edt);
        email_edt = findViewById(R.id.email_edt);
        password_edt = findViewById(R.id.password_edt);
        signUp_btn = findViewById(R.id.singUp_btn);
        upload_txt = findViewById(R.id.uploadImage);
        person_img = findViewById(R.id.person_img);

        getSupportActionBar().setTitle("Sign Up");   //for title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //for back arrow

        alreadySignIn_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iLogin = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(iLogin);
                finish();
            }
        });
        fb_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(intent,"Login using");
                startActivity(shareIntent);
                //Toast.makeText(SignUpActivity.this, "Facebook Login!", Toast.LENGTH_SHORT).show();
            }
        });
        google_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(intent,"Google");
                startActivity(shareIntent);
               // Toast.makeText(SignUpActivity.this, "Google Login!", Toast.LENGTH_SHORT).show();
            }
        });

        //SharedPreference

        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name="";
                String number="";
                String email="";
                String password="";

                if(name_edt.getText().toString().length()>0 &&
                   number_edt.getText().toString().length()>0 &&
                   email_edt.getText().toString().length()>0 &&
                   password_edt.getText().toString().length()>0) {

                    name = name_edt.getText().toString();
                    number = number_edt.getText().toString();
                    email = email_edt.getText().toString();
                    password = password_edt.getText().toString();

                    SharedPreferences pref = getSharedPreferences("Data",MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("Name",name);
                    editor.putString("Number",number);
                    editor.putString("Email",email);
                    editor.putString("Password",password);
                    editor.apply();

                    Intent iLogin = new Intent(SignUpActivity.this,LoginActivity.class);
                    iLogin.putExtra("Email",email);
                    iLogin.putExtra("Password",password);
                    startActivity(iLogin);


                }else {
                    Toast.makeText(SignUpActivity.this, "Please enter complete details!", Toast.LENGTH_LONG).show();
                }

            }
        });

        upload_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,GALLERY_REQ_CODE);
            }
        });

        
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            if(requestCode==GALLERY_REQ_CODE) {
                person_img.setImageURI(data.getData());
            }
        }

    }
}