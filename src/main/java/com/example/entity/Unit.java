package com.example.entity;

import java.util.ArrayList;

public class Unit {

    private String bop;
    private String project_name;
    private String signalment;
    private String prickle;
    private double amount;
    private double integrated_unit_price;
    private String maplnfo_name;
    private String single_name;
    private String unit_engineering;
    private String partial_name;

    public Unit() {
        super();
    }

    public Unit(String bop, String project_name, String signalment, String prickle, double amount, double integrated_unit_price, String maplnfo_name, String single_name, String unit_engineering, String partial_name) {
        this.bop = bop;
        this.project_name = project_name;
        this.signalment = signalment;
        this.prickle = prickle;
        this.amount = amount;
        this.integrated_unit_price = integrated_unit_price;
        this.maplnfo_name = maplnfo_name;
        this.single_name = single_name;
        this.unit_engineering = unit_engineering;
        this.partial_name = partial_name;
    }

    public String getBop() {
        return bop;
    }

    public void setBop(String bop) {
        this.bop = bop;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getSignalment() {
        return signalment;
    }

    public void setSignalment(String signalment) {
        this.signalment = signalment;
    }

    public String getPrickle() {
        return prickle;
    }

    public void setPrickle(String prickle) {
        this.prickle = prickle;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getIntegrated_unit_price() {
        return integrated_unit_price;
    }

    public void setIntegrated_unit_price(double integrated_unit_price) {
        this.integrated_unit_price = integrated_unit_price;
    }

    public String getMaplnfo_name() {
        return maplnfo_name;
    }

    public void setMaplnfo_name(String maplnfo_name) {
        this.maplnfo_name = maplnfo_name;
    }

    public String getSingle_name() {
        return single_name;
    }

    public void setSingle_name(String single_name) {
        this.single_name = single_name;
    }

    public String getUnit_engineering() {
        return unit_engineering;
    }

    public void setUnit_engineering(String unit_engineering) {
        this.unit_engineering = unit_engineering;
    }

    public String getPartial_name() {
        return partial_name;
    }

    public void setPartial_name(String partial_name) {
        this.partial_name = partial_name;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "bop='" + bop + '\'' +
                ", project_name='" + project_name + '\'' +
                ", signalment='" + signalment + '\'' +
                ", prickle='" + prickle + '\'' +
                ", amount=" + amount +
                ", integrated_unit_price=" + integrated_unit_price +
                ", maplnfo_name='" + maplnfo_name + '\'' +
                ", single_name='" + single_name + '\'' +
                ", unit_engineering='" + unit_engineering + '\'' +
                ", partial_name='" + partial_name + '\'' +
                '}';
    }
}
