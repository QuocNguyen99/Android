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

import com.example.myproject.Activity.ViewActivity;
import com.example.myproject.Adapter.ListFlimAdapter;
import com.example.myproject.Class.Flim;
import com.example.myproject.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FragmentListFlim extends Fragment implements ListFlimAdapter.ItemListFlimClick {
    private View viewroot;
    private RecyclerView rc_listflim;
    DatabaseReference mData;
    private Flim flim=new Flim();
    private ArrayList<Flim> listflim=new ArrayList<>();
    private ListFlimAdapter adapter=null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewroot=inflater.inflate(R.layout.fragment_listflim,container,false);
        Anhxa();

        return viewroot;
    }


    public void getDatabse2(){
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


    private void Anhxa() {
        rc_listflim=(RecyclerView) viewroot.findViewById(R.id.rc_listflim);
        rc_listflim.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rc_listflim.setLayoutManager(layoutManager);
        adapter=new ListFlimAdapter(getContext(),this,listflim);
        rc_listflim.setAdapter(adapter);
        getDatabse2();
    }


    @Override
    public void onClick1(View v, Flim flim) {
        Intent intent=new Intent(getActivity(), ViewActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("maphong",flim.getIdPhong());
        bundle.putString("tenphim",flim.getTenPhim());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
