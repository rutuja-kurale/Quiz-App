package com.rutuja.android.myquizapp;

import android.content.Context;
import android.media.MediaParser;
import android.media.MediaPlayer;

public class AudioAlertForAnswer {

    private Context context;

    public AudioAlertForAnswer(Context context){
        this.context = context;
    }

    public void setAudio(int flag){
        switch (flag){
            case 1:
                int correctAudio = R.raw.right;
                playAudio(correctAudio);
                break;
            case 2:
                int inCorrectAudio = R.raw.wrong;
                playAudio(inCorrectAudio);
                break;
            case 3:
                int timerAudio = R.raw.times_up;
                playAudio(timerAudio);
                break;
            default:
                stop(flag);
        }
    }

    public void playAudio(int audioFile) {
       MediaPlayer mediaPlayer = MediaPlayer.create(context,audioFile);
       mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
           @Override
           public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
           }
       });
       mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
           @Override
           public void onCompletion(MediaPlayer mp) {
               mediaPlayer.release();
           }
       });
    }

    public void stop(int audioFile){
        MediaPlayer mediaPlayer = MediaPlayer.create(context,audioFile);
        if (mediaPlayer.isPlaying()) {
            //pause music
            mediaPlayer.pause();
        }
    }

}
