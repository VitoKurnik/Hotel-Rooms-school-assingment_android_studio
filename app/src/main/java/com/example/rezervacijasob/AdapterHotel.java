package com.example.rezervacijasob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libdatastructure.Hotel;
import com.example.libdatastructure.Soba;

public class AdapterHotel extends RecyclerView.Adapter<AdapterHotel.ViewHolder> {
    private ApplicationMy app;
    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public AdapterHotel(ApplicationMy app) {
        this.app = app;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.rv_rowlayout, parent, false);
        // Return a new holder instance
        AdapterHotel.ViewHolder viewHolder = new AdapterHotel.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Soba trenutna = app.getRoom(position);
        holder.txtHeader.setText(trenutna.toString());
        holder.txtFooter.setText(trenutna.getCena()+"");
        if (position%2 == 1) {
            holder.ozadje.setBackgroundColor(Color.LTGRAY);
            holder.txtHeader.setTextColor(Color.RED);
        } else {
            holder.ozadje.setBackgroundColor(Color.WHITE);
            holder.txtHeader.setTextColor(Color.BLACK);

        }

    }

    @Override
    public int getItemCount() {
        return app.getHotelSize();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView iv;
        public View ozadje;
        public ViewHolder(@NonNull View v) {
            super(v);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            iv = (ImageView)v.findViewById(R.id.icon);
            ozadje = v.findViewById(R.id.mylayoutrow);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });
        }
    }
}
