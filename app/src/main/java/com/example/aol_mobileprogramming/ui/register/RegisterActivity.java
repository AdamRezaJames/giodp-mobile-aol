package com.example.aol_mobileprogramming.ui.register;

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
import com.example.aol_mobileprogramming.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    TextView welcomeText, registerEmailText, registerPassText, loginQuestionText;
    LinearLayout authGoogleButton;
    EditText registerPassInput, registerEmailTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginQuestionText = findViewById(R.id.loginQuestionText);
        loginQuestionText.setOnClickListener(v -> {
            Intent in = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(in);
        });

        authGoogleButton = findViewById(R.id.authGoogleButton);
        authGoogleButton.setOnClickListener(v -> {
            Intent in = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(in);
        });

    }
}