package com.example.aol_mobileprogramming.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aol_mobileprogramming.R;
import com.example.aol_mobileprogramming.ui.db.DBManager;
import com.example.aol_mobileprogramming.ui.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context activityContext;
    List<Transaction> transactionList;
    List<Boolean> checkedList = new ArrayList<Boolean>();

    public CartAdapter(List<Transaction> transactions) {
        this.transactionList = transactions;
        for (int i = 0; i< this.transactionList.size(); i++){
            checkedList.add(false);
        }
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activityContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(activityContext);
        View vw = inflater.inflate(R.layout.list_item_cart, parent, false);
        return new CartAdapter.CartViewHolder(vw);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {

        Transaction transaction = transactionList.get(position);
        holder.textTitle.setText(transaction.getCourse().getName());
        holder.textPrice.setText("Rp. " + transaction.getCourse().getPrice());
        holder.imageItemCart.setImageResource(transaction.getCourse().getImage());
        holder.checkBoxPurchased.setOnClickListener(v -> {
            checkedList.set(position,true);
        });
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public List<Transaction> getCheckedTransactions(){
        List<Transaction> checkedTransactions = new ArrayList<>();
        for (int i = 0; i< checkedList.size(); i++){
            if (checkedList.get(i)){
                checkedTransactions.add(transactionList.get(i));
            }
        }
        return checkedTransactions;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle, textPrice;
        ImageView imageItemCart;
        CheckBox checkBoxPurchased;
//        CardView cardConstraintLayoutListItemCart;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textJudulCart);
            textPrice = itemView.findViewById(R.id.textPriceCart);
            imageItemCart = itemView.findViewById(R.id.imageItemCart);
            checkBoxPurchased = itemView.findViewById(R.id.checkBoxPurchased);
//            cardConstraintLayoutListItemCart = itemView.findViewById(R.id.cardConstraintLayoutListItemCart);
        }
    }
}

