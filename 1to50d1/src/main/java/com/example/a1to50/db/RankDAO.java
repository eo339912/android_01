package com.example.a1to50.db;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class RankDAO {
	
	private static RankDAO _instance = null;
	private static Activity _activity = null;
	private  SQLiteDatabase _db = null;
		
	private RankDAO() throws Exception{
		super();
		OpenDatabase();
	}
	
	public static synchronized RankDAO GetInstance(Activity act) throws Exception{
		
		if(_instance == null) {
			_activity = act;
			_instance = new RankDAO();			
		}
		
		return _instance;
	}

	private void OpenDatabase() throws Exception {
		try{
			if(_db == null){
				_db = _activity.openOrCreateDatabase("db", Activity.MODE_PRIVATE, null);				
				//_db.execSQL("drop table T_RANK");
				_db.execSQL("Create Table IF NOT EXISTS T_RANK (name varchar(30), score DECIMAL(7,2), inst_dt date) ");
				_db.execSQL("Create Index IF NOT EXISTS IDX_SCORE ON T_RANK (score asc)");
			}						
		}catch(Exception ig){
			throw ig;
		}
	}
	
	public void Append(String pName, double pScore) throws Exception{
		_db.execSQL("insert into t_rank (name, score, inst_dt) values('" + pName+ "'," + pScore + ",date('now')) ");
	}
	
	public ArrayList<HashMap<String,String>> GetRankData(){
		ArrayList<HashMap<String,String>> _Items = new ArrayList<HashMap<String,String>>();
		
		Cursor _cursor = _db.rawQuery("Select name, inst_dt || '   ' || score || '초' From T_RANK Order By score asc ", null);
		
		int nRank = 1;
		if(_cursor.moveToFirst()){
			do{
				HashMap<String,String> _Item = new HashMap<String,String>();
				_Item.put("Name", "No."+ nRank + "\t" + _cursor.getString(0).toString());
				_Item.put("Score", _cursor.getString(1).toString());
				
				_Items.add(_Item);
			    nRank++;
			}while(_cursor.moveToNext());		
			
			_cursor.close();
		}
		
		return _Items;
	}
}
