package com.tdc.edu.vn.myapplication.CTToan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

import com.tdc.edu.vn.myapplication.R;
import com.tdc.edu.vn.myapplication.card_model.card_view_model;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Vector<card_view_model> list;

    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.imageText);
        }
    }
    public MyAdapter(Vector<card_view_model> list) {
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        CardView cardView = (CardView) inflater.inflate(viewType, viewGroup, false);
        return new MyViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        card_view_model aCard = list.get(i);
        holder.textView.setText(aCard.getCardName());
        holder.imageView.setImageResource(aCard.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    @Override
    public int getItemViewType(int position) {
            return R.layout.card_layout;
    }

}
