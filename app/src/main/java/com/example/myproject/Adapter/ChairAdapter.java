package com.example.myproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.myproject.Class.Chair;
import com.example.myproject.R;

import java.util.List;

public class ChairAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private Context context;
    private int layout;
    private List<Chair> listChair;
    private int selected=-1;

    public ChairAdapter(Context context, int layout, List<Chair> listChair) {
        this.context = context;
        this.layout = layout;
        this.listChair = listChair;
    }

    @Override
    public int getCount() {
        return listChair.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }




    private class ViewHolder{
        Button btnChair;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder=new ViewHolder();
            convertView=inflater.inflate(layout,null);
            holder.btnChair=(Button) convertView.findViewById(R.id.buttonChair);
            convertView.setTag(holder);
        }
        else{
            holder= (ViewHolder) convertView.getTag();
        }

        final Chair chair=listChair.get(position);
        holder.btnChair.setText(chair.getTenGhe());


        //set gia tri selected bằng true nếu click
        //set gia tri bang false neu click lần 2

        holder.btnChair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chair.isSelected==null || chair.isSelected==false) {
                    chair.isSelected = true;
                    chair.setBackGround(holder.btnChair);
                    Toast.makeText(context, chair.getTrangThai(), Toast.LENGTH_SHORT).show();
                }else{
                    chair.isSelected=false;
                    chair.setBackGround(holder.btnChair);
                    Toast.makeText(context, chair.getTrangThai(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //set mau khi trang thai la da dat
        if(chair.getTrangThai().equals("đã đặt")){
            holder.btnChair.setBackgroundResource(R.drawable.custom_button_selected_dadat2);
            holder.btnChair.setEnabled(false);
        }





        return convertView;
    }

    //lấy giá trị position khi click
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selected=position;
        this.notifyDataSetChanged();
    }
}
