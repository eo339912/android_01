package com.example.tway;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class InfoDAO {
    DBHelper dbhelper;
    String tableName = "info";

    //등록
    public void insert(Context context, InfoVO info){
        dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("baby_num", info.getBaby_num());
        contentValues.put("baby_name", info.getBaby_name());
        contentValues.put("gender", info.getGender());
        contentValues.put("birthday", info.getBirthday());
        contentValues.put("baby_face", info.getBaby_face());
        contentValues.put("abo", info.getAbo());

        db.insert(tableName, null, contentValues);
        System.out.println("저장");
        dbhelper.close();

    }

    //한건조회
    public ArrayList<HashMap<String, String>> selectOne(Context context){
        dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        String sql = "select baby_name, gender, birthday, abo, baby_face, MAX(rowid) from info"+";";


        Cursor cursor=db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("baby_name", cursor.getString(0));
            map.put("gender", cursor.getString(1));
            map.put("birthday", cursor.getString(2));
            map.put("abo", cursor.getString(3));
            map.put("baby_face", cursor.getString(4));
            list.add(map);
        }

        dbhelper.close();
        System.out.println(list);
        return list;


    }
}


