package com.example.tendermanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_evaluation extends AppCompatActivity {

    // الأوزان: مجموعها لازم = 1.0
    static final double W_PRICE = 0.5;
    static final double W_QUALITY = 0.3;
    static final double W_EXPERIENCE = 0.2;

    TextView tvEvaluationCompany, tvTotalScore;
    Button btnSaveEvaluation;
    SeekBar seekPrice, seekQuality, seekExperience;
    EditText etEvaluationNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);
        Link();

        String companyName = getIntent().getStringExtra("Company_Name");
        if (companyName == null) {
            companyName = "غير محدد"; // حماية في حال لم يصل الاسم
        }
        tvEvaluationCompany.setText("الشركة: " + companyName);

        // Listener واحد نستخدمه للثلاث SeekBars بدل تكراره 3 مرات
        SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                calculateTotalScore();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };
        seekPrice.setOnSeekBarChangeListener(listener);
        seekQuality.setOnSeekBarChangeListener(listener);
        seekExperience.setOnSeekBarChangeListener(listener);

        btnSaveEvaluation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double total = calculateTotalScore();
                String notes = etEvaluationNotes.getText().toString().trim();

                Intent intent = new Intent(activity_evaluation.this, activity_award.class);
                intent.putExtra("Company_Name", getIntent().getStringExtra("Company_Name"));
                intent.putExtra("Bid_Amount", getIntent().getDoubleExtra("Bid_Amount", 0));
                intent.putExtra("Total_Score", total);
                startActivity(intent);
            }
        });
    }

    // نتيجة موزونة بدل المتوسط العادي، وتستخدم double حتى لا تضيع الكسور
    double calculateTotalScore() {
        double total = seekPrice.getProgress() * W_PRICE
                + seekQuality.getProgress() * W_QUALITY
                + seekExperience.getProgress() * W_EXPERIENCE;

        tvTotalScore.setText(String.format("النتيجة النهائية: %.1f / 100", total));
        return total;
    }

    void Link() {
        tvEvaluationCompany = findViewById(R.id.tvEvaluationCompany);
        tvTotalScore = findViewById(R.id.tvTotalScore);
        btnSaveEvaluation = findViewById(R.id.btnSaveEvaluation);
        seekPrice = findViewById(R.id.seekPrice);
        seekQuality = findViewById(R.id.seekQuality);
        seekExperience = findViewById(R.id.seekExperience);
        etEvaluationNotes = findViewById(R.id.etEvaluationNotes);
    }
}