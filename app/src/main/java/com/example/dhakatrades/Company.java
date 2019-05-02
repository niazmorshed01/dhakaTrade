package com.example.dhakatrades;

import android.os.Parcel;
import android.os.Parcelable;

public class Company implements Parcelable {
    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };
    private String tradeCode;
    private String companyName;
    private double highest;
    private double lowest;
    private double priceChange;
    private double changePercent;
    private double closePrice;
    private double ltp;
    private double ycp;
    private double volume;
    private double trade;
    private double value;
    private boolean isFavourite;

    Company(String tradeCode, double ltp, double priceChange, double changePercent){
        this.tradeCode = tradeCode;
        this.ltp=ltp;
        this.priceChange = priceChange;
        this.changePercent = changePercent;
    }

    Company(String tradeCode, double ltp, double highest, double lowest, double closePrice, double ycp, double priceChange, double changePercent, double trade, double value, double volume){
        this.tradeCode = tradeCode;
        this.companyName = companyName;
        this.ltp=ltp;
        this.priceChange = priceChange;
        this.changePercent = changePercent;
        this.highest = highest;
        this.lowest = lowest;
        this.ycp=ycp;
        this.trade=trade;
        this.value=value;
        this.closePrice = closePrice;
        this.volume=volume;
    }

    public Company(Parcel in) {
        tradeCode = in.readString();
        companyName = in.readString();
        ltp=in.readDouble();
        priceChange =in.readDouble();
        changePercent =in.readDouble();
    }

    public double getClosePrice() {
        return closePrice;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int arg1) {
        // TODO Auto-generated method stub
        dest.writeString(tradeCode);
        dest.writeString(companyName);
        dest.writeDouble(ltp);
        dest.writeDouble(priceChange);
        dest.writeDouble(changePercent);
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getLtp() {
        return ltp;
    }

    public Double getPriceChange() {
        return priceChange;
    }

    public double getHighest() {
        return highest;
    }

    public double getLowest() {
        return lowest;
    }

    public double getYcp() {
        return ycp;
    }

    public double getTrade() {
        return trade;
    }

    public double getValue() {
        return value;
    }

    public Double getChangePercent() {
        return changePercent;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}