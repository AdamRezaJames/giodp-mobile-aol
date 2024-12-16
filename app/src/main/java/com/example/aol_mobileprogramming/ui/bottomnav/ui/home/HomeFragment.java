package com.example.aol_mobileprogramming.ui.bottomnav.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aol_mobileprogramming.R;
import com.example.aol_mobileprogramming.adapters.CourseAdapter;
import com.example.aol_mobileprogramming.databinding.FragmentHomeBinding;
import com.example.aol_mobileprogramming.ui.cart.CartActivity;
import com.example.aol_mobileprogramming.ui.db.DBManager;
import com.example.aol_mobileprogramming.ui.models.Course;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    LinearLayout layout1, layout2, layoutparent;
    RecyclerView recyclerViewLayoutHome;
    ImageButton cartButton;
    DBManager dbManager;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.cartButton.setOnClickListener(v -> {
            Intent in = new Intent(requireContext(), CartActivity.class);
            startActivity(in);
        });

        recyclerViewLayoutHome = binding.recyclerViewLayoutHome;
        recyclerViewLayoutHome.setLayoutManager(new LinearLayoutManager(getContext()));
        CourseAdapter adapter = new CourseAdapter(new DBManager(getContext()).getCourses());
        recyclerViewLayoutHome.setAdapter(adapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}