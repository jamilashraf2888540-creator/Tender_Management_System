package com.example.tendermanagementsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// محرك اختيار أفضل عرض: بيحسب نتيجة موزونة لكل عرض وبيرتبهم من الأفضل للأسوأ
public class BidEvaluator {

    // الأوزان: مجموعها لازم = 1.0
    public static final double W_PRICE = 0.5;
    public static final double W_QUALITY = 0.3;
    public static final double W_EXPERIENCE = 0.2;

    public static ArrayList<Bid> rank(ArrayList<Bid> bids) {
        if (bids.isEmpty()) {
            return bids;
        }

        // أرخص سعر هو المرجع: بياخد 100، والباقي نسبة منه
        double lowest = bids.get(0).getAmount();
        for (Bid b : bids) {
            if (b.getAmount() < lowest) {
                lowest = b.getAmount();
            }
        }

        for (Bid b : bids) {
            double priceScore = (lowest / b.getAmount()) * 100;
            double total = priceScore * W_PRICE
                    + b.getQualityScore() * W_QUALITY
                    + b.getExperienceScore() * W_EXPERIENCE;
            b.setFinalScore(total);
        }

        // ترتيب تنازلي: الأول بالقائمة هو الأفضل
        Collections.sort(bids, new Comparator<Bid>() {
            @Override
            public int compare(Bid a, Bid b) {
                return Double.compare(b.getFinalScore(), a.getFinalScore());
            }
        });
        return bids;
    }
}
