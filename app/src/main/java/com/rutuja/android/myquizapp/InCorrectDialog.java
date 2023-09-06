package com.rutuja.android.myquizapp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InCorrectDialog {
    private Context context;
    private Dialog wrongDialog;
    private MainActivity mMainActivity;


    public InCorrectDialog(Context context) {
        this.context = context;
    }

    public void wrongDialog(String correctAns, MainActivity mainActivity){
        mMainActivity = mainActivity;
        wrongDialog = new Dialog(context);
        wrongDialog.setContentView(R.layout.wrong_answer);

        Button btnWrongDialog = wrongDialog.findViewById(R.id.wrongOk);
        TextView txtCorrectAns = wrongDialog.findViewById(R.id.inCorrectAns);

        txtCorrectAns.setText("Answer - "+String.valueOf(correctAns));

        btnWrongDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongDialog.dismiss();
                mainActivity.showQuestions();

            }
        });
        wrongDialog.show();
        wrongDialog.setCancelable(false);
        wrongDialog.setCanceledOnTouchOutside(false);

        wrongDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }


}
