package com.example.a1to50;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import androidx.annotation.Nullable;

import com.example.a1to50.db.RankDAO;

public class ContentsActivity extends Activity {
    View[] include = new View[2];
    TextView now_number;  //위 버튼 숫자
    RelativeLayout[] button = new RelativeLayout[25];
    TextView[] button_text = new TextView[25];
    RelativeLayout ok_button;
    TextView text;
    long start;
    private Dialog          m_input_dlg = null;
    private RankDAO m_dao       = null;            /* R_Rank DAO   */
    TextView rankbutton;
    AudioManager aaaaa;
    // aaaaa = (AudioManager)getSystemService(Context.AUDIO_SERVICE);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);
        all_view();
        include[0].setVisibility(View.VISIBLE);    //gone view 숨기는거
        include[1].setVisibility(View.GONE);
        game();
        all_button_click();
        start = System.nanoTime();

        try{
            m_dao = RankDAO.GetInstance(this);
        }catch(Exception e){

            e.printStackTrace();
        }
    }


    int number_group1, number_group2;
    private void game() {
        now_number.setText(Integer.toString(match_number_int));
        for(number_group1 = 1; number_group1 <= 2; number_group1++) {
            for(number_group2 = 1; number_group2 <= 25; number_group2++) {
                random();
            }
        }
        for(int print_button_num = 0; print_button_num <= 24; print_button_num++) {
            button_text[print_button_num].setText(Integer.toString(button_number_01[print_button_num]));
        }
    }


    int match_number_int = 1;
    int random_button_number_int;
    int[] button_number_01 = new int[25];
    int[] button_number_02 = new int[25];
    private void random() {
        Random random = new Random();
        random_button_number_int = random.nextInt(25);
        if(number_group1 == 1 && button_number_01[(random_button_number_int)] == 0) {
            button_number_01[(random_button_number_int)] = number_group2;
        }else if(number_group1 == 1 && number_group2 <= 25){
            random();
        }
        if(number_group1 == 2 && button_number_02[(random_button_number_int)] == 0) {
            button_number_02[(random_button_number_int)] = (number_group2+25);
        }else if(number_group1 == 2 && number_group2 <= 25){
            random();
        }
    }


    private void all_view() {
        include[0] = findViewById(R.id.include_game_view);
        include[1] = findViewById(R.id.include_success_view);
        now_number = (TextView)findViewById(R.id.now_number);
        button[0] = (RelativeLayout)findViewById(R.id.button_1);
        button[1] = (RelativeLayout)findViewById(R.id.button_2);
        button[2] = (RelativeLayout)findViewById(R.id.button_3);
        button[3] = (RelativeLayout)findViewById(R.id.button_4);
        button[4] = (RelativeLayout)findViewById(R.id.button_5);
        button[5] = (RelativeLayout)findViewById(R.id.button_6);
        button[6] = (RelativeLayout)findViewById(R.id.button_7);
        button[7] = (RelativeLayout)findViewById(R.id.button_8);
        button[8] = (RelativeLayout)findViewById(R.id.button_9);
        button[9] = (RelativeLayout)findViewById(R.id.button_10);
        button[10] = (RelativeLayout)findViewById(R.id.button_11);
        button[11] = (RelativeLayout)findViewById(R.id.button_12);
        button[12] = (RelativeLayout)findViewById(R.id.button_13);
        button[13] = (RelativeLayout)findViewById(R.id.button_14);
        button[14] = (RelativeLayout)findViewById(R.id.button_15);
        button[15] = (RelativeLayout)findViewById(R.id.button_16);
        button[16] = (RelativeLayout)findViewById(R.id.button_17);
        button[17] = (RelativeLayout)findViewById(R.id.button_18);
        button[18] = (RelativeLayout)findViewById(R.id.button_19);
        button[19] = (RelativeLayout)findViewById(R.id.button_20);
        button[20] = (RelativeLayout)findViewById(R.id.button_21);
        button[21] = (RelativeLayout)findViewById(R.id.button_22);
        button[22] = (RelativeLayout)findViewById(R.id.button_23);
        button[23] = (RelativeLayout)findViewById(R.id.button_24);
        button[24] = (RelativeLayout)findViewById(R.id.button_25);
        button_text[0] = (TextView)findViewById(R.id.button_text_1);
        button_text[1] = (TextView)findViewById(R.id.button_text_2);
        button_text[2] = (TextView)findViewById(R.id.button_text_3);
        button_text[3] = (TextView)findViewById(R.id.button_text_4);
        button_text[4] = (TextView)findViewById(R.id.button_text_5);
        button_text[5] = (TextView)findViewById(R.id.button_text_6);
        button_text[6] = (TextView)findViewById(R.id.button_text_7);
        button_text[7] = (TextView)findViewById(R.id.button_text_8);
        button_text[8] = (TextView)findViewById(R.id.button_text_9);
        button_text[9] = (TextView)findViewById(R.id.button_text_10);
        button_text[10] = (TextView)findViewById(R.id.button_text_11);
        button_text[11] = (TextView)findViewById(R.id.button_text_12);
        button_text[12] = (TextView)findViewById(R.id.button_text_13);
        button_text[13] = (TextView)findViewById(R.id.button_text_14);
        button_text[14] = (TextView)findViewById(R.id.button_text_15);
        button_text[15] = (TextView)findViewById(R.id.button_text_16);
        button_text[16] = (TextView)findViewById(R.id.button_text_17);
        button_text[17] = (TextView)findViewById(R.id.button_text_18);
        button_text[18] = (TextView)findViewById(R.id.button_text_19);
        button_text[19] = (TextView)findViewById(R.id.button_text_20);
        button_text[20] = (TextView)findViewById(R.id.button_text_21);
        button_text[21] = (TextView)findViewById(R.id.button_text_22);
        button_text[22] = (TextView)findViewById(R.id.button_text_23);
        button_text[23] = (TextView)findViewById(R.id.button_text_24);
        button_text[24] = (TextView)findViewById(R.id.button_text_25);
        ok_button = (RelativeLayout)findViewById(R.id.ok_button);
        text = (TextView)findViewById(R.id.test);
        rankbutton = (TextView)findViewById(R.id.rank);
    }
   private void vib(){
       Vibrator vibrator;
       vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
       aaaaa = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

       if(aaaaa.getRingerMode()!=AudioManager.RINGER_MODE_SILENT ){
           vibrator.vibrate(100);
       }



   }
    private void sound(){
//        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), uri);
//        Uri notification = Uri.parse("android.resource://com.example.a1to50/raw/sound.mp3");
//        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);

        MediaPlayer player = MediaPlayer.create(this, R.raw.sound);
        aaaaa = (AudioManager)getSystemService(Context.AUDIO_SERVICE);


      if(aaaaa.getRingerMode()==AudioManager.RINGER_MODE_NORMAL){
          player.start();
       }


//        r.play();
    };

    int button_num;
    private void all_button_click() {


        for (int i = 0; i < 25; i++) {
            final int j = i;
            button[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(match_number_int == 50) {
                        include[1].setVisibility(View.VISIBLE);
                        include[0].setVisibility(View.GONE);
                        long end = System.nanoTime();
                        double time = (end - start) / 1000000000.0;
                        text.setText(String.valueOf(time));

                    }
                    int number = j;
                    if(match_number_int == button_number_01[number]) {
                        sound();
                        vib();
                        match_number_int += 1;
                        button_text[number].setText(Integer.toString(button_number_02[number]));
                    }
                    if(match_number_int == button_number_02[number]) {
                        sound();
                        vib();
                        match_number_int += 1;
                        button_text[number].setVisibility(View.GONE);
                    }
                    now_number.setText(Integer.toString(match_number_int));
                }
            });
        }
        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                match_number_int = 1;
                for(int reset_button_number = 0; reset_button_number <= 24; reset_button_number++) {
                    button_number_01[reset_button_number] = 0;
                    button_number_02[reset_button_number] = 0;
                    button_text[reset_button_number].setVisibility(View.VISIBLE);
                }
                include[0].setVisibility(View.VISIBLE);
                include[1].setVisibility(View.GONE);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
//                game();
            }
        });
        rankbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialog();
            }
        });
    }

    public View.OnKeyListener on_key = new View.OnKeyListener(){

        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if(event.getAction() == KeyEvent.ACTION_DOWN ){
                if(keyCode == KeyEvent.KEYCODE_ENTER){

                    try{
                        String playerName = ((EditText)m_input_dlg.findViewById(R.id.etName)).getText().toString();

                        if(playerName == null || playerName.length() == 0){
                            playerName = "Unknown Player";
                        }

                        String strTime = text.getText().toString();



                        m_dao.Append( playerName, Double.parseDouble(strTime));


                    }catch(Exception ig){
                    }

                    m_input_dlg.dismiss();
                    GoRankView();
                    return true;
                }
            }

            return false;
        }

    };

    public void ShowDialog(){
        m_input_dlg = new Dialog(this);
        m_input_dlg.setContentView(R.layout.input_dialog);
         
        ((EditText)m_input_dlg.findViewById(R.id.etName)).setOnKeyListener(on_key);;
        m_input_dlg.show();
    }

    public void GoRankView(){
        Intent it = new Intent(this, com.example.a1to50.RankingActivity.class);
        it.putExtra("text", "view ranking");
        startActivityForResult(it, 0);
    }
}
