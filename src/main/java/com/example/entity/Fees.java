package com.example.entity;

public class Fees {

    private String project_name;
    private double base;
    private double rate;
    private String maplnfo_name;
    private String single_name;
    private Double money;
    private String unit_engineering;

    public Fees() {
        super();
    }

    public Fees(String project_name, double base, double rate, String maplnfo_name, String single_name, Double money, String unit_engineering) {
        this.project_name = project_name;
        this.base = base;
        this.rate = rate;
        this.maplnfo_name = maplnfo_name;
        this.single_name = single_name;
        this.money = money;
        this.unit_engineering = unit_engineering;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Fees{" +
                "project_name='" + project_name + '\'' +
                ", base=" + base +
                ", rate=" + rate +
                ", maplnfo_name='" + maplnfo_name + '\'' +
                ", single_name='" + single_name + '\'' +
                ", money=" + money +
                ", unit_engineering='" + unit_engineering + '\'' +
                '}';
    }
}
