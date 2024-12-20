package com.example.aol_mobileprogramming.ui.login;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aol_mobileprogramming.R;
import com.example.aol_mobileprogramming.ui.bottomnav.BottomNavbarActivity;
import com.example.aol_mobileprogramming.ui.db.DBManager;
import com.example.aol_mobileprogramming.ui.models.User;
import com.example.aol_mobileprogramming.ui.register.RegisterActivity;
import com.google.firebase.messaging.FirebaseMessaging;

public class LoginActivity extends AppCompatActivity {

    TextView registerQuestion;
    EditText emailAddressInput, passInput;
    Button loginButton;
    DBManager dbManager;

    private void generateToken() {
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(v -> {
            if (v.isSuccessful()) {
                Log.d("LoginActiv", "generateToken: " + v.getResult());
            }
        });
    }

    private void requestPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.POST_NOTIFICATIONS,
                },100);
            }
        }
    }

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
        requestPermission();
        generateToken();

        passInput = findViewById(R.id.passInput);
        loginButton = findViewById(R.id.loginButton);
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

                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("user_id", user.getId());
                editor.apply();

                startActivity(in);
                finish();
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show();
            }
        });

    }
}