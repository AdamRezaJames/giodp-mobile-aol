package com.example.aol_mobileprogramming.ui.models;

public class Transaction {
    private int id;
    private User user;
    private Course course;
    private boolean status;

    public Transaction(int id, User user, Course course, boolean status) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    public boolean getStatus() {
        return status;
    }
}
