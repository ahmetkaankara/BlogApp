package com.example.blogapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;


    EditText loginEmail,loginPass;
    Button loginBtn;
    TextView forgot, sign;
    ProgressDialog progressDialog;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        loginEmail = findViewById(R.id.login_email);
        loginPass = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_btn);
        forgot = findViewById(R.id.forgot_password);
        sign = findViewById(R.id.sign_account);

        progressDialog = new ProgressDialog(this);


        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = loginEmail.getText().toString().trim();
                String password = loginPass.getText().toString().trim(); ;

                if (TextUtils.isEmpty(email)){
                    loginEmail.setError("E-mail boş bırakılamaz!");
                }else if (TextUtils.isEmpty(password)){
                    loginPass.setError("Şifre boş bırakılamaz!");
                }else if (password.length() < 6 ){
                    Toast.makeText(MainActivity.this,"Şifre uzunluğu 6 karakterden fazla olmalıdır.", Toast.LENGTH_SHORT).show();
                }else {
                    login(email,password);
                }
            }
        });

    }

    private void login(String email, String password) {
        progressDialog.setTitle("Lütfen Bekleyiniz...");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        }else {
                            Toast.makeText(MainActivity.this, "Giriş Başarısız", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }




}