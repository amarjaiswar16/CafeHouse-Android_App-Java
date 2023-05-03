package com.amrdroid.cafehouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.amrdroid.cafehouse.Adapters.FoodMainAdapter;
import com.amrdroid.cafehouse.Models.FoodMainModel;

import java.util.ArrayList;

public class FoodMainActivity extends AppCompatActivity {
    RecyclerView main_rv;
    ArrayList<FoodMainModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_main);
        main_rv = findViewById(R.id.main_rv);
        list = new ArrayList<>();

        list.add(new FoodMainModel(R.drawable.bicecream,"Chicken Cheese Burger","500","Chicken burger with extra cheese"));
        list.add(new FoodMainModel(R.drawable.burger,"Stuffed Bean Burger","400","Chicken burger with extra cheese"));
        list.add(new FoodMainModel(R.drawable.burgerwithchicken,"Crunchy Chicken burger","800","Chicken burger with extra cheese"));
        list.add(new FoodMainModel(R.drawable.bwithcocke,"Big King","300","Chicken burger with extra cheese"));
        list.add(new FoodMainModel(R.drawable.capcicumpizza,"Detroit Pizza","400","Pepperoni, brick cheese and tomato sauce."));
        list.add(new FoodMainModel(R.drawable.pizza,"Pizza","500","Topped with bits of tomato, onion, anchovies, and herbs."));
        list.add(new FoodMainModel(R.drawable.pop_2,"Burger","300","Chicken burger with extra cheese"));
        list.add(new FoodMainModel(R.drawable.pop_3,"Detroit Pizza","600","Tomato sauce, Mozzarella, Hot Italian salami, Hot chili peppers"));
        list.add(new FoodMainModel(R.drawable.cheesepizza,"Cipolla","500","Tomato sauce, Mozzarella, Onions, Oregano."));
        list.add(new FoodMainModel(R.drawable.cherrypizza,"Neapolitan Pizza","600","tomatoes, mozzarella from Campania."));
        list.add(new FoodMainModel(R.drawable.chickenpizza,"Sicilian Pizza","400","Topped with bits of tomato, onion, anchovies, and herbs."));
        list.add(new FoodMainModel(R.drawable.sandwich,"Supreme Veggie","200","Tomato sauce, Mozzarella, Hot Italian salami, Hot chili peppers."));
        list.add(new FoodMainModel(R.drawable.slider,"Tikki Burger Meal","300","Mayo, fresh onions, and tomatoes"));
        list.add(new FoodMainModel(R.drawable.tomatopizza,"Con cozze","500","Mussels, Garlic, Olive oil, Parsley"));
        list.add(new FoodMainModel(R.drawable.tripleburger,"Chicken Twin Burgers","800","Chicken burger with extra cheese"));
        list.add(new FoodMainModel(R.drawable.bicecream,"Chicken Cheese Burger","500","Chicken burger with extra cheese"));
        list.add(new FoodMainModel(R.drawable.burger,"Stuffed Bean Burger","400","Chicken burger with extra cheese"));
        list.add(new FoodMainModel(R.drawable.burgerwithchicken,"Crunchy Chicken burger","800","Chicken burger with extra cheese"));
        list.add(new FoodMainModel(R.drawable.bwithcocke,"Big King","300","Chicken burger with extra cheese"));
        list.add(new FoodMainModel(R.drawable.capcicumpizza,"Detroit Pizza","400","Pepperoni, brick cheese and tomato sauce."));
        list.add(new FoodMainModel(R.drawable.cheesepizza,"Cipolla","500","Tomato sauce, Mozzarella, Onions, Oregano."));
        list.add(new FoodMainModel(R.drawable.cherrypizza,"Neapolitan Pizza","600","tomatoes, mozzarella from Campania."));
        list.add(new FoodMainModel(R.drawable.chickenpizza,"Sicilian Pizza","400","Topped with bits of tomato, onion, anchovies, and herbs."));
        list.add(new FoodMainModel(R.drawable.sandwich,"Supreme Veggie","200","Tomato sauce, Mozzarella, Hot Italian salami, Hot chili peppers."));
        list.add(new FoodMainModel(R.drawable.slider,"Tikki Burger Meal","300","Mayo, fresh onions, and tomatoes"));
        list.add(new FoodMainModel(R.drawable.tomatopizza,"Con cozze","500","Mussels, Garlic, Olive oil, Parsley"));
        list.add(new FoodMainModel(R.drawable.tripleburger,"Chicken Twin Burgers","800","Chicken burger with extra cheese"));


        FoodMainAdapter adapter = new FoodMainAdapter(FoodMainActivity.this,list);
        main_rv.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        main_rv.setLayoutManager(linearLayoutManager);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.order_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.orderOption){
            Intent intent = new Intent(FoodMainActivity.this,OrderActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}