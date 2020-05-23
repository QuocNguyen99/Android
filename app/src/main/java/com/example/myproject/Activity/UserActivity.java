package com.example.myproject.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {

    private Toolbar tb;
    private TextView txtUserName,txtHistory;
    Button btnLogout;

    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Anhxa();
        mData= FirebaseDatabase.getInstance().getReference();
        ReadSession();
        txtHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignOut();
            }
        });
    }


    public void SignOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(UserActivity.this,MainActivity.class);
        startActivity(intent);
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

    private void ReadSession(){
        SharedPreferences sharedPreferences=getSharedPreferences("dataLogin", MODE_PRIVATE);
        String name=sharedPreferences.getString("taikhoan","Tên tài khoản");

        txtUserName.setText(name);
    }

    private void Toolbar(){
        tb=(Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(tb);
        ActionBar actionBar=getSupportActionBar();

        Drawable iconBack=getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(iconBack);
    }

    private void Anhxa() {
        Toolbar();
        txtUserName =(TextView) findViewById(R.id.textViewName);
        txtHistory  =(TextView) findViewById(R.id.textViewHistory);
        btnLogout   =(Button) findViewById(R.id.buttonLogout);
    }


}
