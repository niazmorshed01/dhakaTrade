package com.example.dhakatrades;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText etFirst;
    EditText etLast;
    EditText etEmail;
    EditText etPhone;
    EditText etUsername;
    EditText etPassword;
    EditText etBdate;
    EditText etConfirmPassword;
    SharedPreferences pref;
    Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        View decorView = getWindow().getDecorView();

        etFirst = findViewById(R.id.firstNameId);
        etLast = findViewById(R.id.lastNameId);
        etPhone = findViewById(R.id.phoneId);
        etEmail = findViewById(R.id.emailId);
        etUsername = findViewById(R.id.userNameId);
        etPassword = findViewById(R.id.passwordId);
        etConfirmPassword = findViewById(R.id.confirmPasswordId);
        etBdate = findViewById(R.id.birthDateId);

        // creating an shared Preference file for the information to be stored
        // first argument is the name of file and second is the mode, 0 is private mode

        pref = getSharedPreferences("Registration", 0);
        // get editor to edit in file
        editor = pref.edit();

        // the tap of button we will fetch the information from three edittext    btn_register.setOnClickListener(new View.OnClickListener() {
    }


    public void signUp(View view) {
        String bpass = etPassword.getText().toString();
        final String cpass = etConfirmPassword.getText().toString();

        if (etFirst.getText().length() <= 0) {
            Toast.makeText(SignupActivity.this, "Enter First Name", Toast.LENGTH_SHORT).show();
        } else if (etLast.getText().length() <= 0) {
            Toast.makeText(SignupActivity.this, "Enter Last Name", Toast.LENGTH_SHORT).show();
        } else if (etEmail.getText().length() <= 0) {
            Toast.makeText(SignupActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
        } else if (etPhone.getText().length() <= 9) {
            Toast.makeText(SignupActivity.this, "Enter Phone number with atleast 9 digit", Toast.LENGTH_SHORT).show();
        } else if (etPassword.getText().length() <= 5) {
            Toast.makeText(SignupActivity.this, "Enter Password length 6", Toast.LENGTH_SHORT).show();
        } else if (etUsername.getText().length() <= 0) {
            Toast.makeText(SignupActivity.this, "Enter User Name", Toast.LENGTH_SHORT).show();
        } else if (etBdate.getText().length() <= 0) {
            Toast.makeText(SignupActivity.this, "Enter Birthdate", Toast.LENGTH_SHORT).show();
        } else if (bpass.equals( cpass)) {
            String firstName = etFirst.getText().toString();
            String lastName = etLast.getText().toString();
            String email = etEmail.getText().toString();
            String phone = etPhone.getText().toString();
            String userName = etUsername.getText().toString();
            String pass = etPassword.getText().toString();
            String conpass = etConfirmPassword.getText().toString();
            String bdate = etBdate.getText().toString();

            // as now we have information in string. Lets stored them with the help of editor
            editor.putString("FirstName", firstName);
            editor.putString("LastName", lastName);
            editor.putString("Email", email);
            editor.putString("Phone", phone);
            editor.putString("UserName", userName);
            editor.putString("Password", pass);
            editor.putString("ConfirmPassword", conpass);
            editor.putString("BirthDate", bdate);
            editor.apply();   // commit the values


            // after saving the value open next activity
            Intent ob = new Intent(SignupActivity.this, EntryActivity.class);
            startActivity(ob);
        } else {
            Toast.makeText(SignupActivity.this, "Password Doesn't Match", Toast.LENGTH_SHORT).show();

        }
    }
}
