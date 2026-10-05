package com.example.tendermanagementsystem;

import android.app.DatePickerDialog;
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

import java.util.Calendar;

public class activity_create_tender extends AppCompatActivity {

    EditText etTenderTitle, etTenderDescription, etEstimatedBudget,
            etOpeningDate, etClosingDate, etRequirements;
    Spinner spDepartment;
    Button btnCreateTender;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tender);
        Link();

        databaseHelper = new DatabaseHelper(this);

        // قائمة الأقسام، نص أبيض لأن الخلفية غامقة
        String[] departments = {"تقنية المعلومات", "الشبكات", "الأمن السيبراني", "الدعم الفني"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, departments) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView tv = (TextView) super.getView(position, convertView, parent);
                tv.setTextColor(0xFFFFFFFF);
                return tv;
            }
        };
        spDepartment.setAdapter(adapter);

        // اختيار التاريخ بالضغط على الحقل (الحقول focusable=false)
        etOpeningDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { pickDate(etOpeningDate); }
        });
        etClosingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { pickDate(etClosingDate); }
        });

        btnCreateTender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTenderTitle.getText().toString().trim();
                String description = etTenderDescription.getText().toString().trim();
                String budget = etEstimatedBudget.getText().toString().trim();
                String opening = etOpeningDate.getText().toString().trim();
                String closing = etClosingDate.getText().toString().trim();
                String requirements = etRequirements.getText().toString().trim();
                String department = spDepartment.getSelectedItem().toString();

                // فحص الحقول الإلزامية قبل الحفظ
                if (title.isEmpty() || budget.isEmpty() || opening.isEmpty() || closing.isEmpty()) {
                    Toast.makeText(activity_create_tender.this,
                            "الرجاء تعبئة العنوان والميزانية والتواريخ", Toast.LENGTH_SHORT).show();
                    return;
                }

                TenderModel tender = new TenderModel(title, description, budget,
                        opening, closing, requirements, department);
                databaseHelper.insertTender(tender);

                Toast.makeText(activity_create_tender.this, "تم حفظ المناقصة", Toast.LENGTH_SHORT).show();
                finish(); // activity_tenders.onResume بيحدّث القائمة
            }
        });
    }

    // DatePickerDialog: الشهر يبدأ من 0 فنضيف 1
    private void pickDate(final EditText target) {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {
                target.setText(year + "/" + (month + 1) + "/" + day);
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    void Link() {
        etTenderTitle = findViewById(R.id.etTenderTitle);
        etTenderDescription = findViewById(R.id.etTenderDescription);
        spDepartment = findViewById(R.id.spDepartment);
        etEstimatedBudget = findViewById(R.id.etEstimatedBudget);
        etOpeningDate = findViewById(R.id.etOpeningDate);
        etClosingDate = findViewById(R.id.etClosingDate);
        etRequirements = findViewById(R.id.etRequirements);
        btnCreateTender = findViewById(R.id.btnCreateTender);
    }
}
