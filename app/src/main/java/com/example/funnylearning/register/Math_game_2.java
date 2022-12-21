package com.example.funnylearning.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funnylearning.Bean.model.UserGameRecord;
import com.example.funnylearning.Database.UserGameRecordDao;
import com.example.funnylearning.Database.UserGoalLevelDao;
import com.example.funnylearning.Homepage;
import com.example.funnylearning.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Math_game_2 extends AppCompatActivity {

    //Initialization of variables
    TextView scoreTextView;
    TextView timeLeftTextView;
    TextView alertTextView;
    TextView finalScoreTextView;

    ProgressBar timeLeftProgressBar;

    TextView questionTextView;
    TextView btn1TextView;
    TextView btn2TextView;
    TextView btn3TextView;
    TextView btn4TextView;

    FrameLayout btn1;
    FrameLayout btn2;
    FrameLayout btn3;
    FrameLayout btn4;
    Button startBtn;
    Button playAgainBtn;
    Button quitBtn;

    LinearLayout normalUI;
    ConstraintLayout finalUI;
    ConstraintLayout ansUI;

    CountDownTimer countDownTimer;
    Random random =new Random();
    int a;
    int b;
    int indexOfCorrectAnswer;
    int wrongAnswer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int points = 0;
    int progress = 0;
    int achievement = 80;

    Animation scaleUp,scaleDown;

    MediaPlayer mp_btn = new MediaPlayer();
    MediaPlayer mp_correct = new MediaPlayer();
    MediaPlayer mp_wrong = new MediaPlayer();
    MediaPlayer mp_countdown = new MediaPlayer();
    MediaPlayer mp_game_over = new MediaPlayer();

    int userId = 0;
    int gameId = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game2);

        //Getting user and game id from previous page
        Bundle extra = getIntent().getExtras();
        if(extra != null){
            userId = extra.getInt("userId");
            gameId = extra.getInt("gameId");
        }else{
            Toast.makeText(this, "Id not passed", Toast.LENGTH_SHORT).show();
        }

        //Declaration of variables
        scoreTextView = findViewById(R.id.scoreTextView);
        timeLeftTextView = findViewById(R.id.timeLeftTextView);
        alertTextView = findViewById(R.id.alertTextView);
        finalScoreTextView = findViewById(R.id.finalScoreTextView);

        timeLeftProgressBar = findViewById(R.id.circular_progress_bar);

        questionTextView = findViewById(R.id.math_level_2_question);
        btn1TextView = findViewById(R.id.math_level_2_fish_txt1);
        btn2TextView = findViewById(R.id.math_level_2_fish_txt2);
        btn3TextView = findViewById(R.id.math_level_2_fish_txt3);
        btn4TextView = findViewById(R.id.math_level_2_fish_txt4);

        btn1 = findViewById(R.id.math_level_2_fish_1);
        btn2 = findViewById(R.id.math_level_2_fish_2);
        btn3 = findViewById(R.id.math_level_2_fish_3);
        btn4 = findViewById(R.id.math_level_2_fish_4);

        startBtn = findViewById(R.id.start_btn);
        playAgainBtn = findViewById(R.id.btnPlayAgain);
        quitBtn = findViewById(R.id.btnQuit);

        mp_btn = MediaPlayer.create(this,R.raw.game_button_click_sound);
        mp_btn.setVolume(100,100);


        normalUI = findViewById(R.id.normalUI);
        finalUI = findViewById(R.id.finalUI);
        ansUI = findViewById(R.id.ansUI);

        startBtn.setVisibility(View.VISIBLE);
        normalUI.setVisibility(View.INVISIBLE);
        finalUI.setVisibility(View.INVISIBLE);
        ansUI.setVisibility(View.INVISIBLE);

        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        scoreTextView.setText(Integer.toString(points));

        //start button
        startBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    startBtn.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            start(view);
                        }
                    }, 500);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    startBtn.startAnimation(scaleUp);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    startBtn.startAnimation(scaleDown);
                }
                return true;
            }
        });

        //button 1
        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    btn1.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            optionSelect(view);
                        }
                    }, 500);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    btn1.startAnimation(scaleUp);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    btn1.startAnimation(scaleDown);
                }
                return true;
            }
        });

        //button 2
        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    btn2.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            optionSelect(view);
                        }
                    }, 500);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    btn2.startAnimation(scaleUp);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    btn2.startAnimation(scaleDown);
                }
                return true;
            }
        });

        //button 3
        btn3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    btn3.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            optionSelect(view);
                        }
                    }, 500);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    btn3.startAnimation(scaleUp);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    btn3.startAnimation(scaleDown);
                }
                return true;
            }
        });

        //button 4
        btn4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    btn4.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            optionSelect(view);
                        }
                    }, 500);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    btn4.startAnimation(scaleUp);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    btn4.startAnimation(scaleDown);
                }
                return true;
            }
        });

        //play again button
        playAgainBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    playAgainBtn.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            playAgain(view);
                        }
                    }, 300);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    playAgainBtn.startAnimation(scaleUp);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){

                    playAgainBtn.startAnimation(scaleDown);

                }

                return true;
            }
        });

        //quit button
        quitBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    quitBtn.startAnimation(scaleDown);
                    Math_game_2.this.finish();

                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    quitBtn.startAnimation(scaleUp);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){

                    quitBtn.startAnimation(scaleDown);

                }

                return true;
            }
        });

    }

    //render next question
    @SuppressLint("SetTextI18n")
    public void NextQuestion(){
        //question
        a = random.nextInt(11);
        b = random.nextInt(11);
        questionTextView.setText(a + "+" + b + "=?");

        //position of answer
        indexOfCorrectAnswer = random.nextInt(4);

        answers.clear();
        for(int i = 0; i<4; i++){

            if(indexOfCorrectAnswer == i){
                answers.add(a+b);
            }else {
                wrongAnswer = random.nextInt(21);
                while(wrongAnswer==a+b){

                    wrongAnswer = random.nextInt(21);

                }
                answers.add(wrongAnswer);
            }
        }

        btn1TextView.setText(Integer.toString(answers.get(0)));
        btn2TextView.setText(Integer.toString(answers.get(1)));
        btn3TextView.setText(Integer.toString(answers.get(2)));
        btn4TextView.setText(Integer.toString(answers.get(3)));

    }

    //on click
    @SuppressLint("SetTextI18n")
    public void optionSelect(View view){

        mp_correct = MediaPlayer.create(this,R.raw.correct_answer_sound);
        mp_correct.setVolume(100,100);

        mp_wrong = MediaPlayer.create(this,R.raw.wrong_answer_sound);
        mp_wrong.setVolume(100,100);

        //correct answer
        if(Integer.toString(indexOfCorrectAnswer).equals(view.getTag().toString())){
            mp_correct.start();
            points+=10;
            alertTextView.setText("Correct");
            alertTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,45);
        }
        //wrong answer
        else {
            mp_wrong.start();
            alertTextView.setText("Correct Answer: " + (a+b));
            alertTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,35);
        }

        onAnswer();

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("return answer");
                ansUI.setAlpha(0.5f);
                ansUI.setVisibility(View.INVISIBLE);
                ansUI.animate()
                        .alpha(0f)
                        .setDuration(200)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                if (finalUI.getVisibility() == View.INVISIBLE) {
                                    normalUI.setVisibility(View.VISIBLE);
                                }
                            }
                        });
            }
        }, 2300);

        //setting score
        scoreTextView.setText(Integer.toString(points));

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                System.out.println("next");
                NextQuestion();
            }
        }, 1000);
    }

    //answer page
    public void onAnswer(){
        System.out.println("onAnswer");
        normalUI.setVisibility(View.INVISIBLE);
        ansUI.setAlpha(0f);
        ansUI.setVisibility(View.VISIBLE);
        ansUI.animate()
                .alpha(0.5f)
                .setDuration(500)
                .setListener(null);
    }

    //play again reset game
    @SuppressLint("SetTextI18n")
    public void playAgain(View view){
        points=0;
        progress = 0;
        scoreTextView.setText(Integer.toString(points));
        timeLeftProgressBar.setProgress(0,true);
        countDownTimer.start();
        finalUI.setVisibility(View.INVISIBLE);
        normalUI.setVisibility(View.VISIBLE);
        if(mp_game_over.isPlaying())
        {
            //stop sound effect
            mp_game_over.stop();
        }
    }

    //start game
    public void start(View view) {

        mp_countdown = MediaPlayer.create(this,R.raw.countdown_sound);
        mp_countdown.setVolume(100,100);

        mp_game_over = MediaPlayer.create(this,R.raw.game_over_sound);
        mp_game_over.setVolume(100,100);

        startBtn.setVisibility(View.INVISIBLE);
        normalUI.setVisibility(View.VISIBLE);
        NextQuestion();
        countDownTimer = new CountDownTimer(60000,1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                //time left
                timeLeftTextView.setText(String.valueOf(millisUntilFinished/1000));
                //progress bar
                if (progress >= 80) //progress more than 80
                {
                    progress ++;
                    timeLeftProgressBar.setProgress(progress, true);
                }
                else                //progress less than 80
                {
                    progress += 2;
                    timeLeftProgressBar.setProgress(progress, true);
                }

                if(progress >= 90)  //10 seconds left
                {
                    mp_countdown.start();
                }

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {                                    //end screen
                finalScoreTextView.setText(Integer.toString(points));   //final score
                insertDatabase(points);                                 //game record
                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mp_game_over.start();
                        normalUI.setVisibility(View.INVISIBLE);
                        finalUI.setVisibility(View.VISIBLE);
                    }
                }, 1400);
            }

        }.start();
    }

    //update game record
    public void insertDatabase (int score){

        UserGameRecord userGameRecord =  new UserGameRecord();
        Date date = new Date(System.currentTimeMillis());

        UserGameRecordDao userGameRecordDao = new UserGameRecordDao(this);
        userGameRecordDao.open();

        userGameRecord.setDate(date);
        userGameRecord.setUserId(userId);
        userGameRecord.setGameId(gameId);
        userGameRecord.setScore(points);
        userGameRecordDao.insertScore(userGameRecord);


        if(points >= achievement)
        {
            UserGoalLevelDao userGoalLevelDao = new UserGoalLevelDao(this);
            userGoalLevelDao.open();

            userGoalLevelDao.updateLevel(userId,gameId);
        }
    }

    //clean up
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (countDownTimer!=null)
        {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        if(mp_btn.isPlaying())
        {
            mp_btn.stop();
            mp_btn.release();
        }
        if(mp_correct.isPlaying())
        {
            mp_correct.stop();
            mp_correct.release();
        }
        if(mp_wrong.isPlaying())
        {
            mp_wrong.stop();
            mp_wrong.release();
        }
        if(mp_game_over.isPlaying())
        {
            mp_game_over.stop();
            mp_game_over.release();
        }
        if(mp_countdown.isPlaying())
        {
            mp_countdown.stop();
            mp_countdown.release();
        }
    }
}