package com.example.eventtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);

        //1. 무명(익명)클래스
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //System.out.println("버튼 1 클릭");
                Toast.makeText(getApplicationContext(), "버튼 1클릭", Toast.LENGTH_LONG).show();
            }
        });

        //2. 인터페이스 구현클래스
        button2.setOnClickListener(new ButtonHandler());


        //4. 하나의 이벤트 핸들러 여러개의 view(컴포넌트) 제어
        Button.OnClickListener myclick = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( v.getId() == R.id.button4){
                    Toast.makeText(getApplicationContext(), "버튼 4클릭", Toast.LENGTH_LONG).show();
                }else if(v.getId() == R.id.button5){
                    Toast.makeText(getApplicationContext(), "버튼 5클릭", Toast.LENGTH_LONG).show();
                }
            }
        };
        button4.setOnClickListener(myclick);
        button5.setOnClickListener(myclick);

    }//end of onCreate method

        //3. veiw onclick =" butPlusListener"
    public void butPlusListener(View v) {
        Toast.makeText(getApplicationContext(), "버튼 3클릭", Toast.LENGTH_LONG).show();
    }

    //2에대한 내부클래스 정의
    class ButtonHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //System.out.println("버튼 2 클릭");
            Toast.makeText(getApplicationContext(), "버튼 2클릭", Toast.LENGTH_LONG).show();
        }
    }//end of EventActivity class
}
