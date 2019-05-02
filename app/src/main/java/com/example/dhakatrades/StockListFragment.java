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
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class StockListFragment extends Fragment {
    public static ListView lv;
    Intent intent;
    View v;
    private SharedPreferences sharedPreferences;
    private EditText searchView = null;
    private ArrayList<Company> companyList = new ArrayList<Company>();
    private ListAdapter adapter;

    public static ListView getLv() {
        return lv;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        if (v == null)
            v = inflater.inflate(R.layout.fragment_list,
                    container, false
            );
        return v;
    }

    /*@Override
    public void onResume() {
        super.onResume();
        companyList = (ArrayList<Company>) getTasksFromSharedPrefs(getContext());
        if (companyList == null){
            companyList = new ArrayList<>();
        } //I put the above in the onResume method. Check Sean McClane's article for info on the Android life cycle!

    }*/

    @Override
    public void onStart() {
        super.onStart();
        //companyList = (ArrayList<Company>) getTasksFromSharedPrefs(getContext());
        lv = getActivity().findViewById(R.id.companyLists);
        GatherCompanyInfo CompanyList = new GatherCompanyInfo(companyList, getContext(), adapter, lv);
        CompanyList.execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {
                if (adapter == null) {
                    adapter = new ListAdapter(getContext(), R.layout.adapter_view_fav, companyList);
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

}

