package com.example.aol_mobileprogramming.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aol_mobileprogramming.R;
import com.example.aol_mobileprogramming.ui.bottomnav.BottomNavbarActivity;
import com.example.aol_mobileprogramming.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    TextView loginText, emailText, passText, registerQuestion;
    EditText emailAddressInput, passInput;
    Button loginButton;
    LinearLayout googleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        registerQuestion = findViewById(R.id.registerQuestion);
        registerQuestion.setOnClickListener(v -> {
            Intent in = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(in);
        });

        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            Intent in = new Intent(LoginActivity.this, BottomNavbarActivity.class);
            startActivity(in);
        });

        googleButton = findViewById(R.id.googleButton);
        googleButton.setOnClickListener(v -> {
            Intent in = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(in);
        });

    }
}