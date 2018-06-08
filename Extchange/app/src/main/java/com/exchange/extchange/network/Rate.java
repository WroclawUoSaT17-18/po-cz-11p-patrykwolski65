package com.exchange.extchange.network;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rate implements Serializable {

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("mid")
    @Expose
    private double mid;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getMid() {
        return mid;
    }

    public void setMid(double mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return code;
    }
}
