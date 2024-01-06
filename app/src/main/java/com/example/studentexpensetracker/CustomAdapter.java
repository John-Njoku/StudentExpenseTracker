package com.example.studentexpensetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

import Model.Data;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<Data> dataList;
    private LayoutInflater inflater;



    public CustomAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.dataList = new ArrayList<>();
    }

    public void addData(Data data) {
        dataList.add(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        dataList.clear();
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = inflater.inflate(R.layout.expense_recycler_data, parent, false);

        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Data currentData = dataList.get(position);
        holder.setDate(currentData.getDate());
        holder.setType(currentData.getType());
        holder.setNote(currentData.getNote());
        holder.setAmount(currentData.getAmount());


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView dateTextView;
        private TextView typeTextView;
        private TextView noteTextView;
        private TextView amountTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize your views here
            dateTextView = itemView.findViewById(R.id.date_txt_expense);
            typeTextView = itemView.findViewById(R.id.type_txt_expense);
            noteTextView = itemView.findViewById(R.id.note_txt_expense);
            amountTextView = itemView.findViewById(R.id.amount_txt_expense);
        }

        public void setDate(String date) {
            dateTextView.setText(date);
        }

//        public void setType(String type) {
//            typeTextView.setText(type);
//        }

        public void setType(String type) {
            // Adjust the logic based on the type field

            typeTextView.setText(type);
        }

        public void setNote(String note) {
            noteTextView.setText(note);
        }

        public void setAmount(int amount) {
            amountTextView.setText(String.valueOf(amount));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Data data);
    }

}
