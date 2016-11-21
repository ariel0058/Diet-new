package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class form2 extends AppCompatActivity implements View.OnClickListener {

    String age_num;
    String weight_num;
    String height_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);
		// get the background layout
		LinearLayout background_form1 = (LinearLayout)findViewById(R.id.form2_linear_layout);

		// set the listener to it
		background_form1.setOnClickListener(this);

		age_num = getIntent().getStringExtra("age");
    }


    public void getWeight(View view) {

        EditText height = (EditText) findViewById(R.id.editTextHeight);
        EditText weight1 = (EditText) findViewById(R.id.editTextWeight1);
        weight_num = weight1.getText().toString();
        height_num = height.getText().toString();

        if(TextUtils.isEmpty(weight_num)) {
            Toast.makeText(this, "weight cannot be empty!", Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(height_num)) {
            Toast.makeText(this, "height cannot be empty!", Toast.LENGTH_LONG).show();
        }else {
            Intent it = new Intent(this, form3.class);
            it.putExtra("height", height_num);
            it.putExtra("weight", weight_num);
            it.putExtra("age", age_num);
            startActivity(it);
        }

    }

    public void onClickLast(View v){
        Intent i = new Intent(this, form1.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
		if(v.getId() == R.id.form2_linear_layout) {
			// hide the keyboard
			InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		}
    }
}
