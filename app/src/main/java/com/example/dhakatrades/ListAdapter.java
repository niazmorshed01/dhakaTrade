package com.example.dhakatrades;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Company> implements Filterable {
    private static final String TAG="CompanyListAdapter";
    Boolean fav;
    int mResources;
    private ArrayList<Company> companyList;
    private Filter companyFilter;
    private Context mContext;
    private ArrayList<Company> originalList;

    public ListAdapter(@NonNull Context context, int resource, ArrayList<Company> objects) {
        super(context, resource, objects);
        originalList =objects;
        mContext=context;
        companyList=objects;
        mResources=resource;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        String name=getItem(position).getCompanyName();
        String code=getItem(position).getTradeCode();
        double ltp=getItem(position).getLtp();
        double change=getItem(position).getPriceChange();
        double changePercentage=getItem(position).getChangePercent();

        Company company=new Company(code,ltp,change,changePercentage);

        LayoutInflater inflater= LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResources,parent,false);
        TextView tltp = convertView.findViewById(R.id.ltp);
        TextView tcode = convertView.findViewById(R.id.tradeCode);
        TextView tchange = convertView.findViewById(R.id.priceChange);
        TextView tchangeP = convertView.findViewById(R.id.changePercent);
        TextView tName = convertView.findViewById(R.id.companyName);
        tcode.setText(code);
        if(code.startsWith("UP")){
            System.out.println(code);
        }
        if(change<0)tchange.setTextColor(ContextCompat.getColor(getContext(), R.color.orange_red));
        else tchange.setTextColor(ContextCompat.getColor(getContext(), R.color.forest_green));
        if(change<0)tchangeP.setTextColor(ContextCompat.getColor(getContext(), R.color.orange_red));
        else tchangeP.setTextColor(ContextCompat.getColor(getContext(), R.color.forest_green));
        tchange.setText("Change: " + change);
        tchangeP.setText(changePercentage + "%");
        tltp.setText("LTP: " + ltp);
        tName.setText(String.valueOf(name));

        return convertView;
    }
    public int getCount() {
        return companyList.size();
    }

    public Company getItem(int position) {
        return companyList.get(position);
    }

    public long getItemId(int position) {

        return companyList.get(position).hashCode();
    }


}



