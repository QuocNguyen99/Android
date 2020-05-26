package com.example.myproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
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
import com.google.firebase.auth.FirebaseUser;

public class ChangepassActivity extends AppCompatActivity {

    Toolbar tb;
    EditText edtold, edtnew, edtconfirm;
    Button btnchange;

    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        Anhxa();
        ReadSession();

        btnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePass();
            }
        });
    }



    private void ReadSession() {
        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String name = sharedPreferences.getString("taikhoan", "Tên tài khoản");
        pass = sharedPreferences.getString("matkhau", "");

    }

    private void ChangePass() {
        String old = String.valueOf(edtold.getText());
        String newpass = String.valueOf(edtnew.getText());
        String confirm = String.valueOf(edtconfirm.getText());
        if (old.equals(pass)==false) {
            Toast.makeText(this, "Sai mật khẩu cũ", Toast.LENGTH_SHORT).show();
        } else {
            if (newpass.equals(old)==true) {
                Toast.makeText(this, "Mật khẩu mới trùng mật khẩu cũ", Toast.LENGTH_SHORT).show();
            } else {
                if (newpass.equals(confirm)==false) {
                    Toast.makeText(this, "Mật khẩu nhập lại không giống", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    user.updatePassword(newpass)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ChangepassActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        }
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
        tb = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();

        Drawable iconBack = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(iconBack);
    }

    private void Anhxa() {
        Toolbar();
        btnchange = (Button) findViewById(R.id.btnChangepass);
        edtnew = (EditText) findViewById(R.id.edtNewPass);
        edtold = (EditText) findViewById(R.id.edtOldPass);
        edtconfirm = (EditText) findViewById(R.id.edtComfirm);
    }

}
