package com.example.abhi.myapplication;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton,b0,b1,b2,b3,play;
    TextView tans,tt;
    TextView pt,tm;
    ArrayList<Integer> answers =new ArrayList<Integer>();
    RelativeLayout rl;
    int loc;
    int score=0;
    int num=0;
    public void playAgain(View view){
        score=0;
        num=0;
        tm.setText("30s");
        pt.setText("0/0");
        tans.setText("");
        play.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(3100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                tm.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                play.setVisibility(View.VISIBLE);
                tm.setText("0s");
                tans.setText("Your score"+Integer.toString(score)+"/"+Integer.toString(num));
            }
        };
    }
    public void generateQuestion(){
        Random rand =new Random();
        int a =rand.nextInt(21);
        int b =rand.nextInt(21);
        tt.setText(Integer.toString(a)+" + "+Integer.toString(b));
        loc=rand.nextInt(4);
        answers.clear();
        int incorrect;
        for(int i=0;i<4;i++)
        {
            if(i==loc){
                answers.add(a+b);
            }else{
                incorrect=rand.nextInt(41);
                while(incorrect == a+b){
                    incorrect=rand.nextInt(41);
                }
                answers.add(incorrect);
            }
        }
        b0.setText(Integer.toString(answers.get(0)));
        b1.setText(Integer.toString(answers.get(1)));
        b2.setText(Integer.toString(answers.get(2)));
        b3.setText(Integer.toString(answers.get(3)));
    }
    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(loc))){
            score++;
            tans.setText("Correct");
        }
        else{
            tans.setText("Incorrect");
        }
        num++;
        pt.setText(Integer.toString(score)+"/"+Integer.toString(num));
        generateQuestion();

    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        rl.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton=(Button)findViewById(R.id.startButton);
         tt=(TextView)findViewById(R.id.sumTextView);
         b0=(Button)findViewById(R.id.button0);
         b1=(Button)findViewById(R.id.button1);
         b2=(Button)findViewById(R.id.button2);
         b3=(Button)findViewById(R.id.button3);
        tans=(TextView)findViewById(R.id.resultTextView);
        pt=(TextView)findViewById(R.id.pointsTextView);
        tm=(TextView)findViewById(R.id.timerTextView);
        play=(Button)findViewById(R.id.playAgainButton);
        rl=(RelativeLayout)findViewById(R.id.gameRelativeLayout);

    }
}
