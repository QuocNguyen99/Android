package com.example.myproject.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.myproject.Adapter.TabLayoutAdapter;
import com.example.myproject.R;
import com.google.android.material.tabs.TabLayout;

public class InforActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tabLayout;
    private Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor);

        Anhxa();
    }

    // chắc chán oxa1 toolbar widget cũ
    private void Toolbar(){
        tb=(Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(tb);
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuuser:
                Intent intent=new Intent(InforActivity.this,UserActivity.class);
                startActivity(intent);
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void Anhxa() {
        vp                  =(ViewPager)findViewById(R.id.viewpagerFragment);
        tabLayout           =(TabLayout)findViewById(R.id.tl_fragment);
        vp.setAdapter(new TabLayoutAdapter(getSupportFragmentManager(),tabLayout.getTabCount()));
        TabLayout tl =(TabLayout) findViewById(R.id.tl_fragment);
        tl.setupWithViewPager(vp);
        Toolbar();
    }

}
