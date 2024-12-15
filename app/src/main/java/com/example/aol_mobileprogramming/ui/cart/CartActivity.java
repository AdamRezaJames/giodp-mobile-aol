package com.example.aol_mobileprogramming.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aol_mobileprogramming.R;
import com.example.aol_mobileprogramming.adapters.CartAdapter;
import com.example.aol_mobileprogramming.ui.db.DBManager;
import com.example.aol_mobileprogramming.ui.models.Transaction;
import com.example.aol_mobileprogramming.ui.payment.PaymentActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView cartRecyclerView = findViewById(R.id.recyclerViewCart);
        TextView emptyCartText = findViewById(R.id.emptyCartText);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationViewCart);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        DBManager dbManager = new DBManager(this);
        List<Transaction> transactionList = dbManager.getTransactions(1, false);

        if (transactionList == null || transactionList.isEmpty()) {
            emptyCartText.setVisibility(View.VISIBLE);
            cartRecyclerView.setVisibility(View.GONE);
            bottomNavigationView.setVisibility(View.GONE);
        } else {
            emptyCartText.setVisibility(View.GONE);
            CartAdapter adapter = new CartAdapter(transactionList);
            cartRecyclerView.setAdapter(adapter);
        }

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_checkout) {

                CartAdapter adapter = new CartAdapter(transactionList);
                List<Transaction> trueTransactions = adapter.getCheckedTransactions();

                dbManager.pay(1, trueTransactions);
                Intent in = new Intent(this, PaymentActivity.class);
                startActivity(in);
                return true;
            }
            return false;
        });

        dbManager.close();

    }
}