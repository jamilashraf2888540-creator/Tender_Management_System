package com.example.tendermanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_award extends AppCompatActivity {

    Spinner spAwardTender ;
    TextView tvWinningCompany , tvWinningAmount ;
    EditText etAwardNotes;
    Button btnAwardTender ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_award);
        Link();
        String company = getIntent().getStringExtra("Company_Name");
        if (company == null) company = "غير محدد";   // قبل العرض
        double amount = getIntent().getDoubleExtra("Bid_Amount", 0);
        tvWinningCompany.setText("الشركة الفائزة: " + company);
        tvWinningAmount.setText("قيمة العقد: " + String.format("%,.0f", amount) + " دولار");
        String[] tenders = {"توريد أجهزة حاسوب", "توريد طابعات", "صيانة شبكات"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, tenders) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = (TextView) super.getView(position, convertView, parent);
                tv.setTextColor(0xFFFFFFFF);
                return tv;
            }
        };
        spAwardTender.setAdapter(adapter);
        btnAwardTender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity_award.this, "تمت ترسية المناقصة بنجاح", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(activity_award.this, activity_dashboard.class);
                // يرجع للـ Dashboard الموجودة ويسكّر كل الشاشات اللي فوقها
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

    }

    void Link(){

        spAwardTender = findViewById(R.id.spAwardTender);
        tvWinningCompany = findViewById(R.id.tvWinningCompany);
        tvWinningAmount = findViewById(R.id.tvWinningAmount);
        etAwardNotes = findViewById(R.id.etAwardNotes);
        btnAwardTender = findViewById(R.id.btnAwardTender);

    }

}