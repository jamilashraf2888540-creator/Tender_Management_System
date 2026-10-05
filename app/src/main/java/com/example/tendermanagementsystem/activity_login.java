package com.example.tendermanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_login extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findById();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // equals لمقارنة النصوص، و && لأن الاثنين لازم يكونوا صح
                if (user.equals("admin") && password.equals("123456")) {
                    Intent intent = new Intent(activity_login.this, activity_dashboard.class);
                    intent.putExtra("user", user); // الاسم فقط، بدون كلمة المرور
                    startActivity(intent);
                    finish(); // حتى لا يرجع المستخدم لشاشة الدخول بزر الرجوع
                } else {
                    Toast.makeText(activity_login.this,
                            "اسم المستخدم أو كلمة المرور غير صحيحة",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // لاحقاً: شاشة نسيت كلمة المرور
            }
        });
    }

    void findById() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
    }
}