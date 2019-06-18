package com.example.entity;

public class Single {

    private int id;
    private String maplnfo_name;
    private String single_name;
    private double sum_money;
    private double salfe_money;
    private double fees_money;


    public Single() {
        super();
    }


    public Single(int id, String maplnfo_name, String single_name, double sum_money, double salfe_money, double fees_money) {
        this.id = id;
        this.maplnfo_name = maplnfo_name;
        this.single_name = single_name;
        this.sum_money = sum_money;
        this.salfe_money = salfe_money;
        this.fees_money = fees_money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getSum_money() {
        return sum_money;
    }

    public void setSum_money(double sum_money) {
        this.sum_money = sum_money;
    }

    public double getSalfe_money() {
        return salfe_money;
    }

    public void setSalfe_money(double salfe_money) {
        this.salfe_money = salfe_money;
    }

    public double getFees_money() {
        return fees_money;
    }

    public void setFees_money(double fees_money) {
        this.fees_money = fees_money;
    }

    @Override
    public String toString() {
        return "Single{" +
                "id=" + id +
                ", maplnfo_name='" + maplnfo_name + '\'' +
                ", single_name='" + single_name + '\'' +
                ", sum_money=" + sum_money +
                ", salfe_money=" + salfe_money +
                ", fees_money=" + fees_money +
                '}';
    }
}
