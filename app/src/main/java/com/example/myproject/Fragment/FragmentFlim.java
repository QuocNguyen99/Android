package com.example.myproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.Activity.DetailsActivity;
import com.example.myproject.Adapter.FlimAdapter;
import com.example.myproject.Class.Flim;
import com.example.myproject.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class FragmentFlim extends Fragment implements FlimAdapter.ItemClickListener,Serializable {

    private View viewroot;
    private RecyclerView rcvFLim;
    private DatabaseReference mData;
    private Flim flim=new Flim();
    private ArrayList<Flim> listflim=new ArrayList<>();
    private FlimAdapter adapter=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewroot=inflater.inflate(R.layout.fragment_flim,container,false);

        Anhxa();
        return viewroot;
    }



    //lấy dữ liệu từ firebase
    public void getDatabse(){
        mData= FirebaseDatabase.getInstance().getReference();
        mData.child("phim").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                flim= dataSnapshot.getValue(Flim.class);
                listflim.add(new Flim(flim.getIdPhim(),flim.getTenPhim(),flim.getHinhAnh(),flim.getMoTa(),flim.getTheLoai(),flim.getThoiGian(),flim.getIdPhong()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //hàm click recylerview
    @Override
    public void onClick(View v,Flim flimPosition) {
        Intent intent=new Intent(getActivity(), DetailsActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("tenphim",flimPosition.getTenPhim());
        bundle.putString("mota",flimPosition.getMoTa());;
        bundle.putString("theloai",flimPosition.getTheLoai());
        bundle.putString("thoigian",flimPosition.getThoiGian());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void Anhxa() {
        rcvFLim=(RecyclerView) viewroot.findViewById(R.id.recylerview_Flim);
        rcvFLim.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        rcvFLim.setLayoutManager(layoutManager);
        adapter=new FlimAdapter(getContext(),this,listflim);
        rcvFLim.setAdapter(adapter);
        getDatabse();
    }


}
