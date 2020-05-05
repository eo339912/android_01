package com.example.tway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PrintActivity extends AppCompatActivity {

    TextView txtDate;
    Button btnDateRight;
    Button btnDateLeft;
    TextView txtGetMilk;
    TextView txtGetSleep;
    ImageButton btnHome;

    Calendar cal = Calendar.getInstance();
//    Date date = cal.getTime();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH) + 1;
    int day = cal.get(Calendar.DATE);
    int totaldate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //해당월의 최대 일수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);

        txtDate = findViewById(R.id.txtDate);
        btnDateRight = findViewById(R.id.btnDateRight);
        btnDateLeft = findViewById(R.id.btnDateLeft);
        txtGetMilk = findViewById(R.id.txtGetMilk);
        txtGetSleep = findViewById(R.id.txtGetSleep);
        btnHome = findViewById(R.id.btnHome);

        txtDate.setText(year + "-" + month + "-" + day);

        //left, right btn click
        dayBtnClick();

        //list
        listMilk();
        txtGetMilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listMilk();
            }
        });
        txtGetSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listSleep();
            }
        });

        //setMilkSum
        setMilkSum();

        //setSleepSum
        setSleepSum();


        //btnHome 클릭 시
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Home
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);

            }
        });




    }

    //left, right btn click
    public void dayBtnClick(){
        btnDateLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(day>1 && day <= totaldate){
                    day--;
                    cal.set(year,month, day);
                }else if(day == 1){ //1일
                    if(month > 1){ // a월 1일
                        month--;
                        day = totaldate;
                        cal.set(year,month, day);
                    }else if(month == 1 && day ==1){ //1월 1일
                        month = 12;
                        day = totaldate;
                        year--;
                        cal.set(year,month, day);
                    }
                }

                txtDate.setText(year + "-" + month + "-" + day);

                listMilk();
                setMilkSum();
            }
        });

        btnDateRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(day >= 1 && day < 30){
                    day++;
                    cal.set(year,month, day);
                }else if(day >= 30){
                    if(month < 12){ // a월 30일
                        month++;
                        day = 1;
                        cal.set(year,month, day);
                    }else if(month == 12 && day ==30){ //12월 30일
                        month = 1;
                        day = 1;
                        year++;
                        cal.set(year,month, day);
                    }
                }

                txtDate.setText(year + "-" + month + "-" + day);

                listMilk();
                setMilkSum();
            }
        });
    }

    public void listMilk() {
        CareVO vo = new CareVO();
        String Mdate = txtDate.getText().toString();
        vo.setM_date(Mdate);
        //MVC 중  Model
        ArrayList<HashMap<String, String>> list =  new CareDAO().selectAllMilk(getApplicationContext(), vo);


        //listview에 customAdapter 연결
        ListViewAdapter adapter = new ListViewAdapter();
        adapter.setList(list);
        ListView lvCustom = findViewById(R.id.lvCare);
        lvCustom.setAdapter(adapter);

    } //end of load_value

    public void listSleep() {
        CareVO vo = new CareVO();
        String Sdate = txtDate.getText().toString();
        vo.setS_date(Sdate);
        //MVC 중  Model
        ArrayList<HashMap<String, String>> list =  new CareDAO().selectAllSleep(getApplicationContext(), vo);


        //listview에 customAdapter 연결
        ListViewAdapter2 adapter = new ListViewAdapter2();
        adapter.setList(list);
        ListView lvCustom = findViewById(R.id.lvCare);
        lvCustom.setAdapter(adapter);

    } //end of load_value


    public void setMilkSum(){
        CareVO vo = new CareVO();
        String Mdate = txtDate.getText().toString();
        vo.setM_date(Mdate);

        //MVC 중  Model
        ArrayList<HashMap<String, String>> list =  new CareDAO().selectMilk(getApplicationContext(), vo);

        String str = "";
        for(HashMap<String, String> map : list){
            str += map.get("m_sum");
        }
        txtGetMilk.setText("총 " + str + "ml");
    }

    public void setSleepSum() {
        CareVO vo = new CareVO();
        String Sdate = txtDate.getText().toString();
        vo.setS_date(Sdate);

        //MVC 중  Model
        ArrayList<HashMap<String, String>> list =  new CareDAO().selectSleep(getApplicationContext(), vo);

        String str = "";
        for(HashMap<String, String> map : list){
            str += map.get("s_sum"); //ss.SSS
        }
        Long hms = Long.parseLong(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(hms);

        int mYear = calendar.get(Calendar.HOUR);
        int mMonth = calendar.get(Calendar.MINUTE);
        int mDay = calendar.get(Calendar.SECOND);

        txtGetSleep.setText("총 " + mYear + ":" + mMonth + ":" + mDay);
    }
}
