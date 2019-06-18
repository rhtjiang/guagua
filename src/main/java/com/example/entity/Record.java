package com.example.entity;

public class Record {
    private int id;
    private String maplnfo_name;
    private String single_name;
    private String unit_name;
    private String partial_name;
    private String project_name;
    private double old_match;
    private double new_match;
    private String signalment;
    private int  item_id;

    public Record() {
        super();
    }

    public Record(int id, String maplnfo_name, String single_name, String unit_name, String partial_name, String project_name, double old_match, double new_match, String signalment, int item_id) {
        this.id = id;
        this.maplnfo_name = maplnfo_name;
        this.single_name = single_name;
        this.unit_name = unit_name;
        this.partial_name = partial_name;
        this.project_name = project_name;
        this.old_match = old_match;
        this.new_match = new_match;
        this.signalment = signalment;
        this.item_id = item_id;
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

    public String getUnit_name() {
        return unit_name;
    }

    public void setUnit_name(String unit_name) {
        this.unit_name = unit_name;
    }

    public String getPartial_name() {
        return partial_name;
    }

    public void setPartial_name(String partial_name) {
        this.partial_name = partial_name;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public double getOld_match() {
        return old_match;
    }

    public void setOld_match(double old_match) {
        this.old_match = old_match;
    }

    public double getNew_match() {
        return new_match;
    }

    public void setNew_match(double new_match) {
        this.new_match = new_match;
    }

    public String getSignalment() {
        return signalment;
    }

    public void setSignalment(String signalment) {
        this.signalment = signalment;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", maplnfo_name='" + maplnfo_name + '\'' +
                ", single_name='" + single_name + '\'' +
                ", unit_name='" + unit_name + '\'' +
                ", partial_name='" + partial_name + '\'' +
                ", project_name='" + project_name + '\'' +
                ", old_match=" + old_match +
                ", new_match=" + new_match +
                ", signalment='" + signalment + '\'' +
                ", item_id=" + item_id +
                '}';
    }
}
