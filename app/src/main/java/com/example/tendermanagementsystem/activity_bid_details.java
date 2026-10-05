package com.example.tendermanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_bid_details extends AppCompatActivity {

    TextView tvBidCompany , tvBidTender , tvBidAmount , tvBidDuration , tvBidNotes ;
    Button btnEvaluateBid ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_details);
        Link();
        String company = getIntent().getStringExtra("Company_Name");
        String tender = getIntent().getStringExtra("Tender_Title");
        double amount = getIntent().getDoubleExtra("Bid_Amount", 0);
        int duration = getIntent().getIntExtra("Bid_Duration", 0);
        String notes = getIntent().getStringExtra("Bid_Notes");
        if (company == null) {company = "غير محدد";}
        if (tender == null) {tender = "غير محدد";}
        if (notes == null || notes.isEmpty()) {notes = "لا يوجد";}
        tvBidCompany.setText("الشركة: " + company);
        tvBidTender.setText("المناقصة: " + tender);
        tvBidAmount.setText("قيمة العرض: " + String.format("%,.0f", amount) + " دولار");
        tvBidDuration.setText("مدة التنفيذ: " + duration + " يوم");
        tvBidNotes.setText(notes);
        String companyName = company;
        double bidAmount = amount;
        btnEvaluateBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_bid_details.this, activity_evaluation.class);
                intent.putExtra("Company_Name", companyName);
                intent.putExtra("Bid_Amount", bidAmount);
                startActivity(intent);
            }
        });

    }

    void Link (){

        tvBidCompany = findViewById(R.id.tvBidCompany);
        tvBidTender = findViewById(R.id.tvBidTender);
        tvBidAmount = findViewById(R.id.tvBidAmount);
        tvBidDuration = findViewById(R.id.tvBidDuration);
        tvBidNotes = findViewById(R.id.tvBidNotes);
        btnEvaluateBid = findViewById(R.id.btnEvaluateBid);

    }

}