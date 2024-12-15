package com.example.aol_mobileprogramming.ui.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.aol_mobileprogramming.ui.models.Course;
import com.example.aol_mobileprogramming.ui.models.Transaction;
import com.example.aol_mobileprogramming.ui.models.User;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private final Context ctx;
    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        this.ctx = context;
        helper = new DBHelper(context);
    }

    public void open() {
        if (db != null && db.isOpen()) {
            return;
        }
        helper = new DBHelper(ctx);
        db = helper.getWritableDatabase();
        db.setForeignKeyConstraintsEnabled(true);
    }

    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
        if (helper != null) {
            helper.close();
        }
    }

    public SQLiteDatabase getDB() {
        return db;
    }

    public User login(String email, String password) {
        if (email == null || password == null) {
            return null;
        }

        open();
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        Cursor c = db.rawQuery(sql, new String[]{email, password});
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();
        User user = new User(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
        c.close();
        close();
        return user;
    }

    public User register(String name, String email, String password) {
        if (name == null || email == null || password == null) {
            return null;
        }
        open();
        String checkExistSql = "SELECT * FROM users WHERE email = ?";
        Cursor c = db.rawQuery(checkExistSql, new String[]{email});
        if (c.getCount() > 0) {
            c.close();
            return null;
        }
        String insertSql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        db.execSQL(insertSql, new String[]{name, email, password});
        c.close();
        close();
        return login(email, password);
    }

    public User getUser(int id) {
        open();
        String sql = "SELECT * FROM users WHERE id = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(id)});
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();
        User user = new User(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
        c.close();
        close();
        return user;
    }

    public List<User> getUsers() {
        open();
        String sql = "SELECT * FROM users";
        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();
        List<User> users = new ArrayList<>();
        while (!c.isAfterLast()) {
            User user = new User(c.getInt(0), c.getString(1), c.getString(2), c.getString(3));
            users.add(user);
            c.moveToNext();
        }
        c.close();
        close();
        return users;
    }

    public Course getCourse(int id) {
        open();
        String sql = "SELECT * FROM courses WHERE id = ?";
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(id)});
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();
        Course course = new Course(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4));
        c.close();
        close();
        return course;
    }

    public List<Course> getCourses() {
        open();
        String sql = "SELECT * FROM courses";
        Cursor c = db.rawQuery(sql, null);
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();
        List<Course> courses = new ArrayList<>();
        while (!c.isAfterLast()) {
            Course course = new Course(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4));
            courses.add(course);
            c.moveToNext();
        }
        c.close();
        close();
        return courses;
    }

    public void addCourse(String name, String description, String price) {
        open();
        String sql = "INSERT INTO courses (name, description, price) VALUES (?, ?, ?)";
        db.execSQL(sql, new String[]{name, description, price});
        close();
    }

    public Transaction getTransaction(int userId, int courseId, @Nullable Boolean paid) {
        open();
        String sql = "SELECT * FROM `transaction` WHERE user_id = ? AND course_id = ?";
        if (paid != null) {
            if (paid) {
                sql = "SELECT * FROM `transaction` WHERE user_id = ? AND course_id = ? AND status = 1";
            } else {
                sql = "SELECT * FROM `transaction` WHERE user_id = ? AND course_id = ? AND status = 0";
            }
        }
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(userId), String.valueOf(courseId)});
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();
        Transaction transaction = new Transaction(c.getInt(0), new User(c.getInt(1), null, null, null), new Course(c.getInt(2), null, null, null, null), c.getInt(3) == 1);
        c.close();
        close();
        return transaction;
    }

    public Transaction getTransaction(int id, @Nullable Boolean paid) {
        open();
        String sql = "SELECT * FROM `transaction` WHERE id = ?";
        if (paid != null) {
            if (paid) {
                sql = "SELECT * FROM `transaction` WHERE id = ? AND status = 1";
            } else {
                sql = "SELECT * FROM `transaction` WHERE id = ? AND status = 0";
            }
        }
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(id)});
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();
        Transaction transaction = new Transaction(c.getInt(0), new User(c.getInt(1), null, null, null), new Course(c.getInt(2), null, null, null, null), c.getInt(3) == 1);
        c.close();
        close();
        return transaction;
    }

    public List<Transaction> getTransactions(int userId, @Nullable Boolean paid) {
        open();
        String sql = "SELECT * FROM `transaction` WHERE user_id = ?";
        if (paid != null) {
            if (paid) {
                sql = "SELECT * FROM `transaction` WHERE user_id = ? AND status = 1";
            } else {
                sql = "SELECT * FROM `transaction` WHERE user_id = ? AND status = 0";
            }
        }
        Cursor c = db.rawQuery(sql, new String[]{String.valueOf(userId)});
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        c.moveToFirst();
        List<Transaction> transactions = new ArrayList<>();
        while (!c.isAfterLast()) {
            Transaction transaction = new Transaction(c.getInt(0), new User(c.getInt(1), null, null, null), new Course(c.getInt(2), null, null, null, null), c.getInt(3) == 1);
            transactions.add(transaction);
            c.moveToNext();
        }
        c.close();
        close();
        return transactions;
    }

    public void addTransaction(int userId, int courseId) {
        open();
        String sql = "INSERT INTO `transaction` (user_id, course_id) VALUES (?, ?)";
        db.execSQL(sql, new String[]{String.valueOf(userId), String.valueOf(courseId)});
        close();
    }

    public void removeTransaction(int userId, int courseId) {
        open();
        String sql = "DELETE FROM `transaction` WHERE user_id = ? AND course_id = ?";
        db.execSQL(sql, new String[]{String.valueOf(userId), String.valueOf(courseId)});
        close();
    }

    public void removeTransaction(int id) {
        open();
        String sql = "DELETE FROM `transaction` WHERE id = ?";
        db.execSQL(sql, new String[]{String.valueOf(id)});
        close();
    }

    public void pay(int userId, List<Transaction> transactionsList) {
        open();
        for (Transaction transaction : transactionsList) {
            String sql = "UPDATE `transaction` SET status = 1 WHERE user_id = ? AND course_id = ?";
            db.execSQL(sql, new String[]{String.valueOf(userId), String.valueOf(transaction.getCourse().getId())});
        }
        close();
    }

    public void updateTransaction(int id, boolean status) {
        open();
        String sql = "UPDATE `transaction` SET status = ? WHERE id = ?";
        db.execSQL(sql, new String[]{status ? "1" : "0", String.valueOf(id)});
        close();
    }

    public void updateTransaction(int userId, int courseId, boolean status) {
        open();
        String sql = "UPDATE `transaction` SET status = ? WHERE user_id = ? AND course_id = ?";
        db.execSQL(sql, new String[]{status ? "1" : "0", String.valueOf(userId), String.valueOf(courseId)});
        close();
    }
}
