package com.example.tendermanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tendermanagementsystem.Database.Database_Adapter;

import java.util.ArrayList;

public class activity_tenders extends AppCompatActivity {

    EditText etSearchTender;
    Spinner spTenderFilter;
    Button btnAddTender;
    RecyclerView rvTenders;

    DatabaseHelper databaseHelper;
    Database_Adapter adapter;
    ArrayList<TenderModel> tendersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenders);
        Link();

        databaseHelper = new DatabaseHelper(this);

        rvTenders.setLayoutManager(new LinearLayoutManager(this));

        btnAddTender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_tenders.this, activity_create_tender.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTenders();
    }

    private void loadTenders() {
        tendersList = databaseHelper.getAllTenders();

        adapter = new Database_Adapter(tendersList, new Database_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(TenderModel tender) {
                Intent intent = new Intent(activity_tenders.this, activity_tender_details.class);
                intent.putExtra("Tender_Title", tender.getTitle());
                intent.putExtra("Tender_Description", tender.getDescription());
                intent.putExtra("Estimated_Budget", tender.getBudget());
                intent.putExtra("Opening_Date", tender.getOpeningDate());
                intent.putExtra("Closing_Date", tender.getClosingDate());
                intent.putExtra("Requirements", tender.getRequirements());
                intent.putExtra("Department", tender.getDepartment());
                startActivity(intent);
            }
        });

        rvTenders.setAdapter(adapter);
    }

    void Link() {
        etSearchTender = findViewById(R.id.etSearchTender);
        spTenderFilter = findViewById(R.id.spTenderFilter);
        btnAddTender = findViewById(R.id.btnAddTender);
        rvTenders = findViewById(R.id.rvTenders);
    }
}