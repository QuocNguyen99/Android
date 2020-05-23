package com.example.myproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.Class.Booking;
import com.example.myproject.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{

    ArrayList<Booking> dataBooking;
    Context context;

    public HistoryAdapter(Context context,ArrayList<Booking> dataBooking) {
        this.dataBooking = dataBooking;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(dataBooking.size()==0){
            holder.txtTen.setText("Chưa có dữ liệu");
        }else {
            holder.txtTen.setText("Phim: "+dataBooking.get(position).getTenPhim());
            holder.txtNgaydat.setText("Ngày: "+dataBooking.get(position).getNgayDat());
            holder.txtGhe.setText("Ghế: "+dataBooking.get(position).getTenGhe());
        }
    }


    @Override
    public int getItemCount() {
        return dataBooking.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTen,txtNgaydat,txtGhe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtGhe      =(TextView) itemView.findViewById(R.id.textViewSoghe);
            txtNgaydat  =(TextView) itemView.findViewById(R.id.textViewNgayDat);
            txtTen      =(TextView) itemView.findViewById(R.id.textViewTenphimHistory);
        }
    }
}
