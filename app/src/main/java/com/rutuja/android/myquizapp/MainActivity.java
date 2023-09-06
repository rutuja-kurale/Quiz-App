package com.rutuja.android.myquizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCountDown;


    private ArrayList<Quiz> questionList;
    private int questionCounter;
    private int questionTotalCount;
    private Quiz currentQuestions;
    private boolean answered;


    private final Handler handler = new Handler();

    private int correctAns = 0, wrongAns = 0;

    private TimesUp timerDialog;
    private correctDialog correctDialog;
    private InCorrectDialog wrongDialog;

    private AudioAlertForAnswer playAudioForAnswers;

    int FLAG = 0;

    int score =0;

    private int totalSizeofQuiz=0;

    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private CountDownTimer countDownTimer;
    private long timeLeft;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();

        fetchDB();
        Log.i("BUGBUG","onCreate() in QuizActivity");

        timerDialog = new TimesUp(this);
        correctDialog = new correctDialog(this);
        wrongDialog = new InCorrectDialog(this);
        playAudioForAnswers = new AudioAlertForAnswer(this);
    }



    private void setupUI(){
        textViewQuestion = findViewById(R.id.show_question);
        textViewScore = findViewById(R.id.score);
        textViewQuestionCount = findViewById(R.id.totalQues);
        textViewCountDown = findViewById(R.id.time);

        rbGroup = findViewById(R.id.radio);
        rb1 = findViewById(R.id.button);
        rb2 = findViewById(R.id.button2);
        rb3 = findViewById(R.id.button3);
        rb4 = findViewById(R.id.button4);
        buttonConfirmNext = findViewById(R.id.next);
    }

    public void fetchDB() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        questionList = dbHelper.getAllQuestions();
        startQuiz();

    }


    public void startQuiz() {
        questionTotalCount = questionList.size();
        Collections.shuffle(questionList);

        showQuestions();

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {



                switch (checkedId){

                    case R.id.button:
                        rb1.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
                        rb2.setTextColor(Color.BLACK);
                        rb3.setTextColor(Color.BLACK);
                        rb4.setTextColor(Color.BLACK);

                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_selected));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));
                        break;

                    case R.id.button2:
                        rb2.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
                        rb1.setTextColor(Color.BLACK);
                        rb3.setTextColor(Color.BLACK);
                        rb4.setTextColor(Color.BLACK);

                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_selected));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));
                        break;

                    case R.id.button3:
                        rb3.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
                        rb2.setTextColor(Color.BLACK);
                        rb1.setTextColor(Color.BLACK);
                        rb4.setTextColor(Color.BLACK);

                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_selected));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));
                        break;

                    case R.id.button4:
                        rb4.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
                        rb2.setTextColor(Color.BLACK);
                        rb3.setTextColor(Color.BLACK);
                        rb1.setTextColor(Color.BLACK);

                        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));
                        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));
                        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));
                        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.option_selected));
                        break;
                }
            }
        });

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {

                        quizOperation();
                    } else {

                        Toast.makeText(MainActivity.this, "Please Select the Answer ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    public void showQuestions() {
        rbGroup.clearCheck();
        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));
        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));
        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));
        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.buttons_design));

        rb1.setTextColor(Color.BLACK);
        rb2.setTextColor(Color.BLACK);
        rb3.setTextColor(Color.BLACK);
        rb4.setTextColor(Color.BLACK);


        if (questionCounter < questionTotalCount) {
            currentQuestions = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestions.getQuestion());
            rb1.setText(currentQuestions.getOpt1());
            rb2.setText(currentQuestions.getOpt2());
            rb3.setText(currentQuestions.getOpt3());
            rb4.setText(currentQuestions.getOpt4());

            questionCounter++;
            answered = false;
            buttonConfirmNext.setText("Confirm");

            textViewQuestionCount.setText("Questions: " + questionCounter + "/" + questionTotalCount);
            timeLeft = COUNTDOWN_IN_MILLIS;
            startCountDown();

        } else {
            // If Number of Questions Finishes then we need to finish the Quiz and Shows the User Quiz Performance
            Toast.makeText(this, "Quiz Finished", Toast.LENGTH_SHORT).show();

            rb1.setClickable(false);
            rb2.setClickable(false);
            rb3.setClickable(false);
            rb4.setClickable(false);
            buttonConfirmNext.setClickable(false);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    finalResult();

                }
            }, 2000);
        }
    }

    private void quizOperation() {
        answered = true;
        countDownTimer.cancel();
        RadioButton rbselected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbselected) + 1;

        checkSolution(answerNr, rbselected);

    }


    private void checkSolution(int answerNr, RadioButton rbselected) {

        switch (currentQuestions.getAnswerOpt()) {
            case 1:
                if (currentQuestions.getAnswerOpt() == answerNr) {

                    rb1.setBackground(ContextCompat.getDrawable(
                            this, R.drawable.correct_answer));
                    rb1.setTextColor(Color.BLACK);
                    correctAns++;

                    score += 10;
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score,this);

                    FLAG = 1;
                    playAudioForAnswers.setAudio(FLAG);

                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;

                    String correctAnswer = (String) rb1.getText();
                    wrongDialog.wrongDialog(correctAnswer,this);

                    FLAG = 2;
                    playAudioForAnswers.setAudio(FLAG);
                }
                break;
            case 2:
                if (currentQuestions.getAnswerOpt() == answerNr) {
                    rb2.setBackground(ContextCompat.getDrawable(this, R.drawable.correct_answer));
                    rb2.setTextColor(Color.BLACK);
                    correctAns++;

                    score += 10;
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score,this);

                    FLAG = 1;
                    playAudioForAnswers.setAudio(FLAG);

                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;

                    String correctAnswer = (String) rb2.getText();
                    wrongDialog.wrongDialog(correctAnswer,this);

                    FLAG = 2;
                    playAudioForAnswers.setAudio(FLAG);
                }
                break;
            case 3:
                if (currentQuestions.getAnswerOpt() == answerNr) {

                    rb3.setBackground(ContextCompat.getDrawable(this, R.drawable.correct_answer));
                    rb3.setTextColor(Color.BLACK);
                    correctAns++;
                    score += 10;
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score,this);

                    FLAG = 1;
                    playAudioForAnswers.setAudio(FLAG);



                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;

                    String correctAnswer = (String) rb3.getText();
                    wrongDialog.wrongDialog(correctAnswer,this);

                    FLAG = 2;
                    playAudioForAnswers.setAudio(FLAG);
                }
                break;
            case 4:
                if (currentQuestions.getAnswerOpt() == answerNr) {
                    rb4.setBackground(ContextCompat.getDrawable(this, R.drawable.correct_answer));
                    rb4.setTextColor(Color.BLACK);
                    correctAns++;
                    score += 10;
                    textViewScore.setText("Score: " + String.valueOf(score));
                    correctDialog.correctDialog(score,this);

                    FLAG = 1;
                    playAudioForAnswers.setAudio(FLAG);

                } else {
                    changetoIncorrectColor(rbselected);
                    wrongAns++;
                    String correctAnswer = (String) rb4.getText();
                    wrongDialog.wrongDialog(correctAnswer,this);

                    FLAG = 2;
                    playAudioForAnswers.setAudio(FLAG);
                }
                break;
        }
        if (questionCounter == questionTotalCount) {
            buttonConfirmNext.setText("Confirm and Finish");
        }
    }

    void changetoIncorrectColor(RadioButton rbselected) {
        rbselected.setBackground(ContextCompat.getDrawable(this, R.drawable.wrong_answer));
        rbselected.setTextColor(Color.BLACK);
    }
    //  the timer code

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeft,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeft = 0;
                updateCountDownText();
            }
        }.start();
    }

    private void updateCountDownText(){
        int minutes = (int) (timeLeft/1000) /60;
        int seconds = (int) (timeLeft/1000) % 60;

        //  String timeFormatted = String.format(Locale.getDefault(),"02d:%02d",minutes,seconds);

        String timeFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes, seconds);
        textViewCountDown.setText(timeFormatted);
        if (timeLeft < 10000){
            textViewCountDown.setTextColor(ContextCompat.getColor(this,R.color.red));

            FLAG = 3;
            playAudioForAnswers.setAudio(FLAG);


        }else {
            textViewCountDown.setTextColor(ContextCompat.getColor(this,R.color.white));
        }

        if (timeLeft == 0 ){
            Toast.makeText(this, "Times Up!", Toast.LENGTH_SHORT).show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    timerDialog.timeUpDialog();
                }
            },2000);
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("BUGBUG","onRestart() in QuizActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("BUGBUG","onStop() in QuizActivity");
        finish();

    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("BUGBUG","onPause() in QuizActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("BUGBUG","onResume() in QuizActivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("BUGBUG","onStart() in QuizActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer!=null){
            countDownTimer.cancel();
        }
        Log.i("BUGBUG","onDestroy() in QuizActivity");


    }


    private void finalResult(){

        Intent resultData = new Intent(MainActivity.this,ResultActivity.class);

        resultData.putExtra("UserScore",score);
        resultData.putExtra("TotalQuestion",questionTotalCount);
        resultData.putExtra("CorrectQues",correctAns);
        resultData.putExtra("WrongQues",wrongAns);
        startActivity(resultData);

    }


    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){

            Intent intent = new Intent(MainActivity.this,Quiz.class);
            startActivity(intent);

        }else {
            Toast.makeText(this, "Press Again to Exit", Toast.LENGTH_SHORT).show();

        }
        backPressedTime = System.currentTimeMillis();
    }
}