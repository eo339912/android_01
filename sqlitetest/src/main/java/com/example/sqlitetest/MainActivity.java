package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sqliteDB = init_database(); //db 생성 or 오픈
        load_values(); //전체 조회

        //save 클릭이벤트
        Button buttonSave = findViewById(R.id.buttonSave) ;
        buttonSave.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_values() ;
            }
        });

        ListView listview = findViewById(R.id.lvCustom);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                Map<String, String> item = (Map) parent.getItemAtPosition(position);

                String name = item.get("name") ;
                String phone = item.get("phone") ;

                // TODO : use item data.
                Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();

            }
        }) ;

    } //end of onCreate

    private void save_values() {
        EditText editTextNo = (EditText) findViewById(R.id.editTextNo) ;
        String no = editTextNo.getText().toString() ;

        EditText editTextName = (EditText) findViewById(R.id.editTextName) ;
        String name = editTextName.getText().toString() ;

        EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone) ;
        String phone = editTextPhone.getText().toString() ;

        CheckBox checkBoxOver20 = (CheckBox) findViewById(R.id.checkBoxOver20) ;
        boolean isOver20 = checkBoxOver20.isChecked() ;

        ContactVO vo = new ContactVO();
        vo.set_no(Integer.parseInt(no));
        vo.setName(name);
        vo.setPhone(phone);
        vo.setOver20(isOver20 == true ? 1 : 0);
        new ContactDAO().insert(getApplicationContext(), vo);
        load_values(); //전체조회

    } //end of save_values

    private void load_values() {
        //MVC 중  Model
        ArrayList<HashMap<String, String>> list =  new ContactDAO().selectAll(getApplicationContext());

        //Adapter(=controller)
        SimpleAdapter simpleAdapter =
                new SimpleAdapter(
                    this,
                    list,   //model(data)
                    android.R.layout.simple_list_item_2,
                    new String[]{"_no", "name"},
                    new int[]{android.R.id.text1, android.R.id.text2}
                );

        //ListView에 adapter 연결
        ListView listView = findViewById(R.id.lvCotact);
        listView.setAdapter(simpleAdapter);

        //2. listview에 customAdapter 연결
        ListViewAdapter adapter = new ListViewAdapter();
        adapter.setList(list);
        ListView lvCustom = findViewById(R.id.lvCustom);
        lvCustom.setAdapter(adapter);


//        TextView result = findViewById(R.id.textView);
//        String str = "";
//        for(HashMap<String, String> map : list){
//            str += map.get("_no") + ":" + map.get("name") + "\n";
//        }
//        result.setText(str);
    } //end of load_value




}
