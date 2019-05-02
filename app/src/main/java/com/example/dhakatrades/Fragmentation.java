package com.example.dhakatrades;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class Fragmentation extends AppCompatActivity {
    public static ListAdapter adapter;
    TabLayout tabLayout;
    GatherCompanyInfo CompanyList;
    Boolean fav;
    private SectionsPagerAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private ListView lv;
    private ArrayList<Company> companyList = new ArrayList<Company>();
    private ArrayList<String> favlist = new ArrayList<String>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmentation);

        mSectionsPageAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        setupTabIcons();
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getContext(), R.color.DeepPink);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getContext(), R.color.light_grey);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        CompanyList = new GatherCompanyInfo(companyList, getContext(), adapter, lv);
        adapter = CompanyList.getAdapter();

    }


    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.top);
        tabLayout.getTabAt(0).getIcon().setColorFilter(ContextCompat.getColor(getContext(), R.color.light_grey), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).setIcon(R.drawable.stock);
        tabLayout.getTabAt(1).getIcon().setColorFilter(ContextCompat.getColor(getContext(), R.color.light_grey), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).setIcon(R.drawable.box);
        tabLayout.getTabAt(2).getIcon().setColorFilter(ContextCompat.getColor(getContext(), R.color.light_grey), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(3).setIcon(R.drawable.profile);
        tabLayout.getTabAt(3).getIcon().setColorFilter(ContextCompat.getColor(getContext(), R.color.light_grey), PorterDuff.Mode.SRC_IN);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Top20Fragment(), "Top 20");
        adapter.addFragment(new StockListFragment(), "Stocks");
        adapter.addFragment(new Top20ByVolume(), "Value");
        adapter.addFragment(new ProfileFragment(), "Profile");
        viewPager.setAdapter(adapter);
    }


    private Context getContext() {
        return this;
    }

    public void logoutProfile(View view) {
        EntryActivity.sp.edit().putBoolean("logged", false).apply();
        Intent intent = new Intent(Fragmentation.this, EntryActivity.class);
        startActivity(intent);
    }
}