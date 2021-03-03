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

        int defaultTime = 10;
        int max = 30;

        timeSeekBar.setProgress(defaultTime);
        timeSeekBar.setMax(max);
        timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int min = 1;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress < min){
                    seekBar.setProgress(min);
                    time = seekBar.getProgress();
                }else{
                    time = seekBar.getProgress();
                }
                time = time*60000;
                timeTextView.setText(new SimpleDateFormat("mm:ss").format(new Date( time)));
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