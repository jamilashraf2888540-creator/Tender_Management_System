package com.example.tendermanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_TENDERS = "tenders";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_BUDGET = "budget";
    public static final String COLUMN_OPENING_DATE = "opening_date";
    public static final String COLUMN_CLOSING_DATE = "closing_date";
    public static final String COLUMN_REQUIREMENTS = "requirements";
    public static final String COLUMN_DEPARTMENT = "department";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TENDERS_TABLE = "CREATE TABLE " + TABLE_TENDERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_DESCRIPTION + " TEXT, "
                + COLUMN_BUDGET + " TEXT, "
                + COLUMN_OPENING_DATE + " TEXT, "
                + COLUMN_CLOSING_DATE + " TEXT, "
                + COLUMN_REQUIREMENTS + " TEXT, "
                + COLUMN_DEPARTMENT + " TEXT" + ")";
        db.execSQL(CREATE_TENDERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TENDERS);
        onCreate(db);
    }

    public boolean insertTender(TenderModel tender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, tender.getTitle());
        cv.put(COLUMN_DESCRIPTION, tender.getDescription());
        cv.put(COLUMN_BUDGET, tender.getBudget());
        cv.put(COLUMN_OPENING_DATE, tender.getOpeningDate());
        cv.put(COLUMN_CLOSING_DATE, tender.getClosingDate());
        cv.put(COLUMN_REQUIREMENTS, tender.getRequirements());
        cv.put(COLUMN_DEPARTMENT, tender.getDepartment());

        long result = db.insert(TABLE_TENDERS, null, cv);
        db.close();
        return result != -1;
    }

    public ArrayList<TenderModel> getAllTenders() {
        ArrayList<TenderModel> tendersList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TENDERS, null);

        if (cursor.moveToFirst()) {
            do {
                TenderModel tender = new TenderModel();
                tender.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                tender.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE)));
                tender.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
                tender.setBudget(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BUDGET)));
                tender.setOpeningDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_OPENING_DATE)));
                tender.setClosingDate(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CLOSING_DATE)));
                tender.setRequirements(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_REQUIREMENTS)));
                tender.setDepartment(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DEPARTMENT)));

                tendersList.add(tender);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tendersList;
    }

    // دالة جديدة لجلب العدد الإجمالي للمناقصات لإحصائيات لوحة التحكم والتقارير
    public int getTendersCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_TENDERS, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return count;
    }
}