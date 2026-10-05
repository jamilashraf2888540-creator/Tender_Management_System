package com.example.tendermanagementsystem;

import java.util.ArrayList;

public class SampleData {

    public static ArrayList<Bid> getBids() {
        ArrayList<Bid> list = new ArrayList<Bid>();
        list.add(new Bid("شركة المستقبل", 45000, 30, 85, 90, "ضمان سنتين على الأجهزة"));
        list.add(new Bid("تك سوليوشنز", 41000, 45, 70, 75, "ضمان سنة واحدة"));
        list.add(new Bid("الأفق للحاسوب", 48000, 25, 92, 80, "ضمان 3 سنوات مع دعم فني"));
        return list;
    }
}
