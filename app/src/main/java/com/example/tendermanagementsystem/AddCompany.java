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

import androidx.appcompat.app.AppCompatActivity;

public class AddCompany extends AppCompatActivity {

    EditText etCompanyName, etCompanyRegistration, etCompanyPhone, etCompanyEmail;
    Spinner spCompanyStatus;
    Button btnSaveCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);
        Link();

        // حالات الشركة (قائمة ثابتة)، نص أبيض لأن الخلفية غامقة
        String[] statuses = {"نشطة", "موقوفة"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, statuses) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = (TextView) super.getView(position, convertView, parent);
                tv.setTextColor(0xFFFFFFFF);
                return tv;
            }
        };
        spCompanyStatus.setAdapter(adapter);

        btnSaveCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etCompanyName.getText().toString().trim();
                String status = spCompanyStatus.getSelectedItem().toString();
                String registration = etCompanyRegistration.getText().toString().trim();
                String phone = etCompanyPhone.getText().toString().trim();
                String email = etCompanyEmail.getText().toString().trim();

                // الاسم ورقم التسجيل إلزاميين، والباقي اختياري
                if (name.isEmpty() || registration.isEmpty()) {
                    Toast.makeText(AddCompany.this,
                            "الرجاء تعبئة اسم الشركة ورقم التسجيل",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // نفس المفاتيح اللي بتقراها activity_company_details
                Intent intent = new Intent(AddCompany.this, activity_company_details.class);
                intent.putExtra("Company_Name", name);
                intent.putExtra("Company_Status", status);
                intent.putExtra("Registration_No", registration);
                intent.putExtra("Phone", phone);
                intent.putExtra("Email", email);
                startActivity(intent);
                finish(); // حتى لا يرجع المستخدم للنموذج وهو معبّى
            }
        });
    }

    void Link() {
        etCompanyName = findViewById(R.id.etCompanyName);
        spCompanyStatus = findViewById(R.id.spCompanyStatus);
        etCompanyRegistration = findViewById(R.id.etCompanyRegistration);
        etCompanyPhone = findViewById(R.id.etCompanyPhone);
        etCompanyEmail = findViewById(R.id.etCompanyEmail);
        btnSaveCompany = findViewById(R.id.btnSaveCompany);
    }
}