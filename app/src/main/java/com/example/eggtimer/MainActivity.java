package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import com.example.eggtimer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    Boolean counterIsActive = false;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        activityMainBinding = activityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        int max = 600;
        int defaultTime = 30;

        activityMainBinding.timeSeekBar.setMax(max);
        activityMainBinding.timeSeekBar.setProgress(defaultTime);
        activityMainBinding.timeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minutes = progress/60;
                int seconds = progress - minutes*60;

                String secondString = Integer.toString(seconds);

                if (secondString.equals("0")){
                    secondString = "00";
                }

                activityMainBinding.timeTxtView.setText(Integer.toString(minutes) + ":" + secondString);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void resetTimer(){
        activityMainBinding.timeTxtView.setText("0:30");
        activityMainBinding.timeSeekBar.setProgress(30);
        activityMainBinding.timeSeekBar.setEnabled(true);
        countDownTimer.cancel();
        activityMainBinding.goButton.setText("Go!");
        counterIsActive = false;

    }

    public void buttonClicked(View view) {

        if (counterIsActive){
            resetTimer();

        }else {

            counterIsActive = true;
            activityMainBinding.timeSeekBar.setEnabled(false);
            activityMainBinding.goButton.setText("STOP!");


            countDownTimer = new CountDownTimer(activityMainBinding.timeSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();
                    resetTimer();
                }

            }.start();
        }
    }

    public void updateTimer(int secondsLeft){
        int minutes = secondsLeft/60;
        int seconds = secondsLeft - minutes*60;

        String secondString = Integer.toString(seconds);

        if (seconds <= 9){
            secondString = "0"+secondString;
        }

        activityMainBinding.timeTxtView.setText(Integer.toString(minutes) + ":" + secondString);
    }
}