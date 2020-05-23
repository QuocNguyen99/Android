package com.example.myproject.Class;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class Data {

    ArrayList<Flim> fList;
    List<Chair> list=new ArrayList<>();
    DatabaseReference mData;
    Flim flim=new Flim();
    ArrayList<Flim> listflim = new ArrayList<>();

//sẽ xóa
//    public ArrayList<Flim> getDataFlim(){
//        fList=new ArrayList<>();
//        return fList;
//    }
//
//    public Flim getDataFlimWithPosition(int position){
//        Flim flim=getDataFlim().get(position);
//        return flim;
//    }
//
//    public void getDatabse(final FlimAdapter adapter){
//        mData= FirebaseDatabase.getInstance().getReference();
//        mData.child("phim").addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                flim= dataSnapshot.getValue(Flim.class);
//                listflim.add(new Flim(flim.getIdPhim(),flim.getTenPhim(),flim.getHinhAnh(),flim.getMoTa(),flim.getTheLoai(),flim.getThoiGian(),flim.getIdPhong()));
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//    }

}
