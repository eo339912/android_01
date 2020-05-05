package com.example.tway;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static String NAME = "tway.db";
    public static int VERSION = 1;

    public DBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {


        String sql = "create table if not exists info("
                + " baby_num integer PRIMARY KEY autoincrement, "
                + " baby_name text, "
                + " gender text, "
                + " birthday text, "
                + " baby_face text, "
                + " abo text)";

        String sql2 = "create table if not exists BABYCARE ("
                + " IDX INTEGER PRIMARY KEY autoincrement, "
                + " M_DATE text, "
                + " S_DATE text, "
                + " M_TIME text, "
                + " S_TIME text, "
                + " M_MEMO text, "
                + " S_MEMO text, "
                + " M_SIZE integer, "
                + " S_SIZE integer)";

        db.execSQL(sql);
        db.execSQL(sql2);

    }

    public void onOpen(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS info");
            db.execSQL("DROP TABLE IF EXISTS BABYCARE");
            onCreate(db);
        }
    }
}

