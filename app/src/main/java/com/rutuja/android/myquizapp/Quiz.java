package com.rutuja.android.myquizapp;

public class Quiz {

    public static final String CATEGORY_PROGRAMMING = "Programming";
    public static final String CATEGORY_PYTHON = "Python";
    public static final String CATEGORY_JAVA = "Java";
    public static final String CATEGORY_DBMS = "Dbms";
    public static final String CATEGORY_DATA_STRUCTURES = "DS";
    public static final String CATEGORY_ML = "ML";
    public static final String CATEGORY_CC = "Cc";
    public static final String CATEGORY_COMPUTER_SECURITY = "Computer_Security";

    private String question;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;
    private int answerOpt;
    private String category;


    public Quiz(){}

    public Quiz(String question, String opt1, String opt2, String opt3, String opt4, int answerOpt,String category) {
        this.question = question;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.answerOpt = answerOpt;
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }

    public int getAnswerOpt() {
        return answerOpt;
    }

    public void setAnswerOpt(int answerOpt) {
        this.answerOpt = answerOpt;
    }

    public String getCategory() {
        return String.valueOf(category);
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
