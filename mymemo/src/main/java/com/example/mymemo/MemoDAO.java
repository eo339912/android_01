package com.example.mymemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MemoDAO {
    DBHelper dbhelper;
    String tableName = "memo";

    //목록조회
    public ArrayList<HashMap<String, String>> selectAll(Context context){

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        dbhelper = new DBHelper(context);
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        String sql = "select _id, title, content, time from memo order by _id desc ";
        Cursor cursor=db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("_id", cursor.getString(0));
            map.put("title", cursor.getString(1));
            map.put("content", cursor.getString(2));
            map.put("time", cursor.getString(3));
            list.add(map);
        }
        dbhelper.close();
        System.out.println(list);
        return list;
    }
    //등록
    public void insert(Context context, MemoVO memo){
        dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        //contentValues.put("_id", memo.get_id());
        contentValues.put("title", memo.getTitle());
        contentValues.put("content", memo.getContent());

        //현재시간 설정
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String formatDate = sdfNow.format(mDate);
        contentValues.put("time", formatDate);

        db.insert(tableName, null, contentValues);
        System.out.println("저장");
        dbhelper.close();
    }

    //수정
    public void update(Context context, MemoVO memo){
        dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();   //데이터베이스 연결

        ContentValues contentValues = new ContentValues();
        contentValues.put("title", memo.getTitle());
        contentValues.put("content", memo.getContent());
        String id = Integer.toString(memo.get_id());
        db.update(tableName, contentValues, "_id=?", new String[]{id}) ;
        dbhelper.close();
    }

    //삭제
    public void delete(Context context, String id){
        dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.delete(tableName, "_id=?", new String[]{id}) ;
        dbhelper.close();
    }


    //단건조회
}
