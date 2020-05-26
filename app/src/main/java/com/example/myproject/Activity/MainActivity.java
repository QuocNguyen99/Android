package com.example.myproject.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.Class.Chair;
import com.example.myproject.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btnDangNhap;
    Button btnGG;
    EditText edtUser, edtPass;
    TextView txtDangKy,txtForget;
    CheckBox cbLuu;

    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    DatabaseReference mData;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        edtUser.setText(sharedPreferences.getString("taikhoan", ""));
        edtPass.setText(sharedPreferences.getString("matkhau", ""));
        cbLuu.setChecked(sharedPreferences.getBoolean("checked", false));

        mAuth = FirebaseAuth.getInstance();
        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhap();
            }
        });

        txtForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        btnGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });
    }

    private void DangNhap() {
        final String email = edtUser.getText().toString();
        final String password = edtPass.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(MainActivity.this, InforActivity.class);
                            startActivity(intent);
                            finish();
                            //luu neu check
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            if (cbLuu.isChecked()) {
                                editor.putString("taikhoan", email);
                                editor.putString("matkhau", password);
                                editor.putBoolean("checked", true);
                                editor.commit()
                                ;
                            } else {
                                //xoa neu k check
                                editor.remove("taikhoan");
                                editor.remove("matkhau");
                                editor.remove("checked");
                                editor.commit();
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "Sai tài khoản mật khẩu", Toast.LENGTH_SHORT).show();
//                            xoa luu neu login sai
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("taikhoan");
                            editor.remove("matkhau");
                            editor.remove("checked");
                            editor.commit();
                        }
                    }
                });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                            Intent intent = new Intent(getApplicationContext(), InforActivity.class);
                            startActivity(intent);


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Sai thông tin đăng nhập", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    private void Anhxa() {
        btnDangNhap = (Button) findViewById(R.id.buttonConfirm);
        btnGG       = (Button) findViewById(R.id.btnLoginGG);
        edtPass     = (EditText) findViewById(R.id.editTextPass);
        edtUser     = (EditText) findViewById(R.id.editTextUser);
        txtDangKy   = (TextView) findViewById(R.id.textViewRegist);
        txtForget   =(TextView) findViewById(R.id.textViewforgot);
        cbLuu = (CheckBox) findViewById(R.id.checkBoxRemember);

        mData = FirebaseDatabase.getInstance().getReference();

    }
}

