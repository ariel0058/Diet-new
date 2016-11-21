package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.DecimalFormat;

public class form3 extends AppCompatActivity {

    double bmi;
    String bmi_result;
    String temp;
    String temp1;
    String temp2;
    double current_weight;
    double height;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3);

        DecimalFormat df = new DecimalFormat("#.##");

        temp = getIntent().getStringExtra("weight");
        current_weight = Double.parseDouble(temp) * 0.45;
        temp1 = getIntent().getStringExtra("height");
        height = Double.parseDouble(temp1) / 100;
        temp2 = getIntent().getStringExtra("age");
        age = Integer.parseInt(temp2);

        //temp = String.valueOf(current_weight);

        bmi = current_weight/Math.pow(2,height);

        if(bmi < 18.5) {
            bmi_result = " " + df.format(bmi) + ", You are underweight.";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            bmi_result = " " + bmi + ", You are normal.";
        } else if (bmi >= 25 && bmi <= 29.9) {
            bmi_result = " " + df.format(bmi) + ", You are overweight.";
        } else  {
            bmi_result = " " + df.format(bmi) + ", You are OBESITY!!!.";
        }

        double idea_weight = (0.5 * bmi + 11.5) * Math.pow(2,height);
        int recommand_calories = (int) Math.floor( 66.67 + (13.75 * idea_weight) + (5 * height*100) - (6.76 * age ));


        TextView bmi_index = (TextView) findViewById(R.id.bmi_index);
        TextView target_weight = (TextView) findViewById(R.id.idea_weight);
        TextView daily_calories = (TextView) findViewById(R.id.daily_calories);

        bmi_index.setText(bmi_result);
        target_weight.setText(df.format(idea_weight) + " kg");
        daily_calories.setText(df.format(recommand_calories) + " calories");

        // store age
		// [1.1] get user id info
		ParseUser user = new ParseUser();
		ParseObject userInfo= new ParseObject("UserInfo");
		userInfo.put("user_id", user.getCurrentUser().getObjectId());
		userInfo.put("age", age);

		// store height
		// height * 100  cm
		userInfo.put("height", height * 100);

		// store bmi
		// df.format(bmi)
		userInfo.put("bmi", df.format(bmi));

		// store idea_weight
		// df.format(idea_weight)
		userInfo.put("ideal_weight", df.format(idea_weight));

		// store recommand_calories
		userInfo.put("recommended_calories", recommand_calories);

		// store current
		// current_weight // kg
		userInfo.put("current_weight", current_weight);
		userInfo.saveInBackground(new SaveCallback() {
			@Override
			public void done(ParseException e) {
				if (e == null) {

					Log.i("SaveInBackground", "Successful");

				} else {


					Log.i("SaveInBackground", "Failed. Error: " + e.toString());

				}
			}
		});
    }

    public void onClickLast(View v){
        Intent i = new Intent(this, form2.class);
        startActivity(i);
    }

    public void onClickNext(View v){
        Intent i = new Intent(this, menu.class);
        startActivity(i);
    }

}
