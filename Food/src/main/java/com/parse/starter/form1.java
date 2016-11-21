package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class form1 extends AppCompatActivity implements View.OnClickListener {

    String temp;
    int age_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);
        TextView tv = (TextView)findViewById(R.id.tv_name);
		// get the background layout
		LinearLayout background_form1 = (LinearLayout)findViewById(R.id.form1_linear_layout);

        // set the listener to it
		background_form1.setOnClickListener(this);

		if(ParseUser.getCurrentUser() != null) {  // which means the user is logged in
            // Log.i("currentUser", "User logged in " + ParseUser.getCurrentUser().getUsername());
            tv.setText("User logged in as " + ParseUser.getCurrentUser().getUsername());
        } else {
            Toast.makeText(this, "Fail to login", Toast.LENGTH_LONG).show();
        }
    }

    public void getAge(View v) {
        EditText age = (EditText) findViewById(R.id.editTextAge);
        temp = age.getText().toString();

        if(TextUtils.isEmpty(temp)) {
            Toast.makeText(this, "age cannot be empty!", Toast.LENGTH_LONG).show();
        }else {
            Intent it = new Intent(this, form2.class);
            //age_num = Integer.parseInt(temp);
            //it.putExtra("gender",genderText);
            it.putExtra("age", temp);
            startActivity(it);
        }

        // Toast toast = Toast.makeText(this, temp, Toast.LENGTH_LONG);
        // toast.show();
    }

    @Override
    public void onClick(View v) {
		if(v.getId() == R.id.form1_linear_layout) {
			// hide the keyboard
			InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		}
    }
}
