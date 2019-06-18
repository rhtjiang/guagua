package com.example.entity;

public class list_item {


    private int id;
    private String maplnfo_name;
    private String single_name;
    private String unit_name;
    private String partial_name;
    private String project_name;


    public list_item() {
        super();
    }

    public list_item(int id, String maplnfo_name, String single_name, String unit_name, String partial_name, String project_name) {
        this.id = id;
        this.maplnfo_name = maplnfo_name;
        this.single_name = single_name;
        this.unit_name = unit_name;
        this.partial_name = partial_name;
        this.project_name = project_name;
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

    @Override
    public String toString() {
        return "list_item{" +
                "id=" + id +
                ", maplnfo_name='" + maplnfo_name + '\'' +
                ", single_name='" + single_name + '\'' +
                ", unit_name='" + unit_name + '\'' +
                ", partial_name='" + partial_name + '\'' +
                ", project_name='" + project_name + '\'' +
                '}';
    }
}
