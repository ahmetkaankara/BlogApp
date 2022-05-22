package com.example.blogapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    EditText registerEmail, registerPass,username,surname;
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
        username = findViewById(R.id.username);
        surname = findViewById(R.id.surname);
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
                String usernameN = username.getText().toString().trim();
                String surnameN = surname.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    registerEmail.setError("E-mail boş bırakılamaz!");
                }else if (TextUtils.isEmpty(password)){
                    registerPass.setError("Şifre boş bırakılamaz!");
                }else if (password.length() < 6 ){
                Toast.makeText(RegisterActivity.this,"Şifre uzunluğu 6 karakterden fazla olmalıdır.", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(usernameN)){
                    registerPass.setError("Ad kısmı boş bırakılamaz!");
                }else if (TextUtils.isEmpty(surnameN)){
                    registerPass.setError("Soy ad kısmı boş bırakılamaz!");
                }else {
                    registerUser(email, password,usernameN,surnameN);

                }
            }
        });

    }


    private void registerUser(String email, String password,String username,String surname) {
        progressDialog.setTitle("Lütfen Bekleyiniz...");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            updateUserInfo(username,surname);
                            progressDialog.dismiss();

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

    private void updateUserInfo(String username,String surname) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String fullName = username + " "+ surname;

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(fullName)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                        }
                    }
                });

    }
}