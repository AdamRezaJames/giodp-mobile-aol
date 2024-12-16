package com.example.aol_mobileprogramming.ui.bottomnav.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aol_mobileprogramming.adapters.CourseAdapter;
import com.example.aol_mobileprogramming.databinding.FragmentDashboardBinding;
import com.example.aol_mobileprogramming.ui.db.DBManager;
import com.example.aol_mobileprogramming.ui.models.Course;
import com.example.aol_mobileprogramming.ui.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    RecyclerView recyclerViewPaidCourses;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerViewPaidCourses = binding.recyclerViewPaidCourses;
        recyclerViewPaidCourses.setLayoutManager(new LinearLayoutManager(getContext()));

        DBManager dbManager = new DBManager(getContext());
        List<Transaction> paidTransactions = dbManager.getTransactions(1, true);
        List<Course> paidCourses = new ArrayList<>();

        if (paidTransactions != null) {
            for (Transaction t : paidTransactions) {
                paidCourses.add(t.getCourse());
            }
        }

        if (!paidCourses.isEmpty()) {
            CourseAdapter adapter = new CourseAdapter(paidCourses);
            recyclerViewPaidCourses.setAdapter(adapter);
        } else {
            binding.emptyDashboardText.setVisibility(View.VISIBLE);
        }
        dbManager.close();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}