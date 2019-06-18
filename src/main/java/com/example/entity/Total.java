package com.example.entity;

public class Total {

    private String bop;
    private  String project_name;
    private Double rate;
    private Double money;
    private String maplnfo_name;
    private String single_name;
    private String unit_engineering;

    public Total() {
        super();
    }

    public Total(String bop, String project_name, Double rate, Double money, String maplnfo_name, String single_name, String unit_engineering) {
        this.bop = bop;
        this.project_name = project_name;
        this.rate = rate;
        this.money = money;
        this.maplnfo_name = maplnfo_name;
        this.single_name = single_name;
        this.unit_engineering = unit_engineering;
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
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

    @Override
    public String toString() {
        return "Total{" +
                "bop='" + bop + '\'' +
                ", project_name='" + project_name + '\'' +
                ", rate=" + rate +
                ", money=" + money +
                ", maplnfo_name='" + maplnfo_name + '\'' +
                ", single_name='" + single_name + '\'' +
                ", unit_engineering='" + unit_engineering + '\'' +
                '}';
    }


}
