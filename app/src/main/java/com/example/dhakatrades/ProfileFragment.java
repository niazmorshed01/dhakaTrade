package com.example.dhakatrades;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    SharedPreferences pref;
    TextView tvName;
    TextView tvUserName;
    TextView tvBirthDate;
    TextView tvPhone;
    TextView tvEmail;
    Intent intent;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvName= getView().findViewById(R.id.prName);
        tvUserName = getActivity().findViewById(R.id.prUsername);
        tvPhone = getActivity().findViewById(R.id.prPhone);
        tvEmail = getActivity().findViewById(R.id.prEmail);
        tvBirthDate = getActivity().findViewById(R.id.prAge);
        //intent = getActivity().getIntent();
        pref = getActivity().getSharedPreferences("Registration", 0 );
        //retrieving value from Registration
        String firstName = pref.getString("FirstName", null);
        String lastName = pref.getString("LastName", null);
        String email = pref.getString("Email", null);
        String phone = pref.getString("Phone", null);
        String userName = pref.getString("UserName", null);
        String bdate = pref.getString("BirthDate", null);

        tvName.setText(firstName +" "+lastName);
        tvBirthDate.setText("Birth Date: "+bdate);
        tvEmail.setText("Email: "+email);
        tvPhone.setText("Phone Number: "+phone);
        tvUserName.setText("User Name: "+ userName);
    }

}
