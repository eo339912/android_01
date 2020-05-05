package com.example.tway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MilkActivity extends AppCompatActivity {
    private PointF mCenter;
    private float mRadius;
    private float mMaxDist;
    private float box = 0.0f;
    Button milksizebox;
    TextView txtMilkSize;
    Button btnMilkDown;
    Button btnMilkUp;
    TextView MilkMemo;
    ImageButton btnSave;
    ImageButton btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk);

        txtMilkSize = findViewById(R.id.txtMilkSize);
        milksizebox = findViewById(R.id.milksizebox);
        btnMilkDown = findViewById(R.id.btnMilkDown);
        btnMilkUp = findViewById(R.id.btnMilkUp);
        MilkMemo = findViewById(R.id.SleepMemo);
        btnSave = findViewById(R.id.btnSave);
        btnHome = findViewById(R.id.btnHome);

        milksizebox.setOnTouchListener(touchListener);

        //btnSave 클릭 시  insert이벤트
        btnclick();

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

    //화면 터치이벤트
    private View.OnTouchListener touchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Rect rect = new Rect();
            v.getGlobalVisibleRect(rect); //RootView 레이아웃을 기준으로한 좌표(절대값)
            mRadius = milksizebox.getWidth() / 2;

            mMaxDist = milksizebox.getHeight() / 2;
            float x = rect.left + mRadius;
            float y = rect.top + mMaxDist;
            mCenter = new PointF(x, y);
            //중간값을 가져옴

            box = event.getY(); //v의  y값
            float d = event.getRawY(); //화면에서 y

            if ( mCenter.y > event.getRawY() ) { //박스의 센터값이 화면터치보다 크면
                box = mCenter.y - d + mMaxDist; //박스의 센터에서 화면터치값을 빼고
            }

            milksizebox.setLayoutParams(new RelativeLayout.LayoutParams(rect.bottom,(int)box ));
            // Float to int -> to String
            int iMilk = (int)(((int)box-41)*40/88);
            String sMilk = Integer.toString(iMilk);
            txtMilkSize.setText(sMilk);

            return true;
        }

    }; //end of touchListener

    //btnclick 시 이벤트
    private void btnclick(){
        //+버튼
        btnMilkUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rect rect = new Rect();
                v.getGlobalVisibleRect(rect); //RootView 레이아웃을 기준으로한 좌표(절대값)

                //중간값을 가져옴

                box += 129.0f; //박스의 센터에서 화면터치값을 빼고

                milksizebox.setLayoutParams(new RelativeLayout.LayoutParams(rect.bottom,(int)box ));
                // Float to int -> to String
                int iMilk = (int)(((int)box-41)*40/88);
                String sMilk = Integer.toString(iMilk);
                txtMilkSize.setText(sMilk);

            }
        });

        //-버튼
        btnMilkDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rect rect = new Rect();
                v.getGlobalVisibleRect(rect); //RootView 레이아웃을 기준으로한 좌표(절대값)

                box -= 129.0f; //박스의 센터에서 화면터치값을 빼고

                milksizebox.setLayoutParams(new RelativeLayout.LayoutParams(rect.bottom,(int)box ));
                // Float to int -> to String
                int iMilk = (int)(((int)box-41)*40/88);
                String sMilk = Integer.toString(iMilk);
                txtMilkSize.setText(sMilk);

            }
        });
    }

    //분유저장
    public void info_insert() {

        String memo = MilkMemo.getText().toString();
        String size = txtMilkSize.getText().toString();


        CareVO vo = new CareVO();
        vo.setM_Memo(memo);
        vo.setM_Size(size);

        new CareDAO().milkInsert(getApplicationContext(), vo);

    }





}
