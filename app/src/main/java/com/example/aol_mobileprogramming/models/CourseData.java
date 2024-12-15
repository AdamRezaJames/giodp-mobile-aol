package com.example.aol_mobileprogramming.models;

public class CourseData {

    private String name, desc;
    int price;

    public CourseData(String name, String desc, int price){
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public String getTitle() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public String getDesc() {
        return desc;
    }
}
