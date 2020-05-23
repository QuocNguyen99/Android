package com.example.myproject.Activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myproject.R;

import java.io.Serializable;

public class DetailsActivity extends AppCompatActivity implements Serializable {

    Toolbar toolbarDetail;
    TextView txtNameDetails,txtMota,txtTheloai,txtThoigian;
    int position; //biến position cục bộ



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Anhxa();
        getObject();

    }

    //lấy giá trị tu7 fragment flim
    private void getObject(){
        Bundle bundle= getIntent().getExtras();
        String tenphim=bundle.getString("tenphim","aa");
        String mota=bundle.getString("mota","bbb");
        String theloai=bundle.getString("theloai","ccc");
        String thoigian=bundle.getString("thoigian","ddd");

        txtNameDetails.setText(tenphim);
        txtMota.setText("Mô tả:"+" "+mota);
        txtTheloai.setText("Thể loại :"+" "+theloai);
        txtThoigian.setText("Thời gian chiếu :"+" "+thoigian);
    }




    //setting icon back lùi về
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }

    //setup toolbar
    private void ToolBar(){
        toolbarDetail=(Toolbar) findViewById(R.id.tb_details);
        setSupportActionBar(toolbarDetail);
        ActionBar actionBar=getSupportActionBar();

        Drawable iconBack=getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(iconBack);
    }

    private void Anhxa() {
        ToolBar();
        txtMota         =(TextView) findViewById(R.id.tvMota);
        txtNameDetails  =(TextView) findViewById(R.id.tvNameDetails);
        txtTheloai      =(TextView) findViewById(R.id.tvTheloai);
        txtThoigian      =(TextView) findViewById(R.id.tvThoigian);
    }




}
