package com.example.aol_mobileprogramming.ui.coursedetail;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aol_mobileprogramming.R;
import com.example.aol_mobileprogramming.ui.db.DBManager;

public class CourseDetailActivity extends AppCompatActivity {

    ImageView imageDetail;
    TextView titleDetail, descDetail, priceDetail;
    Button buyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageDetail = findViewById(R.id.imageDetail);
        titleDetail = findViewById(R.id.titleDetail);
        descDetail = findViewById(R.id.descDetail);
        priceDetail = findViewById(R.id.priceDetail);
        buyButton = findViewById(R.id.buyButton);

        imageDetail.setImageResource(getIntent().getIntExtra("image", 0));
        descDetail.setText(getIntent().getStringExtra("description"));
        titleDetail.setText(getIntent().getStringExtra("name"));
        String price = getIntent().getStringExtra("price");
        String formattedPrice = "Rp. " + price;
        priceDetail.setText(formattedPrice);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("user_id", -1);

        if (userId == -1) {
            Toast.makeText(this, "Error: User not logged in!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        int course_id = getIntent().getIntExtra("course_id", -1);
        DBManager dbManager = new DBManager(this);

        if (dbManager.getTransaction(userId, course_id, false) != null) {
            buyButton.setText("Product already in Cart");
            buyButton.setEnabled(false);
        } else if ((dbManager.getTransaction(userId, course_id, true) != null)){
            buyButton.setText("Product already Bought");
            buyButton.setEnabled(false);
        } else {
            buyButton.setOnClickListener(v -> {
                dbManager.addTransaction(userId, course_id);
                Toast.makeText(this, "Product added to cart!", Toast.LENGTH_SHORT).show();
                buyButton.setText("Product already in Cart");
                buyButton.setEnabled(false);
            });
        }

    }
}