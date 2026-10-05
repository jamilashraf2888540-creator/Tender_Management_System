package com.example.tendermanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_settings extends AppCompatActivity {

    TextView tvAccountSettings , tvNotificationSettings , tvDatabaseSettings , tvAbout ;
    Button btnLogout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Link();
        View.OnClickListener comingSoon = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity_settings.this, "قريباً", Toast.LENGTH_SHORT).show();


            }
        };

        tvAccountSettings.setOnClickListener(comingSoon);
        tvNotificationSettings.setOnClickListener(comingSoon);
        tvDatabaseSettings.setOnClickListener(comingSoon);
        tvAbout.setOnClickListener(comingSoon);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_settings.this , activity_login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }

    void Link(){

        tvAccountSettings = findViewById(R.id.tvAccountSettings);
        tvNotificationSettings = findViewById(R.id.tvNotificationSettings);
        tvDatabaseSettings = findViewById(R.id.tvDatabaseSettings);
        tvAbout = findViewById(R.id.tvAbout);
        btnLogout = findViewById(R.id.btnLogout);

    }

}