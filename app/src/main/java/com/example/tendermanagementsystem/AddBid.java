package com.example.tendermanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddBid extends AppCompatActivity {

    TextView tvAddBidTender ;
    EditText etBidCompany , etBidAmount , etBidDuration , etBidNotes ;
    Button btnSaveBid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bid);
        Link();
        final String tenderTitle = getIntent().getStringExtra("Tender_Title");
        tvAddBidTender.setText("المناقصة: " + (tenderTitle == null ? "غير محدد" : tenderTitle));

        btnSaveBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String company = etBidCompany.getText().toString().trim();
                String amountText = etBidAmount.getText().toString().trim();
                String durationText = etBidDuration.getText().toString().trim();
                String notes = etBidNotes.getText().toString().trim();

                // فحص قبل التحويل، حتى ما يصير Crash
                if (company.isEmpty() || amountText.isEmpty() || durationText.isEmpty()) {
                    Toast.makeText(AddBid.this, "الرجاء تعبئة الاسم والقيمة والمدة", Toast.LENGTH_SHORT).show();
                    return;
                }

                double amount = Double.parseDouble(amountText); // String → double
                int duration = Integer.parseInt(durationText);  // String → int

                Intent intent = new Intent(AddBid.this, activity_bid_details.class);
                intent.putExtra("Company_Name", company);
                intent.putExtra("Tender_Title", tenderTitle);
                intent.putExtra("Bid_Amount", amount);
                intent.putExtra("Bid_Duration", duration);
                intent.putExtra("Bid_Notes", notes);
                startActivity(intent);
                finish();
            }
        });

    }

    void Link() {
        tvAddBidTender = findViewById(R.id.tvAddBidTender);
        etBidCompany = findViewById(R.id.etBidCompany);
        etBidAmount = findViewById(R.id.etBidAmount);
        etBidDuration = findViewById(R.id.etBidDuration);
        etBidNotes = findViewById(R.id.etBidNotes);
        btnSaveBid = findViewById(R.id.btnSaveBid);
    }
}