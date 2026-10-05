package com.example.tendermanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_companies extends AppCompatActivity {

    EditText etSearchCompany ;
    Button btnAddCompany ;
    ListView lvCompanies ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies);
        Link();


        btnAddCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_companies.this, AddCompany.class);
                startActivity(intent);
            }
        });

    }

    void Link (){

        etSearchCompany = findViewById(R.id.etSearchCompany);
        btnAddCompany = findViewById(R.id.btnAddCompany);
        lvCompanies = findViewById(R.id.lvCompanies);

    }

}