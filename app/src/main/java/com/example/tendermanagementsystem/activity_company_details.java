package com.example.tendermanagementsystem;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_company_details extends AppCompatActivity {

    TextView tvCompanyName , tvCompanyStatus , tvRegistration , tvPhone , tvEmail ;

    ListView lvCompanyBids ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);
        Link();
        String name = getIntent().getStringExtra("Company_Name");
        String status = getIntent().getStringExtra("Company_Status");
        String registration = getIntent().getStringExtra("Registration_No");
        String phone = getIntent().getStringExtra("Phone");
        String email = getIntent().getStringExtra("Email");

        if (name == null) name = "غير محدد";
        if (status == null) status = "غير محدد";
        if (registration == null) registration = "غير محدد";
        if (phone == null) phone = "غير محدد";
        if (email == null) email = "غير محدد";

        tvCompanyName.setText(name);
        tvCompanyStatus.setText(status);
        tvRegistration.setText("رقم التسجيل: " + registration);
        tvPhone.setText("الهاتف: " + phone);
        tvEmail.setText("البريد: " + email);

    }

    void Link(){

        tvCompanyName = findViewById(R.id.tvCompanyName);
        tvCompanyStatus = findViewById(R.id.tvCompanyStatus);
        tvRegistration = findViewById(R.id.tvRegistration);
        tvPhone = findViewById(R.id.tvPhone);
        tvEmail = findViewById(R.id.tvEmail);
        lvCompanyBids = findViewById(R.id.lvCompanyBids);

    }

}