package com.example.dhakatrades;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    SharedPreferences pref;
    TextView tvName;
    TextView tvUserName;
    TextView tvBirthDate;
    TextView tvPhone;
    TextView tvEmail;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName=findViewById(R.id.prName);
        tvUserName = findViewById(R.id.prUsername);
        tvPhone = findViewById(R.id.prPhone);
        tvEmail = findViewById(R.id.prEmail);
        tvBirthDate = findViewById(R.id.prAge);
        intent = getIntent();
        pref = getSharedPreferences("Registration", 0 );
         //retrieving value from Registration
        String firstName = pref.getString("FirstName", null);
        String lastName = pref.getString("LastName", null);
        String email = pref.getString("Email", null);
        String phone = pref.getString("Phone", null);
        String userName = pref.getString("UserName", null);
        String bdate = pref.getString("BirthDate", null);

        tvName.setText("Name: "+ firstName +" "+lastName);
        tvBirthDate.setText("Birth Date: "+bdate);
        tvEmail.setText("Email: "+email);
        tvPhone.setText("Phone Number: "+phone);
        tvUserName.setText("User Name: "+ userName);
    }

}
