package com.example.tendermanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activity_compare_bids extends AppCompatActivity {

    Spinner spCompareTender;
    TextView tvCompareResult;
    ListView lvCompareBids;
    Button btnStartEvaluation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_bids);
        Link();

        String received = getIntent().getStringExtra("Tender_Title");
        String tenderTitle = (received == null) ? "غير محدد" : received;

        // الـ Spinner بيعرض المناقصة الحالية فقط
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
        spCompareTender.setAdapter(tenderAdapter);

        // ترتيب العروض من الأفضل للأسوأ
        ArrayList<Bid> ranked = BidEvaluator.rank(SampleData.getBids());

        if (ranked.isEmpty()) {
            tvCompareResult.setText("لا توجد عروض للمقارنة");
            btnStartEvaluation.setEnabled(false);
            return;
        }

        final Bid best = ranked.get(0);
        tvCompareResult.setText("أفضل عرض: " + best.getCompanyName()
                + "\nالنتيجة: " + String.format("%.1f", best.getFinalScore()) + " / 100");

        String[] items = new String[ranked.size()];
        for (int i = 0; i < ranked.size(); i++) {
            Bid b = ranked.get(i);
            items[i] = (i + 1) + ". " + b.getCompanyName()
                    + " - " + String.format("%.1f", b.getFinalScore()) + " نقطة"
                    + "\n" + String.format("%,.0f", b.getAmount()) + " دولار | "
                    + b.getDurationDays() + " يوم | جودة " + b.getQualityScore()
                    + " | خبرة " + b.getExperienceScore();
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
        lvCompareBids.setAdapter(adapter);

        // الانتقال للتقييم بأفضل عرض
        btnStartEvaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_compare_bids.this, activity_evaluation.class);
                intent.putExtra("Company_Name", best.getCompanyName());
                intent.putExtra("Bid_Amount", best.getAmount());
                startActivity(intent);
            }
        });
    }

    void Link() {
        spCompareTender = findViewById(R.id.spCompareTender);
        tvCompareResult = findViewById(R.id.tvCompareResult);
        lvCompareBids = findViewById(R.id.lvCompareBids);
        btnStartEvaluation = findViewById(R.id.btnStartEvaluation);
    }
}
