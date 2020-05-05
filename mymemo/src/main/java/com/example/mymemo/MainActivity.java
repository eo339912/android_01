package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //메모작성 버튼 클릭 이벤트
        Button newMemo = (Button)findViewById(R.id.btnNewMemo);
        newMemo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MemoActivity.class);
                startActivity(intent);

            }
        });

        //리스트 뷰
        load_values();

        //
        ListView listview = findViewById(R.id.lvMemo);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
            // get item
                Map<String, String> item = (Map) parent.getItemAtPosition(position);

                String name = item.get("title") ;
                String content = item.get("time") ;

                Intent intent = new Intent(getApplicationContext(), MemoActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("content", content);
                startActivity(intent);


            }
        });
    }

    private void load_values() {
        //MVC 중  Model
        ArrayList<HashMap<String, String>> list =  new MemoDAO().selectAll(getApplicationContext());


        //2. listview에 customAdapter 연결
        ListViewAdapter adapter = new ListViewAdapter();
        adapter.setList(list);
        ListView lvCustom = findViewById(R.id.lvMemo);
        lvCustom.setAdapter(adapter);



    } //end of load_value
}
