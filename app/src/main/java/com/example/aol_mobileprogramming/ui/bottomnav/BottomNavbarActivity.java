package com.example.aol_mobileprogramming.ui.bottomnav;

import android.graphics.Insets;
import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.aol_mobileprogramming.databinding.ActivityBottomNavbarBinding;
import com.example.aol_mobileprogramming.R;

public class BottomNavbarActivity extends AppCompatActivity {

    private ActivityBottomNavbarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBottomNavbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_bottom_navbar);

        NavController navController = ((NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_bottom_navbar))
                .getNavController();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard)
                .build();

        ActionBar bar = this.getSupportActionBar();
        if (bar != null) {
            bar.hide();
        }

//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}