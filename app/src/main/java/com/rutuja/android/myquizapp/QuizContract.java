package com.rutuja.android.myquizapp;

import android.provider.BaseColumns;

public class QuizContract {

    public QuizContract() {}

    public static class QuestionTable implements BaseColumns{
        public static final String TABLE_NAME = "PlayQuiz";

        public static final String COLUMN_QUESTION = "questions";
        public static final String COLUMN_OPTION_1 = "option1";
        public static final String COLUMN_OPTION_2 = "option2";
        public static final String COLUMN_OPTION_3 = "option3";
        public static final String COLUMN_OPTION_4 = "option4";
        public static final String COLUMN_CORRECT_ANS = "ans";
        public static final String COLUMN_CATEGORY = "category";
    }
}
