package com.parse.starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onClickCalorie(View v){
        Intent i = new Intent(this, Calorie.class);
        startActivity(i);
    }

    public void onClickRecipe(View v){
        Intent i = new Intent(this, form3.class);
        startActivity(i);
    }

    public void onClickProfile(View v){
        Intent i = new Intent(this, Profile.class);
        startActivity(i);
    }

}
