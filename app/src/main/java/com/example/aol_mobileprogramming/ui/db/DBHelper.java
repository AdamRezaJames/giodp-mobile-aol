package com.example.aol_mobileprogramming.ui.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
        db.execSQL("INSERT OR IGNORE INTO courses (name, description, price) VALUES ('Course 1', 'Description 1', '100000')");
        db.execSQL("INSERT OR IGNORE INTO courses (name, description, price) VALUES ('Course 2', 'Description 2', '200000')");
        db.execSQL("INSERT OR IGNORE INTO courses (name, description, price) VALUES ('Course 3', 'Description 3', '300000')");
        db.execSQL("INSERT OR IGNORE INTO courses (name, description, price) VALUES ('Course 4', 'Description 4', '400000')");
        db.execSQL("INSERT OR IGNORE INTO courses (name, description, price) VALUES ('Course 5', 'Description 5', '500000')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS courses");
        db.execSQL("DROP TABLE IF EXISTS `transaction`");
        onCreate(db);
    }
}
