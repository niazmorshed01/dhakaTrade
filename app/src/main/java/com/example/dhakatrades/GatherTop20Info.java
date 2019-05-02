package com.example.dhakatrades;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class GatherTop20Info extends AsyncTask<Void, Void, Void>{
    int counter = 0;
    private Context context;
    private ArrayList<Company> favouriteList;
    private ListView listView;
    private ListAdapter adapter;
    private String fav = "";
    GatherTop20Info(ArrayList<Company> companyList, Context context, ListAdapter adapter, ListView listView) {
        this.favouriteList = companyList;
        this.context=context;
        this.adapter=adapter;
        this.listView = listView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            favouriteList.clear();
        } catch (Exception e) {

        }
        try {
            Document company = null;
            try {
                company = Jsoup.connect("https://dsebd.org/latest_share_price_all_by_value.php").get();
            } catch (SocketTimeoutException e) {
                String d=e.getStackTrace().toString();
                System.out.println(d);
            }
            Elements importantLinks = company.select("tr");
            boolean first = true;
            for (Element data : importantLinks) {
                if(counter<=20)
                if (!first) {
                    String[] strings = data.text().split(" ");
                    String code = strings[1];
                    {
                        double high;
                        double low;
                        try {
                            low = Double.parseDouble(strings[4].replace(",", ""));
                        } catch (Exception c) {
                            low = 0;
                        }
                        try {
                            high = Double.parseDouble(strings[3].replace(",", ""));
                        } catch (Exception b) {
                            high = 0;
                        }
                        double ltp;
                        try {
                            ltp = Double.parseDouble(strings[2].replace(",", ""));
                        } catch (Exception a) {
                            ltp = 0;
                        }

                        double ycp;
                        try {
                            ycp = Double.parseDouble(strings[6].replace(",", ""));
                        } catch (Exception e) {
                            ycp = 0;
                        }
                        double closep;
                        try {
                            closep = Double.parseDouble(strings[5].replace(",", ""));
                        } catch (Exception de) {
                            closep = 0;
                        }
                        double change;
                        try {
                            change = Double.parseDouble(strings[7].replace(",", ""));
                        } catch (Exception f) {
                            change = 0;
                        }
                        double changeP;
                        if (ltp > 0)
                            changeP = Double.parseDouble(String.format("%.2f", (((ltp - ycp) * 100) / ycp)));
                        else changeP = 0;
                        double trade;
                        try {
                            trade = Double.parseDouble(strings[8].replace(",", ""));
                        } catch (Exception g) {
                            trade = 0;
                        }

                        double volume;
                        try {
                            volume = Double.parseDouble(strings[10].replace(",", ""));
                        } catch (Exception h) {
                            volume = 0;
                        }
                        double value;
                        try {
                            value = Double.parseDouble(strings[9].replace(",", ""));
                        } catch (Exception j) {
                            value = 0;
                        }
                         {
                            favouriteList.add(new Company(code, ltp, high, low, closep, ycp, change, changeP, trade, value, volume));
                            counter++;
                        }
                    }
                }
                first = false;
            }


            company = Jsoup.connect("http://www.dsebd.org/company%20listing.php").get();

            importantLinks = company.getElementsByTag("font");
            for (Element link : importantLinks) {
                if (link.text().contains("(")) {
                    String linkText = link.text().substring((link.text().indexOf(' ') + 2), link.text().lastIndexOf(')'));
                    String code = link.text().substring(0, link.text().indexOf(' '));

                    //System.out.println(linkText+"+"+code);//THIS IS THE TEXT I WANTED...
                    if (code.toUpperCase().equals(code) && !code.contains("T20") && !code.contains("T05") && !code.contains("T10") && !code.contains("T15") && !code.contains("T5") && code.length() > 1) {
                        String name = linkText;
                        //System.out.println(name);
                        for (Company a : favouriteList) {
                            if (a.getTradeCode().equals(code)) {
                                a.setCompanyName((name));
                                //System.out.println("Company Name: "+a.getCompanyName()+"\nCompany Code:"+a.getTradeCode());
                                break;
                            }
                        }

                        //companyList.add(new Company(name, ltp, change, changeP));
                        //System.out.println(name);

                    }

                }

            }

        } catch (Exception e) {
            String d=e.getStackTrace().toString();
            System.out.println(d);
        }

        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        try{
            adapter=new ListAdapter(context, R.layout.adapter_view,favouriteList);
            listView.setAdapter(adapter);
            //Toast.makeText(context, "field empty", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            String d=e.getStackTrace().toString();
            System.out.println(d);
        }

    }
    public ListAdapter getAdapter(){
        return adapter;
    }

}
