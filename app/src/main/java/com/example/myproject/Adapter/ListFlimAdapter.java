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

import java.util.List;

public class ListFlimAdapter extends RecyclerView.Adapter<ListFlimAdapter.ViewHolder> {

    private Context context;
    private List<Flim> list;
    private ItemListFlimClick itemListFlimClick;

    public ListFlimAdapter(Context context,ItemListFlimClick itemListFlimClick,List<Flim> list) {
        this.context=context;
        this.itemListFlimClick=itemListFlimClick;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listflim,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Flim flim=list.get(position);
        Glide.with(context)
                .load(flim.getHinhAnh())
                .into(holder.imgAnhListFlim);
        holder.txtThoigian.setText(flim.getThoiGian());
        holder.txtTenListFlim.setText(flim.getTenPhim());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAnhListFlim;
        TextView txtTenListFlim,txtThoigian;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            imgAnhListFlim=(ImageView) itemView.findViewById(R.id.imageViewListFlim);
            txtTenListFlim=(TextView) itemView.findViewById(R.id.textViewNameListFlim);
            txtThoigian   =(TextView) itemView.findViewById(R.id.txtThoigian);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemListFlimClick.onClick1(view,list.get(getLayoutPosition()));
                }
            });
        }
    }

    public interface ItemListFlimClick{
        void onClick1(View v,Flim flim);
    }
}
