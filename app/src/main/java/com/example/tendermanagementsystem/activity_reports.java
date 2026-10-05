package com.example.tendermanagementsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_reports extends AppCompatActivity {

    TextView tvReportTenders, tvReportCompanies, tvReportBids, tvReportContracts;
    Button btnGenerateReport;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        Link();

        databaseHelper = new DatabaseHelper(this);
        loadReportsData();

        btnGenerateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity_reports.this, "تم إنشاء التقرير بنجاح", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadReportsData();
    }

    private void loadReportsData() {
        int totalTenders = databaseHelper.getTendersCount();
        int totalCompanies = 35; // يمكن ربطها لاحقاً بجدول الشركات
        int totalBids = 72;      // يمكن ربطها لاحقاً بجدول العروض
        int totalContracts = 12; // يمكن ربطها لاحقاً بجدول العقود

        tvReportTenders.setText("إجمالي المناقصات\n" + totalTenders);
        tvReportCompanies.setText("إجمالي الشركات\n" + totalCompanies);
        tvReportBids.setText("إجمالي العروض\n" + totalBids);
        tvReportContracts.setText("إجمالي العقود\n" + totalContracts);
    }

    void Link(){
        tvReportTenders = findViewById(R.id.tvReportTenders);
        tvReportCompanies = findViewById(R.id.tvReportCompanies);
        tvReportBids = findViewById(R.id.tvReportBids);
        tvReportContracts = findViewById(R.id.tvReportContracts);
        btnGenerateReport = findViewById(R.id.btnGenerateReport);
    }
}