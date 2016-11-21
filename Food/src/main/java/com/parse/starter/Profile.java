package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.parse.ParseUser;

import java.util.Map;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        UserInfoUtil user = new UserInfoUtil();
        Map<String, String> userInfo;
        userInfo = user.getUserInfoMap();

        TextView age = (TextView) findViewById(R.id.textView19);
        TextView height = (TextView) findViewById(R.id.textView11);
        TextView current = (TextView) findViewById(R.id.textView13);
        TextView ideal = (TextView) findViewById(R.id.textView15);
        TextView dailycalories = (TextView) findViewById(R.id.textView17);
        TextView username = (TextView) findViewById(R.id.username);

        age.setText(userInfo.get("age"));
        username.setText(userInfo.get("userName"));
        height.setText(userInfo.get("height")+" cm");
        current.setText(userInfo.get("current_weight")+" lb");
        ideal.setText(userInfo.get("ideal_weight")+" lb");
        dailycalories.setText(userInfo.get("recommended_calories")+" kcal");
    }

    public void onClickLast(View v){
        Intent i = new Intent(this, menu.class);
        startActivity(i);
    }

    // log out the user
	public void onClickLogout(View v) {
		ParseUser.logOut();
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}

}
