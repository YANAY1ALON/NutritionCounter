package com.example.user.nutritioncounter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Tablet extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "NCdb.db";
    private static final int DATABASE_VERSION = 1;

    public Tablet(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        onCreate(db);
    }

}
