package com.example.myproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetActivity extends AppCompatActivity {

    Toolbar tb;
    Button btnSend;
    EditText edtMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        Anhxa();


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendMail();
            }
        });
    }


    private void SendMail(){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = String.valueOf(edtMail.getText());


        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgetActivity.this, "Đã gửi", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Toolbar() {
        tb = (Toolbar) findViewById(R.id.toolbar6);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();

        Drawable iconBack = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(iconBack);
    }

    private void Anhxa() {
        Toolbar();
        btnSend     =(Button) findViewById(R.id.btnSendmail);
        edtMail     =(EditText) findViewById(R.id.editTextMail);
    }
}
