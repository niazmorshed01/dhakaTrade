package com.example.dhakatrades;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EntryActivity extends AppCompatActivity {
    public static SharedPreferences sp;
    Button login;
    private EditText etUsrName;
    private EditText etPass;
    private String userName;
    private String password;
    private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        etUsrName = findViewById(R.id.userNameId2);
        etPass = findViewById(R.id.passwordId2);

        login = findViewById(R.id.signInId);
        final Intent intent = new Intent(EntryActivity.this, Fragmentation.class);
        sp = getSharedPreferences("login",MODE_PRIVATE);
        if(sp.getBoolean("logged",false)){
            startActivity(intent);
        }

    }

    public void exitApp(View view) {
        sp.edit().putBoolean("logged",false).apply();
        finish();
    }
    public void signIn(View view) {
    final Intent intent = new Intent(EntryActivity.this, Fragmentation.class);
        pref = this.getSharedPreferences("Registration", 0);
        //retrieving value from Registration
        userName = pref.getString("UserName", null);
        password = pref.getString("Password", null);
        if(sp.getBoolean("logged",false)){
            startActivity(intent);
        }
                else if ( ( etUsrName.getText().toString().trim().equals("")) || ( etPass.getText().toString().trim().equals("")) )
                {
                    Toast.makeText(getApplicationContext(), "One or both field empty", Toast.LENGTH_SHORT).show();
                }
                else if(etUsrName.getText().toString().equals(userName)) {
                    if ((etPass.getText().toString()).equals(password)) {
                        startActivity(intent);
                        sp.edit().putBoolean("logged",true).apply();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Username or password does't match", Toast.LENGTH_SHORT).show();
                }


    }

    public void goToSignUp(View view) {
        Intent intent = new Intent(EntryActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}
