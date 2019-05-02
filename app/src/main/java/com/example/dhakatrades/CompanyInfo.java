package com.example.dhakatrades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class CompanyInfo extends AppCompatActivity {
    Intent intent;
    private TextView name;
    private TextView code;
    private TextView ltp;
    private TextView ycp;
    private TextView tcp;
    private TextView volume;
    private TextView trade;
    private TextView value;
    private TextView high;
    private TextView low;
    private TextView change;
    private TextView changeP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_info);
        code=findViewById(R.id.tradeCodeInfo);
        name=findViewById(R.id.companyName);
        ltp=findViewById(R.id.ltp);
        ycp=findViewById(R.id.ycp);
        tcp=findViewById(R.id.tcp);
        volume=findViewById(R.id.volume);
        trade=findViewById(R.id.trade);
        value=findViewById(R.id.value);
        high=findViewById(R.id.highest);
        low=findViewById(R.id.lowest);
        change=findViewById(R.id.priceChange);
        changeP=findViewById(R.id.changePercent);
        String json= getIntent().getStringExtra("company");
        Gson string=new Gson();
        Type type = new TypeToken<Company>() {}.getType();
        Company company= string.fromJson(json, type);

        name.setText((company.getCompanyName()));
        code.setText(company.getTradeCode());
        ltp.setText(company.getLtp().toString());
        ycp.setText(String.valueOf(company.getYcp()));
        tcp.setText(String.valueOf(company.getClosePrice()));
        volume.setText((String.valueOf(company.getVolume())));
        trade.setText(String.valueOf(company.getTrade()));
        value.setText(String.valueOf(company.getValue()));
        high.setText(String.valueOf(company.getHighest()));
        low.setText(String.valueOf(company.getLowest()));
        change.setText(String.valueOf(company.getPriceChange()));
        changeP.setText(company.getChangePercent() + "%");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Intent intent = new Intent(CompanyInfo.this, Fragmentation.class);
    }
}
