package com.rutuja.android.myquizapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimesUp {
    private Context context;
    private Dialog timerDialog;
    private TextView textView;

    public TimesUp(Context context) {
        this.context = context;
    }

    public void timeUpDialog(){
        timerDialog = new Dialog(context);
        timerDialog.setContentView(R.layout.times_up);

        final Button timerBtn = timerDialog.findViewById(R.id.timesUpOk);
        timerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerDialog.dismiss();
                Intent intent = new Intent(context,PlayAgain.class);
                context.startActivity(intent);
            }
        });

        timerDialog.show();
        timerDialog.setCancelable(false);
        timerDialog.setCanceledOnTouchOutside(false);
    }
}
