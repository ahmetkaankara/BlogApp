package com.example.blogapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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

public class RegisterActivity extends AppCompatActivity {

    EditText registerEmail, registerPass;
    Button registerBtn;
    TextView login;

    FirebaseAuth mAuth;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerEmail = findViewById(R.id.register_email);
        registerPass = findViewById(R.id.register_password);
        registerBtn = findViewById(R.id.register_btn);
        login = findViewById(R.id.sign_account);

        mAuth = FirebaseAuth.getInstance();


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Kayıt Ol");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        progressDialog = new ProgressDialog(this);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = registerEmail.getText().toString().trim();
                String password = registerPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    registerEmail.setError("E-mail boş bırakılamaz!");
                }else if (TextUtils.isEmpty(password)){
                    registerPass.setError("Şifre boş bırakılamaz!");
                }else if (password.length() < 6 ){
                Toast.makeText(RegisterActivity.this,"Şifre uzunluğu 6 karakterden fazla olmalıdır.", Toast.LENGTH_SHORT).show();
                }else {
                    registerUser(email, password);

                }
            }
        });

    }

    private void registerUser(String email, String password) {
        progressDialog.setTitle("Lütfen Bekleyiniz...");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Giriş Başarısız", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}