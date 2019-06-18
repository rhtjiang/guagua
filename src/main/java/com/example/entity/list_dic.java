package com.example.entity;

import java.util.List;

public class list_dic {


    private int uid;
    private String name;
    private String orders;
    private String t_name;
    private String status;
    private list_relate relates;

    public list_dic() {
        super();
    }

    public list_dic(int uid, String name, String orders, String t_name, String status, list_relate relates) {
        this.uid = uid;
        this.name = name;
        this.orders = orders;
        this.t_name = t_name;
        this.status = status;
        this.relates = relates;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public list_relate getRelates() {
        return relates;
    }

    public void setRelates(list_relate relates) {
        this.relates = relates;
    }

    @Override
    public String toString() {
        return "list_dic{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", orders='" + orders + '\'' +
                ", t_name='" + t_name + '\'' +
                ", status='" + status + '\'' +
                ", relates=" + relates +
                '}';
    }
}
