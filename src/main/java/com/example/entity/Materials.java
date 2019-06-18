package com.example.entity;

public class Materials {

    private String name;
    private String units;
    private double amont;
    private double unit_money;
    private String maplnfo_name;
    private String single_name;
    private String unit_engineering;

    public Materials() {
        super();
    }

    public Materials(String name, String units, double amont, double unit_money, String maplnfo_name, String single_name, String unit_engineering) {
        this.name = name;
        this.units = units;
        this.amont = amont;
        this.unit_money = unit_money;
        this.maplnfo_name = maplnfo_name;
        this.single_name = single_name;
        this.unit_engineering = unit_engineering;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public double getAmont() {
        return amont;
    }

    public void setAmont(double amont) {
        this.amont = amont;
    }

    public double getUnit_money() {
        return unit_money;
    }

    public void setUnit_money(double unit_money) {
        this.unit_money = unit_money;
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
        return "Materials{" +
                "name='" + name + '\'' +
                ", units='" + units + '\'' +
                ", amont=" + amont +
                ", unit_money=" + unit_money +
                ", maplnfo_name='" + maplnfo_name + '\'' +
                ", single_name='" + single_name + '\'' +
                ", unit_engineering='" + unit_engineering + '\'' +
                '}';
    }
}
