package com.example.myproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myproject.Class.Flim;
import com.example.myproject.R;

import java.io.Serializable;
import java.util.ArrayList;

public class  FlimAdapter extends RecyclerView.Adapter<FlimAdapter.ViewHolder> implements Serializable {

    private Context context;
    private ArrayList<Flim> listFlim;
    private ItemClickListener itemClickListener;


    public FlimAdapter(Context context,ItemClickListener itemClickListener, ArrayList<Flim> listFlim) {
        this.context=context;
        this.listFlim = listFlim;
        this.itemClickListener=itemClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flim,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Flim flim=listFlim.get(position);
        Glide.with(context)
                .load(flim.getHinhAnh())
                .into(holder.ivPoster);

        holder.txtName.setText(flim.getTenPhim());

    }

    @Override
    public int getItemCount() {
        return listFlim.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements Serializable {
        ImageView ivPoster;
        TextView txtName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPoster    =(ImageView) itemView.findViewById(R.id.imageViewPoster);
            txtName     =(TextView) itemView.findViewById(R.id.textViewName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onClick(view,listFlim.get(getLayoutPosition()));
                }
            });
        }
    }

    public interface ItemClickListener{
        void onClick(View v,Flim flim);
    }
}
