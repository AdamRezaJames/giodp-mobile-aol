package com.example.aol_mobileprogramming.ui.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aol_mobileprogramming.R;
import com.example.aol_mobileprogramming.ui.bottomnav.BottomNavbarActivity;
import com.example.aol_mobileprogramming.ui.db.DBManager;
import com.example.aol_mobileprogramming.ui.login.LoginActivity;
import com.example.aol_mobileprogramming.ui.models.User;

public class RegisterActivity extends AppCompatActivity {

    TextView loginQuestionText;
    LinearLayout authGoogleButton;
    EditText registerPassInput, registerEmailTextInput, registerNameInput;
    DBManager dbManager;
    Button registerButton;

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

        authGoogleButton = findViewById(R.id.authGoogleButton);
        registerPassInput = findViewById(R.id.registerPassInput);
        registerNameInput = findViewById(R.id.registerNameInput);
        registerButton = findViewById(R.id.registerButton);
        loginQuestionText = findViewById(R.id.loginQuestionText);
        registerEmailTextInput = findViewById(R.id.registerEmailTextInput);
        dbManager = new DBManager(this);

        loginQuestionText.setOnClickListener(v -> {
            Intent in = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(in);
        });

        registerButton.setOnClickListener(v -> {

            String name = registerNameInput.getText().toString();
            String email = registerEmailTextInput.getText().toString();
            String password = registerPassInput.getText().toString();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = dbManager.register(name, email, password);
            if (user != null) {
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(this, BottomNavbarActivity.class);

                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("user_id", user.getId());
                editor.apply();

                startActivity(in);
                finish();
            } else {
                Toast.makeText(this, "Email already exists!", Toast.LENGTH_SHORT).show();
            }
        });

    }
}