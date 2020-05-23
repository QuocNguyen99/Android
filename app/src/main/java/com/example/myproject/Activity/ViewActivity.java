package com.example.myproject.Activity;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myproject.Adapter.ChairAdapter;
import com.example.myproject.Class.Booking;
import com.example.myproject.Class.Chair;
import com.example.myproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ViewActivity extends AppCompatActivity {


    GridView gvChair;
    ArrayList<Chair> arrayList;
    ChairAdapter adapter;
    Toolbar toolbar;
    Button btnBooking;
    TextView txtTen;

    Calendar calendar;
    int idPhong=-1;
    String tenghe1="";
    String tenphim;

    DatabaseReference mData;
    Chair c=new Chair();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        Anhxa();
        getValue();
        adapter=new ChairAdapter(this,R.layout.dong_button,arrayList);
        gvChair.setAdapter(adapter);
        getDatabase();



        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeValueButton();
                UpdateDatbaseBooking();
                tenghe1="";
            }
        });
    }

    //thay doi gia tri trang thai
    private void changeValueButton(){
        for(Chair x:arrayList){
            try {
                if(x.isSelected){
//                    x.setTrangThai("đã đặt");
                    UpdateDatabase(x.getTenGhe());
                    tenghe1=tenghe1+" "+x.getTenGhe();
                }
                else
                {
                    UpdateDatabase2(x.getTenGhe());
                }
            }catch (Exception ex){
            }

        }
    }

    //lấy giá trị từ listflimadapter
    private void getValue(){
        Bundle bundle=getIntent().getExtras();
        idPhong=bundle.getInt("maphong",0);
        tenphim=bundle.getString("tenphim","phim");
        txtTen.setText(tenphim);
    }

    @Override
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
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();

        Drawable iconBack=getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(iconBack);
    }

    private void getDatabase(){
        mData= FirebaseDatabase.getInstance().getReference();
        mData.child("Ghe_Chitiet").child(String.valueOf(idPhong)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot data:dataSnapshot.getChildren()) {
                    String value= String.valueOf(data.getValue());
                    String  key=data.getKey();
                    arrayList.add(new Chair(key,value));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void UpdateDatabase(String tenghe){
        mData.child("Ghe_Chitiet").child(String.valueOf(idPhong)).child(tenghe).setValue("đã đặt");
    }

    private void UpdateDatabase2(String tenghe){
        mData.child("Ghe_Chitiet").child(String.valueOf(idPhong)).child(tenghe).setValue("trống");
    }

    // cat mail
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

    private  void UpdateDatbaseBooking(){
        SharedPreferences sharedPreferences=getSharedPreferences("dataLogin",MODE_PRIVATE);
        String ten=sharedPreferences.getString("taikhoan","");

        String mailsaukhicat=TimIndex(ten);

        calendar=Calendar.getInstance();

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
        String thoigian= simpleDateFormat.format(calendar.getTime());

        Booking booking=new Booking(tenphim,thoigian,tenghe1.trim());

        if(tenghe1==null) {
            Toast.makeText(this, "Bạn chưa chọn ghế", Toast.LENGTH_SHORT).show();
        }
        else {
            mData.child("Booking").child(mailsaukhicat).push().setValue(booking);
        }
    }

    private void Anhxa() {
        Toolbar();
        gvChair=(GridView) findViewById(R.id.girdviewChair);
        btnBooking=(Button) findViewById(R.id.buttonDatVe);
        txtTen=(TextView) findViewById(R.id.tvNameView);
        arrayList=new ArrayList<>();

    }
}
