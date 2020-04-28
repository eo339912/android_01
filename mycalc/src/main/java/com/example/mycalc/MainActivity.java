package com.example.mycalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdd = findViewById(R.id.btnPlus);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText num1 = findViewById(R.id.txtNum1);
                EditText num2 = findViewById(R.id.txtNum2);
                TextView result = findViewById(R.id.txtResult);

                String sn1 = num1.getText().toString();
                String sn2 = num2. getText().toString();

                int intResult = Integer.parseInt(sn1) + Integer.parseInt(sn2);
                result.setText(String.valueOf(intResult));

            }
        });
    }
}
