package com.example.myproject.Activity;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.Adapter.HistoryAdapter;
import com.example.myproject.Class.Booking;
import com.example.myproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    Toolbar tb;

    DatabaseReference mData;
    Booking bk=new Booking();
    ArrayList<Booking> bookings=new ArrayList<>();
    RecyclerView rcHistory;
    HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        mData= FirebaseDatabase.getInstance().getReference();
        Anhxa();
    }

    private String TimIndex(String a){
        char[] ch= a.toCharArray();
        String tenmail="";
        for(int i=0;i<ch.length;i++){
            String c= String.valueOf(ch[i]);
            if(c.equals("@")){
                tenmail=a.substring(0,i);
                return tenmail;
            }
        }
        return null;
    }

    private void getDataBase(){
        SharedPreferences sharedPreferences=getSharedPreferences("dataLogin",MODE_PRIVATE);
        String ten=sharedPreferences.getString("taikhoan","");
        String tensaukhicat=TimIndex(ten);

        mData.child("Booking").child(tensaukhicat).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    bk = data.getValue(Booking.class);
                    bookings.add(new Booking(bk.getTenPhim(),bk.getNgayDat(),bk.getTenGhe()));
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Toolbar(){
        tb=(Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(tb);
        ActionBar actionBar=getSupportActionBar();

        Drawable iconBack=getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(iconBack);
    }

    private void Anhxa() {
        Toolbar();
        rcHistory=(RecyclerView) findViewById(R.id.rcHistory);
        rcHistory.setHasFixedSize(false);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcHistory.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,layoutManager.getOrientation());
        rcHistory.addItemDecoration(dividerItemDecoration);
        adapter=new HistoryAdapter(this,bookings);
        rcHistory.setAdapter(adapter);
        getDataBase();
    }

}
