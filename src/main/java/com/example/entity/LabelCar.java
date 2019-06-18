package com.example.entity;

public class LabelCar {

    private String uid;
    private String label_id;
    private String label_name;
    private String label_value;
    private Partial partial;


    public LabelCar() {
        super();
    }

    public LabelCar(String uid, String label_id, String label_name, String label_value, Partial partial) {
        this.uid = uid;
        this.label_id = label_id;
        this.label_name = label_name;
        this.label_value = label_value;
        this.partial = partial;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLabel_id() {
        return label_id;
    }

    public void setLabel_id(String label_id) {
        this.label_id = label_id;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }

    public String getLabel_value() {
        return label_value;
    }

    public void setLabel_value(String label_value) {
        this.label_value = label_value;
    }

    public Partial getPartial() {
        return partial;
    }

    public void setPartial(Partial partial) {
        this.partial = partial;
    }

    @Override
    public String toString() {
        return "LabelCar{" +
                "uid='" + uid + '\'' +
                ", label_id='" + label_id + '\'' +
                ", label_name='" + label_name + '\'' +
                ", label_value='" + label_value + '\'' +
                ", partial=" + partial +
                '}';
    }
}
