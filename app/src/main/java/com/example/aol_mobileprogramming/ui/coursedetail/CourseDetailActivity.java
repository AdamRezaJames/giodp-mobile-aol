package com.example.aol_mobileprogramming.ui.coursedetail;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aol_mobileprogramming.R;

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

    }
}