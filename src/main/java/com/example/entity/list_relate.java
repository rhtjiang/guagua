package com.example.entity;

public class list_relate {

    private Integer id;
    private Integer item_id;
    private Integer dic_id;
    private String status;
    private String orders;

    public list_relate() {
        super();
    }

    public list_relate(Integer id, Integer item_id, Integer dic_id, String status, String orders) {
        this.id = id;
        this.item_id = item_id;
        this.dic_id = dic_id;
        this.status = status;
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public Integer getDic_id() {
        return dic_id;
    }

    public void setDic_id(Integer dic_id) {
        this.dic_id = dic_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "list_relate{" +
                "id=" + id +
                ", item_id=" + item_id +
                ", dic_id=" + dic_id +
                ", status='" + status + '\'' +
                ", orders='" + orders + '\'' +
                '}';
    }
}
