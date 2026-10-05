package com.example.tendermanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activity_tender_details extends AppCompatActivity {

    TextView tvTenderTitle, tvTenderStatus, tvTenderInfo, tvTenderRequirements;
    Button btnAddBidFromTender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tender_details);
        Link();

        String tenderTitle = getIntent().getStringExtra("Tender_Title");
        String tenderDescription = getIntent().getStringExtra("Tender_Description");
        String estimatedBudget = getIntent().getStringExtra("Estimated_Budget");
        String openingDate = getIntent().getStringExtra("Opening_Date");
        String closingDate = getIntent().getStringExtra("Closing_Date");
        String requirements = getIntent().getStringExtra("Requirements");
        String department = getIntent().getStringExtra("Department");

        tvTenderTitle.setText(tenderTitle);

        tvTenderInfo.setText(
                "الوصف: " + tenderDescription +
                        "\nالقسم: " + department +
                        "\nالميزانية: " + estimatedBudget +
                        "\nتاريخ الفتح: " + openingDate +
                        "\nتاريخ الإغلاق: " + closingDate
        );

        tvTenderRequirements.setText(requirements);

        btnAddBidFromTender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_tender_details.this, activity_bids.class);
                intent.putExtra("Tender_Title", tenderTitle);
                startActivity(intent);
            }
        });
    }

    void Link() {
        tvTenderTitle = findViewById(R.id.tvTenderTitle);
        tvTenderStatus = findViewById(R.id.tvTenderStatus);
        tvTenderInfo = findViewById(R.id.tvTenderInfo);
        tvTenderRequirements = findViewById(R.id.tvTenderRequirements);
        btnAddBidFromTender = findViewById(R.id.btnAddBidFromTender);
    }
}