package com.example.tway;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View {
    int x=100, y=1203;

    private Paint paint;
    public CustomView(Context context) {
        super(context);
    }
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 뷰가 화면에 디스플레이 될때 자동으로 호출
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint(); // 페인트 객체 생성

        paint.setColor(Color.RED);
        paint.setTextSize(100);
        canvas.drawRect(268,y,647,1303, paint); // 사각형그림
//        canvas.drawRect(x,y,x-100,y-100, paint);
        int a = (1203-y)*280/636;
        String txt = Integer.toString(a);
        canvas.drawText(txt, 50, 500, paint); // 글자 출력

        System.out.println(x + ","+ y+ ","+ (x+100)+ ","+ (y+100));


    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 화면에 터치가 발생했을 때 호출되는 콜백 메서드
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN :
            case MotionEvent.ACTION_MOVE :
            case MotionEvent.ACTION_UP     :
                x = (int)event.getX();


                if(y >= 567 && y<= 1203){
                    y = (int)event.getY();
                }else if(y < 567){
                    y = 567;
                }else if(y > 1203){
                    y = 1203;
                }
                invalidate(); // 화면을 다시 그려줘라 => onDraw() 호출해준다
        }
        return true;
    }
} // end of class MyView