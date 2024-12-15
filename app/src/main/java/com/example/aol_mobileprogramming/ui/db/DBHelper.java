package com.example.aol_mobileprogramming.ui.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

import com.example.aol_mobileprogramming.R;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, "GioDP", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.beginTransaction();

            db.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT, " +
                    "email TEXT, " +
                    "password TEXT)");

            db.execSQL("CREATE TABLE IF NOT EXISTS courses (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT, " +
                    "image INTEGER, " +
                    "description TEXT, " +
                    "price TEXT)");

            db.execSQL("CREATE TABLE IF NOT EXISTS `transaction` (" +
                    "id INTEGER PRIMARY KEY, " +
                    "user_id INTEGER, " +
                    "course_id INTEGER, " +
                    "status BOOLEAN DEFAULT 0, " + // 0: pending, 1: paid
                    "FOREIGN KEY(user_id) REFERENCES users(id), " +
                    "FOREIGN KEY(course_id) REFERENCES courses(id))");

            seed(db);

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }

    }

    private void seed(SQLiteDatabase db) {
        db.execSQL("INSERT OR IGNORE INTO users (name, email, password) VALUES ('user', 'uesr@mail.com', 'user')");
        db.execSQL("INSERT OR IGNORE INTO courses (name, description, price, image) VALUES ('System Design Based on Business Processes', 'Kursus ini menyampaikan pentingnya sistem desain dalam pembuatan sistem informasi bisnis. Visi, misi, tujuan, strategi, fungsi, proses , dan aktivitas bisnis akan mendorong sistem informasi yang mendukung bisnis, dan membedakan perbedaan pendekatan terstruktur dan objek dalam desain sistem informasi. Diagram REA akan digunakan sebagai dasar untuk mengembangkan ERD, DFD, dan Diagram Alir Sistem', '100000', " + R.drawable.books + ")");
        db.execSQL("INSERT OR IGNORE INTO courses (name, description, price, image) VALUES ('Information Concept System', 'This course is about strategy for IELTS. It focuses on what to do when a candidate takes the IELTS test and how to do it. As such it is not designed to be an English language development course. Although some grammar is covered, this is not the central purpose of the course.', '200000', " + R.drawable.ls + ")");
        db.execSQL("INSERT OR IGNORE INTO courses (name, description, price, image) VALUES ('Photography Skill Branding', 'This course is about Photography skill branding. ', '300000', " + R.drawable.cam + ")");
        db.execSQL("INSERT OR IGNORE INTO courses (name, description, price, image) VALUES ('Introduction to Blockchain', 'This course is about introduction to Blockchain', '400000', " + R.drawable.library + ")");
        db.execSQL("INSERT OR IGNORE INTO courses (name, description, price, image) VALUES ('Introduction to Sculpturing', 'This course is about sculpturing', '500000', " + R.drawable.clay + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS courses");
        db.execSQL("DROP TABLE IF EXISTS `transaction`");
        onCreate(db);
    }
}
