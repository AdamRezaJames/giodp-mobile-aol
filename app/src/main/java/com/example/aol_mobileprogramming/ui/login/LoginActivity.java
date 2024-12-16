package com.example.aol_mobileprogramming.ui.login;

import android.content.Intent;
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
import com.example.aol_mobileprogramming.ui.models.User;
import com.example.aol_mobileprogramming.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    TextView registerQuestion;
    EditText emailAddressInput, passInput;
    Button loginButton;
    LinearLayout googleButton;
    DBManager dbManager;

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

        passInput = findViewById(R.id.passInput);
        loginButton = findViewById(R.id.loginButton);
        googleButton = findViewById(R.id.googleButton);
        registerQuestion = findViewById(R.id.registerQuestion);
        emailAddressInput = findViewById(R.id.emailAddressInput);
        dbManager = new DBManager(this);

        registerQuestion.setOnClickListener(v -> {
            Intent in = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(in);
        });

        loginButton.setOnClickListener(v -> {
            String email = emailAddressInput.getText().toString();
            String password = passInput.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            User user = dbManager.login(email, password);
            if (user != null) {
                Toast.makeText(this, "Welcome " + user.getName(), Toast.LENGTH_SHORT).show();
                Intent in = new Intent(this, BottomNavbarActivity.class);
//                in.putExtra("user_id", user.getId());
                startActivity(in);
                finish();
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });

        googleButton.setOnClickListener(v -> {
            Intent in = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(in);
        });

    }
}