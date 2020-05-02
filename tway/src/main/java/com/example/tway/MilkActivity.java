package com.example.tway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MilkActivity extends AppCompatActivity {
    ImageView imageView;
    LinearLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk);

//        textView = findViewById(R.id.textView);
//        imageView = findViewById(R.id.imageView);
//        view = findViewById(R.id.view);

//        CustomView m = new CustomView(this);
//        setContentView(m);

        //view.setOnTouchListener(touchListener);
    }


//    private View.OnTouchListener touchListener = new View.OnTouchListener() {
//
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//            switch (event.getAction()) {
//                //눌렀을때
//                case MotionEvent.ACTION_DOWN:
//                    prevY = event.getY();
//                    break;
//
//                //움직일때
//                case MotionEvent.ACTION_MOVE:
//                    float dy = prevY - event.getY();
//                    if (dy > 0 && d < 300) {
//                        d += 50;
//                        float mScale = getResources().getDisplayMetrics().density;
//                        int calHeight = (int) (d * mScale);
//                        textView.getLayoutParams().height = calHeight;
//
//                        //지금 그려진 화면을 무효화 하고 화면을 갱신한다(onDraw호출).
//                        textView.invalidate();
//
//
//                    } else if (dy < 0 && d > 0) {
//                        d -= 50;
//                        float mScale = getResources().getDisplayMetrics().density;
//                        int calHeight = (int) (d * mScale);
//                        textView.getLayoutParams().height = calHeight;
//
//                        //지금 그려진 화면을 무효화 하고 화면을 갱신한다(onDraw호출).
//                        textView.invalidate();
//                    }
//                    System.out.println(d);
//
//                    break;
//
//                case MotionEvent.ACTION_CANCEL:
//                case MotionEvent.ACTION_UP:
//                    break;
//            }
//
//            return true;
//        }
//
//
//
//
//    };




}



