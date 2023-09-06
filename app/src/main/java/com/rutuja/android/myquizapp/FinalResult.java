package com.rutuja.android.myquizapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinalResult {
    private Context context;
    private Dialog dialog;
    private TextView textViewScore;

    public FinalResult(Context context) {
        this.context = context;
    }

    public void finalScoreDialog(int rightAns, int wrongAns, int totalSizeOfQuiz){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.final_result);

        final Button scoreBtn = dialog.findViewById(R.id.ok);

        scoreCalculation(rightAns,wrongAns,totalSizeOfQuiz);

        scoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(context,MainActivity.class);
                context.startActivity(intent);
            }
        });
        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }


    private void scoreCalculation(int correctAns,int inCorrectAns, int totalSizeOfQuiz) {
        int tempScore = 0;
        textViewScore = dialog.findViewById(R.id.scoreFinal);

        if (correctAns == totalSizeOfQuiz){
            tempScore = (correctAns * 20) - (inCorrectAns * 5);
            textViewScore.setText("Final Score: "+String.valueOf(tempScore));
        }else if(inCorrectAns == totalSizeOfQuiz){
            tempScore = 0;
            textViewScore.setText("Final Score: "+String.valueOf(tempScore));
        }else  if (correctAns > inCorrectAns) {
            tempScore = (correctAns * 20) -  (inCorrectAns * 5);
            textViewScore.setText("Final Score: "+String.valueOf(tempScore));
        }else if(inCorrectAns > correctAns) {
            tempScore = (correctAns * 20) -  (inCorrectAns * 5);
            textViewScore.setText("Final Score: "+String.valueOf(tempScore));
        }else if (correctAns == inCorrectAns) {
            tempScore = (correctAns * 20) - (inCorrectAns * 5);
            textViewScore.setText("Final Score: "+String.valueOf(tempScore));
        }
    }
}
