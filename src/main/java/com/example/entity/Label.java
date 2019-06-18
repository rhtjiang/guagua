package com.example.entity;

public class Label {

    //定义属性
    private int id;
    private String field;
    private String name;
    private String major;

    public Label() {
        super();
    }

    public Label(int id, String field, String name, String major) {
        this.id = id;
        this.field = field;
        this.name = name;
        this.major = major;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }


    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", field='" + field + '\'' +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
