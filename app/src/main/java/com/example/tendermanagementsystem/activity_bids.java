package com.example.tendermanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activity_bids extends AppCompatActivity {

    Spinner spBidTender;
    Button btnAddBid, btnCompareBids;
    ListView lvBids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bids);
        Link();

        // عنوان المناقصة القادم من Tender Details
        String received = getIntent().getStringExtra("Tender_Title");
        final String tenderTitle = (received == null) ? "غير محدد" : received;

        // الـ Spinner بيعرض المناقصة الحالية فقط (نص أبيض لأن الخلفية غامقة)
        String[] tenders = {tenderTitle};
        ArrayAdapter<String> tenderAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, tenders) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = (TextView) super.getView(position, convertView, parent);
                tv.setTextColor(0xFFFFFFFF);
                return tv;
            }
        };
        spBidTender.setAdapter(tenderAdapter);

        // العروض التجريبية
        final ArrayList<Bid> bids = SampleData.getBids();
        String[] items = new String[bids.size()];
        for (int i = 0; i < bids.size(); i++) {
            Bid b = bids.get(i);
            items[i] = b.getCompanyName() + "\n"
                    + String.format("%,.0f", b.getAmount()) + " دولار | "
                    + b.getDurationDays() + " يوم";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = (TextView) super.getView(position, convertView, parent);
                tv.setTextColor(0xFFFFFFFF);
                return tv;
            }
        };
        lvBids.setAdapter(adapter);

        // الضغط على عرض يفتح تفاصيله
        lvBids.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bid b = bids.get(position);
                Intent intent = new Intent(activity_bids.this, activity_bid_details.class);
                intent.putExtra("Company_Name", b.getCompanyName());
                intent.putExtra("Tender_Title", tenderTitle);
                intent.putExtra("Bid_Amount", b.getAmount());
                intent.putExtra("Bid_Duration", b.getDurationDays());
                intent.putExtra("Bid_Notes", b.getNotes());
                startActivity(intent);
            }
        });

        btnAddBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_bids.this, AddBid.class);
                intent.putExtra("Tender_Title", tenderTitle);
                startActivity(intent);
            }
        });

        btnCompareBids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_bids.this, activity_compare_bids.class);
                intent.putExtra("Tender_Title", tenderTitle);
                startActivity(intent);
            }
        });
    }

    void Link() {
        spBidTender = findViewById(R.id.spBidTender);
        btnAddBid = findViewById(R.id.btnAddBid);
        btnCompareBids = findViewById(R.id.btnCompareBids);
        lvBids = findViewById(R.id.lvBids);
    }
}
