package com.example.tendermanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activity_dashboard extends AppCompatActivity {

    TextView tvWelcome, tvTotalTenders, tvOpenTenders, tvReviewTenders, tvAwardedTenders, tvContractValue;
    ListView lvRecentTenders;
    Button btnDashboardTenders, btnDashboardCompanies, btnDashboardReports;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Link();

        databaseHelper = new DatabaseHelper(this);

        String user = getIntent().getStringExtra("user");
        if (user != null) {
            tvWelcome.setText("مرحباً بك " + user + " 👋");
        }

        loadDashboardData();

        btnDashboardTenders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_dashboard.this, activity_tenders.class);
                startActivity(intent);
            }
        });

        btnDashboardCompanies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_dashboard.this, activity_companies.class);
                startActivity(intent);
            }
        });

        btnDashboardReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_dashboard.this, activity_reports.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // لتحديث البيانات تلقائياً عند العودة للوحة التحكم بعد إضافة مناقصة جديدة
        loadDashboardData();
    }

    private void loadDashboardData() {
        // 1. تحديث الإحصائيات (عدد المناقصات)
        int count = databaseHelper.getTendersCount();
        tvTotalTenders.setText("إجمالي المناقصات\n" + count);

        // 2. جلب المناقصات من قاعدة البيانات وعرضها في الـ ListView
        ArrayList<TenderModel> tendersList = databaseHelper.getAllTenders();
        ArrayList<String> displayList = new ArrayList<>();

        for (TenderModel tender : tendersList) {
            displayList.add(tender.getTitle() + " | القسم: " + tender.getDepartment());
        }

        // استخدام ArrayAdapter لعرض النصوص في القائمة
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        lvRecentTenders.setAdapter(adapter);
    }

    void Link() {
        tvWelcome = findViewById(R.id.tvWelcome);
        tvTotalTenders = findViewById(R.id.tvTotalTenders);
        tvOpenTenders = findViewById(R.id.tvOpenTenders);
        tvReviewTenders = findViewById(R.id.tvReviewTenders);
        tvAwardedTenders = findViewById(R.id.tvAwardedTenders);
        tvContractValue = findViewById(R.id.tvContractValue);
        lvRecentTenders = findViewById(R.id.lvRecentTenders);
        btnDashboardTenders = findViewById(R.id.btnDashboardTenders);
        btnDashboardCompanies = findViewById(R.id.btnDashboardCompanies);
        btnDashboardReports = findViewById(R.id.btnDashboardReports);
    }
}