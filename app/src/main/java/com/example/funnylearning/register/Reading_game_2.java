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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funnylearning.Bean.FindWordsBean;
import com.example.funnylearning.Bean.model.UserGameRecord;
import com.example.funnylearning.Database.FindWordsDao;
import com.example.funnylearning.Database.UserGameRecordDao;
import com.example.funnylearning.Database.UserGoalLevelDao;
import com.example.funnylearning.Homepage;
import com.example.funnylearning.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Reading_game_2 extends AppCompatActivity {

    //Initialization of variables
    TextView scoreTextView;
    TextView timeLeftTextView;
    TextView alertTextView;
    TextView finalScoreTextView;

    ProgressBar timeLeftProgressBar;

    TextView btn1TextView;
    TextView btn2TextView;
    TextView btn3TextView;

    Button startBtn;
    Button playAgainBtn;
    Button quitBtn;

    LinearLayout normalUI;
    ConstraintLayout finalUI;
    ConstraintLayout ansUI;

    CountDownTimer countDownTimer;
    Random random =new Random();
    int display;
    int indexOfCorrectAnswer;
    ArrayList<String> answers = new ArrayList<String>();
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
        setContentView(R.layout.activity_reading_game2);

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

        mp_btn = MediaPlayer.create(this,R.raw.game_button_click_sound);
        /*float log1=(float)(Math.log(maxVolume-currVolume)/Math.log(maxVolume));*/
        mp_btn.setVolume(100,100);

        btn1TextView = findViewById(R.id.reading_level_2_text_1);
        btn2TextView = findViewById(R.id.reading_level_2_text_2);
        btn3TextView = findViewById(R.id.reading_level_2_text_3);

        startBtn = findViewById(R.id.start_btn);
        playAgainBtn = findViewById(R.id.btnPlayAgain);
        quitBtn = findViewById(R.id.btnQuit);

        normalUI = findViewById(R.id.normalUI);
        finalUI = findViewById(R.id.finalUI);
        ansUI =findViewById(R.id.ansUI);

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
        btn1TextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    btn1TextView.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            optionSelect(view);
                        }
                    }, 500);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    btn1TextView.startAnimation(scaleUp);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    btn1TextView.startAnimation(scaleDown);
                }
                return true;
            }
        });

        //button 2
        btn2TextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    btn2TextView.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            optionSelect(view);
                        }
                    }, 500);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    btn2TextView.startAnimation(scaleUp);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    btn2TextView.startAnimation(scaleDown);
                }
                return true;
            }
        });

        //button 3
        btn3TextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    btn3TextView.startAnimation(scaleDown);
                    view.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            optionSelect(view);
                        }
                    }, 500);
                }else if (motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    mp_btn.start();
                    btn3TextView.startAnimation(scaleUp);

                }else if (motionEvent.getAction()==MotionEvent.ACTION_MOVE){
                    btn3TextView.startAnimation(scaleDown);
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
                    Reading_game_2.this.finish();

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

        FindWordsDao dao = new FindWordsDao(this);
        dao.open();                                             //open database

        ArrayList<FindWordsBean> words = dao.getAllWords();     //get questions

        display = random.nextInt(words.size());                 //random index for questions
        indexOfCorrectAnswer = random.nextInt(3);

        answers.clear();
        for(int i = 0; i<3; i++){

            if(indexOfCorrectAnswer == i){
                answers.add(words.get(display).correct);
            }else {
                if(!answers.contains(words.get(display).wrong1))
                {
                    answers.add(words.get(display).wrong1);
                }
                else
                {
                    answers.add(words.get(display).wrong2);
                }
            }
        }

        btn1TextView.setText(answers.get(0));
        btn2TextView.setText(answers.get(1));
        btn3TextView.setText(answers.get(2));
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
            alertTextView.setText("Correct Answer: " + (answers.get(indexOfCorrectAnswer)));
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


        scoreTextView.setText(Integer.toString(points));  //set score

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
            mp_game_over.stop();
        }
    }


    //start game
    public void start(View view) {

        mp_countdown = MediaPlayer.create(this,R.raw.countdown_sound);
        mp_game_over = MediaPlayer.create(this,R.raw.game_over_sound);
        mp_countdown.setVolume(100,100);
        mp_game_over.setVolume(100,100);

        startBtn.setVisibility(View.INVISIBLE);
        normalUI.setVisibility(View.VISIBLE);
        NextQuestion();
        countDownTimer = new CountDownTimer(60000,1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftTextView.setText(String.valueOf(millisUntilFinished/1000)); //time left
                if (progress >= 80)         //progress more than 80
                {
                    progress ++;
                    timeLeftProgressBar.setProgress(progress, true);
                }
                else                        //progress less than 80
                {
                    progress += 2;
                    timeLeftProgressBar.setProgress(progress, true);
                }

                if(progress >= 90)          //10 seconds left
                {
                    mp_countdown.start();
                }

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
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