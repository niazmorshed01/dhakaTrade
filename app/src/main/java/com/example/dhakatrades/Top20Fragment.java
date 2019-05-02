package com.example.dhakatrades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Top20Fragment extends Fragment {
    private static final String TAG= "profile";
    ListView lv;
    ArrayList<Company> arrayList = new ArrayList<Company>();
    private Button btnTest;
    private ListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_top20, container, false);
        return  view;

    }
   /* @Override
    public void onResume() {
        super.onResume();
        arrayList = (ArrayList<Company>) getTasksFromSharedPrefs(getContext());
        if (arrayList == null){
            arrayList = new ArrayList<Company>();

        }

    }
*/


   /* @Override
    public void onPause() {
        super.onPause();
        saveTasksToSharedPrefs(getContext());
    }*/
    @Override
    public void onStart() {
        super.onStart();
        lv = getView().findViewById(R.id.companyList);
        GatherTop20Info CompanyList = new GatherTop20Info(arrayList, getContext(), adapter, lv);
        CompanyList.execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {
                if (adapter == null) {
                    adapter = new ListAdapter(getContext(), R.layout.adapter_view, arrayList);
                }
                Company selItem = adapter.getItem(position);
                Gson string = new Gson();
                String json = string.toJson(selItem);
                Intent intent = new Intent(getActivity(), CompanyInfo.class);
                intent.putExtra("company", json);
                startActivity(intent);
            }
        });

    }

    public void saveTasksToSharedPrefs(Context context) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList); //tasks is an ArrayList instance variable
        prefsEditor.putString("currentTasks", json);
        prefsEditor.commit();
    }
    public List<Company> getTasksFromSharedPrefs(Context context) {
        SharedPreferences appSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("currentTasks", "");
        arrayList = gson.fromJson(json, new TypeToken<ArrayList<Company>>(){}.getType());
        return arrayList;
    }
}