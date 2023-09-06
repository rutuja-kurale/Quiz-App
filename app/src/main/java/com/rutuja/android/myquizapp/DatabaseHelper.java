package com.rutuja.android.myquizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rutuja.android.myquizapp.QuizContract.QuestionTable;

import java.util.ArrayList;

class DatabaseHelper extends SQLiteOpenHelper {

   private static final String DATABASE_NAME = "GoQuiz.db";
   private static final int DATABASE_VERSION = 2;

   private SQLiteDatabase db;

   DatabaseHelper(Context context) {
       super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
       this.db = db;

       final String SQL_CREATE_TABLE_QUERY = " CREATE TABLE IF NOT EXISTS " +
               QuestionTable.TABLE_NAME + " ( " +
               QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
               QuestionTable.COLUMN_QUESTION + " TEXT NOT NULL, " +
               QuestionTable.COLUMN_OPTION_1 + " TEXT NOT NULL, " +
               QuestionTable.COLUMN_OPTION_2 + " TEXT NOT NULL, " +
               QuestionTable.COLUMN_OPTION_3 + " TEXT NOT NULL, " +
               QuestionTable.COLUMN_OPTION_4 + " TEXT NOT NULL, " +
               QuestionTable.COLUMN_CORRECT_ANS + " INTEGER NOT NULL," +
               QuestionTable.COLUMN_CATEGORY + " TEXT " + ") ";

       db.execSQL(SQL_CREATE_TABLE_QUERY);
       fillQuestionsTable();
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

       db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
       onCreate(db);

   }

   private void fillQuestionsTable() {
       //quiz for programming
       Quiz q1 = new Quiz("What two words every programmer learned to code first?", "Hello, world", "“Hello dear,.”", "Welcome World", "Bye World", 1, Quiz.CATEGORY_PROGRAMMING);
       addQuestions(q1);

       Quiz q2 = new Quiz("_______ is the process of finding errors and fixing them within a program.", "Compiling", "Executing", "Debugging", "Scanning", 1, Quiz.CATEGORY_PROGRAMMING);
       addQuestions(q2);

       Quiz q3 = new Quiz("Which command will stop an infinite loop?", "Alt - C", "Shift - C", "Esc", "Ctrl - C", 4, Quiz.CATEGORY_PROGRAMMING);
       addQuestions(q3);

       Quiz q4 = new Quiz("Sayali needs to execute a section of code ten times within a program. Compare the selection structures below and select which one meets the needs identified", "If-else", "for", "while", "If", 2, Quiz.CATEGORY_PROGRAMMING);
       addQuestions(q4);

       Quiz q5 = new Quiz("RAM stands for ", "Windows", "Drivers", "GUI", "Random Access Memory", 4, Quiz.CATEGORY_PROGRAMMING);
       addQuestions(q5);

       Quiz q6 = new Quiz("Which of the following is the correct way to use the standard namespace in C++?", "Using namespace std;", "Using namespace standard;", "Using standard namespace;", "Standard namespace used;", 1, Quiz.CATEGORY_PROGRAMMING);
       addQuestions(q6);

       Quiz q7 = new Quiz("Which of the following is themselves a collection of different data types?", "String", "Structures", "char", "All the above", 2, Quiz.CATEGORY_PROGRAMMING);
       addQuestions(q7);

       Quiz q8 = new Quiz("What does HTML stand for? ", "Hyper Trainer Marking Language", "Hyper Text Marketing Language", "Hyper Text Markup Language", "Hyper Text Markup Leveler", 3, Quiz.CATEGORY_PROGRAMMING);
       addQuestions(q8);

       Quiz q9 = new Quiz("It is an electronic device capable of performing complex computations in a short time.", "Application", "Operating System", "Software", "Computer", 4, Quiz.CATEGORY_PROGRAMMING);
       addQuestions(q9);

       Quiz q10 = new Quiz("To get the most accurate value we prefer the data type", "Int", "Long Int", "Float", "Double", 4, Quiz.CATEGORY_PROGRAMMING);
       addQuestions(q10);

       //quiz for python
       Quiz q11 = new Quiz("What is the value of this expression? \n2**2**3**1", "12", "64", "128", "256", 4, Quiz.CATEGORY_PYTHON);
       addQuestions(q11);

       Quiz q12 = new Quiz("What is the output of the following code?\n" +
               ">>> list = [‘a’, ‘b’, ‘c’]\n" +
               ">>> list += ‘de’\n" +
               ">>> print(list)", " [‘a’, ‘b’, ‘c’, ‘d’, ‘e’]", " [‘a’, ‘b’, ‘c’, ‘de’]", " [‘ade’, ‘bde’, ‘cde’]", " This raises an exception because we cannot add a string to a list", 1, Quiz.CATEGORY_PYTHON);
       addQuestions(q12);

       Quiz q13 = new Quiz("Who developed the Python language?", "Zim Den", "Guido van Rossum", "Niene Stom", "Wick van Rossum", 3, Quiz.CATEGORY_PYTHON);
       addQuestions(q13);

       Quiz q14 = new Quiz("What do we use to define a block of code in Python language?", "Key", "Brackets", "Indentation", "None of these", 3, Quiz.CATEGORY_PYTHON);
       addQuestions(q14);

       Quiz q15 = new Quiz("Which of the following is not a keyword in Python language?", "val", "raise", "try", "with", 3, Quiz.CATEGORY_PYTHON);
       addQuestions(q15);

       Quiz q16 = new Quiz("Which of the following operators is the correct option for power(ab)?", "a ^ b", "a**b", "a ^ ^ b", "a ^ * b", 2, Quiz.CATEGORY_PYTHON);
       addQuestions(q16);

       Quiz q17 = new Quiz(" Which of the following functions is a built-in function in python language?", "val()", "print()", "printf()", "None of these", 2, Quiz.CATEGORY_PYTHON);
       addQuestions(q17);

       Quiz q18 = new Quiz("Study the following function:\n" +
               "round(4.576) \n What will be the output of this function?  ", "4", "4.5", "576", "5", 4, Quiz.CATEGORY_PYTHON);
       addQuestions(q18);

       Quiz q19 = new Quiz("Which of the following is correctly evaluated for this function?\n" +
               "pow(x,y,z)  ", "(x**y) / z", "(x / y) * z", "(x**y) % z", "(x / y) / z", 3, Quiz.CATEGORY_PYTHON);
       addQuestions(q19);

       Quiz q20 = new Quiz(" The output to execute string.ascii_letters can also be obtained from:?", "character", "ascii_lowercase_string.digits", "lowercase_string.upercase", "ascii_lowercase+string.ascii_upercase\n", 4, Quiz.CATEGORY_PYTHON);
       addQuestions(q20);


       //quiz for java
       Quiz q21 = new Quiz("_____ is used to find and fix bugs in the Java programs.", "JVM", "JRE", "JDK", "JDB", 4, Quiz.CATEGORY_JAVA);
       addQuestions(q21);

       Quiz q22 = new Quiz("What is the return type of the hashCode() method in the Object class?", "Object", "int", "long", "void", 2, Quiz.CATEGORY_JAVA);
       addQuestions(q22);

       Quiz q23 = new Quiz("Which of the following tool is used to generate API documentation in HTML format from doc comments in source code?", "javap tool", "javaw command", "Javadoc tool", "javah command", 3, Quiz.CATEGORY_JAVA);
       addQuestions(q23);

       Quiz q24 = new Quiz("Which method of the Class.class is used to determine the name of a class represented by the class object as a String?", "getClass()", "intern()", "getName()", "toString()", 3, Quiz.CATEGORY_JAVA);
       addQuestions(q24);

       Quiz q25 = new Quiz("An interface with no fields or methods is known as a ______.", "Runnable Interface", "Marker Interface", "Abstract Interface", "CharSequence Interface", 2, Quiz.CATEGORY_JAVA);
       addQuestions(q25);

       Quiz q26 = new Quiz("In which memory a String is stored, when we create a string using new operator?", "Stack", "String memory", "Heap memory", "Random storage space", 3, Quiz.CATEGORY_JAVA);
       addQuestions(q26);

       Quiz q27 = new Quiz(" In java, jar stands for_____.", "Java Archive Runner", "Java Application Resource", "Java Application Runner", "None of the above", 4, Quiz.CATEGORY_JAVA);
       addQuestions(q27);

       Quiz q28 = new Quiz("Given,\n" +
               "ArrayList list = new ArrayList();  \n" +
               "What is the initial quantity of the ArrayList list?", "5", "10", "0", "100", 2, Quiz.CATEGORY_JAVA);
       addQuestions(q28);

       Quiz q29 = new Quiz("How many threads can be executed at a time?", "Only one thread", "Multiple threads", "Only main (main() method) thread", "Two threads", 2, Quiz.CATEGORY_JAVA);
       addQuestions(q29);

       Quiz q30 = new Quiz("If a thread goes to sleep", "It releases all the locks it has.", "It does not release any locks.", "It releases half of its locks.", "It releases all of its lock except one.", 2, Quiz.CATEGORY_JAVA);
       addQuestions(q30);

       //quiz for dbms
       Quiz q31 = new Quiz("What are the different view to present a Table ?", "Datasheet View", "Design View", "Pivote TableView", "All Of Above", 4, Quiz.CATEGORY_DBMS);
       addQuestions(q31);
       Quiz q32 = new Quiz("In one-to-many relationship the table on 'many' side is called _______", "Parent", "Child", "Master", "Sister", 4, Quiz.CATEGORY_DBMS);
       addQuestions(q32);
       Quiz q33 = new Quiz("In which state one gathers and list all the necessary fields for the database design project.", "Data Definition", "Data Refinement", "Establishing Relationship", "None Of The Above", 1, Quiz.CATEGORY_DBMS);
       addQuestions(q33);
       Quiz q34 = new Quiz("It is used to establish an association between related tables.", "Line", "Relationship", "Primary Key", "Records", 2, Quiz.CATEGORY_DBMS);
       addQuestions(q34);
       Quiz q35 = new Quiz("This key that uniquely identifies each record is called :", "Primary Key", "Key Record", "Unique Key", "Field Name", 1, Quiz.CATEGORY_DBMS);
       addQuestions(q35);
       Quiz q36 = new Quiz("The third stage of designing a database is when we create___________ between tables", "Relationship", "Join", "Query", "None of These", 1, Quiz.CATEGORY_DBMS);
       addQuestions(q36);
       Quiz q37 = new Quiz("In a database Table, the each category of information Is called __________", "Tuple", "Field", "Record", "All Of Above", 2, Quiz.CATEGORY_DBMS);
       addQuestions(q37);
       Quiz q38 = new Quiz("Which of the following fields has width of 8 bytes?", "Memo", "Number", "Date/time", "Hyperlink", 3, Quiz.CATEGORY_DBMS);
       addQuestions(q38);
       Quiz q39 = new Quiz("Which of the following is not a database model", "Network Database Model", "Relational Database Model", "Object Oriented Database Model", "None", 4, Quiz.CATEGORY_DBMS);
       addQuestions(q39);
       Quiz q40 = new Quiz("A data dictionary is a repository that manages _____", "Memory", "Metadata", "Spell Checker", "Data Validator", 2, Quiz.CATEGORY_DBMS);
       addQuestions(q40);

       //quiz for Data structures
       Quiz q41 = new Quiz("The purpose of the bubble sorting algorithm is what?", "To speed up the search of an item in the list", "To sort the contents of the list", "Both choices above", "Both choices above", 3, Quiz.CATEGORY_DATA_STRUCTURES);
       addQuestions(q41);
       Quiz q42 = new Quiz("Which of the following data structures falls under the category of a 'dictionary'?", "Linked list", "Tree", "Hash table", "Hash", 3, Quiz.CATEGORY_DATA_STRUCTURES);
       addQuestions(q42);
       Quiz q43 = new Quiz("What is the time complexity to insert a node based on position in a priority queue?", "O(nlogn)", "O(logn)", "O(n)", "O(n)^2", 3, Quiz.CATEGORY_DATA_STRUCTURES);
       addQuestions(q43);
       Quiz q44 = new Quiz(" How many stacks are required for evaluation of prefix expression?", "1", "2", "3", "4", 2, Quiz.CATEGORY_DATA_STRUCTURES);
       addQuestions(q44);
       Quiz q45 = new Quiz("What is the other name for a postfix expression?", "Normal polish Notation", "Reverse polish Notation", "Warsaw notation", "Infix notation", 2, Quiz.CATEGORY_DATA_STRUCTURES);
       addQuestions(q45);
       Quiz q46 = new Quiz("The result of the postfix expression 5 3 * 9 + 6 / 8 4 / + is _____________", "8", "6", "10", "9", 2, Quiz.CATEGORY_DATA_STRUCTURES);
       addQuestions(q46);
       Quiz q47 = new Quiz("What is the time complexity of reversing a word using stack algorithm?", "O(NlogN)", "O(N2)", "O(N)", "O(MlogN)", 3, Quiz.CATEGORY_DATA_STRUCTURES);
       addQuestions(q47);
       Quiz q48 = new Quiz("How will you implement dynamic arrays in Java?", "Set", "Map", "HashMap", "List", 4, Quiz.CATEGORY_DATA_STRUCTURES);
       addQuestions(q48);
       Quiz q49 = new Quiz("Which of the following is a disadvantage of dynamic arrays?", "Locality of reference", "Random access", "Data cache utilization", "Memory leak", 4, Quiz.CATEGORY_DATA_STRUCTURES);
       addQuestions(q49);
       Quiz q50 = new Quiz("Minimum number of moves required to solve a Tower of Hanoi puzzle is", " 2n^2", " 2^(n-1)", "2^n - 1", "2n - 1", 2, Quiz.CATEGORY_DATA_STRUCTURES);
       addQuestions(q50);
       //quiz for ml
       Quiz q51 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_ML);
       addQuestions(q51);
       Quiz q52 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_ML);
       addQuestions(q52);
       Quiz q53 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_ML);
       addQuestions(q53);
       Quiz q54 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_ML);
       addQuestions(q54);
       Quiz q55 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_ML);
       addQuestions(q55);
       Quiz q56 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_ML);
       addQuestions(q56);
       Quiz q57 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_ML);
       addQuestions(q57);
       Quiz q58 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_ML);
       addQuestions(q58);
       Quiz q59 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_ML);
       addQuestions(q59);
       Quiz q60 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_ML);
       addQuestions(q60);
       //quiz for cloud computing
       Quiz q61 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_CC);
       addQuestions(q61);
       Quiz q62 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_CC);
       addQuestions(q62);
       Quiz q63 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_CC);
       addQuestions(q63);
       Quiz q64 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_CC);
       addQuestions(q64);
       Quiz q65 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_CC);
       addQuestions(q65);
       Quiz q66 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_CC);
       addQuestions(q66);
       Quiz q67 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_CC);
       addQuestions(q67);
       Quiz q68 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_CC);
       addQuestions(q68);
       Quiz q69 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_CC);
       addQuestions(q69);
       Quiz q70 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_CC);
       addQuestions(q70);
       // quiz for cyber security
       Quiz q71 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_COMPUTER_SECURITY);
       addQuestions(q71);
       Quiz q72 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_COMPUTER_SECURITY);
       addQuestions(q72);
       Quiz q73 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_COMPUTER_SECURITY);
       addQuestions(q73);
       Quiz q74 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_COMPUTER_SECURITY);
       addQuestions(q74);
       Quiz q75 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_COMPUTER_SECURITY);
       addQuestions(q75);
       Quiz q76 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_COMPUTER_SECURITY);
       addQuestions(q76);
       Quiz q77 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_COMPUTER_SECURITY);
       addQuestions(q77);
       Quiz q78 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_COMPUTER_SECURITY);
       addQuestions(q78);
       Quiz q79 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_COMPUTER_SECURITY);
       addQuestions(q79);
       Quiz q80 = new Quiz("", "", "", "", "", 2, Quiz.CATEGORY_COMPUTER_SECURITY);
       addQuestions(q80);

   }

   private void addQuestions(Quiz question) {
       ContentValues cv = new ContentValues();
       cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
       cv.put(QuestionTable.COLUMN_OPTION_1, question.getOpt1());
       cv.put(QuestionTable.COLUMN_OPTION_2, question.getOpt2());
       cv.put(QuestionTable.COLUMN_OPTION_3, question.getOpt3());
       cv.put(QuestionTable.COLUMN_OPTION_4, question.getOpt4());
       cv.put(QuestionTable.COLUMN_CORRECT_ANS, question.getAnswerOpt());
       cv.put(QuestionTable.COLUMN_CATEGORY, question.getCategory());
       db.insert(QuestionTable.TABLE_NAME, null, cv);
   }


   public ArrayList<Quiz> getAllQuestions(String category) {
       ArrayList<Quiz> questionList = new ArrayList<>();
       db = getReadableDatabase();

       String Projection[] = {
               QuestionTable._ID,
               QuestionTable.COLUMN_QUESTION,
               QuestionTable.COLUMN_OPTION_1,
               QuestionTable.COLUMN_OPTION_2,
               QuestionTable.COLUMN_OPTION_3,
               QuestionTable.COLUMN_OPTION_4,
               QuestionTable.COLUMN_CORRECT_ANS
       };

//        here we are using cursor to traverse through all question list
       Cursor c = db.query(QuestionTable.TABLE_NAME,
               Projection,
               null,
               null,
               null,
               null,
               null);
       if (c.moveToFirst()) {
           do {
               Quiz question = new Quiz();
               question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
               question.setOpt1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION_1)));
               question.setOpt2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION_2)));
               question.setOpt3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION_3)));
               question.setOpt4(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION_4)));
               question.setAnswerOpt(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_CORRECT_ANS)));

               questionList.add(question);

           } while (c.moveToNext());

       }
       c.close();
       return questionList;
   }
}