package com.example.a1to50;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import com.example.a1to50.db.RankDAO;

import java.util.ArrayList;
import java.util.HashMap;

public class RankingActivity extends Activity{
	
	 private RankDAO m_dao = null;
	 private ListView m_lv = null;
	 private BaseAdapter m_ba = null; 
	 private ArrayList<HashMap<String,String>> _DataSource = null; 
	
	 public void onCreate(Bundle savedInstanceState) {
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		 
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.rank);	  
		 
		 try{
		      m_dao = RankDAO.GetInstance(this);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 Refresh();
		 
		 m_ba = new SimpleAdapter( this
				                  ,_DataSource
				                  ,android.R.layout.simple_list_item_2 
				                  ,new String[]{"Name","Score"}
		                          ,new int[]{android.R.id.text1, android.R.id.text2}
				                  );
		 
		 
		 m_lv = (ListView)findViewById(R.id.lvRank);
		 m_lv.setAdapter(m_ba);
		 
	 }
	 
	 public void Refresh(){
		 _DataSource = m_dao.GetRankData();
	 }
	 	 
}
