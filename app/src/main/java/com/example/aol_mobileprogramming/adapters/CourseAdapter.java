package com.example.aol_mobileprogramming.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aol_mobileprogramming.R;
import com.example.aol_mobileprogramming.ui.coursedetail.CourseDetailActivity;
import com.example.aol_mobileprogramming.ui.models.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder> {

    Context activityContext;
    List<Course> courseList;

    public CourseAdapter(List<Course> list){
        courseList = list;
    }

    @NonNull
    @Override
    public CourseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activityContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(activityContext);
        View vw = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(vw);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.MyViewHolder holder, int position) {

        Course data = courseList.get(position);
        holder.textJudul.setText(data.getName());
        holder.textDesc.setText(data.getDescription());
        holder.textPrice.setText(data.getPrice());
        holder.imageItem.setImageResource(data.getImage());
        holder.cardConstraintLayoutListItem1.setOnClickListener(v -> {
            Intent detailActivity = new Intent(activityContext, CourseDetailActivity.class);
            detailActivity.putExtra("name", data.getName());
            detailActivity.putExtra("description", data.getDescription());
            detailActivity.putExtra("image", data.getImage());
            detailActivity.putExtra("price", data.getPrice());
            activityContext.startActivity(detailActivity);
        });

    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cardConstraintLayoutListItem1;
        ImageView imageItem;
        ConstraintLayout constraintLayoutListItem1;
        TextView textJudul, textDesc, textPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textJudul = itemView.findViewById(R.id.textJudul);
            imageItem = itemView.findViewById(R.id.imageItem);
            textPrice = itemView.findViewById(R.id.textPrice);
            textDesc = itemView.findViewById(R.id.textDesc);
            cardConstraintLayoutListItem1 = itemView.findViewById(R.id.cardConstraintLayoutListItem1);

        }
    }
}
