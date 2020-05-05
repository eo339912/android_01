package com.example.tway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SleepActivity extends AppCompatActivity {

    ImageButton myBtnStart;
    EditText SleepMemo;
    TextView myOutput;
    TextView txtWakeup;

    ImageButton btnSave;
    ImageButton btnHome;

    final static int Init =0;
    final static int Run =1;
    final static int Pause =2;

    int cur_Status = Init; //현재의 상태를 저장할변수를 초기화함.
    int myCount=1;
    long myBaseTime;
    long myPauseTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        myOutput = findViewById(R.id.timeOut);
        myBtnStart = findViewById(R.id.btn_start);
        txtWakeup = findViewById(R.id.txtWakeup);
        SleepMemo = findViewById(R.id.SleepMemo);
        btnSave = findViewById(R.id.btnSave);
        btnHome = findViewById(R.id.btnHome);

        //+,- btnclick 시 이벤트
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info_insert();
                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

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

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
    }

    public void myOnClick(View v){
        switch(v.getId()){
            case R.id.btn_start: //시작버튼을 클릭했을때 현재 상태값에 따라 다른 동작을 할수있게끔 구현.
                switch(cur_Status){
                    case Init:
                        myBaseTime = SystemClock.elapsedRealtime();
                        System.out.println(myBaseTime);
                        //myTimer이라는 핸들러를 빈 메세지를 보내서 호출
                        myTimer.sendEmptyMessage(0);
                        txtWakeup.setText("아기가 일어나면 터치해주세요");
//                        myBtnRec.setEnabled(true); //기록버튼 활성
                        cur_Status = Run; //현재상태를 런상태로 변경
                        break;
                    case Run:
                        myTimer.removeMessages(0); //핸들러 메세지 제거
                        myPauseTime = SystemClock.elapsedRealtime();
                        txtWakeup.setText("아기가 잠들면 터치해 주세요!");
//                        myBtnStart.setText("시작");
//                        myBtnRec.setText("리셋");
                        cur_Status = Pause;
                        break;
                    case Pause:
                        long now = SystemClock.elapsedRealtime();
                        myTimer.sendEmptyMessage(0);
                        myBaseTime += (now- myPauseTime);
                        txtWakeup.setText("아기가 일어나면 터치해주세요");
//                        myBtnStart.setText("멈춤");
//                        myBtnRec.setText("기록");
                        cur_Status = Run;
                        break;


                }
                break;

        }
    }

    Handler myTimer = new Handler(){
        public void handleMessage(Message msg){
            myOutput.setText(getTimeOut());

            //sendEmptyMessage 는 비어있는 메세지를 Handler 에게 전송하는겁니다.
            myTimer.sendEmptyMessage(0);
        }
    };
    //현재시간을 계속 구해서 출력하는 메소드
    String getTimeOut(){
        long now = SystemClock.elapsedRealtime(); //애플리케이션이 실행되고나서 실제로 경과된 시간(??)^^;
        long outTime = now - myBaseTime;
        String easy_outTime = String.format("%02d:%02d:%02d", outTime/1000 / 60, (outTime/1000)%60,(outTime%1000)/10);
        return easy_outTime;

    }

    //수면저장
    public void info_insert() {

        String memo = SleepMemo.getText().toString();
        String size = myOutput.getText().toString();
        SimpleDateFormat transAtime = new SimpleDateFormat("hh:mm:ss");
        Date time = null;
        try {
            time = transAtime.parse(size);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar c = Calendar.getInstance();
        c.setTime(time);

        long hms = c.getTimeInMillis();
        String shms = Long.toString(hms);

        CareVO vo = new CareVO();
        vo.setS_Memo(memo);
        vo.setS_Size(shms);

        new CareDAO().sleepInsert(getApplicationContext(), vo);

    }
}
