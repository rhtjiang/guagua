package com.example.entity;

public class StandardTable {


    private int id;
    private String table_name;
    private int standard_id;
    private String project_name;

    public StandardTable() {
        super();
    }

    public StandardTable(int id, String table_name, int standard_id, String project_name) {
        this.id = id;
        this.table_name = table_name;
        this.standard_id = standard_id;
        this.project_name = project_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public int getStandard_id() {
        return standard_id;
    }

    public void setStandard_id(int standard_id) {
        this.standard_id = standard_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    @Override
    public String toString() {
        return "StandardTable{" +
                "id=" + id +
                ", table_name='" + table_name + '\'' +
                ", standard_id=" + standard_id +
                ", project_name='" + project_name + '\'' +
                '}';
    }
}
