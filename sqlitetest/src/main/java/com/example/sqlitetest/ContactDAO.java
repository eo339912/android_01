package com.example.sqlitetest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class ContactDAO {
    private Object dbhelper;

    //목록조회
    public ArrayList<HashMap<String, String>> selectAll(Context context){

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        DBHelper dbhelper = new DBHelper(context);
        SQLiteDatabase db=dbhelper.getReadableDatabase(); //데이터베이스 연결
        String sql = "select _no, name, phone, over20 from CONTACT_T order by _no ";
        Cursor cursor=db.rawQuery(sql, null);   //sql 구문
        while (cursor.moveToNext()){                        //결과(Resultset)를 list에 담기
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("_no", cursor.getString(0));
            map.put("name", cursor.getString(1));
            map.put("phone", cursor.getString(2));
            map.put("over20", cursor.getString(3));
            list.add(map);
        }
        dbhelper.close();                                   //db연결 해제
        return list;
    }
    //등록
    public void insert(Context context, ContactVO vo){
        DBHelper dbhelper = new DBHelper(context);
        SQLiteDatabase db=dbhelper.getReadableDatabase();

        String sqlInsert = "INSERT INTO CONTACT_T " +
                "(_NO, NAME, PHONE, OVER20) VALUES (" +
                vo.get_no() + "," +
                "'" + vo.getName() + "'," +
                "'" + vo.getPhone() + "'," +
                vo.getOver20() + ")" ;

        db.execSQL(sqlInsert);
        dbhelper.close();
    }

    //수정

    //삭제

    //단건조회
}
