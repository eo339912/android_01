package com.example.tway;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CareDAO {
    DBHelper dbhelper = null;
    String tableName = "BABYCARE";


    //분유목록조회
    public ArrayList<HashMap<String, String>> selectAllMilk(Context context, CareVO vo){

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        dbhelper = new DBHelper(context);
        SQLiteDatabase db= dbhelper.getReadableDatabase(); //데이터베이스 연결

        String sql = "select * from BABYCARE where M_DATE = ? order by IDX ";
        Cursor cursor=db.rawQuery(sql, new String[] {vo.getM_date()}); // sql구문 실행
        while (cursor.moveToNext()){                      // 결과(Resultset)를 list에 담기
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("IDX ", cursor.getString(0));
            map.put("M_DATE", cursor.getString(1));
            map.put("S_DATE", cursor.getString(2));
            map.put("M_TIME", cursor.getString(3));
            map.put("S_TIME", cursor.getString(4));
            map.put("M_MEMO", cursor.getString(5));
            map.put("S_MEMO", cursor.getString(6));
            map.put("M_SIZE", cursor.getString(7));
            map.put("S_SIZE", cursor.getString(8));
            list.add(map);
        }
        dbhelper.close();           // DB연결 해제
        return list;
    }

    //수면목록조회
    public ArrayList<HashMap<String, String>> selectAllSleep(Context context, CareVO vo){

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        dbhelper = new DBHelper(context);
        SQLiteDatabase db= dbhelper.getReadableDatabase(); //데이터베이스 연결

        String sql = "select * from BABYCARE where S_DATE = ? order by IDX ";
        Cursor cursor=db.rawQuery(sql, new String[] {vo.getS_date()}); // sql구문 실행
        while (cursor.moveToNext()){                      // 결과(Resultset)를 list에 담기
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("IDX ", cursor.getString(0));
            map.put("M_DATE", cursor.getString(1));
            map.put("S_DATE", cursor.getString(2));
            map.put("M_TIME", cursor.getString(3));
            map.put("S_TIME", cursor.getString(4));
            map.put("M_MEMO", cursor.getString(5));
            map.put("S_MEMO", cursor.getString(6));
            map.put("M_SIZE", cursor.getString(7));
            map.put("S_SIZE", cursor.getString(8));
            list.add(map);
        }
        dbhelper.close();           // DB연결 해제
        return list;
    }


    //인서트1(분유)
    public void milkInsert(Context context, CareVO Cvo){
        dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        //현재시간 설정
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat nowDATE = new SimpleDateFormat("yyyy-M-d");
        String formatDate = nowDATE.format(mDate);
        SimpleDateFormat nowTIME = new SimpleDateFormat("mm:ss");
        String formatTIME = nowTIME.format(mDate);
        contentValues.put("M_DATE", formatDate); // 현재시간 넣기
        contentValues.put("M_TIME", formatTIME); // 현재시간 넣기
        contentValues.put("M_MEMO", Cvo.getM_Memo()); // 메모내용 넣기
        contentValues.put("M_SIZE", Cvo.getM_Size()); // 우유용량 넣기

        db.insert(tableName, null, contentValues);
        System.out.println("저장");
        dbhelper.close();
    }


    //인서트2(수면)
    public void sleepInsert(Context context, CareVO Cvo){
        dbhelper = new DBHelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        //현재시간 설정
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat nowDATE = new SimpleDateFormat("yyyy-M-d");
        String formatDate = nowDATE.format(mDate);
        SimpleDateFormat nowTIME = new SimpleDateFormat("mm:ss");
        String formatTIME = nowTIME.format(mDate);
        contentValues.put("S_DATE", formatDate); // 현재시간 넣기
        contentValues.put("S_TIME", formatTIME); // 현재시간 넣기 // 현재시간 넣기
        contentValues.put("S_MEMO", Cvo.getS_Memo()); // 메모내용 넣기
        contentValues.put("S_SIZE", Cvo.getS_Size()); // 우유용량 넣기

        db.insert(tableName, null, contentValues);
        System.out.println("저장");
        dbhelper.close();

    }

    //목록조회
    public ArrayList<HashMap<String, String>> selectMilk(Context context, CareVO vo){

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        dbhelper = new DBHelper(context);
        SQLiteDatabase db= dbhelper.getReadableDatabase(); //데이터베이스 연결

        String sql = "select IFNULL(SUM(M_SIZE), 0) as m_sum from BABYCARE where M_DATE = ? ";
        Cursor cursor=db.rawQuery(sql, new String[] {vo.getM_date()}); // sql구문 실행

        while (cursor.moveToNext()){ // 결과(Resultset)를 list에 담기
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("m_sum", cursor.getString(0));
            list.add(map);
        }
        dbhelper.close();           // DB연결 해제
        return list;
    }

    //목록조회
    public ArrayList<HashMap<String, String>> selectSleep(Context context, CareVO vo){

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        dbhelper = new DBHelper(context);
        SQLiteDatabase db= dbhelper.getReadableDatabase(); //데이터베이스 연결

        String sql = "select IFNULL(SUM(S_SIZE), 0) as s_sum from BABYCARE where S_DATE = ? ";
        Cursor cursor=db.rawQuery(sql, new String[] {vo.getS_date()}); // sql구문 실행

        while (cursor.moveToNext()){ // 결과(Resultset)를 list에 담기
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("s_sum", cursor.getString(0));
            list.add(map);
        }
        dbhelper.close();           // DB연결 해제
        return list;
    }



}
