package com.example.tway;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class InfoDAO {
    DBHelper dbhelper;
    String tableName = "info";

    //등록
    public void insert(Context context, InfoVO info){
        dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("baby_num", "select nvl(max(baby_num),0)+1 from info");
        contentValues.put("baby_name", info.getBaby_name());
        contentValues.put("gender", info.getGender());
        contentValues.put("birthday", info.getBirthday());
        contentValues.put("abo", info.getAbo());

        db.insert(tableName, null, contentValues);
        System.out.println("저장");
        dbhelper.close();
    }

}
