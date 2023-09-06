package com.rutuja.android.myquizapp;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TransferQueue;

public class correctDialog {
    private Context context;
    private Dialog correct_dialog;
    private MainActivity mMainActivity;

    public correctDialog(Context context) {
        this.context = context;
    }

    public void correctDialog(int score, MainActivity mainActivity){
        mMainActivity = mainActivity;
        correct_dialog = new Dialog(context);
        correct_dialog.setContentView(R.layout.correct_answer);

        Button btnCorrectDialog = correct_dialog.findViewById(R.id.rightOk);

        score(score);

        btnCorrectDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correct_dialog.dismiss();
                mainActivity.showQuestions();
            }
        });
        correct_dialog.show();
        correct_dialog.setCancelable(false);
        correct_dialog.setCanceledOnTouchOutside(false);
    }

    private void score(int score) {
        TextView textViewScore = correct_dialog.findViewById(R.id.correctAns);
        textViewScore.setText("score : "+String.valueOf(score));
    }
}
