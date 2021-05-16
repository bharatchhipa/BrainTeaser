package com.example.brainteaser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    int locationOfCorrectAnswer;
    int a,b;
    TextView rightWrong;
    int score = 0;
    int questions = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;


    public void playAgain(View view){
        rightWrong.setVisibility(View.INVISIBLE);
        score = 0;
        questions =0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(questions));
        newQuestion();

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                rightWrong.setVisibility(View.VISIBLE);
                rightWrong.setText("Time Over");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();


        playAgainButton.setVisibility(View.INVISIBLE);
    }


    public void chooseAnswer(View view){
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            Log.i("correct", "you got it ");
            rightWrong.setVisibility(View.VISIBLE);
            rightWrong.setText("Correct");
            score++;
        }
        else{
            Log.i("Incorrect", "you not got it ");
            rightWrong.setVisibility(View.VISIBLE);
            rightWrong.setText("Incorrect");
        }
        questions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(questions));
        newQuestion();

    }


    public  void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    public void newQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(20-0);
        int b = rand.nextInt(20-0);

        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        answers.clear();

        locationOfCorrectAnswer = rand.nextInt(4);

        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else{
                int wrongAnswer = rand.nextInt(41);

                while(wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);

        goButton = findViewById(R.id.goButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);


        rightWrong = findViewById(R.id.rightWrong);

        scoreTextView = findViewById(R.id.scoreTextView);

        playAgainButton = findViewById(R.id.playAgainButton);

        timerTextView = findViewById(R.id.timerTextView);


        gameLayout = findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);

        goButton.setVisibility(View.VISIBLE);









    }
}