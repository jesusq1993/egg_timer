package com.example.eggtimer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    SeekBar timeSeekBar;
    TextView timeTextView;
    long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTextView = findViewById(R.id.timeTextView);
        timeSeekBar = findViewById(R.id.timeSeekBar);

        int defaultTime = 30;
        int max = 600;

        timeSeekBar.setMax(max);
        timeSeekBar.setProgress(defaultTime);
        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minutes = progress/60;
                int seconds = progress - (minutes*60);

                String secondString = Integer.toString(seconds);

                if(secondString.equals("0")){
                    secondString = "00";
                }

                timeTextView.setText(Integer.toString(minutes) + ":" + secondString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void startTimer(View view){
        new CountDownTimer(time,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeTextView.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
            }

            @Override
            public void onFinish() {

                timeTextView.setText("Done!!");
            }
        }.start();
    }
}