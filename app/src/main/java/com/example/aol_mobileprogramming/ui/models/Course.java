package com.example.aol_mobileprogramming.ui.models;

public class Course {
    private int id, image;
    private String name;
    private String description;
    private String price;

    public Course(int id, int image, String name, String description, String price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }
}
