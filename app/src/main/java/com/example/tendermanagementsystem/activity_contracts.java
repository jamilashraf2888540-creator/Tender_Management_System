package com.example.tendermanagementsystem;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_contracts extends AppCompatActivity {

    Spinner spContractStatus;
    ListView lvContracts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contracts);
        Link();

        // فلتر الحالة (قائمة ثابتة)، نص أبيض لأن الخلفية غامقة
        String[] statuses = {"الكل", "ساري", "منتهي", "ملغي"};
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, statuses) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = (TextView) super.getView(position, convertView, parent);
                tv.setTextColor(0xFFFFFFFF);
                return tv;
            }
        };
        spContractStatus.setAdapter(statusAdapter);


        final String[] companies = {"شركة المستقبل", "تك سوليوشنز", "الأفق للحاسوب"};
        final String[] tenders = {"توريد أجهزة حاسوب", "توريد طابعات", "صيانة شبكات"};
        final double[] amounts = {45000, 12000, 8500};
        final String[] contractStatuses = {"ساري", "منتهي", "ساري"};

        String[] items = new String[companies.length];
        for (int i = 0; i < companies.length; i++) {
            items[i] = companies[i] + "\n" + tenders[i] + " | " + contractStatuses[i];
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
        lvContracts.setAdapter(adapter);

        lvContracts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(activity_contracts.this,
                        companies[position] + ": " + String.format("%,.0f", amounts[position]) + " دولار",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    void Link() {
        spContractStatus = findViewById(R.id.spContractStatus);
        lvContracts = findViewById(R.id.lvContracts);
    }
}