package com.example.root.appday.Models;

/**
 * Created by root on 28/09/2016.
 */
public class DataCity {

    private int id;
    private String name;
    private String state;
    private String description;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DataCity(String name, String state, String description) {
        this.name = name;
        this.state = state;
        this.description = description;
    }

    public DataCity(int id, String name, String state, String description) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.description = description;
    }
}
